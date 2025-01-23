package com.biblioteca.GerenciamentoBiblioteca.dto;

public class AlterarEmprestimoDTO {
    private Integer usuarioId;
    private String livroIsbn;

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getLivroIsbn() {
        return livroIsbn;
    }

    public void setLivroIsbn(String livroIsbn) {
        this.livroIsbn = livroIsbn;
    }
}
