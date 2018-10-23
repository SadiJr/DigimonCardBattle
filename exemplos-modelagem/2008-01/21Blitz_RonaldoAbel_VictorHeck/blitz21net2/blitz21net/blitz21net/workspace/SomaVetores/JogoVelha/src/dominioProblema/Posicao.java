package dominioProblema;

public class Posicao {
 
	protected Jogador ocupante;
	 
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
	 
	public boolean mesmoOcupante(Posicao p1, Posicao p2) {
		return (  (ocupante == p1.informarOcupante())
			   && (ocupante == p2.informarOcupante()) );
	}
	 
}
 
