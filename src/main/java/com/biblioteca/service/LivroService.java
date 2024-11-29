package com.biblioteca.service;

import com.biblioteca.exceptions.IsbnDuplicadoException;
import com.biblioteca.model.Livro;
import com.biblioteca.repository.LivroRepository;
import com.biblioteca.repository.LivroRepositoryImplementacao;

import java.util.HashMap;

public class LivroService{

    private final LivroRepository livroRepository;
    //Injeção de dependencia
    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }


    private void verificaDuplicidade(Livro livro){
        livroRepository.buscarPorIsbn(livro.getISBN())
                .orElseThrow(() -> new IsbnDuplicadoException("Já existe um livro com esse ISBN"));
    }

    public void adicionarLivro(Livro livro){
        verificaDuplicidade(livro);
        livroRepository.salvar(livro);
    }

}
