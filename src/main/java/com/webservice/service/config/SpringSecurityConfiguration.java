//package com.webservice.service.config;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
////@Configuration
//public class SpringSecurityConfiguration {
//
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity.authorizeHttpRequests(
//				auth -> auth.anyRequest().authenticated());
//		
//		httpSecurity.httpBasic(withDefaults());
//		
//		httpSecurity.csrf().disable();
//		
//		return httpSecurity.build();
//	}
//}
