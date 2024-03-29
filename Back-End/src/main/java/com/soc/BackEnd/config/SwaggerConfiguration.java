package com.soc.backend.config;

import org.springframework.data.domain.Pageable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.soc.backend"))
                .paths(PathSelectors.any())
                .build()
                .host("prod.soc-project.site")
                .ignoredParameterTypes(AuthenticationPrincipal.class, Errors.class, Pageable.class)
                .useDefaultResponseMessages(false);
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("S-O-C-Develop")
                .description("S-O-C App Back-End 연동 문서")
                .license("© 2021. vividswan").licenseUrl("https://vividswan.github.io/").version("1").build();
    }
}