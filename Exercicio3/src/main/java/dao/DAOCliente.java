package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class DAOCliente extends Conexao {
	public DAOCliente() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}

	public boolean insert(Cliente cliente) {
		boolean status = false;
		try {
			String sql = "INSERT INTO \"public\".\"Cliente\" (biografia, cpf, rg, nome) " + "VALUES ('"
					+ cliente.getBiografia() + "', '" + cliente.getCpf() + "', '" + cliente.getRg() + "', '"
					+ cliente.getNome() + "');";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public Cliente get(int id_cliente) {
		Cliente cliente = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM \"public\".\"Cliente\" WHERE id_cliente=" + id_cliente;
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				cliente = new Cliente(rs.getInt("id_cliente"), rs.getString("nome"), rs.getString("cpf"),
						rs.getString("rg"), rs.getString("biografia"));
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return cliente;
	}

	public List<Cliente> get() {
		return get("");
	}

	public List<Cliente> getOrderByID() {
		return get("id_cliente");
	}

	public List<Cliente> getOrderByNome() {
		return get("nome");
	}

	public List<Cliente> getOrderByCpf() {
		return get("cpf");
	}

	private List<Cliente> get(String orderBy) {
		List<Cliente> clientes = new ArrayList<Cliente>();

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM \"public\".\"Cliente\""
					+ ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Cliente p = new Cliente(rs.getInt("id_cliente"), rs.getString("nome"), rs.getString("cpf"),
						rs.getString("rg"), rs.getString("biografia"));
				clientes.add(p);
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return clientes;
	}

	public boolean update(Cliente cliente) {
		boolean status = false;
		try {
			String sql = "UPDATE \"public\".\"Cliente\" SET biografia = '" + cliente.getBiografia() + "' , cpf= '"
					+ cliente.getCpf() + "'," + "rg = '" + cliente.getRg() + "', nome = '" + cliente.getNome() + "' "
					+ "WHERE id_cliente = ? ;";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setInt(1, cliente.getId_cliente());
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean delete(int id_cliente) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM \"public\".\"Cliente\" WHERE id_cliente = " + id_cliente);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
}