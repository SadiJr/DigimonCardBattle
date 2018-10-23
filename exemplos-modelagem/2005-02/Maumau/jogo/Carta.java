package jogo;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Entidade Carta.
 * @author Administrador
 * 
 */
public class Carta extends JLabel {

	/**
	 * Contém o endereço para a imagem da carta.
	 */
	private String aImagem;

	/**
	 * IDs para os naipes: 1 - copas 2 - ouro 3 - espada 4 - paus
	 */
	private int aNaipe;

	/**
	 * Numero da carta. Detalhes: Ás = 1; Valete = 11; Dama = 12; Rei = 13;
	 */
	private int aNumero;

	public Carta(int pNaipe, int pNumero, String pImagem) {
		this.setANaipe(pNaipe);
		this.setANumero(pNumero);
		this.setAImagem(pImagem);
		if (pImagem != "") {
			this.setIcon(new ImageIcon(getClass().getResource(this.aImagem)));
		}
	}

	/**
	 * @return Returns the aImagem.
	 */
	public String getAImagem() {
		return aImagem;
	}

	/**
	 * @return Returns the aNaipe.
	 */
	public int getANaipe() {
		return aNaipe;
	}

	/**
	 * @return Returns the aNumero.
	 */
	public int getANumero() {
		return aNumero;
	}

	/**
	 * @param imagem
	 *            The aImagem to set.
	 */
	public void setAImagem(String imagem) {
		aImagem = imagem;
	}

	/**
	 * @param naipe
	 *            The aNaipe to set.
	 */
	public void setANaipe(int naipe) {
		aNaipe = naipe;
	}

	/**
	 * @param numero
	 *            The aNumero to set.
	 */
	public void setANumero(int numero) {
		aNumero = numero;
	}
}
