package InterfaceGrafica;

import DominioDoProblema.*;
import Rede.AtorNetGames;

public class AtorJogador {

	protected Tabuleiro tab;
	protected AtorNetGames rede;
	protected String idUsuario;
	protected InterfaceIsolation janela;

	public AtorJogador(InterfaceIsolation jan) {
		super();
		rede= new AtorNetGames(this);
		janela=jan;
		tab=new Tabuleiro();
		tab.iniciar();
	}
	
	public InterfaceIsolation informarJanela() {
		return this.janela;
	}

	/**
	 * 
	 * @param posicao
	 */
	public void iniciarNovaPartida(Integer posicao) {
		tab.esvaziar();
		tab.criarJogador(idUsuario);
		String idJogador=rede.informarNomeAdversario(idUsuario);
		tab.criarJogador(idJogador);
		tab.habilitar(posicao);
		janela.exibirEstado();
	}

	public int conectar() {
		boolean conectado=tab.informarConectado();
		if(!conectado) {
			String servidor=janela.obterServidor();
			idUsuario=janela.obterIdJogador();
			boolean exito=rede.conectar(servidor, idUsuario);
			if(exito) {
				tab.estabelecerConectado(true);
				return 0;
			}else {
				return 2;
			}
		}
		return 1;
	}

	public int desconectar() {
		boolean conectado=tab.informarConectado();
		if(conectado) {
			boolean exito=rede.desconectar();
			if(exito) {
				tab.estabelecerConectado(false);
				return 3;
			}else {
				return 5;
			}
			
		}
		return 4;
	}

	public int iniciarPartida() {
		boolean emAndamento=tab.informarEmAndamento();
		boolean interromper=false;
		boolean conectado=false;
		if(emAndamento) {
			interromper=avaliarInterrupcao();
		}else {
			conectado=tab.informarConectado();
		}
		if(interromper||(!emAndamento&&conectado)) {
			rede.iniciarPartida();
			return 6;
		}
		if(!conectado) {
			return 7;
		}
		return 13;
	}

	private boolean avaliarInterrupcao() {
		return true;
	}

	/**
	 * 
	 * @param linha
	 * @param coluna
	 */
	public int click(int linha, int coluna) {
		int resultado=tab.click(linha, coluna);
		if(resultado==9||resultado==10) {
			enviarJogada(linha, coluna);
		}
		return resultado;
	}

	/**
	 * 
	 * @param linha
	 * @param coluna
	 */
	public void enviarJogada(int linha, int coluna) {
		Lance lance=tab.informarJogada(linha, coluna);
		rede.enviarJogada(lance);
	}

	/**
	 * 
	 * @param jogada
	 */
	public void receberJogada(Lance jogada) {
		tab.receberJogada(jogada);
		janela.exibirEstado();
	}

	public ImagemTabuleiro informarEstado() {
		return tab.informarEstado();
	}

}