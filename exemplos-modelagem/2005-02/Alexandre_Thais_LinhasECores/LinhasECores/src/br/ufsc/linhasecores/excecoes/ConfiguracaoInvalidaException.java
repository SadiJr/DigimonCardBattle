/*
 * ConfiguracaoInvalidaException.java
 *
 * Created on 20 de Novembro de 2005, 18:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.ufsc.linhasecores.excecoes;

/**
 *
 * @author xande
 */
public class ConfiguracaoInvalidaException extends java.lang.Exception
{
   
   /**
    * Creates a new instance of <code>ConfiguracaoInvalidaException</code> without detail message.
    */
   public ConfiguracaoInvalidaException()
   {
   }
   
   
   /**
    * Constructs an instance of <code>ConfiguracaoInvalidaException</code> with the specified detail message.
    * @param msg the detail message.
    */
   public ConfiguracaoInvalidaException(String msg)
   {
      super(msg);
   }
}
