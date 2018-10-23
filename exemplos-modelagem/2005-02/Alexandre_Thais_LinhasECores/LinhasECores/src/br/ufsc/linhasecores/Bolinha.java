package br.ufsc.linhasecores;

import br.ufsc.linhasecores.excecoes.CorInvalidaException;

public class Bolinha
{
   public static final int MARRON   = 0;
   public static final int VERMELHO = 1;
   public static final int VERDE    = 2;
   public static final int AMARELO  = 3;
   public static final int AZUL     = 4;
   public static final int ROSA     = 5;
   public static final int BRANCO   = 6;
   
   private int cor;
   
   public Bolinha(int cor)
   throws CorInvalidaException
   {
      if ( cor <= BRANCO )
      {
         this.cor = cor;
      }      
      else
      {
         throw new CorInvalidaException ("Tentou-se criar uma bolinha com a cor " + cor + 
            ", mas o valor máximo aceito para cor é 6");
      }
   }
         
   public int getCor()
   {
      return cor;
   }
}

