package dominioProblema;
import estrategiasJogo.Estrategia;
import estrategiasJogo.Estrategia1;
import estrategiasJogo.Estrategia2;
import estrategiasJogo.Estrategia3;

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
		return lance;
	}
	 
	public void definirEstrategia(ImagemDeTabuleiro estado) {
		if (estado.informarVazio()) {
			aEstrategia = new Estrategia1();
		} else {
			if (estado.informarCentroVazio()) {
				aEstrategia = new Estrategia2();
			} else {
				aEstrategia = new Estrategia3();
			};			
		};
	}
	 
}
 
