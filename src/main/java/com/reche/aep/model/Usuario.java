package com.reche.aep.model;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String nome;
    private String papel; // EX: "ADMIN", "ATENDENTE"

    public Usuario() {}

    public Usuario(String username, String password, String nome, String papel) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.papel = papel;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getPapel() { return papel; }
    public void setPapel(String papel) { this.papel = papel; }
}
