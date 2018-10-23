package interfaceGrafica;

import javax.swing.JFrame;
/**
 * 
 * @author Ronaldo Jose Abel
 * @author Victor Hugo Heck
 * @version 1.02
 **/

/**
 * O jogo Blitz 21 foi implementado para disciplina Analise e Projeto de Sistemas - INE 5608
 * do Curso de Sistemas de Informação
 * Sob Orientação do Professor Dr. Ricardo Pereira e Silva
 * O objetivo do jogo foi  aprender a sintaxe de UML e utiliza-lá
 * para especificação e desenvolvimento e planejamento de  projeto de Software  
 * que apresentam um certo grau de Complexidade. 
 * Foi utilizado o projeto UML do Jogo da Velha Versão 17 - implementado pelo professor como base
 */
/**
 * O jogo Blitz 21 é um jogo de cartas composto por um  tabuleiro de 16 posições
 * A dinamica do Jogo consiste em construir um baralho de 52 cartas, onde o número da carta corresponde ao seu valor,
 * excetuando as figuras que valem 10 pontos, uma carta é sorteada e o Jogador indica a posição da Carta no Tabuleiro
 * entao e feita a somatoria da linha e coluna caso a somatoria seja = 21 o jogador ganha 10 pontos, caso ultrapasse 21
 * ou complete a linha ou coluna sem chegar a 21, o jogador perde 10.
 * O Jogador pode jogar: 
 * -Sozinho contra o Programa 
 * -contra outra pessoa no mesmo console;
 * -contra outra pessoa em Rede utiliando o framework desenvolvido  Leonardo de Souza Brasil
 *  
 */
public class blitz21 {

	/**
	 * 
	 * 
	 * @param args
	 * Classe Principal Instancia a objeto Janela tipo Ator Jogador 
	 * Pacote interface Grafica
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AtorJogador janela;
		janela = new AtorJogador();
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);
		}

}
