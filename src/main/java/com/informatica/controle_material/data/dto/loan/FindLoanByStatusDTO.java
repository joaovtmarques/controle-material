package com.informatica.controle_material.data.dto.loan;

import jakarta.validation.constraints.NotBlank;

public record FindLoanByStatusDTO(
  @NotBlank(message = "Um status da cautela deve ser informado")
  String status,
  @NotBlank(message = "Um tipo da cautela deve ser informado")
  String type
) {
  
}
