package com.biblioteca.repository;


import com.biblioteca.model.Usuario;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsuarioRepositoryImplementacao implements UsuarioRepository {
    private Map<Integer, Usuario> usuarios = new HashMap<Integer, Usuario>();

    @Override
    public void salvar(Usuario usuario){
        usuarios.put(usuario.getID(), usuario);
    }


    public Optional<Usuario> buscarPorID(int id){
        return Optional.ofNullable(usuarios.get(id));
    }

    public List<Usuario> listarTodos(){
        return usuarios.values().stream().collect(Collectors.toList());
    }

    public void excluir(int id){
        usuarios.remove(id);
    }

}
