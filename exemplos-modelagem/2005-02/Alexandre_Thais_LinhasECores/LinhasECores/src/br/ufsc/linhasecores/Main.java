/*
 * Main.java
 *
 * Created on 12 de Outubro de 2005, 12:05
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package br.ufsc.linhasecores;

import br.ufsc.linhasecores.ui.ControladorJogo;

/**
 *
 * @author xande
 */
public class Main
{
   
   /** Creates a new instance of Main */
   public Main ()
   {
   }
   
   /**
    * @param args the command line arguments
    */
   public static void main (String[] args)
   {
      // TODO code application logic here
      ControladorJogo c = new ControladorJogo();
      c.run();
   }
   
}
