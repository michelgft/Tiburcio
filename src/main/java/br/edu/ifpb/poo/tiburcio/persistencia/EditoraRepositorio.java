package br.edu.ifpb.poo.tiburcio.persistencia;

import br.edu.ifpb.poo.tiburcio.modelo.Editora;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditoraRepositorio implements Repositorio<Editora> {
    
    private Map<String, Editora> banco = new HashMap<>();
    
    @Override
    public boolean salvar(Editora editora) {
        if (banco.containsKey(editora.getId())) {
            return false;
        }
        banco.put(editora.getId(), editora);
        return true;
    }
    
    @Override
    public Editora buscar(String id) {
        return banco.get(id);
    }
    
    public Editora buscarPorNome(String nome) {
        for (Editora e : banco.values()) {
            if (e.getNome().equalsIgnoreCase(nome)) {
                return e;
            }
        }
        return null;
    }
    
    @Override
    public List<Editora> buscarTodos() {
        return new ArrayList<>(banco.values());
    }
    
    @Override
    public boolean atualizar(Editora editora) {
        if (!banco.containsKey(editora.getId())) {
            return false;
        }
        banco.put(editora.getId(), editora);
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