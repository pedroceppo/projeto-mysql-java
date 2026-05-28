package application;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import model.dao.impl.VendedorDaoJDBC;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        VendedorDao vendedorDao = FabricaDao.criarVendedorDao();

        System.out.println("=== TESTE 1> VENDEDOR BUSCAR ID===");
        Vendedor vendedor = vendedorDao.buscarId(3);
        System.out.println(vendedor);

        System.out.println("\n===TESTE 2: VENDEDOR BUSCAR POR DEPARTAMENTO===");
        List<Vendedor> list = vendedorDao.buscarPorDepartamento(new Departamento(2,null));
        for (Vendedor obj : list) {
            System.out.println(obj);
        }
    }
}
