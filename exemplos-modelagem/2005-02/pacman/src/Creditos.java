import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Creditos extends JDialog {

	private JPanel jContentPane = null;
	private JTextPane jTextPane = null;
	private JLabel jLabel = null;

	/**
	 * Este é o construtor padrão
	 */
	public Creditos() {
		super();
		initialize();
		this.setVisible(true);
	}

	/**
	 * Este método inicializa "this" (JDialog)
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(450, 350);
		this.setTitle("Sobre o PacMan...");
		this.setContentPane(getJContentPane());
	}

	/**
	 * Este método inicializa o jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setIcon(new ImageIcon(getClass().getResource("/pictures/AboutLogo.gif")));
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.setBackground(java.awt.Color.black);
			jContentPane.add(jLabel, BorderLayout.WEST);
			jContentPane.add(getJTextPane(), BorderLayout.EAST );
			
			
		}
		return jContentPane;
	}

	/**
	 * Este método inicializa o jTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJTextPane() {
		if (jTextPane == null) {
			jTextPane = new JTextPane();
			jTextPane.setBackground(java.awt.Color.black);
			jTextPane.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
			jTextPane.setForeground(java.awt.Color.red);
			jTextPane.setEditable(false);
			jTextPane.setComponentOrientation(java.awt.ComponentOrientation.LEFT_TO_RIGHT);
			jTextPane.setText("\n\nProjeto PacMan\n\nEste é um projeto de código aberto,\n" +
					"desenvolvido para atender a Disciplina:\n\n" +
					"Análise e Projeto de Sistemas\n\nMinistrante:\n" +
					"Prof. Ricardo Pereira e Silva\n\n\n" +
					"Desenvolvedores:\n" +
					"Fernando Melo Faraco \t\t- Mat: 0423851-6\n" +
					"farakeys@inf.ufsc.br\n\n" +
					"Cleiton Edgar Janke Duarte \t- Mat: 0423841-9\n" +
					"cleitone@inf.ufsc.br");
		}
		return jTextPane;
	}
} 
