package com.biolab.authsysystem.DTOs;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class Usuariorequest {
    @NotNull
    private String name;
    @Email
    private String email;
    @Size(min = 6,max = 20, message = "teste hahahahah")
    private String senha;
    private LocalDate dtcriacao;

    public Usuariorequest() {
    }

    public Usuariorequest(String name, String email, String senha) {
        this.name = name;
        this.email = email;
        this.senha = senha;
    }

    public Usuariorequest(String name, String email, String senha, LocalDate dtcriacao) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.dtcriacao = dtcriacao;
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
}
