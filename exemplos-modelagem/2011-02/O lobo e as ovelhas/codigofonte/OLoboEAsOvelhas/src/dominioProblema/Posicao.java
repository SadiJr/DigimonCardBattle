package dominioProblema;

public class Posicao {
	
	protected Jogador ocupante;
	
	public Posicao(){
		ocupante = null;
	}
	
	public Posicao(Jogador ocupante){
		this.ocupante = ocupante;
	}	

	public boolean ocupada() {
		return !(ocupante == null);
	}

	public Jogador informarOcupante() {
		return ocupante;
	}
	
	public void definirOcupante(Jogador ocupante){
		this.ocupante = ocupante;
	}

	public void esvaziar() {
		ocupante = null;
	}

}
