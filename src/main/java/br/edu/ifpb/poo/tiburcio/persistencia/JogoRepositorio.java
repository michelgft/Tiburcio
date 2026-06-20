package br.edu.ifpb.poo.tiburcio.persistencia;

import br.edu.ifpb.poo.tiburcio.modelo.JogoTabuleiro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JogoRepositorio implements Repositorio<JogoTabuleiro> {
    
    private Map<String, JogoTabuleiro> banco = new HashMap<>();
    
    @Override
    public boolean salvar(JogoTabuleiro jogo) {
        if (banco.containsKey(jogo.getId())) {
            return false;
        }
        banco.put(jogo.getId(), jogo);
        return true;
    }
    
    @Override
    public JogoTabuleiro buscar(String id) {
        return banco.get(id);
    }
    
    @Override
    public List<JogoTabuleiro> buscarTodos() {
        return new ArrayList<>(banco.values());
    }
    
    @Override
    public boolean atualizar(JogoTabuleiro jogo) {
        if (!banco.containsKey(jogo.getId())) {
            return false;
        }
        banco.put(jogo.getId(), jogo);
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