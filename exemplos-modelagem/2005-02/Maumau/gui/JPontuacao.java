/*
 * Created on 25/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

import jogo.Jogador;
import javax.swing.JButton;

/**
 * Janela que demonstra a pontuação do jogo.
 * @author Administrador
 * 
 */
public class JPontuacao extends JFrame {

	private JButton jButton = null;

	private javax.swing.JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel10 = null;

	private JLabel jLabel11 = null;

	private JLabel jLabel12 = null;

	private JLabel jLabel13 = null;

	private JLabel jLabel14 = null;

	private JLabel jLabel15 = null;

	private JLabel jLabel16 = null;

	private JLabel jLabel17 = null;

	private JLabel jLabel19 = null;

	private JLabel jLabel2 = null;

	private JLabel jLabel3 = null;

	private JLabel jLabel4 = null;

	private JLabel jLabel5 = null;

	private JLabel jLabel6 = null;

	private JLabel jLabel7 = null;

	private JLabel jLabel8 = null;

	private JLabel jLabel9 = null;

	private JPrincipal jPrincipal;

	/**
	 * This is the default constructor
	 */
	public JPontuacao(JPrincipal pJPrincipal) {
		super();
		this.jPrincipal = pJPrincipal;
		initialize();
	}

	public void anunciaFimJogo() {
		int num = this.jPrincipal.ctrJogo.ctrJogador.num;
		int[] ordem = new int[num];
		for (int i = 0; i < num; i++) {
			ordem[i] = i;
		}
		for (int k = 0; k < num - 1; k++) {
			for (int i = 0; i < num - 1; i++) {
				if (((Jogador) this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[i]])
						.getAPontuacao() > ((Jogador) this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[i + 1]])
						.getAPontuacao()) {
					int x = ordem[i];
					int y = ordem[i + 1];
					ordem[i] = y;
					ordem[i + 1] = x;
				}
			}
		}
		try {
			this.jLabel2
					.setText(this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[0]]
							.getANome());
			this.jLabel3
					.setText(""
							+ this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[0]]
									.getAPontuacao());
			this.jLabel4
					.setText(this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[1]]
							.getANome());
			this.jLabel5
					.setText(""
							+ this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[1]]
									.getAPontuacao());
			this.jLabel6
					.setText(this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[2]]
							.getANome());
			this.jLabel7
					.setText(""
							+ this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[2]]
									.getAPontuacao());
			this.jLabel8
					.setText(this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[3]]
							.getANome());
			this.jLabel9
					.setText(""
							+ this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[3]]
									.getAPontuacao());
			this.jLabel10
					.setText(this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[4]]
							.getANome());
			this.jLabel11
					.setText(""
							+ this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[4]]
									.getAPontuacao());
			this.jLabel12
					.setText(this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[5]]
							.getANome());
			this.jLabel13
					.setText(""
							+ this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[5]]
									.getAPontuacao());
			this.jLabel14
					.setText(this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[6]]
							.getANome());
			this.jLabel15
					.setText(""
							+ this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[6]]
									.getAPontuacao());
			this.jLabel16
					.setText(this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[7]]
							.getANome());
			this.jLabel17
					.setText(""
							+ this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[7]]
									.getAPontuacao());
		} catch (Exception e) {
		}
		this.jLabel19
				.setText(this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[0]]
						.getANome()
						+ " venceu a partida!");
	}

	public void anunciaFimRodada() {
		int num = this.jPrincipal.ctrJogo.ctrJogador.num;
		int[] ordem = new int[num];
		for (int i = 0; i < num; i++) {
			ordem[i] = i;
		}
		for (int k = 0; k < num - 1; k++) {
			for (int i = 0; i < num - 1; i++) {
				if (((Jogador) this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[i]])
						.getAPontuacao() > ((Jogador) this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[i + 1]])
						.getAPontuacao()) {
					int x = ordem[i];
					int y = ordem[i + 1];
					ordem[i] = y;
					ordem[i + 1] = x;
				}
			}
		}
		try {
			this.jLabel2
					.setText(this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[0]]
							.getANome());
			this.jLabel3
					.setText(""
							+ this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[0]]
									.getAPontuacao());
			this.jLabel4
					.setText(this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[1]]
							.getANome());
			this.jLabel5
					.setText(""
							+ this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[1]]
									.getAPontuacao());
			this.jLabel6
					.setText(this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[2]]
							.getANome());
			this.jLabel7
					.setText(""
							+ this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[2]]
									.getAPontuacao());
			this.jLabel8
					.setText(this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[3]]
							.getANome());
			this.jLabel9
					.setText(""
							+ this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[3]]
									.getAPontuacao());
			this.jLabel10
					.setText(this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[4]]
							.getANome());
			this.jLabel11
					.setText(""
							+ this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[4]]
									.getAPontuacao());
			this.jLabel12
					.setText(this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[5]]
							.getANome());
			this.jLabel13
					.setText(""
							+ this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[5]]
									.getAPontuacao());
			this.jLabel14
					.setText(this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[6]]
							.getANome());
			this.jLabel15
					.setText(""
							+ this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[6]]
									.getAPontuacao());
			this.jLabel16
					.setText(this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[7]]
							.getANome());
			this.jLabel17
					.setText(""
							+ this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[7]]
									.getAPontuacao());
		} catch (Exception e) {
		}
		this.jLabel19
				.setText(this.jPrincipal.ctrJogo.ctrJogador.referenciaJogadores[ordem[0]]
						.getANome()
						+ " está vencendo o jogo! \nRodada "
						+ this.jPrincipal.ctrJogo.aRodadaAtual
						+ " de "
						+ this.jPrincipal.ctrJogo.aRodadas);
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Fechar Janela");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					if (JPontuacao.this.jPrincipal.ctrJogo.aRodadaAtual == JPontuacao.this.jPrincipal.ctrJogo.aRodadas) {
						JPontuacao.this.dispose();
					} else {
						JPontuacao.this.jPrincipal.ctrJogo.verificaAVez();
						JPontuacao.this.dispose();
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
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			jLabel19 = new JLabel();
			jLabel17 = new JLabel();
			jLabel16 = new JLabel();
			jLabel15 = new JLabel();
			jLabel14 = new JLabel();
			jLabel13 = new JLabel();
			jLabel12 = new JLabel();
			jLabel11 = new JLabel();
			jLabel10 = new JLabel();
			jLabel9 = new JLabel();
			jLabel8 = new JLabel();
			jLabel7 = new JLabel();
			jLabel6 = new JLabel();
			jLabel5 = new JLabel();
			jLabel4 = new JLabel();
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints25 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints27 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints29 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
			jLabel3 = new JLabel();
			jLabel2 = new JLabel();
			jLabel1 = new JLabel();
			jLabel = new JLabel();
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new GridBagLayout());
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.insets = new java.awt.Insets(10, 0, 0, 0);
			jLabel.setText("Jogador");
			gridBagConstraints2.gridx = 3;
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.insets = new java.awt.Insets(10, 0, 0, 0);
			jLabel1.setText("Pontuação");
			jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel1
					.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			jLabel1
					.setComponentOrientation(java.awt.ComponentOrientation.LEFT_TO_RIGHT);
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.gridy = 1;
			jLabel2.setText("");
			gridBagConstraints4.gridx = 3;
			gridBagConstraints4.gridy = 1;
			jLabel3.setText("");
			jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			gridBagConstraints17.gridx = 0;
			gridBagConstraints17.gridy = 2;
			jLabel4.setText("");
			gridBagConstraints18.gridx = 3;
			gridBagConstraints18.gridy = 2;
			jLabel5.setText("");
			gridBagConstraints19.gridx = 0;
			gridBagConstraints19.gridy = 3;
			jLabel6.setText("");
			gridBagConstraints20.gridx = 3;
			gridBagConstraints20.gridy = 3;
			jLabel7.setText("");
			gridBagConstraints21.gridx = 0;
			gridBagConstraints21.gridy = 4;
			jLabel8.setText("");
			gridBagConstraints22.gridx = 3;
			gridBagConstraints22.gridy = 4;
			jLabel9.setText("");
			gridBagConstraints23.gridx = 0;
			gridBagConstraints23.gridy = 5;
			jLabel10.setText("");
			gridBagConstraints24.gridx = 3;
			gridBagConstraints24.gridy = 5;
			jLabel11.setText("");
			gridBagConstraints25.gridx = 0;
			gridBagConstraints25.gridy = 6;
			jLabel12.setText("");
			gridBagConstraints26.gridx = 3;
			gridBagConstraints26.gridy = 6;
			jLabel13.setText("");
			gridBagConstraints27.gridx = 0;
			gridBagConstraints27.gridy = 7;
			jLabel14.setText("");
			gridBagConstraints28.gridx = 3;
			gridBagConstraints28.gridy = 7;
			jLabel15.setText("");
			gridBagConstraints29.gridx = 0;
			gridBagConstraints29.gridy = 8;
			jLabel16.setText("");
			gridBagConstraints30.gridx = 3;
			gridBagConstraints30.gridy = 8;
			jLabel17.setText("");
			gridBagConstraints32.gridx = 2;
			gridBagConstraints32.gridy = 12;
			gridBagConstraints32.insets = new java.awt.Insets(10, 0, 10, 0);
			jLabel19.setText("Anúncio Vencedor");
			gridBagConstraints11.gridx = 2;
			gridBagConstraints11.gridy = 13;
			gridBagConstraints11.insets = new java.awt.Insets(10, 0, 10, 0);
			jContentPane.add(jLabel, gridBagConstraints1);
			jContentPane.add(jLabel1, gridBagConstraints2);
			jContentPane.add(jLabel2, gridBagConstraints3);
			jContentPane.add(jLabel3, gridBagConstraints4);
			jContentPane.add(jLabel4, gridBagConstraints17);
			jContentPane.add(jLabel5, gridBagConstraints18);
			jContentPane.add(jLabel6, gridBagConstraints19);
			jContentPane.add(jLabel7, gridBagConstraints20);
			jContentPane.add(jLabel8, gridBagConstraints21);
			jContentPane.add(jLabel9, gridBagConstraints22);
			jContentPane.add(jLabel10, gridBagConstraints23);
			jContentPane.add(jLabel11, gridBagConstraints24);
			jContentPane.add(jLabel12, gridBagConstraints25);
			jContentPane.add(jLabel13, gridBagConstraints26);
			jContentPane.add(jLabel14, gridBagConstraints27);
			jContentPane.add(jLabel15, gridBagConstraints28);
			jContentPane.add(jLabel16, gridBagConstraints29);
			jContentPane.add(jLabel17, gridBagConstraints30);
			jContentPane.add(jLabel19, gridBagConstraints32);
			jContentPane.add(getJButton(), gridBagConstraints11);
		}
		return jContentPane;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/util/images/icone.jpg")));
		this.setSize(458, 253);
		this.setContentPane(getJContentPane());
		this.setTitle("Pontuação do Jogo");
		//		 Get the size of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/util/images/icone.jpg")));

		// Determine the new location of the window
		int w = this.getSize().width;
		int h = this.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;

		// Move the window
		this.setLocation(x, y);
	}
} //  @jve:decl-index=0:visual-constraint="10,10"
