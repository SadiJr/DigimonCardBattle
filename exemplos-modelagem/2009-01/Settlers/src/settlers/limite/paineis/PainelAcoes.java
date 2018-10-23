package settlers.limite.paineis;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;

import javax.swing.JList;

import settlers.limite.AtorJogador;

public class PainelAcoes extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JList listaAcoes;
	private JScrollPane listScrollPane;
	private DefaultListModel modeloLista;
	
	public PainelAcoes() {
		setSize(260, 120);
		setLayout(null);
		setOpaque(false);
		
		modeloLista = new DefaultListModel();
		
		listaAcoes = new JList(modeloLista);
		listaAcoes.setCellRenderer(new RenderizadorLista());
		listaAcoes.setFocusable(false);
		
		listScrollPane = new JScrollPane(listaAcoes);
		listScrollPane.setLocation(10, 10);
		listScrollPane.setSize(240, 100);
		listScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(listScrollPane);
	}
	
	public void registrarAcao(int jogador, String texto) {
		modeloLista.addElement(new ListItem(jogador, texto));
		listaAcoes.ensureIndexIsVisible(modeloLista.getSize() - 1);
		listaAcoes.repaint();
	}
	
	public void limparPainelAcoes() {
		modeloLista.clear();
	}
	
	private class ListItem {
		
	    private String texto;
	    private int jogador;
	
	    public ListItem(int jogador, String texto) {
	        this.jogador = jogador;
	        this.texto = texto;
	    }
	      
	    public Color getColor() {
			switch (jogador) {
			case 0: return Color.white;
			case 1: return AtorJogador.COR_JOGADOR_1;
			case 2: return AtorJogador.COR_JOGADOR_2;
			case 3: return AtorJogador.COR_JOGADOR_3;
			case 4: return AtorJogador.COR_JOGADOR_4;
			default: return null;
			}
	      }
	      
	    public String getValue() {
	        return texto;
	    }
	      
	}

	private class RenderizadorLista extends  JLabel implements ListCellRenderer {
	
		private static final long serialVersionUID = 1L;

		public RenderizadorLista() {
			setOpaque(true);
		}
	
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean iss, boolean chf) {
			setText(" " + ((ListItem) value).getValue());
			setBackground(((ListItem) value).getColor());
			setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.white));
			return this;
		}
	}
	
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        float[] dist = {0.0f, 1.0f};
        Color[] colors = {new Color(0.0f, 0.0f, 0.0f, 0.2f), new Color(1.0f, 1.0f, 1.0f, 0.2f)};
        g2.setPaint(new LinearGradientPaint(0, 0, 0, getHeight(), dist, colors));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        g2.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
     }

}
