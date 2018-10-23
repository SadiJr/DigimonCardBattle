package interfaceGrafica;



import java.awt.Rectangle;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class DialogoSimbolo extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="112,41"

	private JLabel labelXis = null;

	private JLabel labelBola = null;

	private JLabel labelPergunta = null;

	private JRadioButton botaoXis = null;

	private JRadioButton botaoBola = null;

	private JButton botaoOK = null;

	public DialogoSimbolo(java.awt.Frame parent, boolean modal) {
	        super(parent, modal);
	        initialize();
	        this.setTitle("Escolha do símbolo");
//	        this.setBounds(384, 250, 254, 215);
	 }
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(250, 250);
		this.setContentPane(getJContentPane());
	    this.addWindowListener(new java.awt.event.WindowAdapter() {
	    	public void windowClosing(java.awt.event.WindowEvent evt) {
	    		acaoBotaoOK();
	    	}
	    });
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			Icon xis = new ImageIcon(ClassLoader.getSystemResource("xis.gif"));
			Icon bola = new ImageIcon(ClassLoader.getSystemResource("bola.gif"));
			labelPergunta = new JLabel();
			labelPergunta.setBounds(new Rectangle(19, 12, 205, 25));
			labelPergunta.setText("Qual o símbolo do jogador 1?");
			labelBola = new JLabel();
			labelBola.setBounds(new Rectangle(160, 50, 70, 70));
			labelBola.setIcon(bola);
			labelXis = new JLabel();
			labelXis.setBounds(new Rectangle(10, 50, 70, 70));
			labelXis.setIcon(xis);
		    ButtonGroup grupoBotoesRadio = new ButtonGroup();
		    botaoXis = this.getBotaoXis();
		    botaoBola = this.getBotaoBola();
		    grupoBotoesRadio.add(botaoXis);
		    grupoBotoesRadio.add(botaoBola);
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(labelXis, null);
			jContentPane.add(labelBola, null);
			jContentPane.add(labelPergunta, null);
			jContentPane.add(botaoXis, null);
			jContentPane.add(botaoBola, null);
			jContentPane.add(getBotaoOK(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes botaoXis	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getBotaoXis() {
		if (botaoXis == null) {
			botaoXis = new JRadioButton();
			botaoXis.setSelected(true);
			botaoXis.setBounds(new Rectangle(35, 130, 21, 21));
		}
		return botaoXis;
	}

	/**
	 * This method initializes botaoBola	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getBotaoBola() {
		if (botaoBola == null) {
			botaoBola = new JRadioButton();
			botaoBola.setSelected(false);
			botaoBola.setBounds(new Rectangle(185, 130, 21, 21));
		}
		return botaoBola;
	}

	/**
	 * This method initializes botaoOK	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBotaoOK() {
		if (botaoOK == null) {
			botaoOK = new JButton();
			botaoOK.setText("OK");
			botaoOK.setBounds(new Rectangle(75, 170, 90, 28));
			botaoOK.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					acaoBotaoOK();
				}
			});
		}
		return botaoOK;
	}
	
	public void acaoBotaoOK() {
        setVisible(false);
        dispose();
	}
	
	public void definePergunta(String pergunta) {
		labelPergunta.setText(pergunta);
	}
	
	public boolean xisSelecionado() {
		return botaoXis.isSelected();
	}
	
	public static boolean informaSimbolo(String pergunta) {
	  	DialogoSimbolo x = new DialogoSimbolo(new javax.swing.JFrame(), true);
	  	x.definePergunta(pergunta);        
	  	x.show();
	    return (x.xisSelecionado());
	}
}
