package br.edu.ifpb.poo.tiburcio.persistencia;

import java.util.List;

public interface Repositorio<T> {
    boolean salvar(T obj);
    T buscar(String id);
    List<T> buscarTodos();
    boolean atualizar(T obj);
    boolean excluir(String id);
    long contar();
}