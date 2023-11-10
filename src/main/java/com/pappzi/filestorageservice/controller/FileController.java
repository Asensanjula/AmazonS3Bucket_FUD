package com.pappzi.filestorageservice.controller;

import com.pappzi.filestorageservice.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/file")
public class FileController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public String uploadFileToS3Bucket(@RequestParam(value = "file") MultipartFile file) {

        return storageService.uploadFile(file);

    }

}
