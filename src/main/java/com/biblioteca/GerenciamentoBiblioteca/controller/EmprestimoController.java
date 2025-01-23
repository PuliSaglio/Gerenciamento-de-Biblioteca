package com.biblioteca.GerenciamentoBiblioteca.controller;

import com.biblioteca.GerenciamentoBiblioteca.dto.AlterarEmprestimoDTO;
import com.biblioteca.GerenciamentoBiblioteca.model.Emprestimo;
import com.biblioteca.GerenciamentoBiblioteca.model.Livro;
import com.biblioteca.GerenciamentoBiblioteca.model.Usuario;
import com.biblioteca.GerenciamentoBiblioteca.service.EmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Emprestimo", description = "Operações relacionadas a empréstimos")
@RequestMapping(value = "/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @Operation(summary = "Criar um novo empréstimo", method = "POST")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void criarEmprestimo(@RequestBody
                                AlterarEmprestimoDTO EmprestimoDTO) {
        emprestimoService.criarEmprestimo(EmprestimoDTO);

    }

    @Operation(summary = "Excluir um empréstimo", method = "DELETE")
    @DeleteMapping(value = "/excluir/{id}/{isbn}")
    public void excluirEmprestimo(@PathVariable int id, @PathVariable String isbn) {
        emprestimoService.excluirEmprestimo(id, isbn);
    }

    @Operation(summary = "Devolver um empréstimo", method = "PUT")
    @PutMapping(value = "/devolver/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public double devolverEmprestimo(@PathVariable int id) {
        return emprestimoService.devolverEmprestimo(id);
    }

    @Operation(summary = "Listar todos os empréstimos", method = "GET")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Emprestimo> listarEmprestimos() {
        return emprestimoService.buscarEmprestimos();
    }

}
