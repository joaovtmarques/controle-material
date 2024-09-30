package com.informatica.controle_material.data.dto.loan;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateLoanStatusDTO(
  @NotBlank(message = "Um status da cautela deve ser informado")
  String status,
  @NotNull(message = "Informe se houve alteração na cautela")
  Boolean alteration
) {}
