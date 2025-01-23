package com.biblioteca.GerenciamentoBiblioteca.service;

import com.biblioteca.GerenciamentoBiblioteca.exceptions.IdUsuarioDuplicadoException;
import com.biblioteca.GerenciamentoBiblioteca.exceptions.UsuarioNaoEncontradoException;
import com.biblioteca.GerenciamentoBiblioteca.model.Usuario;
import com.biblioteca.GerenciamentoBiblioteca.repository.UsuarioRepository;
import com.biblioteca.GerenciamentoBiblioteca.repository.UsuarioRepositoryImplementacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * Classe de serviço que contém as regras de negócio para a entidade Usuario
 * @Author: Ignacio Rossini
 * @Autowired: Injeção de dependência para o repositório de Usuario
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositoryImplementacao usuarioRepository;

    /**
     * Método que verifica se já existe um Usuario com o ID informado
     * @param id: ID do Usuario a ser verificado
     * @throws IdUsuarioDuplicadoException: Exceção lançada caso já exista um Usuario com o ID informado
     */
    public void verificaDuplicidade(Integer id) {
        if(usuarioRepository.buscarPorID(id).isPresent()){
            throw new IdUsuarioDuplicadoException("Já existe um Usuario com esse ID");
        }
    }

    /**
     * Método que verifica se existe um Usuario com o ID informado
     * @param id: ID do Usuario a ser verificado
     * @throws UsuarioNaoEncontradoException: Exceção lançada caso não exista um Usuario com o ID informado
     */
    public void verificaExistenciaId(Integer id) {
        if (!usuarioRepository.buscarPorID(id).isPresent()) {
            throw new UsuarioNaoEncontradoException("Não existe um Usuario com esse ID");
        }
    }

    /**
     * Método que exclui um Usuario
     * @param id: ID do Usuario a ser excluído
     * @throws UsuarioNaoEncontradoException: Exceção lançada caso não exista um Usuario com o ID informado
     */
    public void excluirUsuario(Integer id) {
        verificaExistenciaId(id);
        usuarioRepository.excluir(id);
    }

    /**
     * Método que adiciona um Usuario
     * @param usuario: Usuario a ser adicionado
     */
    public void adicionarUsuario(Usuario usuario) {
        verificaDuplicidade(usuario.getID());
        usuarioRepository.salvar(usuario);
    }

    /**
     * Método que altera o nome de um Usuario
     * @param id: ID do Usuario a ser alterado
     * @param nomeUsuario: Novo nome do Usuario
     * @throws UsuarioNaoEncontradoException: Exceção lançada caso não exista um Usuario com o ID informado
     */
    public void alterarNomeUsuario(Integer id, String nomeUsuario) {
        usuarioRepository.buscarPorID(id).ifPresentOrElse(
                usuario -> {
                    System.out.println(nomeUsuario);
                    usuario.setNome(nomeUsuario);

                    usuarioRepository.salvar(usuario);
                },
                () -> {throw new UsuarioNaoEncontradoException("Nao existe um Usuario com esse ID");}
        );
    }

    /**
     * Método que altera o email de um Usuario
     * @param id: ID do Usuario a ser alterado
     * @param emailUsuario: Novo email do Usuario
     * @throws UsuarioNaoEncontradoException: Exceção lançada caso não exista um Usuario com o ID informado
     */
    public void alterarEmailUsuario(Integer id, String emailUsuario) {
        usuarioRepository.buscarPorID(id).ifPresentOrElse(
                usuario -> {
                    usuario.setEmail(emailUsuario);
                    usuarioRepository.salvar(usuario);
                },
                () -> {throw new UsuarioNaoEncontradoException("Nao existe um Usuario com esse ID");}
        );
    }

    /**
     * Método que lista todos os Usuarios
     * @return {@link List} - Lista de Usuarios
     */
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarTodos();
    }

    /**
     * Método que busca um Usuario pelo ID
     * @param id: ID do Usuario a ser buscado
     * @return {@link Optional} - Usuario encontrado ou um {@link Optional#empty()} se não for encontrado.
     */
    public Optional<Usuario> buscarUsuarioPorId(Integer id) {
        return usuarioRepository.buscarPorID(id);
    }

    /**
     * Método que ordena os Usuarios por nome
     * @return {@link List} - Lista de Usuarios ordenados por nome
     */
    public List<Usuario> listarUsuariosPorNome() {
        return usuarioRepository.listarTodos().stream()
                .sorted(Comparator.comparing(Usuario::getNome)).toList();
    }

    /**
     * Método que ordena os Usuarios por email
     * @return {@link List} - Lista de Usuarios ordenados por email
     */
    public List<Usuario> listarUsuariosPorEmail() {
        return usuarioRepository.listarTodos().stream()
                .sorted(Comparator.comparing(Usuario::getEmail)).toList();
    }
}
