package estrategiasJogo;
import dominioProblema.ImagemDeTabuleiro;
import dominioProblema.Lance;

public class Estrategia1 extends Estrategia {
 
	public Lance definirLance(ImagemDeTabuleiro estado) {
		Lance lance;
		switch (ordemLance){
			case 0: lance = this.lance1(estado);
				break;
			case 1: lance = this.lance2(estado);
			break;
			case 2: lance = this.lance3(estado);
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
		if (estado.informarPosicaoOcupada(2, 2)) {
			caminho = 3;
			ordemLance++;
			lance = new Lance();
			lance.assumir(3, 3);
		} else {
			if ((estado.informarPosicaoOcupada(2, 1)) || (estado.informarPosicaoOcupada(2, 3)) 
			   || (estado.informarPosicaoOcupada(3, 1)) ) {
				caminho = 2;
				ordemLance++;
				lance = new Lance();
				lance.assumir(1, 3);
			} else {
				caminho = 1;
				ordemLance++;
				lance = new Lance();
				lance.assumir(3, 1);
			};
		};
		return lance;
	}
	 
	public Lance lance3(ImagemDeTabuleiro estado) {
		Lance lance = null;
		switch (caminho) {
		case 1: lance = this.lance3Caminho1(estado);
		break;
		case 2: lance = this.lance3Caminho2(estado);
		break;
		case 3: lance = this.lance3Caminho3(estado);	
		};
		return lance;
	}
	 
	public Lance lance3Caminho1(ImagemDeTabuleiro estado) {
		Lance lance;
		if (estado.informarPosicaoVazia(2, 1)) {
			lance = this.lanceBasico(estado);
		} else {
			if ((estado.informarPosicaoVazia(3, 2)) && (estado.informarPosicaoVazia(3, 3)) ) {
				ordemLance++;
				lance = new Lance();
				lance.assumir(3, 3);
			} else {
				ordemLance++;
				lance = new Lance();
				lance.assumir(1, 3);
			};
		};
		return lance;
	}
	 
	public Lance lance3Caminho2(ImagemDeTabuleiro estado) {
		Lance lance;
		if (estado.informarPosicaoVazia(1, 2)) {
			lance = this.lanceBasico(estado);
		} else {
			if ((estado.informarPosicaoVazia(2, 3)) && (estado.informarPosicaoVazia(3, 3)) ) {
				ordemLance++;
				lance = new Lance();
				lance.assumir(3, 3);
			} else {
				ordemLance++;
				lance = new Lance();
				lance.assumir(3, 1);
			};
		};
		return lance;
	}
	 
	public Lance lance3Caminho3(ImagemDeTabuleiro estado) {
		Lance lance;
		if (estado.informarPosicaoOcupada(3, 1)) {
			ordemLance++;
			lance = new Lance();
			lance.assumir(1, 3);
		} else {
			if (estado.informarPosicaoOcupada(1, 3)) {
				ordemLance++;
				lance = new Lance();
				lance.assumir(3, 1);
			} else {
				lance = this.lanceBasico(estado);
			};
		};
		return lance;
	}
	 
}
 
