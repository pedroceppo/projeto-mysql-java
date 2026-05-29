package application;

import model.dao.DepartamentoDao;
import model.dao.FabricaDao;
import model.entities.Departamento;


import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DepartamentoDao departamentoDao = FabricaDao.criarDepartamentoDao();

        System.out.println("===TESTE 1> BUSCAR ID DO DEPARTAMENTO");
        Departamento dep = departamentoDao.buscarId(1);
        System.out.println(dep);

        System.out.println("===TESTE 2> INSERIR DEPARTAMENTO");
        Departamento novoDep = new Departamento(null,"Musica");
        departamentoDao.inserir(novoDep);
        System.out.println("Inserido com sucesso!");

        System.out.println("===TESTE 3> ATUALIZAR DEPARTAMENTO");
        Departamento attDep = departamentoDao.buscarId(1);
        attDep.setNome("Esportes");
        departamentoDao.atualizar(attDep);
        System.out.println("Atualizado com sucesso!");

        System.out.println("===TESTE 4> EXCLUIR DEPARTAMENTO");
        System.out.println("Digite o id do departamento: ");
        int id = sc.nextInt();
        departamentoDao.excluir(id);
        System.out.println("Excluido com sucesso!");

        System.out.println("===TESTE 5> BUSCAR TODOS");
        List<Departamento> list = departamentoDao.buscarTodos();
        System.out.println("Todos os departamentos:");
        for (Departamento obj : list) {
            System.out.println(obj);
        }



    }


}
