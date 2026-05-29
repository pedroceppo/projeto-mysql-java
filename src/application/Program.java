package application;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import model.dao.impl.VendedorDaoJDBC;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        VendedorDao vendedorDao = FabricaDao.criarVendedorDao();

        System.out.println("=== TESTE 1> VENDEDOR BUSCAR ID===");
        Vendedor vendedor = vendedorDao.buscarId(3);
        System.out.println(vendedor);

        System.out.println("\n===TESTE 2> VENDEDOR BUSCAR POR DEPARTAMENTO===");
        List<Vendedor> list = vendedorDao.buscarPorDepartamento(new Departamento(2, null));
        for (Vendedor obj : list) {
            System.out.println(obj);
        }

        System.out.println("\n===TESTE 3> VENDEDOR BUSCAR TODOS===");
        list = vendedorDao.buscarTodos();
        for (Vendedor obj : list) {
            System.out.println(obj);
        }

        System.out.println("\n===TESTE 4: INSERIR VENDEDOR===");
        Vendedor novoVendedor = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 4000.00, new Departamento(2, null));
        vendedorDao.inserir(novoVendedor);
        System.out.println("Inserido com sucesso!/Novo id = " + novoVendedor.getId());

        System.out.println("\nTESTE 5: ATUALIZAR VENDEDOR===");
        Vendedor attVendedor = vendedorDao.buscarId(11);
        attVendedor.setNome("José");
        vendedorDao.atualizar(attVendedor);
        System.out.println("Atualizado com sucesso!/Vendedor = " + attVendedor);
    }
}
