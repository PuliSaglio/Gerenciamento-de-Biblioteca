package com.biblioteca.GerenciamentoBiblioteca.dto;

public class AlterarUsuarioDTO {
    private int id;
    private String atributoNovo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAtributoNovo() {
        return atributoNovo;
    }

    public void setAtributoNovo(String atributoNovo) {
        this.atributoNovo = atributoNovo;
    }
}
