package com.biblioteca.repository;

import com.biblioteca.model.Livro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class LivroRepositoryImplementacao implements LivroRepository {
    HashMap<String, Livro> livros = new HashMap<String, Livro>();

    @Override
    public void salvar(Livro livro){
        livros.put(livro.getISBN(), livro);
    }

    @Override
    public void excluir(String isbn) {
        livros.remove(isbn);
    }

    public Optional<Livro> buscarPorIsbn(String isbn){
        return Optional.ofNullable(livros.get(isbn));
    }

    public Optional<Livro> buscarPorTitulo(String titulo){
        return Optional.of(livros.values().stream().filter(livro -> livro.getTitulo().equals(titulo)).findFirst().get());
    }

    public List<Livro> listarTodos(){

        return new ArrayList<Livro>(livros.values());
    }

}
