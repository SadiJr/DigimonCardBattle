package br.com.mj.blackjack.net;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.mj.blackjack.control.BJControl;
import br.com.mj.blackjack.model.JogadaBlackjack;
import br.com.mj.blackjack.view.AtorJogador;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

public class AtorRede implements OuvidorProxy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9021773026015216233L;

	private AtorJogador ator;
	
	private Proxy proxy;
	
	private boolean ehMinhaVez = false;
	
	private BJControl control;
	
	private String nickJogador;
	
	public AtorRede(BJControl control,AtorJogador ator){
		super();
		this.ator = ator;
		this.control = control;
		proxy = Proxy.getInstance();
		proxy.addOuvinte(this);
		
	}
	
	
	public String getNickJogador() {
		return nickJogador;
	}


	public void setNickJogador(String nickJogador) {
		this.nickJogador = nickJogador;
	}
	
	public void setMinhaVez(boolean vez){
		this.ehMinhaVez = vez;
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
			JOptionPane.showMessageDialog(ator, e.getMessage());
			e.printStackTrace();
		} catch (NaoPossivelConectarException e) {
			JOptionPane.showMessageDialog(ator, e.getMessage());
			e.printStackTrace();
		} catch (ArquivoMultiplayerException e) {
			JOptionPane.showMessageDialog(ator, e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Inicia uma partida entre cliente e servidor.
	 * 
	 */
	public void iniciarPartidaRede(int nrJogadores){
		try {
			proxy.iniciarPartida(nrJogadores);
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(ator, e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void iniciarNovaPartida(Integer posicao) {
		if (posicao == 1){
			ehMinhaVez = true;
			sincronizaMesa();
		}else{
			ehMinhaVez = false;
		}
		//ator.iniciarPartidaRede();

	}
	
	public void sincronizaMesa(){
		if(ehMinhaVez){
			Estado estado = new Estado(control.getMesa());
			try {
				proxy.enviaJogada(estado);
			} catch (NaoJogandoException e) {
				JOptionPane.showMessageDialog(ator, e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void enviarJogada(Jogada jogada){
		
		try {
			proxy.enviaJogada(jogada);
		} catch (NaoJogandoException e) {
			JOptionPane.showMessageDialog(ator, e.getMessage());
			e.printStackTrace();
		}
		
		if(jogada instanceof JogadaBlackjack){
			JogadaBlackjack jbj = (JogadaBlackjack)jogada;
			if(jbj.equals(JogadaBlackjack.PASSAR)){
				ehMinhaVez = false;
				this.control.habilitaDesabilitaBotoes();
			}
		}
	}
	
	public void receberJogada(Jogada jogada) {
		if(jogada instanceof Estado){
			Estado estado = (Estado)jogada;
			this.control.setMesa(estado.getMesa());
			this.control.sincronizaMesa();
		}else if(jogada instanceof JogadaBlackjack){
			JogadaBlackjack jbj = (JogadaBlackjack)jogada;
			this.control.procederJogada(jbj);
			if(jbj.equals(JogadaBlackjack.PASSAR)){
				if(nickJogador.equals(this.control.getMesa().getJogadorAtual().getNome())){
					ehMinhaVez = true;
					this.control.habilitaDesabilitaBotoes();
				}
				//sincronizaMesa();
			}
		}else if(jogada instanceof Mensagem){
			Mensagem msg = (Mensagem)jogada;
			this.control.mostraMensagem(msg.getMensagem());
		}
	}
	
	
	public void desconectar(){
		try {
			proxy.desconectar();
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(ator, e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void finalizarPartidaComErro(String message) {
		JOptionPane.showMessageDialog(ator, message);

	}

	public void tratarConexaoPerdida() {
		JOptionPane.showMessageDialog(ator, "A conex�o com o servidor foi perdida!");

	}

	public void tratarPartidaNaoIniciada(String message) {
		JOptionPane.showMessageDialog(ator, "N�o foi poss�vel iniciar a conversa");

	}
	
	public void receberMensagem(String msg) {
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
	
	public List<String> obterNomeAdversarios(){
		return proxy.obterNomeAdversarios();
	}

	public boolean ehMinhaVez() {
		return ehMinhaVez;
	}


	public void enviaMensagem(String msg) {
		Mensagem mensagem = new Mensagem(nickJogador+" diz: "+msg);
		enviarJogada(mensagem);
	}

}
