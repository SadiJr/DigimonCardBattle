/**
 * 
 */
package br.com.mj.blackjack.model;

import java.io.Serializable;

/**
 * A classe que representa uma carta
 * @author Jhonatan da Rosa
 * @version v1.0, 23/04/2009
 */
public class Carta implements Serializable  {
   /**The serial version UID*/
	private static final long serialVersionUID = -1943944428709280856L;
	/** O naipe da carta */ 
   private Naipe naipe;
   /** O número da carta */ 
   private NumeroCarta numero;
   
   /**
    * O construtor para a classe Carta.
    * @param naipe
    * @param numero
    */
   public Carta(Naipe naipe,NumeroCarta numero) {
      this.naipe = naipe;
      this.numero = numero;
   }

   /**
    * Retorna o naipe da carta 
    * @return o naipe
    */
   public Naipe getNaipe() {
      return this.naipe;
   }

   /**
    * Seta o naipe da carta
    * @param naipe
    */
   public void setNaipe(Naipe naipe) {
      this.naipe = naipe;
   }

   /**
    * Retorna o número da carta
    * @return o número
    */
   public NumeroCarta getNumero() {
      return this.numero;
   }

   /**
    * Seta o número da carta
    * @param numero 
    */
   public void setNumero(NumeroCarta numero) {
      this.numero = numero;
   }

   /**
    * Esta carta representada em um objeto String
    * @return o número concatenado com o naipe
    * @see java.lang.Object#toString()
    */
   public String toString() {
      return this.numero.toString()+this.naipe.toString();
   }
   /**
    * Retorna o valor da carta
    * @return o valor
    */
   public int getValor(){
	   return this.numero.getValor();
   }
   
   /**
    * Retorna o segundo valor da carta
    * @return o segundo valor
    */
   public int getSegundoValor(){
	   return this.numero.getSegundoValor();
   }

   /**
    * Retorna o código hash que identifica esta carta
    * @see java.lang.Object#hashCode()
    */
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((this.numero == null) ? 0 : this.numero.hashCode());
      result = prime * result + ((this.naipe == null) ? 0 : this.naipe.hashCode());
      return result;
   }

   /**
    * Este método compara um objeto com este 
    * e retorna true somente se todos os atributos
    * são iguais, caso contrário retorna false
    * 
    * @return <code>true</code> se igual e <code>false</code> caso contrário.
    * @see java.lang.Object#equals(java.lang.Object)
    */
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      final Carta other = (Carta) obj;
      if (this.numero == null) {
         if (other.numero != null)
            return false;
      } else if (!this.numero.equals(other.numero))
         return false;
      if (this.naipe == null) {
         if (other.naipe != null)
            return false;
      } else if (!this.naipe.equals(other.naipe))
         return false;
      return true;
   }
   
}//fim da classe carta
