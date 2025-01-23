package com.biblioteca.GerenciamentoBiblioteca.repository;

import com.biblioteca.GerenciamentoBiblioteca.model.Usuario;

import java.util.List;
import java.util.Optional;

//implementar CRUD (Create Read Update Delete)
public interface UsuarioRepository {
    void salvar(Usuario usuario);
    //Optional pode ou nao conter um valor null
    Optional<Usuario> buscarPorID(int id);
    List<Usuario> listarTodos();
    void excluir(int id);

}
