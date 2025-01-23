package com.biblioteca.GerenciamentoBiblioteca.repository;


import com.biblioteca.GerenciamentoBiblioteca.model.Usuario;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**Classe que implementa a interface UsuarioRepository,
 * onde serão armazenados os métodos de salvar, excluir, buscar por ID e listar todos os usuários.
 * @autor Ignacio Rossini
 */
@Service
public class UsuarioRepositoryImplementacao implements UsuarioRepository {
    private Map<Integer, Usuario> usuarios = new HashMap<Integer, Usuario>();

    /**Método que salva um usuário.
     * @param usuario Usuario - usuário a ser salvo.
     */
    @Override
    public void salvar(Usuario usuario){
        usuarios.put(usuario.getID(), usuario);
    }

    /**Método que busca um usuário por ID.
     * @param id Integer - ID do usuário a ser buscado.
     * @return um {@link Optional} contendo o usuário encontrado, ou um {@link Optional#empty()} se não for encontrado.
     */
    @Override
    public Optional<Usuario> buscarPorID(int id){
        return Optional.ofNullable(usuarios.get(id));
    }

    /**Método que lista todos os usuários.
     * @return uma {@link List} contendo todos os usuários disponíveis no repositório.
     */
    @Override
    public List<Usuario> listarTodos(){
        return usuarios.values().stream().collect(Collectors.toList());
    }

    /**Método que exclui um usuário.
     * @param id Integer - ID do usuário a ser excluído.
     */
    @Override
    public void excluir(int id){
        usuarios.remove(id);
    }

}
