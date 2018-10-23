package dominioProblema;
import estrategiasJogo.Estrategia;
import estrategiasJogo.Estrategia1;



public class JogadorAutomatico extends Jogador {
 
	protected Estrategia aEstrategia;
	 
	public void iniciar(String umNome, int umSimbolo) {
		simbolo = umSimbolo;
		daVez = false;
		vencedor = false;
		nome = umNome;
		aEstrategia = null;
	}
	 
	public void reiniciar() {
		daVez = false;
		vencedor = false;
		aEstrategia = null;
	}
	 
	public Lance habilitar(ImagemDeTabuleiro estado) {
		Lance lance;
		daVez = true;
		if (aEstrategia == null){
			this.definirEstrategia(estado);
		};
		lance = aEstrategia.definirLance(estado);
		
		//System.out.println("lance linha"+lance.informarLinha()+"lance coluna"+lance.informarColuna());
		return lance;
	}
	 
	public void definirEstrategia(ImagemDeTabuleiro estado) {
		aEstrategia = new Estrategia1();
		
		
	}
	 
}
 
