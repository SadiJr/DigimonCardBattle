/*
 * Created on 13/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gui;

import javax.swing.JFrame;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 * Janela Sobre.
 * @author Administrador
 * 
 */
public class JAbout extends JFrame {

	private javax.swing.JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JLabel jLabel3 = null;

	private JLabel jLabel4 = null;

	private JLabel jLabel5 = null;

	private JLabel jLabel6 = null;

	/**
	 * This is the default constructor
	 */
	public JAbout() {
		super();
		initialize();
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel6 = new JLabel();
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			jLabel5 = new JLabel();
			jLabel3 = new JLabel();
			jLabel4 = new JLabel();
			jLabel2 = new JLabel();
			jLabel1 = new JLabel();
			jLabel = new JLabel();
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new GridBagLayout());
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.gridwidth = 2;
			jLabel.setText("MauMau 2.0");
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 3;
			gridBagConstraints3.gridheight = 2;
			jLabel1.setText("Trabalho Final da Disciplina");
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.gridy = 5;
			jLabel2.setText("Análise e Projeto de Sistemas - INE5608");
			gridBagConstraints6.gridx = 1;
			gridBagConstraints6.gridy = 6;
			jLabel4.setText("Desenvolvido por:");
			gridBagConstraints7.gridx = 1;
			gridBagConstraints7.gridy = 9;
			jLabel3.setText("Fabio Kreusch - 04238222");
			gridBagConstraints8.gridx = 1;
			gridBagConstraints8.gridy = 10;
			jLabel1
					.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN,
							12));
			jLabel2
					.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN,
							12));
			jLabel4
					.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN,
							12));
			jLabel3
					.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN,
							12));
			jLabel5
					.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN,
							12));
			jLabel5.setText("Marcon Antônio Ferreira Domingues - 04238389");
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 6;
			jLabel6.setText("");
			jLabel6.setIcon(new ImageIcon(getClass().getResource(
					"/util/images/logo.jpg")));
			jContentPane.add(jLabel, gridBagConstraints2);
			jContentPane.add(jLabel1, gridBagConstraints3);
			jContentPane.add(jLabel2, gridBagConstraints4);
			jContentPane.add(jLabel4, gridBagConstraints6);
			jContentPane.add(jLabel3, gridBagConstraints7);
			jContentPane.add(jLabel5, gridBagConstraints8);
			jContentPane.add(jLabel6, gridBagConstraints1);
		}
		return jContentPane;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setLocation(280, 280);
		this.setResizable(false);
		this
				.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setSize(431, 166);
		this.setContentPane(getJContentPane());
		this.setTitle("Sobre...");
		// Get the size of the screen
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
} //  @jve:decl-index=0:visual-constraint="65,12"
