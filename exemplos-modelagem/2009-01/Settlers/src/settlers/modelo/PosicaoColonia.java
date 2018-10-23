package settlers.modelo;

import java.util.LinkedList;

/**
 * 
 * @author Murilo Fernando de Oliveira
 * @author Paulo Rogério de Pinho Filho
 * 
 */
public class PosicaoColonia {

	private static int contadorId = 0;
	
	private Tabuleiro tabuleiro;
	private PosicaoEstrada posicoesEstrada[];
	private Terreno terrenos[];
	private Colonia colonia;
	private Porto porto;
    private int identificador;
	
	public PosicaoColonia(Tabuleiro tabuleiro, PosicaoEstrada posicoesEstrada[], Terreno terrenos[], int lados[]) {
    	this.identificador = contadorId++;
		this.tabuleiro = tabuleiro;
		this.tabuleiro.registrarPosicaoColonia(this);
		this.posicoesEstrada = posicoesEstrada;
		this.terrenos = terrenos;
        for (int i = 0; i < 3; i++) {
        	if (terrenos[i] != null) {
        		terrenos[i].setPosicaoColonia(this, lados[i]);
        	}
        }
	}
	
	public void limpar() {
		colonia = null;
	}
	
    public int getIdentificador() {
		return identificador;
	}
	
	public Terreno getTerreno(int lado) {
        return terrenos[lado % 3];
	}
	
	public PosicaoEstrada getPosicaoEstrada(int lado) {
        return posicoesEstrada[lado % 3];
	}
	
	public Colonia getColonia() {
		return colonia;
	}
	
	public void setColonia(Colonia colonia) {
		this.colonia = colonia;
		this.colonia.setPosicaoColonia(this);
	}
	
	public PosicaoColonia getColoniaVizinha(int lado) {
		if (posicoesEstrada[lado] == null) {
			return null;
		}
		if (posicoesEstrada[lado].getPosicaoColonia(0) == this) {
			return posicoesEstrada[lado].getPosicaoColonia(1);
		}
		return posicoesEstrada[lado].getPosicaoColonia(0);
	}
	
	public boolean possuiEstradaExtremidades(int jogador) {
		for (int i = 0; i < 3; i++) {
			if (posicoesEstrada[i].possuiEstradaJogador(jogador)) {
				return true;
			}
		}
		return false;
	}
	
	public int getQuantidadeEstradas() {
		int quantidade = 0;
		for (int i = 0; i < 3; i++) {
			if (posicoesEstrada[i].getEstrada() != null) {
				quantidade++;
			}
		}
		return quantidade;
	}

	public LinkedList<Estrada> getEstradas(int jogador) {
		LinkedList<Estrada> estradas = new LinkedList<Estrada>();
		for (int i = 0; i < 3; i++) {
			if (posicoesEstrada[i].getEstrada() != null && posicoesEstrada[i].getEstrada().getJogador().getId() == jogador) {
				estradas.add(posicoesEstrada[i].getEstrada());
			}
		}
		return estradas;
	}
	
	public Porto getPorto() {
		return porto;
	}

	public void setPorto(Porto porto) {
		this.porto = porto;
	}

	public boolean isVazia() {
		return colonia == null;
	}
	
	public boolean isLivre() {
		if (!isVazia()) {
			return false;
		}
		for (int i = 0; i < 3; i++) {
			if (getColoniaVizinha(i) != null && !getColoniaVizinha(i).isVazia()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isConstruivelVila(int jogador) {
		if (!isLivre()) {
			return false;
		}
		for (int i = 0; i < 3; i++) {
			if (posicoesEstrada[i] != null && posicoesEstrada[i].getEstrada() != null && posicoesEstrada[i].getEstrada().getJogador().getId() == jogador) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isConstruivelCidade(int jogador) {
		return !isVazia() && colonia instanceof Vila && colonia.getJogador().getId() == jogador;
	}

}
