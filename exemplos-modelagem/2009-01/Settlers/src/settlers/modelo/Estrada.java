package settlers.modelo;

import java.util.ArrayList;
import java.util.LinkedList;

public class Estrada {
	
	private Jogador jogador;
	private PosicaoEstrada posicaoEstrada;
	
	public Estrada(Jogador jogador) {
		this.jogador = jogador;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
	public PosicaoEstrada getPosicaoEstrada() {
		return posicaoEstrada;
	}

	public void setPosicaoEstrada(PosicaoEstrada posicaoEstrada) {
		this.posicaoEstrada = posicaoEstrada;
	}

	public int getTamanhoEstrada() {
		ArrayList<Estrada> contabilizadas = new ArrayList<Estrada>();
		return getTamanhoEstrada(this, 0, contabilizadas) +  getTamanhoEstrada(this, 1, contabilizadas) - 1;
	}
	
	private int getTamanhoEstrada(Estrada estrada, int lado, ArrayList<Estrada> contabilizadas) {
		contabilizadas.add(estrada);
		int maiorTamanho = 0;
		LinkedList<Estrada> estradas;
		if (lado >= 0) {
			estradas = estrada.getEstradasVizinhas(lado);
			for (int i = 0; i < estradas.size(); i++) {
				if (contabilizadas.contains(estradas.get(i))) {
					estradas.remove(estradas.get(i));
				}
			}
		} else {
			estradas = estrada.getEstradasVizinhas(contabilizadas);
		}
		contabilizadas.addAll(estradas);
		for (int i = 0; i < estradas.size(); i++) {
			int tamanho = getTamanhoEstrada(estradas.get(i), -1, contabilizadas);
			if (tamanho > maiorTamanho) {
				maiorTamanho = tamanho;
			}
		}
		return 1 + maiorTamanho;
	}
	
	private LinkedList<Estrada> getEstradasVizinhas(ArrayList<Estrada> contabilizadas) {
		LinkedList<Estrada> estradasVizinhas = new LinkedList<Estrada>();
		for (int i = 0; i < 2; i++) {
			LinkedList<Estrada> estradas = getEstradasVizinhas(i);
			if (estradas != null) {
				for (int j = 0; j < estradas.size(); j++) {
					if (!contabilizadas.contains(estradas.get(j))) {
						estradasVizinhas.add(estradas.get(j));
					}
				}
			}
		}
		return estradasVizinhas;
	}
	
	private LinkedList<Estrada> getEstradasVizinhas(int lado) {
		return posicaoEstrada.getEstradasVizinhas(lado % 2, jogador.getId());
	}
	
}
