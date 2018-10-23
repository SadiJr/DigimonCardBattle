package estrategiasJogo;
import dominioProblema.ImagemDeTabuleiro;
import dominioProblema.Lance;

public class Estrategia3 extends Estrategia {
 
	public Lance definirLance(ImagemDeTabuleiro estado) {
		Lance lance;
		switch (ordemLance){
			case 0: lance = this.lance1(estado);
				break;
			case 1: lance = this.lance2(estado);
				break;
			default: lance = this.lanceBasico(estado);		
		};
		return lance;
	}
	 
	public Lance lance1(ImagemDeTabuleiro estado) {
		Lance lance;
		ordemLance++;
		lance = new Lance();
		lance.assumir(1, 1);
		return lance;
	}
	 
	public Lance lance2(ImagemDeTabuleiro estado) {
		Lance lance;
		if (estado.informarPosicaoVazia(3, 3)) {
			caminho = 1;
			lance = this.lanceBasico(estado);
		} else {
			caminho = 2;
			ordemLance++;
			lance = new Lance();
			lance.assumir(3, 1);
		};
		return lance;
	}
	 
}
 
