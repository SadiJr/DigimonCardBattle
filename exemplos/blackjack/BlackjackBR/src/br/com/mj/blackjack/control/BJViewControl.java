package br.com.mj.blackjack.control;

import br.com.mj.blackjack.model.JogadaBlackjack;
import br.com.mj.blackjack.model.Jogador;
import br.com.mj.blackjack.view.AtorJogador;

public class BJViewControl {
	/**The view*/
	   private AtorJogador view;
	   
	   private BJControl control;
	   
	   /**
	    * The default constructor
	    */
	   public BJViewControl(BJControl control) {
		   this.control = control;
	       view = new AtorJogador(this);
	   }
	   
	   public AtorJogador getView() {
		return view;
	}

	public void setView(AtorJogador view) {
		this.view = view;
	}

	public BJControl getControl() {
		return control;
	}

	public void setControl(BJControl control) {
		this.control = control;
	}

	/**
	    * Delegates for view to show the welcome message
	    */
	   public void mostraTelaInicial() {
	      this.view.setLocationRelativeTo(null);
	      this.view.setVisible(true);
	      this.view.mostraTelaInicial();
	   }
	   
	   /**
	    * Retorna o número de Jogadores
	    * @return número de jogadores
	    */
	   public int getNumeroDeJogadores() {
	      
	      int opcao = -1;
	      //
	      while(true) {
	         opcao = this.view.getNumeroDeJogadores();
	         //
	         if(opcao == -1) {//the -1 means that occurred the NumberFormatException
	            this.view.mostraMensagem("Apenas numeros são aceitos!\n");
	         }else if(opcao > 4 || opcao < 1) {//incorrect numbers
	            this.view.mostraMensagem("Numero incorreto de jogadores!\n");
	         }else {
	            break;
	         }
	      }//end while loop;
	      
	      return opcao;
	   }
	   
	   public void adicionaJogador(Jogador jogador){
		   this.view.adicionaJogador(jogador);
	   }
	   
	   public void atualizaMaoJogadores(){
		   this.view.atualizaMaoJogadores();
	   }
	   
	   public void sair(){
		   this.control.sair();
	   }
	   
	   /**
	    * Get's a player's name 
	    * @param numero the number of the player
	    * @return the player's name.
	    */
	   public String getNomeDoJogador(int numero){
	      return this.view.getNomeDoJogador(numero);
	   }
	   
	   /**
	    * Delegates for view to show a message
	    * @param msg the message to show
	    */
	   public void mostraMensagem(String msg) {
	      this.view.mostraMensagem(msg);
	   }
	   
	public void novoJogo() {
		this.control.novoJogo();
	}
	public void procederLance(JogadaBlackjack jogada){
		this.control.procederJogada(jogada);
	}
	public void mostraMensagemTela(String msg){
		this.view.mostraMensagemTela(msg);
	}

	public void conectar(String nick, String servidor) {
		this.control.conectar(nick,servidor);
	}

	public void iniciarPartidaRede(int nrJogadores) {
		this.control.iniciarPartidaRede(nrJogadores);
	}
	
	public boolean ehMinhaVez(){
		return this.control.ehMinhaVez();
	}
	
	public void sincronizaMesa(){
		this.view.sincronizaMesa(this.control.getMesa());
	}

	public void desconectar() {
		this.control.desconectar();
	}

	public void enviaJogadaRede(JogadaBlackjack jogada) {
		this.control.enviaJogadaRede(jogada);
	}

	public void habilitaDesabilitaBotoes() {
		this.view.habilitaDesabilitaBotoes();
	}

	public void enviaMensagem(String msg) {
		this.control.enviaMensagem(msg);
	}
}
