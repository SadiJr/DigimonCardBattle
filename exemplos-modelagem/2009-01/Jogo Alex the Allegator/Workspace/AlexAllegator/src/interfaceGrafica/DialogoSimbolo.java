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

	private JPanel jContentPane = null;
	private JLabel labelAzul = null;
	private JLabel labelVerde = null;
	private JLabel labelPergunta = null;
	private JRadioButton botaoAzul = null;
	private JRadioButton botaoVerde = null;
	private JButton botaoOK = null;

	public DialogoSimbolo(java.awt.Frame parent, boolean modal) {
	        super(parent, modal);
	        initialize();
	        this.setTitle("Escolha do s�mbolo");
	        this.setLocationRelativeTo(null);
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
			Icon azul = new ImageIcon(ClassLoader.getSystemResource("azulEscolha.png"));
			Icon verde = new ImageIcon(ClassLoader.getSystemResource("verdeEscolha.png"));
			labelPergunta = new JLabel();
			labelPergunta.setBounds(new Rectangle(19, 12, 205, 25));
			labelPergunta.setText("Qual a cor do jogador 1?");
			labelAzul = new JLabel();
			labelAzul.setBounds(new Rectangle(10, 50, 70, 70));
			labelAzul.setIcon(azul);
			labelVerde = new JLabel();
			labelVerde.setBounds(new Rectangle(160, 50, 70, 70));
			labelVerde.setIcon(verde);
		    ButtonGroup grupoBotoesRadio = new ButtonGroup();
		    botaoAzul = this.getBotaoAzul();
		    botaoVerde = this.getBotaoVerde();
		    grupoBotoesRadio.add(botaoAzul);
		    grupoBotoesRadio.add(botaoVerde);
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(labelAzul, null);
			jContentPane.add(labelVerde, null);
			jContentPane.add(labelPergunta, null);
			jContentPane.add(botaoAzul, null);
			jContentPane.add(botaoVerde, null);
			jContentPane.add(getBotaoOK(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes BotaoAzul	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getBotaoAzul() {
		if (botaoAzul == null) {
			botaoAzul = new JRadioButton();
			botaoAzul.setSelected(true);
			botaoAzul.setBounds(new Rectangle(35, 130, 21, 21));
		}
		return botaoAzul;
	}

	/**
	 * This method initializes BotaoVerde	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getBotaoVerde() {
		if (botaoVerde == null) {
			botaoVerde = new JRadioButton();
			botaoVerde.setSelected(false);
			botaoVerde.setBounds(new Rectangle(185, 130, 21, 21));
		}
		return botaoVerde;
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
	
	public boolean azulSelecionado() {
		return botaoAzul.isSelected();
	}
	
	public static boolean informaSimbolo(String pergunta) {
	  	DialogoSimbolo d = new DialogoSimbolo(new javax.swing.JFrame(), true);
	  	d.definePergunta(pergunta);        
	  	d.show();
	    return (d.azulSelecionado());
	}
}