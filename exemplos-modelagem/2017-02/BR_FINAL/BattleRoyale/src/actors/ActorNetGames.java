package actors;

import javax.swing.JOptionPane;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import game.Action;
import game.Control;

public class ActorNetGames implements OuvidorProxy {
	protected Control control;
	protected Proxy proxy;

	public ActorNetGames(Control control) {
		super();
		this.control = control;
		this.proxy = Proxy.getInstance();
		this.proxy.addOuvinte(this);
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		if (!control.b) {
			this.control.initGame(posicao);
		}
	}

	public void sendAction(Action action) {
		try {
			this.control.updateBoard();
			this.proxy.enviaJogada(action);
		} catch (NaoJogandoException e) {
			JOptionPane.showMessageDialog(null, "Not connected");
		}

	}

	@Override
	public void receberJogada(Jogada jogada) {
		Action action = (Action) jogada;
		this.control.makeAction(action);

	}

	@Override
	public void finalizarPartidaComErro(String message) {

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

	public void connect(String ip, String name) {
		try {
			System.out.println(name + " connecting...");
			this.proxy.conectar(ip, name);
			System.out.println(name + " connected");
		} catch (JahConectadoException e) {
			new JOptionPane("You are already connected.");
			e.printStackTrace();
		} catch (NaoPossivelConectarException e) {
			System.out.println("not possible to connect");
			e.printStackTrace();
		} catch (ArquivoMultiplayerException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			this.proxy.desconectar();
		} catch (NaoConectadoException e) {
			new JOptionPane("You are disconnected.");
			e.printStackTrace();
		}

	}

	public void iniciarPartida() {
		try {
			System.out.println("Starting game...");
			this.proxy.iniciarPartida(2);
		} catch (NaoConectadoException e) {
			System.out.println("Not connected");
		}
	}

	public void setPlayerName(String name) {
		this.proxy.setNomeJogador(name);
	}

	public String getOpponentsName() {
		String name;
		if (control.getPlayer().isTurn()) {
			name = proxy.obterNomeAdversario(2);
		} else {
			name = proxy.obterNomeAdversario(1);
		}
		return name;
	}

}
