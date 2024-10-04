package com.luizalabs.order_parser.mock;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileMock {
    public static MultipartFile getValidFile() throws IOException {
        return getFile("valid_file.txt");
    }

    public static MultipartFile getInvalidFile() throws IOException {
        return getFile("invalid_file.txt");
    }

    public static MultipartFile getEmptyFile() throws IOException {
        return getFile("empty_file.txt");
    }

    private static MultipartFile getFile(String fileName) throws IOException {
        ClassLoader classLoader = FileMock.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        return new MockMultipartFile("file", fileName, "text/plain", fileBytes);
    }
}
