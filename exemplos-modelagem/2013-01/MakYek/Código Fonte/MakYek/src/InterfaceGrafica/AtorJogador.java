package InterfaceGrafica;

import DominioDoProblema.ImagemTabuleiro;
import DominioDoProblema.Lance;
import DominioDoProblema.Tabuleiro;
import NetGames.AtorNetGames;

public class AtorJogador {
	protected Tabuleiro tab;
	protected String nome;
	protected AtorNetGames rede;
	protected InterfaceMakYek janela;

	public AtorJogador(InterfaceMakYek janela) {
		super();
		rede = new AtorNetGames(this);
		tab = new Tabuleiro();
		tab.iniciar();
		this.janela = janela;
	}

	public int conectar() {
		int resultado = -1;
		boolean conectado = tab.isConectado();
		if (!conectado) {
			String servidor = this.solicitaDadosConexao();
			/*
			 * resultado: 0 - ja conectado 1 - falha conexao 2 - Exito conexao
			 */
			resultado = rede.conectar(servidor, nome);
			if (resultado == 2) {
				conectado = true;
				tab.estabeleceConectado(conectado);
				janela.desejaIniciarPartida();
			}
		} else
			resultado = 0;

		return resultado;
	}

	public String solicitaDadosConexao() {
		nome = janela.solicitaIdJogador();
		String servidor = janela.solicitaIdServidor();

		return servidor;
	}

	public int iniciarPartida() {
		boolean interromper = false;
		boolean conectado = false;
		int resultado = -1;

		boolean andamento = tab.isJogoEmAndamento();
		if (andamento)
			interromper = avaliaInterrupcao(); // ainda nao foi implementado,
		// esta apenas retornando true
		else
			conectado = tab.isConectado();

		if (interromper || (!andamento && conectado)) 
			rede.iniciarPartida();
		else
			resultado = 4;
		/*
		 * resultado: 4 - nao esta conectado 
		 * se for exito vai aparecer as pecas no tabuleiro
		 */
		return resultado;
	}

	public boolean avaliaInterrupcao() {
		return true;
	}

	public int desconectar() {
		/*
		 * resultado: 14 - desconexao com exito 15 - desconexao sem
		 * conexao estabelecida 16 - desconexao falhou
		 */
		Boolean andamento = tab.isJogoEmAndamento();
		Boolean conectado = false;
		if (andamento) {
			tab.encerrarPartidaAndamento();
		} else {
			conectado = tab.isConectado();
		}

		Boolean exito = false;

		if (andamento || conectado) {
			exito = rede.desconectar();
			if (exito) {
				tab.estabeleceConectado(!exito);
			} else {
				return 16;
			}
		} else {
			return 15;
		}

		return 14;

	}

	public int selecionaPosicao(int linha, int coluna) {
		/*
		 * resultado: 17 - recebeu a vez 18 - nao eh a vez do jogador 19 - passou
		 * a vez 20 - partida nao iniciada
		 */
		int resultado = 20; // partida nao iniciada
		boolean jogoEmAndamento = tab.isJogoEmAndamento();
		if (jogoEmAndamento) {
			boolean ehMinhaVez = rede.ehMinhaVez();
			if (ehMinhaVez) {
				resultado = tab.selecionaPosicao(linha, coluna);
				if (resultado == 17 || resultado == 12 || resultado == 13) {
					rede.enviaJogada(tab.informaJogada());
				}
				if (resultado == 17)
					resultado = 19; // informa passou a vez
			} else
				resultado = 18; // nao eh vez do jogador
		}
		return resultado;
	}

	public void receberJogada(Lance jogada) {
		int resultado = tab.receberJogada(jogada);
		janela.exibirEstado();
		janela.notifica(resultado);
	}

	public void tratarIniciarPartida(int posicao) {
		tab.esvaziar();
		tab.criaJogador(nome, posicao);
		String nomeOutro = rede.informarNomeAdversario(nome);
		int posicaoOutro = 1;
		if (posicao == 1)
			posicaoOutro = 2;
		tab.criaJogador(nomeOutro, posicaoOutro);
		tab.habilitar();
		janela.exibirEstado();
	}

	public ImagemTabuleiro informarEstado() {
		ImagemTabuleiro estado = tab.informarEstado();
		return estado;
	}

	public int cancelaInicioPartida() {
		/*
		 * resultado: 3 - cancelado iniciar partida
		 */
		return 3;
	}

	public InterfaceMakYek informarJanela() {
		return janela;
	}

	public void encerraPartidaPorFalha() {
		tab.encerrarPartidaAndamento();

	}
}
