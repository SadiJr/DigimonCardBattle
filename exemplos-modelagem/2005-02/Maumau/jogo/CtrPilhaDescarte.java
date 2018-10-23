package jogo;

import util.PilhaDinamica;

/**
 * Controla as cartas na mesa (descarte).
 */
public class CtrPilhaDescarte extends PilhaDinamica {

	private Carta carta;

	private CtrJogo ctrJogo;

	public CtrPilhaDescarte(CtrJogo pCtrJogo) {
		this.ctrJogo = pCtrJogo;
	}

}
