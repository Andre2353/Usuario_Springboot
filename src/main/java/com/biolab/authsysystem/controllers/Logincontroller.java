package com.biolab.authsysystem.controllers;

import com.biolab.authsysystem.DTOs.LoginDTO;
import com.biolab.authsysystem.sevice.Loginservice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class Logincontroller {

    private final Loginservice loginservice;

    public Logincontroller(Loginservice loginservice) {
        this.loginservice = loginservice;
    }
    @PostMapping
    public ResponseEntity <?> logar(@RequestBody LoginDTO dto){
        return ResponseEntity.ok(loginservice.login(dto));
    }
}
