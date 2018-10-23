package settlers.limite.posicoes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import settlers.limite.paineis.PainelTabuleiro;
import settlers.limite.peoes.PeaoEstrada;

public class PosicaoAresta extends Posicao {

	private static final long serialVersionUID = 1L;
	
	private static int contadorId = 0;

	public static final int TIPO_OCEANO  = 0;
    public static final int TIPO_ESTRADA = 1;
    public static final int TIPO_PRAIA   = 2;

    private PosicaoVertice vertices[];
    private PosicaoHexagono hexagonos[];
    private PeaoEstrada estrada;
    private int identificador;
    private int tipo;
    private int x;
    private int y;
    
    public PosicaoAresta(PainelTabuleiro painelTabuleiro, PosicaoHexagono hexagono0, PosicaoHexagono hexagono1, int lado, int tipo) {
        super(painelTabuleiro);
    	this.identificador = contadorId++;
        this.vertices = new PosicaoVertice[2];
        this.hexagonos = new PosicaoHexagono[2];
        this.hexagonos[0] = hexagono0;
        this.hexagonos[1] = hexagono1;
        this.hexagonos[0].setAresta(lado, this);
        this.hexagonos[1].setAresta((lado + 3) % 6, this);
        this.tipo = tipo;
        setPoligono(geraPoligono());
        setLocation(x, y);
        // Sentido horario
        geraVertice(0);
        // Sentido anti-horario
        geraVertice(1);
        // Adiciona ao tabuleiro
        getPainelTabuleiro().registrarPosicaoAresta(this);
        getPainelTabuleiro().add(this, -1);
    }

    private void geraVertice(int vertice) {
        vertice = vertice % 2;
    
        PosicaoAresta arestasVertice[] = new PosicaoAresta[3];
        PosicaoHexagono hexagonosVertice[] = new PosicaoHexagono[3];
        int posicoesVertices[] = new int[3];

        PosicaoHexagono hexagono = hexagonos[0];
        PosicaoAresta aresta = this;
        int ladoHexagono = hexagono.getLadoAresta(aresta);
        
        for (int i = 0; i < 3; i++) {
            arestasVertice[i] = aresta;
            hexagonosVertice[i] = hexagono;
            if (vertice == 0) {
                posicoesVertices[i] = hexagono.getLadoAresta(aresta) - 1;
            } else {
                posicoesVertices[i] = hexagono.getLadoAresta(aresta);
            }
            // Pega proximo hexagono
            hexagono = hexagono.getHexagono(ladoHexagono);
            if (vertice == 0) {
            	ladoHexagono = (ladoHexagono + 4) % 6;
            } else {
            	ladoHexagono = (ladoHexagono + 2) % 6;
            }
            aresta = hexagono.getAresta(ladoHexagono);
            if (aresta == null) {
                return;
            }
        }
        
        vertices[vertice] = new PosicaoVertice(getPainelTabuleiro(), arestasVertice, hexagonosVertice, posicoesVertices);
    }

    public int getIdentificador() {
		return identificador;
	}

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
        repaint();
    }

    public PosicaoHexagono getHexagono(int lado) {
        return hexagonos[lado % 2];
    }

    public PosicaoVertice getVertice(int lado) {
        return vertices[lado % 2];
    }

    public PosicaoHexagono getHexagonoOposto(PosicaoHexagono hexagono) {
        if (hexagonos[0] == hexagono) {
            return hexagonos[1];
        } else if (hexagonos[1] == hexagono) {
            return hexagonos[0];
        } else {
            return null;
        }
    }

    public void setEstrada(PeaoEstrada estrada) {
        this.estrada = estrada;
    }

    public PeaoEstrada getEstrada() {
        return estrada;
    }

    private Polygon geraPoligono() {
        Point pontos[] = new Point[4];
        for (int i = 0; i < 2; i++) {
            try {
                int lado = hexagonos[i].getLadoAresta(this);
                pontos[(2 * i)] = hexagonos[i].getPosicaoVertice(lado);
                pontos[(2 * i) + 1] = hexagonos[i].getPosicaoVertice(lado - 1);
            } catch (Exception ex) {
                Logger.getLogger(PosicaoAresta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Busca menor x e y
        int menorX = Integer.MAX_VALUE;
        int menorY = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            if (pontos[i].x < menorX) {
                menorX = pontos[i].x;
            }
            if (pontos[i].y < menorY) {
                menorY = pontos[i].y;
            }
        }
        // Ajusta Pontos
        for (int i = 0; i < 4; i++) {
            pontos[i].x -= menorX;
            pontos[i].y -= menorY;
        }
        // Polígono
        Polygon poligono = new Polygon();
        for (int i = 0; i < 4; i++) {
            poligono.addPoint(pontos[i].x, pontos[i].y);
        }
        x = menorX;
        y = menorY - 1;
        return poligono;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (isAtivo()) {
        	getPainelTabuleiro().colocarEstrada(this);
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        if (tipo != TIPO_OCEANO) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (isAtivo()) {
                g2.setColor(Color.orange.brighter());
            } else {
	            if (tipo == TIPO_ESTRADA) {
	                g2.setColor(Color.orange);
	            } else {
	                g2.setColor(Color.white);
	            }
            }
            g2.fillPolygon(getPoligono());
            g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            if (tipo == TIPO_ESTRADA) {
                g2.setColor(Color.orange);
            } else {
                g2.setColor(Color.white);
            }
            g2.drawPolygon(getPoligono());
        }
    }
   
}
