package vn.dnict.microservice.uaa.config;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;

import springfox.documentation.service.SecurityReference;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import vn.dnict.microservice.uaa.data.TokenProperties;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Autowired
	private TokenProperties properties;
//	@Autowired
//	private RestAccessDeniedHandler accessDeniedHandler;
	@SuppressWarnings("unchecked")
	@Bean
	public Docket api() {
		// MavenXpp3Reader reader = new MavenXpp3Reader();
		// Model model = reader.read(new FileReader("pom.xml"));
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(Predicates.or(RequestHandlerSelectors.basePackage("vn.dnict.microservice.uaa.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.core.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.danhmuc.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.ocop.chuthe.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.ocop.sanpham.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.ocop.tieuchinam.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.ocop.danhgia.api"),

						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.baocao.yeucaubaocao.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.baocao.chitieunam.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.baocao.kehoach.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.chomeo.chuquanly.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.api"),

						
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.hopdong.baocaothongke.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.hopdong.danhmuc.loaihopdong.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.hopdong.danhmuc.phuongthucbaolanhhd.api"),
						
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.dm.giong.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.dm.loaidongvat.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.dm.loaivatnuoi.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.dm.nganhhang.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.dm.phanhang.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.dm.phannhom.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.dm.nhom.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.dm.phantieuchi.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.dm.loaihinh.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.dm.loaidoanhnghiep.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.dm.nganhnghe.api"),

						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.kehoach.danhmuc.loainhiemvu.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.kehoach.danhmuc.trangthaikehoach.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.kehoach.kehoachnam.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.kehoach.kehoachthang.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.api"),
						
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.dm.loainhiemvu.api"),
						
						
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.api"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.kiemsoatgietmo.danhmuc.loaigiayto.api"),

						
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.api"), 
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.api"),
						// RequestHandlerSelectors.basePackage("vn.dnict.microservice.dvcnoivu.hoso.controller"),
						// RequestHandlerSelectors.basePackage("vn.dnict.microservice.trungtamso.baocaocongviec.controller"),
						// RequestHandlerSelectors.basePackage("vn.dnict.microservice.trungtamso.lamthemgio.controller"),
						RequestHandlerSelectors.basePackage("vn.dnict.microservice.dvcnoivu.danhmuc.controller")))
				.paths(PathSelectors.any()).build()
				.apiInfo(new ApiInfo("CSDL Nông nghiệp và Phát triển nông thôn Service Api Documentation", "Documentation automatically generated", "API v1",
						"Terms of service", new Contact("Trung tâm Công nghệ thông tin và Truyền thông",
								"http://dnict.vn", "dnict@danang.gov.vn"),
						"License of API", "API license URL", Collections.emptyList()))
				//cấu hình auth khi sử dụng swagger-ui
				.securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.newArrayList(apiKey()))
				.useDefaultResponseMessages(false).forCodeGeneration(true);
	}
	
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
	}
	
	private ApiKey apiKey() {
		return new ApiKey("JWT", properties.getHeader(), "header");
	}
	
	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
	}
}