package rede;

import br.ufsc.inf.Constantes;
import br.ufsc.inf.control.Controlador;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import br.ufsc.inf.model.EstadoMesa;

public class AtorNetGames implements OuvidorProxy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Proxy proxy;
	protected Controlador controlador;

	public AtorNetGames(Controlador controlador) {
		super();
		this.controlador = controlador;
		this.proxy = Proxy.getInstance();
		this.proxy.addOuvinte(this);
	}

	public boolean conectar(String servidor, String nome) {
		try {
			this.proxy.conectar(servidor, nome);
		} catch (JahConectadoException | NaoPossivelConectarException
				| ArquivoMultiplayerException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean desconectar() {
		try {
			this.proxy.desconectar();
		} catch (NaoConectadoException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean iniciarPartida() {
		try {
			this.proxy.iniciarPartida(Constantes.NUMERO_JOGADORES);
		} catch (NaoConectadoException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String obterNomeAdversario(int ordem) {
		return this.proxy.obterNomeAdversario(ordem);
	}

	public void enviarJogada(EstadoMesa estadoMesa) {
		try {
			this.proxy.enviaJogada(estadoMesa);
		} catch (NaoJogandoException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void iniciarNovaPartida(Integer ordem) {
		this.controlador.iniciarNovaPartida(ordem);
	}

	@Override
	public void receberJogada(Jogada jogada) {
		this.controlador.receberJogada((EstadoMesa) jogada);
	}

	@Override
	public void finalizarPartidaComErro(String message) {
		return;
	}

	@Override
	public void receberMensagem(String msg) {
		return;
	}

	@Override
	public void tratarConexaoPerdida() {
		return;
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		return;
	}

}