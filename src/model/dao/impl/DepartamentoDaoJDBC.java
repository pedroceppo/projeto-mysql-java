package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDaoJDBC implements DepartamentoDao {

    private Connection conn;
    public DepartamentoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Departamento obj) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("INSERT INTO Department (Name) VALUES (?)",PreparedStatement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            int rowsAffected =  st.executeUpdate();
            if(rowsAffected > 0){
                rs = st.getGeneratedKeys();
                if(rs.next()){
                    obj.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizar(Departamento obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("UPDATE Department SET Name=? WHERE Id=?",PreparedStatement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            st.setInt(2, obj.getId());
            int rowsAffected = st.executeUpdate();
            if(rowsAffected == 0){
                throw new DbException("Erro ao atualizar departamento");
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public void excluir(Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("DELETE FROM Department WHERE Id=?");
            st.setInt(1,id);
            int rowsAffected = st.executeUpdate();
            if(rowsAffected == 0){
                throw new DbException("Erro ao excluir departamento");
            }

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public Departamento buscarId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("SELECT * FROM department WHERE Id=?");
            st.setInt(1,id);
            rs = st.executeQuery();
            if(rs.next()){
                Departamento dep = instanciarDepartamentoDao(rs);
                return dep;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return null;
    }

    @Override
    public List<Departamento> buscarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Departamento> list = new ArrayList<>();
        try{
            st = conn.prepareStatement("SELECT *FROM department ORDER BY Name");
            rs = st.executeQuery();
            while(rs.next()){
                Departamento dep = new Departamento();
                dep.setId(rs.getInt("Id"));
                dep.setNome(rs.getString("Name"));
                list.add(dep);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return list;
    }

    public Departamento instanciarDepartamentoDao(ResultSet rs) throws SQLException {
        Departamento dep = new Departamento();
        dep.setId(rs.getInt("Id"));
        dep.setNome(rs.getString("Name"));
        return dep;
    }
}
