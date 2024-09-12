package fr.maxime.template_spring.controller;

import fr.maxime.template_spring.entity.Etudiant;
import fr.maxime.template_spring.service.EtudiantService;
import fr.maxime.template_spring.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final LoginService loginService;
    private final EtudiantService etudiantService;
    @Autowired
    public LoginController(final LoginService loginService, EtudiantService etudiantService) {
        this.loginService = loginService;
        this.etudiantService = etudiantService;
    }

    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password){
        Etudiant controleEtudiant = etudiantService.findByName(username);
        System.out.println(controleEtudiant);
        System.out.println(controleEtudiant.getPassword().equals(password));
        System.out.println(controleEtudiant.getNom().equals(username));
        if (controleEtudiant.getPassword().equals(password) && controleEtudiant.getNom().equals(username)){
            loginService.login(true);
            return "redirect:/protected";
        }
        return "redirect:/inscription";
    }

    @RequestMapping("/protected")
    public String protectedPage() {
        if(loginService.isLogged()){
            return "protected";
        }
        return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String logout() {
        loginService.logout();
        return "redirect:/";
    }
}
