package br.ufsc.inf.model;

import java.util.ArrayList;
import java.util.List;

import br.ufsc.inf.leobr.cliente.Jogada;

public class EstadoMesa implements Jogada {

	private static final long serialVersionUID = 1L;

	protected List<Carta> mesaJogadorUm;
	protected List<Carta> mesaJogadorDois;
	protected List<Carta> monte;
	protected List<Carta> lixo;
	protected boolean temVencedor;

	public EstadoMesa() {
		this.mesaJogadorUm = new ArrayList<Carta>();
		this.mesaJogadorDois = new ArrayList<Carta>();
		this.monte = new ArrayList<Carta>();
		this.lixo = new ArrayList<Carta>();
		this.temVencedor = false;
	}

	public Carta retiraCartaLixo() {
		return this.lixo.remove(this.lixo.size() - 1);
	}

	public Carta retiraCartaMonte() {
		return this.monte.remove(this.monte.size() - 1);
	}

	/**
	 * 
	 * @param cartaMao
	 */
	public void adicionaCartaLixo(Carta cartaMao) {
		if (cartaMao != null) {
			this.lixo.add(cartaMao);
		}
	}

	public Carta obterCartaMesaJogadorUm(int posicao) {
		return this.mesaJogadorUm.get(posicao - 1);
	}

	public void inserirCartaMesaJogadorUm(int posicao, Carta carta) {
		this.mesaJogadorUm.set(posicao - 1, carta);
	}

	public List<Carta> getMesaJogadorUm() {
		return this.mesaJogadorUm;
	}

	public void setMesaJogadorUm(List<Carta> mesaJogadorUm) {
		this.mesaJogadorUm = new ArrayList<Carta>(mesaJogadorUm);
	}

	public List<Carta> getMesaJogadorDois() {
		return this.mesaJogadorDois;
	}

	public void setMesaJogadorDois(List<Carta> mesaJogadorDois) {
		this.mesaJogadorDois = new ArrayList<Carta>(mesaJogadorDois);
	}
	
	public List<Carta> getMonte() {
		return this.monte;
	}
	
	public void setMonte(List<Carta> monte) {
		this.monte = new ArrayList<Carta>(monte);
	}
	
	public List<Carta> getLixo() {
		return this.lixo;
	}
	
	public void setLixo(List<Carta> lixo) {
		this.lixo = new ArrayList<Carta>(lixo);
	}

	public boolean isTemVencedor() {
		return this.temVencedor;
	}

	public void setTemVencedor(boolean temVencedor) {
		this.temVencedor = temVencedor;
	}

}