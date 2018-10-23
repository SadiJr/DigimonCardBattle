package logica;

public class Roleta {

	protected Posicao[] posicoes;

	protected boolean partidaEmAndamento;

	protected Jogador jogador;

	protected boolean trancada;

	public void iniciar() {
		trancada = true;
		partidaEmAndamento = false;
		posicoes = new Posicao[49];

		for(int indice = 0; indice <= 36; indice++) {
			posicoes[indice] = new Posicao();
			int[] vencem = {indice};
			posicoes[indice].iniciar(vencem, 36);
		}

		posicoes[37] = new Posicao();
		int[] vencem1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		posicoes[37].iniciar(vencem1, 3);

		posicoes[38] = new Posicao();
		int[] vencem2 = {13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
		posicoes[38].iniciar(vencem2, 3);

		posicoes[39] = new Posicao();
		int[] vencem3 = {25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36};
		posicoes[39].iniciar(vencem3, 3);

		posicoes[46] = new Posicao();
		int[] vencem4 = {1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34};
		posicoes[46].iniciar(vencem4, 3);

		posicoes[47] = new Posicao();
		int[] vencem5 = {2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35};
		posicoes[47].iniciar(vencem5, 3);

		posicoes[48] = new Posicao();
		int[] vencem6 = {3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36};
		posicoes[48].iniciar(vencem6, 3);

		posicoes[40] = new Posicao();
		int[] vencem7 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
		posicoes[40].iniciar(vencem7, 2);

		posicoes[41] = new Posicao();
		int[] vencem8 = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36};
		posicoes[41].iniciar(vencem8, 2);

		posicoes[42] = new Posicao();
		int[] vencem9 = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
		posicoes[42].iniciar(vencem9, 2);

		posicoes[43] = new Posicao();
		int[] vencem10 = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
		posicoes[43].iniciar(vencem10, 2);

		posicoes[44] = new Posicao();
		int[] vencem11 = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35};
		posicoes[44].iniciar(vencem11, 2);

		posicoes[45] = new Posicao();
		int[] vencem12 = {19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36};
		posicoes[45].iniciar(vencem12, 3);
	}

	public boolean informaEmAndamento() {
		return partidaEmAndamento;
	}

	public void limpar(boolean zeraDinheiro) {
		System.out.println("dinheiro antes: "+jogador.atualizaDinheiro(0));
		for(int indice = 0; indice <= 48; indice++) {
			Posicao posicaoSelecionada = obterPosicao(indice);
			int dinheiro = posicaoSelecionada.atualizaAposta(0);
			jogador.atualizaDinheiro(dinheiro);
			posicaoSelecionada.zerar();
		}
		System.out.println("dinheiro depois: "+jogador.atualizaDinheiro(0));

		if(zeraDinheiro) {
			jogador.zerar();
		}
	}

	public void criarJogador() {
		partidaEmAndamento = true;
		destrancarApostas();

		jogador = new Jogador();
		jogador.atualizaDinheiro(5000);
	}

	public AtualizaGUI clique(int posicao, int valor) {
		Posicao posicaoSelecionada = obterPosicao(posicao);
		boolean disponivel = jogador.verificarDisponivel(valor);

		if(disponivel) {
			int aposta = posicaoSelecionada.atualizaAposta(valor);
			int dinheiro = jogador.atualizaDinheiro(-valor);
			AtualizaGUI atualiza = new AtualizaGUI();
			atualiza.iniciar(dinheiro, aposta, -1, -1);
			return atualiza;
		}

		return null;
	}

	public int getSaldoAtual() {
		return jogador.atualizaDinheiro(0);
	}

	public Posicao obterPosicao(int indice) {
		return posicoes[indice];
	}

	public boolean informarTrancada() {
		return trancada;
	}

	public AtualizaGUI rodarRoleta() {
		trancarApostas();
		int numero = sortearNumero();
		int soma = somarGanhos(numero);
		int dinheiro = jogador.atualizaDinheiro(soma);
		if(dinheiro == 0) partidaEmAndamento = false;
		AtualizaGUI atualiza = new AtualizaGUI();
		atualiza.iniciar(dinheiro, -1, soma, numero);
		return atualiza;
	}

	public int sortearNumero() {
		return (int)Math.round((Math.random()*1000)%37);
	}

	public void trancarApostas() {
		trancada = true;
	}

	public int somarGanhos(int numeroSorteado) {
		int soma = 0;

		for(int indice = 0; indice <= 48; indice++) {
			Posicao posicaoAtual = obterPosicao(indice);
			soma += posicaoAtual.obterGanho(numeroSorteado);
			posicaoAtual.zerar();
		}

		return soma;
	}

	public void destrancarApostas() {
		trancada = false;
	}

}

