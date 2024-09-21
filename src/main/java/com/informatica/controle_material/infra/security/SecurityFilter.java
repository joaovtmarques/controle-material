package com.informatica.controle_material.infra.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.informatica.controle_material.domain.model.User;
import com.informatica.controle_material.domain.usecases.token.TokenUseCase;
import com.informatica.controle_material.infra.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  TokenUseCase tokenService;

  @Autowired
  UserRepository userRepository;

  @SuppressWarnings("unchecked")
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    var token = this.recoverToken(request);
    var login = tokenService.validateToken(token);

    if (login != null) {
      User user = userRepository.findByEmail(login).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
      ArrayList<SimpleGrantedAuthority> authorities = (ArrayList<SimpleGrantedAuthority>) user.getAuthorities();
      var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(request, response);
  }

  private String recoverToken(HttpServletRequest request) {
    var authHeader = request.getHeader("Authorization");
    if (authHeader == null)
      return null;
    return authHeader.replace("Bearer ", "");
  }
}
