package fr.maxime.template_spring.controller;


import fr.maxime.template_spring.entity.Etudiant;
import fr.maxime.template_spring.service.EtudiantService;
import fr.maxime.template_spring.service.UploadService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
public class EtudiantController {
    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @RequestMapping("/")
    public String index() {
        return "Accueil";
    }

    @RequestMapping("/detail")
    public String detail() {
        return "DetailEtudiant";
    }

    @RequestMapping("/inscription")
    public String inscription(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "Inscription";
    }

    @RequestMapping("/list")
    public String listEtudiant(Model model) {
        model.addAttribute("etudiants", etudiantService.findAll());
        model.addAttribute("message", "Liste de tout les étudiant");
        return "ListEtudiants";
    }

    @RequestMapping("/search")
    public String rechercheEtudiant(Model model,
                                    @RequestParam(name = "etudiantName", required = false) String name) {
        Etudiant etudiant = etudiantService.findByName(name);
        if (etudiant != null) {
            model.addAttribute("etudiant", etudiant);
            return "DetailEtudiant";
        }
        return "RechercheEtudiant";
    }

    @PostMapping("/inscription")
    public String inscriptionEtudiant(@Valid @ModelAttribute("etudiant") Etudiant etudiant,
                                      BindingResult bindingResult,
                                      Model model,
                                      @RequestParam("img") MultipartFile img
                                      ) throws IOException {

        if (bindingResult.hasErrors()) {

            return "Inscription";
        } else {
            UploadService uploadService = new UploadService();
            etudiant.setImage(uploadService.upload(img));
//            String directoryImage = "src/main/resources/static/image/";
//            Path destinationFile = Paths.get(directoryImage).resolve(Objects.requireNonNull(image.getOriginalFilename())).toAbsolutePath();
//            //recuperation de l'image
//            InputStream inputStream = image.getInputStream();
//            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
//            System.out.println(etudiant);
//            etudiant.setImage(destinationFile.toString());
//            System.out.println(etudiant);
            etudiantService.save(etudiant);

            model.addAttribute("etudiants", etudiantService.findAll());

            return "ListEtudiants";
        }

    }
//    @RequestMapping("/inscription/newEtudiant")
//    public String inscription(@RequestParam(name = "nom", required = false) String nom,
//                              @RequestParam(name = "prenom", required = false) String prenom,
//                              @RequestParam(name = "age", required = false) int age,
//                              @RequestParam(name = "mail", required = false) String mail,
//                              Model model) {
//        Etudiant newEtudiant = Etudiant.builder()
//                .id(UUID.randomUUID())
//                .nom(nom)
//                .prenom(prenom)
//                .age(age)
//                .email(mail)
//                .build();
//        etudiantService.addEtudiant(newEtudiant);
//        model.addAttribute("etudiants", etudiantService.getAllEtudiants());
//        return "ListEtudiants";
//    }

    @RequestMapping("/detail/{idContact}")
    public String contactPage(Model model, @PathVariable("idContact") UUID id) {
        model.addAttribute("etudiant", etudiantService.findById(id));
        Etudiant etudiant = etudiantService.findById(id);
        if (etudiant != null) {
            model.addAttribute("etudiant", etudiant);
            return "DetailEtudiant";
        }
        return "DetailEtudiant";
    }

    @RequestMapping("/delete/{idContact}")
    public String deleteContact(Model model, @PathVariable("idContact") UUID id) {
        Etudiant etudiant = etudiantService.findById(id);
        if (etudiant != null) {
            etudiantService.delete(etudiant);
            List<Etudiant> listEtudiant = etudiantService.findAll();
            model.addAttribute("etudiants", listEtudiant);
            model.addAttribute("message", "suppression réussi");
            return "ListEtudiants";
        }
        return "ListEtudiants";
    }

    @RequestMapping("/update/{idContact}")
    public String updateContact(Model model, @PathVariable("idContact") UUID id) {
        Etudiant etudiant = etudiantService.findById(id);
        if (etudiant != null) {
            model.addAttribute("etudiant", etudiant);
            return "updateEtudiant";
        }
        return "updateEtudiant";
    }

    @RequestMapping("/detail/name{etudiantName}")
    public String contactPage(Model model, @PathVariable("etudiantName") String name) {
        Etudiant etudiant = etudiantService.findByName(name);
        if (etudiant != null) {
            model.addAttribute("etudiant", etudiant);
            return "DetailEtudiant";
        }
        return "DetailEtudiant";
    }


    @RequestMapping("/recherche")
    public String contactForm(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "RechercheEtudiant";
    }

    @PostMapping("/recherche/name")
    public String submitContact(@ModelAttribute("name") String name) {
        Etudiant etudiantSearch = etudiantService.findByName(name);
        System.out.println(etudiantSearch);
        if (etudiantSearch != null) {
            return "redirect:/detail/name" + etudiantSearch.getNom();
        } else {
            return "redirect:/recherche";
        }
//        if(contact != null){
//
//        }
    }

    @PostMapping("/update")
    public String updateEtudiant(Model model, @ModelAttribute("etudiant") Etudiant etudiant) {
        System.out.println(etudiant);
        Etudiant etudiantFind = etudiantService.findById(etudiant.getId());
        if (etudiantFind != null) {
            etudiantService.save(etudiant);
            return "redirect:/list";
        }
        System.out.println(etudiant);
        return "updateEtudiant";
    }

}
