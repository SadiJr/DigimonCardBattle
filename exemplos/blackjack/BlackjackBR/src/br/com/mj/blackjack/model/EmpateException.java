/**
 * 
 */
package br.com.mj.blackjack.model;

import java.io.Serializable;

/**
 * A Exceção Empate
 * @author Jhonatan da Rosa
 * @version v1.0 29/04/2009
 */
public class EmpateException extends java.lang.Exception implements Serializable {

   /**The serial version UID*/
   private static final long serialVersionUID = -923401069683651641L;

   /**
    * Construtor padrão
    */
   public EmpateException() {
      this("Empate");
   }

   /**
    * @param arg0 a mensagem
    * @param arg1 a exceção causa
    */
   public EmpateException(String arg0, Throwable arg1) {
      super(arg0, arg1);
   }

   /**
    * @param arg0 a mensagem
    */
   public EmpateException(String arg0) {
      super(arg0);
   }

   /**
    * @param arg0 a exceção causa
    */
   public EmpateException(Throwable arg0) {
      super(arg0);
   }
   
}//fim da classe EmpateException
