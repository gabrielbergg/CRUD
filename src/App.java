import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import connection.Conect;
import connection.DataException;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;


public class App {
    public static void main(String[] args) throws Exception {
        

        Scanner leia = new Scanner(System.in, "CP850");
        int option = 1, readOption, depId, readId, updateId, updateOption, cont = 0, deleteId;
        String name, email, birthDate;
        Double baseSalary;

        System.out.println("Projeto CRUD");

        Connection conn = null;
        Seller seller = new Seller();
        Department department = new Department();
        SellerDao sellerDao = DaoFactory.createSellerDao();
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        

        try {
            System.out.print("Escolha uma das opções: ");
            

            while (option != 5) {
                System.out.print("1 - Create  2 - Read  3 - Update  4 - Delete  5 - Close the program: ");
                option = leia.nextInt();

                if (option == 1) {
                    leia.nextLine();
                    System.out.println("Insira um vendedor no sistema: ");
                    System.out.print("Nome: ");
                    name = leia.nextLine();
                    System.out.print("Email: ");
                    email = leia.nextLine();
                    System.out.print("Data de nascimento (dd/mm/aaaa): ");
                    birthDate = leia.nextLine();
                    System.out.print("Salário: ");
                    baseSalary = leia.nextDouble();
                    System.out.print("Departamento Id (De 1 até 4): ");
                    depId = leia.nextInt();

                    department = new Department(depId, null);

                    seller = new Seller(null, name, email, new java.sql.Date(dt.parse(birthDate).getTime()), baseSalary, department);

                    sellerDao.insert(seller);

                    System.out.println("Dados inseridos com sucesso!");
                    
                }
                else if (option == 2) {
                    System.out.print("\nEscolha uma opção: ");
                    System.out.print("1 - Mostrar todos os vendedores da tabela Seller  2 - Mostrar um vendendor específico de acordo com o Id: ");
                    readOption = leia.nextInt();

                    List<Seller> list = new ArrayList<>();

                    if (readOption == 1) {
                        list = sellerDao.findAll();


                        for (Seller sl : list) {
                            System.out.println(sl);
                        }
                    }
                    else if (readOption == 2) {
                        System.out.println("Informe o Id: ");
                        readId = leia.nextInt();

                        System.out.println(sellerDao.find(readId));
                    }
                }
                else if (option == 3) {
                    
                    System.out.print("Informe o Id do vendedor que você deseja fazer o update: ");
                    updateId = leia.nextInt();

                    System.out.print("O que você deseja alterar? 1 - Nome  2 - Email  3 - Salário  4 - Id Departamento: ");
                    updateOption = leia.nextInt();
                    leia.nextLine();

                    seller = sellerDao.find(updateId);
                    if (updateOption == 1) {
                        System.out.print("Nome: ");
                        name = leia.nextLine();
                        seller.setName(name);
                    }
                    else if (updateOption == 2) {
                        System.out.print("Email: ");
                        email = leia.nextLine();
                        seller.setEmail(email);
                    }
                    else if (updateOption == 3) {
                        System.out.print("Salário: ");
                        baseSalary = leia.nextDouble();
                        seller.setBaseSalary(baseSalary);
                    }
                    else if (updateOption == 4) {
                        System.out.print("Id Departamento: ");
                        depId = leia.nextInt();
                        seller.setDepartment(new Department(depId, null));
                    }
                    else {
                        System.out.println("Opção inválida!");
                        cont++;
                    }

                    sellerDao.update(seller);

                    if (cont == 0) {
                        System.out.println("Update realizado.");
                    }
                    

                }
                else if (option == 4) {
                    System.out.println("Selecione o Id do vendedor que você deseja deletar: ");
                    deleteId = leia.nextInt();

                    sellerDao.delete(deleteId);
                    System.out.println("Id deletado com sucesso.");
                }
                else if (option < 1 || option > 5) {
                    System.out.println("Opção inválida!");
                }
                System.out.println();
            }
            System.out.println("Fim do programa!");
        } 
        catch (Exception e) {
            throw new DataException("Aconteceu um erro: " +e.getMessage());
        }
        finally {
            leia.close();
            Conect.closeConnection();
        }
    
    
    }
}
