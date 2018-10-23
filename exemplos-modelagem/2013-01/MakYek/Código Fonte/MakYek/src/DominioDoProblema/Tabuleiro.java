package DominioDoProblema;

public class Tabuleiro {

	protected Posicao posicoes[][] = new Posicao[8][8];
	protected boolean jogoEmAndamento = false;
	protected boolean conectado;
	protected Jogador jogador1;
	protected Jogador jogador2;
	protected Lance lance;
	protected boolean primeiroClick = true;

	public boolean isConectado() {
		return this.conectado;
	}

	public void estabeleceConectado(boolean conectado) {
		this.conectado = conectado;
	}

	public boolean isJogoEmAndamento() {
		return this.jogoEmAndamento;
	}

	public void encerrarPartidaAndamento() {
		jogoEmAndamento = false;
	}

	public int tratarLance(int linhaDestino, int colunaDestino,
			Jogador jogadorDaVez) {
		Peca temPeca;
		String corJogVez;
		String corPeca;
		corJogVez = jogadorDaVez.informaCorJogador();

		// verifica captura na VERTICAL
		if (linhaDestino != 7 && linhaDestino!=0) {// limite tabuleiro inferior e superior
			temPeca = this.encontraPosicao(linhaDestino + 1, colunaDestino)
					.getPeca();
			if (temPeca != null) {
				corPeca = this.encontraPosicao(linhaDestino + 1, colunaDestino)
						.informarCorPeca();
				if (corJogVez != corPeca) {
					temPeca = this.encontraPosicao(linhaDestino - 1,
							colunaDestino).getPeca();

					if (temPeca != null) {
						corPeca = this.encontraPosicao(linhaDestino - 1,
								colunaDestino).informarCorPeca();
						if (corJogVez != corPeca) {
							this.encontraPosicao(linhaDestino + 1,
									colunaDestino).retirePeca();
							this.encontraPosicao(linhaDestino - 1,
									colunaDestino).retirePeca();
						}
					} else {
						if (linhaDestino != 6) {
							temPeca = this.encontraPosicao(linhaDestino + 2,
									colunaDestino).getPeca();
							if (temPeca != null) {
								corPeca = this.encontraPosicao(
										linhaDestino + 2, colunaDestino)
										.informarCorPeca();
								if (corJogVez == corPeca)
									this.encontraPosicao(linhaDestino + 1,
											colunaDestino).retirePeca();
							}
						}
					}
				}
			}
		}

		if (linhaDestino != 0 && linhaDestino != 1) {// limite tabuleiro
			// superior
			temPeca = this.encontraPosicao(linhaDestino - 1, colunaDestino)
					.getPeca();
			if (temPeca != null) {
				corPeca = this.encontraPosicao(linhaDestino - 1, colunaDestino)
						.informarCorPeca();
				if (corJogVez != corPeca) {
					temPeca = this.encontraPosicao(linhaDestino - 2,
							colunaDestino).getPeca();
					if (temPeca != null) {
						corPeca = this.encontraPosicao(linhaDestino - 2,
								colunaDestino).informarCorPeca();
						if (corJogVez == corPeca)
							this.encontraPosicao(linhaDestino - 1,
									colunaDestino).retirePeca();
					}
				}
			}
		}

		// verifica captura na HORIZONTAL
		if (colunaDestino != 7 && colunaDestino!=0) {// limite tabuleiro lado direito e esquerdo
			temPeca = this.encontraPosicao(linhaDestino, colunaDestino + 1)
					.getPeca();
			if (temPeca != null) {
				corPeca = this.encontraPosicao(linhaDestino, colunaDestino + 1)
						.informarCorPeca();
				if (corJogVez != corPeca) {
					temPeca = this.encontraPosicao(linhaDestino,
							colunaDestino - 1).getPeca();

					if (temPeca != null) {
						corPeca = this.encontraPosicao(linhaDestino,
								colunaDestino - 1).informarCorPeca();
						if (corJogVez != corPeca) {
							this.encontraPosicao(linhaDestino,
									colunaDestino + 1).retirePeca();
							this.encontraPosicao(linhaDestino,
									colunaDestino - 1).retirePeca();
						}
					} else {
						if (colunaDestino != 6) {
							temPeca = this.encontraPosicao(linhaDestino,
									colunaDestino + 2).getPeca();
							if (temPeca != null) {
								corPeca = this.encontraPosicao(linhaDestino,
										colunaDestino + 2).informarCorPeca();
								if (corJogVez == corPeca)
									this.encontraPosicao(linhaDestino,
											colunaDestino + 1).retirePeca();
							}
						}
					}
				}
			}
		}

		if (colunaDestino != 0 && colunaDestino != 1) {// limite tabuleiro lado
			// esquerdo
			temPeca = this.encontraPosicao(linhaDestino, colunaDestino - 1)
					.getPeca();
			if (temPeca != null) {
				corPeca = this.encontraPosicao(linhaDestino, colunaDestino - 1)
						.informarCorPeca();
				if (corJogVez != corPeca) {
					temPeca = this.encontraPosicao(linhaDestino,
							colunaDestino - 2).getPeca();
					if (temPeca != null) {
						corPeca = this.encontraPosicao(linhaDestino,
								colunaDestino - 2).informarCorPeca();
						if (corJogVez == corPeca)
							this.encontraPosicao(linhaDestino,
									colunaDestino - 1).retirePeca();
					}
				}
			}
		}
		return 11;
	}

	public int selecionaPosicao(int linha, int coluna) {
		int resultado = -1;

		if (primeiroClick) {
			resultado = this.trataPrimeiroClick(linha, coluna);
		} else {
			resultado = this.verificaPossibilidadeMovimento(linha, coluna);
			if (resultado == 9) {
				boolean vez = jogador1.informaDaVez();
				Jogador jogVez;
				if (vez)
					jogVez = jogador1;
				else
					jogVez = jogador2;

				resultado = this.tratarLance(linha, coluna, jogVez);

				resultado = this.verificaTerminoPartida();

				if (resultado == 17)
					this.passaVez();
			}
		}
		return resultado;
	}

	public ImagemTabuleiro informarEstado() {
		ImagemTabuleiro imagemTabuleiro;
		boolean vez;
		String mensagem = "";
		boolean vencedor;
		boolean ocupada;
		Posicao posicao;
		String corPeca;

		imagemTabuleiro = new ImagemTabuleiro();
		jogoEmAndamento = isJogoEmAndamento();

		if (jogoEmAndamento) {
			vez = jogador1.informaDaVez();
			if (vez)
				mensagem = jogador1.informarNome();
			else
				mensagem = jogador2.informarNome();

			imagemTabuleiro.receberMensagem("Jogador da vez:" + mensagem);
		} else {
			vencedor = jogador1.informaVencedor();
			if (vencedor)
				mensagem = "Jogador: " + jogador1.informarNome() + " Venceu";
			else {
				vencedor = jogador2.informaVencedor();
				if (vencedor)
					mensagem = "Jogador:" + jogador2.informarNome() + "Venceu";
				else
					mensagem = "Nao ha mais possibilidade de lance\nEmpate";
			}
			imagemTabuleiro.receberMensagem(mensagem);
		}

		for (int linha = 0; linha < 8; linha++) {
			for (int coluna = 0; coluna < 8; coluna++) {
				posicao = encontraPosicao(linha, coluna);
				ocupada = posicao.temPeca();

				if (ocupada) {
					corPeca = posicao.informarCorPeca();

					if (posicao.getPeca().getSelecionda()) {
						corPeca = corPeca + "Select";
					}
				}
				else
					corPeca = "semCor"; // nao ha peca na posicao

				imagemTabuleiro.defineValor(linha, coluna, corPeca);
			}
		}

		return imagemTabuleiro;
	}

	private Posicao encontraPosicao(int linha, int coluna) {
		return posicoes[linha][coluna];
	}

	public int trataPrimeiroClick(int linha, int coluna) {
		int resultado = 6; // posicao sem peca
		String corJog;
		Posicao posicao = this.encontraPosicao(linha, coluna);
		Peca temPeca = posicao.getPeca();
		if (temPeca != null) {
			String corPeca = posicao.informarCorPeca();

			boolean vez = jogador1.informaDaVez();

			if (vez)
				corJog = jogador1.informaCorJogador();
			else
				corJog = jogador2.informaCorJogador();

			boolean corValida = this.confereIgualdadeCorPeca(corPeca, corJog);

			if (corValida) {
				temPeca.setSelecionda(true);
				lance = new Lance();
				lance.informaPosicaoOrigem(linha, coluna);
				this.modificaPrimeiroClick(false);
				resultado = 8; // peca valida
			} else
				resultado = 7; // peca nao e do jogador

		}
		return resultado;
	}

	public void passaVez() {
		boolean vez = jogador1.informaDaVez();
		if (vez) {
			jogador1.desabilitar();
			jogador2.habilitar();
		} else {
			jogador2.desabilitar();
			jogador1.habilitar();
		}
	}

	public void iniciar() {
		for (int linha = 0; linha < 8; linha++) {
			for (int coluna = 0; coluna < 8; coluna++) {
				posicoes[(linha)][(coluna)] = new Posicao();
			}
		}
	}

	public void criaJogador(String nome, int posicao) {
		if (posicao == 1) {
			jogador1 = new Jogador();
			jogador1.iniciar();
			jogador1.setNome(nome);
		} else {
			jogador2 = new Jogador();
			jogador2.iniciar();
			jogador2.setNome(nome);
		}
	}

	public void modificaPrimeiroClick(boolean primeiroClick) {
		this.primeiroClick = primeiroClick;
	}

	public boolean confereIgualdadeCorPeca(String corPeca, String corJog) {
		if (corPeca.equals(corJog))
			return true;
		else
			return false;
	}

	public int verificaPossibilidadeMovimento(int linhaDestino,
			int colunaDestino) {
		int linhaOrigem;
		int colunaOrigem;
		boolean temPeca = false;
		int resultado = -1;

		linhaOrigem = lance.getLinhaOrigem();
		colunaOrigem = lance.getColunaOrigem();

		if (!(linhaOrigem == linhaDestino && colunaOrigem == colunaDestino)) {
			if (linhaOrigem == linhaDestino && colunaOrigem != colunaDestino) {
				if (colunaOrigem > colunaDestino) {
					while (colunaOrigem > colunaDestino && !temPeca) {
						temPeca = encontraPosicao(linhaOrigem, colunaOrigem - 1)
								.temPeca();
						colunaOrigem--;
					}
				} else {
					while (colunaOrigem < colunaDestino && !temPeca) {
						temPeca = encontraPosicao(linhaOrigem, colunaOrigem + 1)
								.temPeca();
						colunaOrigem++;
					}
				}
			} else if (colunaOrigem == colunaDestino
					&& linhaOrigem != linhaDestino) {
				if (linhaOrigem > linhaDestino) {
					while (linhaOrigem > linhaDestino && !temPeca) {
						temPeca = encontraPosicao(linhaOrigem - 1, colunaOrigem)
								.temPeca();
						linhaOrigem--;
					}
				} else {
					while (linhaOrigem < linhaDestino && !temPeca) {
						temPeca = encontraPosicao(linhaOrigem + 1, colunaOrigem)
								.temPeca();
						linhaOrigem++;
					}
				}
			} else
				temPeca = true; // evita movimento em diagonal ou mesma posicao!

			if (!temPeca) {
				resultado = 9; // nao e usado para notificacao, e sim para ver
				// se continua para tratar lance
				lance.informarPosicaoDestino(linhaDestino, colunaDestino);
				this.movimentaPecas(lance.getLinhaOrigem(),
						lance.getColunaOrigem(), linhaDestino, colunaDestino);
				this.modificaPrimeiroClick(true);
			} else
				resultado = 10;
		} else {
			Posicao posicao = this.encontraPosicao(linhaOrigem, colunaOrigem);
			Peca peca = posicao.getPeca();
			peca.setSelecionda(false);
			this.modificaPrimeiroClick(true);
			resultado = 8; // mesma posicao, deseleciona a peca;
		}
		return resultado;
	}

	public void movimentaPecas(int linhaOrigem, int colunaOrigem,
			int linhaDestino, int colunaDestino) {

		if (jogador1.informaDaVez()) {
			posicoes[linhaOrigem][colunaOrigem].retirePeca();
			posicoes[linhaDestino][colunaDestino].criePeca(true);
		} else if (jogador2.informaDaVez()) {
			posicoes[linhaOrigem][colunaOrigem].retirePeca();
			posicoes[linhaDestino][colunaDestino].criePeca(false);
		}

	}

	public int receberJogada(Lance jogada) {
		Jogador jogVez;
		int colunaDestino = jogada.getColunaDestino();
		int linhaDestino = jogada.getLinhaDestino();
		int linhaOrigem = jogada.getLinhaOrigem();
		int colunaOrigem = jogada.getColunaOrigem();

		this.movimentaPecas(linhaOrigem, colunaOrigem, linhaDestino,
				colunaDestino);

		boolean vez = jogador1.informaDaVez();
		if (vez) {
			jogVez = jogador1;
		} else {
			jogVez = jogador2;
		}

		this.tratarLance(linhaDestino, colunaDestino, jogVez);

		int resultado = this.verificaTerminoPartida();

		if (resultado == 12) {
			resultado--;
		}
		// recebeu a jogada, eh necessario desativar o jog antigo e ativar o novo
		this.passaVez();

		return resultado;
	}

	public void esvaziar() {
		for (int linha = 0; linha < 8; linha++) {
			for (int coluna = 0; coluna < 8; coluna++) {
				posicoes[linha][coluna].retirePeca();
			}
		}
		jogador1 = null;
		jogador2 = null;
		jogoEmAndamento = false;
	}

	public void habilitar() {
		jogoEmAndamento = true;

		jogador1.habilitar();
		jogador1.assumeCor(true);
		jogador2.assumeCor(false);

		posicionaPecas();

	}

	public void posicionaPecas() {
		for (int coluna = 0; coluna < 8; coluna++) {
			posicoes[0][coluna].criePeca(false);
		}
		for (int coluna = 0; coluna < 8; coluna++) {
			posicoes[2][coluna].criePeca(false);
		}
		for (int coluna = 0; coluna < 8; coluna++) {
			posicoes[5][coluna].criePeca(true);
		}
		for (int coluna = 0; coluna < 8; coluna++) {
			posicoes[7][coluna].criePeca(true);
		}
	}

	public int verificaTerminoPartida() {
		int resultado = -1;
		boolean vez;
		Jogador jogadorDaVez;
		Posicao posicaoAtual;
		boolean temPeca;
		int preta = 0;
		int branca = 0;
		String corPeca;
		vez = jogador1.informaDaVez();
		if (vez)
			jogadorDaVez = jogador1;
		else
			jogadorDaVez = jogador2;

		for (int linha = 0; linha < 8; linha++) {
			for (int coluna = 0; coluna < 8; coluna++) {
				posicaoAtual = encontraPosicao(linha, coluna);
				temPeca = posicaoAtual.temPeca();
				if (temPeca) {
					corPeca = posicaoAtual.informarCorPeca();
					if (corPeca.equals("preta"))
						preta++;
					else
						branca++;
				}
			}
		}
		if (branca == 0 || preta == 0) {
			jogadorDaVez.vencedor();
			resultado = 12;
			this.encerrarPartidaAndamento();
		} else if (branca == 1 && preta == 1) {
			resultado = 13;
			this.encerrarPartidaAndamento();
		} else {
			resultado = 17;
		}

		return resultado;
	}

	public Lance informaJogada() {
		return lance;
	}
}
