package com.yizzuide.ice.api.test;

import com.yizzuide.ice.api.service.SysUserRoleService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * PlayTest
 *
 * @author yizzuide
 * <br>
 * Create at 2022/10/16 02:22
 */
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
// @WebMvcTest或@WebFluxTest开启的是Mocked servlet environment
//@WebMvcTest
// @SpringBootTest会开启内置Tomcat启动的容器，真实的可请求环境
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlayTest {
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    // 还可以使用@MockBean、@SpyBean替换Spring上下文中的对应的Bean
    @Autowired
    private SysUserRoleService sysUserRoleService;

    // 注入需要开启@AutoConfigureMockMvc，或将@SpringBootTest替换为@WebMvcTest
    @Autowired
    private MockMvc mockMvc;

    // Spring Boot 2.6.x: Developers could use WebTestClient to test WebFlux apps in mock environments,
    // or any Spring web app against live servers. This change also enables WebTestClient for Spring MVC in
    // mock environments: classes annotated with @AutoConfigureMockMvc can get injected a WebTestClient.
    // WebTestClient需要添加依赖：spring-boot-starter-webflux
    // 注入需要开启@SpringBootTest和@WebFluxTest其中一个
    @Autowired
    private WebTestClient webTestClient;

    @BeforeAll
    void setUp() {
        System.out.println(mockMvc);
        System.out.println(webTestClient);
    }

    @Test
    public void genPwd() {
        System.out.println(passwordEncoder.encode("123456"));
    }

    @DisplayName("根据用户id，查询角色id列表")
    @Test
    public void findRoleIds() {
        List<Long> ids = sysUserRoleService.findRoleListByUserId(1L);
        System.out.println(ids);
        Assertions.assertFalse(ids.isEmpty());
    }
}
