package br.edu.ifpb.poo.tiburcio.persistencia;

import br.edu.ifpb.poo.tiburcio.modelo.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemRepositorio implements Repositorio<Item> {
    
    private Map<String, Item> banco = new HashMap<>();
    
    @Override
    public boolean salvar(Item item) {
        if (banco.containsKey(item.getId())) {
            return false;
        }
        banco.put(item.getId(), item);
        return true;
    }
    
    @Override
    public Item buscar(String id) {
        return banco.get(id);
    }
    
    @Override
    public List<Item> buscarTodos() {
        return new ArrayList<>(banco.values());
    }
    
    @Override
    public boolean atualizar(Item item) {
        if (!banco.containsKey(item.getId())) {
            return false;
        }
        banco.put(item.getId(), item);
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
