package gui;

import javax.swing.JOptionPane;

import logica.AtualizaGUI;
import logica.Roleta;

public class AtorApostador {
	protected Roleta roleta;
	
	public AtorApostador() {
		new GUIPrincipal(this);
	}
	
	public void iniciarPartida() {
		if(roleta == null){
			roleta = new Roleta();
			roleta.iniciar();
		}
		
		boolean andamento = roleta.informaEmAndamento();
		boolean autoriza = false;
		
		if(andamento) {
			autoriza = avaliarInterrupcao(false);
		}
		
		if(autoriza) {
			roleta.limpar(true);
		}
		
		if(!andamento || autoriza) {
			roleta.criarJogador();
		}
	}
	
	public AtualizaGUI clique(int posicao, int valor) {
		boolean trancada = roleta.informarTrancada();
		AtualizaGUI resultado = null;
		if(trancada) {
			mostrarMsgApostasTrancadas();
			return null;
		} else {
			resultado = roleta.clique(posicao, valor);
			
			if(resultado == null) {
				mostrarMsgValorIndisponivel();
			}
		}
		
		return resultado;
	}
	
	public AtualizaGUI rodarRoleta() {
		AtualizaGUI atualiza = roleta.rodarRoleta();
		roleta.destrancarApostas();
		return atualiza;
	}
	
	public boolean avaliarInterrupcao(boolean zerarDinheiro) {
		String pergunta = "Deseja interromper partida em andamento?";
		if(!zerarDinheiro) {
			pergunta = "Deseja limpar as apostas atuais?";
		}
		boolean afirmativo = false;
		boolean condRepetir = true;
		int resposta;
		while (condRepetir) {
			resposta = JOptionPane.showConfirmDialog(null, pergunta);
			afirmativo = resposta == JOptionPane.YES_OPTION;
			condRepetir = resposta == JOptionPane.CANCEL_OPTION;
		}
		return afirmativo;
	}
	
	public boolean reiniciarPartida(boolean zerarDinheiro) {
		if(roleta == null) {
			iniciarPartida();
			return true;
		} else {
			boolean andamento = roleta.informaEmAndamento();
			
			if(!andamento) {
				iniciarPartida();
				return true;
			} else {
				boolean autoriza = avaliarInterrupcao(zerarDinheiro);
				
				if(autoriza) {
					roleta.limpar(zerarDinheiro);
					return true;
				}
			}
		}
		
		return false;
	}
	
	public int getSaldoAtual() {
		return roleta.getSaldoAtual();
	}
	
	public void mostrarInstrucoes() {
		JOptionPane.showMessageDialog(null, "Para jogar o jogo de Roleta primeiro selecione a\n" +
				"op��o \"Novo Jogo\"e ent�o escolha uma ficha\n" +
				"para apostar.\n\n" +
				"Em seguida fa�a sua aposta em alguma das casas\n" +
				"que possuem premia��es conforme abaixo:\n" +
				"- N�meros simples = 36 vezes\n- \"1st 12\", \"2nd 12\", \"3rd 12\" e \"2 to 1\" = 3 vezes\n" +
				"- \"1-18\", \"19-36\", \"Even\", \"Odd\", \"Black\", \"Red\" = 2 vezes\n\n" +
				"Ap�s estar satisfeito com suas apostas clique em\n" +
				"\"Rodar\" para rodar a roleta e sortear um n�mero.\n" +
				"Ap�s isso aparecer� uma mensagem informando o\n" +
				"resultado da jogada, e o n�mero sorteado e o\n" +
				"pr�mio aparecer�o no topo direito da tela de jogo.\n" +
				"Se quiser remover as apostas clique em \"Limpar\".\n" +
				"Para reiniciar o jogo com a pontua��o inicial clique\n" +
				"em \"Reiniciar\".\n\n" +
				"Boa Sorte!", "Instru��es", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void mostrarCreditos() {
		JOptionPane.showMessageDialog(null, "Jogo de Roleta desenvolvido por:\n" +
				"Thiago Schoppen Veronese\n\nhttp://www.inf.ufsc.br/~tveronese/\n\n" +
				"UFSC - Ci�ncias da Computa��o - 2007/1", "Cr�ditos", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void mostrarMsgValorIndisponivel() {
		JOptionPane.showMessageDialog(null, "Saldo indispon�vel!", "Erro!", JOptionPane.ERROR_MESSAGE);
	}
	
	public void mostrarMsgApostasTrancadas() {
		JOptionPane.showMessageDialog(null, "As apostas est�o trancadas!", "Erro!", JOptionPane.ERROR_MESSAGE);
	}
	
	public void mostrarMsgGanhou() {
		JOptionPane.showMessageDialog(null, "Voc� ganhou!", "Parab�ns!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void mostrarMsgPerdeu() {
		JOptionPane.showMessageDialog(null, "Voc� perdeu!", "Que pena!", JOptionPane.WARNING_MESSAGE);
	}
	
}
