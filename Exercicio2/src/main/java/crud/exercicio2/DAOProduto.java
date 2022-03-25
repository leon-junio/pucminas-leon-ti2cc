package crud.exercicio2;

import java.util.ArrayList;
import java.sql.*;

public class DAOProduto {

	public boolean inserirProduto(Produto produto) {
		boolean status = false;
		try {
			Statement st = Conexao.createSt();
			int code = nextCodigo();
			int result = st.executeUpdate("INSERT INTO \"public\".\"Produto\" (codigo, nome, descricao, preco, categoria) " + "VALUES (" + code
					+ ", '" + produto.getNome() + "', '" + produto.getDescricao() + "', " + produto.getPreco() + " , '"
					+ produto.getCategoria() + "');");
			st.close();
			if(result>0) {
				status = true;
			}else {
				status = false;
			}
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarProduto(Produto produto) {
		boolean status = false;
		try {
			Statement st = Conexao.createSt();
			String sql = "UPDATE \"public\".\"Produto\" SET nome = '" + produto.getNome() + "', descricao = '" + produto.getDescricao()
					+ "', preco = " + produto.getPreco() + " , categoria = '" + produto.getCategoria() + "' "
					+ " WHERE codigo = " + produto.getCodigo();
			int result = st.executeUpdate(sql);
			st.close();
			if(result>0) {
				status = true;
			}else {
				status = false;
			}
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirProduto(int codigo) {
		boolean status = false;
		try {
			Statement st = Conexao.createSt();
			int result = st.executeUpdate("DELETE FROM \"public\".\"Produto\" WHERE codigo = " + codigo);
			st.close();
			if(result>0) {
				status = true;
			}else {
				status = false;
			}
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	private int nextCodigo() {
		int cod = 0;
		ArrayList<Produto> array = getProdutos();
		if (!array.isEmpty()) {
			cod = array.get(array.size() - 1).getCodigo() + 1;
		} else {
			cod++;
		}
		return cod;
	}

	public ArrayList<Produto> getProdutos() {
		ArrayList<Produto> lista = null;
		try {
			Statement st = Conexao.createStIs();
			ResultSet rs = st.executeQuery("SELECT * FROM \"public\".\"Produto\";");
			lista = new ArrayList<>();
			while (rs.next()) {
				Produto prod = new Produto(rs.getInt("codigo"), rs.getString("nome"), rs.getString("descricao"),
						rs.getFloat("preco"), rs.getString("categoria"));
				lista.add(prod);
			}
			rs.close();
			st.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return lista;
	}

}
