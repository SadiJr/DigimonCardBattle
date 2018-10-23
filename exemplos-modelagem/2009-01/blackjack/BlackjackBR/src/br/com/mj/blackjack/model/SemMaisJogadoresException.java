/**
 * 
 */
package br.com.mj.blackjack.model;

/**
 * A exceção SemMaisJogadoresException
 * Esta exceção representa que a mesa não possui mais jogadores para jogar
 * @author Jhonatan da Rosa
 * @version v1.0,26/04/2009
 */
public class SemMaisJogadoresException extends Exception {


   /**The serial version UID*/
   private static final long serialVersionUID = -3218440995607354289L;

   /**
    * Construtor padrão
    */
   public SemMaisJogadoresException() {
      this("Sem mais jogadores!");
   }

   /**
    * @param arg0 a mensagem
    * @param arg1 a exceção causa
    */
   public SemMaisJogadoresException(String arg0, Throwable arg1) {
      super(arg0, arg1);
   }

   /**
    * @param arg0 a mensagem
    */
   public SemMaisJogadoresException(String arg0) {
      super(arg0);
   }

   /**
    * @param arg0 a exceção causa
    */
   public SemMaisJogadoresException(Throwable arg0) {
      super(arg0);
   }

}//fim da classe SemMaisJogadoresException
