package model.dao.impl;

import model.dao.VendedorDao;
import model.entities.Vendedor;

import java.util.List;

public class VendedorDaoJDBC implements VendedorDao {
    @Override
    public void inserir(Vendedor obj) {

    }

    @Override
    public void atualizar(Vendedor obj) {

    }

    @Override
    public void excluir(Integer id) {

    }

    @Override
    public Vendedor buscarId(Integer id) {
        return null;
    }

    @Override
    public List<Vendedor> buscarTodos() {
        return List.of();
    }
}
