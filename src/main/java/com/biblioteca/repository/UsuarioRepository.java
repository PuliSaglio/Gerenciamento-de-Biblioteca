package com.biblioteca.repository;

import com.biblioteca.model.Usuario;

import java.util.List;
import java.util.Optional;

//implementar CRUD (Create Read Update Delete)
public interface UsuarioRepository {
    void salvar(Usuario usuario);
    Optional<Usuario> buscarPorID(int id);
    List<Usuario> listarTodos();
    void excluir(int id);

}