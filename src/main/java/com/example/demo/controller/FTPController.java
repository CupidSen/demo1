package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/ftp")
@CrossOrigin(origins = "*")
public class FTPController {

    @Autowired
    private FTPService ftpService;

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                File tempFile = File.createTempFile("temp", file.getOriginalFilename());
                file.transferTo(tempFile);
                ftpService.uploadFile(tempFile, "/upload/" + file.getOriginalFilename());
                tempFile.delete();
                return "File uploaded successfully!";
            } catch (IOException e) {
                return "Failed to upload file: " + e.getMessage();
            }
        } else {
            return "File is empty!";
        }
    }

    @GetMapping("/download")
    public String handleFileDownload(@RequestParam("filename") String filename) {
        String localFilePath = "/path/to/save/downloaded/" + filename;
        String remoteFilePath = "/download/" + filename;
        try {
            ftpService.downloadFile(remoteFilePath, localFilePath);
            return "File downloaded successfully!";
        } catch (IOException e) {
            return "Failed to download file: " + e.getMessage();
        }
    }
}
