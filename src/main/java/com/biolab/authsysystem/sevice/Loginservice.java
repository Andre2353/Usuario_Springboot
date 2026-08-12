package com.biolab.authsysystem.sevice;

import com.biolab.authsysystem.DTOs.LoginDTO;
import com.biolab.authsysystem.entities.Usuario;
import com.biolab.authsysystem.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class Loginservice {
    private final UsuarioRepository usuarioRepository;

    public Loginservice(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public String login(LoginDTO loginDTO){
        //Verifica se o usario existe
        Usuario usuario = usuarioRepository.findAllByEmail(loginDTO.getEmail());
        if (usuario == null){
            return "Usuario não cadastrado";
           }
        // o usuario existe vamos verificar a senha
        if (usuario.getSenha().equals(loginDTO.getSenha())){
            return "Acesso liberado";
        }
        // retorno caso a senha incorreta
        return "Senha ou e-mail incorretos";
    }
}
