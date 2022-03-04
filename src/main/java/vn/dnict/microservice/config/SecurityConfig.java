//package vn.dnict.microservice.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.BeanIds;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
//import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
//
//import vn.dnict.microservice.exceptions.RestAccessDeniedHandler;
//import vn.dnict.microservice.exceptions.RestAuthenticationEntryPoint;
//import vn.dnict.microservice.uaa.dao.service.auth.TokenAuthenticationUserDetailsService;
//import vn.dnict.microservice.uaa.data.TokenAuthenticationFilter;
//
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//	@Autowired
//	private RestAccessDeniedHandler accessDeniedHandler;
//	@Autowired
//	private RestAuthenticationEntryPoint unauthorizedHandler;
//	@Autowired
//	private TokenAuthenticationUserDetailsService service;
//	@Autowired
//	private TokenAuthenticationFilter filter;
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests()
//		// No need authentication.
////	            .and()
////	            .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
////	            .and()
////	            .authorizeRequests()
//				// No need authentication.
//				.antMatchers("/v2/api-docs/**", "/actuator/**", "/public/**").permitAll()
//				// Need authentication.
//				.anyRequest().authenticated()
//				/*
//				 * .antMatcher("/**") .authorizeRequests() .anyRequest() .permitAll()
//				 */
//				.antMatchers(HttpMethod.POST, "/**").authenticated()
//				.antMatchers(HttpMethod.PUT, "/**").authenticated()
//				.antMatchers(HttpMethod.DELETE, "/**").authenticated().and()
//				.exceptionHandling().accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(unauthorizedHandler).and()
//				.addFilterBefore(filter, RequestHeaderAuthenticationFilter.class)
//				.authenticationProvider(preAuthProvider()).sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////	            .and()
////	            .csrf()
////	            .disable()
//		;
//	}
//
//	@Bean
//	public AuthenticationProvider preAuthProvider() {
//		PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
//		provider.setPreAuthenticatedUserDetailsService(service);
//		return provider;
//	}
//
//}
