package settlers.limite.posicoes;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

import settlers.limite.paineis.PainelTabuleiro;

public abstract class Posicao extends JComponent implements MouseListener, MouseMotionListener {

    private Polygon poligono;
    private boolean ativo;
    private PainelTabuleiro painelTabuleiro;
    private boolean destacado;
    
    private float opacidade = 1.0f;
    
    public Posicao(PainelTabuleiro painelTabuleiro) {
    	this.painelTabuleiro = painelTabuleiro;
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setAtivo(boolean ativo) {
    	this.ativo = ativo;
        setCursor(Cursor.getPredefinedCursor(ativo ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
        setDestacado(false);
    }

    public boolean isAtivo() {
        return ativo;
    }
    
    public float getOpacidade() {
    	return opacidade;
    }
    
    public boolean isDestacado() {
    	return destacado;
    }
    
	public void destacar() {
		new ThreadDestacar().start();
	}
	
	private void setDestacado(boolean destacado) {
		this.destacado = destacado;
		if (destacado) {
			opacidade = 0.5f;
		} else {
			opacidade = 1.0f;
		}
		repaint();
	}
	
    private void dormir(int tempo) {
        try { 
            Thread.sleep(tempo);
        } catch (InterruptedException ex) {}
    }

	public void setPoligono(Polygon p) {
        poligono = p;
        Rectangle retangulo = new Rectangle(poligono.getBounds());
        retangulo.grow(1, 1);
        setBounds(retangulo);
        poligono.translate(-retangulo.x, -retangulo.y);
    }

    public Polygon getPoligono() {
        return poligono;
    }
    
    @Override
    public boolean contains(int nX, int nY) {
        return poligono.contains(nX, nY);
    }

    @Override
    public boolean contains(Point p) {
        return poligono.contains(p);
    }

    public void mouseMoved(MouseEvent e) {
    	if (isAtivo()) {
	        if (poligono.contains(e.getPoint())) {
	        	setDestacado(true);
	        } else {
	        	setDestacado(false);
	        }
    	}
    }

    public PainelTabuleiro getPainelTabuleiro() {
        return painelTabuleiro;
    }
    
    private class ThreadDestacar extends Thread {
    	@Override
    	public void run() {
    		while (opacidade > 0.5f) {
    			opacidade -= 0.05f;
    			repaint();
        		dormir(20);
    		}
    		dormir(3000);
    		if (!isDestacado()) {
        		while (opacidade < 1.0f) {
        			opacidade += 0.05f;
        			repaint();
            		dormir(20);
        		}
    		}
    	}
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
        mouseMoved(e);
    }

    public void mouseEntered(MouseEvent e) {
        mouseMoved(e);
    }

}
