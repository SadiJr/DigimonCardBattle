package settlers.rede;

import javax.swing.JOptionPane;

import settlers.jogadas.JogadaAceitarTroca;
import settlers.jogadas.JogadaAnoFartura;
import settlers.jogadas.JogadaComprarCarta;
import settlers.jogadas.JogadaConstruirCidade;
import settlers.jogadas.JogadaConstruirEstrada;
import settlers.jogadas.JogadaConstruirVila;
import settlers.jogadas.JogadaIniciarTabuleiro;
import settlers.jogadas.JogadaMonopolio;
import settlers.jogadas.JogadaMoverLadrao;
import settlers.jogadas.JogadaOferecerTroca;
import settlers.jogadas.JogadaPassarVez;
import settlers.jogadas.JogadaRolarDados;
import settlers.jogadas.JogadaTrocaMaritima;
import settlers.jogadas.JogadaUsarCarta;
import settlers.limite.AtorJogador;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

/**
 * 
 * @author Murilo Fernando de Oliveira
 * @author Paulo Rogério de Pinho Filho
 * 
 */
public class AtorRede implements OuvidorProxy {

	private static final long serialVersionUID = 1L;
	
	private boolean conectado;
	private AtorJogador atorJogador;
	
	public AtorRede(AtorJogador atorJogador) {
		Proxy.getInstance().addOuvinte(this);
		this.atorJogador = atorJogador;
		this.conectado = false;
	}
	/**
	 * Informa o estado atual da conexão com o NetGames
	 * @return True se conectado e false se desconectado
	 */
	public boolean isConectado() {
		return conectado;
	}
	/**
	 * Estabelece uma conexão com o servidor NetGames
	 * @param nome Representa o nome ou apelido informado pelo jogador
	 * @param ipServidor É o ip ou host do servidor NetGames
	 * @return True em caso de sucesso e false, caso contrário
	 */
	public boolean conectar(String nome, String ipServidor) {
		try {
			Proxy.getInstance().conectar(ipServidor, nome);
			conectado = true;
		} catch (JahConectadoException e) {
			JOptionPane.showMessageDialog(atorJogador, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			atorJogador.getJanelaIniciar().cancelar();
		} catch (NaoPossivelConectarException e) {
			JOptionPane.showMessageDialog(atorJogador, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			atorJogador.getJanelaIniciar().cancelar();
		} catch (ArquivoMultiplayerException e) {
			JOptionPane.showMessageDialog(atorJogador, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			atorJogador.getJanelaIniciar().cancelar();
		}
		return conectado;
	}
	/**
	 * Tenta desconectar do servidor
	 * Não faz nada se já estiver desconectado
	 */
	public void desconectar() {
		conectado = false;
		try {
			Proxy.getInstance().desconectar();
		} catch (NaoConectadoException e) {}
	}
	/**
	 * Tenta iniciar partida
	 * Não faz nada se não for possível por falta de jogadores
	 * @param jogadores Indica a quantidade de jogadores na partida
	 */
	public void iniciarPartida(int jogadores) {
		try {
			Proxy.getInstance().iniciarPartida(jogadores);
		} catch (NaoConectadoException e) {}
	}
	/**
	 * Força o reinício de uma partida após o seu início e pode
	 * ser invocada durante ou após a sua conclusão
	 */
	public void reiniciar() {
		try {
			Proxy.getInstance().reiniciarPartida();
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(atorJogador, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} catch (NaoJogandoException e) {
			JOptionPane.showMessageDialog(atorJogador, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * Envia uma jogada aos demais clientes conectados ao servidor
	 * @param jogada Contem um objeto que empacota as informações da jogada
	 */
	public void enviarJogada(Jogada jogada) {
		try {
			Proxy.getInstance().enviaJogada(jogada);
		} catch (NaoJogandoException e) {
			JOptionPane.showMessageDialog(atorJogador, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * Método invocado pelo proxy ao receber uma jogada
	 * enviada por outro jogador
	 */
	@Override
	public void receberJogada(Jogada jogada) {
		if (jogada instanceof JogadaIniciarTabuleiro) {
			atorJogador.iniciarTabuleiro((JogadaIniciarTabuleiro) jogada);
		} else if (jogada instanceof JogadaRolarDados) {
			atorJogador.rolarDados((JogadaRolarDados) jogada);
		} else if (jogada instanceof JogadaPassarVez) {
			atorJogador.passarVez();
		} else if (jogada instanceof JogadaConstruirVila) {
			atorJogador.construirVila((JogadaConstruirVila) jogada);
		} else if (jogada instanceof JogadaConstruirEstrada) {
			atorJogador.construirEstrada((JogadaConstruirEstrada) jogada);
		} else if (jogada instanceof JogadaConstruirCidade) {
			atorJogador.construirCidade((JogadaConstruirCidade) jogada);
		} else if (jogada instanceof JogadaComprarCarta) {
			atorJogador.comprarCarta((JogadaComprarCarta) jogada);
		} else if (jogada instanceof JogadaUsarCarta) {
			atorJogador.usarCarta((JogadaUsarCarta) jogada);
		} else if (jogada instanceof JogadaTrocaMaritima) {
			atorJogador.trocaMaritima((JogadaTrocaMaritima) jogada);
		} else if (jogada instanceof JogadaMoverLadrao) {
			atorJogador.moverLadrao((JogadaMoverLadrao) jogada);
		} else if (jogada instanceof JogadaMonopolio) {
			atorJogador.monopolio((JogadaMonopolio) jogada);
		} else if (jogada instanceof JogadaAnoFartura) {
			atorJogador.anoFartura((JogadaAnoFartura) jogada);
		} else if (jogada instanceof JogadaOferecerTroca){
			atorJogador.oferecerTroca((JogadaOferecerTroca) jogada);
		} else if (jogada instanceof JogadaAceitarTroca){
			atorJogador.aceitarTroca((JogadaAceitarTroca) jogada);
		}
	}
	/**
	 * Obtém o nome do jogador participante da partida em andamento
	 * @param posicao Representa a ordem do jogador atribuída pelo servidor
	 * @return nome Informado pelo jogador
	 */
	public String obterNomeAdversario(Integer posicao) {
		return Proxy.getInstance().obterNomeAdversario(posicao);
	}
	/**
	 * Método invocado pelo proxy ao ser solicitado o início ou
	 * reinício de uma partida
	 */
	@Override
	public void iniciarNovaPartida(Integer posicao) {
		atorJogador.iniciarPartida(posicao);
	}
	/**
	 * Método invocado pelo proxy se ocorrer algum evento
	 * que impeça a continuidade da partida
	 */
	@Override
	public void finalizarPartidaComErro(String message) {
		if (isConectado()) {
			atorJogador.desconectar();
			JOptionPane.showMessageDialog(atorJogador, message, "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void receberMensagem(String msg) {
	}

	@Override
	public void tratarConexaoPerdida() {
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
	}

}
