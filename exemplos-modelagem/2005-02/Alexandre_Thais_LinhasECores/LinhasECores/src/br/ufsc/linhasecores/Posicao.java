package br.ufsc.linhasecores;

public class Posicao
{
   
   private boolean ocupada;
   private Bolinha bolinha;
   private Tabuleiro tabuleiro;
   private int linha;
   private int coluna;
   
   public Posicao (int linha, int coluna, Tabuleiro tabuleiro)
   {
      setLinhaEColuna (linha, coluna);
      setTabuleiro (tabuleiro);
   }
   
   public void setTabuleiro(Tabuleiro t)
   {
      tabuleiro = t;
   }
   
   public Tabuleiro getTabuleiro()
   {
      return tabuleiro;
   }
   
   public void setBolinha(Bolinha bolinha)
   {
      this.bolinha = bolinha;      
   }
   
   public Bolinha getBolinha()
   {
      return bolinha;
   }
   
   public boolean isOcupada()
   {
      return (bolinha != null);
   }
   
   public int getLinha()
   {
      return linha;
   }
   
   public int getColuna()
   {
      return coluna;
   }
   
   public void setLinhaEColuna (int l, int c)
   {
      linha = l; 
      coluna = c;
   }
}

