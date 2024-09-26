package com.informatica.controle_material.domain.usecases.loan_doc;

import java.io.IOException;

import com.informatica.controle_material.data.dto.loan.DownloadLoanDocDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface DownloadLoanDocUseCase {
  
  DownloadLoanDocDTO execute(String filePath, HttpServletRequest request) throws IOException;

}
