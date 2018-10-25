package br.ufsc.inf.view;

import br.ufsc.inf.control.Controlador;
import br.ufsc.inf.model.Carta;
import br.ufsc.inf.model.EstadoMesa;

public class AtorJogador {

	protected Controlador controlador;
	protected InterfaceMesa interfaceMesa;

	public AtorJogador(Controlador controlador) {
		this.interfaceMesa = new InterfaceMesa(this);
		this.controlador = controlador;
	}

	public void conectar() {
		int resultado = this.controlador.conectar();
		this.informarResultado(resultado);
	}

	public void desconectar() {
		int resultado = this.controlador.desconectar();
		this.informarResultado(resultado);
	}

	public void iniciarPartida() {
		int resultado = this.controlador.iniciarPartida();
		this.informarResultado(resultado);
	}

	public String obterNomeJogador() {
		return this.interfaceMesa.obterJogador();
	}

	public String obterNomeServidor() {
		return this.interfaceMesa.obterServidor();
	}

	public void clickCartaMao(int origemCarta) {
		this.controlador.selecionouOrigemCartaMao(origemCarta);
	}

	public void clickCartaDestino(int destinoCarta) {
		this.controlador.selecionouDestinoCartaMao(destinoCarta);
	}

	public void inicializaMesa() {
		this.interfaceMesa.inicializaMesa();
	}

	public void atualizaEstadoMesa(EstadoMesa estadoMesa, Carta cartaMaoJogador) {
		this.interfaceMesa.atualizaInterface(estadoMesa, cartaMaoJogador);
	}

	public void informarResultado(int resultado) {
		this.interfaceMesa.informarResultado(resultado);
	}

	public void mostraInterface() {
		this.interfaceMesa.setVisible(true);
	}

	public boolean souOPrimeiroJogador() {
		return this.controlador.souOPrimeiroJogador();
	}

	public void atualizaFluxoJogada() {
		this.interfaceMesa.atualizaFluxoJogada();

	}

	public boolean minhaVez() {
		return this.controlador.minhaVez();
	}

	public void informarNomeAdversario(String nomeAdversario) {
		this.interfaceMesa.atualizarNomeAdversario(nomeAdversario);
	}

}