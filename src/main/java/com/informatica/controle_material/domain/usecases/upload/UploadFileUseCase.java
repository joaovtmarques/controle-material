package com.informatica.controle_material.domain.usecases.upload;

import java.io.File;

public interface UploadFileUseCase {

  String execute(File file) throws Exception;

}
