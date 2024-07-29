package com.patika.bloghubservice.file;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileService {


    public String saveBase64StringFile(String image) {

        String fileName = UUID.randomUUID().toString()+".jpeg";
        Path path = Paths.get("uploads", "blog",fileName);

        try {
            OutputStream outputStream = new FileOutputStream(path.toFile());
            outputStream.write(decodedImage(image));
            outputStream.close();
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    private byte[] decodedImage(String encodedImage) {
        return Base64.getDecoder().decode(encodedImage.split(",")[1]);
    }
}
