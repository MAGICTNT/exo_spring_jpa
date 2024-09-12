package fr.maxime.template_spring.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.Objects;

@Service
public class UploadService {
    private String directoryImage = "src/main/resources/static/image/";
    public String upload(MultipartFile image) throws IOException {
        Path destinationFile = Paths.get(directoryImage).resolve(Objects.requireNonNull(image.getOriginalFilename())).toAbsolutePath();
        //recuperation de l'image
        InputStream inputStream = image.getInputStream();
        Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
        return "/image/" + image.getOriginalFilename() ;
    }

}
