package edu.aeu.msit.assigment.attendance.qr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(apiInfo())
                .select()                                  
                .apis(RequestHandlerSelectors.any())              
                .paths(PathSelectors.any())                          
                .build();
    }
    
    

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Assignment Attendance QR API")
                .description("Assignment Attendance QR API reference for developers")
                .termsOfServiceUrl("www.javacambodia.com")
                .contact(new Contact("Java Developer", "www.javacambodia.com", "developer@javacambodia.com")).license("Javacambodia")
                .licenseUrl("license@javacambodia.com").version("1.0").build();
    }
}