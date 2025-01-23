package com.biblioteca.GerenciamentoBiblioteca.controller;

import com.biblioteca.GerenciamentoBiblioteca.dto.AlterarUsuarioDTO;
import com.biblioteca.GerenciamentoBiblioteca.model.Usuario;
import com.biblioteca.GerenciamentoBiblioteca.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuario", description = "Operações relacionadas a usuários")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Listar todos os usuários", method = "GET")
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarUsuarios();
    }

    @Operation(summary = "Adicionar um novo usuário", method = "POST")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void adicionarUsuario(
            @RequestBody Usuario usuario
    ){
        usuarioService.adicionarUsuario(usuario);
    }

    @Operation(summary = "Excluir um usuário", method = "DELETE")
    @DeleteMapping(value = "/excluir/{id}")
    public void excluirUsuario(@PathVariable int id){
        usuarioService.excluirUsuario(id);
    }

    @Operation(summary = "Alterar o nome de um usuário", method = "PUT")
    @PutMapping(value = "/alterar/nome", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void alterarNome(@RequestBody AlterarUsuarioDTO alterarUsuarioDTO){
        usuarioService.alterarNomeUsuario(alterarUsuarioDTO.getId(), alterarUsuarioDTO.getAtributoNovo());
    }

    @Operation(summary = "Alterar o email de um usuário", method = "PUT")
    @PutMapping(value = "/alterar/email", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void alterarEmail(@RequestBody AlterarUsuarioDTO alterarUsuarioDTO){
        usuarioService.alterarEmailUsuario(alterarUsuarioDTO.getId(), alterarUsuarioDTO.getAtributoNovo());
    }

    @Operation(summary = "Ordenar usuários por nome", method = "GET")
    @GetMapping(value = "/ordenados/nome" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Usuario> ordenarNome(){
        return usuarioService.listarUsuariosPorNome();
    }

    @Operation(summary = "Ordenar usuários por email", method = "GET")
    @GetMapping(value = "/ordenados/email", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public List<Usuario> ordenarEmail(){
        return usuarioService.listarUsuariosPorEmail();
    }
}
