package br.ufsc.linhasecores;

import br.ufsc.linhasecores.excecoes.CorInvalidaException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class Jogada
{
   
   private Posicao    posicaoSaida;
   private Posicao    posicaoChegada;
   private Posicao[]  bolinhasInseridas;
   private HashMap<Posicao, Bolinha> bolinhasEliminadas;
   private Partida partida;
   private boolean emCurso;
   
   
   public Jogada(Partida aPartida)
   {
      bolinhasInseridas = null;
      bolinhasEliminadas = new HashMap<Posicao, Bolinha>();
      
      setPosicaoSaida(null);
      setPosicaoChegada(null);
      
      partida = aPartida;
      
      emCurso = false;
   }
   
   public void setBolinhasInseridas(Posicao[] posicoes)
   {
      this.bolinhasInseridas = posicoes;
   }
   
   public void addPosicaoEliminada(Posicao p)
   {
      partida.addPontosBolinha();
      bolinhasEliminadas.put (p, p.getBolinha());
   }
   
   public Posicao getPosicaoSaida()
   {
      return posicaoSaida;
   }
   
   public synchronized void setEmCurso(boolean b)
   {
      emCurso = b;
   }
   
   public synchronized boolean isEmCurso()
   {
      return emCurso;
   }
   
   public class ExecucaoJogada extends Thread
   {
      int linha, coluna;
      Jogada aJogada;
      
      public ExecucaoJogada(int linha, int coluna, Jogada jogada)
      {
         this.linha = linha;
         this.coluna = coluna;
         aJogada = jogada;
      }
      
      public void run()
      {
         aJogada.setEmCurso(true);
         
         Posicao p = partida.getTabuleiro().getPosicao(linha, coluna);
         AtorJogador ator = AtorJogador.getInstance();
         
         if (p.isOcupada())
         {
            Posicao anterior = getPosicaoSaida();
            setPosicaoSaida(p);
            if (anterior != null)
            {
               ator.desenharPosicao(anterior.getLinha(), anterior.getColuna());
            }
            ator.desenharPosicao(linha, coluna);
         }
         else
         {
            if (getPosicaoSaida() == null)
            {
               AtorJogador.getInstance().mensagemErro("Selecione uma bolinha");
            }
            else
            {
               setPosicaoChegada(p);
               Stack caminho = partida.getTabuleiro().obterCaminho(getPosicaoSaida(), getPosicaoChegada(),                   null, new Stack<Posicao>());
               if ( caminho != null )
               {
                  AtorJogador.getInstance().animarCaminho(caminho);
                  
                  try
                  {
                     partida.finalizarJogada();
                  }
                  catch (CorInvalidaException e)
                  {
                     // não deveria acontecer, pois a configuração já foi aceita!
                  }
               }
               else
               {
                  AtorJogador.getInstance().mensagemErro("Não é possível realizar o movimento (caminho obstruído)");
               }
               
            }
         }
         
         aJogada.setEmCurso(false);
      }
   }
   
   public void desfazer()
   {
      for (Posicao p : bolinhasEliminadas.keySet())
      {
         p.setBolinha(bolinhasEliminadas.get(p));
         partida.descontaPontosBolinha();
      }
      if (bolinhasInseridas != null)
      {
         Bolinha[] bolinhas = new Bolinha[3];
         for (int i=0; i < 3; ++i)
         {         
            bolinhas[i] = bolinhasInseridas[i].getBolinha();
            bolinhasInseridas[i].setBolinha(null);
         }
         partida.getTabuleiro().setProximasBolinhas (bolinhas);
      }
      getPosicaoSaida().setBolinha (getPosicaoChegada().getBolinha());
      getPosicaoChegada().setBolinha(null);
      AtorJogador.getInstance().redesenharTudo();
   }
   
   public void click(int linha, int coluna)
   throws CorInvalidaException
   {
      if (isEmCurso() )
      {
         // já estou executando uma jogada... ignoro o click!
      }
      else
      {
         ExecucaoJogada exec = new ExecucaoJogada(linha, coluna, this);
         exec.start();
      }
   }

    public void setPosicaoSaida(Posicao posicaoSaida) {
        this.posicaoSaida = posicaoSaida;
    }

    public Posicao getPosicaoChegada() {
        return posicaoChegada;
    }

    public void setPosicaoChegada(Posicao posicaoChegada) {
        this.posicaoChegada = posicaoChegada;
    }
}

