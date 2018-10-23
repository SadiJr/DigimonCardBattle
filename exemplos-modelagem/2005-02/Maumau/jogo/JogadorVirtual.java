package jogo;

/**
 * Jogador virtual que pensa sozinho.
 */
public class JogadorVirtual extends Jogador {

	private CtrJogo ctrJogo;

	private JogadorVirtual jogadorVirtual;

	public JogadorVirtual(String pNome, CtrJogo pCtrJogo) {
		super(pNome);
		this.ctrJogo = pCtrJogo;
	}

	/**
	 * Método para pedir uma jogada ao Jogador Virtual.
	 */
	public Carta jogar(int pNaipe, int pNumero) {
		int n = this.getAMao().size();
		Carta pCarta = new Carta(0, 0, "");
		boolean soValete = false;

		/**
		 * Seleciona as Cartas Descartáveis, incluindo o Valete, sempre como uma
		 * carta Descartável
		 */
		Carta[] descartaveis = new Carta[n];
		int nDescartaveis = 0;

		for (int i = 0; i < n; i++) {
			if (pNumero == 11) {
				if (((Carta) this.getAMao().elementAt(i)).getANaipe() == this.ctrJogo
						.getNaipeCoringa()
						|| ((Carta) this.getAMao().elementAt(i)).getANumero() == pNumero) {
					descartaveis[nDescartaveis] = (Carta) this.getAMao()
							.elementAt(i);
					nDescartaveis++;
				}
			} else {
				if (((Carta) this.getAMao().elementAt(i)).getANaipe() == pNaipe
						|| ((Carta) this.getAMao().elementAt(i)).getANumero() == pNumero
						|| ((Carta) this.getAMao().elementAt(i)).getANumero() == 11) {
					descartaveis[nDescartaveis] = (Carta) this.getAMao()
							.elementAt(i);
					nDescartaveis++;
				}
			}
		}
		
		/**
		 * Verifica se nas descartáveis só há Valete
		 */
		int contValete = 0;
		for (int i = 0; i < nDescartaveis; i++) {
			if (descartaveis[i].getANumero() == 11) {
				contValete++;
			}
		}
		if (contValete == nDescartaveis) {
			soValete = true;
		}
		/**
		 * Verifica se carta anterior é 7
		 */
		if (pNumero == 7) {
			/**
			 * anterior = 7
			 */
			for (int i = 0; i < nDescartaveis; i++) {
				/**
				 * verifica se tem 7 na mão
				 */
				if (descartaveis[i].getANumero() == 7) {
					pCarta = descartaveis[i];
					break;
				}
			}
		} else {
			/**
			 * anterior != de 7
			 */
			if (soValete && (nDescartaveis > 0)) {
				/**
				 * Cartas jogáveis são só valete(s)
				 */
				pCarta = descartaveis[0];
				/**
				 * Conta os naipes na mão para escolher
				 */
				int nPaus = 0, nEspada = 0, nCopas = 0, nOuro = 0;
				for (int i = 0; i < n; i++) {
					int naipe = ((Carta) this.getAMao().elementAt(i))
							.getANaipe();
					switch (naipe) {
					case 1:
						nCopas++;
						break;
					case 2:
						nOuro++;
						break;
					case 3:
						nEspada++;
						break;
					case 4:
						nPaus++;
						break;
					}
				}
				int maior = nCopas;
				if (nOuro > maior) {
					maior = nOuro;
				} else if (nEspada > maior) {
					maior = nEspada;
				} else if (nPaus > maior) {
					maior = nPaus;
				}
				this.ctrJogo.ecolheNaipe(maior);
			} else if (nDescartaveis == 0) {
				/**
				 * Cartas Jogáveis == 0
				 */
				return pCarta;
			} else if (nDescartaveis > 0) {
				/**
				 * Cartas jogáveis > 0
				 */
				boolean temSete = false;
				boolean temNove = false;
				boolean temAs = false;
				boolean temRei = false;
				for (int i = 0; i < nDescartaveis; i++) {
					/**
					 * verifica se tem 7 na mão
					 */
					if (descartaveis[i].getANumero() == 7) {
						pCarta = descartaveis[i];
						temSete = true;
						temNove = true;
						temAs = true;
						temRei = true;
						break;
					}
				}
				if (!temSete) {
					for (int i = 0; i < nDescartaveis; i++) {
						/**
						 * verifica se tem 9 na mão
						 */
						if (descartaveis[i].getANumero() == 9) {
							pCarta = descartaveis[i];
							temNove = true;
							temAs = true;
							temRei = true;
							break;
						}
					}
				}
				if (!temNove) {
					for (int i = 0; i < nDescartaveis; i++) {
						/**
						 * verifica se tem As na mão
						 */
						if (descartaveis[i].getANumero() == 1) {
							pCarta = descartaveis[i];
							temAs = true;
							temRei = true;
							break;
						}
					}
				}
				if (!temAs) {
					for (int i = 0; i < nDescartaveis; i++) {
						/**
						 * verifica se tem Rei na mão
						 */
						if (descartaveis[i].getANumero() == 13) {
							pCarta = descartaveis[i];
							temRei = true;
							break;
						}
					}
				}
				if (!temRei) {
					pCarta = descartaveis[0];
					if (descartaveis[0].getANumero() == 11) {
						/**
						 * Conta os naipes na mão para escolher
						 */
						int nPaus = 0, nEspada = 0, nCopas = 0, nOuro = 0;
						for (int i = 0; i < n; i++) {
							int naipe = ((Carta) this.getAMao().elementAt(i))
									.getANaipe();
							switch (naipe) {
							case 1:
								nCopas++;
								break;
							case 2:
								nOuro++;
								break;
							case 3:
								nEspada++;
								break;
							case 4:
								nPaus++;
								break;
							}
						}
						int maior = nCopas;
						if (nOuro > maior) {
							maior = nOuro;
						} else if (nEspada > maior) {
							maior = nEspada;
						} else if (nPaus > maior) {
							maior = nPaus;
						}
						this.ctrJogo.ecolheNaipe(maior);
					}
				}
			}
		}

		return pCarta;

		/*
		 * int n = this.getAMao().size(); Carta pCarta = new Carta(0, 0, "");
		 * for (int i = 0; i < n; i++) { if(pNumero == 11){
		 * System.out.println("Carta naipe: " + ((Carta)
		 * this.getAMao().elementAt(i)).getANaipe()); System.out.println("Naipe
		 * Coringa: " + this.ctrJogo.getNaipeCoringa());
		 * 
		 * if (((Carta) this.getAMao().elementAt(i)).getANaipe() ==
		 * this.ctrJogo.getNaipeCoringa() || ((Carta)
		 * this.getAMao().elementAt(i)).getANumero() == pNumero) { pCarta =
		 * (Carta) this.getAMao().elementAt(i); break; } } else { if (((Carta)
		 * this.getAMao().elementAt(i)).getANaipe() == pNaipe || ((Carta)
		 * this.getAMao().elementAt(i)).getANumero() == pNumero) { pCarta =
		 * (Carta) this.getAMao().elementAt(i); break; } }
		 *  } return pCarta;
		 *  
		 */

	}

}
