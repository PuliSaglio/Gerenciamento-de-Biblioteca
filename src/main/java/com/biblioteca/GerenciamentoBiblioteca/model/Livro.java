package com.biblioteca.GerenciamentoBiblioteca.model;


/**Classe que representa um livro, onde serão armazenados os dados do título, autor, ISBN, categoria e disponibilidade.
 * @autor Ignacio Rossini
 */
public class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private String categoria;
    private boolean disponivel;


    public Livro(String titulo, String autor, String isbn, String categoria , boolean disponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.categoria = categoria;
        this.disponivel = disponivel;
    }

    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getAutor(){
        return autor;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }

    public String getISBN(){
        return isbn;
    }

    public void setISBN(String isbn){
        this.isbn = isbn;

    }

    public String getCategoria(){
        return categoria;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    public boolean getDisponivel(){
        return disponivel;
    }
    public void setDisponivel(boolean disponivel){
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", isbn='" + isbn + '\'' +
                ", categoria='" + categoria + '\'' +
                ", disponivel=" + disponivel +
                '}';
    }
}

