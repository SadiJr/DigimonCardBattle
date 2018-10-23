package br.ufsc.linhasecores;

import br.ufsc.linhasecores.excecoes.CorInvalidaException;
import java.util.Stack;

public class Partida
{
   // pontuação da partida
   private int pontuacao;
   // jogadas realizadas (histórico para desfazer)
   private Stack<Jogada> jogadas;   
   // tabuleiro onde a partida é jogada
   private Tabuleiro tabuleiro;
   // jogada atual
   private Jogada jogada;
   
   public Partida(Tabuleiro tabuleiro)
   throws CorInvalidaException
   {
      this.setTabuleiro(tabuleiro);
      tabuleiro.limpar();
      tabuleiro.setPartida (this);
      // tratamento da primeira jogada:
      jogada = new Jogada(this);
      
      tabuleiro.sortearBolinhas();      
      jogada.setBolinhasInseridas (tabuleiro.inserirBolinhas());

      jogadas = new Stack<Jogada>();
      
      addJogada (jogada);
      
      jogada = new Jogada(this);
      
      tabuleiro.sortearBolinhas();
   }
   
   public void mostrarUltimaJogada(Jogada ultimaJogada)
   {
   }
   
   public Jogada getJogada()
   {
      return this.jogada;
   }

   public void setJogada(final Jogada jogada)
   {
      this.jogada = jogada;
   }

   public int getPontuacao()
   {
      return this.pontuacao;
   }

   public void setPontuacao(final int pontuacao)
   {
      this.pontuacao = pontuacao;
   }

   public Tabuleiro getTabuleiro()
   {
      return this.tabuleiro;
   }

   public void setTabuleiro(final Tabuleiro tabuleiro)
   {
      this.tabuleiro = tabuleiro;
   }

   public void addJogada (Jogada jogada)
   {
      jogadas.push (jogada);
   }
   
   public void addPontosBolinha()
   {
      int x = (int) 10 * getTabuleiro().getNumCores() / getTabuleiro().getTamanho();
      setPontuacao (getPontuacao()+x);
   }
   
   public void descontaPontosBolinha()
   {
      int x = (int) 10 * getTabuleiro().getNumCores() / getTabuleiro().getTamanho();
      setPontuacao (getPontuacao()-x);      
   }
   
   public void desfazerJogada()
   {
      Jogada jogadaAnterior = jogadas.pop();
      
      jogadaAnterior.desfazer();
      AtorJogador.getInstance().atualizarPlacar();      
   }
   
   public void finalizarPartida()
   {
      tabuleiro.verificaRecord (getPontuacao());
   }
   
   public void finalizarJogada()
   throws CorInvalidaException
   {
      //TODO: tem que verificar se marcou ponto e registrar a pontuação!!!!
      //verificarMarcacaoPontos();
      if ( tabuleiro.marcouPontos(jogada) )
      {
         // não insere as bolinhas
      }
      else
      {
         Posicao[] inseridas = new Posicao[0];
         do
         {
            inseridas = tabuleiro.inserirBolinhas();
            jogada.setBolinhasInseridas(inseridas);                     
            tabuleiro.sortearBolinhas();         
         }
         while ( tabuleiro.marcouPontos(jogada) );         
         
         if (tabuleiro.isTerminado())
         {
            // acabou o jogo:
            finalizarPartida();
            return;
         }
      }      
      addJogada (jogada);
      jogada = new Jogada (this);
      AtorJogador.getInstance().atualizarPlacar();
   }
}

