package com.informatica.controle_material.data.usecase.upload;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.informatica.controle_material.domain.usecases.upload.UploadFileUseCase;
import com.informatica.controle_material.infra.config.AwsS3BucketProperties;

import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(AwsS3BucketProperties.class)
public class UploadFileImpl implements UploadFileUseCase {

  private final S3Template s3Template;
  private final AwsS3BucketProperties awsS3BucketProperties;

  @Override
  public String execute(File file) throws Exception {
    var objectKey = file.getName();
    var bucketName = awsS3BucketProperties.getBucketName();
    var fileInputStream = new FileInputStream(file);
    S3Resource s3r = s3Template.upload(bucketName, objectKey, fileInputStream);
    return s3r.getURI().toString();
  }
}
