package settlers.limite.peoes;

import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import settlers.limite.posicoes.PosicaoVertice;

public class PeaoVila extends PeaoColonia {

	private static final long serialVersionUID = 1L;
	
	private PosicaoVertice vertice;

    public PeaoVila(int jogador) {
        super(jogador);
        setPoligono(geraPoligono());
        setBounds(0, 0, getPoligono().getBounds().width + 1, getPoligono().getBounds().height + 1);
        addMouseListener(new TrataEventos());
    }

    public PeaoVila(int jogador, PosicaoVertice vertice) {
        this(jogador);
        this.vertice = vertice;
        this.vertice.setColonia(this);
        setLocation(vertice.getX() - (Math.abs(vertice.getWidth() - getWidth())) / 2, vertice.getY() - (Math.abs(vertice.getHeight() - getHeight())) / 2 - 1);
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
        poligono.addPoint(7, 0);
        poligono.addPoint(15, 6);
        poligono.addPoint(15, 20);
        poligono.addPoint(0, 20);
        poligono.addPoint(0, 6);
        return poligono;
    }

    private class TrataEventos extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent arg0) {
            if (isAtivo()) {
                vertice.getPainelTabuleiro().colocarPeaoCidade(vertice);
            }
        }

    }

}
