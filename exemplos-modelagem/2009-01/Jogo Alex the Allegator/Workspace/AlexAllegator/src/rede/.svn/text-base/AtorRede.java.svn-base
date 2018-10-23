package rede;

import interfaceGrafica.AtorJogador;

import java.awt.Rectangle;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import dominioProblema.JogadaAllegator;

public class AtorRede implements OuvidorProxy {

	public AtorJogador atorJogador;

	public Proxy proxy;

	private JMenu menuRede = null;
	
	private boolean ehJogoRede = false;
	
	private boolean ehMinhaVez = false;

	private JMenuItem jMenuItemConectar = null,
			jMenuItemIniciarPartidaRede = null,
			jMenuItemReiniciarPartidaRede = null, 
			jMenuItemDesconectar = null;

	public AtorRede(AtorJogador atorJogador) {
		super();
		this.atorJogador = atorJogador;
		proxy = Proxy.getInstance();
	}

	// Interface gráfica para menus de rede 
	public JMenu getMenuRede() {
		if (menuRede == null) {
			menuRede = new JMenu();
			menuRede.setText("Jogar Online");
			menuRede.setBounds(new Rectangle(1, 0, 57, 21));
			menuRede.add(getJMenuItemConectar());
			menuRede.add(getJMenuItemIniciarPartidaRede());
			menuRede.add(getJMenuReiniciarParitdaRede());
			menuRede.add(getJMenuItemDesconectar());
		}
		return menuRede;
	}

	 
	private JMenuItem getJMenuItemConectar() {
		if (jMenuItemConectar == null) {
			jMenuItemConectar = new JMenuItem();
			jMenuItemConectar.setText("Conectar");
			jMenuItemConectar
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							conectar();
						}
					});
		}
		return jMenuItemConectar;
	}
	 
	private JMenuItem getJMenuItemIniciarPartidaRede() {
		if (jMenuItemIniciarPartidaRede == null) {
			jMenuItemIniciarPartidaRede = new JMenuItem();
			jMenuItemIniciarPartidaRede.setText("Iniciar Partida de Rede");
			jMenuItemIniciarPartidaRede
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							iniciarPartidaRede();
						}
					});
		}
		return jMenuItemIniciarPartidaRede;
	}

	 
	private JMenuItem getJMenuItemDesconectar() {
		if (jMenuItemDesconectar == null) {
			jMenuItemDesconectar = new JMenuItem();
			jMenuItemDesconectar.setText("Desconectar");
			jMenuItemDesconectar
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							desconectar();
						}
					});
		}
		return jMenuItemDesconectar;
	}

	private JMenuItem getJMenuReiniciarParitdaRede() {
		if (jMenuItemReiniciarPartidaRede == null) {
			jMenuItemReiniciarPartidaRede = new JMenuItem();
			jMenuItemReiniciarPartidaRede.setText("Reiniciar partida");
			jMenuItemReiniciarPartidaRede
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							reiniciar();
						}
					});
		}
		return jMenuItemReiniciarPartidaRede;
	}
	
	/**
	 * Método que abre a gui para o usuário digitar o nome e ip do servidor.
	 */
	protected void conectar() {
		GuiRede ic = new GuiRede(this);
		ic.createFront();
	}

	// Métodos que realizam a lógica de rede
	/**
	 * Conecta o jogo em rede.
	 * @param nome
	 * @param ipServidor
	 */
	protected void conectarRede(String nome, String ipServidor) {
		try {
			Proxy.getInstance().conectar(ipServidor, nome);
			//Adiciona o ator como um dos ouvintes do Proxy.
			Proxy.getInstance().addOuvinte(this);
		} 
		catch (JahConectadoException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(atorJogador, e.getMessage());
		} 
		catch (NaoPossivelConectarException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(atorJogador, "Erro: "
					+ e.getMessage());
		} 
		catch (ArquivoMultiplayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(atorJogador, "Erro: "
					+ e.getMessage());
		}
	}
	 
	/**
	 * Inicia uma partida de rede.
	 * Esse método é chamado por um jogador que está conectado e deseja jogar.
	 * No servidor, um outro jogador, também conectado e que ainda não está jogando será sorteado para inciar uma partida.
	 * O usuário quando chama esse método ainda não iniciou uma partida, mas solicitou seu inicio.
	 */
	protected void iniciarPartidaRede() {		
		try {
			Proxy.getInstance().iniciarPartida(Integer.valueOf(2));
		} 
		catch (NaoConectadoException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(atorJogador, e.getMessage());
		}
	}
	 
	/* 
	 * Esse método efetivamente inicia a partida.
	 * Ele é chamado pelo servidor em todos os jogadores que fizerem parte da partida,
	 * inclusive pelo usuário que fez a solicitação do início da partida.
	 * (non-Javadoc)
	 * @see br.ufsc.inf.leobr.cliente.OuvidorProxy#iniciarNovaPartida(java.lang.Integer)
	 */
	//@Override
	public void iniciarNovaPartida(Integer posicao) {
		if (posicao == 1) {
			JOptionPane.showMessageDialog(atorJogador,
			"Partida Iniciada, você começa jogando!");
			ehMinhaVez = true;
			atorJogador.iniciarPartidaRede(ehMinhaVez);
			ehJogoRede = true;
		} 
		else {
			JOptionPane.showMessageDialog(atorJogador,
			"Partida Iniciada, aguarde uma jogada");
			ehMinhaVez = false;
			atorJogador.iniciarPartidaRede(ehMinhaVez);
			ehJogoRede = true;
		}
	}
	
	/**
	 * Se o jogo estiver sendo realizado em rede, envia a jogada para os outros
	 * jogadores
	 * 
	 * @param linha
	 * @param coluna
	 */
	 
	public void enviarJogadaRede(int linha, int coluna) {
		if (ehJogoRede) {
			Jogada jg = new JogadaAllegator(linha, coluna);
			try {
				Proxy.getInstance().enviaJogada(jg);
				ehMinhaVez = false;
			} 
			catch (NaoJogandoException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(atorJogador, e.getMessage());
			}
		}
	}
	
	/* Método chamado pelo servidor para enviar a jogada do jogo da velha
	 * para os participantes de uma partida.
	 * O único jogador no qual o método não é invocado é aquele que enviou a jogada.
	 * (non-Javadoc)
	 * @see br.ufsc.inf.leobr.cliente.OuvidorProxy#receberJogada(br.ufsc.inf.leobr.cliente.Jogada)
	 */
	//@Override
	public void receberJogada(Jogada jogada) {
		// Recebe uma jogada do outro lado
		dominioProblema.JogadaAllegator jg = (dominioProblema.JogadaAllegator) jogada;
		atorJogador.efetuarJogadaRede(jg.getLinha(), jg.getColuna());
		ehMinhaVez = true;
	}
	
	/**
	 * Desconecta o jogo da rede.
	 */
	protected void desconectar() {
		try {
			ehJogoRede = false;
			Proxy.getInstance().desconectar();
		} 
		catch (NaoConectadoException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(atorJogador, "Erro: "
					+ e.getMessage());
		}		
	}
	
	//Métodos complementares
	/**
	 * Reinicia uma partida com todos os iniciantes já selecionados.
	 */
	protected void reiniciar() {
		try {
			Proxy.getInstance().reiniciarPartida();
		} 
		catch (NaoConectadoException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(atorJogador, "Erro: "
					+ e.getMessage());
		} 
		catch (NaoJogandoException e) {
			JOptionPane.showMessageDialog(atorJogador, "Erro: "
					+ e.getMessage());
		}
	}

	public void finalizarPartidaComErro(String mensagem) {
		JOptionPane.showMessageDialog(atorJogador, mensagem);
		ehJogoRede = false;
	}

	public void receberMensagem(String mensagem) {
		JOptionPane.showMessageDialog(atorJogador,
				"Mensagem recebida do servidor:" + mensagem);
	}

	public void tratarConexaoPerdida() {
		JOptionPane.showMessageDialog(atorJogador,
						"A conexão com o servidor foi perdida, por favor tente novamente mais tarde.");
		ehJogoRede = false;
	}

	public void tratarPartidaNaoIniciada(String message) {
		JOptionPane.showMessageDialog(atorJogador,
				"A partida não pode ser iniciada devido ao seguinte erro: "
						+ message);
	}
	
	public boolean ehJogoRede(){
		return ehJogoRede;
	}
	
	public boolean ehMinhaVez(){
		return ehMinhaVez;
	}
	
	/**
	 * Obtëm o nome do outro jogador de rede.
	 * @return
	 */
	public String obtemNomeAdversario(){
		return Proxy.getInstance().obterNomeAdversarios().get(0);
	}	
}