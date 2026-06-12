package com.reche.aep.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String protocolo;

    private String categoria;
    private String descricao;
    private String localizacao;
    private boolean anonimo;
    private String status;
    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "solicitacao", cascade = CascadeType.ALL)
    private List<StatusUpdate> historico = new ArrayList<>();

    public Solicitacao() {}

    public Solicitacao(String protocolo, String categoria, String descricao, String localizacao, boolean anonimo) {
        this.protocolo = protocolo;
        this.categoria = categoria;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.anonimo = anonimo;
        this.status = "TRIAGEM";
        this.dataCriacao = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProtocolo() { return protocolo; }
    public void setProtocolo(String protocolo) { this.protocolo = protocolo; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public boolean isAnonimo() { return anonimo; }
    public void setAnonimo(boolean anonimo) { this.anonimo = anonimo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public List<StatusUpdate> getHistorico() { return historico; }
    public void setHistorico(List<StatusUpdate> historico) { this.historico = historico; }
}
