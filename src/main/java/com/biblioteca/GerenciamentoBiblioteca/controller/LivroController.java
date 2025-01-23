package com.biblioteca.GerenciamentoBiblioteca.controller;

import com.biblioteca.GerenciamentoBiblioteca.dto.AlterarLivroDTO;
import com.biblioteca.GerenciamentoBiblioteca.model.Livro;
import com.biblioteca.GerenciamentoBiblioteca.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/livros")
@Tag(name = "Livros", description = "Operações relacionadas a livros")
public class LivroController {
    //Injeção Dependencia
    @Autowired
    private LivroService livroService;

    @Operation(summary = "Listar todos os livros" , method = "GET")
    @GetMapping
    public List<Livro> getAllLivros(){
        return livroService.listarTodosLivros();
    }

    @Operation(summary = "Cadastrar um livro", method = "POST")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void cadastrarLivro(@RequestBody Livro livro) {
        livroService.adicionarLivro(livro);
    }

    @Operation(summary = "Excluir um livro", method = "DELETE")
    @DeleteMapping(value = "excluir/{isbn}")
    public void excluirLivro(@PathVariable String isbn) {
        livroService.excluirLivro(isbn);
    }

    @Operation(summary = "Alterar o autor de um livro", method = "PUT")
    @PutMapping(value = "/alterar/autor",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void alterarAutorLivro(@RequestBody AlterarLivroDTO alterarLivroDTO) {
        livroService.alterarAutorPorIsbn(alterarLivroDTO.getIsbn(), alterarLivroDTO.getAtributoNovo());
    }

    @Operation(summary = "Alterar a categoria de um livro", method = "PUT")
    @PutMapping(value = "/alterar/categoria",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void alterarCategoriaLivro(@RequestBody AlterarLivroDTO alterarLivroDTO) {
        livroService.alterarCategoriaPorIsbn(alterarLivroDTO.getIsbn(), alterarLivroDTO.getAtributoNovo());
    }

    @Operation(summary = "Alterar o título de um livro", method = "PUT")
    @PutMapping(value = "/alterar/titulo",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void alterarTituloLivro(@RequestBody AlterarLivroDTO alterarLivroDTO) {
        livroService.alterarTituloPorIsbn(alterarLivroDTO.getIsbn(), alterarLivroDTO.getAtributoNovo());
    }

    @Operation(summary = "Alterar a disponibilidade de um livro", method = "PUT")
    @PutMapping(value = "/alterar/disponibilidade", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void alterarDisponibilidadeLivro(@RequestBody AlterarLivroDTO alterarLivroDTO) {
        boolean disponibilidadeNova = Boolean.parseBoolean(alterarLivroDTO.getAtributoNovo());
        livroService.alterarDisponibilidadePorIsbn(alterarLivroDTO.getIsbn(), disponibilidadeNova);
    }

    @Operation(summary = "Ordenar livros por título", method = "GET")
    @GetMapping(value = "/ordenados/titulo",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Livro> ordenarTituloLivro() {
        return livroService.ordenarPorTitulo();
    }

    @Operation(summary = "Ordenar livros por autor", method = "GET")
    @GetMapping(value = "ordenados/autor",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Livro> ordenarAutorLivro() {
        return livroService.ordenarPorAutor();
    }

    @Operation(summary = "Ordenar livros por categoria", method = "GET")
    @GetMapping(value = "/ordenados/categoria",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Livro> ordenarCategoriaLivro() {
        return livroService.ordenarPorCategoria();
    }

    @Operation(summary = "Ordenar livros por disponibilidade", method = "GET")
    @GetMapping(value = "ordenados/disponibilidade",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Livro> ordenarDisponibilidadeLivro() {
        return livroService.ordenarPorDisponibilidade();
    }

    @Operation(summary = "Retorna o número total de livros cadastrados", method = "GET")
    @GetMapping(value = "total", produces = MediaType.APPLICATION_JSON_VALUE)
    public int totalLivros() {
        return livroService.totalLivrosCadastrados();
    }

    @Operation(summary = "Buscar livro por ISBN", method = "GET")
    @GetMapping(value = "/{isbn}",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Livro> buscarLivroPorIsbn(@PathVariable String isbn) {
        return livroService.pegarPorIsbn(isbn);
    }

}
