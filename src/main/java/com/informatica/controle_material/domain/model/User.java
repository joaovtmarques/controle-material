package com.informatica.controle_material.domain.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, unique = true, nullable = false)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "war_name", nullable = false)
  private String warName;

  @Column(name = "rank", nullable = false)
  private String rank;

  @Column(name = "company", nullable = false)
  private String company;

  @Column(name = "cpf", unique = true, nullable = false)
  private String cpf;

  @Column(name = "telephone", nullable = false)
  private String telephone;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @JsonIgnore
  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "type", nullable = false)
  private String type;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Role> roles;

  @JsonIgnore
  @OneToMany(mappedBy = "lender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Loan> loans;

  @Override
  public String getUsername() {
    return email;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<Role> userRoles = roles;

    ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();

    for (Role r : userRoles) {
      roles.add(new SimpleGrantedAuthority(r.getRole()));
    }
    return roles;
  }

}
