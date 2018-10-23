package settlers.limite;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import settlers.Settlers;
import settlers.jogadas.JogadaAceitarTroca;
import settlers.jogadas.JogadaAnoFartura;
import settlers.jogadas.JogadaComprarCarta;
import settlers.jogadas.JogadaConstruirCidade;
import settlers.jogadas.JogadaConstruirEstrada;
import settlers.jogadas.JogadaConstruirVila;
import settlers.jogadas.JogadaIniciarTabuleiro;
import settlers.jogadas.JogadaMonopolio;
import settlers.jogadas.JogadaMoverLadrao;
import settlers.jogadas.JogadaPassarVez;
import settlers.jogadas.JogadaOferecerTroca;
import settlers.jogadas.JogadaRolarDados;
import settlers.jogadas.JogadaTrocaMaritima;
import settlers.jogadas.JogadaUsarCarta;
import settlers.limite.janelas.JanelaIniciar;
import settlers.limite.janelas.JanelaOferecerTroca;
import settlers.limite.janelas.JanelaResultado;
import settlers.limite.janelas.JanelaSelecionarJogador;
import settlers.limite.janelas.JanelaTrocaMaritima;
import settlers.limite.paineis.PainelBarraStatus;
import settlers.limite.paineis.PainelTabuleiro;
import settlers.modelo.CartaDesenvolvimento;
import settlers.modelo.Tabuleiro;
import settlers.rede.AtorRede;

/**
 * 
 * @author Murilo Fernando de Oliveira
 * @author Paulo Rogério de Pinho Filho
 * 
 */
public class AtorJogador extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private int numeroJogadores;
	private String nomeJogador;
	private String servidor;
	
	private AtorRede atorRede;
	private Tabuleiro tabuleiro;
	private boolean jogadaEmAndamento;
	private boolean jogadaCancelavel;
	
    private PainelTabuleiro painelTabuleiro;
    private PainelBarraStatus painelBarraStatus;
    
	private JMenuBar barraMenu;
	private JMenu menuJogo;
	private JMenuItem menuItemIniciar;
	private JMenuItem menuItemReiniciar;
	private JMenuItem menuItemSair;
	private JMenu menuAjuda;
	private JMenuItem menuItemSobre;
	
	private JanelaIniciar janelaIniciar;
	private JanelaOferecerTroca janelaOferecerTroca;
	private JanelaTrocaMaritima janelaTrocaMaritima;
	private JanelaSelecionarJogador janelaSelecionarJogador;

	public static final Color COR_JOGADOR_1 = new Color(0.89f, 0.26f, 0.26f, 1.0f);
	public static final Color COR_JOGADOR_2 = new Color(0.27f, 0.37f, 0.89f, 1.0f);
	public static final Color COR_JOGADOR_3 = new Color(0.35f, 0.89f, 0.26f, 1.0f);
	public static final Color COR_JOGADOR_4 = new Color(0.89f, 0.87f, 0.27f, 1.0f);
	
	public static final int TERRENO_DESERTO   = -1;
    public static final int TERRENO_PASTO     =  0;
    public static final int TERRENO_FLORESTA  =  1;
    public static final int TERRENO_MINERIO   =  2;
    public static final int TERRENO_ARGILA    =  3;
    public static final int TERRENO_PLANTACAO =  4;

    public static final int RECURSO_GENERICO       = -1;
    public static final int RECURSO_OVELHA  =  0;
    public static final int RECURSO_MADEIRA =  1;
    public static final int RECURSO_MINERIO =  2;
    public static final int RECURSO_TIJOLO  =  3;
    public static final int RECURSO_TRIGO   =  4;
    
	public static final int CARTA_CONSTRUCAO_ESTRADA = 0;
	public static final int CARTA_ANO_FARTURA        = 1;
	public static final int CARTA_MONOPOLIO          = 2;
	public static final int CARTA_SOLDADO            = 3;
	public static final int CARTA_PONTO_VITORIA      = 4;
	
	public AtorJogador() {
		// Valores Padrao
		numeroJogadores = 2;
		servidor = "venus.inf.ufsc.br";
		// Instancia Tabuleiro e AtorRede
    	tabuleiro = new Tabuleiro(3);
    	atorRede = new AtorRede(this);
    	// Inicializa Janela (Interface Principal)
		inicializarJanela();
    }
    
    public static Color getCorJogador(int jogador) {
    	switch (jogador) {
		case 1: return COR_JOGADOR_1;
		case 2: return COR_JOGADOR_2;
		case 3: return COR_JOGADOR_3;
		case 4: return COR_JOGADOR_4;
		default: return null;
		}
    }

	public JanelaIniciar getJanelaIniciar() {
		return janelaIniciar;
	}

	public String getNomeJogador() {
		return nomeJogador;
	}
	
	public String getNomeJogador(int jogador) {
		return tabuleiro.getNomeJogador(jogador);
	}

	public void setNomeJogador(String nomeJogador) {
		this.nomeJogador = nomeJogador;
	}
	
	public int getNumeroJogadores() {
		return numeroJogadores;
	}
	
	public void setNumeroJogadores(int numeroJogadores) {
		this.numeroJogadores = numeroJogadores;
	}
	
	public String getServidor() {
		return servidor;
	}
	
	public void setServidor(String servidor) {
		this.servidor = servidor;
	}
	
	public void iniciar() {
		janelaIniciar = new JanelaIniciar(this);
		janelaIniciar.setVisible(true);
	}
	
	public void conectar(int numeroJogadores, String nomeJogador, String servidor) {
		this.numeroJogadores = numeroJogadores;
		this.nomeJogador = nomeJogador;
		this.servidor = servidor;
		atorRede.conectar(this.nomeJogador, this.servidor);
		atorRede.iniciarPartida(this.numeroJogadores);
	}
	
	public void desconectar() {
		// Fecha janelas abertas
		if (janelaOferecerTroca != null && janelaOferecerTroca.isVisible()) {
			janelaOferecerTroca.dispose();
		}
		if (janelaSelecionarJogador != null && janelaSelecionarJogador.isVisible()) {
			janelaSelecionarJogador.dispose();
		}
		if (janelaTrocaMaritima != null && janelaTrocaMaritima.isVisible()) {
			janelaTrocaMaritima.dispose();
		}
		atorRede.desconectar();
		tabuleiro.finalizarPartida();
		habilitarMenus();
		habilitarLances();
		setTextoBarraStatus("Pressione F2 para iniciar nova partida.");
	}
	
	public void sair() {
		if (!tabuleiro.isPartidaEmAndamento() || JOptionPane.showConfirmDialog(AtorJogador.this, "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			desconectar();
			System.exit(0);
		}
	}
	
	public void reiniciar() {
		if (JOptionPane.showConfirmDialog(AtorJogador.this, "Deseja realmente reiniciar a partida?", "Reiniciar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			atorRede.reiniciar();
		}
	}
	
	private void inicializarJanela() {
		// Inicializa Menu
		barraMenu = new JMenuBar();
		barraMenu.add(getMenuJogo());
		barraMenu.add(getMenuAjuda());
		setJMenuBar(barraMenu);
		// Personaliza Janela
		setTitle(Settlers.APPLICATION_NAME);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new TratadorEventosJanela());
		setResizable(false);
		// Inicializa Painel Tabuleiro
		painelTabuleiro = new PainelTabuleiro(this, 50, 3, 8);
		painelTabuleiro.atualizarTabuleiro(tabuleiro.getJogadaTabuleiro());
		getRootPane().setPreferredSize(new Dimension(858, 650));
		getRootPane().setMinimumSize(new Dimension(858, 650));
		pack();
		setLocationRelativeTo(null);
		// Inicializa Painel Barra Status
		painelBarraStatus = new PainelBarraStatus();
		// Adiciona Componentes
        add(painelTabuleiro, BorderLayout.CENTER);
		add(painelBarraStatus, BorderLayout.SOUTH);
		// Define texto inicial da barra status
		setTextoBarraStatus("Pressione F2 para iniciar nova partida.");
	}
	
	public void iniciarPartida(int posicao) {
		int daVez;
		janelaIniciar.setVisible(false);
		if (tabuleiro.getJogadorInstancia() == 0) {
			tabuleiro.setJogadorInstancia(posicao);
			// Nova partida: o primeiro jogador a iniciar será o que
			// receber a posição 1 pelo NetGames
			daVez = 1;
		} else {
			// Caso esteja reiniciando o primeiro a jogar será o próximo
			// jogador em relação ao que iniciou a partida anterior
			daVez = tabuleiro.getJogadorInicial() % numeroJogadores + 1;
		}
		tabuleiro.setJogadorInicial(daVez);
		tabuleiro.setJogadorDaVez(daVez);
		tabuleiro.setTurno(0);
		if (tabuleiro.isJogadorDaVez()) {
			iniciarTabuleiro();
		}
		limparPainelAcoes();
		habilitarLances();
		habilitarMenus();
	}
	
	public void habilitarMenus() {
		menuItemIniciar.setEnabled(!atorRede.isConectado());
		menuItemReiniciar.setEnabled(atorRede.isConectado());
	}
	/**
	 * habilitarLances
	 */
	public void habilitarLances() {
		painelTabuleiro.desabilitarLances();
		
		if (tabuleiro.getTurno() == 0) {
			painelTabuleiro.desativarPosicoesAresta();
			painelTabuleiro.desativarPosicoesTerreno();
			painelTabuleiro.desativarPosicoesVertice();
		}
		if (jogadaEmAndamento && jogadaCancelavel && tabuleiro.getTurno() > 2) {
			painelTabuleiro.habilitarCancelar();
		}
		if (jogadaEmAndamento || tabuleiro.getTurno() < 1 || !tabuleiro.isJogadorDaVez()) {
			return;
		}
		if (tabuleiro.getTurno() <= 2) {
			if (tabuleiro.getEtapaJogadaComposta() == 0) {
				setTextoBarraStatus("Clique sobre uma das posicões destacadas para construir uma vila.");
				construirVila();
				return;
			} else if (tabuleiro.getEtapaJogadaComposta() == 1) {
				setTextoBarraStatus("Clique sobre uma das posicões destacadas para construir uma estrada.");
				construirEstrada();
				return;
			} else if (tabuleiro.getEtapaJogadaComposta() == 2) {
				passarVez();
				return;
			}
		}
		if (tabuleiro.getValorDados() == 0) {
			setTextoBarraStatus("Role os dados para iniciar o seu turno.");
			painelTabuleiro.habilitarRolarDados();
			return;
		}
		int quantidadeLancesAtivos = 0;
		if (tabuleiro.isConstruivelVila()) {
			painelTabuleiro.habilitarConstruirVila();
			quantidadeLancesAtivos++;
		}
		if (tabuleiro.isConstruivelCidade()) {
			painelTabuleiro.habilitarConstruirCidade();
			quantidadeLancesAtivos++;
		}
		if (tabuleiro.isConstruivelEstrada()) {
			painelTabuleiro.habilitarConstruirEstrada();
			quantidadeLancesAtivos++;
		}
		if (tabuleiro.isCompravelCarta()) {
			painelTabuleiro.habilitarComprarCarta();
			quantidadeLancesAtivos++;
		}
		if (tabuleiro.isTrocavelMaritima()) {
			painelTabuleiro.habilitarTrocaMaritima();
			quantidadeLancesAtivos++;
		}
		if (tabuleiro.isDisponivelCarta()) {
			painelTabuleiro.habilitarUsarCarta();
			quantidadeLancesAtivos++;
		}
		if (tabuleiro.isOferecivelTroca()) {
			painelTabuleiro.habilitarOferecerTroca();
			quantidadeLancesAtivos++;
		}
		// Passa vez automaticamente se não houver outras possibilidades
		if (quantidadeLancesAtivos == 0) {
			passarVez();
		} else {
			setTextoBarraStatus("Escolha uma opção para efetuar a sua jogada.");
			painelTabuleiro.habilitarPassarVez();
		}
	}
	/**
	 * Jogada iniciarTabuleiro
	 */
	public void iniciarTabuleiro() {
		tabuleiro.iniciarTabuleiro(numeroJogadores);
		JogadaIniciarTabuleiro jogadaIniciarTabuleiro = tabuleiro.getJogadaTabuleiro();
		atorRede.enviarJogada(jogadaIniciarTabuleiro);
		iniciarTabuleiro(jogadaIniciarTabuleiro);
	}

	public void iniciarTabuleiro(JogadaIniciarTabuleiro jogadaIniciarTabuleiro) {
		setTextoBarraStatus("Aguarde enquanto o tabuleiro é iniciado...");
		if (!tabuleiro.isJogadorDaVez()) {
			tabuleiro.atualizarTabuleiro(jogadaIniciarTabuleiro);
		}
		registrarJogadores();
		painelTabuleiro.atualizarTabuleiro(jogadaIniciarTabuleiro);
		inicializarPainelJogadores();
		inicializarPainelCartas();
		atualizarPainelPeoes();
		tabuleiro.setTurno(1);
		jogadaEmAndamento = false;
		habilitarLances();
		if (!tabuleiro.isJogadorDaVez()) {
			setTextoBarraStatus("Aguarde a sua vez. " + tabuleiro.getJogadorDaVez().getNome() + " está jogando...");
		}
	}
	/**
	 * Altera texto da barra de status
	 */
	public void setTextoBarraStatus(String texto) {
		painelBarraStatus.setTexto(texto);
	}
	/**
	 * Inicializa novos jogadores no objeto tabuleiro e 
	 * registra seus respectivos nomes
	 */
	private void registrarJogadores() {
		for (int i = 1; i <= numeroJogadores; i++) {
			if (i == tabuleiro.getJogadorInstancia()) {
				tabuleiro.atualizarJogador(i, nomeJogador);
			} else {
				tabuleiro.atualizarJogador(i, atorRede.obterNomeAdversario(i));
			}
		}
	}
	/**
	 * Atualiza os nomes e zera os valores do painel de jogadores
	 */
	public void inicializarPainelJogadores() {
		setTitle(Settlers.APPLICATION_NAME + " - " + tabuleiro.getNomeJogador());
		for (int i = 1; i <= 4; i++) {
			if (i <= tabuleiro.getQuantidadeJogadores()) {
				painelTabuleiro.atualizarPainelJogadoresNome(i, tabuleiro.getJogador(i).getNome());
				painelTabuleiro.atualizarPainelJogadoresRecursos(i, tabuleiro.getJogador(i).getQuantidadeRecursos());
				painelTabuleiro.atualizarPainelJogadoresCartas(i, tabuleiro.getJogador(i).getQuantidadeCartas());
				painelTabuleiro.atualizarPainelJogadoresSoldados(i, tabuleiro.getJogador(i).getTamanhoExercito());
				painelTabuleiro.atualizarPainelJogadoresMaiorEstrada(i, tabuleiro.getJogador(i).getTamanhoMaiorEstrada());
				painelTabuleiro.atualizarPainelJogadoresPontos(i, tabuleiro.getJogador(i).getPontos());
				painelTabuleiro.atualizarPainelJogadoresPontosVitoria(i, tabuleiro.getJogador(i).getPontosVitoria());
			} else {
				painelTabuleiro.atualizarPainelJogadoresNome(i, "");
			}
		}
		painelTabuleiro.atualizarPainelJogadoresDaVez(tabuleiro.getJogadorDaVez().getId());
	}
	/**
	 * Atualiza "Painel Cartas": Inicializar
	 */
	public void inicializarPainelCartas() {
		painelTabuleiro.inicializarPainelCartas();
	}
	/**
	 * Atualiza "Painel Cartas": Recursos
	 */
	public void atualizarPainelCartasRecursos() {
		for (int i = 0; i < 5; i++) {
			painelTabuleiro.atualizarPainelCartasRecursos(i, tabuleiro.getRecursos(i));
		}
	}
	/**
	 * Atualiza "Painel Cartas": Cartas
	 */
	public void atualizarPainelCartasCartas() {
		int cartas[] = tabuleiro.getJogadorDaVez().getCartas();
		for (int i = 0; i < cartas.length; i++) {
			painelTabuleiro.atualizarPainelCartasCartas(i, cartas[i]);
		}
	}
	/**
	 * Atualiza "Painel Jogadores": Cartas
	 */
	public void atualizarPainelJogadoresCartas() {
		for (int i = 1; i <= tabuleiro.getQuantidadeJogadores(); i++) {
			painelTabuleiro.atualizarPainelJogadoresCartas(i, tabuleiro.getJogador(i).getQuantidadeCartas());
		}
	}
	/**
	 * Atualiza "Painel Jogadores": Soldados
	 */
	public void atualizarPainelJogadoresSoldados() {
		for (int i = 1; i <= tabuleiro.getQuantidadeJogadores(); i++) {
			painelTabuleiro.atualizarPainelJogadoresSoldados(i, tabuleiro.getJogador(i).getTamanhoExercito());
		}
	}
	/**
	 * Atualiza "Painel Jogadores": Recursos
	 */
	public void atualizarPainelJogadoresRecursos() {
		for (int i = 1; i <= tabuleiro.getQuantidadeJogadores(); i++) {
			painelTabuleiro.atualizarPainelJogadoresRecursos(i, tabuleiro.getJogador(i).getQuantidadeRecursos());
		}
	}
	/**
	 * Atualiza "Painel Jogadores": Pontos
	 */
	public void atualizarPainelJogadoresPontos() {
		for (int i = 1; i <= tabuleiro.getQuantidadeJogadores(); i++) {
			painelTabuleiro.atualizarPainelJogadoresPontos(i, tabuleiro.getJogador(i).getPontos());
		}
	}
	public void atualizarPainelJogadoresMaiorEstrada() {
		for (int i = 1; i <= tabuleiro.getQuantidadeJogadores(); i++) {
			painelTabuleiro.atualizarPainelJogadoresMaiorEstrada(i, tabuleiro.getJogador(i).getMaiorEstrada());
		}
	}
	/**
	 * Atualiza "Painel Jogadores": Pontos Vitória
	 */
	public void atualizarPainelJogadoresPontosVitoria() {
		painelTabuleiro.atualizarPainelJogadoresPontosVitoria(tabuleiro.getJogadorInstancia(), tabuleiro.getJogador(tabuleiro.getJogadorInstancia()).getPontosVitoria());
	}
	/**
	 * Atualiza "Painel Peões"
	 */
	public void atualizarPainelPeoes() {
		painelTabuleiro.iniciarPainelPeoes(tabuleiro.getJogadorInstancia(), tabuleiro.getQuantidadeEstradas(), tabuleiro.getQuantidadeVilas(), tabuleiro.getQuantidadeCidades());
	}
	
	/**
	 * Jogada rolarDados
	 */
	public void rolarDados() {
		tabuleiro.rolarDados();
		JogadaRolarDados jogadaRolarDados = new JogadaRolarDados(tabuleiro.getValorDado1(), tabuleiro.getValorDado2());
		atorRede.enviarJogada(jogadaRolarDados);
		rolarDados(jogadaRolarDados);
	}
	
	public void rolarDados(JogadaRolarDados jogadaRolarDados) {
		if (!tabuleiro.isJogadorDaVez()) {
			tabuleiro.setValorDado1(jogadaRolarDados.getValor1());
			tabuleiro.setValorDado2(jogadaRolarDados.getValor2());
		}
		registrarAcao("Os dados marcaram " + tabuleiro.getValorDados());
		painelTabuleiro.setValorDados(jogadaRolarDados.getValor1(), jogadaRolarDados.getValor2());
		painelTabuleiro.destacarTerrenos(tabuleiro.getTerrenosSorteados());
		if (tabuleiro.getValorDados() == 7) {
			if (tabuleiro.isJogadorDaVez()) {
				moverLadrao();
			}
		} else {
			tabuleiro.proverRecursos();
			atualizarPainelCartasRecursos();
			atualizarPainelJogadoresRecursos();
			habilitarLances();
		}
	}
	/**
	 * Mover Ladrão
	 */
	public void moverLadrao() {
		setTextoBarraStatus("Clique sobre o terreno para o qual você deseja mover o ladrão.");
		painelTabuleiro.ativarPosicoesTerreno(tabuleiro.buscarCamadaTerrenoLadrao(), tabuleiro.buscarIdentificadorTerrenoLadrao());
		painelTabuleiro.ativarLadrao();
	}
	
	public void moverLadrao(JogadaMoverLadrao jogada) {
		finalizarMoverLadrao(jogada.getCamada(), jogada.getIdentificador(), jogada.getJogador());
	}
	
	public void finalizarMoverLadrao(int camada, int identificador) {
		int jogadoresRedondeza[] = tabuleiro.buscarJogadoresRedondeza(camada, identificador);
		if (jogadoresRedondeza == null) {
			// Se não encontrar jogadores, finaliza sem roubar
			finalizarMoverLadrao(camada, identificador, 0);
		} else if (jogadoresRedondeza.length == 1) {
			// Se encontrar apenas um jogador, rouba recursos automaticamente
			finalizarMoverLadrao(camada, identificador, jogadoresRedondeza[0]);
		} else {
			// Se encontrar mais de um jogador, solicita de qual jogador roubar
			finalizarMoverLadrao(camada, identificador, selecionarJogador(jogadoresRedondeza));
		}
	}
	
	public void finalizarMoverLadrao(int camada, int identificador, int jogador) {
		tabuleiro.moverLadrao(camada, identificador, jogador);
		painelTabuleiro.moverLadrao(camada, identificador);
		atualizarPainelCartasRecursos();
		atualizarPainelJogadoresRecursos();
		registrarAcao("%s moveu o ladrão");
		if (tabuleiro.isJogadorDaVez()) {
			atorRede.enviarJogada(new JogadaMoverLadrao(camada, identificador, jogador));
			finalizarJogada();
		}
	}
	
	public int selecionarJogador(int jogadoresRedondeza[]) {
		janelaSelecionarJogador = new JanelaSelecionarJogador(this);
		return janelaSelecionarJogador.selecionarJogador(jogadoresRedondeza);
	}
	/**
	 * Construir Estrada
	 */
	public void construirEstrada() {
		setTextoBarraStatus("Clique sobre uma das posicões destacadas para construir uma estrada.");
		iniciarJogada(tabuleiro.getCartaNaMesa() != CARTA_CONSTRUCAO_ESTRADA);
		painelTabuleiro.ativarPosicoesAresta(tabuleiro.buscarPosicoesEstradaLivres());
	}
	
	public void construirEstrada(JogadaConstruirEstrada jogada) {
		finalizarConstruirEstrada(jogada.getIdentificador());
	}
	
	public void finalizarConstruirEstrada(int identificador) {
		tabuleiro.construirEstrada(identificador);
		painelTabuleiro.colocarEstrada(identificador, tabuleiro.getJogadorDaVez().getId());
		painelTabuleiro.atualizarPainelPeoesEstradas(tabuleiro.getQuantidadeEstradas());
		registrarAcao("%s construiu uma estrada");
		atualizarPainelJogadoresRecursos();
		atualizarPainelJogadoresMaiorEstrada();
		atualizarPainelJogadoresPontos();
		if (tabuleiro.isJogadorDaVez()) {
			atualizarPainelCartasRecursos();
			atorRede.enviarJogada(new JogadaConstruirEstrada(identificador));
			painelTabuleiro.desativarPosicoesAresta();
			if (tabuleiro.getJogadorDaVez().getQuantidadeEstradas() > 0 && tabuleiro.getCartaNaMesa() == CARTA_CONSTRUCAO_ESTRADA) {
				construirEstrada();
			} else {
				finalizarJogada();
			}
		}
	}
	/**
	 * Construir Vila
	 */
	public void construirVila() {
		setTextoBarraStatus("Clique sobre uma das posicões destacadas para construir uma vila.");
		iniciarJogada(true);
		painelTabuleiro.ativarPosicoesVertice(tabuleiro.buscarPosicoesColoniaLivres());
	}
	
	public void construirVila(JogadaConstruirVila jogada) {
		finalizarConstruirVila(jogada.getIdentificador());
	}
	
	public void finalizarConstruirVila(int identificador) {
		tabuleiro.construirVila(identificador);
		painelTabuleiro.colocarPeaoVila(identificador, tabuleiro.getJogadorDaVez().getId());
		painelTabuleiro.atualizarPainelPeoesVilas(tabuleiro.getQuantidadeVilas());
		registrarAcao("%s construiu uma vila");
		if (tabuleiro.isJogadorDaVez()) {
			atualizarPainelCartasRecursos();
			atorRede.enviarJogada(new JogadaConstruirVila(identificador));
			painelTabuleiro.desativarPosicoesVertice();
		}
		atualizarPainelJogadoresRecursos();
		atualizarPainelJogadoresPontos();
		if (tabuleiro.isJogadorDaVez()) {
			finalizarJogada();
		}
	}
	/**
	 * Construir Cidade
	 */
	public void construirCidade() {
		setTextoBarraStatus("Clique sobre uma das posicões destacadas para construir uma cidade.");
		iniciarJogada(true);
		painelTabuleiro.ativarPeoesVila(tabuleiro.buscarPosicoesColoniasComVila());
	}
	
	public void construirCidade(JogadaConstruirCidade jogada) {
		finalizarConstruirCidade(jogada.getIdentificador());
	}
	
	public void finalizarConstruirCidade(int id) {
		tabuleiro.construirCidade(id);
		painelTabuleiro.colocarPeaoCidade(id, tabuleiro.getJogadorDaVez().getId());
		painelTabuleiro.atualizarPainelPeoesVilas(tabuleiro.getQuantidadeVilas());
		painelTabuleiro.atualizarPainelPeoesCidades(tabuleiro.getQuantidadeCidades());
		registrarAcao("%s construiu uma cidade");
		if (tabuleiro.isJogadorDaVez()) {
			atualizarPainelCartasRecursos();
			atorRede.enviarJogada(new JogadaConstruirCidade(id));
			painelTabuleiro.desativarPeoesVila();
		}
		atualizarPainelJogadoresRecursos();
		atualizarPainelJogadoresPontos();
		if (tabuleiro.isJogadorDaVez()) {
			finalizarJogada();
		}
	}
	/**
	 * Comprar Carta de Desenvolvimento
	 */
	public void comprarCarta() {
		setTextoBarraStatus("Clique sobre a carta que você deseja comprar.");
		int carta = tabuleiro.sortearCarta();
		JogadaComprarCarta jogada = new JogadaComprarCarta(carta);
		atorRede.enviarJogada(jogada);
		comprarCarta(jogada);
	}
	/**
	 * Comprar Carta de Desenvolvimento
	 * @param jogada Jogada enviada pela rede ou pelo jogador
	 */
	public void comprarCarta(JogadaComprarCarta jogada) {
		tabuleiro.comprarCarta(jogada.getCarta());
		atualizarPainelJogadoresPontosVitoria();
		atualizarPainelJogadoresRecursos();
		atualizarPainelJogadoresCartas();
		if (tabuleiro.isJogadorDaVez()) {
			atualizarPainelCartasRecursos();
			atualizarPainelCartasCartas();
		}
		registrarAcao("%s comprou uma carta");
		habilitarLances();
	}
	/**
	 * Usar Carta de Desenvolvimento
	 */
	public void usarCarta() {
		setTextoBarraStatus("Clique sobre a carta que você deseja usar.");
		iniciarJogada(true);
		int cartasDisponiveis[] = tabuleiro.getCartasDisponiveis();
		for (int i = 0; i < 5; i++) {
			if (cartasDisponiveis[i] > 0) {
				painelTabuleiro.ativarCarta(i);
			}
		}
	}
	/**
	 * Usar Carta de Desenvolvimento
	 * @param carta Tipo de carta que será utilizada
	 */
	public void usarCarta(int carta) {
		JogadaUsarCarta jogada = new JogadaUsarCarta(carta);
		atorRede.enviarJogada(jogada);
		usarCarta(jogada);
	}
	
	public void usarCarta(JogadaUsarCarta jogada) {
		int carta = jogada.getCarta();
		tabuleiro.usarCarta(carta);
		atualizarPainelJogadoresCartas();
		atualizarPainelJogadoresSoldados();
		atualizarPainelJogadoresPontos();
		if (tabuleiro.isJogadorDaVez()) {
			atualizarPainelCartasCartas();
			switch (carta) {
			case CartaDesenvolvimento.CARTA_ANO_FARTURA:
				tabuleiro.setEtapaJogadaComposta(0);
				anoFartura();
				break;
			case CartaDesenvolvimento.CARTA_CONSTRUCAO_ESTRADA:
				tabuleiro.setEtapaJogadaComposta(0);
				registrarAcao("%s usou a carta Construir Estrada");
				construirEstrada();
				break;
			case CartaDesenvolvimento.CARTA_MONOPOLIO:
				monopolio();
				break;
			case CartaDesenvolvimento.CARTA_SOLDADO:
				painelTabuleiro.desabilitarLances();
				registrarAcao("%s usou a carta Soldado");
				moverLadrao();
				break;
			}
		}
	}
	
	public void acaoUsarCarta(int recurso) {
		if (tabuleiro.getJogadorDaVez().getCartaNaMesa() == CartaDesenvolvimento.CARTA_ANO_FARTURA) {
			JogadaAnoFartura jogada = new JogadaAnoFartura(recurso);
			atorRede.enviarJogada(jogada);
			anoFartura(jogada);
			if (tabuleiro.getEtapaJogadaComposta() == 0) {
				tabuleiro.setEtapaJogadaComposta(tabuleiro.getEtapaJogadaComposta() + 1);
				painelTabuleiro.ativarRecursos();
			} else {
				finalizarJogada();
			}
		} else if (tabuleiro.getJogadorDaVez().getCartaNaMesa() == CartaDesenvolvimento.CARTA_MONOPOLIO) {
			JogadaMonopolio jogada = new JogadaMonopolio(recurso);
			atorRede.enviarJogada(jogada);
			monopolio(jogada);
			finalizarJogada();
		}
	}
	
	public void anoFartura() {
		setTextoBarraStatus("Escolha dois recursos que você deseja obter.");
		iniciarJogada(false);
		painelTabuleiro.ativarRecursos();
	}
	
	public void anoFartura(JogadaAnoFartura jogada) {
		tabuleiro.anoFartura(jogada.getRecurso());
		atualizarPainelCartasRecursos();
		atualizarPainelJogadoresRecursos();
		registrarAcao("%s usou a carta Ano de Fartura");
	}
	
	public void monopolio() {
		setTextoBarraStatus("Escolha o recurso que você deseja obter de todos os outros jogadores.");
		iniciarJogada(false);
		painelTabuleiro.ativarRecursos();
	}
	
	public void monopolio(JogadaMonopolio jogada) {
		tabuleiro.monopolizarRecurso(jogada.getRecurso());
		atualizarPainelCartasRecursos();
		atualizarPainelJogadoresRecursos();
		registrarAcao("%s usou a carta Monopólio");
	}
	/**
	 * Oferecer Troca
	 */
	public void oferecerTroca() {
		int jogadorInstancia = tabuleiro.getJogadorInstancia();
		int jogadorDaVez = tabuleiro.getJogadorDaVez().getId();
		String jogadores[] = tabuleiro.getNomesJogadores();
		int recursos[] = tabuleiro.getJogadorDaVez().getRecursos();

		janelaOferecerTroca = new JanelaOferecerTroca(this, jogadorInstancia, jogadorDaVez, jogadores, recursos);
		janelaOferecerTroca.setVisible(true);
	}
	
	public void oferecerTroca(int oferta[], int procura[]) {
		JogadaOferecerTroca jogada = new JogadaOferecerTroca(tabuleiro.getJogadorInstancia(), oferta, procura);
		atorRede.enviarJogada(jogada);
	}
	
	public void oferecerTroca(JogadaOferecerTroca jogada) {
		if ((janelaOferecerTroca != null && janelaOferecerTroca.isVisible()) || (jogada.getJogador() == tabuleiro.getJogadorDaVez().getId())) {
			if (jogada.getJogador() == tabuleiro.getJogadorDaVez().getId() && (jogada.getOferta() == null || jogada.getProcura() == null)) {
				if (janelaOferecerTroca != null && janelaOferecerTroca.isVisible()) {
					janelaOferecerTroca.setVisible(false);
				}
			} else {
				if (janelaOferecerTroca == null || !janelaOferecerTroca.isVisible()) {
					int jogadorInstancia = tabuleiro.getJogadorInstancia();
					int jogadorDaVez = tabuleiro.getJogadorDaVez().getId();
					String jogadores[] = tabuleiro.getNomesJogadores();
					int recursos[] = tabuleiro.getJogador(tabuleiro.getJogadorInstancia()).getRecursos();
					janelaOferecerTroca = new JanelaOferecerTroca(this, jogadorInstancia, jogadorDaVez, jogadores, recursos);
				}
				janelaOferecerTroca.atualizarOferta(jogada.getJogador(), jogada.getOferta(), jogada.getProcura());
				// É necessário criar uma thread para exibir a janela modal para não bloquear
				// o proxy do netgames
				new Thread() {
					@Override
					public void run() {
						janelaOferecerTroca.setVisible(true);
					}
				}.start();
			}
		}
	}
	/**
	 * Aceitar Troca
	 */
	public void aceitarTroca(int jogador, int oferta[], int procura[]) {
		JogadaAceitarTroca jogada = new JogadaAceitarTroca(jogador, tabuleiro.getJogadorInstancia(), oferta, procura);
		atorRede.enviarJogada(jogada);
		aceitarTroca(jogada);
	}
	
	public void aceitarTroca(JogadaAceitarTroca jogada) {
		if (janelaOferecerTroca != null && janelaOferecerTroca.isVisible()) {
			janelaOferecerTroca.dispose();
		}
		tabuleiro.efetuarTroca(jogada.getJogadorOrigem(), jogada.getJogadorDestino(), jogada.getOferta(), jogada.getProcura());
		atualizarPainelJogadoresRecursos();
		atualizarPainelCartasRecursos();
	}
	/** 
	 * Troca Marítima de recursos
	 */
	public void trocaMaritima() {
		int proporcaoOfertas[] = tabuleiro.getJogadorDaVez().getProporcaoOfertaMaritima();
		int recursosDisponiveis[] = tabuleiro.getJogadorDaVez().getRecursos();
		janelaTrocaMaritima = new JanelaTrocaMaritima(this, recursosDisponiveis, proporcaoOfertas);
		janelaTrocaMaritima.setVisible(true);
	}
	
	public void trocaMaritima(int recursoOfertado, int quantidadeOfertado, int recursoRecebido, int quantidadeRecebida) {
		JogadaTrocaMaritima jogada = new JogadaTrocaMaritima(recursoOfertado, quantidadeOfertado, recursoRecebido, quantidadeRecebida);
		atorRede.enviarJogada(jogada);
		trocaMaritima(jogada);
	}
	
	public void trocaMaritima(JogadaTrocaMaritima jogada) {
		tabuleiro.trocaMaritima(jogada.getRecursoOfertado(), jogada.getQuantidadeOfertada(), jogada.getRecursoRecebido(), jogada.getQuantidadeRecebida());
		atualizarPainelJogadoresRecursos();
		if (tabuleiro.isJogadorDaVez()) {
			atualizarPainelCartasRecursos();
		}
		habilitarLances();
	}
	/**
	 * Cancela jogada de construção em andamento e retorna ao estado anterior
	 */
	public void cancelarJogada() {
		painelTabuleiro.desativarPosicoesAresta();
		painelTabuleiro.desativarPosicoesTerreno();
		painelTabuleiro.desativarPosicoesVertice();
		painelTabuleiro.desativarPeoesVila();
		painelTabuleiro.desativarCartas();
		finalizarJogada();
	}
	/**
	 * Passar Vez
	 */
	public void passarVez() {
		if (tabuleiro.isJogadorDaVez()) {
			atorRede.enviarJogada(new JogadaPassarVez());
		}
		if (tabuleiro.isVencedor()) {
			setTextoBarraStatus("Partida terminada.");
			tabuleiro.setTurno(0);
			habilitarLances();
			exibirResultado();
			setTextoBarraStatus("Pressione F3 para reiniciar a partida.");
		} else {
			tabuleiro.passarVez();
			painelTabuleiro.atualizarPainelJogadoresDaVez(tabuleiro.getJogadorDaVez().getId());
			habilitarLances();
			if (!tabuleiro.isJogadorDaVez()) {
				setTextoBarraStatus("Aguarde a sua vez. " + tabuleiro.getJogadorDaVez().getNome() + " está jogando...");
			}
		}
	}
	
	public void registrarAcao(String texto) {
		painelTabuleiro.registrarAcao(tabuleiro.getJogadorDaVez().getId(), String.format(texto, tabuleiro.getJogadorDaVez().getNome()));
	}

	public void limparPainelAcoes() {
		painelTabuleiro.limparPainelAcoes();
	}
	public void exibirResultado() {
		boolean vencedor = tabuleiro.getJogadorDaVez().getId() == tabuleiro.getJogadorInstancia();
		int idVencedor = tabuleiro.getJogadorVencedor();
		String resultado[][] = tabuleiro.getResultado();
		JanelaResultado janelaResultado = new JanelaResultado(this, vencedor, idVencedor, resultado);
		janelaResultado.setVisible(true);
	}
	
	private void iniciarJogada(boolean cancelavel) {
		jogadaEmAndamento = true;
		jogadaCancelavel = cancelavel;
		habilitarLances();
	}
	
	private void finalizarJogada() {
		jogadaEmAndamento = false;
		habilitarLances();
	}
	
	public JMenu getMenuJogo() {
		menuJogo = new JMenu("Jogo");
		menuItemIniciar = new JMenuItem("Iniciar...");
		menuItemIniciar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		menuItemIniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				iniciar();
			}
		});
		menuItemReiniciar = new JMenuItem("Reiniciar");
		menuItemReiniciar.setEnabled(false);
		menuItemReiniciar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		menuItemReiniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reiniciar();
			}
		});
		menuItemSair = new JMenuItem("Sair");
		menuItemSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sair();
			}
		});
		menuJogo.add(menuItemIniciar);
		menuJogo.add(menuItemReiniciar);
		menuJogo.addSeparator();
		menuJogo.add(menuItemSair);
		return menuJogo;
	}
	
	public JMenu getMenuAjuda() {
		menuAjuda = new JMenu("Ajuda");
		menuItemSobre = new JMenuItem("Sobre...");
		menuItemSobre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String mensagem = Settlers.APPLICATION_NAME + " v. 1.0 - 2009\n";
				mensagem += "\n";
				mensagem += "UFSC - Universidade Federal de Santa Catarina\n";
				mensagem += "Projeto da disciplina Análise e Projeto de Sistemas\n";
				mensagem += "\n";
				mensagem += "Autores:\n";
				mensagem += "Murilo Fernando de Oliveira\n";
				mensagem += "Paulo Rogério de Pinho Filho\n";
				JOptionPane.showMessageDialog(AtorJogador.this, mensagem, "Sobre", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuAjuda.add(menuItemSobre);
		return menuAjuda;
	}
	/**
	 * Trata o evento fechar janela, que solicita confirmação de saida caso
	 * um jogo esteja em andamento.
	 */
	private class TratadorEventosJanela extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			sair();
		}
	}

}
