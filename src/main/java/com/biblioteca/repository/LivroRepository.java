package com.biblioteca.repository;

import com.biblioteca.model.Livro;

import java.util.Optional;
import java.util.List;
import java.util.Optional;

public interface LivroRepository {
    void salvar(Livro livro);
    void excluir(String isbn);
    Optional<Livro> buscarPorIsbn(String isbn);
    Optional<Livro> buscarPorTitulo(String titulo);
    List<Livro> listarTodos();

}
