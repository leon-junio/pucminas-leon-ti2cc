package crud.exercicio2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		// criando conexao com o banco de dados
		Conexao.getConexao();
		int op = 0;
		Scanner scan = new Scanner(System.in);
		DAOProduto dao = new DAOProduto();
		try {
			System.out.println("CRUD DE CADASTRO DE PRODUTOS");
			do {
				System.out.println("\nEscolha uma opção:");
				System.out.println("1- Listar produtos");
				System.out.println("2- Inserir um novo produto");
				System.out.println("3- Excluir um produto");
				System.out.println("4- Atualizar um produto");
				System.out.println("0- Sair do sistema");
				op = scan.nextInt();
				scan.nextLine();
				switch (op) {
				case 1:
					System.out.println("Listagem dos dados: ");
					for (Produto prod : dao.getProdutos()) {
						System.out.println("Código: " + prod.getCodigo());
						System.out.println("Nome: " + prod.getNome());
						System.out.println("Descrição: " + prod.getDescricao());
						System.out.println("Preço: " + prod.getPreco());
						System.out.println("Categoria: " + prod.getCategoria());
					}
					break;
				case 2:
					try {
						System.out.println("Cadastro de novo produto: ");
						Produto prod = new Produto();
						System.out.println("Insira o nome do produto: ");
						prod.setNome(scan.nextLine());
						System.out.println("Insira a descrição do produto: ");
						prod.setDescricao(scan.nextLine());
						System.out.println("Insira o valor do produto: ");
						prod.setPreco(scan.nextFloat());
						scan.nextLine();
						System.out.println("Insira a categoria do produto: ");
						prod.setCategoria(scan.nextLine());
						if (dao.inserirProduto(prod)) {
							System.out.println("INSERIDO COM SUCESSO!");
						} else {
							System.out.println("PRODUTO NÃO INSERIDO");
						}
					} catch (RuntimeException run) {
						System.out.println("Erro de entrada de dados \n" + "Erro: " + run.getMessage());
					} catch (Exception e) {
						System.out.println("Erro interno inesperado: " + e.getMessage());
					}
					break;
				case 3:
					int cod = 0;
					System.out.println("EXCLUSÃO DE PRODUTO\n" + "Digite o codigo do produto que deseja excluir:");
					for (Produto prod : dao.getProdutos()) {
						System.out.println(prod.getCodigo() + " --> " + prod.getNome());
					}
					try {
						cod = scan.nextInt();
						if (dao.excluirProduto(cod)) {
							System.out.println("Produto excluido");
						} else {
							System.out.println("Produto não foi excluido");
						}
					} catch (RuntimeException run) {
						System.out.println("Erro de entrada de dados \n" + "Erro: " + run.getMessage());
					} catch (Exception e) {
						System.out.println("Erro interno inesperado: " + e.getMessage());
					}
					break;
				case 4:
					int esc = 0;
					System.out.println("ATUALIZAÇÃO DE PRODUTO\n" + "Digite o codigo do produto que deseja atualizar:");
					for (Produto prod : dao.getProdutos()) {
						System.out.println(prod.getCodigo() + " --> " + prod.getNome());
					}
					try {
						esc = scan.nextInt();
						scan.nextLine();
						Produto prod = new Produto();
						System.out.println("Insira o nome do produto: ");
						prod.setNome(scan.nextLine());
						System.out.println("Insira a descrição do produto: ");
						prod.setDescricao(scan.nextLine());
						System.out.println("Insira o valor do produto: ");
						prod.setPreco(scan.nextFloat());
						scan.nextLine();
						System.out.println("Insira a categoria do produto: ");
						prod.setCategoria(scan.nextLine());
						prod.setCodigo(esc);
						if (dao.atualizarProduto(prod)) {
							System.out.println("PRODUTO ATUALIZADO COM SUCESSO");
						} else {
							System.out.println("PRODUTO NÃO ATUALIZADO");
						}
					} catch (RuntimeException run) {
						System.out.println("Erro de entrada de dados \n" + "Erro: " + run.getMessage());
					} catch (Exception e) {
						System.out.println("Erro interno inesperado: " + e.getMessage());
					}
					break;
				case 0:
					Conexao.close();
					scan.close();
					System.out.println("ENCERRANDO SISTEMA");
					break;
				default:
					System.out.println("OPÇÃO INVÁLIDA, ESCOLHA NOVAMENTE!");
					break;
				}
			} while (op != 0);
		} catch (InputMismatchException ie) {
			System.err.println("Erro na opção selecionada e enviada: " + ie.getMessage());
		} catch (NumberFormatException ne) {
			System.err.println("Erro na leitura do numero: " + ne.getMessage());
		} catch (RuntimeException r) {
			System.err.println("Erro no acesso aos dados: " + r.getMessage());
		} catch (Exception e) {
			System.err.println("Erro inesperado interno: " + e.getMessage());
		}
		System.out.println("ENCERRADO");
	}

}
