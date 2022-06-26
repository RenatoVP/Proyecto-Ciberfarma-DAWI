package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.entity.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	public Usuario findByUsernameAndContrasena(String correo, String contrasena);
}
