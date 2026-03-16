package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(requests -> requests
				.requestMatchers(new AntPathRequestMatcher("/unosMenadzera")).hasRole("Admin")
				.requestMatchers(new AntPathRequestMatcher("/lokacija/**")).hasRole("Admin")
				.requestMatchers(new AntPathRequestMatcher("/grad/**")).hasRole("Admin")
				.requestMatchers(new AntPathRequestMatcher("/dogadjaji/unos")).hasRole("Menadžer")
				.requestMatchers(new AntPathRequestMatcher("/dogadjaji/unosSedista")).hasRole("Menadžer")
				.requestMatchers(new AntPathRequestMatcher("/dogadjaji/potvrda")).hasRole("Posetilac")
				.requestMatchers(new AntPathRequestMatcher("/registracija")).anonymous()
				.requestMatchers(new AntPathRequestMatcher("/korisnik/**")).authenticated()
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll().anyRequest())
						.formLogin(form -> form.loginPage("/login.jsp").failureHandler(customAuthenticationFailureHandler()).permitAll()
						.loginProcessingUrl("/login")
						.defaultSuccessUrl("/"))
						.exceptionHandling(handling -> handling.accessDeniedPage("/accessDenied.jsp"))
						.csrf(csrf -> csrf.disable());

		return http.build();

	}

	@Bean
	UserDetailsService userDetailsService() {
		return new CustomUserDetailService();
	}
	
	@Bean
	AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}

	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Bean
    AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
}
