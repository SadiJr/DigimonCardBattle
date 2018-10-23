import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LimiteCerto extends JFrame {

	private final String tituloJanela = "\\O/";

	// Cria os rotulos
	private Icon fig1;

	private Icon fig2;

	private JButton gravura1;

	private JButton gravura2;

	private JLabel parabens = new JLabel("PARABENS !!!! Voce Acertou!!!!");

	private JTextArea areaTexto;

	private JScrollPane scroll;

	private JScrollBar scrollVertical;

	private JButton botaoVoltar = new JButton("Voltar");

	// Cria o container e o layout
	private Container container = new Container();

	private GridBagLayout layout = new GridBagLayout();

	/**
	 * Metodo Construtor
	 * @param figura - Recebe a posicao da figura
	 * @param figuraAntiga - Recebe a posicao da figuraantiga(primeira clicada)
	 */
	public LimiteCerto(String [] caminhosFigura, String texto, String [] nome) {
		fig1 = new ImageIcon(caminhosFigura[0]);
		fig2 = new ImageIcon(caminhosFigura[1]);
		gravura1 = new JButton(fig1);
		gravura2 = new JButton(fig2);
		// Configura o layout
		container = getContentPane();
		container.setLayout(layout);
		areaTexto = new JTextArea(10, 35);
		areaTexto.setText(texto);
		areaTexto.setLineWrap(true);
		areaTexto.setWrapStyleWord(true);
		areaTexto.setEditable(false);
		areaTexto.setFocusable(false);
		areaTexto.setBackground(null);
		areaTexto.setCaretPosition(0);
		gravura1.setToolTipText(nome[0]);
		gravura2.setToolTipText(nome[1]);

		scroll = new JScrollPane(areaTexto);
		scrollVertical = scroll.getVerticalScrollBar();
		// Adiciona os componentes ao container
		// Rotulos e campos de edicao
		container.add(parabens, new GridBagConstraints(1, 0, 2, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(
						0, 13, 15, 0), 0, 0));
		container.add(gravura1, new GridBagConstraints(0, 1, 2, 1, 0, 0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 100), 0, 0));
		container.add(gravura2, new GridBagConstraints(2, 1, 2, 1, 0, 0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 22), 0, 0));
		container.add(scroll, new GridBagConstraints(0, 2, 5, 1, 0, 0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(
						25, 17, 0, 10), 0, 0));
		container.add(botaoVoltar, new GridBagConstraints(1, 3, 1, 1, 0, 0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						20, 150, 17, 0), 0, 0));

		// Registra o tratador de eventos
		GerenciadorGeral gerGeral = new GerenciadorGeral();
		botaoVoltar.addActionListener(gerGeral);

		// Define as configuracoes gerais
		setTitle(tituloJanela);
		gravura1.setBorderPainted(false);
		gravura1.setContentAreaFilled(false);
		gravura2.setBorderPainted(false);
		gravura2.setContentAreaFilled(false);
		gravura1.setFocusPainted(false);
		gravura2.setFocusPainted(false);
		// parabens.setBorderPainted(false);
		// parabens.setContentAreaFilled(false);
		// parabens.setFocusPainted(false);
		// parabens.setFont();
		gravura1.setVerticalTextPosition(JButton.BOTTOM);
		gravura1.setHorizontalTextPosition(JButton.CENTER);
		gravura2.setVerticalTextPosition(JButton.BOTTOM);
		gravura2.setHorizontalTextPosition(JButton.CENTER);
		// container.setBackground(Color.LIGHT_GRAY);
		parabens.setFont(new Font("Currier New", Font.ITALIC, 25));
		parabens.setForeground(Color.RED);

		// scroll.add(texto);
		setResizable(false);
		setSize(448, 500);
		setLocation(300, 100);
		setVisible(true);

	}

	/**
	 * @author Rafael Brundo Uriarte e Sergio Piazza Borges Filho
	 *Classe para tratamento de eventos
	 */
	private class GerenciadorGeral implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			if (evento.getSource() == botaoVoltar)
				dispose();
		}
	}
}
