package com.leon.myblog.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration   //不可缺少 相当于spring xml配置中的bean，放置在容器中
@EnableSwagger2  // 启动类注解
public class SwaggerConfig {
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //.groupName("business-api")
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.leon.myblog.controller"))
                //扫描Controller
                .build();
    }
    /**
     * swagger api 信息描述<br/>
     * @return {@link ApiInfo swagger API 对象}
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("miaopasi的博客的api")
                .description("博客首页：http://www.bonjours.cn/blog/")
                //.termsOfServiceUrl("http://blog.didispace.com/")
                //.license("License Version 1.0")
                .contact("miaopasi")
                .version("1.0")
                .build();
    }
}
