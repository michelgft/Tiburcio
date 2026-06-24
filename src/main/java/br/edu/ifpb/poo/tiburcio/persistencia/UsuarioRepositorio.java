package br.edu.ifpb.poo.tiburcio.persistencia;

import br.edu.ifpb.poo.tiburcio.modelo.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioRepositorio implements Repositorio<Usuario> {
    
    private Map<String, Usuario> banco = new HashMap<>();
    
    @Override
    public boolean salvar(Usuario usuario) {
        if (banco.containsKey(usuario.getId())) {
            return false;
        }
        banco.put(usuario.getId(), usuario);
        return true;
    }
    
    @Override
    public Usuario buscar(String id) {
        return banco.get(id);
    }
    
    @Override
    public List<Usuario> buscarTodos() {
        return new ArrayList<>(banco.values());
    }
    
    @Override
    public boolean atualizar(Usuario usuario) {
        if (!banco.containsKey(usuario.getId())) {
            return false;
        }
        banco.put(usuario.getId(), usuario);
        return true;
    }
    
    @Override
    public boolean excluir(String id) {
        return banco.remove(id) != null;
    }
    
    @Override
    public long contar() {
        return banco.size();
    }
}