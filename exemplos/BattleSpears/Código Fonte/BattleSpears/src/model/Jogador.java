package model;

import java.util.Random;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Jogador implements Jogada {

	protected String nome;
	protected boolean jogadorDaVez= false;
	protected boolean vencedor;
	protected int numeroDeVitorias;
	protected int[] posicoes = new int[3];
	protected int posicaoAtual;
	protected int codigo;

	public Jogador(String nome, int posicao){
		this.nome = nome;
		this.numeroDeVitorias = 0;	
		this.codigo = posicao;
		criarPosicoes();
		obterPosicaoInicial();
		if (posicao == 1 ){
			this.jogadorDaVez = true;
		}
	}
	
	
	public void criarPosicoes(){
		if(codigo ==1){
			posicoes[0]= 0;
			posicoes[1]= 1;
			posicoes[2]= 2;
		}else{
			posicoes[0]= 3;
			posicoes[1]= 4;
			posicoes[2]= 5;
		}
	}
	
	public void obterPosicaoInicial(){
		Random random = new Random();
		posicaoAtual = posicoes[random.nextInt(3)];
		System.out.println("minha posicao é " + posicaoAtual);
	}
	
	public void notificarAtaque(){
		
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public boolean isJogadorDaVez() {
		return jogadorDaVez;
	}


	public void setJogadorDaVez(boolean jogadorDaVez) {
		this.jogadorDaVez = jogadorDaVez;
	}


	public boolean isVencedor() {
		return vencedor;
	}


	public void setVencedor(boolean vencedor) {
		this.vencedor = vencedor;
	}


	public int getNumeroDeVitorias() {
		return numeroDeVitorias;
	}


	public void setNumeroDeVitorias(int numeroDeVitorias) {
		this.numeroDeVitorias = numeroDeVitorias;
	}


	public int[] getPosicoes() {
		return posicoes;
	}


	public void setPosicoes(int[] posicoes) {
		this.posicoes = posicoes;
	}


	public int getPosicaoAtual() {
		return posicaoAtual;
	}


	public void setPosicaoAtual(int posicaoAtual) {
		this.posicaoAtual = posicaoAtual;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
	
	
}

