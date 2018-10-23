/*
 * CorInvalidaException.java
 *
 * Created on 30 de Outubro de 2005, 11:59
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package br.ufsc.linhasecores.excecoes;

/**
 * Exceção levantada quando tentamos criar uma bolinha
 * com uma cor inválida para o jogo.
 *
 * @author Alexandre Machado <axmachado@gmail.com>
 */
public class CorInvalidaException extends ConfiguracaoInvalidaException
{
   
   /**
    * Creates a new instance of <code>CorInvalidaException</code> without detail message.
    */
   public CorInvalidaException()
   {
   }
   
   
   /**
    * Constructs an instance of <code>CorInvalidaException</code> with the specified detail message.
    * @param msg the detail message.
    */
   public CorInvalidaException(String msg)
   {
      super(msg);
   }
}
