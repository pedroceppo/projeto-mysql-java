package application;

import model.dao.FabricaDao;
import model.dao.VendedorDao;

public class Program {
    public static void main(String[] args) {
        VendedorDao vendedorDao = FabricaDao.criarVendedorDao();
    }
}
