package aRede;

import javax.swing.JOptionPane;

import joormanji.AtorJogador;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

public class AtorARede implements OuvidorProxy {

	private AtorJogador atorJogador;
	
	private Proxy proxy;
	
	private boolean ehMinhaVez = false;
	
	public AtorARede(AtorJogador atorJogador){
		super();
		this.atorJogador = atorJogador;
		proxy = Proxy.getInstance();
		proxy.addOuvinte(this);
	}
	
	/**
	 * Conecta o jogo ao servidor.
	 * @param nome
	 * @param servidor
	 */
	public void conectar(String nome, String servidor){
		try {
			proxy.conectar(servidor, nome);
		} catch (JahConectadoException e) {
			JOptionPane.showMessageDialog(atorJogador.getFrame(), e.getMessage());
			e.printStackTrace();
		} catch (NaoPossivelConectarException e) {
			JOptionPane.showMessageDialog(atorJogador.getFrame(), e.getMessage());
			e.printStackTrace();
		} catch (ArquivoMultiplayerException e) {
			JOptionPane.showMessageDialog(atorJogador.getFrame(), e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Inicia uma partida entre cliente e servidor.
	 * Através dessa partida os participantes do chat
	 * irão trocar mensagens.
	 */
	public void iniciarPartidaRede(){
		try {
			proxy.iniciarPartida(2);
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(atorJogador.getFrame(), e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void iniciarNovaPartida(Integer posicao) {
		if (posicao == 1){
			ehMinhaVez = true;
		}else if (posicao == 2){
			ehMinhaVez = false;
		}
		
		atorJogador.iniciarPartidaRede(ehMinhaVez);

	}
	
	public void enviarJogada(int valorDoDado, boolean tireiCinco){
		
		lance msg = new lance(valorDoDado, tireiCinco);
		try {
			proxy.enviaJogada(msg);
			ehMinhaVez = false;
		} catch (NaoJogandoException e) {
			JOptionPane.showMessageDialog(atorJogador.getFrame(), e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void receberJogada(Jogada jogada) {
		lance msg = (lance) jogada;
		atorJogador.receberLanceRede(msg.getLance(), msg.getTirouCinco());
		ehMinhaVez = true;
	}
	
	public void desconectar(){
		try {
			proxy.desconectar();
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(atorJogador.getFrame(), e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void finalizarPartidaComErro(String message) {
		JOptionPane.showMessageDialog(atorJogador.getFrame(), message);

	}

	@Override
	public void tratarConexaoPerdida() {
		JOptionPane.showMessageDialog(atorJogador.getFrame(), "A conexão com o servidor foi perdida!");

	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		JOptionPane.showMessageDialog(atorJogador.getFrame(), "Não foi possível iniciar a conversa");

	}
	
	@Override
	public void receberMensagem(String msg) {
		// TODO Auto-generated method stub

	}

	public String obterNomeAdversario() {
		String nome = "";
		if (ehMinhaVez){
			nome = proxy.obterNomeAdversario(2);
		}else{
			nome = proxy.obterNomeAdversario(1);
		}
		
		return nome;
	}

	public boolean ehMinhaVez() {
		return ehMinhaVez;
	}

}
