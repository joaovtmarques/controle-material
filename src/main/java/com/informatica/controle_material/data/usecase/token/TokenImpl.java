package com.informatica.controle_material.data.usecase.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.informatica.controle_material.data.dto.auth.AuthResponseDTO;
import com.informatica.controle_material.domain.model.User;
import com.informatica.controle_material.domain.usecases.token.TokenUseCase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenImpl implements TokenUseCase {
  @Value("${jwt.private.key}")
  private String secret;

  public AuthResponseDTO generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);

      String token = JWT.create()
          .withIssuer("controlematerial")
          .withSubject(user.getEmail())
          .withExpiresAt(this.generateExpirationDate())
          .sign(algorithm);
      return new AuthResponseDTO(token, user);
    } catch (JWTCreationException exception) {
      throw new RuntimeException("Erro ao tentar autenticar");
    }
  }

  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.require(algorithm)
          .withIssuer("controlematerial")
          .build()
          .verify(token)
          .getSubject();
    } catch (JWTVerificationException exception) {
      return null;
    }
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}
