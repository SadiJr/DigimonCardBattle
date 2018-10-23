package estrategiasJogo;
import dominioProblema.ImagemDeTabuleiro;
import dominioProblema.Lance;

public abstract class Estrategia {
 
	protected int ordemLance = 0;
	 
	protected int caminho = 0;
	 
	public abstract Lance definirLance(ImagemDeTabuleiro estado);
	public abstract Lance lance1(ImagemDeTabuleiro estado);
	public abstract Lance lance2(ImagemDeTabuleiro estado);
	
	public Lance informarPrimeiroLance() {
		Lance lance;
		lance = new Lance();
		lance.assumir(1, 1);
		return lance;
	}
	
	public Lance lance3(ImagemDeTabuleiro estado) {
		Lance lance;
		lance = this.lanceBasico(estado);
		return lance;
	}
	 
	public Lance lance4(ImagemDeTabuleiro estado) {
		Lance lance;
		lance = this.lanceBasico(estado);
		return lance;
	}
	 
	public Lance lanceBasico(ImagemDeTabuleiro estado) {
		ordemLance++;
		Lance lance;
		lance = this.buscarVitoria(estado);
		if (lance.informarLinha() == 0) {
			lance = this.evitarDerrota(estado);
			if (lance.informarLinha() == 0) {
				lance = this.lanceQualquer(estado);
			};
		};
		return lance;
	}
	 	 
	
	public Lance avaliarLinhaPreenchivel (ImagemDeTabuleiro estado, int simbolo){
		Lance lance;
		int contSimbolo = 0;
		int linhaDesoc = 0;
		int colunaDesoc = 0;
		lance = new Lance();
		for (int linha=1; linha<4; linha++){
			if (estado.ocupadasNaLinha(linha) == 2) {
				contSimbolo = 0;
				linhaDesoc = 0;
				colunaDesoc = 0;
				for (int coluna=1; coluna<4; coluna++){
					if (estado.informarPosicaoVazia(linha, coluna)) {
						linhaDesoc = linha;
						colunaDesoc = coluna;
					} else {
						if (estado.informarValor(linha, coluna) == simbolo) {
							contSimbolo++;
						}
					};
				};
				if (contSimbolo == 2 ) {
					lance.assumir(linhaDesoc, colunaDesoc);
					return lance;
				};
			};
		};
		return lance;
	}
	
	public Lance avaliarColunaPreenchivel (ImagemDeTabuleiro estado, int simbolo){
		Lance lance;
		int contSimbolo = 0;
		int linhaDesoc = 0;
		int colunaDesoc = 0;
		lance = new Lance();
		for (int coluna=1; coluna<4; coluna++){
			if (estado.ocupadasNaColuna(coluna) == 2) {
				contSimbolo = 0;
				linhaDesoc = 0;
				colunaDesoc = 0;
				for (int linha=1; linha<4; linha++){
					if (estado.informarPosicaoVazia(linha, coluna)) {
						linhaDesoc = linha;
						colunaDesoc = coluna;
					} else {
						if (estado.informarValor(linha, coluna) == simbolo) {
							contSimbolo++;
						}
					};
				};
				if (contSimbolo == 2 ) {
					lance.assumir(linhaDesoc, colunaDesoc);
					return lance;
				};
			};
		};
		return lance;
	}

	public Lance avaliarDiagonalPrincipalPreenchivel (ImagemDeTabuleiro estado, int simbolo){
		Lance lance;
		int contSimbolo = 0;
		int linhaDesoc = 0;
		int colunaDesoc = 0;
		lance = new Lance();
		if (estado.informarPosicaoVazia(1, 1)) {
			linhaDesoc = 1;
			colunaDesoc = 1;
		} else {
			if (estado.informarValor(1, 1) == simbolo) {
				contSimbolo++;
			}
		};
		if (estado.informarPosicaoVazia(2, 2)) {
			linhaDesoc = 2;
			colunaDesoc = 2;
		} else {
			if (estado.informarValor(2, 2) == simbolo) {
				contSimbolo++;
			}
		};
		if (estado.informarPosicaoVazia(3, 3)) {
			linhaDesoc = 3;
			colunaDesoc = 3;
		} else {
			if (estado.informarValor(3, 3) == simbolo) {
				contSimbolo++;
			}
		};
		if (contSimbolo == 2 ) {
			lance.assumir(linhaDesoc, colunaDesoc);
			return lance;
		};
		return lance;
	}

	public Lance avaliarDiagonalSecundariaPreenchivel (ImagemDeTabuleiro estado, int simbolo){
		Lance lance;
		int contSimbolo = 0;
		int linhaDesoc = 0;
		int colunaDesoc = 0;
		lance = new Lance();
		if (estado.informarPosicaoVazia(3, 1)) {
			linhaDesoc = 3;
			colunaDesoc = 1;
		} else {
			if (estado.informarValor(3, 1) == simbolo) {
				contSimbolo++;
			}
		};
		if (estado.informarPosicaoVazia(2, 2)) {
			linhaDesoc = 2;
			colunaDesoc = 2;
		} else {
			if (estado.informarValor(2, 2) == simbolo) {
				contSimbolo++;
			}
		};
		if (estado.informarPosicaoVazia(1, 3)) {
			linhaDesoc = 1;
			colunaDesoc = 3;
		} else {
			if (estado.informarValor(1, 3) == simbolo) {
				contSimbolo++;
			}
		};
		if (contSimbolo == 2 ) {
			lance.assumir(linhaDesoc, colunaDesoc);
			return lance;
		};
		return lance;
	}
	
	public Lance buscarVitoria(ImagemDeTabuleiro estado) {
		Lance lance, primeiroLance;
		int meuSimbolo;
		primeiroLance = this.informarPrimeiroLance();
		meuSimbolo = estado.informarValor((primeiroLance.informarLinha()), (primeiroLance.informarColuna()));
		// varredura de linhas
		lance = this.avaliarLinhaPreenchivel(estado, meuSimbolo);
		if (lance.informarLinha() != 0) {
			return lance;
		};
		// varredura de colunas
		lance = this.avaliarColunaPreenchivel(estado, meuSimbolo);
		if (lance.informarLinha() != 0) {
			return lance;
		};
		// diagonal principal
		lance = this.avaliarDiagonalPrincipalPreenchivel(estado, meuSimbolo);
		if (lance.informarLinha() != 0) {
			return lance;
		};
		// diagonal secundaria
		lance = this.avaliarDiagonalSecundariaPreenchivel(estado, meuSimbolo);
		if (lance.informarLinha() != 0) {
			return lance;
		};
		return lance;
	}
	 
	public Lance evitarDerrota(ImagemDeTabuleiro estado) {
		Lance lance, primeiroLance;
		int meuSimbolo;
		int outroSimbolo = 1;
		lance = new Lance();
		primeiroLance = this.informarPrimeiroLance();
		meuSimbolo = estado.informarValor((primeiroLance.informarLinha()), (primeiroLance.informarColuna()));
		if (meuSimbolo == 1){
			outroSimbolo = 2;
		};
		// varredura de linhas
		lance = this.avaliarLinhaPreenchivel(estado, outroSimbolo);
		if (lance.informarLinha() != 0) {
			return lance;
		};
		// varredura de colunas
		lance = this.avaliarColunaPreenchivel(estado, outroSimbolo);
		if (lance.informarLinha() != 0) {
			return lance;
		};
		// diagonal principal
		lance = this.avaliarDiagonalPrincipalPreenchivel(estado, outroSimbolo);
		if (lance.informarLinha() != 0) {
			return lance;
		};
		// diagonal secundaria
		lance = this.avaliarDiagonalSecundariaPreenchivel(estado, outroSimbolo);
		if (lance.informarLinha() != 0) {
			return lance;
		};
		return lance;
	}
	 
	public Lance lanceQualquer(ImagemDeTabuleiro estado) {
		Lance lance = null;
		for (int linha=1; linha<4; linha++){
			for (int coluna=1; coluna<4; coluna++){
				if (estado.informarPosicaoVazia(linha, coluna)) {
					lance = new Lance();
					lance.assumir(linha, coluna);
					return lance;
				};
			};
		};
		return lance;
	}
	 
}
 
