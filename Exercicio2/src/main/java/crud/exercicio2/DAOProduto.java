package crud.exercicio2;

import java.util.ArrayList;
import java.sql.*;

public class DAOProduto {

	public boolean inserirProduto(Produto produto) {
		boolean status = false;
		try {
			Statement st = Conexao.createSt();
			st.executeUpdate("INSERT INTO Produto (codigo, nome, descricao, preco, categoria) " + "VALUES ("
					+ produto.getCodigo() + ", '" + produto.getNome() + "', '" + produto.getDescricao() + "', "
					+ produto.getPreco() + " , '" + produto.getCategoria() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarProduto(Produto produto) {
		boolean status = false;
		try {
			Statement st = Conexao.createSt();
			String sql = "UPDATE Produto SET nome = '" + produto.getNome() + "', descricao = '" + produto.getDescricao()
					+ "', preco = " + produto.getPreco() + " , categoria = '" + produto.getCategoria() + "' "
					+ " WHERE codigo = " + produto.getCodigo();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirProduto(int codigo) {
		boolean status = false;
		try {
			Statement st = Conexao.createSt();
			st.executeUpdate("DELETE FROM usuario WHERE codigo = " + codigo);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public ArrayList<Produto> getProdutos() {
		ArrayList<Produto> lista = null;
		try {
			Statement st = Conexao.createSt();
			ResultSet rs = st.executeQuery("SELECT * FROM Produto");
			if (rs.next()) {
				rs.last();
				lista = new ArrayList<>();
				while (rs.next()) {
					Produto prod = new Produto(rs.getInt("codigo"),rs.getString("nome"),rs.getString("descricao"),
							rs.getFloat("preco"),rs.getString("categoria"));
					lista.add(prod);
				}
			}
			rs.close();
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return lista;
	}

}
