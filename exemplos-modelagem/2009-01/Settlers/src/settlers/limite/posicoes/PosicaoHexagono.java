package settlers.limite.posicoes;

import java.awt.Point;
import java.awt.Polygon;

import settlers.limite.paineis.PainelTabuleiro;

public abstract class PosicaoHexagono extends Posicao {

	private static final long serialVersionUID = 1L;
	
	private int camada;
	private int id;

    private PosicaoAresta arestas[];
    private PosicaoVertice vertices[];
    private int larguraAresta;
	
	public PosicaoHexagono(int larguraAresta, PainelTabuleiro painelTabuleiro, int camada, int id) {
		super(painelTabuleiro);
		this.camada = camada;
		this.id = id;
        this.arestas = new PosicaoAresta[6];
        this.vertices = new PosicaoVertice[6];
        this.larguraAresta = larguraAresta;
        setPoligono(geraPoligono());
        setLocation((getPainelTabuleiro().getWidth() - getWidth()) / 2, (getPainelTabuleiro().getHeight() - getHeight()) / 2 + larguraAresta / 2);
	}

    public PosicaoHexagono(PosicaoHexagono hexagono, int lado, int distancia, int camada, int id) {
        this(hexagono.getLarguraAresta(), hexagono.getPainelTabuleiro(), camada, id);
        if (hexagono.getAresta(lado) == null) {
            setLocation(getPosicaoRelativa(hexagono, lado, distancia));
            geraAresta(hexagono, lado);
        } else {
            throw new RuntimeException("Ja existe um hexagono nessa posicaoo");
        }
    }

	public int getCamada() {
		return camada;
	}

	public int getId() {
		return id;
	}

    private void geraAresta(PosicaoHexagono hexagono, int lado) {
        if (this instanceof PosicaoOceano && hexagono instanceof PosicaoOceano) {
            new PosicaoAresta(getPainelTabuleiro(), hexagono, this, lado, PosicaoAresta.TIPO_OCEANO);
        } else if (this instanceof PosicaoOceano && hexagono instanceof PosicaoTerreno) {
            new PosicaoAresta(getPainelTabuleiro(), hexagono, this, lado, PosicaoAresta.TIPO_PRAIA);
        } else {
            new PosicaoAresta(getPainelTabuleiro(), hexagono, this, lado, PosicaoAresta.TIPO_ESTRADA);
        }
        // Buscar sentido horario
        PosicaoHexagono arredor = hexagono;
        int ladoArredor = lado + 1;
        while (arredor.getHexagono(ladoArredor) != null) {
            arredor = arredor.getHexagono(ladoArredor);
            ladoArredor += 3;
            if (arredor.getAresta(ladoArredor + 1) == null) {
                if (this instanceof PosicaoOceano && arredor instanceof PosicaoOceano) {
                    new PosicaoAresta(getPainelTabuleiro(), arredor, this, ladoArredor + 1, PosicaoAresta.TIPO_OCEANO);
                } else if (this instanceof PosicaoOceano && arredor instanceof PosicaoTerreno) {
                    new PosicaoAresta(getPainelTabuleiro(), arredor, this, ladoArredor + 1, PosicaoAresta.TIPO_PRAIA);
                } else {
                    new PosicaoAresta(getPainelTabuleiro(), arredor, this, ladoArredor + 1, PosicaoAresta.TIPO_ESTRADA);
                }
            }
            ladoArredor += 2;
        }
        // Buscar sentido anti-horario
        arredor = hexagono;
        ladoArredor = lado - 1;
        while (arredor.getHexagono(ladoArredor) != null) {
            arredor = arredor.getHexagono(ladoArredor);
            ladoArredor -= 3;
            if (arredor.getAresta(ladoArredor - 1) == null) {
                if (this instanceof PosicaoOceano && arredor instanceof PosicaoOceano) {
                    new PosicaoAresta(getPainelTabuleiro(), arredor, this, ladoArredor - 1, PosicaoAresta.TIPO_OCEANO);
                } else if (this instanceof PosicaoOceano && arredor instanceof PosicaoTerreno) {
                    new PosicaoAresta(getPainelTabuleiro(), arredor, this, ladoArredor - 1, PosicaoAresta.TIPO_PRAIA);
                } else {
                    new PosicaoAresta(getPainelTabuleiro(), arredor, this, ladoArredor - 1, PosicaoAresta.TIPO_ESTRADA);
                }
            }
            ladoArredor -= 2;
        }
    }

    private Point getPosicaoRelativa(PosicaoHexagono hexagono, int lado, int distancia) {
        int posX;
        int posY;
        if (lado == 0) {
            posX = hexagono.getX();
            posY = hexagono.getY() - getHeight() + 1 - distancia;
        } else if (lado == 1) {
            posX = (int) Math.round(hexagono.getX() + (larguraAresta * 3.0 / 2.0) + (distancia * Math.sqrt(3) / 2.0)) + 1;
            posY = (int) Math.round(hexagono.getY() - (larguraAresta * Math.sqrt(3) / 2.0) - (distancia / 2.0)) - 1;
        } else if (lado == 2) {
            posX = (int) Math.round(hexagono.getX() + (larguraAresta * 3.0 / 2.0) + (distancia * Math.sqrt(3) / 2.0)) + 1;
            posY = (int) Math.round(hexagono.getY() + (larguraAresta * Math.sqrt(3) / 2.0) + (distancia / 2.0)) + 1;
        } else if (lado == 3) {
            posX = hexagono.getX();
            posY = hexagono.getY() + getHeight() - 1 + distancia;
        } else if (lado == 4) {
            posX = (int) Math.round(hexagono.getX() - (larguraAresta * 3.0 / 2.0) - (distancia * Math.sqrt(3) / 2.0)) - 1;
            posY = (int) Math.round(hexagono.getY() + (larguraAresta * Math.sqrt(3) / 2.0) + (distancia / 2.0)) + 1;
        } else {
            posX = (int) Math.round(hexagono.getX() - (larguraAresta * 3.0 / 2.0) - (distancia * Math.sqrt(3) / 2.0)) - 1;
            posY = (int) Math.round(hexagono.getY() - (larguraAresta * Math.sqrt(3) / 2.0) - (distancia / 2.0)) - 1;
        }
        return new Point(posX, posY);
    }

    public int getLarguraAresta() {
        return larguraAresta;
    }

    public Point getPosicaoVertice(int vertice) {
        vertice = (vertice + 6) % 6;
        return new Point(getPoligono().xpoints[vertice] + getX(), getPoligono().ypoints[vertice] + getY());
    }

    public PosicaoHexagono getHexagono(int lado) {
        PosicaoAresta aresta = arestas[(lado + 6) % 6];
        if (aresta == null)
            return null;
        if (aresta.getHexagono(0) == this)
            return aresta.getHexagono(1);
        return aresta.getHexagono(0);
    }

    public PosicaoAresta getAresta(int lado) {
        return arestas[(lado + 6) % 6];
    }

    public void setAresta(int lado, PosicaoAresta aresta) {
        arestas[(lado + 6) % 6] = aresta;
    }

    public PosicaoVertice getVertice(int vertice) {
        return vertices[(vertice + 6) % 6];
    }

    public void setVertice(int lado, PosicaoVertice vertice) {
        vertices[(lado + 6) % 6] = vertice;
    }

    public int getLadoAresta(PosicaoAresta aresta) {
        for (int i = 0; i < 6; i++) {
            if (arestas[i] == aresta) {
                return i;
            }
        }
        throw new RuntimeException("Aresta nao encontrada");
    }

    public PosicaoHexagono getHexagonoMaisDistante(int lado) {
        if (arestas[lado] == null) {
            return this;
        } else {
            return arestas[lado].getHexagonoOposto(this).getHexagonoMaisDistante(lado);
        }
    }

    public int getLadoPraia() {
        int a[] = {2, 4, 0, 1, 3, 5};
        for (int i = 0; i < a.length; i++) {
            if (getAresta(a[i]) != null && getAresta(a[i]).getTipo() == PosicaoAresta.TIPO_PRAIA) {
                return a[i];
            }
        }
        return -1;
    }

    private Polygon geraPoligono() {
        Polygon poligono = new Polygon();
        poligono.addPoint((int) Math.round(larguraAresta * 3.0 / 2.0), 0);
        poligono.addPoint(larguraAresta * 2, (int) Math.round(larguraAresta * Math.sqrt(3) / 2.0));
        poligono.addPoint((int) Math.round(larguraAresta * 3.0 / 2.0), (int) Math.round(larguraAresta * Math.sqrt(3)));
        poligono.addPoint((int) Math.round(larguraAresta / 2.0), (int) Math.round(larguraAresta * Math.sqrt(3)));
        poligono.addPoint(0, (int) Math.round(larguraAresta * Math.sqrt(3) / 2.0));
        poligono.addPoint((int) Math.round(larguraAresta / 2.0), 0);
        return poligono;
    }

}
