package br.ufsc.linhasecores;

import br.ufsc.linhasecores.excecoes.ConfiguracaoInvalidaException;
import br.ufsc.linhasecores.excecoes.CorInvalidaException;
import br.ufsc.linhasecores.excecoes.TamanhoInvalidoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Stack;

public class Tabuleiro
{
   
   private int tamanho;
   private Bolinha[] proximasBolinhas;
   private int numCores;
   private Posicao[][] posicoes;
   private Partida partida;
   private ArrayList<Posicao> posicoesLivres;
   private Record[] record;
   
   public Tabuleiro()
   throws ConfiguracaoInvalidaException
   {
      record = new Record[10];
      for (int i=0; i < 10; i++)
      {
         record[i] = new Record();
      }
      try
      {
         carregarConfiguracao();
      }
      catch (IOException e)
      {
         System.out.println("Erro ao carregar configuração: usando configuração padrão.");
         setTamanho(9);
         setNumCores(5);
      }
   }
   
   public void carregarConfiguracao()
   throws IOException
   {
      File arqConfiguracao = new File(System.getProperty("user.home") + "/" + ".linhas-e-cores.cfg");
      
      Properties config = new Properties();
      config.load(new FileInputStream(arqConfiguracao));
      try
      {
         setTamanho(Integer.parseInt(config.getProperty("tamanho")));
      }
      catch (TamanhoInvalidoException e)
      {
         System.out.println("Tamanho inválido no arquivo de configuração");
      }
      try
      {
         setNumCores(Integer.parseInt(config.getProperty("numero.de.cores")));
      }
      catch (CorInvalidaException e)
      {
         System.out.println  ("Número de cores inválido no arquivo de configuração");
      }
      
      for (int i=0; i < 10; i++)
      {
         record[i].setNome(config.getProperty("record." + (i+1) + ".nome", "ninguém"));
         record[i].setNumCores(Integer.parseInt(config.getProperty("record." + (i+1) + ".cores", "5")));
         record[i].setPontuacao(Integer.parseInt(config.getProperty("record."+ (i+1) + ".pontos", "0")));
         record[i].setTamanho(Integer.parseInt(config.getProperty("record."+ (i+1) + ".pontos", "0")));
      }
   }
   
   public void salvarConfiguracao()
   throws IOException
   {
      File arqConfiguracao = new File(System.getProperty("user.home") + "/" + ".linhas-e-cores.cfg");
      
      Properties config = new Properties();
      config.setProperty("tamanho", Integer.toString(getTamanho()));
      config.setProperty("numero.de.cores", Integer.toString(getNumCores()));
      
      for (int i=0; i < 10; i++)
      {
         config.setProperty("record." + (i+1) + ".nome", record[i].getNome() );
         config.setProperty("record." + (i+1) + ".tamanho", Integer.toString(record[i].getTamanho()) );
         config.setProperty("record." + (i+1) + ".cores", Integer.toString(record[i].getNumCores()) );
         config.setProperty("record." + (i+1) + ".pontos", Integer.toString(record[i].getPontuacao()) );
      }
      
      config.store(new FileOutputStream(arqConfiguracao), "Configurações do Linhas e Cores");
   }
   
   public Record[] getRecords()
   {
      return record;
   }
   
   public void setPartida(Partida p)
   {
      partida = p;
   }
   
   public Partida getPartida()
   {
      return partida;
   }
   
   public int getTamanho()
   {
      return tamanho;
   }
   
   public void limpar()
   {
      posicoesLivres.clear();
      for (int i=0; i < tamanho; i++)
      {
         for (int j=0; j < tamanho; j++ )
         {
            posicoes[i][j].setBolinha(null);
            posicoesLivres.add(posicoes[i][j]);
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
         proximasBolinhas[i] = new Bolinha((int)(Math.random()*getNumCores()));
      }
      AtorJogador.getInstance().mostrarProximasBolinhas(proximasBolinhas);
   }
   
   public synchronized Bolinha[] getProximasBolinhas()
   {
      return proximasBolinhas;
   }
   
   public synchronized void setProximasBolinhas(Bolinha[] bolinhas)
   {
      proximasBolinhas = bolinhas;
   }
   
   public void verificaRecord(int pontos)
   {
      int posicao = 10;
      for (int i=9; i >=0; i--)
      {
         if ( pontos > record[i].getPontuacao())
         {
            posicao = i;
         }
      }
      
      for (int i=8; i > posicao; i--)
      {
         record[i+1] = record[i];
      }
      
      String nome = AtorJogador.getInstance().obterNomeJogador();
      
      Record rec = new Record(nome, pontos, getNumCores(), getTamanho());
      record[posicao] = rec;
      
      try
      {
         salvarConfiguracao();
      }
      catch (IOException e)
      {
         AtorJogador.getInstance().mensagemErro("Erro ao salvar recordes");
      }
   }
   
   public boolean isTerminado()
   {
      return posicoesLivres.size() == 0;
   }
   
   public Posicao[] inserirBolinhas()
   {
      int max = (posicoesLivres.size()>3) ? 3 : posicoesLivres.size();
      Posicao[] inseridas = new Posicao[max];
      for (int i=0; i < max; ++i)
      {
         int qual = (int) Math.floor(Math.random() * posicoesLivres.size());
         Posicao p = posicoesLivres.get(qual);
         p.setBolinha(proximasBolinhas[i]);
         posicoesLivres.remove(p);
         inseridas[i] = p;
         AtorJogador.getInstance().desenharPosicao(p.getLinha(), p.getColuna());
      }
      return inseridas;
   }
       
   public Posicao[][] getPosicoes()
   {
      return posicoes;
   }
   
   public void registrarPontos(Jogada jogada, List<Posicao> posicoes)
   {
      Iterator<Posicao> it = posicoes.iterator();
      
      while (it.hasNext())
      {
         Posicao p = it.next();
         jogada.addPosicaoEliminada(p);
         p.setBolinha(null);
         posicoesLivres.add(p);
         AtorJogador.getInstance().desenharPosicao(p.getLinha(), p.getColuna());
      }
   }
   
   public static final int DIAG_SUP_ESQ = 0;
   public static final int SUP = 1;
   public static final int DIAG_SUP_DIR = 2;
   public static final int DIR = 3;
   public static final int DIAG_INF_DIR = 4;
   public static final int INF = 5;
   public static final int DIAG_INF_ESQ = 6;
   public static final int ESQ = 7;
   
   public Posicao getVizinha(int direcao, Posicao posicao)
   {
      int linha, coluna;
      switch (direcao)
      {
         case DIAG_SUP_ESQ:
            linha = posicao.getLinha()-1;
            coluna = posicao.getColuna()-1;
            break;
         case SUP:
            linha = posicao.getLinha()-1;
            coluna = posicao.getColuna();
            break;
         case DIAG_SUP_DIR:
            linha = posicao.getLinha()-1;
            coluna = posicao.getColuna()+1;
            break;
         case DIR:
            linha = posicao.getLinha();
            coluna = posicao.getColuna()+1;
            break;
         case DIAG_INF_DIR:
            linha = posicao.getLinha()+1;
            coluna = posicao.getColuna()+1;
            break;
         case INF:
            linha = posicao.getLinha()+1;
            coluna = posicao.getColuna();
            break;
         case DIAG_INF_ESQ:
            linha = posicao.getLinha()+1;
            coluna = posicao.getColuna()-1;
            break;
         case ESQ:
            linha = posicao.getLinha();
            coluna = posicao.getColuna()-1;
            break;
         default:
            return null;
      }
      if ( linha >= 0 && linha < getTamanho() &&
         coluna >= 0 && coluna < getTamanho() )
      {
         return getPosicao(linha, coluna);
      }
      else
      {
         return null;
      }
   }
   
   public void verificaPosicao(Posicao posicao, int direcao, ArrayList lista)
   {
      Posicao proxima ;
      
      int cor = posicao.getBolinha().getCor();
      
      proxima = posicao;
      proxima = getVizinha(direcao, proxima);
      if (proxima != null)
      {
         if (proxima.isOcupada())
         {
            if (proxima.getBolinha().getCor() == cor)
            {
               lista.add(proxima);
               verificaPosicao(proxima, direcao, lista);
            }
         }
      }
   }
   
   public boolean veriricarVizinhanca(Posicao posicao, Jogada jogada)
   {
      ArrayList<Posicao> result = new ArrayList<Posicao>();
      
      for (int i=0; i < 8; i++)
      {
         result.clear();
         result.add(posicao);
         
         verificaPosicao(posicao, i, result);
         
         if (result.size() >= 5)
         {
            registrarPontos(jogada, result);
            return true;
         }
         
      }
      
      return false;
   }
   
   public boolean marcouPontos(Jogada jogada)
   {
      /*
      return marcouPontosHorizontal(jogada) || marcouPontosVertical(jogada) ||
         marcouPontosDiagonal(jogada);
       **/
      boolean res = false;
      for (int i=0; i < getTamanho(); i++)
      {
         for (int j=0; j < getTamanho(); j++)
         {
            Posicao p = getPosicao(i,j);
            if (p.isOcupada())
            {
               res = res || veriricarVizinhanca(p, jogada);
            }
         }
      }
      
      return res;
   }
   
   private Posicao[] obterSaidas(Posicao origem, Posicao destino)
   {
      Posicao[] saidas = new Posicao[4];
      int linhaOrigem = origem.getLinha();
      int colunaOrigem = origem.getColuna();
      
      int distanciay = Math.abs(origem.getLinha()-destino.getLinha());
      int distanciax = Math.abs(origem.getColuna()-destino.getColuna());
      
      
      Posicao direita  = (colunaOrigem+1 < getTamanho()) ? getPosicao(linhaOrigem, colunaOrigem+1) : null;
      Posicao superior = (linhaOrigem > 0) ? getPosicao(linhaOrigem-1, colunaOrigem) : null;
      Posicao esquerda = (colunaOrigem > 0) ? getPosicao(linhaOrigem, colunaOrigem-1) : null;
      Posicao inferior = (linhaOrigem+1 < getTamanho()) ? getPosicao(linhaOrigem+1, colunaOrigem) : null;
      
      if (distanciax > distanciay)
      {
         if (destino.getColuna() < colunaOrigem)
         {
            saidas[0] = esquerda;
            saidas[1] = direita;
         }
         else
         {
            saidas[0] = direita;
            saidas[1] = esquerda;
         }
         if (destino.getLinha() < linhaOrigem)
         {
            saidas[2] = superior;
            saidas[3] = inferior;
         }
         else
         {
            saidas[2] = inferior;
            saidas[3] = superior;
         }
      }
      else
      {
         if (destino.getLinha() < linhaOrigem)
         {
            saidas[0] = superior;
            saidas[1] = inferior;
         }
         else
         {
            saidas[0] = inferior;
            saidas[1] = superior;
         }
         if (destino.getColuna() < colunaOrigem)
         {
            saidas[2] = esquerda;
            saidas[3] = direita;
         }
         else
         {
            saidas[2] = direita;
            saidas[3] = esquerda;
         }
      }
      
      return saidas;
   }
   
   public Stack obterCaminho(Posicao origem, Posicao destino, Posicao anterior, Stack<Posicao> caminho)
   {
      Posicao[] saidas = obterSaidas(origem, destino);
      Stack resultado = null;
      
      /* // DEBUG
      System.out.println ("obtendo caminho de (" + origem.getLinha() + "," + origem.getColuna() +
         ") até (" + destino.getLinha() + "," + destino.getColuna() + ")" );
      imprimirPilha (caminho);
      // END DEBUG */
      
      caminho.push(origem);
      if (origem != destino)
      {
         for (int i=0; i < 4; i++)
         {
            Posicao x = saidas[i];
            if ( (resultado == null) &&
               (x != null) &&
               (!caminho.contains(x)) &&
               (x.isOcupada() == false) )
            {
               resultado = obterCaminho(x, destino, origem, caminho);
            }
         }
      }
      else
      {
         resultado = caminho;
      }
      if (resultado == null)
      {
         caminho.pop();
      }
      
      return resultado;
   }
   
   public void imprimirPilha(Stack<Posicao> pilha)
   {
      Iterator<Posicao> it = pilha.iterator();
      
      System.out.print("   -> ");
      while (it.hasNext())
      {
         Posicao p = it.next();
         System.out.print(" (" + p.getLinha() + "," + p.getColuna() + ")");
      }
      System.out.println();
   }
   
   public Posicao getPosicao(int linha, int coluna)
   {
      return posicoes[linha][coluna];
   }
   
   public boolean verificaSeTemCaminho(Jogada jogada)
   {
      return false;
   }
   
   public void moveBolinha(Posicao origem, Posicao destino)
   {
      destino.setBolinha(origem.getBolinha());
      origem.setBolinha(null);
      posicoesLivres.remove(destino);
      posicoesLivres.add(origem);
      AtorJogador.getInstance().desenharPosicao(origem.getLinha(), origem.getColuna());
      AtorJogador.getInstance().desenharPosicao(destino.getLinha(), destino.getColuna());
      
   }
   
   public boolean verificaSeMarcouPonto(Posicao posicao)
   {
      return false;
   }
   
   public int getNumCores()
   {
      return numCores;
   }
   
   public void setNumCores(int numCores)
   throws CorInvalidaException
   {
      if (numCores <= 7 && numCores >= 2)
      {
         this.numCores = numCores;
      }
      else
      {
         throw new CorInvalidaException("O jogo suporta de 2 até 7 cores.");
      }
      try
      {
         salvarConfiguracao();
      }
      catch (IOException e)
      {
         AtorJogador.getInstance().mensagemErro("Erro ao gravar configuração");
      }
      
   }
   
   public void setTamanho(int tamanho)
   throws TamanhoInvalidoException
   {
      if (tamanho <= 9 && tamanho >= 5)
      {
         posicoes = new Posicao[tamanho][tamanho];
         posicoesLivres = new ArrayList<Posicao>();
         for (int i=0; i < tamanho; i++)
         {
            for (int j=0; j < tamanho; j++)
            {
               posicoes[i][j] = new Posicao(i, j, this);
               posicoesLivres.add(posicoes[i][j]);
            }
         }
         this.tamanho = tamanho;
      }
      else
      {
         throw new TamanhoInvalidoException("O jogo suporta tabuleiros de 5 a 9 casas");
      }
      try
      {
         salvarConfiguracao();
      }
      catch (IOException e)
      {
         AtorJogador.getInstance().mensagemErro("Erro ao gravar configuração");
      }
   }
}

