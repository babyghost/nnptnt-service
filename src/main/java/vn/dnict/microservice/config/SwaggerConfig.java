//package vn.dnict.microservice.config;
//
//import java.util.Collections;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.google.common.base.Predicates;
//import com.google.common.collect.Lists;
//
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.AuthorizationScope;
//import springfox.documentation.service.Contact;
//import springfox.documentation.service.SecurityReference;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import vn.dnict.microservice.security.model.TokenProperties;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//	@Autowired
//	private TokenProperties properties;
//
//	@SuppressWarnings("unchecked")
//	@Bean
//	public Docket api() {
//		// MavenXpp3Reader reader = new MavenXpp3Reader();
//		// Model model = reader.read(new FileReader("pom.xml"));
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(Predicates.or(RequestHandlerSelectors.basePackage("vn.dnict.microservice.core.api"),
//						RequestHandlerSelectors.basePackage("vn.dnict.microservice.danhmuc.api"),
//						RequestHandlerSelectors.basePackage("vn.dnict.microservice.dvcnoivu.hoso.controller"),
//						RequestHandlerSelectors.basePackage("vn.dnict.microservice.dvcnoivu.danhmuc.controller")))
//				.paths(PathSelectors.any()).build()
//				.apiInfo(new ApiInfo("Dịch vụ công Sở Nội vụ Service Api Documentation",
//						"Documentation automatically generated", "API v1", "Terms of service",
//						new Contact("Trung tâm Công nghệ thông tin và Truyền thông", "http://dnict.vn",
//								"dnict@danang.gov.vn"),
//						"License of API", "API license URL", Collections.emptyList()))
//				.securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.newArrayList(apiKey()))
//				.useDefaultResponseMessages(false).forCodeGeneration(true);
//	}
//
//	private SecurityContext securityContext() {
//		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
//	}
//
//	private ApiKey apiKey() {
//		return new ApiKey("JWT", properties.getHeader(), "header");
//	}
//
//	private List<SecurityReference> defaultAuth() {
//		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//		authorizationScopes[0] = authorizationScope;
//		return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
//	}
//}