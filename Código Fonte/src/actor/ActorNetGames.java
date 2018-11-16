package actor;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import controll.TableController;
import model.Table;

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
		} catch (JahConectadoException e) {
			tableController.informError("Você já está conectado!");
			e.printStackTrace();
			return false;
		} catch(NaoPossivelConectarException | ArquivoMultiplayerException e) {
			tableController.informError("Ocorreu um erro ao tentar estabelecer conexão com o servidor.");
			e.printStackTrace();
			return false;
		}
	}

	public void disconnect() {
		try {
			proxy.desconectar();
		} catch (NaoConectadoException e) {
			tableController.informMessage("Você já está desconectado");
		}
	}

	public String getNameRemotePlayer() throws Exception {
		return proxy.obterNomeAdversarios().get(0);
	}
	
	public void startGame() {
		try {
			proxy.iniciarPartida(2);
		} catch (Exception e) {
			tableController.informError("Houve um erro ao tentar iniciar uma partida. Você está conectado?");
			e.printStackTrace();
		}
	}

	public void sendMove(Table table) {
		try {
			Jogada move = (Jogada) table;
			proxy.enviaJogada(move);
		} catch (NaoJogandoException e) {
			tableController.informError("Você deve estar logado para realizar essa ação!");
			e.printStackTrace();
		}
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		tableController.startNewGame(posicao);
	}

	@Override
	public void finalizarPartidaComErro(String message) {
		tableController.informError(message);
		tableController.exit();
	}

	@Override
	public void receberMensagem(String msg) {
		tableController.informMessage(msg);
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