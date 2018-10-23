/*
 * AtorJogador.java
 *
 * Created on 30 de Outubro de 2005, 11:36
 *
 * $Id: AtorJogador.java,v 1.7 2005/11/29 01:12:00 xande Exp $
 */

package br.ufsc.linhasecores;

import br.ufsc.linhasecores.excecoes.ConfiguracaoInvalidaException;
import br.ufsc.linhasecores.excecoes.CorInvalidaException;
import br.ufsc.linhasecores.excecoes.TamanhoInvalidoException;
import br.ufsc.linhasecores.ui.ControladorJogo;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexandre Machado <axmachado@gmail.com>
 */
public class AtorJogador
{
   
   private static AtorJogador oAtorJogador;
   
   private Partida partida;
   private Tabuleiro tabuleiroJogo;
   private ControladorJogo controlador;
   
   /** Creates a new instance of AtorJogador */
   private AtorJogador()
   {
      try
      {
         //TODO: obter o tamanho do arquivo de configuração
         tabuleiroJogo = new Tabuleiro();
         
      }
      catch (ConfiguracaoInvalidaException ex)
      {
         // quando estivermos lendo a configuração do arquivo,
         // este tratamento será necessário:
         try
         {
            tabuleiroJogo = new Tabuleiro();
         }
         catch (ConfiguracaoInvalidaException e)
         {
            // estamos usando valores constantes,
            // dentro da faixa especificada. Portanto,
            // a exceção nunca vai ocorrer, e é seguro
            // deixar sem tratamento.
         }
      }
   }
   
   public static AtorJogador getInstance()
   {
      if (oAtorJogador == null)
      {
         oAtorJogador = new AtorJogador();
      }
      return oAtorJogador;
   }
   
   public void iniciarNovoJogo()
   {
      try
      {
         partida = new Partida(tabuleiroJogo);
         atualizarPlacar();
         controlador.limpaMensagem();
      }
      catch (CorInvalidaException e)
      {
         // dar a má noticia para o usuário...
         controlador.mensagemErro("Erro interno do sistema: tentei criar uma bolinha com a cor inválida");
      }
   }
   
   public void atualizarPlacar()
   {
      controlador.setTextoPlacar(Integer.toString(partida.getPontuacao()) + " pontos.");
   }
   
   public void setControlador(ControladorJogo c)
   {
      controlador = c;
   }
   
   public void mostrarProximasBolinhas(Bolinha[] bolinhas)
   {
      controlador.mostrarProximasBolinhas(bolinhas);
   }
   
   public void desenharPosicao(int linha, int coluna)
   {
      controlador.redesenharPosicao(linha, coluna);
   }
   
   public Bolinha[] getProximasBolinhas()
   {
      return tabuleiroJogo.getProximasBolinhas();
   }
   
   public Bolinha getConteudoPosicao(int linha, int coluna)
   {
      return tabuleiroJogo.getPosicao(linha, coluna).getBolinha();
   }
   
   public int getTamanhoTabuleiro()
   {
      return tabuleiroJogo.getTamanho();
   }
   
   public int getNumCores()
   {
      return tabuleiroJogo.getNumCores();
   }
   
   public void setTamanhoTabuleiro(int tam)
   throws TamanhoInvalidoException
   {
      tabuleiroJogo.setTamanho(tam);
   }
   
   public String obterNomeJogador()
   {
      return controlador.obterNomeJogador();
   }
   
   public String[][] getRecords()
   {
      String[][] result;
      
      Record[] records = tabuleiroJogo.getRecords();
      result = new String[records.length][4];
            
      for (int i=0; i < records.length; ++i)
      {
         result[i][0] = records[i].getNome();
         result[i][1] = Integer.toString (records[i].getNumCores());
         result[i][2] = Integer.toString (records[i].getTamanho());
         result[i][3] = Integer.toString(records[i].getPontuacao());         
      }
      
      return result;
   }
   
   public void setNumCores(int num)
   throws CorInvalidaException
   {
      tabuleiroJogo.setNumCores(num);
   }
   
   public void clickTabuleiro(int linha, int coluna)
   {
      controlador.limpaMensagem();
      Jogada jog = partida.getJogada();
      try
      {
         jog.click(linha, coluna);
      }
      catch (CorInvalidaException e)
      {
         controlador.mensagemErro(e.getMessage());
      }
      atualizarPlacar();
   }
   
   public void mensagemErro(String msg)
   {
      controlador.mensagemErro(msg);
   }
   
   public void desfazerJogada()
   {
      partida.desfazerJogada();
   }
   
   public void redesenharTudo()
   {
      controlador.redesenharTudo();
   }
   
   public void animarCaminho(Stack<Posicao> caminhoInverso)
   {
      Stack<Posicao> caminho = new Stack<Posicao>();
      
      while (!caminhoInverso.empty())
         caminho.push(caminhoInverso.pop());
      
      Posicao atual = caminho.pop();
      while (!caminho.empty())
      {
         Posicao prox = caminho.pop();
         
         tabuleiroJogo.moveBolinha(atual, prox);
         try
         {
            Thread.currentThread().sleep(100);
         }
         catch (InterruptedException e)
         {
            // se for interrompida, abortar a animação
            return;
         }
         atual = prox;
      }
      
   }   
   
   public boolean isSelecionado(int linha, int coluna)
   {
      Jogada jog = partida.getJogada();
      
      Posicao pos = jog.getPosicaoSaida();
      
      if (pos != null)
      {
         return (pos.getLinha() == linha) && (pos.getColuna() == coluna);
      }
      else
      {
         return false;
      }
   }
   
   public void configurarJogo()
   {
      if (controlador.abirConfiguracao(getNumCores(), getTamanhoTabuleiro()))
      {
         try
         {
            setNumCores(controlador.getConfigNumCores());
            setTamanhoTabuleiro(controlador.getConfigTamanho());
            controlador.finalizaConfiguracao();
         } 
         catch (ConfiguracaoInvalidaException ex)
         {
            mensagemErro (ex.getMessage());
         }
      }      
   }
}
