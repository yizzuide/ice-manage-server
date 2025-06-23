package com.yizzuide.ice.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Knife4jConfiguration
 *
 * @author yizzuide
 * <br />
 * Create at 2022/10/09 22:57
 */
@Configuration
public class Knife4jConfiguration {

    private static final String AUTHORIZED_KEY = "token"; //HttpHeaders.AUTHORIZATION;

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Ice接口文档")
                        .description( "Ice平台服务管理api")
                        .version("V1")
                        .termsOfService("https://github.com/yizzuide")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("技术文档")
                        .url("https://github.com/yizzuide/ice-manage-server"))
                .addSecurityItem(new SecurityRequirement().addList(AUTHORIZED_KEY))
                .components(new Components().addSecuritySchemes(AUTHORIZED_KEY, new SecurityScheme()
                        .name(AUTHORIZED_KEY).type(SecurityScheme.Type.HTTP).scheme("bearer")));
    }
}
