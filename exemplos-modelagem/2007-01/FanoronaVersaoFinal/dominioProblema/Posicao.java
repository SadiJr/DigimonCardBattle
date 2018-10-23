package dominioProblema;


public class Posicao{
	
	// protected Jogador ocupante;
	protected Peca ocupante;
	 
	public boolean ocupada() {
		return (ocupante != null);
	}	
	 
	public void setOcupante(Peca peca) {
		ocupante = peca;
	}
	 
	public Peca getOcupante() {
		return ocupante;
	}
	 
	public void esvaziarPosicao() {
		ocupante = null;
	}
}


