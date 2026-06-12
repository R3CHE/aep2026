package com.reche.aep.controller;

import com.reche.aep.model.Usuario;
import com.reche.aep.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                       @RequestParam String password,
                       HttpSession session,
                       Model model) {

        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);

        if (usuario.isPresent() && usuario.get().getPassword().equals(password)) {
            session.setAttribute("usuario", usuario.get());
            return "redirect:/";
        }

        model.addAttribute("erro", "Usuário ou senha inválidos!");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
