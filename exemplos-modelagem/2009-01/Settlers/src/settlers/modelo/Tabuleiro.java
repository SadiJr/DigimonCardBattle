package settlers.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import settlers.jogadas.JogadaIniciarTabuleiro;

/**
 * 
 * @author Murilo Fernando de Oliveira
 * @author Paulo Rogério de Pinho Filho
 * 
 */
public class Tabuleiro {

	private int camadas;
	private int jogadorInicial;
	private int jogadorInstancia;
	private int jogadorDaVez;
	private int valorDado1;
	private int valorDado2;

	private int turno;
	private int etapaJogadaComposta;
	private int numeroJogadores;

	private Jogador jogadores[];
	private Terreno terrenos[][];
	private Terreno terrenoLadrao;
	private Porto portos[];
	
	private CartaMaiorExercito cartaMaiorExercito;
	private CartaMaiorEstrada cartaMaiorEstrada;

	private LinkedList<CartaDesenvolvimento> poolCartasDesenvolvimento;

	private ArrayList<PosicaoColonia> posicoesColonia;
	private ArrayList<PosicaoEstrada> posicoesEstrada;

	private ArrayList<Integer> repositorioTerrenos;
	private ArrayList<Integer> repositorioNumeros;
	private ArrayList<Integer> repositorioPortos;

    public static final int RECURSO_X       = -1;
    public static final int RECURSO_OVELHA  =  0;
    public static final int RECURSO_MADEIRA =  1;
    public static final int RECURSO_MINERIO =  2;
    public static final int RECURSO_TIJOLO  =  3;
    public static final int RECURSO_TRIGO   =  4;
	
	public static final int TERRENO_DESERTO   = -1;
    public static final int TERRENO_PASTO     =  0;
    public static final int TERRENO_FLORESTA  =  1;
    public static final int TERRENO_MINERIO   =  2;
    public static final int TERRENO_ARGILA    =  3;
    public static final int TERRENO_PLANTACAO =  4;

	public Tabuleiro(int camadas) {
		this.camadas = camadas;
		this.posicoesEstrada = new ArrayList<PosicaoEstrada>();
		this.posicoesColonia = new ArrayList<PosicaoColonia>();
		
		gerarTerrenos();
		gerarPortos();
		gerarRepositorios();
		
		embaralharTabuleiro();
	}

	public void iniciarTabuleiro(int numeroJogadores) {
		removerPeoes();
		setValorDado1(0);
		setValorDado2(0);
		gerarPoolCartas();
		embaralharTabuleiro();
		instanciarJogadores(numeroJogadores);
	}

	public void atualizarTabuleiro(JogadaIniciarTabuleiro jogadaIniciarTabuleiro) {
		removerPeoes();
		setValorDado1(0);
		setValorDado2(0);
		gerarPoolCartas();
		// Atualiza camadas
		camadas = jogadaIniciarTabuleiro.getCamadas();
		// Atualiza terrenos
		for (int camada = 0; camada < terrenos.length; camada++) {
			for (int identificador = 0; identificador < terrenos[camada].length; identificador++) {
				terrenos[camada][identificador].setTipo(jogadaIniciarTabuleiro.getTerreno(camada, identificador));
				terrenos[camada][identificador].setNumero(jogadaIniciarTabuleiro.getNumero(camada, identificador));
			}
		}
		// Atualiza ladrao
		terrenoLadrao = terrenos[jogadaIniciarTabuleiro.getCamadaLadrao()][jogadaIniciarTabuleiro.getIdentificadorLadrao()];
		// Atualiza portos
		for (int i = 0; i < portos.length; i++) {
			portos[i].setRecurso(jogadaIniciarTabuleiro.getPorto(i));
		}
		// Instancia Jogadores
		instanciarJogadores(jogadaIniciarTabuleiro.getNumeroJogadores());
	}

	public JogadaIniciarTabuleiro getJogadaTabuleiro() {
		// Instancia JogadaTabuleiro
		JogadaIniciarTabuleiro jogadaTabuleiro = new JogadaIniciarTabuleiro(camadas);
		// Define terrenos
		for (int camada = 0; camada < terrenos.length; camada++) {
			for (int identificador = 0; identificador < terrenos[camada].length; identificador++) {
				jogadaTabuleiro.setTerreno(camada, identificador, terrenos[camada][identificador].getTipo());
				jogadaTabuleiro.setNumero(camada, identificador, terrenos[camada][identificador].getNumero());
			}
		}
		// Define posicao ladrao
		if (terrenoLadrao != null) {
			jogadaTabuleiro.setPosicaoLadrao(terrenoLadrao.getCamada(), terrenoLadrao.getId());
		} else {
			jogadaTabuleiro.setPosicaoLadrao(0, 0);
		}
		// Define portos
		for (int i = 0; i < portos.length; i++) {
			jogadaTabuleiro.setPorto(i, portos[i].getRecurso());
		}
		// Numero Jogadores
		if (jogadores != null) {
			jogadaTabuleiro.setNumeroJogadores(jogadores.length);
		}
		return jogadaTabuleiro;
	}
	
	public void finalizarPartida() {
		setJogadorInstancia(0);
		setJogadorInicial(0);
		setJogadorDaVez(0);
		setTurno(0);
	}

	private void removerPeoes() {
		for (int i = 0; i < posicoesEstrada.size(); i++) {
			posicoesEstrada.get(i).limpar();
		}
		for (int i = 0; i < posicoesColonia.size(); i++) {
			posicoesColonia.get(i).limpar();
		}
	}

	private void instanciarJogadores(int numeroJogadores) {
		this.numeroJogadores = numeroJogadores;
		jogadores = new Jogador[numeroJogadores];
	}

	public void atualizarJogador(int posicao, String nome) {
		if (jogadores[posicao - 1] == null) {
			jogadores[posicao - 1] = new Jogador(posicao, nome);
		} else {
			jogadores[posicao - 1].setNome(nome);
		}
	}

	public int getNumeroJogadores() {
		return numeroJogadores;
	}

	public int getCamadas() {
		return camadas;
	}

	public void setCamadas(int camadas) {
		this.camadas = camadas;
	}

	public int getQuantidadeJogadores() {
		return jogadores == null ? 0 : jogadores.length;
	}

	public int getJogadorInstancia() {
		return jogadorInstancia;
	}

	public void setJogadorInstancia(int jogadorInstancia) {
		this.jogadorInstancia = jogadorInstancia;
	}

	public String getNomeJogador() {
		return getNomeJogador(jogadorInstancia);
	}

	public String getNomeJogador(int jogador) {
		if (jogador > 0 && jogadores.length >= jogador && jogadores[jogador - 1] != null) {
			return jogadores[jogador - 1].getNome();
		} else {
			return null;
		}
	}

	public int getQuantidadeEstradas() {
		if (jogadorInstancia > 0 && jogadores[jogadorInstancia - 1] != null) {
			return jogadores[jogadorInstancia - 1].getQuantidadeEstradas();
		} else {
			return 0;
		}
	}

	public int getQuantidadeVilas() {
		if (jogadorInstancia > 0 && jogadores[jogadorInstancia - 1] != null) {
			return jogadores[jogadorInstancia - 1].getQuantidadeVilas();
		} else {
			return 0;
		}
	}

	public int getQuantidadeCidades() {
		if (jogadorInstancia > 0 && jogadores[jogadorInstancia - 1] != null) {
			return jogadores[jogadorInstancia - 1].getQuantidadeCidades();
		} else {
			return 0;
		}
	}

	public int getJogadorInicial() {
		return jogadorInicial;
	}

	public void setJogadorInicial(int jogadorInicial) {
		this.jogadorInicial = jogadorInicial;
	}

	public int getJogadorFinal() {
		return (jogadorInicial - 2 + numeroJogadores) % numeroJogadores + 1;
	}

	public boolean isJogadorDaVez() {
		return jogadorDaVez == jogadorInstancia;
	}

	public Jogador getJogadorDaVez() {
		return jogadores[jogadorDaVez - 1];
	}

	public Jogador getJogador(int posicao) {
		if (jogadores != null && posicao <= numeroJogadores) {
			return jogadores[posicao - 1];
		} else {
			return null;
		}
	}

	public void setJogadorDaVez(int jogadorDaVez) {
		this.jogadorDaVez = jogadorDaVez;
	}
	
	public String[] getNomesJogadores() {
		String[] nomes = new String[numeroJogadores];
		for (int i = 0; i < numeroJogadores; i++) {
			nomes[i] = jogadores[i].getNome();
		}
		return nomes;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
		if (turno == 0) {
			etapaJogadaComposta = 0;
		}
	}

	public boolean isPartidaEmAndamento() {
		return turno > 0;
	}

	public void incrementarJogadorDaVez() {
		if (turno == 1) {
			if (jogadorDaVez != getJogadorFinal()) {
				jogadorDaVez = jogadorDaVez % numeroJogadores + 1;
			} else {
				turno++;
			}
		} else if (turno == 2) {
			if (jogadorDaVez != jogadorInicial) {
				jogadorDaVez = (jogadorDaVez - 2 + numeroJogadores) % numeroJogadores + 1;
			} else {
				turno++;
			}
		} else {
			jogadorDaVez = jogadorDaVez % numeroJogadores + 1;
		}
	}

	public void atualizarTurno() {
		if (turno > 2 && jogadorInicial == jogadorDaVez) {
			turno++;
		}
	}

	public int getValorDado1() {
		return valorDado1;
	}

	public void setValorDado1(int valor) {
		valorDado1 = valor;
	}

	public int getValorDado2() {
		return valorDado2;
	}

	public void setValorDado2(int valor) {
		valorDado2 = valor;
	}

	public int getValorDados() {
		return valorDado1 + valorDado2;
	}

	public void rolarDados() {
		valorDado1 = (int) (6 * Math.random() + 1);
		valorDado2 = (int) (6 * Math.random() + 1);
	}

	public int getEtapaJogadaComposta() {
		return etapaJogadaComposta;
	}

	public void setEtapaJogadaComposta(int etapaJogadaComposta) {
		this.etapaJogadaComposta = etapaJogadaComposta;
	}

	public int[][] getTerrenosSorteados() {
		int terrenosSorteados[][] = new int[camadas][];
		for (int camada = 0; camada < camadas; camada++) {
			terrenosSorteados[camada] = new int[terrenos[camada].length];
			for (int i = 0; i < terrenos[camada].length; i++) {
				if (terrenos[camada][i].getNumero() == getValorDados()) {
					terrenosSorteados[camada][i] = 1;
				}
			}
		}
		return terrenosSorteados;
	}
	
	public int getRecursos(int tipo) {
		return jogadores[jogadorInstancia - 1].getQuantidadeRecursos(tipo);
	}

	public void proverRecursos() {
		for (int camada = 0; camada < camadas; camada++) {
			for (int i = 0; i < terrenos[camada].length; i++) {
				if (terrenos[camada][i].getNumero() == getValorDados()) {
					proverRecursos(terrenos[camada][i]);
				}
			}
		}
	}
	
	public void proverRecursos(Jogador jogador) {
		for (int camada = 0; camada < camadas; camada++) {
			for (int i = 0; i < terrenos[camada].length; i++) {
				proverRecursos(terrenos[camada][i], jogador);
			}
		}
	}

	public void proverRecursos(Terreno terreno) {
		if (terreno.getTipo() != Tabuleiro.TERRENO_DESERTO && terreno != terrenoLadrao) {
			LinkedList<Colonia> coloniasRedondeza = terreno.buscarColoniasRedondeza();
			for (int j = 0; j < coloniasRedondeza.size(); j++) {
				coloniasRedondeza.get(j).getJogador().receberRecursos(terreno.getTipo(), coloniasRedondeza.get(j).getRecursosProduzidos());
			}
		}
	}

	public void proverRecursos(Terreno terreno, Jogador jogador) {
		if (terreno.getTipo() != Tabuleiro.TERRENO_DESERTO && terreno != terrenoLadrao) {
			LinkedList<Colonia> coloniasRedondeza = terreno.buscarColoniasRedondeza();
			for (int j = 0; j < coloniasRedondeza.size(); j++) {
				if (coloniasRedondeza.get(j).getJogador() == jogador) {
					jogador.receberRecursos(terreno.getTipo(), coloniasRedondeza.get(j).getRecursosProduzidos());
				}
			}
		}
	}

	public void registrarPosicaoEstrada(PosicaoEstrada posicaoEstrada) {
		posicoesEstrada.add(posicaoEstrada);
	}

	public void registrarPosicaoColonia(PosicaoColonia posicaoColonia) {
		posicoesColonia.add(posicaoColonia);
	}

	public ArrayList<Integer> buscarPosicoesEstradaLivres() {
		ArrayList<Integer> posicoes = new ArrayList<Integer>();
		if (turno == 2) {
			for (int i = 0; i < posicoesColonia.size(); i++) {
				if (!posicoesColonia.get(i).isVazia() && posicoesColonia.get(i).getColonia().getJogador().getId() == jogadorDaVez && posicoesColonia.get(i).getQuantidadeEstradas() == 0) {
					for (int j = 0; j < 3; j++) {
						posicoes.add(posicoesColonia.get(i).getPosicaoEstrada(j).getIdentificador());
					}
					return posicoes;
				}
			}
		} else {
			for (int i = 0; i < posicoesEstrada.size(); i++) {
				if (posicoesEstrada.get(i).isConstruivelEstrada(jogadorDaVez)) {
					posicoes.add(i);
				}
			}
		}
		return posicoes;
	}

	public ArrayList<Integer> buscarPosicoesColoniaLivres() {
		ArrayList<Integer> posicoes = new ArrayList<Integer>();
		for (int i = 0; i < posicoesColonia.size(); i++) {
			if (turno <= 2) {
				if (posicoesColonia.get(i).isLivre()) {
					posicoes.add(i);
				}
			} else {
				if (posicoesColonia.get(i).isConstruivelVila(jogadorDaVez)) {
					posicoes.add(i);
				}
			}
		}
		return posicoes;
	}

	public ArrayList<Integer> buscarPosicoesColoniasComVila() {
		ArrayList<Integer> posicoes = new ArrayList<Integer>();
		for (int i = 0; i < posicoesColonia.size(); i++) {
			if (posicoesColonia.get(i).isConstruivelCidade(jogadorDaVez)) {
				posicoes.add(i);
			}
		}
		return posicoes;
	}

	public boolean isConstruivelEstrada() {
		if (!getJogadorDaVez().possuiEstradas()) {
			return false;
		}
		if (!getJogadorDaVez().possuiRecursosEstrada()) {
			return false;
		}
		return buscarPosicoesEstradaLivres().size() > 0;
	}

	public boolean isConstruivelVila() {
		if (!getJogadorDaVez().possuiVilas()) {
			return false;
		}
		if (!getJogadorDaVez().possuiRecursosVila()) {
			return false;
		}
		return buscarPosicoesColoniaLivres().size() > 0;
	}

	public boolean isConstruivelCidade() {
		if (!getJogadorDaVez().possuiVilas()) {
			return false;
		}
		if (!getJogadorDaVez().possuiRecursosCidade()) {
			return false;
		}
		return buscarPosicoesColoniasComVila().size() > 0;
	}
	
	public boolean isOferecivelTroca() {
		return getJogadorDaVez().getQuantidadeRecursos() > 0;
	}

	public boolean isCompravelCarta() {
		return poolCartasDesenvolvimento.size() > 0 && getJogadorDaVez().possuiRecursoCarta();
	}

	public boolean isDisponivelCarta() {
		return getJogadorDaVez().getQuantidadeCartasDisponiveis(turno) > 0;
	}
	
	public boolean isTrocavelMaritima() {
		// Verifica portos
		ArrayList<Porto> portosJogador = getJogadorDaVez().getPortos();
		for (int i = 0; i < portosJogador.size(); i++) {
			if (portosJogador.get(i).getRecurso() == Tabuleiro.RECURSO_X) {
				// Verifica se possui o mínimo necessário de recursos
				for (int j = 0; j < portosJogador.get(i).getProporcao(); j++) {
					if (getJogadorDaVez().getQuantidadeRecursos(j) >= portosJogador.get(i).getProporcao()) {
						return true;
					}
				}
			} else {
				// Verifica se possui o mínimo necessário de recurso específico
				if (getJogadorDaVez().getQuantidadeRecursos(portosJogador.get(i).getRecurso()) >= portosJogador.get(i).getProporcao()) {
					return true;
				}
			}
		}
		// Verifica trocas 4 : 1
		for (int i = 0; i < 5; i++) {
			if (getJogadorDaVez().getQuantidadeRecursos(i) >= 4) {
				return true;
			}
		}
		return false;
	}

	public int buscarCamadaTerrenoLadrao() {
		if (terrenoLadrao == null) {
			throw new RuntimeException("Sem ladrão definido");
		}
		return terrenoLadrao.getCamada();
	}

	public int buscarIdentificadorTerrenoLadrao() {
		if (terrenoLadrao == null) {
			throw new RuntimeException("Sem ladrão definido");
		}
		return terrenoLadrao.getId();
	}

	public void moverLadrao(int camada, int identificador, int jogador) {
		terrenoLadrao = terrenos[camada][identificador];
		if (jogador > 0) {
			if (getJogador(jogador).getQuantidadeRecursos() > 0) {
				int recursoRoubado = getJogador(jogador).roubarRecursoAleatorio();
				if (recursoRoubado >= 0) {
					getJogadorDaVez().receberRecurso(recursoRoubado);
				}
			}
		}
	}

	public int[] buscarJogadoresRedondeza(int camada, int identificador) {
		return terrenos[camada][identificador].buscarJogadoresRedondeza();
	}

	public void construirEstrada(int identificador) {
		Estrada estrada = getJogadorDaVez().getEstrada();
		posicoesEstrada.get(identificador).setEstrada(estrada);
		// Calcula tamanho da estrada e registra em atributo do jogador se for a maior
		int tamanhoEstrada = estrada.getTamanhoEstrada();
		if (tamanhoEstrada > getJogadorDaVez().getMaiorEstrada()) {
			getJogadorDaVez().setMaiorEstrada(tamanhoEstrada);
		}
		if (turno <= 2) {
			etapaJogadaComposta++;
		}
		if (turno == 2) {
			proverRecursos(getJogadorDaVez());
		} else if (turno > 2) {
			if (getJogadorDaVez().getCartaNaMesa() == CartaDesenvolvimento.CARTA_CONSTRUCAO_ESTRADA) {
				if (etapaJogadaComposta == 1) {
					getJogadorDaVez().consumirCartaMesa();
				}
				etapaJogadaComposta++;
			} else {
				getJogadorDaVez().consumirRecursosEstrada();
			}
			// Tamanho mínimo da estrada para ganhar carta: 5
			if (tamanhoEstrada >= 5) {
				if (cartaMaiorEstrada.getJogador() == null || cartaMaiorEstrada.getJogador().getMaiorEstrada() < tamanhoEstrada) {
					cartaMaiorEstrada.setJogador(getJogadorDaVez());
				}
			}
		}
	}
	
	public int getCartaNaMesa() {
		return getJogadorDaVez().getCartaNaMesa();
	}

	public void construirVila(int identificador) {
		if (turno <= 2) {
			etapaJogadaComposta++;
		}
		posicoesColonia.get(identificador).setColonia(getJogadorDaVez().getVila());
		if (turno > 2) {
			getJogadorDaVez().consumirRecursosVila();
		}
	}

	public void construirCidade(int identificador) {
		getJogadorDaVez().devolverVila((Vila) posicoesColonia.get(identificador).getColonia());
		posicoesColonia.get(identificador).setColonia(getJogadorDaVez().getCidade());
		getJogadorDaVez().consumirRecursosCidade();
	}
	
	public void trocaMaritima(int recursoOfertado, int quantidadeOfertada, int recursoRecebido, int quantidadeRecebida) {
		getJogadorDaVez().consumirRecurso(recursoOfertado, quantidadeOfertada);
		getJogadorDaVez().receberRecursos(recursoRecebido, quantidadeRecebida);
	}
	
	public int sortearCarta() {
		return (int) (poolCartasDesenvolvimento.size() * Math.random());
	}
	
	public int getTipoCarta(int carta) {
		return poolCartasDesenvolvimento.get(carta).getTipo();
	}
	
	public int[] getCartasDisponiveis() {
		return getJogadorDaVez().getCartasDisponiveis(turno);
	}
	
	public void comprarCarta(int carta) {
		CartaDesenvolvimento cartaRecebida = poolCartasDesenvolvimento.remove(carta);
		cartaRecebida.setTurnoRecebimento(turno);
		getJogadorDaVez().receberCartaDesenvolvimento(cartaRecebida);
		getJogadorDaVez().consumirRecursosCarta();
	}
	
	public void usarCarta(int carta) {
		getJogadorDaVez().usarCarta(carta);
		// Tamanho mínimo do exército para receber carta: 3
		if (carta == CartaDesenvolvimento.CARTA_SOLDADO && getJogadorDaVez().getTamanhoExercito() >= 3) {
			concederCartaMaiorExercito();
		}
	}
	
	public void concederCartaMaiorExercito() {
		Jogador jogador = null;
		for (int i = 1; i <= numeroJogadores; i++) {
			if (cartaMaiorExercito.getJogador() == null || cartaMaiorExercito.getJogador().getId() != i) {
				if (getJogador(i).getTamanhoExercito() >= 3) {
					if (jogador == null || jogador.getTamanhoExercito() < getJogador(i).getTamanhoExercito()) {
						jogador = getJogador(i);
					}
				}
			}
		}
		if (jogador != null) {
			cartaMaiorExercito.setJogador(jogador);
		}
	}
	
	public void monopolizarRecurso(int recurso) {
		for (int i = 1; i <= numeroJogadores; i++) {
			if (getJogador(i).getId() != getJogadorDaVez().getId()) {
				int quantidade = getJogador(i).getQuantidadeRecursos(recurso);
				if (quantidade > 0) {
					getJogador(i).consumirRecurso(recurso, quantidade);
					getJogadorDaVez().receberRecursos(recurso, quantidade);
				}
			}
		}
		getJogadorDaVez().consumirCartaMesa();
	}
	
	public void anoFartura(int recurso) {
		getJogadorDaVez().receberRecursos(recurso, 1);
		if (etapaJogadaComposta == 1) {
			getJogadorDaVez().consumirCartaMesa();
		}
	}

	public void efetuarTroca(int jogadorOrigem, int jogadorDestino, int oferta[], int procura[]) {
		for (int i = 0; i < 5; i++) {
			getJogador(jogadorOrigem).consumirRecurso(i, oferta[i]);
			getJogador(jogadorOrigem).receberRecursos(i, procura[i]);
			getJogador(jogadorDestino).consumirRecurso(i, procura[i]);
			getJogador(jogadorDestino).receberRecursos(i, oferta[i]);
		}
	}

	public void passarVez() {
		setValorDado1(0);
		setValorDado2(0);
		atualizarTurno();
		incrementarJogadorDaVez();
		setEtapaJogadaComposta(0);
	}

	public boolean isVencedor() {
		return isVencedor(getJogadorDaVez());
	}
	
	public boolean isVencedor(Jogador jogador) {
		return jogador.getTotalPontos() >= 10;
	}
	
	public int getJogadorVencedor() {
		if (turno == 0) {
			return 0;
		}
		for (int i = 1; i <= numeroJogadores; i++) {
			if (isVencedor(getJogador(i))) {
				return i;
			}
		}
		return 0;
	}
	
	public String[][] getResultado() {
		String[][] resultado = new String[numeroJogadores + 1][7];
		// Cabeçalho
		resultado[0][0] = "Jogador";
		resultado[0][1] = "Vilas";
		resultado[0][2] = "Cidades";
		resultado[0][3] = "Maior Estrada";
		resultado[0][4] = "Maior Exército";
		resultado[0][5] = "Pontos de Vitória";
		resultado[0][6] = "Pontuação";
		// Resultado
		for (int i = 1; i <= numeroJogadores; i++) {
			// Nome
			resultado[i][0] = getJogador(i).getNome();
			// Vilas
			resultado[i][1] = getJogador(i).getQuantidadeVilasEmJogo() > 0 ? Integer.toString(getJogador(i).getQuantidadeVilasEmJogo()) : "-";
			// Cidades
			resultado[i][2] = getJogador(i).getQuantidadeCidadesEmJogo() >  0 ? Integer.toString(getJogador(i).getQuantidadeCidadesEmJogo()) + " x 2" : "-";
			// Maior Estrada
			resultado[i][3] = getJogador(i).getCartaMaiorEstrada() != null ? "2" : "-";
			// Maior Exército
			resultado[i][4] = getJogador(i).getCartaMaiorExercito() != null ? "2" : "-";
			// Carta Ponto de Vitória
			resultado[i][5] = getJogador(i).getPontosVitoria() > 0 ? Integer.toString(getJogador(i).getPontosVitoria()) : "-";
			// Total Pontuação
			resultado[i][6] = Integer.toString(getJogador(i).getTotalPontos());
		}
		return resultado; 
	}

	private void gerarTerrenos() {
		instanciarEstruturaTerrenos();
		gerarEstruturaTerrenos();
	}

	private void instanciarEstruturaTerrenos() {
		// Instancia array camadas
		terrenos = new Terreno[camadas][];
		// Instancia array camada 0
		terrenos[0] = new Terreno[1];
		// Instancia array demais camadas
		for (int i = 1; i < camadas; i++) {
			terrenos[i] = new Terreno[i * 6];
		}
	}

	private void gerarEstruturaTerrenos() {
		// Gera terreno central
		terrenos[0][0] = new Terreno(this, 0, 0);
		// Gera demais terrenos
		for (int camada = 1; camada <= camadas; camada++) {
			int identificador = 0;
			for (int lado = 0; lado < 6; lado++) {
				Terreno terreno = terrenos[0][0].getTerrenoMaisDistante(lado);
				for (int i = 0; i < camada; i++) {
					int ladoTerreno = (i == 0) ? lado : ((lado + 3) % 6) - 1;
					terreno = new Terreno(terreno, ladoTerreno, camada, identificador);
					if (camada < camadas) {
						terrenos[camada][identificador] = terreno;
					}
					identificador++;
				}
			}
		}
	}

	/**
	 * Instancia e associa os portos as respectivas posicoes colonia de acordo
	 * com as regras do jogo
	 */
	private void gerarPortos() {
		portos = new Porto[9];
		portos[0] = new Porto(terrenos[camadas - 1][ 0].getPosicaoColonia(5), terrenos[camadas - 1][ 0].getPosicaoColonia(0));
		portos[1] = new Porto(terrenos[camadas - 1][ 1].getPosicaoColonia(0), terrenos[camadas - 1][ 1].getPosicaoColonia(1));
		portos[2] = new Porto(terrenos[camadas - 1][ 3].getPosicaoColonia(0), terrenos[camadas - 1][ 3].getPosicaoColonia(1));
		portos[3] = new Porto(terrenos[camadas - 1][ 4].getPosicaoColonia(1), terrenos[camadas - 1][ 4].getPosicaoColonia(2));
		portos[4] = new Porto(terrenos[camadas - 1][ 5].getPosicaoColonia(2), terrenos[camadas - 1][ 5].getPosicaoColonia(3));
		portos[5] = new Porto(terrenos[camadas - 1][ 7].getPosicaoColonia(2), terrenos[camadas - 1][ 7].getPosicaoColonia(3));
		portos[6] = new Porto(terrenos[camadas - 1][ 8].getPosicaoColonia(3), terrenos[camadas - 1][ 8].getPosicaoColonia(4));
		portos[7] = new Porto(terrenos[camadas - 1][ 9].getPosicaoColonia(4), terrenos[camadas - 1][ 9].getPosicaoColonia(5));
		portos[8] = new Porto(terrenos[camadas - 1][11].getPosicaoColonia(4), terrenos[camadas - 1][11].getPosicaoColonia(5));
	}

	private void gerarRepositorios() {
		gerarRepositorioTerrenos();
		gerarRepositorioNumeros();
		gerarRepositorioPortos();
	}

	private void gerarRepositorioTerrenos() {
		repositorioTerrenos = new ArrayList<Integer>();
		// Deserto: 01
		repositorioTerrenos.add(Tabuleiro.TERRENO_DESERTO);
		// Floresta: 04
		repositorioTerrenos.add(Tabuleiro.TERRENO_FLORESTA);
		repositorioTerrenos.add(Tabuleiro.TERRENO_FLORESTA);
		repositorioTerrenos.add(Tabuleiro.TERRENO_FLORESTA);
		repositorioTerrenos.add(Tabuleiro.TERRENO_FLORESTA);
		// Plantação: 04
		repositorioTerrenos.add(Tabuleiro.TERRENO_PLANTACAO);
		repositorioTerrenos.add(Tabuleiro.TERRENO_PLANTACAO);
		repositorioTerrenos.add(Tabuleiro.TERRENO_PLANTACAO);
		repositorioTerrenos.add(Tabuleiro.TERRENO_PLANTACAO);
		// Pasto: 04
		repositorioTerrenos.add(Tabuleiro.TERRENO_PASTO);
		repositorioTerrenos.add(Tabuleiro.TERRENO_PASTO);
		repositorioTerrenos.add(Tabuleiro.TERRENO_PASTO);
		repositorioTerrenos.add(Tabuleiro.TERRENO_PASTO);
		// Argila: 03
		repositorioTerrenos.add(Tabuleiro.TERRENO_ARGILA);
		repositorioTerrenos.add(Tabuleiro.TERRENO_ARGILA);
		repositorioTerrenos.add(Tabuleiro.TERRENO_ARGILA);
		// Minerio: 03
		repositorioTerrenos.add(Tabuleiro.TERRENO_MINERIO);
		repositorioTerrenos.add(Tabuleiro.TERRENO_MINERIO);
		repositorioTerrenos.add(Tabuleiro.TERRENO_MINERIO);
	}

	private void gerarRepositorioNumeros() {
		repositorioNumeros = new ArrayList<Integer>();
		repositorioNumeros.add(2);
		repositorioNumeros.add(3);
		repositorioNumeros.add(3);
		repositorioNumeros.add(4);
		repositorioNumeros.add(4);
		repositorioNumeros.add(5);
		repositorioNumeros.add(5);
		repositorioNumeros.add(6);
		repositorioNumeros.add(6);
		repositorioNumeros.add(8);
		repositorioNumeros.add(8);
		repositorioNumeros.add(9);
		repositorioNumeros.add(9);
		repositorioNumeros.add(10);
		repositorioNumeros.add(10);
		repositorioNumeros.add(11);
		repositorioNumeros.add(11);
		repositorioNumeros.add(12);
	}

	private void gerarRepositorioPortos() {
		repositorioPortos = new ArrayList<Integer>();
		repositorioPortos.add(Tabuleiro.RECURSO_X);
		repositorioPortos.add(Tabuleiro.RECURSO_X);
		repositorioPortos.add(Tabuleiro.RECURSO_X);
		repositorioPortos.add(Tabuleiro.RECURSO_X);
		repositorioPortos.add(Tabuleiro.RECURSO_OVELHA);
		repositorioPortos.add(Tabuleiro.RECURSO_MADEIRA);
		repositorioPortos.add(Tabuleiro.RECURSO_MINERIO);
		repositorioPortos.add(Tabuleiro.RECURSO_TIJOLO);
		repositorioPortos.add(Tabuleiro.RECURSO_TRIGO);
	}

	private void gerarPoolCartas() {
		cartaMaiorEstrada = new CartaMaiorEstrada();
		cartaMaiorExercito = new CartaMaiorExercito();
		
		poolCartasDesenvolvimento = new LinkedList<CartaDesenvolvimento>();
		// 14 Soldado
		for (int i = 0; i < 14; i++) {
			poolCartasDesenvolvimento.add(new CartaDesenvolvimento(CartaDesenvolvimento.CARTA_SOLDADO));
		}
		// 2 Progresso - Ano Fartura
		for (int i = 0; i < 2; i++) {
			poolCartasDesenvolvimento.add(new CartaDesenvolvimento(CartaDesenvolvimento.CARTA_ANO_FARTURA));
		}
		// 2 Progresso - Construção Estrada
		for (int i = 0; i < 2; i++) {
			poolCartasDesenvolvimento.add(new CartaDesenvolvimento(CartaDesenvolvimento.CARTA_CONSTRUCAO_ESTRADA));
		}
		// 2 Progresso - Monopólio
		for (int i = 0; i < 2; i++) {
			poolCartasDesenvolvimento.add(new CartaDesenvolvimento(CartaDesenvolvimento.CARTA_MONOPOLIO));
		}
		// 5 Ponto de Vitória
		for (int i = 0; i < 5; i++) {
			poolCartasDesenvolvimento.add(new CartaDesenvolvimento(CartaDesenvolvimento.CARTA_PONTO_VITORIA));
		}
	}

	public void embaralharTabuleiro() {
		embaralharTerrenos();
		embaralharNumeros();
		embaralharPortos();
	}

	private void embaralharTerrenos() {
		Collections.shuffle(repositorioTerrenos);
		int i = 0;
		for (int camada = 0; camada < terrenos.length; camada++) {
			for (int identificador = 0; identificador < terrenos[camada].length; identificador++) {
				terrenos[camada][identificador].setTipo(repositorioTerrenos.get(i));
				if (terrenos[camada][identificador].getTipo() == Tabuleiro.TERRENO_DESERTO) {
					terrenoLadrao = terrenos[camada][identificador];
				}
				i++;
			}
		}
	}

	private void embaralharNumeros() {
		Collections.shuffle(repositorioNumeros);
		int i = 0;
		for (int camada = 0; camada < terrenos.length; camada++) {
			for (int identificador = 0; identificador < terrenos[camada].length; identificador++) {
				if (terrenos[camada][identificador].getTipo() != Tabuleiro.TERRENO_DESERTO) {
					terrenos[camada][identificador].setNumero(repositorioNumeros.get(i));
					i++;
				} else {
					terrenos[camada][identificador].setNumero(0);
				}
			}
		}
	}

	private void embaralharPortos() {
		Collections.shuffle(repositorioPortos);
		for (int i = 0; i < portos.length; i++) {
			portos[i].setRecurso(repositorioPortos.get(i));
		}
	}

}
