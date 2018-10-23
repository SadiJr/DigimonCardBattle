package estrategiasJogo;
import dominioProblema.ImagemDeTabuleiro;
import dominioProblema.Lance;

public class Estrategia2 extends Estrategia {
	
	public Lance informarPrimeiroLance() {
		Lance lance;
		lance = new Lance();
		lance.assumir(2, 2);
		return lance;
	}
 
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
		lance.assumir(2, 2);
		return lance;
	}
	 
	public Lance lance2(ImagemDeTabuleiro estado) {
		Lance lance;
		// caminho 1
		if ((estado.ocupadasNaLinha(1)== 2) || (estado.ocupadasNaLinha(3)== 2) ||
			(estado.ocupadasNaColuna(1) == 2) || (estado.ocupadasNaColuna(3) == 2) )  {
			caminho = 1;
			lance = this.lanceBasico(estado);
		} else {
		// caminho 2
			if ( ( (estado.informarPosicaoOcupada(1, 2)) && (estado.informarPosicaoOcupada(3, 2)) )
				|| ( (estado.informarPosicaoOcupada(2, 1)) && (estado.informarPosicaoOcupada(2, 3)) ) ) {
				caminho = 2;
				ordemLance++;
				lance = new Lance();
				lance.assumir(1, 1);
			} else {
		// caminho 3
				if ( ( (estado.informarPosicaoOcupada(1, 1)) && (estado.informarPosicaoOcupada(3, 3)) )
					|| ( (estado.informarPosicaoOcupada(3, 1)) && (estado.informarPosicaoOcupada(1, 3)) ) ) {
					caminho = 3;
					ordemLance++;
					lance = new Lance();
					lance.assumir(2, 1);
				} else {
		// caminho 4
					if ( ( (estado.informarPosicaoOcupada(1, 2)) && (estado.informarPosicaoOcupada(2, 3)) )
						|| ( (estado.informarPosicaoOcupada(1, 2)) && (estado.informarPosicaoOcupada(3, 3)) ) 
						|| ( (estado.informarPosicaoOcupada(1, 1)) && (estado.informarPosicaoOcupada(2, 3)) ) 
						|| ( (estado.informarPosicaoOcupada(1, 1)) && (estado.informarPosicaoOcupada(3, 3)) ) ) {
						caminho = 4;
						ordemLance++;
						lance = new Lance();
						lance.assumir(1, 3);
					} else {
		// caminho 5
						if ( ( (estado.informarPosicaoOcupada(1, 2)) && (estado.informarPosicaoOcupada(2, 1)) )
							|| ( (estado.informarPosicaoOcupada(1, 2)) && (estado.informarPosicaoOcupada(3, 1)) ) 
							|| ( (estado.informarPosicaoOcupada(1, 3)) && (estado.informarPosicaoOcupada(2, 1)) ) 
							|| ( (estado.informarPosicaoOcupada(1, 3)) && (estado.informarPosicaoOcupada(3, 1)) ) ) {
							caminho = 5;
							ordemLance++;
							lance = new Lance();
							lance.assumir(1, 1);
						} else {
		// caminho 6
							if ( ( (estado.informarPosicaoOcupada(3, 2)) && (estado.informarPosicaoOcupada(1, 3)) )
								|| ( (estado.informarPosicaoOcupada(3, 2)) && (estado.informarPosicaoOcupada(2, 3)) ) 
								|| ( (estado.informarPosicaoOcupada(3, 1)) && (estado.informarPosicaoOcupada(1, 3)) ) 
								|| ( (estado.informarPosicaoOcupada(3, 1)) && (estado.informarPosicaoOcupada(2, 3)) ) ) {
								caminho = 6;
								ordemLance++;
								lance = new Lance();
								lance.assumir(3, 3);
							} else {
		// caminho 7
								caminho = 7;
								ordemLance++;
								lance = new Lance();
								lance.assumir(3, 1);
							};
						};
					};
				};
			};
		};
		return lance;
	}
	 
}
 
