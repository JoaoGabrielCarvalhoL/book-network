package br.com.joaogabriel.booknetwork.utils;


import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtils {

    private final Logger logger = Logger.getLogger(FileUtils.class.getSimpleName());

    public byte[] readFileFromLocation(String path) {
        if (StringUtils.isBlank(path)) return null;
        try {
            Path filePath = new File(path).toPath();
            return Files.readAllBytes(filePath);
        } catch (IOException exception) {
            logger.log(Level.SEVERE, "No file found in the path: {0}", path);
        }
        return null;
    }
}
