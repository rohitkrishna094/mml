package com.rohit.mml.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

public class FileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class.getName());

    public static Properties fetchProperties(String fileName) {
        Properties properties = new Properties();
        try {
            File file = ResourceUtils.getFile("classpath:" + fileName);
            InputStream in = new FileInputStream(file);
            properties.load(in);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return properties;
    }

    public String getFileContentFromResources(String fileName) throws IOException {
        File file = ResourceUtils.getFile("classpath:" + fileName);
        return file == null ? "null" : readFile(file);
    }

    public static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    public static String readFile(File file) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
