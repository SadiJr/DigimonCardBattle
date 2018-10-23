package rede;
import java.util.List;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import logica.AtorJogador;

public class AtorNetgames implements OuvidorProxy {
	private static final long serialVersionUID = 1L;
	protected AtorJogador atorJogador;
	protected Proxy proxy;

	public AtorNetgames(AtorJogador atorJogador) {
		this.atorJogador = atorJogador;
		this.proxy = Proxy.getInstance();
		proxy.addOuvinte(this);
	}
	
	public void enviarDesistencia() {
		Jogada jogada = new Desistencia();
		try {
			proxy.enviaJogada(jogada);
		} catch (NaoJogandoException e) {
			/* não deverá ocorrer */
		}
	}
	
	public void enviarRecusaDeRevanche() {
		Jogada jogada = new RecusaDeRevanche();
		try {
			proxy.enviaJogada(jogada);
		} catch (NaoJogandoException e) {
			/* não deverá ocorrer */
		}
	}
	
	public void enviarSolicitacaoDeRevanche() {
		Jogada jogada = new SolicitacaoDeRevanche();
		try {
			proxy.enviaJogada(jogada);
		} catch (NaoJogandoException e) {
			/* não deverá ocorrer */
		}
	}

	public void desconectar() {
		try {
			proxy.desconectar();
		} catch (NaoConectadoException e) {
			/* não deverá ocorrer */
		}
	}

	public boolean solicitarConexao(String servidor, String nomeJogador) {
		boolean exitoConexao = true;
		try {
			proxy.conectar(servidor, nomeJogador);
		} 
		catch (Exception e) {
			exitoConexao = false;
		}
		return exitoConexao;
	}

	public void solicitarInicio() {
		try {
			proxy.iniciarPartida(2);
		} catch (NaoConectadoException e) {
			/* não deverá ocorrer */
		}
	}

	public String informarNomeDoAdversario(int posicao) {
		String nomeJogador = proxy.obterNomeAdversario(posicao); 
		return nomeJogador;
	}

	public void receberJogada(Jogada jogada) {
		if (jogada instanceof Resultado) {
			Resultado resultado = (Resultado)jogada;
			atorJogador.tratarRecebimentoDeResultado(resultado);		
		}
		else if (jogada instanceof Desistencia) {
			atorJogador.tratarRecebimentoDeDesistencia();
		}
		else if (jogada instanceof SolicitacaoDeRevanche) {
			atorJogador.tratarRecebimentoDeSolicitacaoDeRevanche();
		}
		else {
			atorJogador.tratarRecebimentoDeRecusaDeRevanche();
		}
	}

	public void enviarResultado(Resultado resultado) {
		try {
			proxy.enviaJogada(resultado);
		} 
		catch (NaoJogandoException e) {
			/* não deverá acontecer */
		}
	}
	
	public void reiniciarPartida() {
		try {
			proxy.reiniciarPartida();
		} catch (NaoConectadoException e) {
			/* não deverá ocorrer */
		} catch (NaoJogandoException e) {
			/* não deverá ocorrer */
		}
	}

	public boolean adversarioConectado() {
		List<String> nomesAdversarios = proxy.obterNomeAdversarios();
		int quantidadeAdversarios = nomesAdversarios.size();
		return quantidadeAdversarios != 0;
	}
	
	@Override
	public void tratarPartidaNaoIniciada(String message) {
		atorJogador.tratarFaltaDeAdversario();
	}

	public String getNomeJogador() {
		String nomeJogador = proxy.getNomeJogador();
		return nomeJogador;
	}
	
	@Override
	public void iniciarNovaPartida(Integer posicao) {
		atorJogador.tratarInicioDePartida(posicao);
	}

	@Override
	public void finalizarPartidaComErro(String message) {
		atorJogador.tratarRecebimentoDeDesistencia();
	}

	@Override
	public void receberMensagem(String message) {
		
	}
	
	@Override
	public void tratarConexaoPerdida() {
		
	}	
}