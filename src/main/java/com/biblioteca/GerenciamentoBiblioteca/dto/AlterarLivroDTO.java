package com.biblioteca.GerenciamentoBiblioteca.dto;

public class AlterarLivroDTO {
    private String isbn;
    private String atributoNovo;

    // Getters e Setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAtributoNovo() {
        return atributoNovo;
    }

    public void setAtributoNovo(String atributoNovo) {
        this.atributoNovo = atributoNovo;
    }
}

