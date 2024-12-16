package com.informatica.controle_material.presentation.controller.loan_doc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.data.dto.loan.DownloadLoanDocDTO;
import com.informatica.controle_material.domain.usecases.loan_doc.DownloadLoanDocUseCase;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/loans")
public class DownloadLoanDocController {

  @Autowired
  private DownloadLoanDocUseCase downloadLoanDoc;

  @GetMapping("/download")
  public ResponseEntity<Resource> handle(
      @RequestParam("filePath") String filePath,
      HttpServletRequest request) throws URISyntaxException, IOException {
    try {
      DownloadLoanDocDTO downloadLoanDocDTO = downloadLoanDoc.execute(filePath, request);
      Resource resource = downloadLoanDocDTO.resource();

      String contentType = downloadLoanDocDTO.contentType();
      if (contentType == null) {
        contentType = "application/octet-stream";
      }

      return ResponseEntity.ok()
          .contentType(MediaType.parseMediaType(contentType))
          .header(HttpHeaders.CONTENT_DISPOSITION,
              "attachment; filename=\"" + resource.getFilename() + "\"")
          .body(resource);

    } catch (MalformedURLException ex) {
      return ResponseEntity.badRequest().body(null);
    }
  }
}
