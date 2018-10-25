package model;

import br.ufsc.inf.leobr.cliente.Jogada;

import java.util.List;

public class Jogador implements Jogada {

	protected int id;
	protected String nome;
	protected List<Carta> cartasMao;
	protected int pontuacao;

	public Jogador(int id, String nome, List<Carta> cartasMao) {
		super();
		this.id = id;
		this.nome = nome;
		this.cartasMao = cartasMao;
		this.pontuacao = 0;
	}
	
	public Jogador(int id, String nome) {
		this.id = id;
		this.nome = nome;
		this.pontuacao = 0;
	}

	public Jogador(String nome) {
		this.nome = nome;
		this.pontuacao = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Carta> getCartasMao() {
		return cartasMao;
	}

	public void setCartasMao(List<Carta> cartasMao) {
		this.cartasMao = cartasMao;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
}
