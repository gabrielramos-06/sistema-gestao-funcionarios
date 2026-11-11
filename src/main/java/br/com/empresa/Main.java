package br.com.empresa;

import br.com.empresa.dao.FuncionarioDAO;
import br.com.empresa.model.Caixa;
import br.com.empresa.model.Funcionario;
import br.com.empresa.model.Gerente;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FuncionarioDAO dao = new FuncionarioDAO();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=======================================================");
            System.out.println("       SISTEMA DE GESTÃO DE FUNCIONÁRIOS (POO)         ");
            System.out.println("=======================================================");
            System.out.println("1 - Cadastrar Gerente (Bônus de 20%)");
            System.out.println("2 - Cadastrar Caixa (Bônus de 10%)");
            System.out.println("3 - Listar Funcionários e Calcular Folha (Polimorfismo)");
            System.out.println("4 - Excluir Funcionário por ID");
            System.out.println("0 - Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n[ERRO] Digite um número válido!");
                continue;
            }

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do Gerente: ");
                    String nome = scanner.nextLine();
                    System.out.print("Salário Base (R$): ");
                    double salario = Double.parseDouble(scanner.nextLine().replace(",", "."));
                    System.out.print("Departamento (ex: Vendas, TI, Financeiro): ");
                    String depto = scanner.nextLine();

                    Funcionario gerente = new Gerente(nome, salario, depto);
                    dao.cadastrar(gerente);
                }
                case 2 -> {
                    System.out.print("Nome do Caixa: ");
                    String nome = scanner.nextLine();
                    System.out.print("Salário Base (R$): ");
                    double salario = Double.parseDouble(scanner.nextLine().replace(",", "."));
                    System.out.print("Turno (ex: Manhã, Tarde, Noite): ");
                    String turno = scanner.nextLine();

                    Funcionario caixa = new Caixa(nome, salario, turno);
                    dao.cadastrar(caixa);
                }
                case 3 -> {
                    List<Funcionario> funcionarios = dao.listarTodos();
                    System.out.println("\n------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("                                        LISTA DE FUNCIONÁRIOS E FOLHA DE PAGAMENTO                                      ");
                    System.out.println("------------------------------------------------------------------------------------------------------------------------");
                    if (funcionarios.isEmpty()) {
                        System.out.println("Nenhum funcionário cadastrado no banco de dados.");
                    } else {
                        double totalFolha = 0;
                        for (Funcionario f : funcionarios) {
                            System.out.println(f);
                            totalFolha += f.calcularSalarioFinal();
                        }
                        System.out.println("------------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("TOTAL DA FOLHA DE PAGAMENTO (COM BÔNUS): R$ %.2f%n", totalFolha);
                    }
                }
                case 4 -> {
                    System.out.print("Digite o ID do funcionário que deseja excluir: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    if (dao.excluir(id)) {
                        System.out.println("\n[SUCESSO] Funcionário removido do banco de dados!");
                    } else {
                        System.out.println("\n[AVISO] Nenhum funcionário encontrado com o ID informado.");
                    }
                }
                case 0 -> System.out.println("\nEncerrando o sistema... Até logo!");
                default -> System.out.println("\n[ERRO] Opção inválida!");
            }
        }
        scanner.close();
    }
}
a
