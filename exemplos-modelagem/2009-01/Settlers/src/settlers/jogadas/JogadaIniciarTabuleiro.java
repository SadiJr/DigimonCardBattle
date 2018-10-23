package settlers.jogadas;

import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaIniciarTabuleiro implements Jogada {

	private static final long serialVersionUID = 1L;
	
	private int camadas;
	private int terrenos[][];
	private int numeros[][];
	private int portos[];
	private int camadaLadrao;
	private int identificadorLadrao;
	private int numeroJogadores;
	
	public JogadaIniciarTabuleiro(int camadas) {
		this.camadas = camadas;
		// Instancia array camadas
		terrenos = new int[camadas][];
		numeros = new int[camadas][];
		// Instancia array camada 0
		terrenos[0] = new int[1];
		numeros[0] = new int[1];
		// Instancia array demais camadas
		for (int i = 1; i < camadas; i++) {
			terrenos[i] = new int[i * 6];
			numeros[i] = new int[i * 6];
		}
		// Instancia array portos
		portos = new int[9];
	}
	
	public int getCamadas() {
		return camadas;
	}
	
	public void setTerreno(int camada, int identificador, int valor) {
		terrenos[camada][identificador] = valor;
	}
	
	public int getTerreno(int camada, int identificador) {
		return terrenos[camada][identificador];
	}
	
	public int getTamanhoCamada(int camada) {
		return terrenos[camada].length;
	}
	
	public void setNumero(int camada, int identificador, int valor) {
		numeros[camada][identificador] = valor;
	}
	
	public int getNumero(int camada, int identificador) {
		return numeros[camada][identificador];
	}
	
	public void setPosicaoLadrao(int camada, int identificador) {
		this.camadaLadrao = camada;
		this.identificadorLadrao = identificador;
	}

	public int getCamadaLadrao() {
		return camadaLadrao;
	}

	public int getIdentificadorLadrao() {
		return identificadorLadrao;
	}
	
	public int getQuantidadePortos() {
		return portos.length;
	}
	
	public int getPorto(int identificador) {
		return portos[identificador];
	}
	
	public void setPorto(int identificador, int recurso) {
		portos[identificador] = recurso;
	}

	public int getNumeroJogadores() {
		return numeroJogadores;
	}

	public void setNumeroJogadores(int numeroJogadores) {
		this.numeroJogadores = numeroJogadores;
	}
	
}
