package com.biblioteca.GerenciamentoBiblioteca.service;

import com.biblioteca.GerenciamentoBiblioteca.dto.AlterarEmprestimoDTO;
import com.biblioteca.GerenciamentoBiblioteca.exceptions.LivroNaoEncontradoException;
import com.biblioteca.GerenciamentoBiblioteca.exceptions.UsuarioNaoEncontradoException;
import com.biblioteca.GerenciamentoBiblioteca.model.Emprestimo;
import com.biblioteca.GerenciamentoBiblioteca.model.Livro;
import com.biblioteca.GerenciamentoBiblioteca.model.Usuario;
import com.biblioteca.GerenciamentoBiblioteca.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Classe de serviço para Emprestimo
 * @Service indica que a classe é um bean de serviço
 * @Autowired injeta as dependências necessárias
 * @Author Ignacio Rossini
 */
@Service
public class EmprestimoService {

    private final UsuarioService usuarioService;
    private final LivroService livroService;
    private final EmprestimoRepository emprestimoRepository;

    @Autowired
    public EmprestimoService(UsuarioService usuarioService, LivroService livroService, EmprestimoRepository emprestimoRepository) {
        this.usuarioService = usuarioService;
        this.livroService = livroService;
        this.emprestimoRepository = emprestimoRepository;
    }

    /**
     * Método para criar um empréstimo
     * altera a disponibilidade do livro para false
     * @param emprestimoDTO DTO com os dados do empréstimo
     * @throws UsuarioNaoEncontradoException se o usuário não for encontrado
     * @throws LivroNaoEncontradoException se o livro não for encontrado
     */
    public void criarEmprestimo(AlterarEmprestimoDTO emprestimoDTO) {

        if(livroService.verificaDisponibilidade(emprestimoDTO.getLivroIsbn())){

            Usuario usuario = usuarioService.buscarUsuarioPorId(emprestimoDTO.getUsuarioId())
                    .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado com ID: " + emprestimoDTO.getUsuarioId()));

            Livro livro = livroService.pegarPorIsbn(emprestimoDTO.getLivroIsbn())
                    .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado"));

            livroService.alterarDisponibilidadePorIsbn(emprestimoDTO.getLivroIsbn(), false);

            Emprestimo emprestimo = new Emprestimo(usuario, livro);
            emprestimoRepository.salvar(emprestimo);
        }
    }

    /**
     * Método para alterar um empréstimo
     * @param id ID do empréstimo
     * @param emprestimoDTO DTO com os dados do empréstimo
     * @throws UsuarioNaoEncontradoException se o usuário não for encontrado
     * @throws LivroNaoEncontradoException se o livro não for encontrado
     */
    public void excluirEmprestimo(Integer id , String isbn) {
        emprestimoRepository.excluir(id);
    }


    /**
     * Método para devolver um empréstimo.<br>
     * Altera a disponibilidade do livro para true.<br>
     * Calcula a multa por atraso - 1 real por dia de atraso
     * @param idTransacao ID do empréstimo
     * @return double - multa por atraso
     */
    public double devolverEmprestimo(int idTransacao) {
        int multaPorDiaAtraso = 1;
        long diasAtraso = ChronoUnit.DAYS.between(emprestimoRepository.buscarPorId(idTransacao).getDataDevolucao() , LocalDate.now());

        livroService.alterarDisponibilidadePorIsbn(emprestimoRepository.buscarPorId(idTransacao)
                        .getLivro()
                        .getISBN()
                , true);

        emprestimoRepository.excluir(emprestimoRepository.buscarPorId(idTransacao).getUsuario().getID());

        if(diasAtraso > 0){
            return multaPorDiaAtraso * diasAtraso;
        }
        return 0;
    }

    /**
     * Método para listar todos os empréstimos
     * @return {@link List}- lista de todos empréstimos
     */
    public List<Emprestimo> buscarEmprestimos() {
        return emprestimoRepository.listarTodos();
    }


}
