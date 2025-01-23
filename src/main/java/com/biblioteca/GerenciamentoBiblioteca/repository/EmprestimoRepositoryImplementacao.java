package com.biblioteca.GerenciamentoBiblioteca.repository;

import com.biblioteca.GerenciamentoBiblioteca.exceptions.UsuarioNaoEncontradoException;
import com.biblioteca.GerenciamentoBiblioteca.model.Emprestimo;
import com.biblioteca.GerenciamentoBiblioteca.model.Livro;
import com.biblioteca.GerenciamentoBiblioteca.model.Usuario;
import com.biblioteca.GerenciamentoBiblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
/**Classe que implementa a interface EmprestimoRepository,
 * onde serão armazenados os métodos de salvar, excluir, buscar por ID,
 * buscar por usuário, buscar por livro e listar todos os empréstimos.
 * @autor Ignacio Rossini
*/
@Service
public class EmprestimoRepositoryImplementacao implements EmprestimoRepository{
    HashMap<Usuario, Emprestimo> emprestimos = new HashMap<>();
    @Autowired
    private UsuarioService usuarioService;

    /**Método que salva um empréstimo.
     * @param emprestimo Emprestimo - empréstimo a ser salvo.
     */
    @Override
    public void salvar(Emprestimo emprestimo) {
        emprestimos.put(emprestimo.getUsuario(), emprestimo);
    }

    /**Método que exclui um empréstimo.
     * @param usuarioId Integer - ID do usuário a ser excluído.
     */
    @Override
    public void excluir(Integer usuarioId) {
        emprestimos.remove(usuarioService.buscarUsuarioPorId(usuarioId).
                orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado com ID: " + usuarioId)));
    }

    /**Método que busca um empréstimo por ID.
     * @param id Integer - ID do empréstimo a ser buscado.
     * @return Emprestimo - empréstimo encontrado.
     */
    @Override
    public Emprestimo buscarPorId(Integer id) {
        return emprestimos.values().stream().filter(emprestimo -> emprestimo.getIdTransacao() == id).findFirst().get();
    }

    /**Método que busca um empréstimo por usuário.
     * @param usuario Usuario - usuário a ser buscado.
     * @return Emprestimo - empréstimo encontrado.
     */
    @Override
    public Optional<Emprestimo> buscarPorUsuario(Usuario usuario) {
        return Optional.ofNullable(emprestimos.get(usuario));
    }

    /**Método que busca um empréstimo por livro.
     * @param Livro Livro - livro a ser buscado.
     * @return Emprestimo - empréstimo encontrado.
     */
    @Override
    public Optional<Emprestimo> buscarPorLivro(Livro Livro) {
        return Optional.of(emprestimos.values().stream().filter(emp -> emp.getLivro().equals(Livro)).findFirst().get());
    }

    /**Método que lista todos os empréstimos.
     * @return List<Emprestimo> - lista de empréstimos.
     */
    @Override
    public List<Emprestimo> listarTodos(){
        return new ArrayList<>(emprestimos.values());
    }
}
