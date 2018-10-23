package settlers.limite.peoes;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import javax.swing.JComponent;

import settlers.limite.AtorJogador;

public abstract class PeaoColonia extends JComponent {
    
	private static final long serialVersionUID = 1L;
	
    private int jogador;
    private Polygon poligono;
    private Color cor;
    private boolean ativo;

    public PeaoColonia(int jogador) {
        this.jogador = jogador;
        definirCor();
    }
    
    private void definirCor() {
        switch (this.jogador) {
	        case 1: cor = AtorJogador.COR_JOGADOR_1; break;
	        case 2: cor = AtorJogador.COR_JOGADOR_2; break;
	        case 3: cor = AtorJogador.COR_JOGADOR_3; break;
	        case 4: cor = AtorJogador.COR_JOGADOR_4; break;
        }
    }
    
    public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
        setCursor(Cursor.getPredefinedCursor(ativo ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
		repaint();
	}

	public int getJogador() {
    	return jogador;
    }
    
    public void setJogador(int jogador) {
    	this.jogador = jogador;
        definirCor();
    	repaint();
    }

    public abstract void remover();

    public void setPoligono(Polygon poligono) {
        this.poligono = poligono;
    }

    public Polygon getPoligono() {
        return poligono;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fill(poligono);
    	if (isAtivo()) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
    	}
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setPaint(new GradientPaint(0, 0, cor, getWidth(), getHeight(), cor.darker().darker(), false));
        g2.fillPolygon(poligono);
        g2.setStroke(new BasicStroke(1.5f));
        g2.setColor(isAtivo() ? Color.white : cor.darker().darker());
        g2.drawPolygon(poligono);
   }
    
}
