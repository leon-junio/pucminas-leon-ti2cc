package crud.exercicio2;

public class Produto {
	public Produto() {
	}

	public Produto(int codigo, String nome, String descricao, float preco, String categoria) {
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}

	private int codigo;
	private String nome, descricao, categoria;
	private float preco;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) throws RuntimeException {
		if (descricao.length() == 0) {
			throw new RuntimeException("O campo informado esta vazio");
		} else {
			this.descricao = descricao;
		}
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) throws RuntimeException {
		if (categoria.length() == 0) {
			throw new RuntimeException("O campo informado esta vazio");
		} else {
			this.categoria = categoria;
		}
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) throws RuntimeException {
		if (preco > 0) {
			this.preco = preco;
		} else {
			throw new RuntimeException("Valor menor que 0");
		}
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao + ", categoria=" + categoria
				+ ", preco=" + preco + "]";
	}

}
