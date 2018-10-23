/*
 * VisaoTabuleiro.java
 *
 * Created on 12 de Outubro de 2005, 13:08
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package br.ufsc.linhasecores.ui;

import br.ufsc.linhasecores.AtorJogador;
import br.ufsc.linhasecores.Bolinha;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author xande
 */
public class VisaoTabuleiro
{
   public class ProximasTableModel
      extends AbstractTableModel
   {
      ControladorJogo controlador;
      
      public void notificaConteudoModificado()
      {
         fireTableCellUpdated(0,0);
         fireTableCellUpdated(0,1);
         fireTableCellUpdated(0,2);         
      }
      
      public ProximasTableModel(ControladorJogo c)
      {
         controlador = c;
      }      
      
      public Object getValueAt (int linha, int coluna)
      {     
         Bolinha[] b = controlador.getProximasBolinhas();
         if ( b != null)
         {
            return b[coluna];
         }
         else
         {
            return null;
         }
      }
      
      public int getRowCount()
      {
         return 1;
      }
      
      public int getColumnCount()
      {
         return 3;
      }
      public boolean isCellEditable(int i1, int i2)
      {
         return false;
      }
   }
   
   public class TabuleiroTableModel
         extends AbstractTableModel
   {
      
      private ControladorJogo controlador;
           
      public TabuleiroTableModel (ControladorJogo c)
      {
         controlador = c;
      }
      
      public synchronized void redesenhar (int linha, int coluna)
      {
         fireTableCellUpdated(linha, coluna);
      }
      
      public Object getValueAt(int linha, int coluna)
      {
         return controlador.getBolinha (linha, coluna);
      }

      public int getRowCount()
      {
         return controlador.getTamanhoTabuleiro();
      }

      public int getColumnCount()
      {
         return controlador.getTamanhoTabuleiro();
      }
      
      public boolean isCellEditable(int i1, int i2)
      {
         return false;
      }
   }
   
   public class TabuleiroCellRenderer 
      extends JLabel
      implements TableCellRenderer
   {
      
      ImageIcon[] bolinhas;
      ControladorJogo controlador;
      
      public TabuleiroCellRenderer(ControladorJogo controlador)
      {
         setBackground (Color.LIGHT_GRAY);
         setOpaque (true);
         setHorizontalAlignment (SwingConstants.CENTER);
         setVerticalAlignment (SwingConstants.CENTER);
         
         bolinhas = new ImageIcon[7];
         
         for (int i=0; i < 7; ++i)
         {
            bolinhas[i] = new ImageIcon (getClass().getResource ("/images/bolinha" + i + ".png"));
         }
         this.controlador = controlador;
      }

      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
         boolean hasFocus, int row, int col)
      {
         setBackground(Color.LIGHT_GRAY);
         
         if ( value == null )
         {
            setIcon(null);
         }
         else
         {
            if (value instanceof Bolinha)
            {
               Bolinha b = (Bolinha) value;
               setIcon (bolinhas[b.getCor()]);               
               if (controlador.isSelecionado (row, col))
               {
                  setBackground(Color.DARK_GRAY);
               }               
            }
         }            
         return this;
      }
   }
   
   private TabuleiroTableModel theTableModel;
   private ProximasTableModel proximasBolinhas;
   private TabuleiroCellRenderer theCellRenderer;
   
   /** Creates a new instance of VisaoTabuleiro */
   public VisaoTabuleiro (ControladorJogo controlador)
   {
      theTableModel = new TabuleiroTableModel(controlador);
      proximasBolinhas = new ProximasTableModel(controlador);
      theCellRenderer  = new TabuleiroCellRenderer(controlador);
   }

   public void notificaDisplayModificado()
   {
      proximasBolinhas.notificaConteudoModificado();
   }
   
   public TableModel getTableModel()
   {
      return theTableModel;
   }
   
   public TableModel getProximasBolinhasTableModel()
   {
      return proximasBolinhas;
   }
   
   public void ajustarTabuleiro(JTable tabuleiro)
   {
      tabuleiro.setRowHeight(40);
      
      for (int i=tabuleiro.getColumnModel().getColumnCount(); i > 0;  i--)
      {
         tabuleiro.getColumnModel().removeColumn(tabuleiro.getColumnModel().getColumn(0));
      }
      for (int i=0; i < theTableModel.getColumnCount(); ++i)
      {
         TableColumn col = new TableColumn (i, 40);
         col.setResizable(false);
         col.setPreferredWidth(40);
         col.setMinWidth(40);
         col.setMaxWidth(40);
         col.setWidth(40);
         col.setCellRenderer(theCellRenderer);
         tabuleiro.getColumnModel().addColumn(col);
      }
      
      Dimension tabDm = new Dimension (theTableModel.getRowCount()*40, theTableModel.getColumnCount()*40);
      tabuleiro.setMinimumSize(tabDm);
      tabuleiro.setMaximumSize(tabDm);
      tabuleiro.setPreferredSize(tabDm);
      tabuleiro.setSize (tabDm);      
   }
   
   public void ajustarDisplayProximasBolas (JTable display)
   {
      display.setRowHeight(40);
      display.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      
      for (int i=0; i < 3; ++i)
      {
         TableColumn col = display.getColumnModel().getColumn(i);
         col.setResizable(false);
         col.setPreferredWidth(40);
         col.setMinWidth(40);
         col.setMaxWidth(40);
         col.setWidth(40);
         col.setCellRenderer(theCellRenderer);
      }
      
      Dimension tabDm = new Dimension (120, 40);
      display.setMinimumSize(tabDm);
      display.setMaximumSize(tabDm);
      display.setPreferredSize(tabDm);
      display.setSize (tabDm);      
      
   }
   
   public void redesenharPosicao (int linha, int coluna)
   {
      theTableModel.redesenhar(linha, coluna);
   }
   
   private int getCoordenada (int medida)
   {
      int pos = medida / 40;
      return pos;
   }
   
   public int descobrirLinha (int y)
   {
      return getCoordenada (y);
   }
   
   public int descobrirColuna (int x)
   {
      return getCoordenada (x);
   }
      
}
