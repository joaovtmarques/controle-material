package com.informatica.controle_material.data.usecase.loan_doc;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.informatica.controle_material.data.dto.loan.DownloadLoanDocDTO;
import com.informatica.controle_material.domain.usecases.loan_doc.DownloadLoanDocUseCase;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class DownloadLoanDocImpl implements DownloadLoanDocUseCase {

  @Override
  public DownloadLoanDocDTO execute(String filePath, HttpServletRequest request) throws IOException {
    Path path = Paths.get(filePath);
    Resource resource = new UrlResource(path.toUri());

    String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    if (contentType == null) {
      contentType = "application/octet-stream";
    }

    return new DownloadLoanDocDTO(resource, contentType);
  }

}
