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
        //nao funciona porque eu preciso passar a key do hashmap
//        como estou passando um titulo que nao é uma key ele retorna null
//        preciso dar um jeito de acessar a key por meio do titulo ou vice versa
        return Optional.ofNullable(livros.get(titulo));
    }

    public List<Livro> listarTodos(){
        return new ArrayList<Livro>(livros.values());
    }

    public static void main(String args[]){
        LivroRepositoryImplementacao livro = new LivroRepositoryImplementacao();

        livro.salvar(new Livro("O pequeno principe", "autor legal" , "123-123-123-x", "Fantasia" , true));
        livro.salvar(new Livro("Diario de um banana", "autor divertido", "122-122-122-x", "Comédia" , false));

        System.out.println("Listando todos..." +
        livro.listarTodos());
        System.out.println("Buscando por ISBN..." +
        livro.buscarPorIsbn("123-123-123-x"));
        System.out.println("Buscando por titulo..." +
        livro.buscarPorTitulo("Diario de um banana"));

        livro.excluir("123-123-123-x");
        System.out.println("Listando todos ..." +
        livro.listarTodos());

    }
}
