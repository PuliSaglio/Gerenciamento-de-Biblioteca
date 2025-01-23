package com.biblioteca.GerenciamentoBiblioteca.repository;

import com.biblioteca.GerenciamentoBiblioteca.model.Emprestimo;
import com.biblioteca.GerenciamentoBiblioteca.model.Livro;
import com.biblioteca.GerenciamentoBiblioteca.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface EmprestimoRepository {
    void salvar(Emprestimo emprestimo);
    void excluir(Integer usuarioId);
    Optional<Emprestimo> buscarPorUsuario(Usuario usuario);
    Optional<Emprestimo> buscarPorLivro(Livro livro);
    List<Emprestimo> listarTodos();
    Emprestimo buscarPorId(Integer id);

}
