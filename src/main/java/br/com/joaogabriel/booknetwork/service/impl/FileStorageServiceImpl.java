package br.com.joaogabriel.booknetwork.service.impl;

import br.com.joaogabriel.booknetwork.model.entity.Book;
import br.com.joaogabriel.booknetwork.model.entity.User;
import br.com.joaogabriel.booknetwork.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final String uploadPath;
    private final Logger logger = Logger.getLogger(getClass().getSimpleName());

    public FileStorageServiceImpl(@Value("${file.upload.path}") String uploadPath) {
        this.uploadPath = uploadPath;
    }

    @Override
    public String save(@NonNull MultipartFile file, @NonNull User user) {
        final String uploadSubPath = String.format("users%s%s", File.separator, user.getId());
        return uploadFile(file, uploadSubPath);
    }

    private String uploadFile(@NonNull MultipartFile file, @NonNull String uploadSubPath) {
        final String path = uploadPath + File.separator + uploadSubPath;
        File targetFolder = new File(path);
        if (!targetFolder.exists()) {
            boolean folderCreated = targetFolder.mkdirs();
            if (!folderCreated) {
                logger.log(Level.SEVERE, "Failed to create the target folder!");
                throw new RuntimeException();
            }
        }
        final String fileExtension = getFileExtension(file.getOriginalFilename());
        String targetFilePath =
                String.format("%s%s%s.%s", path, File.separator, System.currentTimeMillis(), fileExtension);
        Path targetPath = Paths.get(targetFilePath);
        try {
            Files.write(targetPath, file.getBytes());
            logger.log(Level.INFO, "File saved into {0}", targetPath);
            return targetFilePath;
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Failed to save a file");
            throw new RuntimeException();
        }
    }

    private String getFileExtension(String originalFilename) {
        if (originalFilename == null || originalFilename.isEmpty()) {
            return "";
        }
        int lastDotIndex = originalFilename.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }
        return originalFilename.substring(lastDotIndex + 1).toLowerCase();
    }
}
