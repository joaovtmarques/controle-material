package com.informatica.controle_material.data.dto.loan;

import org.springframework.core.io.Resource;

public record DownloadLoanDocDTO(
  Resource resource,
  String contentType
) {
  
}
