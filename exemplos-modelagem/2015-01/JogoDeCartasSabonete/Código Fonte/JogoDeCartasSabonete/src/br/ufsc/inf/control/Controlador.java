package br.ufsc.inf.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rede.AtorNetGames;
import br.ufsc.inf.Constantes;
import br.ufsc.inf.model.Carta;
import br.ufsc.inf.model.EstadoMesa;
import br.ufsc.inf.model.Jogador;
import br.ufsc.inf.view.AtorJogador;

public class Controlador {

	protected Jogador jogador;
	protected Jogador adversario;
	protected EstadoMesa estadoMesa;
	protected int ordem;
	protected boolean conectado;
	protected boolean jogoEmAndamento;
	protected AtorJogador atorJogador;
	protected AtorNetGames atorNetGames;

	public Controlador() {
		this.estadoMesa = new EstadoMesa();
		this.conectado = false;
		this.jogoEmAndamento = false;
		this.atorJogador = new AtorJogador(this);
		this.atorNetGames = new AtorNetGames(this);

		this.atorJogador.mostraInterface();
	}

	public int conectar() {
		if (!this.conectado) {
			String servidor = this.atorJogador.obterNomeServidor();
			String nomeJogador = this.atorJogador.obterNomeJogador();
			this.jogador = new Jogador(nomeJogador);
			boolean conectado = this.atorNetGames.conectar(servidor, nomeJogador);
			if (conectado) {
				this.conectado = true;
				return Constantes.CONEXAO_EXITO;
			} else {
				return Constantes.CONEXAO_FALHOU;
			}
		} else {
			return Constantes.JA_CONECTADO;
		}

	}

	public int desconectar() {

		if (this.conectado) {
			boolean exito = this.atorNetGames.desconectar();
			if (exito) {
				this.conectado = false;
				this.jogoEmAndamento = false;
				return Constantes.DESCONEXAO_EXITO;
			} else {
				return Constantes.DESCONEXAO_FALHOU;
			}
		} else {
			return Constantes.JA_DESCONECTADO;
		}
	}

	public int iniciarPartida() {

		if (this.conectado) {
			if (!this.jogoEmAndamento) {
				boolean iniciada = this.atorNetGames.iniciarPartida();
				if (iniciada) {
					return Constantes.SOLICITACAO_INICIO_PARTIDA;
				} else {
					return Constantes.PARTIDA_FALHOU;
				}
			} else {
				return Constantes.PARTIDA_ANDAMENTO;
			}
		} else {
			return Constantes.NAO_CONECTADO;
		}
	}

	public void iniciarNovaPartida(int ordem) {
		String nomeAdversario;
		int ordemAdversario;
		this.limpar();
		this.ordem = ordem;
		this.jogoEmAndamento = true;
		boolean daVez = this.souOPrimeiroJogador();
		this.jogador.setDaVez(daVez);

		if (daVez) {
			this.embaralharMonte();
			this.distribuirCartas();
			this.atorJogador.atualizaEstadoMesa(this.estadoMesa, null);
			ordemAdversario = Constantes.ORDEM_SEGUNDO_JOGADOR;
		} else {
			ordemAdversario = Constantes.ORDEM_PRIMEIRO_JOGADOR;
		}

		nomeAdversario = this.atorNetGames.obterNomeAdversario(ordemAdversario);
		this.adversario = new Jogador(nomeAdversario);
		this.atorJogador.informarNomeAdversario(nomeAdversario);

		this.atorJogador.inicializaMesa();
		this.atorJogador.informarResultado(Constantes.PARTIDA_INICIALIZADA);
	}

	public int verificarJogada(int destinoCarta, Carta cartaMao) {

		int numeroCarta;
		boolean valida;
		Carta cartaPosicao = this.estadoMesa
				.obterCartaMesaJogadorUm(destinoCarta);
		boolean relevada = cartaPosicao.isRevelada();

		if (!relevada) {
			numeroCarta = cartaMao.getNumero();
			valida = this.validaPosicaoComNumeroCarta(destinoCarta,
					numeroCarta);
			if (valida) {
				cartaMao.setRevelada(true);
				this.estadoMesa.inserirCartaMesaJogadorUm(destinoCarta, cartaMao);
				this.jogador.setCartaMao(cartaPosicao);
				return Constantes.JOGADA_OK_TURNO_CONTINUA;
			}
		}

		return Constantes.JOGADA_INVALIDA_TURNO_CONTINUA;
	}

	public int verificarEstadoPartida() {
		List<Carta> mesaJogador = this.estadoMesa.getMesaJogadorUm();
		Carta carta;
		int numeroCarta = 0;
		boolean revelada = false;
		boolean valida = false;

		for (int i = 0; i < mesaJogador.size(); i++) {
			carta = mesaJogador.get(i);
			revelada = carta.isRevelada();
			if (revelada) {
				numeroCarta = carta.getNumero();

				valida = this.validaPosicaoComNumeroCarta(i + 1,
						numeroCarta);
				if (!valida) {
					return Constantes.JOGADA_OK_TURNO_ENCERRADO;
				}
			} else {
				return Constantes.JOGADA_OK_TURNO_ENCERRADO;
			}
		}
		return Constantes.JOGADA_OK_VENCEU_PARTIDA;
	}

	public boolean validaPosicaoComNumeroCarta(int posicao, int numeroCarta) {
		return posicao == numeroCarta;
	}

	public void receberJogada(EstadoMesa estadoMesaAdversario) {
		this.estadoMesa
				.setMesaJogadorDois(estadoMesaAdversario.getMesaJogadorUm());
		this.estadoMesa
				.setMesaJogadorUm(estadoMesaAdversario.getMesaJogadorDois());
		this.estadoMesa.setMonte(estadoMesaAdversario.getMonte());
		this.estadoMesa.setLixo(estadoMesaAdversario.getLixo());
		this.estadoMesa.setTemVencedor(estadoMesaAdversario.isTemVencedor());

		if (estadoMesaAdversario.isTemVencedor()) {
			this.jogoEmAndamento = false;
			this.jogador.setDaVez(false);
			this.atorJogador
					.informarResultado(Constantes.ADVERSARIO_VENCEU_PARTIDA);
		} else {
			this.jogador.setDaVez(true);
		}

		this.atorJogador.atualizaEstadoMesa(this.estadoMesa, null);
	}

	public void enviarJogada() {
		this.jogador.setDaVez(false);
		this.atorNetGames.enviarJogada(this.estadoMesa);
	}

	public void limpar() {
		this.adversario = null;
		this.jogoEmAndamento = false;
		this.estadoMesa = new EstadoMesa();
	}

	public void distribuirCartas() {
		List<Carta> monte = this.estadoMesa.getMonte();

		List<Carta> mesaJogadorUm = new ArrayList<>(monte.subList(0, 13));
		List<Carta> mesaJogadorDois = new ArrayList<>(monte.subList(14, 27));
		monte = monte.subList(28, monte.size() - 1);

		this.estadoMesa.setMesaJogadorUm(mesaJogadorUm);
		this.estadoMesa.setMesaJogadorDois(mesaJogadorDois);
		this.estadoMesa.setMonte(monte);
	}

	public void embaralharMonte() {
		String[] naipes = { "A", "B", "C", "D" };
		List<Carta> monte = new ArrayList<>();

		for (int i = 1; i < 14; i++) {
			for (int j = 0; j < 4; j++) {
				Carta carta = new Carta(i, naipes[j]);
				monte.add(carta);
			}
		}

		Collections.shuffle(monte);
		this.estadoMesa.setMonte(monte);
	}

	public void selecionouOrigemCartaMao(int origemCarta) {
		boolean minhaVez;
		Carta carta = null;
		if (this.jogoEmAndamento) {
			minhaVez = this.jogador.isDaVez();
			if (minhaVez) {
				if (origemCarta == Constantes.ORIGEM_DESTINO_LIXO) {
					carta = this.estadoMesa.retiraCartaLixo();
				} else if (origemCarta == Constantes.ORIGEM_CARTA_MONTE) {
					carta = this.estadoMesa.retiraCartaMonte();
					List<Carta> monte = this.estadoMesa.getMonte();
					
					if (monte.isEmpty()) {
						List<Carta> cartasLixo = this.estadoMesa.getLixo();
						this.estadoMesa.setLixo(new ArrayList<Carta>());
						Collections.shuffle(cartasLixo);
						this.estadoMesa.setMonte(cartasLixo);
					}
				}

				this.jogador.setCartaMao(carta);
				this.atorJogador.atualizaFluxoJogada();
				this.atorJogador.atualizaEstadoMesa(this.estadoMesa, carta);
			}
		}
	}

	public void selecionouDestinoCartaMao(int destinoCarta) {
		Carta cartaMao = this.jogador.getCartaMao();
		int resultado = -1;
		if (cartaMao != null) {
			if (destinoCarta == Constantes.ORIGEM_DESTINO_LIXO) {
				this.estadoMesa.adicionaCartaLixo(cartaMao);
				this.jogador.setCartaMao(null);
				resultado = this.verificarEstadoPartida();

			} else if (destinoCarta >= Constantes.DESTINO_CARTA_UM_MESA
					&& destinoCarta <= Constantes.DESTINO_CARTA_TREZE_MESA) {
				resultado = this.verificarJogada(destinoCarta, cartaMao);
			}
		} else {
			resultado = Constantes.JOGADA_INVALIDA_TURNO_CONTINUA;
		}

		if (resultado == Constantes.JOGADA_OK_TURNO_CONTINUA
				|| resultado == Constantes.JOGADA_OK_TURNO_ENCERRADO) {

			if (resultado == Constantes.JOGADA_OK_TURNO_ENCERRADO) {
				this.enviarJogada();
				this.atorJogador.atualizaFluxoJogada();
			}

			this.atorJogador.atualizaEstadoMesa(this.estadoMesa,
					this.jogador.getCartaMao());
		} else if (resultado == Constantes.JOGADA_OK_VENCEU_PARTIDA) {
			this.jogoEmAndamento = false;
			this.estadoMesa.setTemVencedor(true);
			this.enviarJogada();
			this.atorJogador.atualizaEstadoMesa(this.estadoMesa,
					this.jogador.getCartaMao());
		}

		this.atorJogador.informarResultado(resultado);
	}

	public boolean souOPrimeiroJogador() {
		return this.ordem == Constantes.ORDEM_PRIMEIRO_JOGADOR;
	}
	
	public boolean minhaVez() {
		return this.jogador.isDaVez();
	}

}