package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.sql.*;
import java.util.List;

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
          st = conn.prepareStatement("SELECT seller.*,department.Name as DepName" +
                  "FROM seller INNER JOIN department" +
                  "ON seller.DepartmentId = department.Id" +
                  "WHERE seller.Id = ?");
          st.setInt(1,id);
          rs = st.executeQuery();
          if(rs.next()){
              Departamento dep = new Departamento();
              dep.setId(rs.getInt("DepartmentId"));
              dep.setNome(rs.getString("DepName"));
              Vendedor obj = new Vendedor();
              obj.setId(rs.getInt("Id"));
              obj.setNome(rs.getString("Name"));
              obj.setEmail(rs.getString("Email"));
              obj.setSalarioBase(rs.getDouble("BaseSalary"));
              obj.setDataNascimento(rs.getDate("BirthDate"));
              obj.setDepartamento(dep);
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

    @Override
    public List<Vendedor> buscarTodos() {
        return List.of();
    }
}
