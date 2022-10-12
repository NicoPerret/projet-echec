package fr.echec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityWebServiceConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// @formatter:off
		return http
				/*
				.cors()
	            .and()
	            .headers()
	                .frameOptions().disable()
	                .and()

	            .csrf().disable()
	            .authorizeRequests()
	                .antMatchers("/stomp").permitAll() // On autorise l'appel handshake entre le client et le serveur
	                .anyRequest()
	                    .authenticated()
				
				
				.and()
				*/

				.antMatcher("/api/**")
					.csrf().disable()
			   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			   
			   .and()
			   .authorizeRequests()
			   .antMatchers("/api/coup-possible/**").permitAll()
			   .antMatchers("/api/utilisateur/**").permitAll()
			   .antMatchers("/stomp").permitAll()
			   		.antMatchers(HttpMethod.OPTIONS).permitAll()
			   		.antMatchers(HttpMethod.GET).hasAnyRole("USER", "COACH", "ADMIN")
		   		    .antMatchers(HttpMethod.POST).hasAnyRole("USER", "COACH", "ADMIN")
			   		.antMatchers(HttpMethod.PUT).hasAnyRole("COACH", "ADMIN")
			   		.antMatchers(HttpMethod.DELETE).hasAnyRole( "ADMIN")
			   		.anyRequest().authenticated()
			   		
			   .and()
			   		//plus de formulaire on envoie dans le header de la requete le login, password
			   		.httpBasic()
			   .and()
			   .build();
	// @formatter:on

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}