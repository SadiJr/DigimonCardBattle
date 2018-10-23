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

public class JanelaRegras extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel = null;
	private Panel panelN = null;  //  @jve:decl-index=0:visual-constraint="544,189"
	private JPanel jPanel = null;

	public JanelaRegras() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(650, 350);
		this.setContentPane(getJContentPane());
		this.setTitle("Mancala - Regras");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelN(), BorderLayout.NORTH);
			jContentPane.add(getJPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	private Panel getPanelN() {
		if (panelN == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			panelN = new Panel();
			panelN.setLayout(new GridBagLayout());
			jLabel1 = new JLabel();
			jLabel1.setText("Regras B�sicas");
			jLabel1.setFont(new Font("Bitstream Vera Serif", Font.PLAIN, 26));
			panelN.add(jLabel1, null);
		}
		return panelN;
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new FlowLayout());
			jLabel = new JLabel();
			jLabel.setText("<html><p><p><h3>Regras B�sicas para Mancala:</h3><p>" +
					"<p>1.Cada jogador possui seis casas. As casas do Jogador A s�o as inferiores " +
					"e as do Jogador B s�o as superiores." +
					"<p> O placar total (reposit�rio) do Jogador A fica na direita e do Jogador B " +
					"na esquerda." +
					"<p><p>2.O jogo roda em sentido anti-hor�rio: clique em um punhado de pedras, e " +
					"cada caixa no sentido anti-hor�rio � acrescido de 1." +
					"<p> Se uma pedra cair no seu reposit�rio, seu placar aumenta em 1 ponto." +
					"<p><p>3.Se sua �ltima pedra cair no seu reposit�rio, voc� tem outra jogada."+
	                "<p><p>4.Se sua �ltima pedra cair em uma casa vazia do seu lado,  se houver pedras na casa oposta, do advers�rio, elas s�o adicionadas ao " +
	                "seu reposit�rio. " +
	                "<p> Da mesma forma para o jogador advers�rio."+       
	                "<p><p>5.O jogo termina se algum dos lados limparem todas suas casas"+
	                "<p> =]</html>");
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
			jPanel.add(jLabel, jLabel.getName());
		}
		return jPanel;
	}
}



