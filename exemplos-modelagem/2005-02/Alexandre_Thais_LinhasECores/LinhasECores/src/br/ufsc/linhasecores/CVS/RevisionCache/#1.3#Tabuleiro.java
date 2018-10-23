package br.ufsc.linhasecores;

import br.ufsc.linhasecores.excecoes.CorInvalidaException;
import java.util.ArrayList;

public class Tabuleiro
{
   
   private int tamanho;
   private Bolinha[] proximasBolinhas;
   private int numCores;
   private Posicao[][] posicoes;
   private Partida partida;
   private ArrayList<PosicaoLivre> posicoesLivres;
   private Record[] record;
   
   private class PosicaoLivre
   {
      private Posicao posicao;
      private int linha;
      private int coluna;
      
      public PosicaoLivre(int linha, int coluna, Posicao posicao)
      {
         setPosicao(posicao);
         setLinha(linha);
         setColuna(coluna);
      }
      
      public int getColuna()
      {
         return this.coluna;
      }
      
      public void setColuna(final int coluna)
      {
         this.coluna = coluna;
      }
      
      public int getLinha()
      {
         return this.linha;
      }
      
      public void setLinha(final int linha)
      {
         this.linha = linha;
      }
      
      public Posicao getPosicao()
      {
         return this.posicao;
      }
      
      public void setPosicao(final Posicao posicao)
      {
         this.posicao = posicao;
      }
   }
   
   public Tabuleiro(int tamanho)
   {
      posicoes = new Posicao[tamanho][tamanho];
      posicoesLivres = new ArrayList<PosicaoLivre>();
      for (int i=0; i < tamanho; i++)
      {
         for (int j=0; j < tamanho; j++)
         {
            posicoes[i][j] = new Posicao();
            posicoesLivres.add(new PosicaoLivre(i, j, posicoes[i][j]));
         }
      }
      this.tamanho = tamanho;
   }
   
   public int getTamanho()
   {
      return tamanho;
   }
   
   public void limpar()
   {
      for (int i=0; i < tamanho; i++)
      {
         for (int j=0; j < tamanho; j++ )
         {
            posicoes[i][j].apagarBolinha();
            posicoesLivres.add(new PosicaoLivre(i, j, posicoes[i][j]));
            AtorJogador.getInstance().desenharPosicao(i, j);
         }
      }
   }
   
   public synchronized void sortearBolinhas()
   throws CorInvalidaException
   {
      proximasBolinhas = new Bolinha[3];
      for (int i=0; i<3; i++)
      {
         proximasBolinhas[i] = new Bolinha((int)(Math.random()*7));
      }
      AtorJogador.getInstance().mostrarProximasBolinhas(proximasBolinhas);
   }
   
   public synchronized Bolinha[] getProximasBolinhas()
   {
      return proximasBolinhas;
   }
   
   public void inserirBolinhas()
   {
      for (int i=0; i < 3; ++i)
      {
         int qual = (int) (Math.random() * posicoesLivres.size() + 1);
         PosicaoLivre p = posicoesLivres.get(qual);
         p.getPosicao().setBolinha(proximasBolinhas[i]);
         posicoesLivres.remove(qual);
         AtorJogador.getInstance().desenharPosicao(p.getLinha(), p.getColuna());
      }
   }
   
   public Posicao getPosicao(int linha, int coluna)
   {
      return posicoes[linha][coluna];
   }
   
   public boolean verificaSeTemCaminho(Jogada jogada)
   {
      return false;
   }
   
   public void moveBolinha(Posicao posicao)
   {
   }
   
   public boolean verificaSeMarcouPonto(Posicao posicao)
   {
      return false;
   }
   
   public void definirTamanho(int nLinhas, int nColunas)
   {
   }
   
}

