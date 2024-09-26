package com.informatica.controle_material.data.dto.loan;

import jakarta.validation.constraints.NotBlank;

public record UpdateLoanStatusDTO(
  @NotBlank(message = "Um status da cautela deve ser informado")
  String status
) {}
