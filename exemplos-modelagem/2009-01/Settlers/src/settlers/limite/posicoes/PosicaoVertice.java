package settlers.limite.posicoes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import settlers.limite.paineis.PainelTabuleiro;
import settlers.limite.peoes.PeaoColonia;

public class PosicaoVertice extends Posicao {

	private static final long serialVersionUID = 1L;
	
	private static int contadorId = 0;
	
	public static final int TIPO_ESTRADA = 0;
    public static final int TIPO_PRAIA   = 1;

    private PosicaoAresta arestas[];
    private PosicaoHexagono hexagonos[];
    private Ellipse2D.Float circulo;
    private PeaoColonia colonia;
    private PosicaoPorto porto;
    private int id;
    private int tipo;
    private int x;
    private int y;

    public PosicaoVertice(PainelTabuleiro painelTabuleiro, PosicaoAresta arestas[], PosicaoHexagono hexagonos[], int posicoesVertices[]) {
    	super(painelTabuleiro);
    	this.id = contadorId++;
        getPainelTabuleiro().registrarPosicaoVertice(this);
        this.arestas = arestas;
        this.hexagonos = hexagonos;
        for (int i = 0; i < 3; i++) {
        	this.hexagonos[i].setVertice(posicoesVertices[i], this);
        }
        geraPoligono(posicoesVertices);
        geraCirculo();
        setLocation(x, y);
        getPainelTabuleiro().add(this, 0);
        if (this.arestas[0].getTipo() == PosicaoAresta.TIPO_PRAIA || this.arestas[1].getTipo() == PosicaoAresta.TIPO_PRAIA || this.arestas[2].getTipo() == PosicaoAresta.TIPO_PRAIA) {
            tipo = TIPO_PRAIA;
        } else {
            tipo = TIPO_ESTRADA;
        }
    }

    private void geraPoligono(int posicoesVertices[]) {
        Point pontos[] = new Point[3];
        for (int i = 0; i < 3; i++) {
            pontos[i] = hexagonos[i].getPosicaoVertice(posicoesVertices[i]);
        }
        // Busca menor x e y
        int menorX = Integer.MAX_VALUE;
        int menorY = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (pontos[i].x < menorX) {
                menorX = pontos[i].x;
            }
            if (pontos[i].y < menorY) {
                menorY = pontos[i].y;
            }
        }
        // Define posicao
        x = menorX;
        y = menorY - 1;
        // Ajusta Pontos
        for (int i = 0; i < 3; i++) {
            pontos[i].x -= menorX;
            pontos[i].y -= menorY;
        }
        // Poligono
        Polygon poligono = new Polygon();
        for (int i = 0; i < 3; i++) {
            poligono.addPoint(pontos[i].x, pontos[i].y);
        }
        setPoligono(poligono);
    }

    public int getId() {
		return id;
	}

	public void setPorto(PosicaoPorto porto) {
        this.porto = porto;
    }

    public PosicaoPorto getPorto() {
        return porto;
    }

    public PeaoColonia getColonia() {
        return colonia;
    }

    public void setColonia(PeaoColonia colonia) {
        this.colonia = colonia;
        setAtivo(false);
    }

    public PosicaoAresta getAresta(int lado) {
        return arestas[lado % 3];
    }

    public PosicaoHexagono getHexagono(int lado) {
        return hexagonos[lado % 3];
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (isAtivo()) {
            getPainelTabuleiro().colocarPeaoVila(this);
        }
    }

    private void geraCirculo() {
        Polygon triangulo = getPoligono();
        int xC = 0;
        int yC = 0;
        for (int i = 0; i < triangulo.npoints; i++) {
            xC += triangulo.xpoints[i];
            yC += triangulo.ypoints[i];
        }
        xC = (int) Math.round(xC / triangulo.npoints);
        yC = (int) Math.round(yC / triangulo.npoints);
        circulo = new Ellipse2D.Float(xC - 3, yC - 3, 6, 6);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (tipo == TIPO_PRAIA) {
            g2.setColor(Color.white);
        } else {
            g2.setColor(Color.orange);
        }
        g2.fillPolygon(getPoligono());
        g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawPolygon(getPoligono());
        if (isAtivo()) {
            g2.setColor(Color.orange.brighter());
            g2.fill(circulo);
        }
    }

}
