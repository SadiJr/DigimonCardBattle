/**
 * 
 */
package br.com.mj.blackjack.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe que representa a mesa do jogo
 * Contém os jogadores e o baralho
 * 
 * @author Jhonatan da Rosa
 * @version v1.0, 26/04/2009
 */
public class Mesa implements Serializable {
   /**The serial version UID*/
	private static final long serialVersionUID = -1780672380014194925L;
   /**Os jogadores da mesa*/
   private List<Jogador> jogadores;
   /**O baralho da mesa*/
   private Baralho baralho;

   /**O jogador atual*/
   private Jogador jogadorAtual;
   /**
    * Inicializa a lista de jogadores e o baralho, e embaralha
    */
   public Mesa() {
      this.jogadores = new ArrayList<Jogador>();
      this.baralho = new Baralho();
      this.baralho.embaralhar();
   }

   /**
    * Retorna os jogadores da mesa
    * @return os jogadores
    */
   public List<Jogador> getJogadores() {
      return this.jogadores;
   }

   /**
    * Seta os jogadores na mesa
    * @param jogadores
    */
   public void setJogadores(List<Jogador> jogadores) {
      this.jogadores = jogadores;
   }

   /**
    * Retorna o baralho da mesa
    * @return o baralho
    */
   public Baralho getBaralho() {
      return this.baralho;
   }

   /**
    * Seta o baralho da mesa
    * @param baralho
    */
   public void setBaralho(Baralho baralho) {
      this.baralho = baralho;
   }
   /**
    * Adiciona um jogador a mesa
    * @param jogador
    */
   public void adicionaJogador(Jogador jogador) {
      this.jogadores.add(jogador);
   }
   
   /**
    * Distribui duas cartas para cada jogador.
    */
   public void distribuiCartas() {
      
	  for(Jogador j : this.jogadores) {
         j.limparMao();

         j.getCartaDoBaralho();
         j.getCartaDoBaralho();
         
         analizaMaoJogadores(j);
      }
   }
   /**
    * Retorna o jogador atual
    * @return jogador atual
    */
   public Jogador getJogadorAtual() {
	   if(this.jogadorAtual == null)this.jogadorAtual = jogadores.get(0);
	   return this.jogadorAtual;
   }
   /**
    * Verifica se o jogador possui um blackjack
    * @param jogador
    * @return 
    */
   public boolean isBlackjack(Jogador jogador){
	   if(jogador.getValorDaMao() != 21) return false;
	   
	   List<Carta> cartas = jogador.getCartasDaMao();
	   
	   if(cartas.size() != 2) return false;
	   
	   if(!cartas.get(0).getNaipe().equals(Naipe.ESPADAS) || cartas.get(0).getNaipe().equals(Naipe.PAUS)) return false;
	   if(!cartas.get(0).getNumero().equals(NumeroCarta.AS) || cartas.get(0).getNumero().equals(NumeroCarta.VALETE)) return false;
	   
	   if(!cartas.get(1).getNaipe().equals(Naipe.ESPADAS) || cartas.get(1).getNaipe().equals(Naipe.PAUS)) return false;
	   if(!cartas.get(1).getNumero().equals(NumeroCarta.AS) || cartas.get(1).getNumero().equals(NumeroCarta.VALETE)) return false;
	   
	   return true;
   }
   /**
    * Verifica se o jogador possui cinco cartas charlie
    * @param jogador
    * @return
    */
   public boolean isCincoCartasCharlie(Jogador jogador){
	   if(jogador.getValorDaMao() > 21) return false;
	   
	   List<Carta> cartas = jogador.getCartasDaMao();
	   
	   if(cartas.size() != 5) return false;
	   
	   return true;
   }
   /**
    * Verifica o ganhador e retorna ele
    * @return o ganhador
    * @throws EmpateException se o jogo empatou
    */
   public Jogador getGanhador() throws EmpateException {
      int pontosGanhador = 0;
      int indiceGanhador = -1;
      boolean draw = false;
      boolean blackjack = false;
      boolean cincoCartasCharlie = false;
      
      for(Jogador jogador : jogadores) {
         if(!jogador.isFora()) {
        	 
        	if(isBlackjack(jogador)){
        		
        		jogador.setBlackjack(true);
        		
        		if(indiceGanhador != -1 && blackjack){
        			draw = true;
        			break;
        		}else{
        			indiceGanhador = jogadores.indexOf(jogador);
        			blackjack = true;
        			continue;
        		}
        	}
        	
        	if(!blackjack && isCincoCartasCharlie(jogador)){
        		jogador.setCincoCartasCharlie(true);
        		
        		if(indiceGanhador != -1 && cincoCartasCharlie){
        			draw = true;
        		}else{
        			indiceGanhador = jogadores.indexOf(jogador);
        		}
        		cincoCartasCharlie = true;
        		continue;
        	}
        	
            if(jogador.getValorDaMao() > pontosGanhador) {
               pontosGanhador = jogador.getValorDaMao();
               indiceGanhador = jogadores.indexOf(jogador);
               draw = false;
            }else if(jogador.getValorDaMao() == pontosGanhador){
               draw = true;
            }
         }
      }
      if(draw) {
         throw new EmpateException("O jogo resultou em Empate!");
      }
      if(indiceGanhador >= 0 && indiceGanhador <= jogadores.size()) {
         return jogadores.get(indiceGanhador);
      }
      return null;
   }
   /**
    * Limpa a lista de jogadores
    */
   public void limpaJogadores() {
      this.jogadores.clear();
   }
   /**
    * Seta o proximo jogador
    * @throws SemMaisJogadoresException
    */
   public void proximoJogador() throws SemMaisJogadoresException {
      int indice = jogadores.indexOf(this.jogadorAtual);
      if(indice + 1 == jogadores.size()) {
         throw new SemMaisJogadoresException("Sem mais jogadores para passar!");
      }
      this.jogadorAtual =  jogadores.get(indice+1);
   }
   /**
    * Retorna o numero de jogadores ativos no jogo
    * @return 
    */
   public int getNumeroJogadoresAtivos() {
      int cont = 0;
      for(Jogador player : jogadores) {
         if(!player.isFora())cont++;
      }
      return cont;
   }
   
   /**
    * Analisa a mão do jogador e verifica se ele está fora, possui blackjack ou cinco cartas charlie
    * @param jogador
    */
   public void analizaMaoJogadores(Jogador jogador){
      int valor = jogador.getValorDaMao();
      if(valor > 21) {
         jogador.setFora(true);
      }else {
    	 if(isBlackjack(jogador)){
    		 jogador.setBlackjack(true); 
    	 }else if(isCincoCartasCharlie(jogador)){
    		 jogador.setCincoCartasCharlie(true);
    	 }
      }
   }
   /**
    * Busca o ganhador e retorna uma mensagem indicando
    */
   public String mostraGanhador() {
      Jogador ganhador = null;
      try {
         ganhador = getGanhador();
      }catch(EmpateException e) {
         return e.getMessage();
      }
      
      if(ganhador == null) {
         if(getNumeroJogadoresAtivos() == 0) {
            return "Todos estão fora!";
         }
      }else {
         return String.format("%s é o ganhador!",ganhador);
      }
      return null;
   }
   /**
    * Volta as cartas nas mãos dos jogadores para a mesa
    */
   public void voltaCartasParaMesa() {
      for(Jogador jogador : jogadores) {
         for(Carta carta : jogador.getCartasDaMao()) {
            this.baralho.adicionaCarta(carta);
         }
         jogador.limparMao();
      }
   }
   /**
    * Seta todos os jogadores Ativos
    */
   public void setTodosJogadoresAtivos() {
      for(Jogador player : jogadores) {
         player.setFora(false);
      }
   }
}//fim da classe Mesa
