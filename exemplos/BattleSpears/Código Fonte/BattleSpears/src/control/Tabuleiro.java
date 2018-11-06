package control;

import java.util.*;

import br.ufsc.inf.leobr.cliente.Jogada;
import model.Jogador;
import view.AtorJogador;

public class Tabuleiro implements Jogada {

	protected Jogador jogador1;
	protected Jogador jogador2;
	protected boolean partidaEmAndamento;
	protected int ultimaPosClicJogador1;
	protected int ultimoMovimento;
	protected AtorJogador jogador;
	protected boolean reiniciado;

	public Tabuleiro() {
			
	}
	
	public void criarJogadores(String idJogador, String adversario, int posicao) {
		jogador1 = new Jogador(idJogador, posicao);
		jogador2 = new Jogador(adversario, posicao == 1 ? 2 : 1);

	}

	public boolean informaSePartidaEmAndamento() {
		return this.partidaEmAndamento;
	}

	
	public void realizaJogada(int tipoMovimento, int posicaoClicada) throws Exception{
		verificaMovimentoPosicao(tipoMovimento, posicaoClicada);
		
		calcularDistancia(posicaoClicada, jogador1.getPosicaoAtual(), tipoMovimento);
		if(tipoMovimento == 2){
			this.jogador1.setPosicaoAtual(posicaoClicada);
		}
		if(posicaoClicada == jogador2.getPosicaoAtual()){
			jogador1.setVencedor(true);
			jogador1.setNumeroDeVitorias(jogador1.getNumeroDeVitorias()+1);
		}
		jogador1.setJogadorDaVez(false);
		
	}
	
	public void verificaMovimentoPosicao(int tipoMovimento, int posicaoClicada)throws Exception{
		if(tipoMovimento == 0){
			throw new Exception("Você não selecionou nenhum tipo de movimento");
		}

		int [] posicoes = jogador1.getPosicoes();
		for (int i= 0; i < posicoes.length; i++){
			if(posicaoClicada == posicoes[i] && tipoMovimento == 1){
				throw new Exception("Você nâo pode atacar uma posição do seu lado");				
			}	
		}
		int [] posicoes2 = jogador2.getPosicoes();
		for(int i=0; i < posicoes2.length; i ++){
			if(posicaoClicada == posicoes2[i] && tipoMovimento == 2){
				throw new Exception("Você nâo pode se mover para o lado do seu adversario");				
			}
		}
	}

	
	public void calcularDistancia(int posicaoClicada, int posicaoAtual, int tipoMovimento) throws Exception{
		
		if(tipoMovimento == 1){
			if(Math.abs(posicaoClicada-posicaoAtual) == 1 || Math.abs(posicaoClicada-posicaoAtual) == 5){
				throw new Exception("Não pode selecionar essa casa ,está muito longe.");
			}
		}
		if(tipoMovimento == 2){
			if(Math.abs(posicaoClicada-posicaoAtual) != 1){
				throw new Exception("Você não pode pular posições. Escolha uma posição mais próxima.");
			}
		}
		this.ultimaPosClicJogador1 = posicaoClicada;
		this.ultimoMovimento = tipoMovimento;
	}

	public Jogador getJogador1() {
		return jogador1;
	}

	public void setJogador1(Jogador jogador1) {
		this.jogador1 = jogador1;
	}

	public Jogador getJogador2() {
		return jogador2;
	}

	public void setJogador2(Jogador jogador2) {
		this.jogador2 = jogador2;
	}

	public boolean isPartidaEmAndamento() {
		return partidaEmAndamento;
	}

	public void setPartidaEmAndamento(boolean partidaEmAndamento) {
		this.partidaEmAndamento = partidaEmAndamento;
	}


	public int getUltimaPosClicJogador1() {
		return ultimaPosClicJogador1;
	}

	public void setUltimaPosClicJogador1(int ultimaPosClicJogador1) {
		this.ultimaPosClicJogador1 = ultimaPosClicJogador1;
	}


	public int getUltimoMovimento() {
		return ultimoMovimento;
	}

	public void setUltimoMovimento(int ultimoMovimento) {
		this.ultimoMovimento = ultimoMovimento;
	}

	public boolean isReiniciado() {
		return reiniciado;
	}

	public void setReiniciado(boolean reiniciado) {
		this.reiniciado = reiniciado;
	}
	
	

	

}