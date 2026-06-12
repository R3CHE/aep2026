package org.example.repository;


import org.example.model.Solicitacao;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoRepository {

    private List<Solicitacao> lista = new ArrayList<>();

    public void salvar(Solicitacao s) {
        lista.add(s);
    }

    public List<Solicitacao> listar() {
        return lista;
    }

    public Solicitacao buscarPorProtocolo(String protocolo) {
        for (Solicitacao s : lista) {
            if (s.getProtocolo().equals(protocolo)) {
                return s;
            }
        }
        return null;
    }
}