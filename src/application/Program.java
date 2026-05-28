package application;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import model.dao.impl.VendedorDaoJDBC;
import model.entities.Vendedor;

public class Program {
    public static void main(String[] args) {
        VendedorDao vendedorDao = FabricaDao.criarVendedorDao();

        System.out.println("=== TESTE 1> VENDEDOR BUSCAR ID===");
        Vendedor vendedor = vendedorDao.buscarId(3);
        System.out.println(vendedor);
    }
}
