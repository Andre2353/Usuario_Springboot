package com.biolab.authsysystem.repository;

import com.biolab.authsysystem.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findAllByEmail (String email);
}
