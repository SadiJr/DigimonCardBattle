package settlers.limite.peoes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import javax.swing.JComponent;

import settlers.limite.AtorJogador;
import settlers.limite.posicoes.PosicaoAresta;

public class PeaoEstrada extends JComponent {

	private static final long serialVersionUID = 1L;
	
    private int jogador;
    private PosicaoAresta aresta;
    private Polygon poligono;
    private Color cor;

    public PeaoEstrada(int jogador, PosicaoAresta aresta) {
        this.jogador = jogador;
        this.aresta = aresta;
        this.poligono = geraPoligono();
        definirCor();
        Rectangle rectangle = new Rectangle(poligono.getBounds());
        rectangle.grow(1, 1);
        setBounds(rectangle);
        setLocation(aresta.getX(), aresta.getY());
        aresta.getPainelTabuleiro().add(this, aresta.getPainelTabuleiro().getColonias().size());
        aresta.getPainelTabuleiro().repaint();

    }

    public PeaoEstrada(int jogador, int largura, int altura) {
        this.jogador = jogador;
        geraPoligono(largura, altura);
        definirCor();
        Rectangle rectangle = new Rectangle(poligono.getBounds());
        rectangle.grow(1, 1);
        setBounds(rectangle);
    }
    
    public int getJogador() {
    	return jogador;
    }
    
    public void setJogador(int jogador) {
    	this.jogador = jogador;
    	definirCor();
    	repaint();
    }

    public Polygon geraPoligono() {
        return aresta.getPoligono();
    }

    private void definirCor() {
        switch (this.jogador) {
            case 1: cor = AtorJogador.COR_JOGADOR_1; break;
            case 2: cor = AtorJogador.COR_JOGADOR_2; break;
            case 3: cor = AtorJogador.COR_JOGADOR_3; break;
            case 4: cor = AtorJogador.COR_JOGADOR_4; break;
        }
    }

    public void geraPoligono(int largura, int altura) {
        poligono = new Polygon();
        poligono.addPoint(0, 0);
        poligono.addPoint(largura, 0);
        poligono.addPoint(largura, altura);
        poligono.addPoint(0, altura);
    }

    public void remover() {
        aresta.getPainelTabuleiro().remove(this);
        aresta.setEstrada(null);
    }

    public Point2D getPonto(int lado) {
        Point2D ponto = new Point2D.Float(0, 0);
        for (int i = 2 * lado; i < (2 * lado) + 2; i++) {
            ponto.setLocation(ponto.getX() + poligono.xpoints[i], ponto.getY() + poligono.ypoints[i]);
        }
        ponto.setLocation(ponto.getX() / 2, ponto.getY() / 2);
        return ponto;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        float[] dist = {0.0f, 0.5f, 1.0f};
        Color[] colors = {cor.darker(), cor, cor.darker()};
        g2.setPaint(new LinearGradientPaint(getPonto(0), getPonto(1), dist, colors));
        g2.fillPolygon(poligono);
        g2.setStroke(new BasicStroke(1.5f));
        g2.setColor(cor.darker().darker());
        g2.drawPolygon(poligono);
    }

}
