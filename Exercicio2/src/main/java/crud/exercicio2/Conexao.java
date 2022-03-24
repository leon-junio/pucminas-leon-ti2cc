package crud.exercicio2;

import java.sql.*;

public class Conexao {

	private static Connection conexao;
	private static String driverName = "org.postgresql.Driver";
	private static String serverName = "localhost:8060";
	private static String mydatabase = "teste";
	private static int porta = 5432;
	private static String username = "ti2cc";
	private static String password = "ti@cc";

	public Conexao() {
		conexao = null;
	}

	private static boolean conectar() {
		boolean status = false;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}
		return status;
	}

	public static boolean close() {
		boolean info = false;
		try {
			conexao.close();
			info = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return info;
	}

	public static boolean getConexao() {
		boolean info = false;
		try {
			if (conectionState()) {
				info = conectar();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return info;
	}

	public static boolean conectionState() {
		return conexao != null;
	}

	public static Statement createSt() throws SQLException {
		Statement st = null;
		if (getConexao()) {
			st = conexao.createStatement();
		}
		return st;
	}
	

}
