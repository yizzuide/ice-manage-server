package com.yizzuide.ice.api.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Knife4jConfiguration
 *
 * @author yizzuide
 * <br />
 * Create at 2022/10/09 22:57
 */
@Configuration
public class Knife4jConfiguration {
    @Bean
    public Docket createRestApi() {
        // DocumentationType.SWAGGER_2 -> Swagger 2
        // DocumentationType.OAS_30 -> Swagger 3
        return new Docket(DocumentationType.OAS_30)
                .enable(true)
                .apiInfo(new ApiInfoBuilder()
                        .title("Ice接口文档")
                        .description("Ice平台服务管理api")
                        .contact(new Contact("yizzuide", "http://yizzuide.pub", "fu837014586@163.com"))
                                .version("1.0.0")
                                .build())
                                // 分组名称
                                .groupName("1.0")
                                // 扫描API
                                .select()
                                .apis(RequestHandlerSelectors.basePackage("com.yizzuide.ice.api.controller"))
                                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                                .paths(PathSelectors.any())
                                .build();
    }

    // Springboot 2.6: 解决返回为null：springfox.documentation.spring.web.WebMvcPatternsRequestConditionWrapper.getPatterns
    @Bean
    public BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName) throws BeansException {
                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                List<T> copy = mappings.stream()
                        .filter(mapping -> mapping.getPatternParser() == null)
                        .collect(Collectors.toList());
                mappings.clear();
                mappings.addAll(copy);
            }

            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                try {
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    assert field != null;
                    field.setAccessible(true);
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }
}
