package dominioProblema;

public class Posicao {
 
	protected Jogador ocupante;
	private String acaoRepresentada;	
	private boolean fileiraTravada;
	 
	public boolean ocupada() {
		return (ocupante != null);
	}
	 
	public void alocarPeao(Jogador umJogador) {
		ocupante = umJogador;
	}
	 
	public Jogador informarOcupante() {
		return ocupante;
	}
	 
	public void esvaziar() {
		ocupante = null;
	}
	 
	public boolean mesmoOcupante(Posicao p1) {
		return (  (ocupante == p1.informarOcupante()) );
	}

	public Jogador getOcupante() {
		return ocupante;
	}

	public void setOcupante(Jogador ocupante) {
		this.ocupante = ocupante;
	}

	public String getAcaoRepresentada() {
		return acaoRepresentada;
	}

	public void setAcaoRepresentada(String acaoRepresentada) {
		this.acaoRepresentada = acaoRepresentada;
	}

	public boolean isFileiraTravada() {
		return fileiraTravada;
	}

	public void setFileiraTravada(boolean fileiraTravada) {
		this.fileiraTravada = fileiraTravada;
	}	 
}