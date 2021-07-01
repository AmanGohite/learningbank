package com.banking.leaningbank.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.builders.ApiInfoBuilder;

import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	 public static final Contact DEFAULT_CONTACT = new Contact(
  		   "Aman Gohite", "//https://github.com/AmanGohite", "AmanGohite");
  
	  public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("SpringBoot JPA Repository Rest API", "Rest API for Bank Management",
			  "1.0", "urn:tos",
	          DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());
     private static final Set<String> DEFAULT_CONSUME_PRODUCE = 
    		 new HashSet<String>(Arrays.asList("application/json"));
     
   @Bean
   public Docket apiDocket() {
      
    		   
	   
       Docket docket =  new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.banking.leaningbank.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_CONSUME_PRODUCE)
                .consumes(DEFAULT_CONSUME_PRODUCE);
       
       return docket;
       
    } 
   
   private ApiInfo metaData() {
       return new ApiInfoBuilder()
               .title("REST API")
               .description("\"Spring Boot CRUD REST API \"")
               .version("1.0.0")
               .license("Apache License Version 2.0")
               .licenseUrl("//https://github.com/AmanGohite")
               .contact(new Contact("Aman Gohite", "//https://github.com/AmanGohite", "AmanGohite"))
               
               .build();
   }
  

}