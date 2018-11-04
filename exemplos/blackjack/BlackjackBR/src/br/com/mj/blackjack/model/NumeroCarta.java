/**
 * 
 */
package br.com.mj.blackjack.model;

import java.io.Serializable;

/**
 * Um enum que representa o Numero de uma Carta
 * @author Jhonatan da Rosa
 * @version v1.0, 23/04/2009
 */
public enum NumeroCarta implements Serializable {
   /**Ás*/
   AS(1,"A",11),
   /**Dois*/
   DOIS(2,"2"),
   /**Três*/
   TRES(3,"3"),
   /**Quatro*/
   QUATRO(4,"4"),
   /**Cinco*/
   CINCO(5,"5"),
   /**Seis*/
   SEIS(6,"6"),
   /**Sete*/
   SETE(7,"7"),
   /**Oito*/
   OITO(8,"8"),
   /**Nove*/
   NOVE(9,"9"),
   /**Dez*/
   DEZ(10,"10"),
   /**Valete*/
   VALETE(10,"J"),
   /**Dama*/
   DAMA(10,"Q"),
   /**Rei*/
   REI(10,"K");
   
   /** o valor do número da carta */
   private int valor;
   /** o segundo valor do número da carta */
   private int segundoValor;
   /** O nome representativo da carta */ 
   private String nomeRepresentativo;
   
   /**
    * Consturtor.
    * @param valor o valor do número da carta
    * @param nomeRepresentativo o nome representativo do número da carta
    */
   private NumeroCarta(int valor,String nomeRepresentativo) {
      this.valor = valor;
      this.nomeRepresentativo = nomeRepresentativo;
   }
   
   /**
    * Consturtor.
    * @param valor o valor do número da carta
    * @param nomeRepresentativo o nome representativo do número da carta
    * @param segundoValor o segundo valor do número da carta
    */
   private NumeroCarta(int valor,String nomeRepresentativo,int segundoValor) {
      this.valor = valor;
      this.nomeRepresentativo = nomeRepresentativo;
      this.segundoValor = segundoValor;
   }
   
   /**
    * O valor do número da carta
    * @return o valor
    */
   public int getValor() {
      return this.valor;
   }
   
   /**
    * O segundo valor do número da carta
    * @return o segundo valor
    */
   public int getSegundoValor() {
      return this.segundoValor;
   }

   /**
    * O nome representativo do número da carta
    * @return the representativeName
    */
   public String getNomeRepresentativo() {
      return this.nomeRepresentativo;
   }
   
   /**
    * Um objeto String que representa o número da carta
    * @return O nome representativo
    * @see java.lang.Enum#toString()
    */
   public String toString() {
      return nomeRepresentativo;
   }
}//fim do enum Número da Carta
