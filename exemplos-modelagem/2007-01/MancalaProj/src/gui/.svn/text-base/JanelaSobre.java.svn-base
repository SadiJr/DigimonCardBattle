package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Panel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;

public class JanelaSobre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private javax.swing.JLabel jLabel31 = null;
	private Panel panel = null;
	private Panel panelN = null;
	private Panel panelS = null;  //  @jve:decl-index=0:visual-constraint="544,189"
	private JLabel jLabel32 = null;
	
	public JanelaSobre() {
		super();
		initialize();
	}
	
	private void initialize() {
		this.setSize(450, 223);
		this.setContentPane(getJContentPane());
		this.setTitle("Mancala - Sobre");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelN(), BorderLayout.NORTH);
			jContentPane.add(getPanelS(), BorderLayout.SOUTH);
			jContentPane.add(getPanel(), BorderLayout.WEST);
		}
		return jContentPane;
	}

	private Panel getPanel() {
		if (panel == null) {
			panel = new Panel();
			panel.setLayout(new FlowLayout());
			panel.add(jLabel, java.awt.BorderLayout.NORTH);
			panel.add(jLabel, null);
			panel.add(jLabel, null);
			panel.add(jLabel, null);
		}
		return panel;
	}

	private Panel getPanelN() {
		if (panelN == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			panelN = new Panel();
			panelN.setLayout(new GridBagLayout());
			jLabel1 = new JLabel();
			jLabel1.setText("Sobre");
			jLabel1.setFont(new Font("Bitstream Vera Serif", Font.PLAIN, 26));
			jLabel = new JLabel();
			jLabel.setText("<html><p><p>Análise de Projetos - Macala<p><P>Prof. Ricardo Pereira e Silva</html>");
			jLabel.setFont(new Font("Dialog", Font.BOLD, 14));
			panelN.add(jLabel1, null);
		}
		return panelN;
	}

	private Panel getPanelS() {
		if (panelS == null) {
			panelS = new Panel();
			panelS.setLayout(new FlowLayout());
			jLabel2 = new JLabel();
			jLabel2.setText("Alunos:  Edgar Macari Junior - Fernando De Lucca Siqueira");
			panelS.add(jLabel2, null);		
		}
		return panelS;
	}
}  //  @jve:decl-index=0:visual-constraint="49,65"



