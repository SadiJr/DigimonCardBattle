package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JButton;
import java.awt.Rectangle;

public class GUIPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	protected AtorApostador ator;
	private JButton novoJogoB = null;
	private JButton instrucoesB = null;
	private JButton creditosB = null;

	/**
	 * This is the default constructor
	 */
	public GUIPrincipal(AtorApostador ator) {
		super();
		this.ator = ator;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(375, 650);
		this.setContentPane(getJContentPane());
		this.setTitle("Roleta v1.0");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			JLabel jLabel = new JLabel();
			jLabel.setIcon(new ImageIcon(getClass().getResource("/img/principal.jpg")));
			jLabel.setSize(new Dimension(375, 630));
			jLabel.setLocation(new Point(0, 0));
			jLabel.setText("");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getNovoJogoB(), null);
			jContentPane.add(getInstrucoesB(), null);	
			jContentPane.add(getCreditosB(), null);
			jContentPane.add(jLabel, null);
		}
		return jContentPane;
	}

		/**
		 * This method initializes novoJogoB	
		 * 	
		 * @return javax.swing.JButton	
		 */
		private JButton getNovoJogoB() {
			if (novoJogoB == null) {
				novoJogoB = new JButton();
				novoJogoB.setBounds(new Rectangle(125, 185, 135, 35));
				novoJogoB.setText("Novo Jogo");
				
				novoJogoB.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						ator.iniciarPartida();
						new GUIJogo(ator);
						dispose();
					}
				});
			}
			return novoJogoB;
		}

		/**
		 * This method initializes instrucoesB	
		 * 	
		 * @return javax.swing.JButton	
		 */
		private JButton getInstrucoesB() {
			if (instrucoesB == null) {
				instrucoesB = new JButton();
				instrucoesB.setBounds(new Rectangle(125, 270, 135, 35));
				instrucoesB.setText("Instruções");
				instrucoesB.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						ator.mostrarInstrucoes();
					}
				});
			}
			return instrucoesB;
		}

		/**
		 * This method initializes creditosB	
		 * 	
		 * @return javax.swing.JButton	
		 */
		private JButton getCreditosB() {
			if (creditosB == null) {
				creditosB = new JButton();
				creditosB.setBounds(new Rectangle(125, 355, 135, 35));
				creditosB.setText("Créditos");
				creditosB.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						ator.mostrarCreditos();
					}
				});
			}
			return creditosB;
		}

}  //  @jve:decl-index=0:visual-constraint="10,10"
