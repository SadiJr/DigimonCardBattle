package NetGames;

import javax.swing.JOptionPane;

import DominioDoProblema.Lance;
import InterfaceGrafica.AtorJogador;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

public class AtorNetGames implements OuvidorProxy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2311786362989538570L;

	protected AtorJogador interfaceGrafica;

	protected Proxy proxy;
	protected boolean ehMinhaVez;

	public AtorNetGames(AtorJogador interfaceGrafica) {
		this.interfaceGrafica = interfaceGrafica;
		this.proxy = Proxy.getInstance();
		this.proxy.addOuvinte(this);
	}

	public int conectar(String servidor, String nome) {
		int resultado = -1;
		try {
			proxy.conectar(servidor, nome);
			resultado = 2;
		} catch (JahConectadoException e) {
			resultado = 0;
			e.printStackTrace();
		} catch (NaoPossivelConectarException e) {
			resultado = 1;
			e.printStackTrace();
		} catch (ArquivoMultiplayerException e) {
			resultado = 1;
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean desconectar() {
		try {
			proxy.desconectar();
			return true;
		} catch (NaoConectadoException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(interfaceGrafica.informarJanela(),
					"Erro: " + e.getMessage());
			return false;
		}
	}

	public void iniciarPartida() {
		try {
			proxy.iniciarPartida(new Integer(2));
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(interfaceGrafica.informarJanela(),
					e.getMessage());
			e.printStackTrace();
		}
	}

	public String informarNomeAdversario(String nome) {
		String jogNome1 = proxy.obterNomeAdversario(new Integer(1));
		String jogNome2 = proxy.obterNomeAdversario(new Integer(2));
		;
		if (jogNome1.equals(nome)) {
			return jogNome2;
		} else {
			return jogNome1;
		}
	}

	public void enviaJogada(Lance lance) {
		try {
			proxy.enviaJogada(lance);
			ehMinhaVez = false;
		} catch (NaoJogandoException e) {
			JOptionPane.showMessageDialog(interfaceGrafica.informarJanela(),
					e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		if (posicao == 1)
			ehMinhaVez = true;
		else
			ehMinhaVez = false;

		interfaceGrafica.tratarIniciarPartida(posicao);

	}

	@Override
	public void finalizarPartidaComErro(String message) {
		interfaceGrafica.encerraPartidaPorFalha();
		JOptionPane.showMessageDialog(interfaceGrafica.informarJanela(),
				"Jogador desconectou ou iniciou nova partida");

	}

	@Override
	public void receberMensagem(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receberJogada(Jogada jogada) {
		ehMinhaVez = true;
		Lance lance = (Lance) jogada;
		interfaceGrafica.receberJogada(lance);
	}

	@Override
	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		JOptionPane.showMessageDialog(interfaceGrafica.informarJanela(),
				"A partida nao pode ser iniciada.\n Erro:" + message);

	}

	public boolean ehMinhaVez() {
		return ehMinhaVez;
	}

}
