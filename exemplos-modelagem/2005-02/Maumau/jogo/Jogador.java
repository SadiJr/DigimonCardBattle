package jogo;

import java.util.Vector;

/**
 * Entidade Jogador.
 */
public class Jogador {

	private boolean aJaComprou;

	/**
	 * Vector de Cartas.
	 */
	private Vector aMao;

	/**
	 * Define se o jogador está em estado de "MauMau".
	 */
	private boolean aMaumau;

	/**
	 * Nome do Jogador.
	 */
	private String aNome;

	/**
	 * Pontuação corrente do Jogador.
	 */
	private int aPontuacao;

	private Carta carta;

	private CtrJogador ctrJogador;

	/**
	 * Identificador do Jogador.
	 */
	public int id;

	public Jogador(String pNome) {
		this.setANome(pNome);
	}

	/**
	 * Adiciona uma carta no vector de cartas da mão do jogador.
	 */
	public void adicionaCartaAMao(Carta pCarta) {
		this.aMao.addElement(pCarta);
	}

	/**
	 * @return Returns the aMao.
	 */
	public Vector getAMao() {
		return aMao;
	}

	/**
	 * @return Returns the aNome.
	 */
	public String getANome() {
		return aNome;
	}

	/**
	 * @return Returns the aPontuacao.
	 */
	public int getAPontuacao() {
		return aPontuacao;
	}

	/**
	 * @return Returns the id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return Returns the aJaComprou.
	 */
	public boolean isAJaComprou() {
		return aJaComprou;
	}

	/**
	 * @return Returns the aMaumau.
	 */
	public boolean isAMaumau() {
		return aMaumau;
	}

	/**
	 * Retorna se a mão do jogador está vazia.
	 */
	public boolean maoVazia() {
		if (this.aMao.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param jaComprou
	 *            The aJaComprou to set.
	 */
	public void setAJaComprou(boolean jaComprou) {
		aJaComprou = jaComprou;
	}

	/**
	 * @param mao
	 *            The aMao to set.
	 */
	public void setAMao(Vector mao) {
		aMao = mao;
	}

	/**
	 * @param maumau
	 *            The aMaumau to set.
	 */
	public void setAMaumau(boolean maumau) {
		aMaumau = maumau;
	}

	/**
	 * @param nome
	 *            The aNome to set.
	 */
	public void setANome(String nome) {
		aNome = nome;
	}

	/**
	 * @param pontuacao
	 *            The aPontuacao to set.
	 */
	public void setAPontuacao(int pontuacao) {
		aPontuacao = pontuacao;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(int id) {
		this.id = id;
	}
}
