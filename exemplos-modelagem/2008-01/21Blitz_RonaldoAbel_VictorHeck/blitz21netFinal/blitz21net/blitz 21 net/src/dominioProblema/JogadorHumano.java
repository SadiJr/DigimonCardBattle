package dominioProblema;

public class JogadorHumano extends Jogador {
 	
 
 	//private Carta[] jogo;	
	public void iniciar(String umNome, int umSimbolo) {
		simbolo = umSimbolo;
		daVez = false;
		vencedor = false;
		nome = umNome;
		//jogo = new Carta[1];
	}
	
	public void reiniciar() {
		daVez = false;
		vencedor = false;
		super.setPonto(0);
	}
	 
	public Lance habilitar(ImagemDeTabuleiro estado) {
		
		daVez = true;
		
		return (new Lance());
	}
	 
}
 


