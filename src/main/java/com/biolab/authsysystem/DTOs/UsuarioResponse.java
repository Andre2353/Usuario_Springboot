package com.biolab.authsysystem.DTOs;

import com.biolab.authsysystem.entities.Usuario;

import java.time.LocalDate;
import java.util.Optional;

public class UsuarioResponse {
    private long id;
    private String name;
    private String email;
    private LocalDate dtcriacao;

    public UsuarioResponse() {
    }

    public UsuarioResponse( String name, String email, LocalDate dtcriacao) {
        this.name = name;
        this.email = email;
        this.dtcriacao = dtcriacao;
    }

    public UsuarioResponse(long id, String name, String email,  LocalDate dtcriacao) {
        this.id = id;
        this.name = name;
        this.email = email;
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


    public LocalDate getDtcriacao() {
        return dtcriacao;
    }

    public void setDtcriacao(LocalDate dtcriacao) {
        this.dtcriacao = dtcriacao;
    }
}
