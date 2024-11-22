package com.biblioteca.model;

public class Livro {
    private String titulo;
    private String autor;
    private String ISBN;
    private String categoria;
    private boolean disponivel;

    public Livro() {}

    public Livro(String titulo, String autor, String ISBN, String categoria , boolean disponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.categoria = categoria;
        this.disponivel = false;
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
        return ISBN;
    }

    public void setISBN(String ISBN){
        this.ISBN = ISBN;

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
}

