/**
 * 
 */
package br.com.mj.blackjack.model;

import java.io.Serializable;

/**
 * Um enum que representa o naipe
 * @author Jhonatan da Rosa
 * @version v1.0, 23/04/2009
 */
public enum Naipe implements Serializable {
   /**Espadas*/
   ESPADAS('E'),
   /**Paus*/
   PAUS('P'),
   /**Copas*/
   COPAS('C'),
   /**Ouros*/
   OUROS('O');
   
   /** O caracter que representa o naipe */ 
   private char valorNaipe;
   
   /**
    * O construtor 
    * @param valorNaipe caracter que representa o naipe
    */
   private Naipe(char valorNaipe) {
      this.valorNaipe = valorNaipe;
   }

   /**
    * Retorna o caracter que representa o naipe
    * @return o caracter
    */
   public char getValorNaipe() {
      return this.valorNaipe;
   }

   /**
    * O naipe como um objeto String
    * @see java.lang.Enum#toString()
    */
   public String toString() {
      return Character.toString(this.valorNaipe);
   }
   
}//fim do enum Naipe
