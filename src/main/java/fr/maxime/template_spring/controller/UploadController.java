package fr.maxime.template_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

@Controller
public class UploadController {
//    private String directoryImage = "src/main/resources/static/image/";
//
//    @PostMapping("/upload")
////    @IOException est souvent utilisé pour signaler les erreurs lors de la lecture ou de l'ecriture de fichier externe
//    public String upload(@RequestParam("image") MultipartFile image, Model model, Locale locale) throws IOException {
//        Path destinationFile = Paths.get(directoryImage).resolve(Objects.requireNonNull(image.getOriginalFilename())).toAbsolutePath();
//        //recuperation de l'image
//        InputStream inputStream = image.getInputStream();
//        Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
//        return "redirect:/";
//    }


}
