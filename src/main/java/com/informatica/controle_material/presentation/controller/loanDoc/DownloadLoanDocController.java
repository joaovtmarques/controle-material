package com.informatica.controle_material.presentation.controller.loanDoc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.data.dto.loan.DownloadLoanDocDTO;
import com.informatica.controle_material.domain.usecases.loan_doc.DownloadLoanDocUseCase;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/loans")
public class DownloadLoanDocController {

  @Autowired
  private DownloadLoanDocUseCase downloadLoanDoc;

  @GetMapping("/download")
  public ResponseEntity<String> handle(
    @RequestParam("filePath") String filePath, 
    HttpServletRequest request
  ) throws URISyntaxException, IOException {
    try {
      DownloadLoanDocDTO downloadLoanDocDTO = downloadLoanDoc.execute(filePath, request);

      return ResponseEntity.ok()
          .contentType(MediaType.parseMediaType(downloadLoanDocDTO.contentType()))
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + downloadLoanDocDTO.resource().getFilename() + "\"")
          .body(downloadLoanDocDTO.resource().toString());
    } catch (MalformedURLException ex) {
      return ResponseEntity.badRequest().body(null);
    }
}

}
