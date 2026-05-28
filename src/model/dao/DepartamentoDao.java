package model.dao;

import model.entities.Departamento;

import java.util.List;

public interface DepartamentoDao {

    void inserir(Departamento obj);
    void atualizar(Departamento obj);
    void excluir(Integer id);
    Departamento buscarId(Integer id);
    List<Departamento> buscarTodos();
}
