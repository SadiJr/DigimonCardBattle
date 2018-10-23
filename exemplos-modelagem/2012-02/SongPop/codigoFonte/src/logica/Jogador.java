package logica;

public class Jogador {
	protected String nome;
	protected int id;
	protected EstadoDoJogador estado;

	public Jogador(int idJogador, String nomeJogador) {
		id = idJogador;
		nome = nomeJogador;
		estado = EstadoDoJogador.JOGANDO;
	}

	public void setEstado(EstadoDoJogador estado) {
		this.estado = estado;
	}
	
	public EstadoDoJogador getEstado() {
		return estado;
	}
	
	public String getNome() {
		return nome;
	}

//	public void setId(int id) {
//		this.id = id;
//	}
	
	public int getId() {
		return id;
	}
}