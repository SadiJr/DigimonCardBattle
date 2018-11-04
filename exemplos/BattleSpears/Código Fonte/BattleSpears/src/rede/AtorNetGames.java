package rede;

import br.ufsc.inf.leobr.cliente.Jogada; 
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import control.Tabuleiro;
import view.AtorJogador;

public class AtorNetGames implements OuvidorProxy {

	private static final long serialVersionUID = 0L;
	protected AtorJogador atorJogador;
	protected Proxy proxy;

	public AtorNetGames(AtorJogador ator) {
		atorJogador = ator;
		proxy = Proxy.getInstance();
		proxy.addOuvinte(this);
	}

	public void conectar(String idJogador, String servidor) throws Exception {
		proxy.conectar(servidor, idJogador);
	}

	public void desconectar() throws NaoConectadoException {
		proxy.desconectar();
	}

	public void iniciarPartida() throws NaoConectadoException {
		proxy.iniciarPartida(2);
	}

	public void reiniciarPartida() throws NaoConectadoException, NaoJogandoException {
		proxy.reiniciarPartida();
	}

	public void iniciarNovaPartida(Integer posicao) {
		atorJogador.iniciarNovaPartida(posicao);
	}

	public void finalizarPartidaComErro(String message) {
		this.atorJogador.notificarErro("O outro jogador desconectou da partida.");
	}

	public void receberMensagem(String msg) {
		atorJogador.notificar(msg);
	}

	public void receberJogada(Jogada jogada) {
		Tabuleiro tab = (Tabuleiro) jogada;
		try {
			this.atorJogador.receberJogada(tab);
		} catch (NaoConectadoException e) {
			e.printStackTrace();
		} catch (NaoJogandoException e) {
			e.printStackTrace();
		}

	}

	public void tratarConexaoPerdida() {
		atorJogador.notificar("Conex�o perdida. Por favor, conecte-se novamente.");
	}

	public void tratarPartidaNaoIniciada(String message) {
		this.atorJogador.notificarErro(
				"N�o foi poss�vel iniciar a partida.\nProvavelmente n�o existem outros jogadores conectados.");
	}

	public String getNomeAdversario(int posicao) {
		return proxy.obterNomeAdversarios().get(0);
	}

	public void enviarJogada(Tabuleiro tab) throws NaoJogandoException {
		Jogada jogada = (Jogada) tab;
		proxy.enviaJogada(jogada);
	}

}