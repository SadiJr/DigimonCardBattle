package dominioProblema;

public class Posicao {
 
	protected Jogador ocupante;
	protected Carta carta; 
		
		
	public boolean ocupada() {
		return (ocupante != null);
	}
	 
	 
	public void alocarPeao(Jogador umJogador,Carta pCarta) {
		ocupante = umJogador;
		carta=pCarta;
	}
	
	
	public void alocarCarta(Carta umaCarta){
		this.carta=umaCarta ;
	}
	 
	 
	public Jogador informarOcupante() {
		return ocupante;
	}
	
	
	public Carta informarCarta() {
		return carta;
	}
	
	
	public int informarNumero() {
		return carta.getNumero();
	}
	 
	 
	public void esvaziar() {
		ocupante = null;
		carta= null;// verificar depois imagem verde
	}
	 
	 
	public boolean mesmoOcupante(Posicao p1, Posicao p2) {
		return (  (ocupante == p1.informarOcupante())
			   && (ocupante == p2.informarOcupante()) );
	}
	 
}



