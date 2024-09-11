package com.example.demo.controller;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FTPService {

    private static final String FTP_SERVER = "ftp://47.93.86.105:21";
    private static final int FTP_PORT = 21;
    private static final String FTP_USER = "zhang";
    private static final String FTP_PASS = "123456";

    public void uploadFile(File file, String remoteFilePath) throws IOException {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(FTP_SERVER, FTP_PORT);
            ftpClient.login(FTP_USER, FTP_PASS);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            FileInputStream inputStream = new FileInputStream(file);
            boolean done = ftpClient.storeFile(remoteFilePath, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The file is uploaded successfully.");
            }
        } finally {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        }
    }

    public void downloadFile(String remoteFilePath, String localFilePath) throws IOException {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(FTP_SERVER, FTP_PORT);
            ftpClient.login(FTP_USER, FTP_PASS);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            File downloadFile = new File(localFilePath);
            FileOutputStream outputStream = new FileOutputStream(downloadFile);
            boolean success = ftpClient.retrieveFile(remoteFilePath, outputStream);
            outputStream.close();
            if (success) {
                System.out.println("The file is downloaded successfully.");
            }
        } finally {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        }
    }
}
