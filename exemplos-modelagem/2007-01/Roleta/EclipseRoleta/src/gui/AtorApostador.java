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
				"opção \"Novo Jogo\"e então escolha uma ficha\n" +
				"para apostar.\n\n" +
				"Em seguida faça sua aposta em alguma das casas\n" +
				"que possuem premiações conforme abaixo:\n" +
				"- Números simples = 36 vezes\n- \"1st 12\", \"2nd 12\", \"3rd 12\" e \"2 to 1\" = 3 vezes\n" +
				"- \"1-18\", \"19-36\", \"Even\", \"Odd\", \"Black\", \"Red\" = 2 vezes\n\n" +
				"Após estar satisfeito com suas apostas clique em\n" +
				"\"Rodar\" para rodar a roleta e sortear um número.\n" +
				"Após isso aparecerá uma mensagem informando o\n" +
				"resultado da jogada, e o número sorteado e o\n" +
				"prêmio aparecerão no topo direito da tela de jogo.\n" +
				"Se quiser remover as apostas clique em \"Limpar\".\n" +
				"Para reiniciar o jogo com a pontuação inicial clique\n" +
				"em \"Reiniciar\".\n\n" +
				"Boa Sorte!", "Instruções", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void mostrarCreditos() {
		JOptionPane.showMessageDialog(null, "Jogo de Roleta desenvolvido por:\n" +
				"Thiago Schoppen Veronese\n\nhttp://www.inf.ufsc.br/~tveronese/\n\n" +
				"UFSC - Ciências da Computação - 2007/1", "Créditos", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void mostrarMsgValorIndisponivel() {
		JOptionPane.showMessageDialog(null, "Saldo indisponível!", "Erro!", JOptionPane.ERROR_MESSAGE);
	}
	
	public void mostrarMsgApostasTrancadas() {
		JOptionPane.showMessageDialog(null, "As apostas estão trancadas!", "Erro!", JOptionPane.ERROR_MESSAGE);
	}
	
	public void mostrarMsgGanhou() {
		JOptionPane.showMessageDialog(null, "Você ganhou!", "Parabéns!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void mostrarMsgPerdeu() {
		JOptionPane.showMessageDialog(null, "Você perdeu!", "Que pena!", JOptionPane.WARNING_MESSAGE);
	}
	
}
