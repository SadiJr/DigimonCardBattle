package settlers.limite.peoes;

import java.awt.Polygon;

import settlers.limite.posicoes.PosicaoVertice;

public class PeaoCidade extends PeaoColonia {

	private static final long serialVersionUID = 1L;
	
	private PosicaoVertice vertice;

    public PeaoCidade(int jogador) {
        super(jogador);
        setPoligono(geraPoligono());
        setBounds(0, 0, getPoligono().getBounds().width + 1, getPoligono().getBounds().height + 1);
    }

    public PeaoCidade(int jogador, PosicaoVertice vertice) {
        this(jogador);
        this.vertice = vertice;
        this.vertice.setColonia(this);
        setLocation(vertice.getX() - (Math.abs(vertice.getWidth() - getWidth())) / 2 + 1, vertice.getY() - (Math.abs(vertice.getHeight() - getHeight())) / 2 - 2);
        vertice.getPainelTabuleiro().add(this, 0);
        vertice.getPainelTabuleiro().repaint();
    }

    @Override
    public void remover() {
        vertice.getPainelTabuleiro().remove(this);
        vertice.setColonia(null);
    }

    public Polygon geraPoligono() {
        Polygon poligono = new Polygon();
        poligono.addPoint(8, 0);
        poligono.addPoint(15, 6);
        poligono.addPoint(15, 9);
        poligono.addPoint(22, 9);
        poligono.addPoint(22, 20);
        poligono.addPoint(0, 20);
        poligono.addPoint(0, 6);
        return poligono;
    }

}
