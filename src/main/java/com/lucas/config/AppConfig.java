package com.lucas.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mapping.model.ClassGeneratingPropertyAccessorFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class AppConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .authorizeHttpRequests(Authorize -> Authorize
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll())
            .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class).csrf().disable()	
            .cors().configurationSource(new CorsConfigurationSource() {
            	@Override
            	public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
            		CorsConfiguration cfg= new CorsConfiguration();
            		
            		cfg.setAllowedOrigins(Arrays.asList(
            				"http://localhost:3000",
            				"http://localhost:4200",
            				"https://lucas-e-commerce-final.vercel.app"
            				));
            		cfg.setAllowedMethods(Collections.singletonList("*"));
            		cfg.setAllowCredentials(true);
            		cfg.setAllowedHeaders(Collections.singletonList("*"));
            		cfg.setExposedHeaders(Arrays.asList("Authorization"));
            		cfg.setMaxAge(3600L);
            		return cfg;
            	}
            }).and().httpBasic().and().formLogin();
        
        return http.build();
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/producto/**")
                        .allowedOrigins(
                                "http://localhost:3000",
                                "http://localhost:4200",
                                "https://lucas-e-commerce-final.vercel.app"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .maxAge(3600);
            }
        };
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}
