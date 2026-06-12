package com.reche.aep.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class StatusUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solicitacao_id")
    private Solicitacao solicitacao;

    private String status;
    private String comentario;
    private LocalDateTime dataAlteracao;

    public StatusUpdate() {}

    public StatusUpdate(Solicitacao solicitacao, String status, String comentario) {
        this.solicitacao = solicitacao;
        this.status = status;
        this.comentario = comentario;
        this.dataAlteracao = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Solicitacao getSolicitacao() { return solicitacao; }
    public void setSolicitacao(Solicitacao solicitacao) { this.solicitacao = solicitacao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public LocalDateTime getDataAlteracao() { return dataAlteracao; }
    public void setDataAlteracao(LocalDateTime dataAlteracao) { this.dataAlteracao = dataAlteracao; }
}
