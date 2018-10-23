package jogo;

import util.PilhaDinamica;

/**
 * Controla as cartas do baralho.
 * @author Administrador
 * 
 */

public class CtrBaralho extends PilhaDinamica {

	/**
	 * Array que contém as cartas do baralho. É utilizado para fins de
	 * embaralhar o baralho.
	 */
	private Carta[] aBaralho;

	private Carta carta;

	private CtrJogo ctrJogo;

	private final String nomeArquivo = "baralho.dat";

	public CtrBaralho(CtrJogo pCtrJogo) throws Exception {
		this.ctrJogo = pCtrJogo;
		this.preparaBaralho();
	}

	/**
	 * Cria um novo baralho e o serializa.
	 *  
	 */
	private void criaBaralho() throws Exception {
		aBaralho = new Carta[104];

		//Criação de todas as cartas
		carta = new Carta(1, 1, "/util/images/cartas/carta001.jpg");
		aBaralho[0] = carta;

		carta = new Carta(1, 2, "/util/images/cartas/carta002.jpg");
		aBaralho[1] = carta;

		carta = new Carta(1, 3, "/util/images/cartas/carta003.jpg");
		aBaralho[2] = carta;

		carta = new Carta(1, 4, "/util/images/cartas/carta004.jpg");
		aBaralho[3] = carta;

		carta = new Carta(1, 5, "/util/images/cartas/carta005.jpg");
		aBaralho[4] = carta;

		carta = new Carta(1, 6, "/util/images/cartas/carta006.jpg");
		aBaralho[5] = carta;

		carta = new Carta(1, 7, "/util/images/cartas/carta007.jpg");
		aBaralho[6] = carta;

		carta = new Carta(1, 8, "/util/images/cartas/carta008.jpg");
		aBaralho[7] = carta;

		carta = new Carta(1, 9, "/util/images/cartas/carta009.jpg");
		aBaralho[8] = carta;

		carta = new Carta(1, 10, "/util/images/cartas/carta010.jpg");
		aBaralho[9] = carta;

		carta = new Carta(1, 11, "/util/images/cartas/carta011.jpg");
		aBaralho[10] = carta;

		carta = new Carta(1, 12, "/util/images/cartas/carta012.jpg");
		aBaralho[11] = carta;

		carta = new Carta(1, 13, "/util/images/cartas/carta013.jpg");
		aBaralho[12] = carta;

		carta = new Carta(2, 1, "/util/images/cartas/carta014.jpg");
		aBaralho[13] = carta;

		carta = new Carta(2, 2, "/util/images/cartas/carta015.jpg");
		aBaralho[14] = carta;

		carta = new Carta(2, 3, "/util/images/cartas/carta016.jpg");
		aBaralho[15] = carta;

		carta = new Carta(2, 4, "/util/images/cartas/carta017.jpg");
		aBaralho[16] = carta;

		carta = new Carta(2, 5, "/util/images/cartas/carta018.jpg");
		aBaralho[17] = carta;

		carta = new Carta(2, 6, "/util/images/cartas/carta019.jpg");
		aBaralho[18] = carta;

		carta = new Carta(2, 7, "/util/images/cartas/carta020.jpg");
		aBaralho[19] = carta;

		carta = new Carta(2, 8, "/util/images/cartas/carta021.jpg");
		aBaralho[20] = carta;

		carta = new Carta(2, 9, "/util/images/cartas/carta022.jpg");
		aBaralho[21] = carta;

		carta = new Carta(2, 10, "/util/images/cartas/carta023.jpg");
		aBaralho[22] = carta;

		carta = new Carta(2, 11, "/util/images/cartas/carta024.jpg");
		aBaralho[23] = carta;

		carta = new Carta(2, 12, "/util/images/cartas/carta025.jpg");
		aBaralho[24] = carta;

		carta = new Carta(2, 13, "/util/images/cartas/carta026.jpg");
		aBaralho[25] = carta;

		carta = new Carta(3, 1, "/util/images/cartas/carta027.jpg");
		aBaralho[26] = carta;

		carta = new Carta(3, 2, "/util/images/cartas/carta028.jpg");
		aBaralho[27] = carta;

		carta = new Carta(3, 3, "/util/images/cartas/carta029.jpg");
		aBaralho[28] = carta;

		carta = new Carta(3, 4, "/util/images/cartas/carta030.jpg");
		aBaralho[29] = carta;

		carta = new Carta(3, 5, "/util/images/cartas/carta031.jpg");
		aBaralho[30] = carta;

		carta = new Carta(3, 6, "/util/images/cartas/carta032.jpg");
		aBaralho[31] = carta;

		carta = new Carta(3, 7, "/util/images/cartas/carta033.jpg");
		aBaralho[32] = carta;

		carta = new Carta(3, 8, "/util/images/cartas/carta034.jpg");
		aBaralho[33] = carta;

		carta = new Carta(3, 9, "/util/images/cartas/carta035.jpg");
		aBaralho[34] = carta;

		carta = new Carta(3, 10, "/util/images/cartas/carta036.jpg");
		aBaralho[35] = carta;

		carta = new Carta(3, 11, "/util/images/cartas/carta037.jpg");
		aBaralho[36] = carta;

		carta = new Carta(3, 12, "/util/images/cartas/carta038.jpg");
		aBaralho[37] = carta;

		carta = new Carta(3, 13, "/util/images/cartas/carta039.jpg");
		aBaralho[38] = carta;

		carta = new Carta(4, 1, "/util/images/cartas/carta040.jpg");
		aBaralho[39] = carta;

		carta = new Carta(4, 2, "/util/images/cartas/carta041.jpg");
		aBaralho[40] = carta;

		carta = new Carta(4, 3, "/util/images/cartas/carta042.jpg");
		aBaralho[41] = carta;

		carta = new Carta(4, 4, "/util/images/cartas/carta043.jpg");
		aBaralho[42] = carta;

		carta = new Carta(4, 5, "/util/images/cartas/carta044.jpg");
		aBaralho[43] = carta;

		carta = new Carta(4, 6, "/util/images/cartas/carta045.jpg");
		aBaralho[44] = carta;

		carta = new Carta(4, 7, "/util/images/cartas/carta046.jpg");
		aBaralho[45] = carta;

		carta = new Carta(4, 8, "/util/images/cartas/carta047.jpg");
		aBaralho[46] = carta;

		carta = new Carta(4, 9, "/util/images/cartas/carta048.jpg");
		aBaralho[47] = carta;

		carta = new Carta(4, 10, "/util/images/cartas/carta049.jpg");
		aBaralho[48] = carta;

		carta = new Carta(4, 11, "/util/images/cartas/carta050.jpg");
		aBaralho[49] = carta;

		carta = new Carta(4, 12, "/util/images/cartas/carta051.jpg");
		aBaralho[50] = carta;

		carta = new Carta(4, 13, "/util/images/cartas/carta052.jpg");
		aBaralho[51] = carta;

		carta = new Carta(1, 1, "/util/images/cartas/carta001.jpg");
		aBaralho[52] = carta;

		carta = new Carta(1, 2, "/util/images/cartas/carta002.jpg");
		aBaralho[53] = carta;

		carta = new Carta(1, 3, "/util/images/cartas/carta003.jpg");
		aBaralho[54] = carta;

		carta = new Carta(1, 4, "/util/images/cartas/carta004.jpg");
		aBaralho[55] = carta;

		carta = new Carta(1, 5, "/util/images/cartas/carta005.jpg");
		aBaralho[56] = carta;

		carta = new Carta(1, 6, "/util/images/cartas/carta006.jpg");
		aBaralho[57] = carta;

		carta = new Carta(1, 7, "/util/images/cartas/carta007.jpg");
		aBaralho[58] = carta;

		carta = new Carta(1, 8, "/util/images/cartas/carta008.jpg");
		aBaralho[59] = carta;

		carta = new Carta(1, 9, "/util/images/cartas/carta009.jpg");
		aBaralho[60] = carta;

		carta = new Carta(1, 10, "/util/images/cartas/carta010.jpg");
		aBaralho[61] = carta;

		carta = new Carta(1, 11, "/util/images/cartas/carta011.jpg");
		aBaralho[62] = carta;

		carta = new Carta(1, 12, "/util/images/cartas/carta012.jpg");
		aBaralho[63] = carta;

		carta = new Carta(1, 13, "/util/images/cartas/carta013.jpg");
		aBaralho[64] = carta;

		carta = new Carta(2, 1, "/util/images/cartas/carta014.jpg");
		aBaralho[65] = carta;

		carta = new Carta(2, 2, "/util/images/cartas/carta015.jpg");
		aBaralho[66] = carta;

		carta = new Carta(2, 3, "/util/images/cartas/carta016.jpg");
		aBaralho[67] = carta;

		carta = new Carta(2, 4, "/util/images/cartas/carta017.jpg");
		aBaralho[68] = carta;

		carta = new Carta(2, 5, "/util/images/cartas/carta018.jpg");
		aBaralho[69] = carta;

		carta = new Carta(2, 6, "/util/images/cartas/carta019.jpg");
		aBaralho[70] = carta;

		carta = new Carta(2, 7, "/util/images/cartas/carta020.jpg");
		aBaralho[71] = carta;

		carta = new Carta(2, 8, "/util/images/cartas/carta021.jpg");
		aBaralho[72] = carta;

		carta = new Carta(2, 9, "/util/images/cartas/carta022.jpg");
		aBaralho[73] = carta;

		carta = new Carta(2, 10, "/util/images/cartas/carta023.jpg");
		aBaralho[74] = carta;

		carta = new Carta(2, 11, "/util/images/cartas/carta024.jpg");
		aBaralho[75] = carta;

		carta = new Carta(2, 12, "/util/images/cartas/carta025.jpg");
		aBaralho[76] = carta;

		carta = new Carta(2, 13, "/util/images/cartas/carta026.jpg");
		aBaralho[77] = carta;

		carta = new Carta(3, 1, "/util/images/cartas/carta027.jpg");
		aBaralho[78] = carta;

		carta = new Carta(3, 2, "/util/images/cartas/carta028.jpg");
		aBaralho[79] = carta;

		carta = new Carta(3, 3, "/util/images/cartas/carta029.jpg");
		aBaralho[80] = carta;

		carta = new Carta(3, 4, "/util/images/cartas/carta030.jpg");
		aBaralho[81] = carta;

		carta = new Carta(3, 5, "/util/images/cartas/carta031.jpg");
		aBaralho[82] = carta;

		carta = new Carta(3, 6, "/util/images/cartas/carta032.jpg");
		aBaralho[83] = carta;

		carta = new Carta(3, 7, "/util/images/cartas/carta033.jpg");
		aBaralho[84] = carta;

		carta = new Carta(3, 8, "/util/images/cartas/carta034.jpg");
		aBaralho[85] = carta;

		carta = new Carta(3, 9, "/util/images/cartas/carta035.jpg");
		aBaralho[86] = carta;

		carta = new Carta(3, 10, "/util/images/cartas/carta036.jpg");
		aBaralho[87] = carta;

		carta = new Carta(3, 11, "/util/images/cartas/carta037.jpg");
		aBaralho[88] = carta;

		carta = new Carta(3, 12, "/util/images/cartas/carta038.jpg");
		aBaralho[89] = carta;

		carta = new Carta(3, 13, "/util/images/cartas/carta039.jpg");
		aBaralho[90] = carta;

		carta = new Carta(4, 1, "/util/images/cartas/carta040.jpg");
		aBaralho[91] = carta;

		carta = new Carta(4, 2, "/util/images/cartas/carta041.jpg");
		aBaralho[92] = carta;

		carta = new Carta(4, 3, "/util/images/cartas/carta042.jpg");
		aBaralho[93] = carta;

		carta = new Carta(4, 4, "/util/images/cartas/carta043.jpg");
		aBaralho[94] = carta;

		carta = new Carta(4, 5, "/util/images/cartas/carta044.jpg");
		aBaralho[95] = carta;

		carta = new Carta(4, 6, "/util/images/cartas/carta045.jpg");
		aBaralho[96] = carta;

		carta = new Carta(4, 7, "/util/images/cartas/carta046.jpg");
		aBaralho[97] = carta;

		carta = new Carta(4, 8, "/util/images/cartas/carta047.jpg");
		aBaralho[98] = carta;

		carta = new Carta(4, 9, "/util/images/cartas/carta048.jpg");
		aBaralho[99] = carta;

		carta = new Carta(4, 10, "/util/images/cartas/carta049.jpg");
		aBaralho[100] = carta;

		carta = new Carta(4, 11, "/util/images/cartas/carta050.jpg");
		aBaralho[101] = carta;

		carta = new Carta(4, 12, "/util/images/cartas/carta051.jpg");
		aBaralho[102] = carta;

		carta = new Carta(4, 13, "/util/images/cartas/carta052.jpg");
		aBaralho[103] = carta;

		//Serializa o baralho criado
		this.ctrJogo.ctrSerializacao.gravaObjeto(this.aBaralho,
				this.nomeArquivo);

	}

	/**
	 * Embaralha as cartas e passa para a pilha dinâmica.
	 */
	public void embaralhar() {
		for (int i = 0; i < this.aBaralho.length; i++) {
			int randomNumberOne = (int) (Math.random() * this.aBaralho.length);
			int randomNumberTwo = (int) (Math.random() * this.aBaralho.length);

			Carta tmp = this.aBaralho[randomNumberOne];

			this.aBaralho[randomNumberOne] = this.aBaralho[randomNumberTwo];
			this.aBaralho[randomNumberTwo] = tmp;
		}

		//Passa as cartas de aBaralho para a pilha dinamica.
		for (int i = 0; i < this.aBaralho.length; i++) {
			this.push(aBaralho[i]);
		}
		//Limpa o array de cartas;
		this.aBaralho = null;
	}

	/**
	 * @return Returns the aBaralho.
	 */
	public Carta[] getABaralho() {
		return aBaralho;
	}

	/**
	 * Prepara um baralho para um novo Jogo.
	 */
	public void preparaBaralho() throws Exception {
		try {
			aBaralho = (Carta[]) this.ctrJogo.ctrSerializacao
					.recuperaObjeto(nomeArquivo);
		} catch (Exception e) {
			this.criaBaralho();
		}
		this.embaralhar();
	}

	/**
	 * @param baralho
	 *            The aBaralho to set.
	 */
	public void setABaralho(Carta[] baralho) {
		aBaralho = baralho;
	}
}
