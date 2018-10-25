package br.ufsc.inf;

import br.ufsc.inf.control.Controlador;

public class Main {

	public static void main(String[] args) {
		/*
		 * No construtor, o Controlador instancia os atores e, no construtor do
		 * AtorJogador, a interface grafica eh instanciada, sendo apresentada ao
		 * usuario quando o metodo mostraInterface() do AtorJogador eh chamado
		 */
		new Controlador();
	}

}
