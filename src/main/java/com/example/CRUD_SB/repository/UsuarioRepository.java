package com.example.CRUD_SB.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CRUD_SB.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}