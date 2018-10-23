package settlers.modelo;

import java.util.LinkedList;

/**
 * 
 * @author Murilo Fernando de Oliveira
 * @author Paulo Rogério de Pinho Filho
 * 
 */
public class PosicaoEstrada {
	
	private static int contadorId = 0;

	private Tabuleiro tabuleiro;
	private PosicaoColonia posicoesColonia[];
	private Terreno terrenos[];
	private Estrada estrada;
    private int identificador;

	public PosicaoEstrada(Tabuleiro tabuleiro, Terreno terreno1, Terreno terreno2, int lado) {
    	this.identificador = contadorId++;
		this.tabuleiro = tabuleiro;
		this.tabuleiro.registrarPosicaoEstrada(this);
		posicoesColonia = new PosicaoColonia[2];
		terrenos = new Terreno[2];
		terrenos[0] = terreno1;
		terrenos[1] = terreno2;
		associarPosicoesTerreno(lado);
		gerarPosicoesColonia();
	}
	
	public void limpar() {
		estrada = null;
	}
	
	private void associarPosicoesTerreno(int lado) {
		if (terrenos[0] != null)
			terrenos[0].setPosicaoEstrada(this, lado);
		if (terrenos[1] != null)
			terrenos[1].setPosicaoEstrada(this, (lado + 3) % 6);
	}
	
	private void gerarPosicoesColonia() {
		gerarPosicaoColonia(0);
		gerarPosicaoColonia(1);
	}
	
	private void gerarPosicaoColonia(int lado) {
		lado = lado % 2;
		
        PosicaoEstrada posicoesEstradaColonia[] = new PosicaoEstrada[3];
        Terreno terrenosColonia[] = new Terreno[3];
        int ladosColonia[] = new int[3];

        Terreno terreno = terrenos[0];
        PosicaoEstrada posicaoEstrada = this;
        int ladoTerreno = terreno.getLadoPosicaoEstrada(posicaoEstrada);
        
        for (int i = 0; i < 3; i++) {
        	posicoesEstradaColonia[i] = posicaoEstrada;
        	terrenosColonia[i] = terreno;
            if (lado == 0) {
            	ladosColonia[i] = terreno.getLadoPosicaoEstrada(posicaoEstrada) - 1;
            } else {
            	ladosColonia[i] = terreno.getLadoPosicaoEstrada(posicaoEstrada);
            }
            // Pega próximo terreno
            terreno = terreno.getTerrenoVizinho(ladoTerreno);
            if (lado == 0) {
                ladoTerreno = (ladoTerreno + 4) % 6;
            } else {
                ladoTerreno = (ladoTerreno + 2) % 6;
            }
            posicaoEstrada = terreno.getPosicaoEstrada(ladoTerreno);
            if (posicaoEstrada == null) {
                return;
            }
        }
        
        PosicaoColonia posicaoColonia = new PosicaoColonia(tabuleiro, posicoesEstradaColonia, terrenosColonia, ladosColonia);
        for (int i = 0; i < 3; i++) {
        	posicoesEstradaColonia[i].setPosicaoColonia(posicaoColonia);
        }
	}
	
	public Terreno getTerrenoOposto(Terreno terreno) {
        if (terrenos[0] == terreno) {
            return terrenos[1];
        } else if (terrenos[1] == terreno) {
            return terrenos[0];
        } else {
            return null;
        }
	}
	
    public int getIdentificador() {
		return identificador;
	}

	public Terreno getTerreno(int lado) {
        return terrenos[lado % 2];
	}

	public Estrada getEstrada() {
		return estrada;
	}

	public void setEstrada(Estrada estrada) {
		this.estrada = estrada;
		this.estrada.setPosicaoEstrada(this);
	}
	
	public PosicaoColonia getPosicaoColonia(int lado) {
		return posicoesColonia[lado % 2];
	}
	
	public void setPosicaoColonia(PosicaoColonia posicaoColonia) {
		if (posicoesColonia[0] == null) {
			posicoesColonia[0] = posicaoColonia;
		} else {
			posicoesColonia[1] = posicaoColonia;
		}
	}
	
	public boolean possuiEstradaJogador(int jogador) {
		return estrada != null && estrada.getJogador().getId() == jogador;		
	}
	
	public boolean isConstruivelEstrada(int jogador) {
		if (estrada != null) {
			return false;
		}
		if (possuiColoniaExtremidades(jogador) || possuiEstradaExtremidades(jogador)) {
			return true;
		}
		return false;
	}
	
	private boolean possuiColoniaExtremidades(int jogador) {
		for (int i = 0; i < 2; i++) {
			if (posicoesColonia[i] != null && posicoesColonia[i].getColonia() != null && posicoesColonia[i].getColonia().getJogador().getId() == jogador) {
				return true;
			}
		}
		return false;
	}
	
	private boolean possuiEstradaExtremidades(int jogador) {
		for (int i = 0; i < 2; i++) {
			if (posicoesColonia[i] != null && posicoesColonia[i].possuiEstradaExtremidades(jogador)) {
				return true;
			}
		}
		return false;
	}
	
	public LinkedList<Estrada> getEstradasVizinhas(int lado, int jogador) {
		LinkedList<Estrada> estradas = posicoesColonia[lado].getEstradas(jogador);
		estradas.remove(estrada);
		return estradas;
	}
	
}
