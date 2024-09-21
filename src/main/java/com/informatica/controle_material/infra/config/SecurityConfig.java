package com.informatica.controle_material.infra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.informatica.controle_material.infra.security.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  SecurityFilter securityFilter;
  
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
      .authorizeHttpRequests(authorize -> authorize
          .requestMatchers(HttpMethod.POST, "/api/users").hasAuthority("ADMIN")
          .requestMatchers(HttpMethod.POST, "/api/users").hasAuthority("ADMIN_PELCOM")
          .requestMatchers(HttpMethod.POST, "/api/auth").permitAll()
          // .requestMatchers("/swagger-ui/**", "/swagger-resources/*", "/v3/api-docs/**").permitAll()
          .anyRequest().authenticated()
      )
      .cors(Customizer.withDefaults())
      .csrf(csrf -> csrf.disable())
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
    
    return httpSecurity.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

}
