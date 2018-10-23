/*
 * Created on 16/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

/**
 * Janela de Ajuda.
 * @author Administrador
 * 
 */
public class JAjuda extends JFrame {

	private JButton jButton = null;

	private JButton jButton1 = null;

	private JButton jButton10 = null;

	private JButton jButton11 = null;

	private JButton jButton12 = null;

	private JButton jButton13 = null;

	private JButton jButton14 = null;

	private JButton jButton15 = null;

	private JButton jButton16 = null;

	private JButton jButton17 = null;

	private JButton jButton18 = null;

	private JButton jButton19 = null;

	private JButton jButton6 = null;

	private JButton jButton7 = null;

	private JButton jButton8 = null;

	private JButton jButton9 = null;

	private javax.swing.JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JLabel jLabel3 = null;

	private JLabel jLabel4 = null;

	private JLabel jLabel5 = null;

	private JLabel jLabel6 = null;

	private JLabel jLabel7 = null;

	private JLabel jLabel8 = null;

	private JLabel jLabel9 = null;

	private JPanel jPanel = null;

	private JPanel jPanel3 = null;

	private JPanel jPanel4 = null;

	private JPanel jPanel5 = null;

	private JPanel jPanel6 = null;

	private JPanel jPanel7 = null;

	private JPanel jPanel8 = null;

	private JPanel jPanel9 = null;

	private JTextArea jTextArea = null;

	private JTextArea jTextArea3 = null;

	private JTextArea jTextArea4 = null;

	private JTextArea jTextArea5 = null;

	private JTextArea jTextArea6 = null;

	private JTextArea jTextArea7 = null;

	private JTextArea jTextArea8 = null;

	private JTextArea jTextArea9 = null;

	/**
	 * This is the default constructor
	 */
	public JAjuda() {
		super();
		initialize();
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("<<");
			jButton
					.setComponentOrientation(java.awt.ComponentOrientation.LEFT_TO_RIGHT);
			jButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jButton.setBounds(60, 257, 48, 26);
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					CardLayout cl = (CardLayout) (JAjuda.this.jContentPane
							.getLayout());
					cl.previous(JAjuda.this.jContentPane);
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText(">>");
			jButton1.setBounds(179, 256, 48, 26);
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					CardLayout cl = (CardLayout) (JAjuda.this.jContentPane
							.getLayout());
					cl.next(JAjuda.this.jContentPane);
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton10
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton10() {
		if (jButton10 == null) {
			jButton10 = new JButton();
			jButton10.setBounds(60, 260, 52, 21);
			jButton10.setText("<<");
			jButton10.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					CardLayout cl = (CardLayout) (JAjuda.this.jContentPane
							.getLayout());
					cl.previous(JAjuda.this.jContentPane);
				}
			});
		}
		return jButton10;
	}

	/**
	 * This method initializes jButton11
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton11() {
		if (jButton11 == null) {
			jButton11 = new JButton();
			jButton11.setBounds(180, 260, 51, 22);
			jButton11.setText(">>");
			jButton11.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					CardLayout cl = (CardLayout) (JAjuda.this.jContentPane
							.getLayout());
					cl.next(JAjuda.this.jContentPane);
				}
			});
		}
		return jButton11;
	}

	/**
	 * This method initializes jButton12
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton12() {
		if (jButton12 == null) {
			jButton12 = new JButton();
			jButton12.setBounds(60, 260, 54, 21);
			jButton12.setText("<<");
			jButton12.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					CardLayout cl = (CardLayout) (JAjuda.this.jContentPane
							.getLayout());
					cl.previous(JAjuda.this.jContentPane);
				}
			});
		}
		return jButton12;
	}

	/**
	 * This method initializes jButton13
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton13() {
		if (jButton13 == null) {
			jButton13 = new JButton();
			jButton13.setBounds(180, 260, 51, 21);
			jButton13.setText(">>");
			jButton13.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					CardLayout cl = (CardLayout) (JAjuda.this.jContentPane
							.getLayout());
					cl.next(JAjuda.this.jContentPane);
				}
			});
		}
		return jButton13;
	}

	/**
	 * This method initializes jButton14
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton14() {
		if (jButton14 == null) {
			jButton14 = new JButton();
			jButton14.setBounds(60, 260, 54, 20);
			jButton14.setText("<<");
			jButton14.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					CardLayout cl = (CardLayout) (JAjuda.this.jContentPane
							.getLayout());
					cl.previous(JAjuda.this.jContentPane);
				}
			});
		}
		return jButton14;
	}

	/**
	 * This method initializes jButton15
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton15() {
		if (jButton15 == null) {
			jButton15 = new JButton();
			jButton15.setBounds(180, 260, 51, 20);
			jButton15.setText(">>");
			jButton15.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					CardLayout cl = (CardLayout) (JAjuda.this.jContentPane
							.getLayout());
					cl.next(JAjuda.this.jContentPane);
				}
			});
		}
		return jButton15;
	}

	/**
	 * This method initializes jButton16
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton16() {
		if (jButton16 == null) {
			jButton16 = new JButton();
			jButton16.setBounds(60, 260, 49, 22);
			jButton16.setText("<<");
			jButton16.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					CardLayout cl = (CardLayout) (JAjuda.this.jContentPane
							.getLayout());
					cl.previous(JAjuda.this.jContentPane);
				}
			});
		}
		return jButton16;
	}

	/**
	 * This method initializes jButton17
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton17() {
		if (jButton17 == null) {
			jButton17 = new JButton();
			jButton17.setBounds(180, 260, 49, 21);
			jButton17.setText(">>");
			jButton17.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					CardLayout cl = (CardLayout) (JAjuda.this.jContentPane
							.getLayout());
					cl.next(JAjuda.this.jContentPane);
				}
			});
		}
		return jButton17;
	}

	/**
	 * This method initializes jButton18
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton18() {
		if (jButton18 == null) {
			jButton18 = new JButton();
			jButton18.setBounds(60, 260, 50, 23);
			jButton18.setText("<<");
			jButton18.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					CardLayout cl = (CardLayout) (JAjuda.this.jContentPane
							.getLayout());
					cl.previous(JAjuda.this.jContentPane);
				}
			});
		}
		return jButton18;
	}

	/**
	 * This method initializes jButton19
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton19() {
		if (jButton19 == null) {
			jButton19 = new JButton();
			jButton19.setBounds(180, 260, 50, 21);
			jButton19.setText(">>");
			jButton19.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					CardLayout cl = (CardLayout) (JAjuda.this.jContentPane
							.getLayout());
					cl.next(JAjuda.this.jContentPane);
				}
			});
		}
		return jButton19;
	}

	/**
	 * This method initializes jButton6
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton6() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.setBounds(60, 260, 53, 21);
			jButton6.setText("<<");
			jButton6.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					CardLayout cl = (CardLayout) (JAjuda.this.jContentPane
							.getLayout());
					cl.previous(JAjuda.this.jContentPane);
				}
			});
		}
		return jButton6;
	}

	/**
	 * This method initializes jButton7
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton7() {
		if (jButton7 == null) {
			jButton7 = new JButton();
			jButton7.setBounds(180, 260, 54, 23);
			jButton7.setText(">>");
			jButton7.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					CardLayout cl = (CardLayout) (JAjuda.this.jContentPane
							.getLayout());
					cl.next(JAjuda.this.jContentPane);
				}
			});
		}
		return jButton7;
	}

	/**
	 * This method initializes jButton8
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton8() {
		if (jButton8 == null) {
			jButton8 = new JButton();
			jButton8.setBounds(60, 260, 52, 21);
			jButton8.setText("<<");
			jButton8.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					CardLayout cl = (CardLayout) (JAjuda.this.jContentPane
							.getLayout());
					cl.previous(JAjuda.this.jContentPane);
				}
			});
		}
		return jButton8;
	}

	/**
	 * This method initializes jButton9
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton9() {
		if (jButton9 == null) {
			jButton9 = new JButton();
			jButton9.setBounds(180, 260, 53, 21);
			jButton9.setText(">>");
			jButton9.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					CardLayout cl = (CardLayout) (JAjuda.this.jContentPane
							.getLayout());
					cl.next(JAjuda.this.jContentPane);
				}
			});
		}
		return jButton9;
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
			jContentPane.add(getJPanel(), getJPanel().getName());
			jContentPane.add(getJPanel3(), getJPanel3().getName());
			jContentPane.add(getJPanel4(), getJPanel4().getName());
			jContentPane.add(getJPanel5(), getJPanel5().getName());
			jContentPane.add(getJPanel6(), getJPanel6().getName());
			jContentPane.add(getJPanel7(), getJPanel7().getName());
			jContentPane.add(getJPanel8(), getJPanel8().getName());
			jContentPane.add(getJPanel9(), getJPanel9().getName());
		}
		return jContentPane;
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
			jPanel.setLayout(null);
			jPanel.setName("jPanel");
			jLabel.setText("Ajuda do MauMau1.0");
			jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			jLabel.setBounds(100, 20, 117, 16);
			jPanel.add(jLabel, null);
			jPanel.add(getJTextArea(), null);
			jPanel.add(getJButton(), null);
			jPanel.add(getJButton1(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel3
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jLabel3 = new JLabel();
			jPanel3 = new JPanel();
			jPanel3.setLayout(null);
			jPanel3.setName("jPanel3");
			jLabel3.setBounds(100, 20, 117, 16);
			jLabel3.setText("Regras do Jogo");
			jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jPanel3.add(jLabel3, null);
			jPanel3.add(getJTextArea3(), null);
			jPanel3.add(getJButton6(), null);
			jPanel3.add(getJButton7(), null);
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
			jLabel4 = new JLabel();
			jPanel4 = new JPanel();
			jPanel4.setLayout(null);
			jPanel4.setName("jPanel4");
			jLabel4.setBounds(100, 20, 117, 16);
			jLabel4.setText("Regras do Jogo");
			jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jPanel4.add(jLabel4, null);
			jPanel4.add(getJTextArea4(), null);
			jPanel4.add(getJButton8(), null);
			jPanel4.add(getJButton9(), null);
		}
		return jPanel4;
	}

	/**
	 * This method initializes jPanel5
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jLabel5 = new JLabel();
			jPanel5 = new JPanel();
			jPanel5.setLayout(null);
			jPanel5.setName("jPanel5");
			jLabel5.setBounds(100, 20, 117, 16);
			jLabel5.setText("Regras do Jogo");
			jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jPanel5.add(jLabel5, null);
			jPanel5.add(getJTextArea5(), null);
			jPanel5.add(getJButton10(), null);
			jPanel5.add(getJButton11(), null);
		}
		return jPanel5;
	}

	/**
	 * This method initializes jPanel6
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			jPanel6 = new JPanel();
			jLabel6 = new JLabel();
			jPanel6.setLayout(null);
			jPanel6.setName("jPanel6");
			jLabel6.setBounds(100, 20, 117, 16);
			jLabel6.setText("Pontuação");
			jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jPanel6.add(jLabel6, null);
			jPanel6.add(getJTextArea6(), null);
			jPanel6.add(getJButton12(), null);
			jPanel6.add(getJButton13(), null);
		}
		return jPanel6;
	}

	/**
	 * This method initializes jPanel7
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel7() {
		if (jPanel7 == null) {
			jPanel7 = new JPanel();
			jLabel7 = new JLabel();
			jPanel7.setLayout(null);
			jPanel7.setName("jPanel7");
			jLabel7.setBounds(100, 20, 135, 16);
			jLabel7.setText("Finalizações Especiais");
			jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jPanel7.add(jLabel7, null);
			jPanel7.add(getJTextArea7(), null);
			jPanel7.add(getJButton14(), null);
			jPanel7.add(getJButton15(), null);
		}
		return jPanel7;
	}

	/**
	 * This method initializes jPanel8
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel8() {
		if (jPanel8 == null) {
			jPanel8 = new JPanel();
			jLabel8 = new JLabel();
			jPanel8.setLayout(null);
			jPanel8.setName("jPanel8");
			jLabel8.setBounds(100, 20, 117, 16);
			jLabel8.setText("Cartas Especiais");
			jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jPanel8.add(jLabel8, null);
			jPanel8.add(getJTextArea8(), null);
			jPanel8.add(getJButton16(), null);
			jPanel8.add(getJButton17(), null);
		}
		return jPanel8;
	}

	/**
	 * This method initializes jPanel9
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel9() {
		if (jPanel9 == null) {
			jPanel9 = new JPanel();
			jLabel9 = new JLabel();
			jPanel9.setLayout(null);
			jPanel9.setName("jPanel9");
			jLabel9.setBounds(100, 20, 117, 16);
			jLabel9.setText("Cartas Especiais");
			jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jPanel9.add(jLabel9, null);
			jPanel9.add(getJTextArea9(), null);
			jPanel9.add(getJButton18(), null);
			jPanel9.add(getJButton19(), null);
		}
		return jPanel9;
	}

	/**
	 * This method initializes jTextArea
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setEditable(false);
			jTextArea
					.setText("    Bem vindo ao MauMau 1.0! \n    MauMau é um jogo de baralho, onde seu objetivo é "
							+ "descartar o mais rápido possível todas as cartas da sua mão!"
							+ "\n    O primeiro jogador a descartar inteiramente a sua mão é o vencedor da rodada."
							+ "\n    Uma partida é constituída pelo número de rodadas definidas pelo jogador."
							+ "\n    Quem terminar o jogo com menos pontos ganha!");
			jTextArea.setLineWrap(true);
			jTextArea.setWrapStyleWord(true);
			jTextArea.setBounds(20, 60, 255, 190);
		}
		return jTextArea;
	}

	/**
	 * This method initializes jTextArea3
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTextArea3() {
		if (jTextArea3 == null) {
			jTextArea3 = new JTextArea();
			jTextArea3.setBounds(20, 60, 255, 190);
			jTextArea3.setLineWrap(true);
			jTextArea3.setWrapStyleWord(true);
			jTextArea3
					.setText("    Cada jogador começa o jogo com 7 cartas, e a mesa segue no sentido horário."
							+ "\n    Cada jogador, na sua vez, deve descartar uma carta da mão, seguindo o naipe ou valor da carta do topo da pilha de descarte."
							+ "\n    Na primeira rodada, a carta do topo do baralho é descartada para o jogo poder iniciar."
							+ "\n    Caso o jogador não tenha uma carta correta para descartar, comprará uma carta do topo do baralho.");
			jTextArea3.setEditable(false);
		}
		return jTextArea3;
	}

	/**
	 * This method initializes jTextArea4
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTextArea4() {
		if (jTextArea4 == null) {
			jTextArea4 = new JTextArea();
			jTextArea4.setBounds(20, 60, 255, 190);
			jTextArea4.setLineWrap(true);
			jTextArea4.setWrapStyleWord(true);
			jTextArea4
					.setText("    O jogador pode comprar uma carta do topo mesmo que tenha uma carta para descartar, se quiser."
							+ "\n    Quando um jogador estiver com uma carta na mão, deverá avisar ao grupo que está com MauMau. Se passar a rodada e não avisar, automaticamente comprará duas cartas do topo do baralho.");
			jTextArea4.setEditable(false);
		}
		return jTextArea4;
	}

	/**
	 * This method initializes jTextArea5
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTextArea5() {
		if (jTextArea5 == null) {
			jTextArea5 = new JTextArea();
			jTextArea5.setBounds(20, 60, 255, 190);
			jTextArea5.setLineWrap(true);
			jTextArea5.setWrapStyleWord(true);
			jTextArea5
					.setText("    Quando um dos jogadores ficar sem mão, a rodada termina, e a contagem de pontos é feita."
							+ "\n    O jogador que após o número de rodadas definidas pelo usuário estiver com o menor número de pontos é declarado o vencedor da partida.");
			jTextArea5.setEditable(false);
		}
		return jTextArea5;
	}

	/**
	 * This method initializes jTextArea6
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTextArea6() {
		if (jTextArea6 == null) {
			jTextArea6 = new JTextArea();
			jTextArea6.setBounds(20, 60, 255, 190);
			jTextArea6.setLineWrap(true);
			jTextArea6.setWrapStyleWord(true);
			jTextArea6
					.setText("    Valores para contagem de pontos das cartas:"
							+ "\n  Valete: 20 pontos \n  Ás: 1 ponto \n  Figuras: 10 pontos \n  Outras cartas: Cada uma vale o seu próprio valor. Exemplo: um 4 vale 4 pontos.");
			jTextArea6.setEditable(false);
		}
		return jTextArea6;
	}

	/**
	 * This method initializes jTextArea7
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTextArea7() {
		if (jTextArea7 == null) {
			jTextArea7 = new JTextArea();
			jTextArea7.setBounds(20, 60, 255, 190);
			jTextArea7.setLineWrap(true);
			jTextArea7.setWrapStyleWord(true);
			jTextArea7
					.setText("  Se o jogo terminar com:"
							+ "\n\n  Valete: os pontos são contados em dobro."
							+ "\n\n  7: todos compram 2 cartas antes da contagem de pontos, e para cada 7 extra na mão do jogador, este deverá comprar mais 2 cartas.");
			jTextArea7.setEditable(false);
		}
		return jTextArea7;
	}

	/**
	 * This method initializes jTextArea8
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTextArea8() {
		if (jTextArea8 == null) {
			jTextArea8 = new JTextArea();
			jTextArea8.setBounds(20, 60, 255, 190);
			jTextArea8.setLineWrap(true);
			jTextArea8.setWrapStyleWord(true);
			jTextArea8
					.setText("    Durante a partida, se uma das seguintes cartas for descartada, os seguintes efeitos acontecerão:"
							+ "\n\n  Ás: o próximo jogador é pulado."
							+ "\n\n  7: o próximo jogador compra 2 cartas e é pulado. Se o jogador afetado tiver um 7 na mão, este poderá descartá-lo, e o efeito se acumula para o próximo jogador da roda.");
			jTextArea8.setEditable(false);
		}
		return jTextArea8;
	}

	/**
	 * This method initializes jTextArea9
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTextArea9() {
		if (jTextArea9 == null) {
			jTextArea9 = new JTextArea();
			jTextArea9.setBounds(20, 60, 255, 190);
			jTextArea9.setLineWrap(true);
			jTextArea9.setWrapStyleWord(true);
			jTextArea9
					.setText("  9: o jogador anterior compra 1 carta."
							+ "\n\n  Rei: a rotação da roda é invertida."
							+ "\n\n  Valete: Curinga do jogo. Pode ser descartado sem importância de naipe ou valor. O jogador que descarta um Valete pode escolher o próximo naipe/valor a ser descartado.");
			jTextArea9.setEditable(false);
		}
		return jTextArea9;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(309, 324);
		this.setContentPane(getJContentPane());
		this.setTitle("Regras do Jogo");
		// Get the size of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/util/images/icone.jpg")));
		this.setResizable(false);

		// Determine the new location of the window
		int w = this.getSize().width;
		int h = this.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;

		// Move the window
		this.setLocation(x, y);
	}
} //  @jve:decl-index=0:visual-constraint="10,10"
