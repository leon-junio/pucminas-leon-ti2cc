package crud.exercicio2;

import java.sql.*;

public class Conexao {

	private Connection conexao;

	public Conexao() {
		conexao = null;
	}

	public boolean conectar() {
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost:8060";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;
		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conex�o efetuada com o postgres!");
		} catch (ClassNotFoundException e) {
			System.err.println("Conex�o N�O efetuada com o postgres -- Driver n�o encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conex�o N�O efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}

	public boolean close() {
		boolean status = false;
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	
	

}