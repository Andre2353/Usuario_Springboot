package com.biolab.authsysystem.sevice;

import com.biolab.authsysystem.DTOs.UsuarioResponse;
import com.biolab.authsysystem.DTOs.Usuariorequest;
import com.biolab.authsysystem.entities.Usuario;
import com.biolab.authsysystem.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class Usuarioservice {

    private final UsuarioRepository usuarioRepository;

    public Usuarioservice(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuariorequest criarUsuario(Usuariorequest request) {
        Usuario usuario = new Usuario();
        usuario.setName(request.getName());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(request.getSenha());
        usuario.setDtcriacao(LocalDate.now());
        usuarioRepository.save(usuario);
        return request;
    }

    public List<UsuarioResponse> mostrarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> new UsuarioResponse(
                        usuario.getId(),
                        usuario.getName(),
                        usuario.getEmail(),
                        usuario.getDtcriacao()))
                .toList();
    }

    public UsuarioResponse buscarid(long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + id));

        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setId(usuario.getId());
        usuarioResponse.setDtcriacao(usuario.getDtcriacao());
        usuarioResponse.setName(usuario.getName());
        usuarioResponse.setEmail(usuario.getEmail());
        return usuarioResponse;
    }

    public String deletar(long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) { // Corrigido de (usuario == null) para (usuario.isEmpty())
            return "Usuário não existe";
        } else {
            usuarioRepository.deleteById(id);
            return "Usuário kickado";
        }
    }

    public String atualizarid(Long id, Usuario usuarioatualizado) {
        Usuario usuarioexistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("usuario não encontrado: " + id));

        usuarioexistente.setName(usuarioatualizado.getName());
        usuarioexistente.setEmail(usuarioatualizado.getEmail());
        usuarioexistente.setSenha(usuarioatualizado.getSenha());

        usuarioRepository.save(usuarioexistente);
        return "usuario atualizado com sucesso";
    }
}