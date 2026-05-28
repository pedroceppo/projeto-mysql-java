package model.dao;

import model.entities.Departamento;
import model.entities.Vendedor;

import java.util.List;

public interface VendedorDao {
    void inserir(Vendedor obj);
    void atualizar(Vendedor obj);
    void excluir(Integer id);
    Vendedor buscarId(Integer id);
    List<Vendedor> buscarTodos();
    List<Vendedor>buscarPorDepartamento(Departamento departamento);
}
