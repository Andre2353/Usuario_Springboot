package com.biolab.authsysystem.controllers;

import com.biolab.authsysystem.DTOs.UsuarioResponse;
import com.biolab.authsysystem.DTOs.Usuariorequest;
import com.biolab.authsysystem.entities.Usuario;
import com.biolab.authsysystem.sevice.Usuarioservice;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> criarusuario(@Valid @RequestBody Usuariorequest req) {
        return ResponseEntity.ok("criado com sucessp " + usuarioservice.criarUsuario(req));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> mostrar() { // Alterado de private para public
        return ResponseEntity.ok(usuarioservice.mostrarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarid(@PathVariable("id") Long id) {
        return ResponseEntity.ok(usuarioservice.buscarid(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> apagaid(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioservice.deletar(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> atualizarid(@PathVariable Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioservice.atualizarid(id, usuario));
    }
}