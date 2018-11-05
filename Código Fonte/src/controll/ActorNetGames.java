package src.actor;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import src.controll.TableController;
import src.model.Table;

public class ActorNetGames implements OuvidorProxy {

	private TableController tableController;
	private Proxy proxy;

	public ActorNetGames(TableController ctrl) {
		this.tableController = ctrl;
		this.proxy = Proxy.getInstance();
		proxy.addOuvinte(this);
	}
	
	public boolean connect(String name, String server) {
		try {
			proxy.conectar(server, name);
			return true;
		} catch (JahConectadoException | NaoPossivelConectarException | ArquivoMultiplayerException e) {
			tableController.informError("Ocorreu um erro ao tentar estabelecer conexão com o servidor.");
			return false;
		}
	}

	public boolean disconnect() {
		try {
			proxy.desconectar();
			tableController.disconnect();
		} catch (NaoConectadoException e) {
			tableController.informMessage("Você já está desconectado");
		}
		return true;
	}

	public void receiveStartRequest() {
		// TODO - implement ActorNetGames.receiveStartRequest
		throw new UnsupportedOperationException();
	}

	public String getNameRemotePlayer() {
		// TODO - implement ActorNetGames.getNameRemotePlayer
		throw new UnsupportedOperationException();
	}

	public boolean startGame() {
		// TODO - implement ActorNetGames.startGame
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param table
	 */
	public void receiveTable(Table table) {
		// TODO - implement ActorNetGames.receiveTable
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param table
	 */
	public void sendMove(Table table) {
		try {
			proxy.enviaJogada(table);
		} catch (NaoJogandoException e) {
			tableController.informError("Você deve estar logado para realizar essa ação!");
		}
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		tableController.startNewGame();
	}

	@Override
	public void finalizarPartidaComErro(String message) {
		tableController.informError(message);
	}

	@Override
	public void receberMensagem(String msg) {
	}

	@Override
	public void receberJogada(Jogada jogada) {
		tableController.treatMove((Table)jogada);		
	}

	@Override
	public void tratarConexaoPerdida() {
		tableController.informError("Conexão perdida. Por favor, conecte-se novamente.");
		
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		tableController.informError("Não foi possível iniciar a partida.\nProvavelmente não existem outros jogadores conectados.");		
	}

}