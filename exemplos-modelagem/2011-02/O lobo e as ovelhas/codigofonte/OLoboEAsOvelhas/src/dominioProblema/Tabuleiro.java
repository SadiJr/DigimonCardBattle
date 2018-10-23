package dominioProblema;

public class Tabuleiro {

	protected Posicao posicoes[][] = new Posicao[7][7];

	protected Jogador jogadorLocal;

	protected Jogador jogadorRemoto;

	protected int linhaSelecionada;
	protected int colunaSelecionada;

	protected boolean partidaEmAndamento;


	public Tabuleiro() {
		linhaSelecionada = -1;
		colunaSelecionada = -1;

	}

	public boolean informarPartidaEmAndamento() {
		return partidaEmAndamento;
	}

	public void esvaziar() {
		for (int linha = 0; linha < 7; linha++) {
			for (int coluna = 7; coluna < 7; coluna++) {
				posicoes[linha][coluna].esvaziar();
			}
			;
		}
		;
	}

	public void criarJogador(String nome, int tipo) {
		if ((jogadorLocal != null) && (jogadorRemoto != null)) {
			jogadorRemoto = null;
			jogadorLocal = new Jogador();
			jogadorLocal.iniciar(nome, tipo);
		} else {
			if (jogadorLocal == null) {
				jogadorLocal = new Jogador();
				jogadorLocal.iniciar(nome, tipo);
			} else {
				jogadorRemoto = new Jogador();
				jogadorRemoto.iniciar(nome, tipo);
			}
			;
		}
		;
	}
	
	public boolean informarExistenciaPosSelecionada(){
		boolean retorno = true;
		if(linhaSelecionada == -1 && colunaSelecionada == -1)
			retorno = false;
		return retorno;
	}

	public void definirOPrimeiro(int primeiro) {
		this.esvaziar();
		partidaEmAndamento = true;
		jogadorLocal.reiniciar();
		jogadorRemoto.reiniciar();
		if (primeiro == 1) {
			jogadorLocal.habilitar();
		} else {
			jogadorRemoto.habilitar();
		}
	}

	public void iniciar() {
		linhaSelecionada = -1;
		colunaSelecionada = -1;
		for (int linha = 0; linha < 7; linha++) {
			for (int coluna = 0; coluna < 7; coluna++) {
				posicoes[linha][coluna] = new Posicao();
				posicoes[linha][coluna].esvaziar();

				if ((linha == 0)
						&& ((coluna == 0) || (coluna == 1) || (coluna == 5) || (coluna == 6))) {
					posicoes[linha][coluna] = null;
				}
				if ((linha == 1)
						&& ((coluna == 0) || (coluna == 1) || (coluna == 5) || (coluna == 6))) {
					posicoes[linha][coluna] = null;
				}
				if ((linha == 5)
						&& ((coluna == 0) || (coluna == 1) || (coluna == 5) || (coluna == 6))) {
					posicoes[linha][coluna] = null;
				}
				if ((linha == 6)
						&& ((coluna == 0) || (coluna == 1) || (coluna == 5) || (coluna == 6))) {
					posicoes[linha][coluna] = null;
				}

				if ((linha == 5)
						&& ((coluna == 2) || (coluna == 3) || (coluna == 4))) {
					if (jogadorLocal.informarTipo() == 1)
						posicoes[linha][coluna].definirOcupante(jogadorRemoto);
					else
						posicoes[linha][coluna].definirOcupante(jogadorLocal);
				}
				if ((linha == 6)
						&& ((coluna == 2) || (coluna == 3) || (coluna == 4))) {
					if (jogadorLocal.informarTipo() == 1)
						posicoes[linha][coluna].definirOcupante(jogadorRemoto);
					else
						posicoes[linha][coluna].definirOcupante(jogadorLocal);
				}
				if (linha == 4) {
					if (jogadorLocal.informarTipo() == 1)
						posicoes[linha][coluna].definirOcupante(jogadorRemoto);
					else
						posicoes[linha][coluna].definirOcupante(jogadorLocal);
				}
				if (linha == 1 && coluna == 3) {
					if (jogadorLocal.informarTipo() == 1)
						posicoes[linha][coluna].definirOcupante(jogadorLocal);
					else
						posicoes[linha][coluna].definirOcupante(jogadorRemoto);
				}
			}
		}
	}

	public ImagemDeTabuleiro informarEstado() {

		ImagemDeTabuleiro estado;
		int valor = 0;
		Jogador vencedor = null;
		estado = new ImagemDeTabuleiro();

		if (this.informarPartidaEmAndamento()) {
			if (jogadorLocal.informarDaVez()) {
				estado.assumirMensagem("Jogador: "
						+ jogadorLocal.informarNome());
			} else {
				estado.assumirMensagem("Jogador: "
						+ jogadorRemoto.informarNome());
			}
		} else {
			vencedor = this.informarVencedor();
			estado.assumirMensagem("Vencedor: " + vencedor.informarNome());
		}

		for (int linha = 0; linha < 7; linha++) {
			for (int coluna = 0; coluna < 7; coluna++) {
				if (this.recuperarPosicao(linha, coluna) != null) {
					if ((this.recuperarPosicao(linha, coluna)).ocupada()) {
						valor = ((this.recuperarPosicao(linha, coluna))
								.informarOcupante()).informarTipo();
						if (linha == linhaSelecionada
								&& coluna == colunaSelecionada) {
							estado.assumirValor(linha, coluna, valor + 2);
						} else {
							estado.assumirValor(linha, coluna, valor);
						}
					} else {
						valor = 0;
						estado.assumirValor(linha, coluna, valor);
					}
				} else {
					estado.assumirValor(linha, coluna, -1);
				}
			}
		}

		return (estado);
	}

	public Posicao recuperarPosicao(int linha, int coluna) {
		if (linha < 0 || linha > 6 || coluna < 0 || coluna > 6)
			return null;
		return (posicoes[linha][coluna]);
	}

	private Jogador informarVencedor() {
		if (jogadorLocal.informarVencedor()) {
			return jogadorLocal;
		} else {
			if (jogadorRemoto.informarVencedor()) {
				return jogadorRemoto;
			} else {
				return null;
			}
		}
	}
	
	public int selecionaPeca(int linha, int coluna, boolean remetenteServidor) {
		int retorno = 0;
		if (remetenteServidor
				|| posicoes[linha][coluna].informarOcupante() == jogadorLocal) {
			assumirLinhaColunaSelecionada(linha,coluna);
		} else {
			retorno = 1;
			desselecionarPos();
		}
		return retorno;
	}
	
	private void assumirLinhaColunaSelecionada(int linha, int coluna){
		linhaSelecionada = linha;
		colunaSelecionada = coluna;
	}

	public int tratarLance(int linha, int coluna, boolean remetenteServidor) {
		int retorno = 3; // Distancia não possível;
		if (informarExistenciaPosSelecionada()) {
			int distancia = verificaDistancia(linha, coluna);
			if (distancia == 1) {
				mover(linha, coluna);
				alternarVez(verificarVencedor(remetenteServidor),
						remetenteServidor);
				retorno = 0;// jogada OK;
			} else if (distancia == 2) {
				if (remetenteServidor || jogadorLocal.informarTipo() == 1) {
					int[] pos = verificaExistenciaPecaIntervalo(linha, coluna);
					if (null != pos) {
						recuperarPosicao(pos[0],pos[1]).esvaziar();
						mover(linha, coluna);
						alternarVez(verificarVencedor(remetenteServidor),
								remetenteServidor);
						retorno = 0;// jogada OK;
					}
				}
			}
		} else {
			retorno = 2; // sem peça selecionada;
		}
		return retorno;
	}

	private void mover(int linha, int coluna) {
		recuperarPosicao(linha,coluna)
				.definirOcupante(recuperarPosicao(linhaSelecionada,colunaSelecionada)
						.informarOcupante());
		recuperarPosicao(linhaSelecionada,colunaSelecionada).definirOcupante(null);
	}

	private void alternarVez(boolean haVencedor, boolean remetenteServidor) {
		if (haVencedor) {
			jogadorLocal.desabilitar();
			jogadorRemoto.desabilitar();
		} else if (remetenteServidor) {
			jogadorLocal.habilitar();
			jogadorRemoto.desabilitar();
		} else {
			jogadorLocal.desabilitar();
			jogadorRemoto.habilitar();
		}
	}

	private int verificaDistancia(int linha, int coluna) {
		int distancia = 0;
		if (linha == linhaSelecionada) {
			if (Math.abs(colunaSelecionada - coluna) == 1) {
				distancia = 1;
			} else if (Math.abs(colunaSelecionada - coluna) == 2) {
				distancia = 2;
			}
		} else {
			if (coluna == colunaSelecionada) {
				if (Math.abs(linhaSelecionada - linha) == 1) {
					distancia = 1;
				} else if (Math.abs(linhaSelecionada - linha) == 2) {
					distancia = 2;
				}
			}
		}
		return distancia;
		// distancia = 0 significa que distancia está além do permitido
	}

	private int[] verificaExistenciaPecaIntervalo(int linha, int coluna) {
		int[] pos = null;
		if (linha == linhaSelecionada) {
			if (coluna > colunaSelecionada) {
				if (posicoes[linha][coluna - 1].ocupada()) {
					pos = new int[] { linha, coluna - 1 };
				}
			} else if (coluna < colunaSelecionada) {
				if (posicoes[linha][coluna + 1].ocupada()) {
					pos = new int[] { linha, coluna + 1 };
				}
			}
		} else if (coluna == colunaSelecionada) {
			if (linha > linhaSelecionada) {
				if (posicoes[linha - 1][coluna].ocupada()) {
					pos = new int[] { linha - 1, coluna };
				}
			} else if (linha < linhaSelecionada) {
				if (posicoes[linha + 1][coluna].ocupada()) {
					pos = new int[] { linha + 1, coluna };
				}
			}
		}
		return pos;
	}

	private int informarQuantidadeOvelhas() {
		int quantidade = 0;
		Posicao posicaoAux = null;

		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				posicaoAux = recuperarPosicao(x, y);
				if (posicaoAux != null) {
					if (posicaoAux.ocupada()) {
						if (posicaoAux.informarOcupante().informarTipo() == 2) {
							quantidade++;
						}
					}
				}
			}
		}
		return quantidade;
	}

	private boolean verificarVencedor(boolean remetenteServidor) {
		int tipo;
		boolean retorno = false;
		if (remetenteServidor) {
			tipo = jogadorRemoto.informarTipo();
		} else {
			tipo = jogadorLocal.informarTipo();
		}
		if (tipo == 1) {
			if (informarQuantidadeOvelhas() < 4) {
				terminarPartidaEmAndamento();
				retorno = true;
			}
		} else {
			if (!verificaExistenciaMovimentosValidos()) {
				terminarPartidaEmAndamento();
				retorno = true;
			}
		}
		if (retorno) {
			if (remetenteServidor) {
				jogadorRemoto.assumirVencedor();
			} else {
				jogadorLocal.assumirVencedor();
			}
		}
		return retorno;
	}

	private boolean verificaExistenciaMovimentosValidos() {
		Posicao aux;
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				aux = recuperarPosicao(x, y);
				if (!(aux == null)) {
					if (aux.ocupada()) {
						if (posicoes[x][y].informarOcupante().informarTipo() == 1) {
							aux = recuperarPosicao(x - 1, y);
							if (!(aux == null)) {
								if (!aux.ocupada())
									return true;
							}
							aux = recuperarPosicao(x - 2, y);
							if (!(aux == null)) {
								if (!aux.ocupada())
									return true;
							}
							aux = recuperarPosicao(x + 1, y);
							if (!(aux == null)) {
								if (!aux.ocupada())
									return true;
							}
							aux = recuperarPosicao(x + 2, y);
							if (!(aux == null)) {
								if (!aux.ocupada())
									return true;
							}
							aux = recuperarPosicao(x, y - 1);
							if (!(aux == null)) {
								if (!aux.ocupada())
									return true;
							}
							aux = recuperarPosicao(x, y - 2);
							if (!(aux == null)) {
								if (!aux.ocupada())
									return true;
							}
							aux = recuperarPosicao(x, y + 1);
							if (!(aux == null)) {
								if (!aux.ocupada())
									return true;
							}
							aux = recuperarPosicao(x, y + 2);
							if (!(aux == null)) {
								if (!aux.ocupada())
									return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public int informarLinhaSelecionada() {
		return linhaSelecionada;
	}

	public int informarColunaSelecionada() {
		return colunaSelecionada;
	}

	public void desselecionarPos() {
		linhaSelecionada = -1;
		colunaSelecionada = -1;
	}

	public Jogador informarJogadorLocal() {
		return jogadorLocal;
	}

	public Jogador informarJogadorRemoto() {
		return jogadorRemoto;
	}

	public void terminarPartidaEmAndamento() {
		partidaEmAndamento = false;
	}

}
