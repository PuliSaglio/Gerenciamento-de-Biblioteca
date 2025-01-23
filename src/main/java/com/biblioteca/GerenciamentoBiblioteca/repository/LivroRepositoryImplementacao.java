package com.biblioteca.GerenciamentoBiblioteca.repository;

import com.biblioteca.GerenciamentoBiblioteca.model.Livro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
/**Classe que implementa a interface LivroRepository,
 * onde serão armazenados os métodos de salvar, excluir, buscar por ISBN,
 * buscar por título e listar todos os livros.
 * @autor Ignacio Rossini
 */
@Service
public class LivroRepositoryImplementacao implements LivroRepository {
    HashMap<String, Livro> livros = new HashMap<String, Livro>();

    /**Método que salva um livro.
     * @param livro Livro - livro a ser salvo.
     */
    @Override
    public void salvar(Livro livro){
        livros.put(livro.getISBN(), livro);
    }

    /**Método que exclui um livro.
     * @param isbn String - ISBN do livro a ser excluído.
     */
    @Override
    public void excluir(String isbn) {
        livros.remove(isbn);
    }

    /**Método que busca um livro por ISBN.
     * @param isbn String - ISBN do livro a ser buscado.
     * @return um {@link Optional} contendo o livro encontrado, ou um {@link Optional#empty()} se não for encontrado.
     */
    @Override
    public Optional<Livro> buscarPorIsbn(String isbn){
        return Optional.ofNullable(livros.get(isbn));
    }

    /**Método que busca um livro por título.
     * @param titulo String - título do livro a ser buscado.
     * @return um {@link Optional} contendo o livro encontrado, ou um {@link Optional#empty()} se não for encontrado.
     */
    @Override
    public Optional<Livro> buscarPorTitulo(String titulo){
        return Optional.of(livros.values().stream().filter(livro -> livro.getTitulo().equals(titulo)).findFirst().get());
    }

    /**Método que lista todos os livros.
     * @return uma {@link List} contendo todos os livros disponíveis no repositório.
     */
    @Override
    public List<Livro> listarTodos(){
        return new ArrayList<Livro>(livros.values());
    }

}
