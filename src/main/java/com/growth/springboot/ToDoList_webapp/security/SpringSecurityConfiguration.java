package com.growth.springboot.ToDoList_webapp.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
//	InMemoryUserDetailsManager
//	InMemoryUserDetailsManager(UserDetails... users)
	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsMananger() {
		
		UserDetails userDetails1 = createNewUsername("Manas", "manas123");
		UserDetails userDetails2 = createNewUsername("BrainAxe", "123");
		
		return new InMemoryUserDetailsManager(userDetails1, userDetails2);
	}

private UserDetails createNewUsername(String username, String password) {
	Function<String, String> passwordEncoder 
	= input -> passwordEncoder().encode(input); // This is a Lambda function		
	
	UserDetails userDetails = User.builder()
					.passwordEncoder(passwordEncoder)
					.username(username)
					.password(password)
					.roles("USER", "ADMIN")
					.build();
	return userDetails;
}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//All URLs are protected
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated());
		//Show login form for unauthorized requests
		http.formLogin(withDefaults());
		
		//Disable CSRF
		http.csrf().disable();
		//Disable frame options
		http.headers().frameOptions().disable();
		return http.build();
	}
}