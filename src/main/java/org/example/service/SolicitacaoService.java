package org.example.service;

import org.example.model.Categoria;
import org.example.model.Solicitacao;
import org.example.model.Status;
import org.example.repository.SolicitacaoRepository;

import java.util.List;

public class SolicitacaoService {

    private SolicitacaoRepository repo = new SolicitacaoRepository();

    public void criarSolicitacao(Categoria cat, String desc, String loc, boolean anonimo) {
        Solicitacao s = new Solicitacao(cat, desc, loc, anonimo);
        repo.salvar(s);
        System.out.println("Protocolo gerado: " + s.getProtocolo());
    }

    public List<Solicitacao> listar() {
        return repo.listar();
    }

    public Solicitacao buscar(String protocolo) {
        return repo.buscarPorProtocolo(protocolo);
    }

    public void atualizarStatus(String protocolo, Status status, String comentario) {
        Solicitacao s = repo.buscarPorProtocolo(protocolo);
        if (s != null) {
            s.atualizarStatus(status, comentario);
        }
    }
}