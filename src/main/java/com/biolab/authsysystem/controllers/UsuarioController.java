package com.biolab.authsysystem.controllers;

import com.biolab.authsysystem.DTOs.UsuarioResponse;
import com.biolab.authsysystem.DTOs.Usuariorequest;
import com.biolab.authsysystem.entities.Usuario;
import com.biolab.authsysystem.sevice.Usuarioservice;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    private final Usuarioservice usuarioservice;

    public UsuarioController(Usuarioservice usuarioservice) {
        this.usuarioservice = usuarioservice;
    }
    @PostMapping
    public ResponseEntity<?> criarusuario(@Valid @RequestBody Usuariorequest req){
        return ResponseEntity.ok("criado com sucessp " +usuarioservice.criarUsuario(req));
    }
    @GetMapping
    private  ResponseEntity<List<UsuarioResponse>> mostrar(){
        return ResponseEntity.ok (usuarioservice.mostrarUsuarios());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarid(@PathVariable("id") Long id){
        return ResponseEntity.ok(usuarioservice.buscarid(id));
    }
}
