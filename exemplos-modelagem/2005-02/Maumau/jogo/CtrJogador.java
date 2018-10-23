package jogo;

import util.FilaCircularDinamica;

/**
 * Controle do jogador.
 */
public class CtrJogador extends FilaCircularDinamica {

	private CtrJogo ctrJogo;

	private int fim;

	/**
	 * Informa se a rotação está no sentido horário.
	 */
	public boolean horario = true;

	private int inicio;

	public Jogador jogador;

	public JogadorVirtual jVirtual;

	/**
	 * Array com referencias dos jogadores na fila.
	 */
	public Jogador[] referenciaJogadores;

	public CtrJogador(CtrJogo pCtrJogo) {
		super();
		this.ctrJogo = pCtrJogo;
	}

	/**
	 * Verifica o jogador com a menor pontuação e o coloca como primeiro da
	 * fila.
	 */
	public void ajeitaPrimeiroDaFila() {
		Jogador[] j = new Jogador[this.num];
		for (int i = 0; i < j.length; i++) {
			j[i] = this.referenciaJogadores[i];
		}
		for (int i = 0; i < j.length - 1; i++) {
			for (int k = 0; k < j.length - 1; k++) {
				if (j[k].getAPontuacao() < j[k + 1].getAPontuacao()) {
					Jogador x = j[k];
					Jogador y = j[k + 1];
					j[k] = y;
					j[k + 1] = x;
				}
			}
		}
		try {
			while (this.primeiro() != j[0]) {
				this.prox();
			}
		} catch (Exception e) {
			this.ctrJogo.avisoErro("Erro ao organizar o primeiro da Fila");
		}
	}

	/**
	 * Descarta a carta escolhida pelo jogador.
	 */
	public void descartar(Carta pCarta) throws Exception {
		(((Jogador) this.primeiro()).getAMao()).removeElement(pCarta);
	}

}
