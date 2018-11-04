/**
 * 
 */
package br.com.mj.blackjack.control;

import java.util.List;

import br.com.mj.blackjack.model.Carteador;
import br.com.mj.blackjack.model.JogadaBlackjack;
import br.com.mj.blackjack.model.Jogador;
import br.com.mj.blackjack.model.Mesa;
import br.com.mj.blackjack.model.SemMaisJogadoresException;
import br.com.mj.blackjack.net.AtorRede;

/**
 * This class is responsable to control the game
 * 
 * @author Jhonatan da Rosa
 * @version v1.0 27/04/2009
 */
public class BJControl {
	/** The view controller */
	private BJViewControl viewControl;
	/** The table */
	private Mesa mesa;
	
	private AtorRede atorRede;

	/**
	 * The default constructor
	 */
	public BJControl() {
		this.viewControl = new BJViewControl(this);
		this.atorRede = new AtorRede(this,this.viewControl.getView());
		//this.viewControl.setAtorRede(atorRede);
		this.mesa = new Mesa();
	}
	
	
	public BJViewControl getViewControl() {
		return viewControl;
	}


	public void setViewControl(BJViewControl viewControl) {
		this.viewControl = viewControl;
	}


	public Mesa getMesa() {
		return mesa;
	}


	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}


	public AtorRede getAtorRede() {
		return atorRede;
	}


	public void setAtorRede(AtorRede atorRede) {
		this.atorRede = atorRede;
	}


	/**
	 * Initiate the game
	 */
	public void iniciaJogo() {
		this.viewControl.mostraTelaInicial();
	}

	/**
	 * Show the main Menu
	 */
	public void mostraTelaInicial() {
		this.viewControl.mostraTelaInicial();
	}

	/**
	 * Starts a new game.
	 */
	public void novoJogo() {
		this.mesa.limpaJogadores();
		int numeroJogadores = this.viewControl.getNumeroDeJogadores();
		Carteador carteador = new Carteador(this.mesa);
		this.mesa.adicionaJogador(carteador);
		this.viewControl.adicionaJogador(carteador);
		
		// create the players
		String nome = null;//this.viewControl.getNomeDoJogador(1);
		for (int b = 0; b < numeroJogadores; b++) {
			nome = this.viewControl.getNomeDoJogador(b + 1);
			Jogador jogador = new Jogador(nome, b + 1, this.mesa);
			this.mesa.adicionaJogador(jogador);
			this.viewControl.adicionaJogador(jogador);
		}
		
		this.mesa.distribuiCartas();
		this.viewControl.atualizaMaoJogadores();
		procederJogada(null);
	}
	
	
	public void iniciarJogo(int nrJogadores){
		this.viewControl.mostraMensagemTela("iniciando jogo");
		
		
		this.atorRede.iniciarPartidaRede(nrJogadores);
	}

	/**
	 * Get's the player's option
	 */
	public void procederJogada(JogadaBlackjack jogada) {
		String resultado = null;
		Jogador jogadorAtual = null;

		jogadorAtual = this.mesa.getJogadorAtual();

		if (jogadorAtual instanceof Carteador) {
			mostraMensagemTela("É a vez do carteador!");
			((Carteador) jogadorAtual).jogar();
			this.mesa.analizaMaoJogadores(jogadorAtual);
			this.viewControl.atualizaMaoJogadores();
			try {
				this.mesa.proximoJogador();
				mostraMensagemTela(String.format("É a vez de %s!",this.mesa.getJogadorAtual()));
			} catch (SemMaisJogadoresException e) {
				mostraMensagemTela(e.getMessage());
				resultado = this.mesa.mostraGanhador();
			}
		} else {
			if (jogada.equals(JogadaBlackjack.PEGAR)) {
				jogadorAtual.getCartaDoBaralho();
				this.mesa.analizaMaoJogadores(jogadorAtual);
				this.viewControl.atualizaMaoJogadores();
				if(jogadorAtual.isFora()){
					try {
						this.mesa.proximoJogador();
						this.verificaVez();
						this.sincronizaMesa();
						this.habilitaDesabilitaBotoes();
					} catch (SemMaisJogadoresException e) {
						mostraMensagem(e.getMessage());
						resultado = this.mesa.mostraGanhador();
					}
				}
				mostraMensagemTela(String.format("É a vez de %s!",this.mesa.getJogadorAtual()));
			} else if (jogada.equals(JogadaBlackjack.PASSAR)) {

				try {
					this.mesa.proximoJogador();
					mostraMensagemTela(String.format("É a vez de %s!",this.mesa.getJogadorAtual()));
				} catch (SemMaisJogadoresException e) {
					mostraMensagem(e.getMessage());
					resultado = this.mesa.mostraGanhador();
				}

			}
		}
		if (this.mesa.getNumeroJogadoresAtivos() == 1) {
			resultado = this.mesa.mostraGanhador();
		}
		if(resultado != null){
			mostraMensagem(resultado);
		}
		
	}
	public void mostraMensagemTela(String msg){
		this.viewControl.mostraMensagemTela(msg);
	}
	/**
	 * Delegates for view control to show a message
	 * 
	 * @param msg
	 *            the message to show
	 */
	public void mostraMensagem(String msg) {
		this.viewControl.mostraMensagem(msg);
	}

	public void conectar(String nick, String servidor) {
		this.atorRede.conectar(nick, servidor);
		this.atorRede.setNickJogador(nick);
	}

	public void iniciarPartidaRede(int nrJogadores) {
		this.mesa.voltaCartasParaMesa();
		this.mesa.limpaJogadores();
		
		Carteador carteador = new Carteador(this.mesa);
		this.mesa.adicionaJogador(carteador);
		this.viewControl.adicionaJogador(carteador);
		
		Jogador jogador = new Jogador(this.atorRede.getNickJogador(),1,this.mesa);
		this.mesa.adicionaJogador(jogador);
		this.viewControl.adicionaJogador(jogador);
		
		if(nrJogadores > 1)
			mostraMensagem("Aguardando outros jogadores");
		List<String> nomesAdversarios = null;
		
		while((nomesAdversarios = atorRede.obterNomeAdversarios()).size() != nrJogadores -1){
			
		}
		int index = 2;
		
		for(String nomeAdversario : nomesAdversarios){
			Jogador adversario = new Jogador(nomeAdversario,index++,this.mesa);
			this.mesa.adicionaJogador(adversario);
			this.viewControl.adicionaJogador(adversario);
		}
		
		this.mesa.distribuiCartas();
		this.procederJogada(null);
		this.atorRede.iniciarPartidaRede(nrJogadores);
	}
	
	public void sincronizaMesa(){
		this.viewControl.sincronizaMesa();
		this.viewControl.atualizaMaoJogadores();
	}


	public void desconectar() {
		this.atorRede.desconectar();
	}

	public void enviaJogadaRede(JogadaBlackjack jogada) {
		this.atorRede.enviarJogada(jogada);
	}
	public void habilitaDesabilitaBotoes(){
		this.viewControl.habilitaDesabilitaBotoes();
	}
	
	public boolean ehMinhaVez(){
		return this.atorRede.ehMinhaVez();
	}

	public void enviaMensagem(String msg) {
		this.atorRede.enviaMensagem(msg);
	}
	
	public void verificaVez(){
		if(this.atorRede.getNickJogador().equals(this.mesa.getJogadorAtual().getNome())){
			this.atorRede.setMinhaVez(true);
		}else{
			this.atorRede.setMinhaVez(false);
		}
	}
	
	public void sair(){
		System.exit(0);
	}
	
}// end of BJControl class
