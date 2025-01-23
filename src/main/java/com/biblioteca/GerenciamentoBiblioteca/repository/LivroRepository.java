package com.biblioteca.GerenciamentoBiblioteca.repository;

import com.biblioteca.GerenciamentoBiblioteca.model.Livro;

import java.util.Optional;
import java.util.List;


public interface LivroRepository {
    void salvar(Livro livro);
    void excluir(String isbn);
    Optional<Livro> buscarPorIsbn(String isbn);
    Optional<Livro> buscarPorTitulo(String titulo);
    List<Livro> listarTodos();

}
