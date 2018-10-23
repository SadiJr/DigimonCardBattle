/*
 * ControladorJogo.java
 *
 * Created on 30 de Outubro de 2005, 13:28
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package br.ufsc.linhasecores.ui;
import br.ufsc.linhasecores.*;
import br.ufsc.linhasecores.excecoes.ConfiguracaoInvalidaException;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexandre Machado <axmachado@gmail.com>
 */
public class ControladorJogo
{
   private MainForm tela;
   private AtorJogador jogo;
   private FormConfig formConfig;
   
   private VisaoTabuleiro visaoTabuleiro;
   
   /** Creates a new instance of ControladorJogo */
   public ControladorJogo()
   {
      formConfig = null;
      jogo = AtorJogador.getInstance();
      jogo.setControlador (this);
      tela = new MainForm(this);
      jogo.iniciarNovoJogo();      
   }
   
   public int getTamanhoTabuleiro ()
   {
      return jogo.getTamanhoTabuleiro();
   }
   
   public void mostrarProximasBolinhas (Bolinha[] bolinha)
   {
      tela.redesenharProximasBolinhas();
   }
   
   public synchronized void redesenharPosicao (int linha, int coluna)
   {
      tela.redesenharPosicao ( linha,  coluna);
   }
   
   public Bolinha[] getProximasBolinhas()
   {
      return jogo.getProximasBolinhas();
   }
   
   public Bolinha getBolinha (int linha, int coluna)
   {
      return jogo.getConteudoPosicao (linha, coluna);
   }
   
   public void novoJogo ()
   {
      jogo.iniciarNovoJogo();
      tela.redesenharProximasBolinhas();
   }
   
   public void mensagemErro (String msg)
   {
      tela.setMensagemErro(msg);
   }
   
   public void redesenharTudo()
   {
      tela.redesenharProximasBolinhas();
      tela.redesenharTabuleiro();
      tela.limpaMensagem();
   }
   
   public void limpaMensagem()
   {
      tela.limpaMensagem();
   }
   
   public void desfazerJogada()
   {
      jogo.desfazerJogada();
   }

   public void configurarJogo()
   {
      jogo.configurarJogo();
   }
      
   public boolean abirConfiguracao(int tamanho, int numCores)
   {
      formConfig = new FormConfig(tela, true);
      
      formConfig.setNumCores(tamanho);
      formConfig.setTamanhoTabuleiro(numCores);
      
      formConfig.setVisible(true);
      
      return formConfig.isConfirmado();
   }
   
   public int getConfigNumCores()
   {
      if (formConfig != null)
      {
         return formConfig.getNumCores();
      }
      else
      {
         return 5;
      }
   }
   
   public int getConfigTamanho()
   {
      if (formConfig != null)
      {
         return formConfig.getTamanho();
      }
      else
      {
         return 9;
      }
   }
   
   public void finalizaConfiguracao()
   {
      tela.redesenharTabuleiro();
      this.novoJogo();
      formConfig = null;
   }
   
   public void clickTabuleiro (int linha, int coluna)
   {
      jogo.clickTabuleiro(linha, coluna);
   }
   
   public boolean isSelecionado (int linha, int coluna)
   {
      return jogo.isSelecionado (linha, coluna);
   }
   
   public void setTextoPlacar (String texto)
   {
      tela.setTextoPlacar(texto);
   }
   
   public String obterNomeJogador()
   {
      String res = JOptionPane.showInputDialog(tela, "Digite seu nome", "ninguém");
      return res;
   }
   
   public String[][] getRecords()
   {
      
      return jogo.getRecords();      
   }
   
   public void run()
   {
      java.awt.EventQueue.invokeLater (new Runnable ()
      {
         public void run ()
         {
            tela.setVisible (true);
         }
      });      
   }
}
