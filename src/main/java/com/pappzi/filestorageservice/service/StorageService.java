package com.pappzi.filestorageservice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class StorageService {

    @Value("${s3.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public String uploadFile(MultipartFile file) {

        String fileName = file.getOriginalFilename() + "_" +  System.currentTimeMillis();

        File fileObj = convertMultipartFiletoFile(file);
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        return "File Upload Successful ";
    }

    /**
     * convert Multipart File to File
     * @param file
     * @return
     */
    private File convertMultipartFiletoFile(MultipartFile file) {

        if ( !file.isEmpty()) {

            File convertedFile = new File(file.getOriginalFilename());
            try {
                FileOutputStream fos = new FileOutputStream(convertedFile);
                fos.write(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                log.info("Error creating File ");
            }
            return convertedFile;
        }
        return null;

    }

}
