package vn.dnict.microservice.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import vn.dnict.microservice.uaa.dao.service.auth.CustomAuthenticationProvider;
import vn.dnict.microservice.uaa.dao.service.auth.DaoAuthenticationProvider;
import vn.dnict.microservice.uaa.dao.service.auth.TokenAuthenticationUserDetailsService;
import vn.dnict.microservice.uaa.dao.service.auth.UsernamePasswordDetailsService;
import vn.dnict.microservice.uaa.data.TokenAuthenticationFilter;
import vn.dnict.microservice.uaa.exception.RestAccessDeniedHandler;
import vn.dnict.microservice.uaa.exception.RestAuthenticationEntryPoint;

// @EntityScan
// @EnableJpaRepositories
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	private UsernamePasswordDetailsService service;
	private PasswordEncoder passwordEncoder;
	private CustomAuthenticationProvider customAuthenticationProvider;
	private TokenAuthenticationUserDetailsService tokenAuthenticationUserDetailsService;
	private DaoAuthenticationProvider daoAuthenticationProvider;
	
	@Autowired
	private RestAccessDeniedHandler accessDeniedHandler;
	@Autowired
	private RestAuthenticationEntryPoint unauthorizedHandler;
	
//	@Value("${cas.service.login}")
//	private String CAS_URL_LOGIN;
//	@Value("${cas.service.logout}")
//	private String CAS_URL_LOGOUT;
//	@Value("${cas.url.prefix}")
//	private String CAS_URL_PREFIX;
//	@Value("${app.service.security}")
//	private String CAS_SERVICE_URL;
//	@Value("${app.service.home}")
//	private String APP_SERVICE_HOME;

	@Autowired
	public SecurityConfig(UsernamePasswordDetailsService service, PasswordEncoder passwordEncoder,
			DaoAuthenticationProvider daoAuthenticationProvider,
			TokenAuthenticationUserDetailsService tokenAuthenticationUserDetailsService,
			CustomAuthenticationProvider customAuthenticationProvider) {
		this.service = service;
		this.passwordEncoder = passwordEncoder;
		this.daoAuthenticationProvider = daoAuthenticationProvider;
		this.customAuthenticationProvider = customAuthenticationProvider;
		this.tokenAuthenticationUserDetailsService = tokenAuthenticationUserDetailsService;
	}

	@Configuration
	@Order(1)
	public class BasicAuthConfig extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			System.out.println("configure http");
//			http.cors().and().antMatcher("/uaa/token").authorizeRequests().anyRequest().authenticated().and()
//			.httpBasic().and()
//					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//			http.cors().and().antMatcher("/uaa/token").authorizeRequests()
//					.anyRequest().authenticated().and()
//					.httpBasic().and()
//					.exceptionHandling()
//					.accessDeniedHandler(accessDeniedHandler)
//					.authenticationEntryPoint(unauthorizedHandler).and()
//					.addFilterBefore(authFilter(), RequestHeaderAuthenticationFilter.class)
//					.authenticationProvider(preAuthProvider()).sessionManagement()
//					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//					.and().csrf().disable();
			http.cors().and().csrf().disable().
			headers().frameOptions().disable().and().authorizeRequests()
            .antMatchers("/rest/public/**", "/core/attachment/**", "/core/attachment", "/dvc/iframe/chitiet/**").permitAll() // Cho phÃ©p táº¥t cáº£ má»�i ngÆ°á»�i truy cáº­p vÃ o Ä‘á»‹a chá»‰ nÃ y
			.antMatchers(HttpMethod.GET, "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
			.anyRequest().authenticated().and()
			.httpBasic().and()
			.addFilterBefore(authFilter(), RequestHeaderAuthenticationFilter.class)
			.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			System.out.println("configure http 111111 ");
			auth.userDetailsService(service).passwordEncoder(passwordEncoder).and()
//					 .authenticationProvider(daoAuthenticationProvider);
					.authenticationProvider(customAuthenticationProvider);
			// .authenticationProvider(casAuthenticationProvider());
		}
		
	}

	@Configuration
	@Order(2)
	public class TokenAuthConfig extends WebSecurityConfigurerAdapter {

		@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			System.out.println("configure http 22222222222");
			http.antMatcher("/uaa/user").authorizeRequests()
			.anyRequest().authenticated().and()
			.addFilterBefore(authFilter(), RequestHeaderAuthenticationFilter.class)
			.authenticationProvider(preAuthProvider()).sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().csrf().disable()
			;
		}

	}
	
	@Bean
	public TokenAuthenticationFilter authFilter() {
		return new TokenAuthenticationFilter();
	}

	@Bean
	public AuthenticationProvider preAuthProvider() {
		PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
		provider.setPreAuthenticatedUserDetailsService(tokenAuthenticationUserDetailsService);
		return provider;
	}

}
