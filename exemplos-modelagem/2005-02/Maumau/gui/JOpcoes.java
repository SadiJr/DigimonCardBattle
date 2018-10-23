/*
 * Created on 17/11/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gui;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

/**
 * Janela com as opções do jogo.
 * @author Administrador
 * 
 */
public class JOpcoes extends JFrame {

	private JButton jButton = null;

	private JComboBox jComboBox = null;

	private javax.swing.JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JLabel jLabel3 = null;

	private JLabel jLabel4 = null;

	private JLabel jLabel5 = null;

	private JLabel jLabel6 = null;

	private JLabel jLabel7 = null;

	private JLabel jLabel8 = null;

	private JLabel jLabel9 = null;

	private JPrincipal jPrincipal;

	private JTextField jTextField = null;

	private JTextField jTextField1 = null;

	private JTextField jTextField2 = null;

	private JTextField jTextField3 = null;

	private JTextField jTextField4 = null;

	private JTextField jTextField5 = null;

	private JTextField jTextField6 = null;

	private JTextField jTextField7 = null;

	/**
	 * This is the default constructor
	 */
	public JOpcoes(JPrincipal pJPrincipal) {
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
			jButton.setText("Salvar Alterações");
			jButton.setMnemonic(java.awt.event.KeyEvent.VK_S);
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO
					// Auto-generated
					// Event stub
					// actionPerformed()
					if (getJTextField().equals("")
							|| getJTextField1().equals("")
							|| getJTextField2().equals("")
							|| getJTextField3().equals("")
							|| getJTextField4().equals("")
							|| getJTextField5().equals("")
							|| getJTextField6().equals("")
							|| getJTextField7().equals("")) {
						JOptionPane.showMessageDialog(null,
								"Preencha todos os campos!", "Erro", 0);
					} else {
						String[] pData = {
								getJTextField1().getText(),
								getJTextField2().getText(),
								getJTextField3().getText(),
								getJTextField4().getText(),
								getJTextField5().getText(),
								getJTextField6().getText(),
								getJTextField7().getText(),
								getJTextField().getText(),
								(new Integer(getJComboBox().getSelectedIndex())
										.toString()) };
						JOpcoes.this.jPrincipal.ctrJogo.gravarData(pData);
						JOpcoes.this.dispose();
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
			jComboBox.addItem("Lento");
			jComboBox.addItem("Normal");
			jComboBox.addItem("Rápido");
			jComboBox.addItem("Muito Rápido");
			int i = (new Integer(this.jPrincipal.ctrJogo.data[8]).intValue());
			switch (i) {
			case 0:
				jComboBox.setSelectedIndex(0);
				break;
			case 1:
				jComboBox.setSelectedIndex(1);
				break;
			case 2:
				jComboBox.setSelectedIndex(2);
				break;
			case 3:
				jComboBox.setSelectedIndex(3);
				break;
			}
		}
		return jComboBox;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel9 = new JLabel();
			jLabel8 = new JLabel();
			jLabel7 = new JLabel();
			jLabel6 = new JLabel();
			jLabel5 = new JLabel();
			jLabel4 = new JLabel();
			jLabel3 = new JLabel();
			jLabel2 = new JLabel();
			jLabel1 = new JLabel();
			jLabel = new JLabel();
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new GridBagLayout());
			gridBagConstraints1.gridx = 2;
			gridBagConstraints1.gridy = 0;
			jLabel.setText("Nomes dos Jogadores");
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.insets = new java.awt.Insets(10, 10, 10, 10);
			jLabel1.setText("Jogador 1");
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.gridy = 2;
			gridBagConstraints3.insets = new java.awt.Insets(0, 10, 10, 10);
			jLabel2.setText("Jogador 2");
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 3;
			gridBagConstraints4.insets = new java.awt.Insets(0, 10, 10, 10);
			jLabel3.setText("Jogador 3");
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.gridy = 4;
			gridBagConstraints5.insets = new java.awt.Insets(0, 10, 10, 10);
			jLabel4.setText("Jogador 4");
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 5;
			gridBagConstraints6.insets = new java.awt.Insets(0, 10, 10, 10);
			jLabel5.setText("Jogador 5");
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.gridy = 6;
			gridBagConstraints7.insets = new java.awt.Insets(0, 10, 10, 10);
			jLabel6.setText("Jogador 6");
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.gridy = 7;
			gridBagConstraints8.insets = new java.awt.Insets(0, 10, 10, 10);
			jLabel7.setText("Jogador 7");
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.gridy = 8;
			gridBagConstraints9.insets = new java.awt.Insets(0, 10, 10, 10);
			jLabel8.setText("Jogador 8");
			gridBagConstraints10.gridx = 2;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.weightx = 1.0;
			gridBagConstraints10.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints10.insets = new java.awt.Insets(0, 0, 0, 10);
			gridBagConstraints11.gridx = 2;
			gridBagConstraints11.gridy = 2;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints11.insets = new java.awt.Insets(0, 0, 0, 10);
			gridBagConstraints12.gridx = 2;
			gridBagConstraints12.gridy = 3;
			gridBagConstraints12.weightx = 1.0;
			gridBagConstraints12.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints12.insets = new java.awt.Insets(0, 0, 0, 10);
			gridBagConstraints13.gridx = 2;
			gridBagConstraints13.gridy = 4;
			gridBagConstraints13.weightx = 1.0;
			gridBagConstraints13.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints13.insets = new java.awt.Insets(0, 0, 0, 10);
			gridBagConstraints14.gridx = 2;
			gridBagConstraints14.gridy = 5;
			gridBagConstraints14.weightx = 1.0;
			gridBagConstraints14.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints14.insets = new java.awt.Insets(0, 0, 0, 10);
			gridBagConstraints15.gridx = 2;
			gridBagConstraints15.gridy = 6;
			gridBagConstraints15.weightx = 1.0;
			gridBagConstraints15.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints15.insets = new java.awt.Insets(0, 0, 0, 10);
			gridBagConstraints16.gridx = 2;
			gridBagConstraints16.gridy = 7;
			gridBagConstraints16.weightx = 1.0;
			gridBagConstraints16.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints16.insets = new java.awt.Insets(0, 0, 0, 10);
			gridBagConstraints17.gridx = 2;
			gridBagConstraints17.gridy = 8;
			gridBagConstraints17.weightx = 1.0;
			gridBagConstraints17.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints17.insets = new java.awt.Insets(0, 0, 0, 10);
			gridBagConstraints18.gridx = 0;
			gridBagConstraints18.gridy = 9;
			gridBagConstraints18.insets = new java.awt.Insets(20, 10, 10, 10);
			jLabel9.setText("Velocidade do Jogo");
			gridBagConstraints19.gridx = 2;
			gridBagConstraints19.gridy = 9;
			gridBagConstraints19.weightx = 1.0;
			gridBagConstraints19.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints19.insets = new java.awt.Insets(10, 0, 0, 10);
			gridBagConstraints20.gridx = 2;
			gridBagConstraints20.gridy = 10;
			gridBagConstraints20.insets = new java.awt.Insets(10, 10, 10, 10);
			jContentPane.add(jLabel, gridBagConstraints1);
			jContentPane.add(jLabel1, gridBagConstraints2);
			jContentPane.add(jLabel2, gridBagConstraints3);
			jContentPane.add(jLabel3, gridBagConstraints4);
			jContentPane.add(jLabel4, gridBagConstraints5);
			jContentPane.add(jLabel5, gridBagConstraints6);
			jContentPane.add(jLabel6, gridBagConstraints7);
			jContentPane.add(jLabel7, gridBagConstraints8);
			jContentPane.add(jLabel8, gridBagConstraints9);
			jContentPane.add(getJTextField(), gridBagConstraints10);
			jContentPane.add(getJTextField1(), gridBagConstraints11);
			jContentPane.add(getJTextField2(), gridBagConstraints12);
			jContentPane.add(getJTextField3(), gridBagConstraints13);
			jContentPane.add(getJTextField4(), gridBagConstraints14);
			jContentPane.add(getJTextField5(), gridBagConstraints15);
			jContentPane.add(getJTextField6(), gridBagConstraints16);
			jContentPane.add(getJTextField7(), gridBagConstraints17);
			jContentPane.add(jLabel9, gridBagConstraints18);
			jContentPane.add(getJComboBox(), gridBagConstraints19);
			jContentPane.add(getJButton(), gridBagConstraints20);
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
	 * This method initializes jTextField1
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setText(this.jPrincipal.ctrJogo.data[0]);
		}
		return jTextField1;
	}

	/**
	 * This method initializes jTextField2
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setText(this.jPrincipal.ctrJogo.data[1]);
		}
		return jTextField2;
	}

	/**
	 * This method initializes jTextField3
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
			jTextField3.setText(this.jPrincipal.ctrJogo.data[2]);
		}
		return jTextField3;
	}

	/**
	 * This method initializes jTextField4
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField4() {
		if (jTextField4 == null) {
			jTextField4 = new JTextField();
			jTextField4.setText(this.jPrincipal.ctrJogo.data[3]);
		}
		return jTextField4;
	}

	/**
	 * This method initializes jTextField5
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField5() {
		if (jTextField5 == null) {
			jTextField5 = new JTextField();
			jTextField5.setText(this.jPrincipal.ctrJogo.data[4]);
		}
		return jTextField5;
	}

	/**
	 * This method initializes jTextField6
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
			jTextField6.setText(this.jPrincipal.ctrJogo.data[5]);
		}
		return jTextField6;
	}

	/**
	 * This method initializes jTextField7
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField7() {
		if (jTextField7 == null) {
			jTextField7 = new JTextField();
			jTextField7.setText(this.jPrincipal.ctrJogo.data[6]);
		}
		return jTextField7;
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
		this
				.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setSize(336, 395);
		this.setContentPane(getJContentPane());
		this.setTitle("Opções de Jogo");
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
