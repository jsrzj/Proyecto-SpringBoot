package com.nebrija.mvc.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
   
	@Autowired
    private UserDetailsService userDetailsService;
	
	//Encriptador de contraseñas
	@Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	//Reglas de seguridad
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
	        .authorizeHttpRequests(authRequest ->
            	authRequest
            		.requestMatchers("/registrar", "/registrarUsuario", "/css/**", "/js/**", "/img/**").permitAll()
            		.requestMatchers("/admin").hasRole("ADMIN")
	                .anyRequest().authenticated()
	        )
	        .formLogin(login -> 
	        	login
	        		.loginPage("/login").permitAll()
	                .defaultSuccessUrl("/")
		 	)
	        .logout((logout) -> 
	        	logout
	        		.permitAll()
	        		.logoutSuccessUrl("/login")	
    		);
		return http.build();
	}
	
	//Autenticacion
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.userDetailsService(userDetailsService)
        	.passwordEncoder(passwordEncoder());
    }
    
}
