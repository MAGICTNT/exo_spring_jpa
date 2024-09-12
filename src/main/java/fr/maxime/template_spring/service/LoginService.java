package fr.maxime.template_spring.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private HttpSession httpSession;

    public boolean login(boolean status) {
        if (status) {
            httpSession.setAttribute("login", "ok");
            return true;
        }
        return false;
    }

    public boolean isLogged() {
        try {
            String isOk = (String) httpSession.getAttribute("login");
            return isOk.equals("ok");
        }catch (Exception e) {
            return false;
        }
    }

    public void logout() {
        httpSession.removeAttribute("login");
    }
}
