package com.biblioteca.repository;


import com.biblioteca.model.Usuario;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UsuarioRepositoryImplementacao implements UsuarioRepository {
    private Map<Integer, Usuario> usuarios = new HashMap<Integer, Usuario>();

    @Override
    public void salvar(Usuario usuario){
        usuarios.put(usuario.getID(), usuario);
    }

    
    public Optional<Usuario> buscarPorId(int id){
        return Optional.ofNullable(usuarios.get(id));
    }
}
