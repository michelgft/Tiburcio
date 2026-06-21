package br.edu.ifpb.poo.tiburcio.persistencia;

import br.edu.ifpb.poo.tiburcio.modelo.Venda;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendaRepositorio implements Repositorio<Venda> {
    
    private Map<String, Venda> banco = new HashMap<>();
    
    @Override
    public boolean salvar(Venda venda) {
        if (banco.containsKey(venda.getId())) {
            return false;
        }
        banco.put(venda.getId(), venda);
        return true;
    }
    
    @Override
    public Venda buscar(String id) {
        return banco.get(id);
    }
    
    @Override
    public List<Venda> buscarTodos() {
        return new ArrayList<>(banco.values());
    }
    
    @Override
    public boolean atualizar(Venda venda) {
        if (!banco.containsKey(venda.getId())) {
            return false;
        }
        banco.put(venda.getId(), venda);
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
