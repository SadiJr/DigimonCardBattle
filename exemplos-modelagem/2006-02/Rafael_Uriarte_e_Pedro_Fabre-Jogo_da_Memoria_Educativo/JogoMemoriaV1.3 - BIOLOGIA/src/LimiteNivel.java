import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LimiteNivel extends JPanel {

	AtorJogador ator;
	private String nivel1String = "Nivel 1 (6 pares)";
	private String nivel2String = "Nivel 2 (10 pares)";
	private String nivel = "            Escolha o numero de pares:\n";
	private JLabel picture;
	private JFrame frame;
	private JButton botaoConfirmar = new JButton("Confirmar");
	private int nivelescolhido = 1;

	public LimiteNivel(AtorJogador ator) {
		super(new BorderLayout());
		this.ator = ator;
		// Cria o radiobuton
		JRadioButton botaoNivel1 = new JRadioButton(nivel1String);
		botaoNivel1.setActionCommand(nivel1String);
		botaoNivel1.setSelected(true);
		JRadioButton botaoNive2 = new JRadioButton(nivel2String);
		botaoNive2.setActionCommand(nivel2String);

		// Grupo de Radio Buttons
		ButtonGroup group = new ButtonGroup();
		group.add(botaoNivel1);
		group.add(botaoNive2);

		// Registra o listener 
		TratadorEventos eventos = new TratadorEventos();
		botaoNivel1.addActionListener(eventos);
		botaoNive2.addActionListener(eventos);

		//Coloca as figuras
		picture = new JLabel(new ImageIcon("imagens/" + nivel1String));
		picture.setPreferredSize(new Dimension(177, 177));

		JLabel titulo = new JLabel(nivel);
		botaoConfirmar.addActionListener(eventos);
		JPanel radioPanel = new JPanel(new GridLayout(0, 1));
		radioPanel.add(botaoNivel1);
		radioPanel.add(botaoNive2);
		add(titulo, BorderLayout.NORTH);
		titulo.setFont(new Font("Currier New", Font.BOLD, 15));
		add(radioPanel, BorderLayout.WEST);
		add(picture, BorderLayout.EAST);
		botaoConfirmar.setPreferredSize(new Dimension(10, 30));
		add(botaoConfirmar, BorderLayout.SOUTH);
		// Cria o frame e seta o titulo
		frame = new JFrame("Niveis");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// cria o container
		JComponent conteiner = this;
		conteiner.setOpaque(true); // content panes must be opaque
		frame.setContentPane(conteiner);
		frame.setLocation(300,200);
		frame.setResizable(false);
		//mostra o cntanier 
		frame.pack();
		frame.setVisible(true);

	}
	/**
	 *
	 *Classe para tratar eventos no limite de Nivel
	 */
	private class TratadorEventos implements ActionListener {
		// Classe usada para definir o que acontece se o botao for pressionado
		public void actionPerformed(ActionEvent evento) {
			if (evento.getSource() == botaoConfirmar){
					frame.dispose();
					ator.setarNivel(nivelescolhido);
			}
			else {
				picture.setIcon(new ImageIcon("imagens/"+ evento.getActionCommand()));
				if (evento.getActionCommand() == nivel2String ){
					nivelescolhido = 2;
				} else{
					nivelescolhido = 1;
				}
			}
		}
	}
}
