package settlers.modelo;

import java.util.ArrayList;
import java.util.Stack;

public class Jogador {
	
	private int id;
	private String nome;
	// Guarda o tamanho da maior estrada
	private int maiorEstrada;
	// Recursos conquistados durante partida
	private int recursos[];
	// Pool de peões disponíveis
	private Stack<Estrada> poolEstradas;
	private Stack<Cidade> poolCidades;
	private Stack<Vila> poolVilas;
	// Pilha de peões em jogo
	private Stack<Estrada> estradasEmJogo;
	private Stack<Cidade> cidadesEmJogo;
	private Stack<Vila> vilasEmJogo;
	// Pilha de cartas do jogador
	private Stack<CartaDesenvolvimento> cartasDesenvolvimento;
	private Stack<CartaDesenvolvimento> cartasPontoVitoria;
	private Stack<CartaDesenvolvimento> cartasSoldado;
	// Carta do jogador sendo utilizada
	private CartaDesenvolvimento cartaNaMesa;
	// Cartas especiais
	private CartaMaiorExercito cartaMaiorExercito;
	private CartaMaiorEstrada cartaMaiorEstrada;
	
	public Jogador(int id, String nome) {
		this.id = id;
		this.nome = nome;
		this.recursos = new int[5];
		// Instancia pilha de cartas
		cartasDesenvolvimento = new Stack<CartaDesenvolvimento>();
		cartasPontoVitoria = new Stack<CartaDesenvolvimento>();
		cartasSoldado = new Stack<CartaDesenvolvimento>();
		// Instancia pilha peões em jogo
		estradasEmJogo = new Stack<Estrada>();
		cidadesEmJogo = new Stack<Cidade>();
		vilasEmJogo = new Stack<Vila>();
		// Gera Pool Peões
		gerarPoolEstradas();
		gerarPoolCidades();
		gerarPoolVilas();
	}
	
	public Stack<CartaDesenvolvimento> getCartasDesenvolvimento() {
		return cartasDesenvolvimento;
	}
	
	public void receberCartaDesenvolvimento(CartaDesenvolvimento cartaDesenvolvimento) {
		if (cartaDesenvolvimento.getTipo() == CartaDesenvolvimento.CARTA_PONTO_VITORIA) {
			cartasPontoVitoria.push(cartaDesenvolvimento);
		} else {
			cartasDesenvolvimento.push(cartaDesenvolvimento);
		}
	}
	
	public int getQuantidadeCartas() {
		return cartasDesenvolvimento.size() + cartasPontoVitoria.size();
	}
	
	public int getQuantidadeCartasDisponiveis(int turno) {
		int quantidade = 0;
		for (int i = 0; i < cartasDesenvolvimento.size(); i++) {
			if (cartasDesenvolvimento.get(i).getTurnoRecebimento() < turno) {
				quantidade++;
			}
		}
		return quantidade;
	}

	public int[] getCartas() {
		int cartas[] = new int[5];
		for (int i = 0; i < cartasDesenvolvimento.size(); i++) {
			cartas[cartasDesenvolvimento.get(i).getTipo()]++; 
		}
		for (int i = 0; i < cartasPontoVitoria.size(); i++) {
			cartas[cartasPontoVitoria.get(i).getTipo()]++;
		}
		return cartas;
	}

	public int[] getCartasDisponiveis(int turno) {
		int cartas[] = new int[5];
		for (int i = 0; i < cartasDesenvolvimento.size(); i++) {
			if (cartasDesenvolvimento.get(i).getTipo() == CartaDesenvolvimento.CARTA_CONSTRUCAO_ESTRADA && getQuantidadeEstradas() <= 0) {
				continue;
			}
			if (cartasDesenvolvimento.get(i).getTurnoRecebimento() < turno) {
				cartas[cartasDesenvolvimento.get(i).getTipo()]++; 
			}
		}
		return cartas;
	}
	
	public CartaDesenvolvimento getCarta(int carta) {
		for (int i = 0; i < cartasDesenvolvimento.size(); i++) {
			if (cartasDesenvolvimento.get(i).getTipo() == carta) {
				return cartasDesenvolvimento.remove(i);
			}
		}
		return null;
	}
	
	public void usarCarta(int carta) {
		cartaNaMesa = getCarta(carta);
		if (cartaNaMesa.getTipo() == CartaDesenvolvimento.CARTA_SOLDADO) {
			consumirCartaMesa();
		}
	}
	
	public void consumirCartaMesa() {
		if (cartaNaMesa != null && cartaNaMesa.getTipo() == CartaDesenvolvimento.CARTA_SOLDADO) {
			cartasSoldado.add(cartaNaMesa);
		}
		cartaNaMesa = null;
	}
	
	public int getCartaNaMesa() {
		if (cartaNaMesa == null) {
			return -1;
		} else {
			return cartaNaMesa.getTipo();
		}
	}
	
	public CartaMaiorExercito getCartaMaiorExercito() {
		return cartaMaiorExercito;
	}

	public void setCartaMaiorExercito(CartaMaiorExercito cartaMaiorExercito) {
		this.cartaMaiorExercito = cartaMaiorExercito;
	}

	public CartaMaiorEstrada getCartaMaiorEstrada() {
		return cartaMaiorEstrada;
	}

	public void setCartaMaiorEstrada(CartaMaiorEstrada cartaMaiorEstrada) {
		this.cartaMaiorEstrada = cartaMaiorEstrada;
	}

	public int getMaiorEstrada() {
		return maiorEstrada;
	}

	public void setMaiorEstrada(int maiorEstrada) {
		this.maiorEstrada = maiorEstrada;
	}

	public int getTamanhoExercito() {
		return cartasSoldado.size();
	}

	public int[] getRecursos(){
		return recursos;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPontos() {
		int pontos = vilasEmJogo.size() + (cidadesEmJogo.size() * 2);
		if (cartaMaiorEstrada != null) {
			pontos += 2;
		}
		if (cartaMaiorExercito != null) {
			pontos += 2;
		}
		return pontos;
	}
	
	public int getPontosVitoria() {
		return cartasPontoVitoria.size();
	}
	
	public int getTotalPontos() {
		return getPontos() + getPontosVitoria();
	}
	
	public int getQuantidadeVilasEmJogo() {
		return vilasEmJogo.size();
	}
	
	public int getQuantidadeCidadesEmJogo() {
		return cidadesEmJogo.size();
	}
	
	public int getQuantidadeRecursos(int tipo) {
		return recursos[tipo];
	}
	
	public int getQuantidadeRecursos() {
		int quantidade = 0;
		for (int i = 0; i < 5; i++) {
			quantidade += recursos[i];
		}
		return quantidade;
	}
	
	public int roubarRecursoAleatorio() {
		ArrayList<Integer> recursosDisponiveis = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < recursos[i]; j++) {
				recursosDisponiveis.add(i);
			}
		}
		if (recursosDisponiveis.size() > 0) {
			int recurso = recursosDisponiveis.get((int) (recursosDisponiveis.size() * Math.random()));
			recursos[recurso]--;
			return recurso;
		} else {
			return -1;
	 	}
	}
	
	public void receberRecurso(int tipo) {
		recursos[tipo]++;
	}
	
	public void receberRecursos(int tipo, int valor){
		recursos[tipo] += valor;
	}
	
	public void consumirRecurso(int tipo, int valor) {
		if (recursos[tipo] < valor) {
			throw new RuntimeException("Consumo de recursos inexistentes");
		}
		recursos[tipo] -= valor;
	}
	
	public boolean possuiRecursosEstrada() {
		return recursos[Tabuleiro.RECURSO_TIJOLO] > 0 &&
	       recursos[Tabuleiro.RECURSO_MADEIRA] > 0;
		       
	}
	
	public void consumirRecursosEstrada() {
		recursos[Tabuleiro.RECURSO_TIJOLO]--;
		recursos[Tabuleiro.RECURSO_MADEIRA]--;
	}
	
	public boolean possuiRecursosVila() {
		return recursos[Tabuleiro.RECURSO_TIJOLO] > 0 &&
	       recursos[Tabuleiro.RECURSO_MADEIRA] > 0 &&
	       recursos[Tabuleiro.RECURSO_OVELHA] > 0 &&
	       recursos[Tabuleiro.RECURSO_TRIGO] > 0;
	}
	
	public void consumirRecursosVila() {
		recursos[Tabuleiro.RECURSO_TIJOLO]--;
		recursos[Tabuleiro.RECURSO_MADEIRA]--;
		recursos[Tabuleiro.RECURSO_OVELHA]--;
		recursos[Tabuleiro.RECURSO_TRIGO]--;
	}
	
	public boolean possuiRecursosCidade() {
		return recursos[Tabuleiro.RECURSO_TRIGO] >= 2 &&
	       recursos[Tabuleiro.RECURSO_MINERIO] >= 3;
	}
	
	public void consumirRecursosCidade() {
		recursos[Tabuleiro.RECURSO_TRIGO]--;
	    recursos[Tabuleiro.RECURSO_MINERIO]--;
	}
	
	public boolean possuiRecursoCarta() {
		return recursos[Tabuleiro.RECURSO_OVELHA] > 0 &&
	       recursos[Tabuleiro.RECURSO_TRIGO] > 0 &&
	       recursos[Tabuleiro.RECURSO_MINERIO] > 0;
	}
	
	public void consumirRecursosCarta() {
		recursos[Tabuleiro.RECURSO_OVELHA]--;
	    recursos[Tabuleiro.RECURSO_TRIGO]--;
	    recursos[Tabuleiro.RECURSO_MINERIO]--;	
    }
	
	public Vila getVila() {
		if (poolVilas.size() > 0) {
			vilasEmJogo.push(poolVilas.peek());
			return poolVilas.pop();
		}
		return null;		
	}
	
	public void devolverVila(Vila vila) {
		vila.setPosicaoColonia(null);
		vilasEmJogo.remove(vila);
		poolVilas.push(vila);
	}
	
	public Cidade getCidade() {
		if (poolCidades.size() > 0) {
			cidadesEmJogo.push(poolCidades.peek());
			return poolCidades.pop();
		}
		return null;
	}
	
	public Estrada getEstrada() {
		if (poolEstradas.size() > 0) {
			estradasEmJogo.push(poolEstradas.peek());
			return poolEstradas.pop();
		}
		return null;		
	}
	
	public boolean possuiEstradas() {
		return poolEstradas.size() > 0;
	}
	
	public int getTamanhoMaiorEstrada() {
		int maior = 0;
		for (int i = 0; i < estradasEmJogo.size(); i++) {
			int tamanho = estradasEmJogo.get(i).getTamanhoEstrada();
			if (maior < tamanho) {
				maior = tamanho;
			}
		}
		return maior;
	}
	
	public boolean possuiVilas() {
		return poolVilas.size() > 0;
	}
	
	public boolean possuiCidades() {
		return poolCidades.size() > 0;
	}
	
	public int getQuantidadeEstradas() {
		return poolEstradas.size();
	}
	
	public int getQuantidadeVilas() {
		return poolVilas.size();
	}
	
	public int getQuantidadeCidades() {
		return poolCidades.size();
	}
	
	public ArrayList<Porto> getPortos() {
		ArrayList<Porto> portos = new ArrayList<Porto>();
		for (int i = 0; i < vilasEmJogo.size(); i++) {
			if (vilasEmJogo.get(i).getPosicaoColonia().getPorto() != null) {
				portos.add(vilasEmJogo.get(i).getPosicaoColonia().getPorto());
			}
		}
		for (int i = 0; i < cidadesEmJogo.size(); i++) {
			if (cidadesEmJogo.get(i).getPosicaoColonia().getPorto() != null) {
				portos.add(cidadesEmJogo.get(i).getPosicaoColonia().getPorto());
			}
		}
		return portos;
	}
	
	public int[] getProporcaoOfertaMaritima() {
		int proporcaoOferta[] = new int[5];
		for (int i = 0; i < 5; i++) {
			proporcaoOferta[i] = 4;
		}
		// Reduz proporção se tiver portos
		ArrayList<Porto> portosJogador = getPortos();
		for (int i = 0; i < portosJogador.size(); i++) {
			if (portosJogador.get(i).getRecurso() == Tabuleiro.RECURSO_X) {
				for (int j = 0; j < 5; j++) {
					if (proporcaoOferta[j] > portosJogador.get(i).getProporcao()) {
						proporcaoOferta[j] = portosJogador.get(i).getProporcao();
					}
				}
			} else {
				if (proporcaoOferta[portosJogador.get(i).getRecurso()] > portosJogador.get(i).getProporcao()) {
					proporcaoOferta[portosJogador.get(i).getRecurso()] = portosJogador.get(i).getProporcao();
				}
			}
		}
		return proporcaoOferta;
	}
	
	private void gerarPoolEstradas() {
		poolEstradas = new Stack<Estrada>();
		for (int i = 0; i < 15; i++) {
			poolEstradas.push(new Estrada(this));
		}
	}
	
	private void gerarPoolVilas() {
		poolVilas = new Stack<Vila>();
		for (int i = 0; i < 5; i++) {
			poolVilas.push(new Vila(this));
		}
	}

	private void gerarPoolCidades() {
		poolCidades = new Stack<Cidade>();
		for (int i = 0; i < 4; i++) {
			poolCidades.push(new Cidade(this));
		}
	}
	
}
