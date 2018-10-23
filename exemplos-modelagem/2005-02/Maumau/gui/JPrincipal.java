/*
 * Created on 10/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gui;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jogo.Carta;
import jogo.CtrJogo;
import jogo.Jogador;

/**
 * Janela principal do jogo.
 * @author Administrador
 * 
 */
public class JPrincipal extends JFrame {

	private javax.swing.JMenuItem aboutMenuItem = null;

	//Conta quantos clique foram dados no botão MauMau.
	private int cliquesMaumau = 0;

	public CtrJogo ctrJogo;

	private javax.swing.JMenuItem exitMenuItem = null;

	private javax.swing.JMenu fileMenu = null; //  @jve:decl-index=0:

	private javax.swing.JMenu helpMenu = null;

	private JButton jButton = null;

	public javax.swing.JPanel jContentPane = null;

	private javax.swing.JMenuBar jJMenuBar = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel10 = null;

	private JLabel jLabel11 = null;

	private JLabel jLabel12 = null;

	private JLabel jLabel13 = null;

	private JLabel jLabel2 = null;

	private JLabel jLabel3 = null;

	private JLabel jLabel4 = null;

	private JLabel jLabel5 = null;

	private JLabel jLabel6 = null;

	private JLabel jLabel7 = null;

	private JLabel jLabel8 = null;

	private JLabel jLabel9 = null;

	private JMenuItem jMenuItem = null;

	private JMenuItem jMenuItem1 = null;

	private JMenuItem jogarMenuItem = null;

	private JPanel jPanel = null;

	private JPanel jPanel1 = null;

	private JPanel jPanel2 = null;

	private JPanel jPanel3 = null;

	private JPanel jPanel4 = null;

	private JTextField jTextField = null;

	private boolean minhaVez = false;

	//Ajuda a Interface a saber se é a primeira que é atualizada
	private boolean primeiraVez = true;

	/**
	 * This is the default constructor
	 */
	public JPrincipal(CtrJogo pCtrJogo) {
		super();
		this.ctrJogo = pCtrJogo;
		initialize();
	}

	//Adiciona uyma carta a mão do jogador.
	public void adicionaCarta(/* Jogador jogador */) {
		JLabel iLabel = new JLabel();
		iLabel.setText("");
		iLabel.setIcon(new ImageIcon(getClass().getResource(
				"/util/images/cartas/carta04.gif")));
		jPanel4.add(iLabel);
		this.show();
	}

	/**
	 * Declara fim de jogo ou rodada. Recebe um boolean. Se true, a partida
	 * terminou. Se false, apenas a rodada terminou.
	 * 
	 * @param fimDeJogo
	 */
	public void anunciaFimDeJogo(boolean fimDeJogo) {
		if (fimDeJogo) {
			CardLayout cl = (CardLayout) (this.jContentPane.getLayout());
			cl.first(this.jContentPane);
			JPontuacao jPontuacao = new JPontuacao(this);
			jPontuacao.anunciaFimJogo();
			jPontuacao.show();
			this.getJMenuItem1().setVisible(true);

		} else {
			JPontuacao jPontuacao = new JPontuacao(this);
			jPontuacao.anunciaFimRodada();
			jPontuacao.show();
			jPontuacao.toFront();
		}
	}

	/**
	 * Atualiza a interface relendo todos os dados necessários para mostrar na
	 * tela.
	 * 
	 * @param s
	 */
	public void atualizarInterface(String s) {
		try {
			//Atualiza a imagem dos jogadores
			if (primeiraVez == true) {
				this.jLabel1.setIcon(null);
				this.jLabel2.setIcon(null);
				this.jLabel3.setIcon(null);
				this.jLabel4.setIcon(null);
				this.jLabel5.setIcon(null);
				this.jLabel6.setIcon(null);
				this.jLabel7.setIcon(null);
				this.jLabel1.setText("");
				this.jLabel2.setText("");
				this.jLabel3.setText("");
				this.jLabel4.setText("");
				this.jLabel5.setText("");
				this.jLabel6.setText("");
				this.jLabel7.setText("");
				primeiraVez = false;
			}
			int n = this.ctrJogo.ctrJogador.num;
			for (int i = 0; i < n; i++) {
				switch (i) {
				case 0:
					if (this.ctrJogo.ctrJogador.referenciaJogadores[0]
							.equals((Jogador) this.ctrJogo.ctrJogador
									.primeiro())
							&& (this.ctrJogo.ctrJogador.referenciaJogadores[0]
									.isAMaumau() == true)) {
						this.jLabel10.setIcon(new ImageIcon(getClass()
								.getResource("/util/images/player4.jpg")));
						this.jLabel10
								.setText(this.ctrJogo.ctrJogador.referenciaJogadores[0]
										.getANome()
										+ " "
										+ this.ctrJogo.ctrJogador.referenciaJogadores[0]
												.getAMao().size());
					} else {
						if (this.ctrJogo.ctrJogador.referenciaJogadores[0]
								.equals((Jogador) this.ctrJogo.ctrJogador
										.primeiro())
								&& (this.ctrJogo.ctrJogador.referenciaJogadores[0]
										.isAMaumau() == false)) {
							this.jLabel10.setIcon(new ImageIcon(getClass()
									.getResource("/util/images/player3.jpg")));
							this.jLabel10
									.setText(this.ctrJogo.ctrJogador.referenciaJogadores[0]
											.getANome()
											+ " "
											+ this.ctrJogo.ctrJogador.referenciaJogadores[0]
													.getAMao().size());
						} else {
							if (this.ctrJogo.ctrJogador.referenciaJogadores[0]
									.isAMaumau() == true) {
								this.jLabel10.setIcon(new ImageIcon(
										getClass().getResource(
												"/util/images/player2.jpg")));
								this.jLabel10
										.setText(this.ctrJogo.ctrJogador.referenciaJogadores[0]
												.getANome()
												+ " "
												+ this.ctrJogo.ctrJogador.referenciaJogadores[0]
														.getAMao().size());
							} else {
								this.jLabel10
										.setIcon(new ImageIcon(
												getClass()
														.getResource(
																"/util/images/player.jpg")));
								this.jLabel10
										.setText(this.ctrJogo.ctrJogador.referenciaJogadores[0]
												.getANome()
												+ " "
												+ this.ctrJogo.ctrJogador.referenciaJogadores[0]
														.getAMao().size());
							}
						}
					}
					continue;
				case 1:
					if (this.ctrJogo.ctrJogador.referenciaJogadores[1]
							.equals((Jogador) this.ctrJogo.ctrJogador
									.primeiro())
							&& (this.ctrJogo.ctrJogador.referenciaJogadores[1]
									.isAMaumau() == true)) {
						this.jLabel1.setIcon(new ImageIcon(getClass()
								.getResource("/util/images/player4.jpg")));
						this.jLabel1
								.setText(this.ctrJogo.ctrJogador.referenciaJogadores[1]
										.getANome()
										+ " "
										+ this.ctrJogo.ctrJogador.referenciaJogadores[1]
												.getAMao().size());
					} else {
						if (this.ctrJogo.ctrJogador.referenciaJogadores[1]
								.equals((Jogador) this.ctrJogo.ctrJogador
										.primeiro())
								&& (this.ctrJogo.ctrJogador.referenciaJogadores[1]
										.isAMaumau() == false)) {
							this.jLabel1.setIcon(new ImageIcon(getClass()
									.getResource("/util/images/player3.jpg")));
							this.jLabel1
									.setText(this.ctrJogo.ctrJogador.referenciaJogadores[1]
											.getANome()
											+ " "
											+ this.ctrJogo.ctrJogador.referenciaJogadores[1]
													.getAMao().size());
						} else {
							if (this.ctrJogo.ctrJogador.referenciaJogadores[1]
									.isAMaumau() == true) {
								this.jLabel1.setIcon(new ImageIcon(
										getClass().getResource(
												"/util/images/player2.jpg")));
								this.jLabel1
										.setText(this.ctrJogo.ctrJogador.referenciaJogadores[1]
												.getANome()
												+ " "
												+ this.ctrJogo.ctrJogador.referenciaJogadores[1]
														.getAMao().size());
							} else {
								this.jLabel1
										.setIcon(new ImageIcon(
												getClass()
														.getResource(
																"/util/images/player.jpg")));
								this.jLabel1
										.setText(this.ctrJogo.ctrJogador.referenciaJogadores[1]
												.getANome()
												+ " "
												+ this.ctrJogo.ctrJogador.referenciaJogadores[1]
														.getAMao().size());
							}
						}
					}
					continue;
				case 2:
					if (this.ctrJogo.ctrJogador.referenciaJogadores[2]
							.equals((Jogador) this.ctrJogo.ctrJogador
									.primeiro())
							&& (this.ctrJogo.ctrJogador.referenciaJogadores[2]
									.isAMaumau() == true)) {
						this.jLabel2.setIcon(new ImageIcon(getClass()
								.getResource("/util/images/player4.jpg")));
						this.jLabel2
								.setText(this.ctrJogo.ctrJogador.referenciaJogadores[2]
										.getANome()
										+ " "
										+ this.ctrJogo.ctrJogador.referenciaJogadores[2]
												.getAMao().size());
					} else {
						if (this.ctrJogo.ctrJogador.referenciaJogadores[2]
								.equals((Jogador) this.ctrJogo.ctrJogador
										.primeiro())
								&& (this.ctrJogo.ctrJogador.referenciaJogadores[2]
										.isAMaumau() == false)) {
							this.jLabel2.setIcon(new ImageIcon(getClass()
									.getResource("/util/images/player3.jpg")));
							this.jLabel2
									.setText(this.ctrJogo.ctrJogador.referenciaJogadores[2]
											.getANome()
											+ " "
											+ this.ctrJogo.ctrJogador.referenciaJogadores[2]
													.getAMao().size());
						} else {
							if (this.ctrJogo.ctrJogador.referenciaJogadores[2]
									.isAMaumau() == true) {
								this.jLabel2.setIcon(new ImageIcon(
										getClass().getResource(
												"/util/images/player2.jpg")));
								this.jLabel2
										.setText(this.ctrJogo.ctrJogador.referenciaJogadores[2]
												.getANome()
												+ " "
												+ this.ctrJogo.ctrJogador.referenciaJogadores[2]
														.getAMao().size());
							} else {
								this.jLabel2
										.setIcon(new ImageIcon(
												getClass()
														.getResource(
																"/util/images/player.jpg")));
								this.jLabel2
										.setText(this.ctrJogo.ctrJogador.referenciaJogadores[2]
												.getANome()
												+ " "
												+ this.ctrJogo.ctrJogador.referenciaJogadores[2]
														.getAMao().size());
							}
						}
					}
					continue;
				case 3:
					if (this.ctrJogo.ctrJogador.referenciaJogadores[3]
							.equals((Jogador) this.ctrJogo.ctrJogador
									.primeiro())
							&& (this.ctrJogo.ctrJogador.referenciaJogadores[3]
									.isAMaumau() == true)) {
						this.jLabel3.setIcon(new ImageIcon(getClass()
								.getResource("/util/images/player4.jpg")));
						this.jLabel3
								.setText(this.ctrJogo.ctrJogador.referenciaJogadores[3]
										.getANome()
										+ " "
										+ this.ctrJogo.ctrJogador.referenciaJogadores[3]
												.getAMao().size());
					} else {
						if (this.ctrJogo.ctrJogador.referenciaJogadores[3]
								.equals((Jogador) this.ctrJogo.ctrJogador
										.primeiro())
								&& (this.ctrJogo.ctrJogador.referenciaJogadores[3]
										.isAMaumau() == false)) {
							this.jLabel3.setIcon(new ImageIcon(getClass()
									.getResource("/util/images/player3.jpg")));
							this.jLabel3
									.setText(this.ctrJogo.ctrJogador.referenciaJogadores[3]
											.getANome()
											+ " "
											+ this.ctrJogo.ctrJogador.referenciaJogadores[3]
													.getAMao().size());
						} else {
							if (this.ctrJogo.ctrJogador.referenciaJogadores[3]
									.isAMaumau() == true) {
								this.jLabel3.setIcon(new ImageIcon(
										getClass().getResource(
												"/util/images/player2.jpg")));
								this.jLabel3
										.setText(this.ctrJogo.ctrJogador.referenciaJogadores[3]
												.getANome()
												+ " "
												+ this.ctrJogo.ctrJogador.referenciaJogadores[3]
														.getAMao().size());
							} else {
								this.jLabel3
										.setIcon(new ImageIcon(
												getClass()
														.getResource(
																"/util/images/player.jpg")));
								this.jLabel3
										.setText(this.ctrJogo.ctrJogador.referenciaJogadores[3]
												.getANome()
												+ " "
												+ this.ctrJogo.ctrJogador.referenciaJogadores[3]
														.getAMao().size());
							}
						}
					}
					continue;
				case 4:
					if (this.ctrJogo.ctrJogador.referenciaJogadores[4]
							.equals((Jogador) this.ctrJogo.ctrJogador
									.primeiro())
							&& (this.ctrJogo.ctrJogador.referenciaJogadores[4]
									.isAMaumau() == true)) {
						this.jLabel4.setIcon(new ImageIcon(getClass()
								.getResource("/util/images/player4.jpg")));
						this.jLabel4
								.setText(this.ctrJogo.ctrJogador.referenciaJogadores[4]
										.getANome()
										+ " "
										+ this.ctrJogo.ctrJogador.referenciaJogadores[4]
												.getAMao().size());
					} else {
						if (this.ctrJogo.ctrJogador.referenciaJogadores[4]
								.equals((Jogador) this.ctrJogo.ctrJogador
										.primeiro())
								&& (this.ctrJogo.ctrJogador.referenciaJogadores[4]
										.isAMaumau() == false)) {
							this.jLabel4.setIcon(new ImageIcon(getClass()
									.getResource("/util/images/player3.jpg")));
							this.jLabel4
									.setText(this.ctrJogo.ctrJogador.referenciaJogadores[4]
											.getANome()
											+ " "
											+ this.ctrJogo.ctrJogador.referenciaJogadores[4]
													.getAMao().size());
						} else {
							if (this.ctrJogo.ctrJogador.referenciaJogadores[4]
									.isAMaumau() == true) {
								this.jLabel4.setIcon(new ImageIcon(
										getClass().getResource(
												"/util/images/player2.jpg")));
								this.jLabel4
										.setText(this.ctrJogo.ctrJogador.referenciaJogadores[4]
												.getANome()
												+ " "
												+ this.ctrJogo.ctrJogador.referenciaJogadores[4]
														.getAMao().size());
							} else {
								this.jLabel4
										.setIcon(new ImageIcon(
												getClass()
														.getResource(
																"/util/images/player.jpg")));
								this.jLabel4
										.setText(this.ctrJogo.ctrJogador.referenciaJogadores[4]
												.getANome()
												+ " "
												+ this.ctrJogo.ctrJogador.referenciaJogadores[4]
														.getAMao().size());
							}
						}
					}
					continue;
				case 5:
					if (this.ctrJogo.ctrJogador.referenciaJogadores[5]
							.equals((Jogador) this.ctrJogo.ctrJogador
									.primeiro())
							&& (this.ctrJogo.ctrJogador.referenciaJogadores[5]
									.isAMaumau() == true)) {
						this.jLabel5.setIcon(new ImageIcon(getClass()
								.getResource("/util/images/player4.jpg")));
						this.jLabel5
								.setText(this.ctrJogo.ctrJogador.referenciaJogadores[5]
										.getANome()
										+ " "
										+ this.ctrJogo.ctrJogador.referenciaJogadores[5]
												.getAMao().size());
					} else {
						if (this.ctrJogo.ctrJogador.referenciaJogadores[5]
								.equals((Jogador) this.ctrJogo.ctrJogador
										.primeiro())
								&& (this.ctrJogo.ctrJogador.referenciaJogadores[5]
										.isAMaumau() == false)) {
							this.jLabel5.setIcon(new ImageIcon(getClass()
									.getResource("/util/images/player3.jpg")));
							this.jLabel5
									.setText(this.ctrJogo.ctrJogador.referenciaJogadores[5]
											.getANome()
											+ " "
											+ this.ctrJogo.ctrJogador.referenciaJogadores[5]
													.getAMao().size());
						} else {
							if (this.ctrJogo.ctrJogador.referenciaJogadores[5]
									.isAMaumau() == true) {
								this.jLabel5.setIcon(new ImageIcon(
										getClass().getResource(
												"/util/images/player2.jpg")));
								this.jLabel5
										.setText(this.ctrJogo.ctrJogador.referenciaJogadores[5]
												.getANome()
												+ " "
												+ this.ctrJogo.ctrJogador.referenciaJogadores[5]
														.getAMao().size());
							} else {
								this.jLabel5
										.setIcon(new ImageIcon(
												getClass()
														.getResource(
																"/util/images/player.jpg")));
								this.jLabel5
										.setText(this.ctrJogo.ctrJogador.referenciaJogadores[5]
												.getANome()
												+ " "
												+ this.ctrJogo.ctrJogador.referenciaJogadores[5]
														.getAMao().size());
							}
						}
					}
					continue;
				case 6:
					if (this.ctrJogo.ctrJogador.referenciaJogadores[6]
							.equals((Jogador) this.ctrJogo.ctrJogador
									.primeiro())
							&& (this.ctrJogo.ctrJogador.referenciaJogadores[6]
									.isAMaumau() == true)) {
						this.jLabel6.setIcon(new ImageIcon(getClass()
								.getResource("/util/images/player4.jpg")));
						this.jLabel6
								.setText(this.ctrJogo.ctrJogador.referenciaJogadores[6]
										.getANome()
										+ " "
										+ this.ctrJogo.ctrJogador.referenciaJogadores[6]
												.getAMao().size());
					} else {
						if (this.ctrJogo.ctrJogador.referenciaJogadores[6]
								.equals((Jogador) this.ctrJogo.ctrJogador
										.primeiro())
								&& (this.ctrJogo.ctrJogador.referenciaJogadores[6]
										.isAMaumau() == false)) {
							this.jLabel6.setIcon(new ImageIcon(getClass()
									.getResource("/util/images/player3.jpg")));
							this.jLabel6
									.setText(this.ctrJogo.ctrJogador.referenciaJogadores[6]
											.getANome()
											+ " "
											+ this.ctrJogo.ctrJogador.referenciaJogadores[6]
													.getAMao().size());
						} else {
							if (this.ctrJogo.ctrJogador.referenciaJogadores[6]
									.isAMaumau() == true) {
								this.jLabel6.setIcon(new ImageIcon(
										getClass().getResource(
												"/util/images/player2.jpg")));
								this.jLabel6
										.setText(this.ctrJogo.ctrJogador.referenciaJogadores[6]
												.getANome()
												+ " "
												+ this.ctrJogo.ctrJogador.referenciaJogadores[6]
														.getAMao().size());
							} else {
								this.jLabel6
										.setIcon(new ImageIcon(
												getClass()
														.getResource(
																"/util/images/player.jpg")));
								this.jLabel6
										.setText(this.ctrJogo.ctrJogador.referenciaJogadores[6]
												.getANome()
												+ " "
												+ this.ctrJogo.ctrJogador.referenciaJogadores[6]
														.getAMao().size());
							}
						}
					}
					continue;
				case 7:
					if (this.ctrJogo.ctrJogador.referenciaJogadores[7]
							.equals((Jogador) this.ctrJogo.ctrJogador
									.primeiro())
							&& (this.ctrJogo.ctrJogador.referenciaJogadores[7]
									.isAMaumau() == true)) {
						this.jLabel7.setIcon(new ImageIcon(getClass()
								.getResource("/util/images/player4.jpg")));
						this.jLabel7
								.setText(this.ctrJogo.ctrJogador.referenciaJogadores[7]
										.getANome()
										+ " "
										+ this.ctrJogo.ctrJogador.referenciaJogadores[7]
												.getAMao().size());
					} else {
						if (this.ctrJogo.ctrJogador.referenciaJogadores[7]
								.equals((Jogador) this.ctrJogo.ctrJogador
										.primeiro())
								&& (this.ctrJogo.ctrJogador.referenciaJogadores[7]
										.isAMaumau() == false)) {
							this.jLabel7.setIcon(new ImageIcon(getClass()
									.getResource("/util/images/player3.jpg")));
							this.jLabel7
									.setText(this.ctrJogo.ctrJogador.referenciaJogadores[7]
											.getANome()
											+ " "
											+ this.ctrJogo.ctrJogador.referenciaJogadores[7]
													.getAMao().size());
						} else {
							if (this.ctrJogo.ctrJogador.referenciaJogadores[7]
									.isAMaumau() == true) {
								this.jLabel7.setIcon(new ImageIcon(
										getClass().getResource(
												"/util/images/player2.jpg")));
								this.jLabel7
										.setText(this.ctrJogo.ctrJogador.referenciaJogadores[7]
												.getANome()
												+ " "
												+ this.ctrJogo.ctrJogador.referenciaJogadores[7]
														.getAMao().size());
							} else {
								this.jLabel7
										.setIcon(new ImageIcon(
												getClass()
														.getResource(
																"/util/images/player.jpg")));
								this.jLabel7
										.setText(this.ctrJogo.ctrJogador.referenciaJogadores[7]
												.getANome()
												+ " "
												+ this.ctrJogo.ctrJogador.referenciaJogadores[7]
														.getAMao().size());
							}
						}
					}
				}
			}
			//System.out.println("Jogadores feito");
			//Configura a imagem do sentido do jogo
			if (this.ctrJogo.ctrJogador.horario == true) {
				this.jLabel11.setIcon(new ImageIcon(getClass().getResource(
						"/util/images/horario.jpg")));
			} else {
				this.jLabel11.setIcon(new ImageIcon(getClass().getResource(
						"/util/images/ahorario.jpg")));
			}
			//System.out.println("Sentido feito");
			//Configura a imagem do topo da pilha de descarte
			if (this.ctrJogo.ctrPilhaDescarte.pilhaVazia() == false) {
				this.jLabel9.setIcon(new ImageIcon(getClass().getResource(
						((Carta) this.ctrJogo.ctrPilhaDescarte.top())
								.getAImagem())));

				if (((Carta) this.ctrJogo.ctrPilhaDescarte.top()).getANumero() == 11) {
					int naipe = this.ctrJogo.getNaipeCoringa();
					switch (naipe) {
					case 1:
						this.jLabel13.setIcon(new ImageIcon(getClass()
								.getResource("/util/images/copas.jpg")));
						break;
					case 2:
						this.jLabel13.setIcon(new ImageIcon(getClass()
								.getResource("/util/images/ouro.jpg")));
						break;
					case 3:
						this.jLabel13.setIcon(new ImageIcon(getClass()
								.getResource("/util/images/espada.jpg")));
						break;
					case 4:
						this.jLabel13.setIcon(new ImageIcon(getClass()
								.getResource("/util/images/paus.jpg")));
						break;
					}
				} else {
					this.jLabel13.setIcon(new ImageIcon(getClass().getResource(
							"/util/images/branco.jpg")));
				}

			} else {
				this.jLabel9.setIcon(null);
			}
			//System.out.println("Pilha feito");
			//Configura as cartas na mão do jogador.
			this.jPanel4.removeAll();
			int nCartas = ((Jogador) this.ctrJogo.ctrJogador.referenciaJogadores[0])
					.getAMao().size();
			for (int i = 0; i < nCartas; i++) {
				Carta carta = ((Carta) ((Jogador) this.ctrJogo.ctrJogador.referenciaJogadores[0])
						.getAMao().elementAt(i));
				try {
					carta.removeMouseListener(carta.getMouseListeners()[0]);
				} catch (Exception e) {

				}
				this.jPanel4.add(carta);
				carta.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						System.out.println("mouseClicked():descartar() "
								+ e.getClickCount()); // TODO
						// Auto-generated
						// Event stub
						// mouseClicked()
						if (minhaVez == true) {
							JPrincipal.this.atualizarInterface("");
							if (JPrincipal.this.ctrJogo.descartar((Carta) e
									.getSource())) {
								minhaVez = false;
								if (JPrincipal.this.ctrJogo.verificaFimDeJogo()) {
									JPrincipal.this.ctrJogo.fimDeJogo();
								} else {
									int i = 1;
									//System.out.println("multi: " +
									// this.multiplicadorPassaVez);
									while (i <= JPrincipal.this.ctrJogo
											.getMultiplicadorPassaVez()) {
										System.out.println("passou :" + i);
										JPrincipal.this.ctrJogo.passaAVez();
										i++;
									}

									//JPrincipal.this.ctrJogo.passaAVez();
									JPrincipal.this.ctrJogo.verificaAVez();
								}
							}

						}
					}
				});
			}
			//System.out.println("Mão feito");
			//Configura a frase a aparecer no jText
			this.jTextField.setText(s);
			//System.out.println("JText feito");
			this.show();

		} catch (Exception e) {
			this
					.mostraErro("Ocorreu um erro ao atualizar a Interface.\nMotivo: "
							+ e);
		}

	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new javax.swing.JMenuItem();
			aboutMenuItem.setText("Sobre...");
			aboutMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_S);
			aboutMenuItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							JAbout jAbout = new JAbout();
							jAbout.show();

						}
					});
		}
		return aboutMenuItem;
	}

	/**
	 * @return Returns the cliquesMaumau.
	 */
	public int getCliquesMaumau() {
		return cliquesMaumau;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new javax.swing.JMenuItem();
			exitMenuItem.setText("Fechar");
			exitMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_F);
			exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitMenuItem;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private javax.swing.JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new javax.swing.JMenu();
			fileMenu.setText("Jogo");
			fileMenu.setActionCommand("Jogo");
			fileMenu.setMnemonic(java.awt.event.KeyEvent.VK_J);
			fileMenu.setName("");
			fileMenu.add(getJogarMenuItem());
			fileMenu.add(getJMenuItem1());
			fileMenu.addSeparator();
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private javax.swing.JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new javax.swing.JMenu();
			helpMenu.setText("Ajuda");
			helpMenu.setActionCommand("Ajuda");
			helpMenu.setMnemonic(java.awt.event.KeyEvent.VK_A);
			helpMenu.add(getAboutMenuItem());
			helpMenu.add(getJMenuItem());
		}
		return helpMenu;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("MauMau!");
			jButton.setToolTipText("Clique para declarar MauMau!");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("mouseClicked(): mudaMauMau()"); // TODO
					// Auto-generated
					// Event
					// stub
					// mouseClicked()
					if (minhaVez == true) {
						try {
							AudioClip audioClip = Applet
									.newAudioClip(Class.class
											.getResource("/util/maumau.wav"));
							audioClip.play();
						} catch (Exception x) {
							JPrincipal.this
									.mostraErro("Ocorreu um erro durante o jogo no método\ncalculaPontos().\nMotivo: "
											+ e);
						}
						if (JPrincipal.this.getCliquesMaumau() > 3) {
							JPrincipal.this
									.mostraErro("Chega de clicar aqui!   >_<");
						} else {
							JPrincipal.this.ctrJogo.mudaMauMau();
							int x = JPrincipal.this.getCliquesMaumau();
							JPrincipal.this.setCliquesMaumau(x + 1);
							System.out.println(JPrincipal.this
									.getCliquesMaumau());
						}
					}
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new CardLayout());
			jContentPane.setBackground(java.awt.Color.white);
			jContentPane.add(getJPanel(), getJPanel().getName());
			jContentPane.add(getJPanel1(), getJPanel1().getName());
		}
		return jContentPane;
	}

	/**
	 * This method initializes jJMenuBar
	 * 
	 * @return javax.swing.JMenuBar
	 */
	private javax.swing.JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new javax.swing.JMenuBar();
			jJMenuBar.add(getFileMenu());
			jJMenuBar.add(getHelpMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			jMenuItem.setActionCommand("Regras do Jogo");
			jMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_R);
			jMenuItem.setText("Regras do Jogo");
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					JAjuda jAjuda = new JAjuda();
					jAjuda.show();
				}
			});
		}
		return jMenuItem;
	}

	/**
	 * This method initializes jMenuItem1
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setMnemonic(java.awt.event.KeyEvent.VK_O);
			jMenuItem1.setText("Opções");
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					JOpcoes jOpcoes = new JOpcoes(JPrincipal.this);
					jOpcoes.show();
				}
			});
		}
		return jMenuItem1;
	}

	/**
	 * This method initializes jMenuItem1
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJogarMenuItem() {
		if (jogarMenuItem == null) {
			jogarMenuItem = new JMenuItem();
			jogarMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_J);
			jogarMenuItem.setText("Novo Jogo");
			jogarMenuItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.out.println("actionPerformed()"); // TODO
							// Auto-generated
							// Event
							// stub
							// actionPerformed()
							JIniciarJogo jIniciarJogo = new JIniciarJogo(
									JPrincipal.this);
							jIniciarJogo.show();
						}
					});
		}
		return jogarMenuItem;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel = new JLabel();
			jPanel = new JPanel();
			jPanel.setName("jPanel");
			jPanel.setBackground(java.awt.Color.white);
			jLabel.setText("");
			jLabel.setIcon(new ImageIcon(getClass().getResource(
					"/util/images/fundo.jpg")));
			jPanel.add(jLabel, null);

		}
		return jPanel;
	}

	//jPanel1.add(getJPanel4(), gridBagConstraints11);

	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel13 = new JLabel();
			jLabel10 = new JLabel();
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			jLabel11 = new JLabel();
			jLabel12 = new JLabel();
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.setName("jPanel1");
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.gridy = 6;
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.gridy = 8;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints9.insets = new java.awt.Insets(20, 40, 10, 40);
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.gridy = 11;
			gridBagConstraints11.gridheight = 2;
			gridBagConstraints11.fill = java.awt.GridBagConstraints.VERTICAL;
			gridBagConstraints11.gridwidth = 3;
			jPanel1.setBackground(java.awt.Color.white);
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 4;
			jLabel12.setText("");
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.insets = new java.awt.Insets(10, 10, 10, 10);
			jLabel11.setText("");
			jLabel11.setIcon(new ImageIcon(getClass().getResource(
					"/util/images/horario.jpg")));
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.gridy = 7;
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 7;
			gridBagConstraints51.gridx = 0;
			gridBagConstraints51.gridy = 7;
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 7;
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.gridy = 7;
			gridBagConstraints12.gridx = 0;
			gridBagConstraints12.gridy = 9;
			gridBagConstraints12.insets = new java.awt.Insets(10, 0, 10, 0);
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 10;
			gridBagConstraints1.insets = new java.awt.Insets(10, 0, 10, 0);
			jLabel10.setText("JLabel");
			jLabel10.setIcon(new ImageIcon(getClass().getResource(
					"/util/images/player.jpg")));
			jLabel10.setVerticalAlignment(javax.swing.SwingConstants.TOP);
			jLabel10.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
			jLabel10
					.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			gridBagConstraints13.gridx = 0;
			gridBagConstraints13.gridy = 7;
			jLabel13.setText("Naipe Coringa");
			jLabel13.setIcon(new ImageIcon(getClass().getResource(
					"/util/images/branco.jpg")));
			jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel13
					.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
			jLabel13.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
			jPanel1.add(getJPanel2(), new GridBagConstraints());
			jPanel1.add(getJPanel3(), gridBagConstraints8);
			jPanel1.add(getJTextField(), gridBagConstraints9);
			jPanel1.add(jLabel12, gridBagConstraints2);
			jPanel1.add(jLabel11, gridBagConstraints5);
			jPanel1.add(getJPanel4(), gridBagConstraints11);
			jPanel1.add(getJButton(), gridBagConstraints12);
			jPanel1.add(jLabel10, gridBagConstraints1);
			jPanel1.add(jLabel13, gridBagConstraints13);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel7 = new JLabel();
			jLabel6 = new JLabel();
			jLabel5 = new JLabel();
			jLabel4 = new JLabel();
			jLabel3 = new JLabel();
			jLabel2 = new JLabel();
			jLabel1 = new JLabel();
			jPanel2 = new JPanel();
			jLabel1.setText("");
			//jLabel1.setIcon(new ImageIcon(getClass().getResource(
			//	"/util/images/player.jpg")));
			jLabel1
					.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
			jLabel2.setText("");
			//jLabel2.setIcon(new ImageIcon(getClass().getResource(
			//	"/util/images/player.jpg")));
			jLabel2
					.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
			jLabel3.setText("");
			//jLabel3.setIcon(new ImageIcon(getClass().getResource(
			//	"/util/images/player.jpg")));
			jLabel3
					.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
			jLabel4.setText("");
			//jLabel4.setIcon(new ImageIcon(getClass().getResource(
			//	"/util/images/player.jpg")));
			jLabel4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
			jLabel4
					.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			jLabel5.setText("");
			//jLabel5.setIcon(new ImageIcon(getClass().getResource(
			//	"/util/images/player.jpg")));
			jLabel5
					.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
			jLabel6.setText("");
			//jLabel6.setIcon(new ImageIcon(getClass().getResource(
			//	"/util/images/player.jpg")));
			jLabel6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
			jLabel6
					.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			jLabel7.setText("");
			//jLabel7.setIcon(new ImageIcon(getClass().getResource(
			//	"/util/images/player.jpg")));
			jLabel7
					.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			jLabel7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
			jPanel2.setBackground(new java.awt.Color(252, 255, 255));
			jPanel2.add(jLabel1, null);
			jPanel2.add(jLabel2, null);
			jPanel2.add(jLabel3, null);
			jPanel2.add(jLabel4, null);
			jPanel2.add(jLabel5, null);
			jPanel2.add(jLabel6, null);
			jPanel2.add(jLabel7, null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jPanel3
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jLabel9 = new JLabel();
			jLabel8 = new JLabel();
			jPanel3 = new JPanel();
			jLabel8.setText("");
			jLabel8.setIcon(new ImageIcon(getClass().getResource(
					"/util/images/cartas/baralho1.gif")));
			jLabel8.setEnabled(true);
			jLabel9.setText("");
			jLabel9.setIcon(null);
			jPanel3.setBackground(java.awt.Color.white);
			jPanel3.add(jLabel8, null);
			jPanel3.add(jLabel9, null);
			jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.out.println("mouseClicked(): comprarCarta()"); // TODO
					// Auto-generated
					// Event stub
					// mouseClicked()
					try {
						if (minhaVez == true) {
							JPrincipal.this.ctrJogo.comprarCarta();
							if (((Jogador) JPrincipal.this.ctrJogo.ctrJogador
									.primeiro()).getAMao().size() > 1) {
								((Jogador) JPrincipal.this.ctrJogo.ctrJogador
										.primeiro()).setAMaumau(false);
							}

							/*
							 * int i = 1; //System.out.println("multi: " +
							 * this.multiplicadorPassaVez); while(i
							 * <=JPrincipal.this.ctrJogo.getMultiplicadorPassaVez()) {
							 * System.out.println("passou :" + i);
							 * JPrincipal.this.ctrJogo.passaAVez(); i++; }
							 */

							JPrincipal.this.ctrJogo.passaAVez();
							JPrincipal.this.ctrJogo.verificaAVez();
						}
					} catch (Exception x) {
						System.out
								.println("Erro ao comprar carta e mudar atributo MauMau");
					}
				}
			});
			;
		}
		return jPanel3;
	}

	/**
	 * This method initializes jPanel4
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			jPanel4 = new JPanel();
			jPanel4.setLayout(flowLayout1);
			jPanel4.setBackground(new java.awt.Color(252, 255, 252));
			flowLayout1.setAlignment(java.awt.FlowLayout.LEFT);
			//jPanel4.add(jLabel10, null);
		}
		return jPanel4;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setEditable(false);
			jTextField.setText("Divirta-se!");
			jTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		}
		return jTextField;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setResizable(true);
		this.setName("jPrincipal");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/util/images/icone.jpg")));
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Mau²");

		// Get the size of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(200, 200, 878, 673);

		// Determine the new location of the window
		int w = this.getSize().width;
		int h = this.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;

		// Move the window
		this.setLocation(x, y);
	}

	/**
	 * @return Returns the minhaVez.
	 */
	public boolean isMinhaVez() {
		return minhaVez;
	}

	/**
	 * @return Returns the primeiraVez.
	 */
	public boolean isPrimeiraVez() {
		return primeiraVez;
	}

	/**
	 * Mostra uma mensagem de erro na tela.
	 * 
	 * @param msg
	 */
	public void mostraErro(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Erro", 0);
	}

	/**
	 * @param cliquesMaumau
	 *            The cliquesMaumau to set.
	 */
	public void setCliquesMaumau(int cliquesMaumau) {
		this.cliquesMaumau = cliquesMaumau;
	}

	/**
	 * @param minhaVez
	 *            The minhaVez to set.
	 */
	public void setMinhaVez(boolean minhaVez) {
		this.minhaVez = minhaVez;
	}

	/**
	 * @param primeiraVez
	 *            The primeiraVez to set.
	 */
	public void setPrimeiraVez(boolean primeiraVez) {
		this.primeiraVez = primeiraVez;
	}
} //  @jve:decl-index=0:visual-constraint="10,10"
