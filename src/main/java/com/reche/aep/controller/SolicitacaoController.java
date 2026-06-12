package com.reche.aep.controller;

import com.reche.aep.model.Solicitacao;
import com.reche.aep.service.SolicitacaoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @GetMapping("/")
    public String menuPrincipal() {
        return "menu";
    }

    @GetMapping("/criar")
    public String formCriar(Model model) {
        model.addAttribute("solicitacao", new Solicitacao());
        return "criar";
    }

    @PostMapping("/salvar")
    public String salvarSolicitacao(@ModelAttribute Solicitacao solicitacao) {
        solicitacaoService.criarSolicitacao(solicitacao);
        return "redirect:/sucesso";
    }

    @GetMapping("/sucesso")
    public String sucesso() {
        return "sucesso";
    }

    @GetMapping("/listar")
    public String listar(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("solicitacoes", solicitacaoService.listarTodas());
        return "listar";
    }

    @GetMapping("/buscar")
    public String formBuscar() {
        return "buscar";
    }

    @GetMapping("/consulta")
    public String consultar(@RequestParam String protocolo, Model model) {
        Optional<Solicitacao> solicitacao = solicitacaoService.buscarPorProtocolo(protocolo);
        if (solicitacao.isPresent()) {
            model.addAttribute("solicitacao", solicitacao.get());
            return "consulta";
        }
        return "redirect:/buscar?erro=naoencontrado";
    }

    @GetMapping("/atualizar")
    public String formAtualizar(HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        return "atualizar";
    }

    @PostMapping("/atualizar-status")
    public String processarAtualizacao(HttpSession session, @RequestParam String protocolo,
                                      @RequestParam String status,
                                      @RequestParam String comentario,
                                      Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        try {
            solicitacaoService.atualizarStatus(protocolo, status, comentario);
            return "redirect:/sucesso-atualizacao";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao atualizar: " + e.getMessage());
            return "atualizar";
        }
    }

    @GetMapping("/sucesso-atualizacao")
    public String sucessoAtualizacao() {
        return "sucesso-atualizacao";
    }
}
