package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendedorDaoJDBC implements VendedorDao {

    private Connection conn;

    public VendedorDaoJDBC(Connection conn) {
        this.conn = conn;
    }


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
        PreparedStatement st = null;
        ResultSet rs = null;
       try{
           st = conn.prepareStatement(
                   "SELECT seller.*, department.Name as DepName " +
                           "FROM seller INNER JOIN department " +
                           "ON seller.DepartmentId = department.Id " +
                           "WHERE seller.Id = ?"
           );
          st.setInt(1,id);
          rs = st.executeQuery();
          if(rs.next()){
              Departamento dep = instanciandoDepartamento(rs);
              Vendedor obj = instanciandoVendedor(rs,dep);
              return obj;
          }
       }
       catch (SQLException e){
           throw new DbException(e.getMessage());
       }
       finally {
           DB.closeStatement(st);
           DB.closeResultSet(rs);
       }
       return null;
    }

    private Vendedor instanciandoVendedor(ResultSet rs, Departamento dep) throws SQLException {
        Vendedor obj = new Vendedor();
        obj.setId(rs.getInt("Id"));
        obj.setNome(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setSalarioBase(rs.getDouble("BaseSalary"));
        obj.setDataNascimento(rs.getDate("BirthDate"));
        obj.setDepartamento(dep);
        return obj;
    }

    private Departamento instanciandoDepartamento(ResultSet rs) throws SQLException {
        Departamento dep =  new Departamento();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setNome(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Vendedor> buscarTodos() {
        return List.of();
    }

    @Override
    public List<Vendedor> buscarPorDepartamento(Departamento departamento) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT seller.*, department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE DepartmentId = ? " +
                    "ORDER BY Name");
            st.setInt(1, departamento.getId());
            rs = st.executeQuery();
            List<Vendedor> vendedores = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while (rs.next()) {
                Departamento dep = map.get(rs.getInt("DepartmentId"));
                if (dep == null) {
                    dep = instanciandoDepartamento(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                Vendedor obj = instanciandoVendedor(rs, dep);
                vendedores.add(obj);
            }
            return vendedores;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

}
