package settlers.modelo;

import java.util.LinkedList;

/**
 * 
 * @author Murilo Fernando de Oliveira
 * @author Paulo Rogério de Pinho Filho
 * 
 */
public class Terreno {
	
	private Tabuleiro tabuleiro;

	private int camada;
	private int id;
	private int numero;
	private int tipo;
	
	private PosicaoColonia posicoesColonia[];
	private PosicaoEstrada posicoesEstrada[];
	
	public Terreno(Tabuleiro tabuleiro, int camada, int id) {
		this.tabuleiro = tabuleiro;
		this.camada = camada;
		this.id = id;
		this.posicoesColonia = new PosicaoColonia[6];
		this.posicoesEstrada = new PosicaoEstrada[6];
		this.tipo = Tabuleiro.TERRENO_DESERTO;
	}
	
	public Terreno(Terreno terreno, int lado, int camada, int identificador) {
		this(terreno.getTabuleiro(), camada, identificador);
        if (terreno.getPosicaoEstrada(lado) == null) {
            gerarPosicoesEstrada(terreno, lado);
        }
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public int getCamada() {
		return camada;
	}

	public int getId() {
		return id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getTipo() {
		return tipo;
	}
	
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public PosicaoEstrada getPosicaoEstrada(int lado) {
        return posicoesEstrada[(lado + 6) % 6];
	}
	
	public void setPosicaoEstrada(PosicaoEstrada posicaoEstrada, int lado) {
		posicoesEstrada[(lado + 6) % 6] = posicaoEstrada;
	}
	
	public PosicaoColonia getPosicaoColonia(int lado) {
		return posicoesColonia[lado];
	}
	
	public void setPosicaoColonia(PosicaoColonia posicaoColonia, int lado) {
		posicoesColonia[(lado + 6) % 6] = posicaoColonia;
	}
	/**
	 * Busca o terreno mais distante em um dos lados dos terreno
	 * @param lado Lado do terreno (0 .. 5)
	 */
	public Terreno getTerrenoMaisDistante(int lado) {
        if (posicoesEstrada[lado] == null) {
            return this;
        } else {
            return posicoesEstrada[lado].getTerrenoOposto(this).getTerrenoMaisDistante(lado);
        }
	}

    public Terreno getTerrenoVizinho(int lado) {
        PosicaoEstrada posicaoEstrada = posicoesEstrada[(lado + 6) % 6];
        if (posicaoEstrada == null) {
            return null;
        }
        if (posicaoEstrada.getTerreno(0) == this) {
            return posicaoEstrada.getTerreno(1);
        }
        return posicaoEstrada.getTerreno(0);
    }
    
    public int getLadoPosicaoEstrada(PosicaoEstrada posicaoEstrada) {
        for (int i = 0; i < 6; i++) {
            if (posicoesEstrada[i] == posicaoEstrada) {
                return i;
            }
        }
        throw new RuntimeException("Posição Estrada não encontrada");
    }
	
	private void gerarPosicoesEstrada(Terreno terreno, int lado) {
		// Gera posicaoEstrada com o terreno referencia
        new PosicaoEstrada(tabuleiro, terreno, this, lado);
		// Percorre terrenos no sentido horário
		percorrerTerrenosVizinhos(terreno, lado, +1);
		// Percorre terrenos no sentido anti-horário
		percorrerTerrenosVizinhos(terreno, lado, -1);
	}
	/**
	 * Percorre terrenos vizinhos e gera PosicaoEstrada
	 * @param sentido 1 para sentido horário e -1 para anti-horário
	 */
	private void percorrerTerrenosVizinhos(Terreno terreno, int lado, int sentido) {
		Terreno arredor = terreno;
        lado += sentido;
        while (arredor.getTerrenoVizinho(lado) != null) {
            arredor = arredor.getTerrenoVizinho(lado);
            lado += 3 * sentido;
            if (arredor.getPosicaoEstrada(lado + sentido) == null) {
                new PosicaoEstrada(tabuleiro, arredor, this, lado + sentido);
            }
            lado += 2 * sentido;
        }
	}
	
	public int[] buscarJogadoresRedondeza() {
		LinkedList<Integer> jogadores = new LinkedList<Integer>();
		for (int i = 0; i < 6; i++) {
			if (posicoesColonia[i].getColonia() != null && posicoesColonia[i].getColonia().getJogador() != tabuleiro.getJogadorDaVez() && !jogadores.contains(posicoesColonia[i].getColonia().getJogador().getId())) {
				jogadores.add(posicoesColonia[i].getColonia().getJogador().getId());
			}
		}
		if (jogadores.size() > 0) {
			int arrayJogadores[] = new int[jogadores.size()];
			for (int i = 0; i < jogadores.size(); i++) {
				arrayJogadores[i] = jogadores.get(i);
			}
			return arrayJogadores;
		} else {
			return null;
		}
	}
	
	public LinkedList<Colonia> buscarColoniasRedondeza() {
		LinkedList<Colonia> colonias = new LinkedList<Colonia>();
		for (int i = 0; i < 6; i++) {
			if (!posicoesColonia[i].isVazia()) {
				colonias.add(posicoesColonia[i].getColonia());
			}
		}
		return colonias;
	}
	
}
