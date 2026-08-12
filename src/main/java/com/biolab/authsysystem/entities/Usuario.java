package com.biolab.authsysystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String name;
    @Email
    @Column(unique = true,comment = "vc é burro ")
    private String email;
    @Size(min = 6,max = 20)
    private String senha;
    private LocalDate dtcriacao;

    public Usuario() {
    }

    public Usuario(String name, String email, String senha, LocalDate dtcriacao) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtcriacao = dtcriacao;
    }

    public Usuario(long id, String name, String email, String senha, LocalDate dtcriacao) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtcriacao = dtcriacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDtcriacao() {
        return dtcriacao;
    }

    public void setDtcriacao(LocalDate dtcriacao) {
        this.dtcriacao = dtcriacao;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", dtcriacao=" + dtcriacao +
                '}';
    }
}
