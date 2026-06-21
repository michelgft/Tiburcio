package br.edu.ifpb.poo.tiburcio.persistencia;

import br.edu.ifpb.poo.tiburcio.modelo.Emprestimo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmprestimoRepositorio implements Repositorio<Emprestimo> {
    
    private Map<String, Emprestimo> banco = new HashMap<>();
    
    @Override
    public boolean salvar(Emprestimo emprestimo) {
        if (banco.containsKey(emprestimo.getId())) {
            return false;
        }
        banco.put(emprestimo.getId(), emprestimo);
        return true;
    }
    
    @Override
    public Emprestimo buscar(String id) {
        return banco.get(id);
    }
    
    public List<Emprestimo> buscarPorUsuario(String idUsuario) {
        List<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo e : banco.values()) {
            if (e.getUsuario().getId().equals(idUsuario)) {
                resultado.add(e);
            }
        }
        return resultado;
    }
    
    @Override
    public List<Emprestimo> buscarTodos() {
        return new ArrayList<>(banco.values());
    }
    
    @Override
    public boolean atualizar(Emprestimo emprestimo) {
        if (!banco.containsKey(emprestimo.getId())) {
            return false;
        }
        banco.put(emprestimo.getId(), emprestimo);
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