package fr.echec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//@formatter:off
		return http	
				.antMatcher("/**")
					.csrf().disable()
				.authorizeRequests()
					.antMatchers("/inscription", "/connexion").anonymous()
					.anyRequest().authenticated()
				.and()
				.formLogin()
					.loginPage("/connexion")
					.defaultSuccessUrl("/accueil")
					.failureUrl("/connexion?error=true")
				.and()
				.logout()
					.logoutUrl("/deconnexion")
					.logoutSuccessUrl("/connexion")
				.and()
				.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
