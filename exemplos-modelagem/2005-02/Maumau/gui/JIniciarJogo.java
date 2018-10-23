/*
 * Created on 28/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gui;

import javax.swing.JFrame;

import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

/**
 * Janela de Inicio de um novo jogo.
 * @author Administrador
 * 
 */
public class JIniciarJogo extends JFrame {

	private JButton jButton = null;

	public JComboBox jComboBox = null;

	public JComboBox jComboBox1 = null;

	private javax.swing.JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JPrincipal jPrincipal;

	public JTextField jTextField = null;

	/**
	 * This is the default constructor
	 */
	public JIniciarJogo(JPrincipal pJPrincipal) {
		super();
		this.jPrincipal = pJPrincipal;
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
			jButton.setText("Iniciar Jogo");
			jButton.setMnemonic(java.awt.event.KeyEvent.VK_I);
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					int pRodadas = ((Integer) JIniciarJogo.this.jComboBox1
							.getSelectedItem()).intValue();
					int pJogadores = ((Integer) JIniciarJogo.this.jComboBox
							.getSelectedItem()).intValue();
					if (JIniciarJogo.this.jTextField.getText().equals("")) {
						JIniciarJogo.this.jPrincipal
								.mostraErro("Por favor, digite seu nome!");
					} else {
						String pNome = JIniciarJogo.this.jTextField.getText();
						String[] pData = JIniciarJogo.this.jPrincipal.ctrJogo.data;
						pData[7] = JIniciarJogo.this.getJTextField().getText();
						JIniciarJogo.this.jPrincipal.ctrJogo.gravarData(pData);
						JIniciarJogo.this.jPrincipal.ctrJogo.iniciaNovoJogo(
								pRodadas, pJogadores, pNome);
						CardLayout cl = (CardLayout) (JIniciarJogo.this.jPrincipal.jContentPane
								.getLayout());
						cl.last(JIniciarJogo.this.jPrincipal.jContentPane);
						JIniciarJogo.this.jPrincipal.setPrimeiraVez(true);
						JIniciarJogo.this.jPrincipal.ctrJogo.verificaAVez();
						JIniciarJogo.this.dispose();
					}
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.addItem(new Integer(1));
			jComboBox.addItem(new Integer(2));
			jComboBox.addItem(new Integer(3));
			jComboBox.addItem(new Integer(4));
			jComboBox.addItem(new Integer(5));
			jComboBox.addItem(new Integer(6));
			jComboBox.addItem(new Integer(7));
		}
		return jComboBox;
	}

	/**
	 * This method initializes jComboBox1
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox1() {
		if (jComboBox1 == null) {
			jComboBox1 = new JComboBox();
			jComboBox1.addItem(new Integer(1));
			jComboBox1.addItem(new Integer(2));
			jComboBox1.addItem(new Integer(3));
			jComboBox1.addItem(new Integer(4));
			jComboBox1.addItem(new Integer(5));
			jComboBox1.addItem(new Integer(6));
			jComboBox1.addItem(new Integer(7));
			jComboBox1.addItem(new Integer(8));
			jComboBox1.addItem(new Integer(9));
			jComboBox1.addItem(new Integer(10));
		}
		return jComboBox1;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel2 = new JLabel();
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			jLabel1 = new JLabel();
			jLabel = new JLabel();
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new GridBagLayout());
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.insets = new java.awt.Insets(10, 10, 10, 10);
			jLabel.setText("Nome");
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.gridy = 0;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints4.insets = new java.awt.Insets(10, 10, 10, 10);
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.gridy = 1;
			gridBagConstraints5.insets = new java.awt.Insets(10, 10, 10, 10);
			jLabel1.setText("Adversários");
			gridBagConstraints6.gridx = 1;
			gridBagConstraints6.gridy = 1;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints6.insets = new java.awt.Insets(10, 10, 10, 100);
			gridBagConstraints7.gridx = 1;
			gridBagConstraints7.gridy = 3;
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 2;
			jLabel2.setText("Rodadas");
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.gridy = 2;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.insets = new java.awt.Insets(10, 10, 10, 100);
			jContentPane.add(jLabel, gridBagConstraints3);
			jContentPane.add(getJTextField(), gridBagConstraints4);
			jContentPane.add(jLabel1, gridBagConstraints5);
			jContentPane.add(getJComboBox(), gridBagConstraints6);
			jContentPane.add(getJButton(), gridBagConstraints7);
			jContentPane.add(jLabel2, gridBagConstraints1);
			jContentPane.add(getJComboBox1(), gridBagConstraints2);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setText(this.jPrincipal.ctrJogo.data[7]);
		}
		return jTextField;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setResizable(false);
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("Preparando Novo Jogo");
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
}
