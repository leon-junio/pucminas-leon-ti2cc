package model;

public class Cliente {
	public Cliente() {
		this.id_cliente = -1;
		this.nome = "";
		this.cpf = "";
		this.rg = "";
		this.biografia = "";
	}

	public Cliente(int id_cliente, String nome, String cpf, String rg, String biografia) {
		this.id_cliente = id_cliente;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.biografia = biografia;
	}



	private int id_cliente;
	private String nome, cpf, rg, biografia;


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws RuntimeException {
		if (nome.length() == 0) {
			throw new RuntimeException("O campo informado esta vazio");
		} else {
			this.nome = nome;
		}
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) throws RuntimeException {
		if (biografia.length() == 0) {
			throw new RuntimeException("O campo informado esta vazio");
		} else {
			this.biografia = biografia;
		}
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Override
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", biografia="
				+ biografia + "]";
	}


}
