/*
 * TamanhoInvalidoException.java
 *
 * Created on 20 de Novembro de 2005, 17:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.ufsc.linhasecores.excecoes;

/**
 *
 * @author xande
 */
public class TamanhoInvalidoException extends ConfiguracaoInvalidaException
{
   
   /**
    * Creates a new instance of <code>TamanhoInvalidoException</code> without detail message.
    */
   public TamanhoInvalidoException()
   {
   }
   
   
   /**
    * Constructs an instance of <code>TamanhoInvalidoException</code> with the specified detail message.
    * @param msg the detail message.
    */
   public TamanhoInvalidoException(String msg)
   {
      super(msg);
   }
}
