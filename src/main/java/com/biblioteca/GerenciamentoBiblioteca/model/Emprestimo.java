package com.biblioteca.GerenciamentoBiblioteca.model;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**Classe que representa um empréstimo de um livro,
 * onde serão armazenados os dados do usuário, livro, data de empréstimo e data de devolução.
 * @author Ignacio Rossini
 */
public class Emprestimo {
    private long idTransacao = 0;
    private Usuario usuario;
    private Livro livro;
    private final LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;


    public Emprestimo(Usuario usuario, Livro livro) {
        this.idTransacao = idTransacao++;
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = LocalDate.now().plusDays(30);
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public long getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(long idTransacao) {
        this.idTransacao = idTransacao;
    }
}
