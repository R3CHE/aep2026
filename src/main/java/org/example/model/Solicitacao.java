package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Solicitacao {

    private String protocolo;
    private Categoria categoria;
    private String descricao;
    private String localizacao;
    private boolean anonimo;
    private Status status;
    private List<HistoricoStatus> historico = new ArrayList<>();

    public Solicitacao(Categoria categoria, String descricao, String localizacao, boolean anonimo) {
        this.protocolo = UUID.randomUUID().toString().substring(0,8);
        this.categoria = categoria;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.anonimo = anonimo;
        this.status = Status.ABERTO;
        historico.add(new HistoricoStatus(Status.ABERTO, "Solicitação criada"));
    }

    public String getProtocolo() { return protocolo; }
    public Status getStatus() { return status; }
    public List<HistoricoStatus> getHistorico() { return historico; }

    public void atualizarStatus(Status novoStatus, String comentario) {
        this.status = novoStatus;
        historico.add(new HistoricoStatus(novoStatus, comentario));
    }

    public String toString() {
        return "Protocolo: " + protocolo +
                "\nCategoria: " + categoria +
                "\nDescrição: " + descricao +
                "\nLocal: " + localizacao +
                "\nAnônimo: " + anonimo +
                "\nStatus: " + status + "\n";
    }
}