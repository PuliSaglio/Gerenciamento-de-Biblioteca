package com.biblioteca.service;

import com.biblioteca.exceptions.IdUsuarioDuplicadoException;
import com.biblioteca.exceptions.UsuarioNaoEncontradoException;
import com.biblioteca.model.Usuario;
import com.biblioteca.repository.UsuarioRepository;

import java.util.Comparator;
import java.util.stream.Stream;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    //Injeçao de dependencia
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //Parei pra pensar um pouco e nao

    public void verificaDuplicidade(Integer id) {
        if(usuarioRepository.buscarPorID(id).isPresent()){
            throw new IdUsuarioDuplicadoException("Já existe um Usuario com esse ID");
        }
    }

    public void verificaExistenciaId(Integer id) {
        if (!usuarioRepository.buscarPorID(id).isPresent()) {
            throw new UsuarioNaoEncontradoException("Não existe um Usuario com esse ID");
        }
    }

    //excluir
    public void excluirUsuario(Integer id) {
        verificaExistenciaId(id);
        usuarioRepository.excluir(id);
    }

    //adicionar
    public void adicionarUsuario(Usuario usuario) {
        verificaDuplicidade(usuario.getID());
        usuarioRepository.salvar(usuario);
    }
    //alterar atributos
    public void alterarNomeUsuario(Integer id, String nomeUsuario) {
        usuarioRepository.buscarPorID(id).ifPresentOrElse(
                usuario -> {
                    usuario.setNome(nomeUsuario);
                    usuarioRepository.salvar(usuario);
                },
                () -> {throw new UsuarioNaoEncontradoException("Nao existe um Usuario com esse ID");}
        );
    }

    public void alterarEmailUsuario(Integer id, String emailUsuario) {
        usuarioRepository.buscarPorID(id).ifPresentOrElse(
                usuario -> {
                    usuario.setEmail(emailUsuario);
                    usuarioRepository.salvar(usuario);
                },
                () -> {throw new UsuarioNaoEncontradoException("Nao existe um Usuario com esse ID");}
        );
    }

    //ordenar usuarios
    public Stream<Usuario> listarUsuariosPorNome() {
        return usuarioRepository.listarTodos().stream()
                .sorted(Comparator.comparing(Usuario::getNome));
    }
    public Stream<Usuario> listarUsuariosPorEmail() {
        return usuarioRepository.listarTodos().stream()
                .sorted(Comparator.comparing(Usuario::getEmail));
    }
}
