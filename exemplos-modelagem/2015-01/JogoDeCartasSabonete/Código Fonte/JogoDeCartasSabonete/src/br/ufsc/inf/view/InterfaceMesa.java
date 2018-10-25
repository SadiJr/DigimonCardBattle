package br.ufsc.inf.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.net.URL;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import br.ufsc.inf.Constantes;
import br.ufsc.inf.model.Carta;
import br.ufsc.inf.model.EstadoMesa;

public class InterfaceMesa extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String NAO_E_A_SUA_VEZ = "Não é a sua vez";
	private static final String LIXO = "Lixo";
	private static final String JOGADA_INVALIDA = "Jogada inválida. Continue jogando!";
	private static final String VOCE_PERDEU = "Parece que seu adversário é melhor do que você. Você perdeu a partida! :´(";
	private static final String VOCE_VENCEU = "Parabéns, você venceu a partida. Você é imbatível! \\o/";
	private static final String PARTIDA_ENCERRADA = "Partida encerrada";
	private static final String FALHA_AO_INICIALIZAR_PARTIDA = "Falha ao inicializar partida";
	private static final String PARTIDA_EM_ANDAMENTO = "Partida em andamento";
	private static final String INICIO_NAO_CONECTADO = "Tentativa de início sem conexão previamente estabelecida";
	private static final String PARTIDA_INICIADA = "Partida Iniciada. Bom Jogo!";
	private static final String DESCONEXAO_FALHOU = "Tentativa de desconexão falhou";
	private static final String JA_DESCONECTADO = "Tentativa de desconexão sem conexão previamente estabelecida";
	private static final String DESCONEXAO_EXITO = "Desconexão efetuada com êxito";
	private static final String CONEXAO_FALHOU = "Tentativa de conexão falhou";
	private static final String CONEXAO_JA_ESTABELECIDA = "Tentativa de conexão com conexão previamente estabelecida";
	private static final String CONEXAO_EFETUADA_COM_EXITO = "Conexão efetuada com êxito";
	private static final String MONTE = "Monte";
	private static final String MESA = "Mesa ";
	private static final String AGUARDANDO_ESCOLHA_ADVERSARIO = "Aguardando que o servidor selecione um adversário . . .";
	private static final String SEPARADOR_TEXTO = " | ";
	private static final int FONT_SIZE = 11;
	private static final String FONTE_TAHOMA = "Tahoma";
	private static final String MENU_SAIR = "Sair";
	private static final String MENU_DESCONECTAR = "Desconectar";
	private static final String MENU_CONECTAR = "Conectar";
	private static final String MENU_INICIAR_NOVA_PARTIDA = "Iniciar Nova Partida";
	private static final String MENU_JOGO = "Jogo";
	private static final String MINHA_MESA = "Minha Mesa";
	private static final String MESA_ADVERSARIO = "Mesa Adversário";
	private static final String CARTA_DA_MAO = "Carta da Mão";
	private static final String EXTENSAO_IMAGENS = ".png";
	private static final String IMG_MONTE = "monte";
	private static final String IMG_CARTA_OCULTA = "carta-oculta";
	private static final String INSIRA_O_ENDERECO_DO_SERVIDOR = "Insira o endereço do servidor";
	private static final String SERVIDOR_PADRAO = "venus.inf.ufsc.br";
	private static final String INSIRA_O_NOME_DO_JOGADOR = "Insira o nome do jogador";
	private static final String TITULO_JANELA = "Jogo de Cartas Sabonete";
	private static final String NAO_CONECTADO = "Não conectado ao servidor";
	private static final String NAO_INICIADO = "Jogo não iniciado";
	private static final String NAO_CONECTADO_E_NAO_INICIADO = NAO_CONECTADO
			+ SEPARADOR_TEXTO + NAO_INICIADO;
	private static final String SUA_VEZ = "É a sua vez de jogar.";
	private static final String VEZ_ADVERSARIO = "Aguarde a jogada do adversário . . .";

	private JPanel jContentPane = null;

	private JMenuBar jMenuBar = null;
	private JMenu jMenuJogo = null;
	private JMenuItem jMenuItemIniciar = null;
	private JMenuItem jMenuItemConectar = null;
	private JMenuItem jMenuItemDesconectar = null;
	private JMenuItem jMenuItemSair = null;

	private JPanel statusPanel;
	private JPanel panelLixo;
	private JPanel panelMonte;
	private JLabel monte;
	private JLabel lixo;
	private JLabel cartaMao;

	private JPanel panelMesa1;
	private JLabel mesa1pos1;
	private JLabel mesa1pos2;
	private JLabel mesa1pos3;
	private JLabel mesa1pos4;
	private JLabel mesa1pos5;
	private JLabel mesa1pos6;
	private JLabel mesa1pos7;
	private JLabel mesa1pos8;
	private JLabel mesa1pos9;
	private JLabel mesa1pos10;
	private JLabel mesa1pos11;
	private JLabel mesa1pos12;
	private JLabel mesa1pos13;

	private JLabel mesa2pos1;
	private JLabel mesa2pos2;
	private JLabel mesa2pos3;
	private JLabel mesa2pos4;
	private JLabel mesa2pos5;
	private JLabel mesa2pos6;
	private JLabel mesa2pos7;
	private JLabel mesa2pos8;
	private JLabel mesa2pos9;
	private JLabel mesa2pos10;
	private JLabel mesa2pos11;
	private JLabel mesa2pos12;
	private JLabel mesa2pos13;
	private JLabel labelMonte;
	private JLabel labelLixo;
	private JLabel labelCartaDaMao;
	private JLabel labelMesaAdversario;
	private JLabel labelMinhaMesa;
	private JLabel labelStatusJogo;
	private MouseAdapter clicouItemBaralhoListener;

	protected Boolean escolhendoOrigemCarta = true;
	protected Boolean escolhendoDestinoCarta = false;
	protected AtorJogador atorJogador;

	public InterfaceMesa(AtorJogador atorJogador) {
		this.setSize(1080, 656);
		this.setLocationRelativeTo(null);
		this.setContentPane(this.mountJContentPane());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(TITULO_JANELA);
		this.atorJogador = atorJogador;
	}

	public void conectar() {
		this.atorJogador.conectar();
	}
	
	public void desconectar() {
		this.atorJogador.desconectar();
	}

	public String obterJogador() {
		String nomeJogador = "";
		while (nomeJogador.trim().isEmpty()) {
			nomeJogador = JOptionPane.showInputDialog(this,
					INSIRA_O_NOME_DO_JOGADOR);
		}
		this.setTitle(nomeJogador + SEPARADOR_TEXTO + TITULO_JANELA);
		this.labelMinhaMesa.setText(MESA + nomeJogador);
		return nomeJogador;
	}

	public String obterServidor() {
		String enderecoServidor = SERVIDOR_PADRAO;
		enderecoServidor = JOptionPane.showInputDialog(this,
				INSIRA_O_ENDERECO_DO_SERVIDOR, enderecoServidor);
		return enderecoServidor;
	}

	public void atualizaInterface(EstadoMesa estadoMesa, Carta cartaMaoJogador) {
		List<Carta> mesaJ1 = estadoMesa.getMesaJogadorUm();
		List<Carta> mesaJ2 = estadoMesa.getMesaJogadorDois();
		List<Carta> cartasLixo = estadoMesa.getLixo();
		List<Carta> cartasMonte = estadoMesa.getMonte();

		Icon imgCartaOculta = new ImageIcon(
				this.getImagemCarta(IMG_CARTA_OCULTA));
		Icon imgCartaMonte = new ImageIcon(this.getImagemCarta(IMG_MONTE));

		this.mesa1pos1.setIcon(mesaJ1.get(0).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ1.get(0).getNome())) : imgCartaOculta);
		this.mesa1pos2.setIcon(mesaJ1.get(1).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ1.get(1).getNome())) : imgCartaOculta);
		this.mesa1pos3.setIcon(mesaJ1.get(2).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ1.get(2).getNome())) : imgCartaOculta);
		this.mesa1pos4.setIcon(mesaJ1.get(3).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ1.get(3).getNome())) : imgCartaOculta);
		this.mesa1pos5.setIcon(mesaJ1.get(4).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ1.get(4).getNome())) : imgCartaOculta);
		this.mesa1pos6.setIcon(mesaJ1.get(5).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ1.get(5).getNome())) : imgCartaOculta);
		this.mesa1pos7.setIcon(mesaJ1.get(6).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ1.get(6).getNome())) : imgCartaOculta);
		this.mesa1pos8.setIcon(mesaJ1.get(7).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ1.get(7).getNome())) : imgCartaOculta);
		this.mesa1pos9.setIcon(mesaJ1.get(8).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ1.get(8).getNome())) : imgCartaOculta);
		this.mesa1pos10.setIcon(mesaJ1.get(9).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ1.get(9).getNome())) : imgCartaOculta);
		this.mesa1pos11
				.setIcon(mesaJ1.get(10).isRevelada() ? new ImageIcon(this
						.getImagemCarta(mesaJ1.get(10).getNome()))
						: imgCartaOculta);
		this.mesa1pos12
				.setIcon(mesaJ1.get(11).isRevelada() ? new ImageIcon(this
						.getImagemCarta(mesaJ1.get(11).getNome()))
						: imgCartaOculta);
		this.mesa1pos13
				.setIcon(mesaJ1.get(12).isRevelada() ? new ImageIcon(this
						.getImagemCarta(mesaJ1.get(12).getNome()))
						: imgCartaOculta);

		this.mesa2pos1.setIcon(mesaJ2.get(0).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ2.get(0).getNome())) : imgCartaOculta);
		this.mesa2pos2.setIcon(mesaJ2.get(1).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ2.get(1).getNome())) : imgCartaOculta);
		this.mesa2pos3.setIcon(mesaJ2.get(2).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ2.get(2).getNome())) : imgCartaOculta);
		this.mesa2pos4.setIcon(mesaJ2.get(3).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ2.get(3).getNome())) : imgCartaOculta);
		this.mesa2pos5.setIcon(mesaJ2.get(4).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ2.get(4).getNome())) : imgCartaOculta);
		this.mesa2pos6.setIcon(mesaJ2.get(5).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ2.get(5).getNome())) : imgCartaOculta);
		this.mesa2pos7.setIcon(mesaJ2.get(6).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ2.get(6).getNome())) : imgCartaOculta);
		this.mesa2pos8.setIcon(mesaJ2.get(7).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ2.get(7).getNome())) : imgCartaOculta);
		this.mesa2pos9.setIcon(mesaJ2.get(8).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ2.get(8).getNome())) : imgCartaOculta);
		this.mesa2pos10.setIcon(mesaJ2.get(9).isRevelada() ? new ImageIcon(this
				.getImagemCarta(mesaJ2.get(9).getNome())) : imgCartaOculta);
		this.mesa2pos11
				.setIcon(mesaJ2.get(10).isRevelada() ? new ImageIcon(this
						.getImagemCarta(mesaJ2.get(10).getNome()))
						: imgCartaOculta);
		this.mesa2pos12
				.setIcon(mesaJ2.get(11).isRevelada() ? new ImageIcon(this
						.getImagemCarta(mesaJ2.get(11).getNome()))
						: imgCartaOculta);
		this.mesa2pos13
				.setIcon(mesaJ2.get(12).isRevelada() ? new ImageIcon(this
						.getImagemCarta(mesaJ2.get(12).getNome()))
						: imgCartaOculta);

		this.cartaMao.setIcon(cartaMaoJogador != null ? new ImageIcon(this
				.getImagemCarta(cartaMaoJogador.getNome())) : null);

		this.monte.setIcon(!cartasMonte.isEmpty() ? imgCartaMonte : null);

		ImageIcon lixoIcon = null;
		if (cartasLixo != null && !cartasLixo.isEmpty()) {
			lixoIcon = new ImageIcon(this.getImagemCarta(cartasLixo.get(
					cartasLixo.size() - 1).getNome()));
		}
		this.lixo.setIcon(lixoIcon);

		if (!estadoMesa.isTemVencedor()) {
			this.labelStatusJogo.setText(SUA_VEZ);
		}
		this.liberaClicks(estadoMesa);
	}

	public void informarResultado(int resultado) {
		switch (resultado) {
			case Constantes.CONEXAO_EXITO:
				this.labelStatusJogo.setText(NAO_INICIADO);
				JOptionPane.showMessageDialog(this, CONEXAO_EFETUADA_COM_EXITO);
				break;
			case Constantes.JA_CONECTADO:
				JOptionPane.showMessageDialog(this, CONEXAO_JA_ESTABELECIDA);
				break;
			case Constantes.CONEXAO_FALHOU:
				JOptionPane.showMessageDialog(this, CONEXAO_FALHOU);
				break;
			case Constantes.DESCONEXAO_EXITO:
				this.inicializaMesa();
				this.setTitle(TITULO_JANELA);
				this.labelStatusJogo.setText(NAO_CONECTADO_E_NAO_INICIADO);
				JOptionPane.showMessageDialog(this, DESCONEXAO_EXITO);
				break;
			case Constantes.JA_DESCONECTADO:
				JOptionPane.showMessageDialog(this, JA_DESCONECTADO);
				break;
			case Constantes.DESCONEXAO_FALHOU:
				JOptionPane.showMessageDialog(this, DESCONEXAO_FALHOU);
				break;
			case Constantes.SOLICITACAO_INICIO_PARTIDA:
				this.labelStatusJogo.setText(AGUARDANDO_ESCOLHA_ADVERSARIO);
				break;
			case Constantes.PARTIDA_INICIALIZADA:
				boolean primeiro = this.atorJogador.souOPrimeiroJogador();
				String status = primeiro ? SUA_VEZ : VEZ_ADVERSARIO;
				this.labelStatusJogo.setText(status);
				JOptionPane.showMessageDialog(this, PARTIDA_INICIADA);
				break;
			case Constantes.NAO_CONECTADO:
				JOptionPane.showMessageDialog(this, INICIO_NAO_CONECTADO);
				break;
			case Constantes.PARTIDA_ANDAMENTO:
				JOptionPane.showMessageDialog(this, PARTIDA_EM_ANDAMENTO);
				break;
			case Constantes.PARTIDA_FALHOU:
				JOptionPane.showMessageDialog(this,
						FALHA_AO_INICIALIZAR_PARTIDA);
				break;
			case Constantes.NAO_E_VEZ:
				JOptionPane.showMessageDialog(this, NAO_E_A_SUA_VEZ);
				break;
			case Constantes.PARTIDA_FINALIZADA:
				this.labelStatusJogo.setText(NAO_INICIADO);
				JOptionPane.showMessageDialog(this, PARTIDA_ENCERRADA);
				break;
			case Constantes.JOGADA_OK_VENCEU_PARTIDA:
				this.labelStatusJogo.setText(NAO_INICIADO);
				this.labelMesaAdversario.setText(MESA_ADVERSARIO);
				JOptionPane.showMessageDialog(this, VOCE_VENCEU);
				break;
			case Constantes.ADVERSARIO_VENCEU_PARTIDA:
				this.labelStatusJogo.setText(NAO_INICIADO);
				this.labelMesaAdversario.setText(MESA_ADVERSARIO);
				JOptionPane.showMessageDialog(this, VOCE_PERDEU);
				break;
			case Constantes.JOGADA_OK_TURNO_ENCERRADO:
				this.labelStatusJogo.setText(VEZ_ADVERSARIO);
				break;
			case Constantes.JOGADA_INVALIDA_TURNO_CONTINUA:
				JOptionPane.showMessageDialog(this, JOGADA_INVALIDA);
				break;
			default:
				break;
		}
	}

	public void inicializaMesa() {

		this.escolhendoOrigemCarta = true;
		this.escolhendoDestinoCarta = !this.escolhendoOrigemCarta;

		this.monte.setIcon(new ImageIcon(this.getImagemCarta(IMG_MONTE)));

		Icon cartaOculta = new ImageIcon(this.getImagemCarta(IMG_CARTA_OCULTA));

		this.mesa1pos1.setIcon(cartaOculta);

		this.mesa1pos2.setIcon(cartaOculta);

		this.mesa1pos3.setIcon(cartaOculta);

		this.mesa1pos4.setIcon(cartaOculta);

		this.mesa1pos5.setIcon(cartaOculta);

		this.mesa1pos6.setIcon(cartaOculta);

		this.mesa1pos7.setIcon(cartaOculta);

		this.mesa1pos8.setIcon(cartaOculta);

		this.mesa1pos9.setIcon(cartaOculta);

		this.mesa1pos10.setIcon(cartaOculta);

		this.mesa1pos11.setIcon(cartaOculta);

		this.mesa1pos12.setIcon(cartaOculta);

		this.mesa1pos13.setIcon(cartaOculta);

		this.mesa2pos1.setIcon(cartaOculta);
		this.mesa2pos2.setIcon(cartaOculta);
		this.mesa2pos3.setIcon(cartaOculta);
		this.mesa2pos4.setIcon(cartaOculta);
		this.mesa2pos5.setIcon(cartaOculta);
		this.mesa2pos6.setIcon(cartaOculta);
		this.mesa2pos7.setIcon(cartaOculta);
		this.mesa2pos8.setIcon(cartaOculta);
		this.mesa2pos9.setIcon(cartaOculta);
		this.mesa2pos10.setIcon(cartaOculta);
		this.mesa2pos11.setIcon(cartaOculta);
		this.mesa2pos12.setIcon(cartaOculta);
		this.mesa2pos13.setIcon(cartaOculta);
	}

	public void atualizaFluxoJogada() {
		this.escolhendoOrigemCarta = !this.escolhendoOrigemCarta;
		this.escolhendoDestinoCarta = !this.escolhendoDestinoCarta;
	}

	public void click(int pos) {
		if (this.isEscolhendoOrigemCarta()) {
			this.atorJogador.clickCartaMao(pos);
		} else if (this.isEscolhendoDestinoCarta()) {
			this.atorJogador.clickCartaDestino(pos);
		}
	}
	
	private void liberaClicks(EstadoMesa estadoMesa) {
		boolean minhaVez = this.atorJogador.minhaVez();
		boolean temNoMonte = !estadoMesa.getMonte().isEmpty();
		boolean temNoLixo = !estadoMesa.getLixo().isEmpty();

		this.jContentPane.setEnabled(minhaVez
				&& (this.escolhendoOrigemCarta || this.escolhendoDestinoCarta));
		this.panelLixo
				.setEnabled(minhaVez
						&& ((this.escolhendoOrigemCarta && temNoLixo) || this.escolhendoDestinoCarta));
		this.panelMonte.setEnabled(minhaVez && this.escolhendoOrigemCarta
				&& temNoMonte);
		this.panelMesa1.setEnabled(minhaVez && this.escolhendoDestinoCarta);
	}

	public void atualizarNomeAdversario(String nomeAdversario) {
		this.labelMesaAdversario.setText(MESA + nomeAdversario);
	}

	public Boolean isEscolhendoOrigemCarta() {
		return escolhendoOrigemCarta;
	}

	public Boolean isEscolhendoDestinoCarta() {
		return escolhendoDestinoCarta;
	}

	private URL getImagemCarta(String nome) {
		return InterfaceMesa.class.getResource(Constantes.IMAGE_PATH + nome
				+ EXTENSAO_IMAGENS);
	}

	private JPanel mountJContentPane() {
		if (this.jContentPane == null) {
			
			this.clicouItemBaralhoListener = new MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					JLabel item = (JLabel) e.getSource();

					// verifica se o click do item esta habilitado
					if ((item.equals(InterfaceMesa.this.lixo) && !InterfaceMesa.this.panelLixo
							.isEnabled())
							|| (item.equals(InterfaceMesa.this.monte) && !InterfaceMesa.this.panelMonte
									.isEnabled())
							|| (!item.equals(InterfaceMesa.this.lixo)
									&& !item.equals(InterfaceMesa.this.monte) && !InterfaceMesa.this.panelMesa1
										.isEnabled())) {
						return;
					} else {
						InterfaceMesa.this.click(new Integer(item.getName()));
					}
				}
			};

			this.jMenuBar = new JMenuBar();
			this.jMenuBar.add(this.getMenu());
			this.setJMenuBar(this.jMenuBar);

			this.jContentPane = new JPanel();
			this.jContentPane.setLayout(null);
			this.jContentPane.setEnabled(false);

			this.mesa1pos1 = new JLabel();
			this.mesa1pos1.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.mesa1pos1.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa1pos1.setBackground(Color.WHITE);
			this.mesa1pos1.setOpaque(true);
			this.mesa1pos1.setSize(63, 81);
			this.mesa1pos1.setLocation(54, 11);
			this.mesa1pos1.setName(Constantes.DESTINO_CARTA_UM_MESA.toString());

			this.mesa1pos2 = new JLabel();
			this.mesa1pos2.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.mesa1pos2.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa1pos2.setBackground(Color.WHITE);
			this.mesa1pos2.setOpaque(true);
			this.mesa1pos2.setSize(63, 81);
			this.mesa1pos2.setLocation(127, 11);
			this.mesa1pos2.setName(Constantes.DESTINO_CARTA_DOIS_MESA
					.toString());

			this.mesa1pos3 = new JLabel();
			this.mesa1pos3.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.mesa1pos3.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa1pos3.setBackground(Color.WHITE);
			this.mesa1pos3.setOpaque(true);
			this.mesa1pos3.setSize(63, 81);
			this.mesa1pos3.setLocation(200, 11);
			this.mesa1pos3.setName(Constantes.DESTINO_CARTA_TRES_MESA
					.toString());

			this.mesa1pos4 = new JLabel();
			this.mesa1pos4.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.mesa1pos4.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa1pos4.setBackground(Color.WHITE);
			this.mesa1pos4.setOpaque(true);
			this.mesa1pos4.setSize(63, 81);
			this.mesa1pos4.setLocation(273, 11);
			this.mesa1pos4.setName(Constantes.DESTINO_CARTA_QUATRO_MESA
					.toString());

			this.mesa1pos5 = new JLabel();
			this.mesa1pos5.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.mesa1pos5.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa1pos5.setBackground(Color.WHITE);
			this.mesa1pos5.setOpaque(true);
			this.mesa1pos5.setSize(63, 81);
			this.mesa1pos5.setLocation(346, 11);
			this.mesa1pos5.setName(Constantes.DESTINO_CARTA_CINCO_MESA
					.toString());

			this.mesa1pos6 = new JLabel();
			this.mesa1pos6.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.mesa1pos6.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa1pos6.setBackground(Color.WHITE);
			this.mesa1pos6.setOpaque(true);
			this.mesa1pos6.setSize(63, 81);
			this.mesa1pos6.setLocation(419, 11);
			this.mesa1pos6.setName(Constantes.DESTINO_CARTA_SEIS_MESA
					.toString());

			this.mesa1pos7 = new JLabel();
			this.mesa1pos7.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.mesa1pos7.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa1pos7.setBackground(Color.WHITE);
			this.mesa1pos7.setOpaque(true);
			this.mesa1pos7.setSize(63, 81);
			this.mesa1pos7.setLocation(492, 11);
			this.mesa1pos7.setName(Constantes.DESTINO_CARTA_SETE_MESA
					.toString());

			this.mesa1pos8 = new JLabel();
			this.mesa1pos8.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.mesa1pos8.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa1pos8.setBackground(Color.WHITE);
			this.mesa1pos8.setOpaque(true);
			this.mesa1pos8.setSize(63, 81);
			this.mesa1pos8.setLocation(565, 11);
			this.mesa1pos8.setName(Constantes.DESTINO_CARTA_OITO_MESA
					.toString());

			this.mesa1pos9 = new JLabel();
			this.mesa1pos9.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.mesa1pos9.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa1pos9.setBackground(Color.WHITE);
			this.mesa1pos9.setOpaque(true);
			this.mesa1pos9.setSize(63, 81);
			this.mesa1pos9.setLocation(638, 11);
			this.mesa1pos9.setName(Constantes.DESTINO_CARTA_NOVE_MESA
					.toString());

			this.mesa1pos10 = new JLabel();
			this.mesa1pos10.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.mesa1pos10.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa1pos10.setBackground(Color.WHITE);
			this.mesa1pos10.setOpaque(true);
			this.mesa1pos10.setSize(63, 81);
			this.mesa1pos10.setLocation(711, 11);
			this.mesa1pos10.setName(Constantes.DESTINO_CARTA_DEZ_MESA
					.toString());

			this.mesa1pos11 = new JLabel();
			this.mesa1pos11.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.mesa1pos11.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa1pos11.setBackground(Color.WHITE);
			this.mesa1pos11.setOpaque(true);
			this.mesa1pos11.setSize(63, 81);
			this.mesa1pos11.setLocation(784, 11);
			this.mesa1pos11.setName(Constantes.DESTINO_CARTA_ONZE_MESA
					.toString());

			this.mesa1pos12 = new JLabel();
			this.mesa1pos12.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.mesa1pos12.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa1pos12.setBackground(Color.WHITE);
			this.mesa1pos12.setOpaque(true);
			this.mesa1pos12.setSize(63, 81);
			this.mesa1pos12.setLocation(857, 11);
			this.mesa1pos12.setName(Constantes.DESTINO_CARTA_DOZE_MESA
					.toString());

			this.mesa1pos13 = new JLabel();
			this.mesa1pos13.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.mesa1pos13.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa1pos13.setBackground(Color.WHITE);
			this.mesa1pos13.setOpaque(true);
			this.mesa1pos13.setSize(63, 81);
			this.mesa1pos13.setLocation(930, 11);
			this.mesa1pos13.setName(Constantes.DESTINO_CARTA_TREZE_MESA
					.toString());

			this.panelMesa1 = new JPanel();
			this.panelMesa1.setSize(1044, 141);
			this.panelMesa1.setBorder(null);
			this.panelMesa1.setLocation(10, 419);
			this.panelMesa1.setLayout(null);

			this.panelMesa1.add(this.mesa1pos1);
			this.panelMesa1.add(this.mesa1pos2);
			this.panelMesa1.add(this.mesa1pos3);
			this.panelMesa1.add(this.mesa1pos4);
			this.panelMesa1.add(this.mesa1pos5);
			this.panelMesa1.add(this.mesa1pos6);
			this.panelMesa1.add(this.mesa1pos7);
			this.panelMesa1.add(this.mesa1pos8);
			this.panelMesa1.add(this.mesa1pos9);
			this.panelMesa1.add(this.mesa1pos10);
			this.panelMesa1.add(this.mesa1pos11);
			this.panelMesa1.add(this.mesa1pos12);
			this.panelMesa1.add(this.mesa1pos13);

			this.jContentPane.add(this.panelMesa1);

			this.mesa2pos1 = new JLabel();
			this.mesa2pos1.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa2pos1.setBackground(Color.WHITE);
			this.mesa2pos1.setOpaque(true);
			this.mesa2pos1.setSize(63, 81);
			this.mesa2pos1.setLocation(64, 37);

			this.mesa2pos2 = new JLabel();
			this.mesa2pos2.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa2pos2.setBackground(Color.WHITE);
			this.mesa2pos2.setOpaque(true);
			this.mesa2pos2.setSize(63, 81);
			this.mesa2pos2.setLocation(137, 37);

			this.mesa2pos3 = new JLabel();
			this.mesa2pos3.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa2pos3.setBackground(Color.WHITE);
			this.mesa2pos3.setOpaque(true);
			this.mesa2pos3.setSize(63, 81);
			this.mesa2pos3.setLocation(210, 37);

			this.mesa2pos4 = new JLabel();
			this.mesa2pos4.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa2pos4.setBackground(Color.WHITE);
			this.mesa2pos4.setOpaque(true);
			this.mesa2pos4.setSize(63, 81);
			this.mesa2pos4.setLocation(283, 37);

			this.mesa2pos5 = new JLabel();
			this.mesa2pos5.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa2pos5.setBackground(Color.WHITE);
			this.mesa2pos5.setOpaque(true);
			this.mesa2pos5.setSize(63, 81);
			this.mesa2pos5.setLocation(356, 37);

			this.mesa2pos6 = new JLabel();
			this.mesa2pos6.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa2pos6.setBackground(Color.WHITE);
			this.mesa2pos6.setOpaque(true);
			this.mesa2pos6.setSize(63, 81);
			this.mesa2pos6.setLocation(429, 37);

			this.mesa2pos7 = new JLabel();
			this.mesa2pos7.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa2pos7.setBackground(Color.WHITE);
			this.mesa2pos7.setOpaque(true);
			this.mesa2pos7.setSize(63, 81);
			this.mesa2pos7.setLocation(502, 37);

			this.mesa2pos8 = new JLabel();
			this.mesa2pos8.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa2pos8.setBackground(Color.WHITE);
			this.mesa2pos8.setOpaque(true);
			this.mesa2pos8.setSize(63, 81);
			this.mesa2pos8.setLocation(575, 37);

			this.mesa2pos9 = new JLabel();
			this.mesa2pos9.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa2pos9.setBackground(Color.WHITE);
			this.mesa2pos9.setOpaque(true);
			this.mesa2pos9.setSize(63, 81);
			this.mesa2pos9.setLocation(648, 37);

			this.mesa2pos10 = new JLabel();
			this.mesa2pos10.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa2pos10.setBackground(Color.WHITE);
			this.mesa2pos10.setOpaque(true);
			this.mesa2pos10.setSize(63, 81);
			this.mesa2pos10.setLocation(721, 37);

			this.mesa2pos11 = new JLabel();
			this.mesa2pos11.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa2pos11.setBackground(Color.WHITE);
			this.mesa2pos11.setOpaque(true);
			this.mesa2pos11.setSize(63, 81);
			this.mesa2pos11.setLocation(794, 37);

			this.mesa2pos12 = new JLabel();
			this.mesa2pos12.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa2pos12.setBackground(Color.WHITE);
			this.mesa2pos12.setOpaque(true);
			this.mesa2pos12.setSize(63, 81);
			this.mesa2pos12.setLocation(867, 37);

			this.mesa2pos13 = new JLabel();
			this.mesa2pos13.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.mesa2pos13.setBackground(Color.WHITE);
			this.mesa2pos13.setOpaque(true);
			this.mesa2pos13.setSize(63, 81);
			this.mesa2pos13.setLocation(940, 37);

			this.jContentPane.add(this.mesa2pos1);
			this.jContentPane.add(this.mesa2pos2);
			this.jContentPane.add(this.mesa2pos3);
			this.jContentPane.add(this.mesa2pos4);
			this.jContentPane.add(this.mesa2pos5);
			this.jContentPane.add(this.mesa2pos6);
			this.jContentPane.add(this.mesa2pos7);
			this.jContentPane.add(this.mesa2pos8);
			this.jContentPane.add(this.mesa2pos9);
			this.jContentPane.add(this.mesa2pos10);
			this.jContentPane.add(this.mesa2pos11);
			this.jContentPane.add(this.mesa2pos12);
			this.jContentPane.add(this.mesa2pos13);

			this.monte = new JLabel();
			this.monte.setBounds(62, 30, 85, 81);
			this.monte
					.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.monte.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.monte.setBackground(Color.WHITE);
			this.monte.setOpaque(true);
			this.monte.setName(Constantes.ORIGEM_CARTA_MONTE.toString());

			this.labelMonte = new JLabel(MONTE);
			this.labelMonte.setBounds(62, 116, 46, 14);
			this.labelMonte
					.setFont(new Font(FONTE_TAHOMA, Font.BOLD, FONT_SIZE));

			this.panelMonte = new JPanel();
			this.panelMonte.setBounds(210, 189, 209, 141);
			this.panelMonte.setLayout(null);
			this.panelMonte.add(this.monte);
			this.panelMonte.add(this.labelMonte);
			this.jContentPane.add(this.panelMonte);

			this.lixo = new JLabel();
			this.lixo.setBounds(73, 30, 63, 81);
			this.lixo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.lixo.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.lixo.setBackground(Color.WHITE);
			this.lixo.setOpaque(true);
			this.lixo.setName(Constantes.ORIGEM_DESTINO_LIXO.toString());

			this.labelLixo = new JLabel(LIXO);
			this.labelLixo.setBounds(73, 114, 46, 14);
			this.labelLixo
					.setFont(new Font(FONTE_TAHOMA, Font.BOLD, FONT_SIZE));

			this.panelLixo = new JPanel();
			this.panelLixo.setBounds(648, 189, 209, 141);
			this.panelLixo.setLayout(null);
			this.panelLixo.add(this.lixo);
			this.panelLixo.add(this.labelLixo);
			this.jContentPane.add(this.panelLixo);

			this.cartaMao = new JLabel();
			this.cartaMao.setBorder(new LineBorder(new Color(0, 0, 0)));
			this.cartaMao.setBackground(Color.WHITE);
			this.cartaMao.setOpaque(true);
			this.cartaMao.setSize(63, 81);
			this.cartaMao.setLocation(502, 302);
			this.cartaMao.setName(Constantes.ORIGEM_CARTA_MAO.toString());

			this.jContentPane.add(this.cartaMao);

			this.labelCartaDaMao = new JLabel(CARTA_DA_MAO);
			this.labelCartaDaMao.setFont(new Font(FONTE_TAHOMA, Font.BOLD,
					FONT_SIZE));
			this.labelCartaDaMao.setBounds(502, 386, 90, 14);
			this.jContentPane.add(this.labelCartaDaMao);

			this.labelMesaAdversario = new JLabel(MESA_ADVERSARIO);
			this.labelMesaAdversario.setFont(new Font(FONTE_TAHOMA, Font.BOLD,
					FONT_SIZE));
			this.labelMesaAdversario.setBounds(64, 129, 107, 14);
			this.jContentPane.add(this.labelMesaAdversario);

			this.labelMinhaMesa = new JLabel(MINHA_MESA);
			this.labelMinhaMesa.setBounds(54, 103, 90, 14);
			this.labelMinhaMesa.setFont(new Font(FONTE_TAHOMA, Font.BOLD,
					FONT_SIZE));
			this.panelMesa1.add(this.labelMinhaMesa);

			this.statusPanel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) this.statusPanel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			this.statusPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
					null, null));
			this.statusPanel.setBounds(-1, 571, 1067, 25);
			this.jContentPane.add(this.statusPanel);

			this.labelStatusJogo = new JLabel(NAO_CONECTADO_E_NAO_INICIADO);
			this.labelStatusJogo.setForeground(Color.BLUE);
			this.labelStatusJogo.setFont(new Font(FONTE_TAHOMA, Font.BOLD,
					FONT_SIZE));
			this.statusPanel.add(this.labelStatusJogo);

			this.monte.addMouseListener(this.clicouItemBaralhoListener);
			this.lixo.addMouseListener(this.clicouItemBaralhoListener);
			this.mesa1pos1.addMouseListener(this.clicouItemBaralhoListener);
			this.mesa1pos2.addMouseListener(this.clicouItemBaralhoListener);
			this.mesa1pos3.addMouseListener(this.clicouItemBaralhoListener);
			this.mesa1pos4.addMouseListener(this.clicouItemBaralhoListener);
			this.mesa1pos5.addMouseListener(this.clicouItemBaralhoListener);
			this.mesa1pos6.addMouseListener(this.clicouItemBaralhoListener);
			this.mesa1pos7.addMouseListener(this.clicouItemBaralhoListener);
			this.mesa1pos8.addMouseListener(this.clicouItemBaralhoListener);
			this.mesa1pos9.addMouseListener(this.clicouItemBaralhoListener);
			this.mesa1pos10.addMouseListener(this.clicouItemBaralhoListener);
			this.mesa1pos11.addMouseListener(this.clicouItemBaralhoListener);
			this.mesa1pos12.addMouseListener(this.clicouItemBaralhoListener);
			this.mesa1pos13.addMouseListener(this.clicouItemBaralhoListener);
		}

		return this.jContentPane;
	}

	private void sair() {
		this.dispose();
	}

	private JMenu getMenu() {
		if (this.jMenuJogo == null) {
			this.jMenuJogo = new JMenu();
			this.jMenuJogo.setText(MENU_JOGO);
			this.jMenuJogo.setBounds(new Rectangle(1, 0, 57, 21));
			this.jMenuJogo.add(this.getJMenuItemIniciar());
			this.jMenuJogo.add(this.getJMenuItemConectar());
			this.jMenuJogo.add(this.getJMenuItemDesconectar());
			this.jMenuJogo.addSeparator();
			this.jMenuJogo.add(this.getJMenuItemSair());
		}
		return this.jMenuJogo;
	}

	private JMenuItem getJMenuItemIniciar() {
		if (this.jMenuItemIniciar == null) {
			this.jMenuItemIniciar = new JMenuItem();
			this.jMenuItemIniciar.setText(MENU_INICIAR_NOVA_PARTIDA);
			this.jMenuItemIniciar
			.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					InterfaceMesa.this.atorJogador.iniciarPartida();
				}
			});
		}
		return this.jMenuItemIniciar;
	}

	private JMenuItem getJMenuItemConectar() {
		if (this.jMenuItemConectar == null) {
			this.jMenuItemConectar = new JMenuItem();
			this.jMenuItemConectar.setText(MENU_CONECTAR);
			this.jMenuItemConectar
			.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					InterfaceMesa.this.conectar();
				}
			});
		}
		return this.jMenuItemConectar;
	}

	private JMenuItem getJMenuItemDesconectar() {
		if (this.jMenuItemDesconectar == null) {
			this.jMenuItemDesconectar = new JMenuItem();
			this.jMenuItemDesconectar.setText(MENU_DESCONECTAR);
			this.jMenuItemDesconectar
			.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					InterfaceMesa.this.desconectar();
				}
			});
		}
		return this.jMenuItemDesconectar;
	}

	private JMenuItem getJMenuItemSair() {
		if (this.jMenuItemSair == null) {
			this.jMenuItemSair = new JMenuItem();
			this.jMenuItemSair.setText(MENU_SAIR);
			this.jMenuItemSair
			.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					InterfaceMesa.this.sair();
				}
			});
		}
		return this.jMenuItemSair;
	}
	
}