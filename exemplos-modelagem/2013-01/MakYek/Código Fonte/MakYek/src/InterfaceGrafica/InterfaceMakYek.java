package InterfaceGrafica;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import DominioDoProblema.ImagemTabuleiro;

public class InterfaceMakYek extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2303089895470963630L;

	protected AtorJogador atorJog;
	protected JPanel jContentPane = null;
	protected JLabel mapaPosicao[][] = new JLabel[8][8];
	private JLabel mensagem = null;
	private JMenu menu = null;
	private JMenuBar menuBar = null;
	private JMenuItem menuItemConectar = null;
	private JMenuItem menuItemIniciar = null;
	private JMenuItem menuItemDesconectar = null;

	public InterfaceMakYek() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(764, 680);
		this.setContentPane(getJContentPane());
		this.setTitle("Mak-Yek");
		atorJog = new AtorJogador(this);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			Icon vazia = new ImageIcon(getClass().getResource(
					"posicaoVazia.png"));

			mensagem = new JLabel();
			mensagem.setBounds(new Rectangle(25, 10, 300, 20));
			mensagem.setText("Bem-Vindo");

			int posicaoY = 40;
			for (int i = 0; i < 8; i++) {
				int posicaoX = 20;
				for (int j = 0; j < 8; j++) {
					final Integer iFinal = i;
					final Integer jFinal = j;

					mapaPosicao[i][j] = new JLabel();
					mapaPosicao[i][j].setBounds(new Rectangle(posicaoX,
							posicaoY, 90, 70));
					mapaPosicao[i][j].setIcon(vazia);
					mapaPosicao[i][j]
							.addMouseListener(new java.awt.event.MouseAdapter() {
								public void mouseClicked(
										java.awt.event.MouseEvent e) {
									selecionaPosicao(iFinal, jFinal);
								}
							});
					posicaoX += 88;
				}
				posicaoY += 70;
			}

			menuBar = new JMenuBar();
			menuBar.add(this.getMenu());
			this.setJMenuBar(menuBar);

			jContentPane = new JPanel();
			jContentPane.setLayout(null);

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					jContentPane.add(mapaPosicao[i][j], null);
				}
			}

			jContentPane.add(mensagem, null);
		}
		return jContentPane;
	}

	private JMenu getMenu() {
		if (menu == null) {
			menu = new JMenu();
			menu.setText("Jogo");
			menu.setBounds(new Rectangle(1, 0, 57, 21));
			menu.add(getMenuConectar());
			menu.add(getMenuIniciar());
			menu.add(getMenuDesconectar());

		}
		return menu;
	}

	private JMenuItem getMenuIniciar() {
		if (menuItemIniciar == null) {
			menuItemIniciar = new JMenuItem();
			menuItemIniciar.setText("Iniciar");
			menuItemIniciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					iniciarPartida();
				}
			});
		}
		return menuItemIniciar;
	}

	private JMenuItem getMenuConectar() {
		if (menuItemConectar == null) {
			menuItemConectar = new JMenuItem();
			menuItemConectar.setText("Conectar");
			menuItemConectar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					conectar();
				}
			});
		}
		return menuItemConectar;
	}

	private JMenuItem getMenuDesconectar() {
		if (menuItemDesconectar == null) {
			menuItemDesconectar = new JMenuItem();
			menuItemDesconectar.setText("Desconectar");
			menuItemDesconectar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					desconectar();
				}
			});
		}
		return menuItemDesconectar;
	}

	public void exibirEstado() {
		ImagemTabuleiro estado = atorJog.informarEstado();
		atualizaTela(estado);
	}

	public void conectar() {
		int resultado = atorJog.conectar();
		this.notifica(resultado);
	}

	public String solicitaIdJogador() {
		String nome = "jogador";
		nome = JOptionPane.showInputDialog(this, ("Insira o nome do jogador"));
		return nome;
	}

	public String solicitaIdServidor() {
		String idServidor = ("venus.inf.ufsc.br");
		idServidor = JOptionPane
				.showInputDialog(
						this,
						("Insira o endereco do servidor. Se servidor local escreva localhost"),
						idServidor);
		return idServidor;
	}

	public void notifica(int resultado) {
		switch (resultado) {
		case 0: JOptionPane.showMessageDialog(this, "Conexao ja estabelecida anteriormente"); break;
		case 1:	JOptionPane.showMessageDialog(this, "Falha ao tentar conectar."); break;
		case 2:	JOptionPane.showMessageDialog(this, "Exito em efetuar conexao"); break;
		case 3:	JOptionPane.showMessageDialog(this, "Cancelado iniciar partida"); break;
		case 4:	JOptionPane.showMessageDialog(this,	"Nao ha conexao para iniciar partida");	break;
		case 5:	JOptionPane.showMessageDialog(this, "Exito em iniciar partida"); break;
		case 6:	JOptionPane.showMessageDialog(this, "Posicao selecionada sem peca"); break;
		case 7:	JOptionPane.showMessageDialog(this,	"Peca invalida \nPeca do oponente"); break;
		case 10: JOptionPane.showMessageDialog(this, "Movimento invalido \n Ha peca no caminho"); break;
		case 11: JOptionPane.showMessageDialog(this, "Partida chegou ao fim com derrota"); break;
		case 12: JOptionPane.showMessageDialog(this, "Partida chegou ao fim com vitoria"); break;
		case 13: JOptionPane.showMessageDialog(this, "Partida chegou ao fim com empate"); break;
		case 14: JOptionPane.showMessageDialog(this, "Desconexao com exito"); break;
		case 15: JOptionPane.showMessageDialog(this, "Desconexao sem conexao estabelecida"); break;
		case 16: JOptionPane.showMessageDialog(this, "Desconexao falhou"); break;		
		case 17: JOptionPane.showMessageDialog(this, "Eh sua vez"); break;
		case 18: JOptionPane.showMessageDialog(this, "Nao eh sua vez\nAguarde o lance do outro jogador"); break;
		case 19: JOptionPane.showMessageDialog(this, "Passou a vez!"); break;
		case 20: JOptionPane.showMessageDialog(this, "Partida nao iniciada");
		}

	}

	public void iniciarPartida() {
		int resultado = atorJog.iniciarPartida();
		if(resultado == 4)
			this.notifica(resultado);
	}

	public void desconectar() {
		int resultado = atorJog.desconectar();
		this.notifica(resultado);
	}

	public void selecionaPosicao(int linha, int coluna) {
		int resultado = atorJog.selecionaPosicao(linha, coluna);
		// peca selecionada passou a vez vitoria empate
		if (resultado == 8 || resultado == 19 || resultado == 12
				|| resultado == 13)
			this.exibirEstado();
		if (resultado != 8)// nao precisa notificar se peca selecionda com
			// sucesso
			this.notifica(resultado);
	}

	public void atualizaTela(ImagemTabuleiro estado) {
		int valor;
		Icon branca = new ImageIcon(getClass().getResource("peca-Branca.png"));
		Icon preta = new ImageIcon(getClass().getResource("peca-Preta.png"));
		Icon vazia = new ImageIcon(getClass().getResource("posicaoVazia.png"));
		Icon brancaSelect = new ImageIcon(getClass().getResource(
				"peca-Branca-Selecionada.png"));
		Icon pretaSelect = new ImageIcon(getClass().getResource(
				"peca-Preta-Selecionada.png"));
		mensagem.setText(estado.informarMensagem());
		for (int linha = 0; linha < 8; linha++) {
			for (int coluna = 0; coluna < 8; coluna++) {
				valor = estado.informarValor(linha, coluna);

				switch (valor) {
				case 0:
					mapaPosicao[(linha)][(coluna)].setIcon(vazia);
					break;
				case 1:
					mapaPosicao[(linha)][(coluna)].setIcon(branca);
					break;
				case 2:
					mapaPosicao[(linha)][(coluna)].setIcon(preta);
					break;
				case 3:
					mapaPosicao[(linha)][(coluna)].setIcon(brancaSelect);
					break;
				case 4:
					mapaPosicao[(linha)][(coluna)].setIcon(pretaSelect);
				}
			}
		}
	}

	public void desejaIniciarPartida() {
		int resposta = JOptionPane.showConfirmDialog(null,
				"Deseja iniciar partida?");
		if (resposta == 0)
			confirmaInicioPartida();
		else
			cancelaInicioPartida();
	}

	public void confirmaInicioPartida() {
		iniciarPartida();
	}

	public void cancelaInicioPartida() {
		int resultado = atorJog.cancelaInicioPartida();
		this.notifica(resultado);
	}
}
