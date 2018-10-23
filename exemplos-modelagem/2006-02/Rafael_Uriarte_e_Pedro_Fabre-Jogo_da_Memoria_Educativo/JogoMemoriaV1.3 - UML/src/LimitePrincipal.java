import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LimitePrincipal {
	
	AtorJogador ator;
	
	String dicaPadrao = "Clique para virar a imagem! :)";
	
	String imagemPadrao = "sistema/FiguraPadrao/imagem0";
	
	// Declara o menu
	private JMenuBar barraMenu;

	// Declara topicos do menu
	private JMenu menuJogo, menuAjuda;

	// Declara botoes do menu
	private JMenuItem botaoNovo, botaoRecordes, botaoNivel, botaoSair,
			botaoSobre;

	// Declara um frame
	private JFrame frame;

	// Resolucao
	private int[] resolucao;

	// Localizacao da janela
	private int[] location;
	
	private JButton [] figuras;

	// Configura a GUI
	/**
	 * @param numeroParesTemp -
	 *            Recebe o numero de pares do jogo da memoria
	 * @param resolucaoTemp -
	 *            Recebe a resolucao do jogo
	 * @param locationTemp -
	 *            Recebe a localizacao correta da janela
	 */
	public LimitePrincipal(int[] informacoes,AtorJogador ator) {
		int pares = informacoes[0];
		this.ator = ator;
		resolucao = new int[2];
		location = new int[2];
		resolucao[0] = informacoes[1];
		resolucao[1] = informacoes[2];
		location[0] = informacoes[3];
		location[1] = informacoes[4];
		criaMenu();
		criaFrame();
		figuras = new JButton[pares*2];
		for (int x = 0; x < pares*2; x++) {
			adicionaFiguras(x);
		}
		finalizaMenu();
	}

	/**
	 * Metodo para cirar o frame
	 */
	private void criaFrame() {
		// Instancia um Frame
		frame = new JFrame();
		// Seta titulo da Janela
		frame.setTitle("Jogo da Memoria");
		// Seta operacao padrao no fechamento da janela
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Declara e instancia um container
		Container c = frame.getContentPane();
		// Define o layout do container
		c.setLayout(new FlowLayout());
	}

	/**
	 * Metodo para fechar o menu(nao aceita mais modificacoes)
	 */
	private void finalizaMenu() {
		frame.setJMenuBar(barraMenu);
		frame.pack();
		frame.setSize(resolucao[0], resolucao[1]);
		frame.setLocation(location[0], location[1]);
		frame.setVisible(true);
	}

	private void criaMenu() {

		// Instancia um menu
		barraMenu = new JMenuBar();

		// Cria os topicos
		menuJogo = new JMenu("Jogo");
		menuAjuda = new JMenu("Sobre");

		// Seta atalhos, ALT + LETRA(' ')
		menuJogo.setMnemonic('J');
		menuAjuda.setMnemonic('A');

		// Adiciona Topicos no menu
		barraMenu.add(menuJogo);
		barraMenu.add(menuAjuda);

		// Cria os botoes do menu
		botaoNovo = new JMenuItem("Novo Jogo");
		botaoRecordes = new JMenuItem("Recordes");
		botaoNivel = new JMenuItem("Nivel");
		botaoSair = new JMenuItem("Sair");
		botaoSobre = new JMenuItem("Sobre o Jogo");

		// Seta atalhos quando o topico esta aberto. ALT + (' ')
		botaoNovo.setMnemonic('N');
		botaoRecordes.setMnemonic('R');
		botaoNivel.setMnemonic('N');
		botaoSair.setMnemonic('S');
		botaoSobre.setMnemonic('S');

		// Adiciona os itens para o menu de Cadastro
		menuJogo.add(botaoNovo);
		menuJogo.add(botaoRecordes);
		menuJogo.add(botaoNivel);
		menuJogo.addSeparator();
		menuJogo.add(botaoSair);

		// Adiciona o itens sobre para o menu Ajuda
		menuAjuda.add(botaoSobre);

		// Registra os tratadores de evento
		Gerenciador gerente = new Gerenciador();

		// Adiciona Lister com o tratador na classe GERENTE
		botaoNovo.addActionListener(gerente);
		botaoRecordes.addActionListener(gerente);
		botaoNivel.addActionListener(gerente);
		botaoSair.addActionListener(gerente);
		botaoSobre.addActionListener(gerente);
	}

	/**
	 * @param nome -
	 *            o numero da figura para tratar a jogada
	 */
	private void adicionaFiguras(int numero) {
		// Cria um icone com a imagem com a imagem padrao(a carta sempre comeca
		// virada)
		Icon imagem = new ImageIcon(imagemPadrao);
		// Cria um botao com o icone
		figuras[numero] = new JButton(imagem);
		figuras[numero].setName(Integer.toString(numero));
		// Tira a pintura de envolta do icone
		figuras[numero].setBorderPainted(false);
		// Tira a borda do icone
		figuras[numero].setContentAreaFilled(false);
		figuras[numero].setToolTipText(dicaPadrao);
		// Cria objeto da inner class para tratar eventos
		TratadorEventos eventos = new TratadorEventos();
		// Se tiver evento action manda para o objeto eventos da classe
		// TratadorEventos
		figuras[numero].addActionListener(eventos);
		frame.add(figuras[numero]);
	}

	/**
	 * Metodo para criar um novo jogo
	 */
	public void novoJogo() {
		frame.dispose();
	}

	public void resetarInterface(int pares){
			for ( int counter = 0; counter < pares; counter = counter + 2){
				this.desvirarFigura(counter, counter+1);		
			}
	}
	
	public void virarFigura(int posicaoClick, String[] informacoes){
		figuras[posicaoClick].setIcon(new ImageIcon(informacoes[0]));
		figuras[posicaoClick].setToolTipText(informacoes[1]);
	}
	public void desvirarFigura(int posicao1,int posicao2){
		figuras[posicao1].setIcon(new ImageIcon(imagemPadrao));
		figuras[posicao2].setIcon(new ImageIcon(imagemPadrao));
		figuras[posicao1].setToolTipText(dicaPadrao);
		figuras[posicao2].setToolTipText(dicaPadrao);
	}
	private class Gerenciador implements ActionListener {

		// processa eventos de campos de texto
		public void actionPerformed(ActionEvent evento) {

			// Variaveis de texto para o botao sobre no menu
			String nomeJogo = "Jogo Da Memoria";
			String versaoJogo = "Versao 0.81";
			String buildJogo = "(build 00000000)";
			String programadorUm = "Pedro Paulo Fabre Junior";
			String programadorDois = "Rafael Brundo Uriarte";
			String biologoUm = "Gustavo Hassemer";
			String biologoDois = "Ticiana Farias";

			// Se clicar no botao "Novo" ...
			if (evento.getSource() == botaoNovo) {
				ator.novoJogo(false);
			}

			if (evento.getSource() == botaoRecordes) {
				ator.telaRecordes();
			}
			if (evento.getSource() == botaoNivel) {
				ator.telaNivel();
			}

			// Se clicar no botao "Sair" ele sai do jogo
			if (evento.getSource() == botaoSair) {
				System.exit(0);
			}
			// Se clicar no botao "Sobre" aparece janela
			if (evento.getSource() == botaoSobre) {
				// Cria janela
				if (evento.getSource() == botaoSobre) {
					Icon iconeFigura = new ImageIcon("imagens/Flamey");
					// Cria janela
					JOptionPane.showMessageDialog(null, nomeJogo + "\n\n"
							+ "          " + versaoJogo + " " + buildJogo
							+ "\n\n" + "Programadores:" + "\n" + programadorUm
							+ "\n" + programadorDois + "\n\n" + "Biologos:"
							+ "\n" + biologoUm + "\n" + biologoDois,
							"Sobre o Jogo", JOptionPane.PLAIN_MESSAGE,
							iconeFigura);
				}
			}
		}
	}
	public void esconder() {
		frame.dispose();
		
	}
	private class TratadorEventos implements ActionListener {
		// Classe usada para definir o que acontece se o botao for pressionado
		public void actionPerformed(ActionEvent acaoEvento) {
			Object figura = acaoEvento.getSource();
			String source = figura.toString();
			String nome = source.substring(20, 22);
			String nome2 = nome.substring(1);
			if (nome2.compareToIgnoreCase(",") == 0) {
				nome = nome.substring(0, 1);
			}
			ator.jogada(Integer.parseInt(nome));
		}
	}


}
