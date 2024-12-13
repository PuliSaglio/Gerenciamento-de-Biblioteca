package com.biblioteca.service;

import com.biblioteca.exceptions.IsbnDuplicadoException;
import com.biblioteca.exceptions.LivroNaoEncontradoException;
import com.biblioteca.model.Livro;
import com.biblioteca.repository.LivroRepository;


import java.util.Comparator;
import java.util.stream.Stream;

public class LivroService{

    private final LivroRepository livroRepository;
    //Injeção de dependencia
    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }


    private void verificaDuplicidade(String isbn) {
        if (livroRepository.buscarPorIsbn(isbn).isPresent()) {
            throw new IsbnDuplicadoException("Já existe um livro com esse ISBN");
        }
    }


    private void verificaExistenciaIsbn(String isbn){
        if(!livroRepository.buscarPorIsbn(isbn).isPresent()){
            throw new LivroNaoEncontradoException("Não existe um livro com esse ISBN");
        }
    }

    public void excluirLivro(Livro livro){
        verificaExistenciaIsbn(livro.getISBN());
        livroRepository.excluir(livro.getISBN());
    }

    public void adicionarLivro(Livro livro){
        verificaDuplicidade(livro.getISBN());
        livroRepository.salvar(livro);
    }

    public void alterarAutorPorIsbn(String isbn, String autorNovo){
        livroRepository.buscarPorIsbn(isbn)
                .ifPresentOrElse(
                livro -> {
                    livro.setAutor(autorNovo);
                    livroRepository.salvar(livro);
                },
                () -> { throw new LivroNaoEncontradoException("Não existe livro com esse ISBN");}
        );

    }

    public void alterarCategoriaPorIsbn(String isbn, String categoriaNova){
        livroRepository.buscarPorIsbn(isbn)
                .ifPresentOrElse(
                        livro -> {
                            livro.setCategoria(categoriaNova);
                            livroRepository.salvar(livro);
                        },
                        () -> {throw new LivroNaoEncontradoException("Nao existe livro com esse ISBN");}
                );
    }

    public void alterarTituloPorIsbn(String isbn, String tituloNovo){
        verificaExistenciaIsbn(isbn);
        livroRepository.buscarPorIsbn(isbn)
                .ifPresentOrElse(
                        livro -> {
                            livro.setTitulo(tituloNovo);
                            livroRepository.salvar(livro);
                        },
                        () -> {throw new LivroNaoEncontradoException("Nao existe livro com esse ISBN");}
                );
    }

    public void alterarDisponibilidadePorIsbn(String isbn, boolean disponibilidadeNovo){
        verificaExistenciaIsbn(isbn);
        livroRepository.buscarPorIsbn(isbn)
                .ifPresentOrElse(
                        livro -> {
                            livro.setDisponivel(disponibilidadeNovo);
                            livroRepository.salvar(livro);
                        },
                        () -> {throw new LivroNaoEncontradoException("Nao existe livro com esse ISBN");}
                );
    }

    public Stream<Livro> ordenarPorTitulo(){
        return livroRepository.listarTodos().stream()
                .sorted(Comparator.comparing(Livro::getTitulo));
    }

    public Stream<Livro> ordenarPorAutor(){
        return livroRepository.listarTodos().stream()
                .sorted(Comparator.comparing(Livro::getAutor));
    }

    public Stream<Livro> ordenarPorCategoria(){
        return livroRepository.listarTodos().stream()
                .sorted(Comparator.comparing(Livro::getCategoria));
    }

    public Stream<Livro> ordenarPorDisponibilidade(){
        return livroRepository.listarTodos().stream()
                .sorted(Comparator.comparing(Livro::getDisponivel));
    }

    public int totalLivrosCadastrados(){
        return livroRepository.listarTodos().size();
    }

}
