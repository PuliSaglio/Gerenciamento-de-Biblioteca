package com.biblioteca.GerenciamentoBiblioteca.service;

import com.biblioteca.GerenciamentoBiblioteca.exceptions.IsbnDuplicadoException;
import com.biblioteca.GerenciamentoBiblioteca.exceptions.LivroIndisponivelException;
import com.biblioteca.GerenciamentoBiblioteca.exceptions.LivroNaoEncontradoException;
import com.biblioteca.GerenciamentoBiblioteca.model.Livro;
import com.biblioteca.GerenciamentoBiblioteca.repository.LivroRepository;
import com.biblioteca.GerenciamentoBiblioteca.repository.LivroRepositoryImplementacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
/**
 * Classe de serviço que contém as regras de negócio para a entidade Livro
 * @Author: Ignacio Rossini
 * @Autowired: Injeção de dependência para o repositório de Livro
 */
@Service
public class LivroService{

    @Autowired
    private LivroRepositoryImplementacao livroRepository;


    /**
     * Método que verifica se já existe um livro com o ISBN informado
     * @param isbn: ISBN do livro a ser verificado
     * @throws IsbnDuplicadoException: Exceção lançada caso já exista um livro com o ISBN informado
     */
    private void verificaDuplicidade(String isbn) {
        if (livroRepository.buscarPorIsbn(isbn).isPresent()) {
            throw new IsbnDuplicadoException("Já existe um livro com esse ISBN");
        }
    }

    /**
     * Método que verifica se existe um livro com o ISBN informado
     * @param isbn: ISBN do livro a ser verificado
     * @throws LivroNaoEncontradoException: Exceção lançada caso não exista um livro com o ISBN informado
     */
    private void verificaExistenciaIsbn(String isbn){
        if(livroRepository.buscarPorIsbn(isbn).isEmpty()){
            throw new LivroNaoEncontradoException("Não existe um livro com esse ISBN");
        }
    }

    /**
     * Método que verifica se um livro está disponível
     * @param isbn: ISBN do livro a ser verificado
     * @return boolean: Retorna true caso o livro esteja disponível
     * @throws LivroNaoEncontradoException: Exceção lançada caso não exista um livro com o ISBN informado
     * @throws LivroIndisponivelException: Exceção lançada caso o livro não esteja disponível
     */
    public boolean verificaDisponibilidade(String isbn){
        Livro livro = livroRepository.buscarPorIsbn(isbn)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro com ISBN " + isbn + " não encontrado"));

        if (!livro.getDisponivel()) {
            throw new LivroIndisponivelException("Este livro está indisponível no momento.");
        }

        return true;
    }

    /**Método que exclui um livro do repositório
     * @param isbn: ISBN do livro a ser excluído
     *@throws LivroNaoEncontradoException: Exceção lançada caso não exista um livro com o ISBN informado
     */
    public void excluirLivro(String isbn){
        verificaExistenciaIsbn(isbn);
        livroRepository.excluir(isbn);
    }

    /**
     * Método que adiciona um livro ao repositório
     * @param livro: Livro a ser adicionado
     * @throws IsbnDuplicadoException: Exceção lançada caso já exista um livro com o ISBN informado
     */
    public void adicionarLivro(Livro livro){
        verificaDuplicidade(livro.getISBN());
        livroRepository.salvar(livro);
    }

    /**
     * Método que altera o autor de um livro
     * @param isbn: ISBN do livro a ser alterado
     * @param autorNovo: Novo autor do livro
     * @throws LivroNaoEncontradoException: Exceção lançada caso não exista um livro com o ISBN informado
     */
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

    /**
     * Método que altera a categoria de um livro
     * @param isbn: ISBN do livro a ser alterado
     * @param categoriaNova: Nova categoria do livro
     * @throws LivroNaoEncontradoException: Exceção lançada caso não exista um livro com o ISBN informado
     */
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

    /**
     * Método que altera o título de um livro
     * @param isbn: ISBN do livro a ser alterado
     * @param tituloNovo: Novo título do livro
     * @throws LivroNaoEncontradoException: Exceção lançada caso não exista um livro com o ISBN informado
     */
    public void alterarTituloPorIsbn(String isbn, String tituloNovo){
        livroRepository.buscarPorIsbn(isbn)
                .ifPresentOrElse(
                        livro -> {
                            livro.setTitulo(tituloNovo);
                            livroRepository.salvar(livro);
                        },
                        () -> {throw new LivroNaoEncontradoException("Nao existe livro com esse ISBN");}
                );
    }

    /**
     * Método que altera a disponibilidade de um livro
     * @param isbn: ISBN do livro a ser alterado
     * @param disponibilidadeNovo: Nova disponibilidade do livro
     * @throws LivroNaoEncontradoException: Exceção lançada caso não exista um livro com o ISBN informado
     */
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

    /**
     * Método que ordena os livros por título
     * @return {@link List}: Lista de livros ordenados por título
     */
    public List<Livro> ordenarPorTitulo(){
        return livroRepository.listarTodos().stream()
                .sorted(Comparator.comparing(Livro::getTitulo)).toList();
    }

    /**
     * Método que ordena os livros por autor
     * @return {@link List}: Lista de livros ordenados por autor
     */
    public List<Livro> ordenarPorAutor(){
        return livroRepository.listarTodos().stream()
                .sorted(Comparator.comparing(Livro::getAutor)).toList();
    }

    /**
     * Método que ordena os livros por categoria
     * @return {@link List}: Lista de livros ordenados por categoria
     */
    public List<Livro> ordenarPorCategoria(){
        return livroRepository.listarTodos().stream()
                .sorted(Comparator.comparing(Livro::getCategoria)).toList();
    }

    /**
     * Método que ordena os livros por disponibilidade
     * @return {@link List}: Lista de livros ordenados por disponibilidade
     */
    public List<Livro> ordenarPorDisponibilidade(){
        return livroRepository.listarTodos().stream()
                .sorted(Comparator.comparing(Livro::getDisponivel)).toList();
    }

   /**
     * Método que retorna a quantidade total de livros cadastrados
     * @return int: Quantidade total de livros cadastrados
     */
    public int totalLivrosCadastrados(){
        return livroRepository.listarTodos().size();
    }

    /** Método que lista todos os livros cadastrados
     * @return {@link List}: Lista de todos os livros cadastrados
     */
    public List<Livro> listarTodosLivros(){
        return livroRepository.listarTodos();
    }

    /**
     * Método que retorna um livro pelo ISBN
     * @param isbn: ISBN do livro a ser retornado
     * @return {@link Optional}: Livro com o ISBN informado ou um {@link Optional#empty()} caso não for encontrado.
     */
    public Optional<Livro> pegarPorIsbn(String isbn){
        return livroRepository.buscarPorIsbn(isbn);
    }

}
