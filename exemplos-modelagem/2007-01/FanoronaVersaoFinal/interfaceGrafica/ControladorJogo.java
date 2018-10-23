/*
 * Created on 07/05/2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package interfaceGrafica;

/**
 * @author Nara Sueina
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */


import dominioProblema.ImagemDeTabuleiro;
import dominioProblema.Tabuleiro;

public class ControladorJogo {
	
	protected Tabuleiro tab;  
	protected String nomeJogador1;
	protected InterfaceJogador interfaceJ;
	// protected ImagemDeTabuleiro estado;
	
	
   //Construtor da Controladora recebe referencia da InterfaceJogador
	public ControladorJogo (InterfaceJogador i) {
		interfaceJ=i;	
	}
		
	public Tabuleiro getTabuleiro(){
		return tab;
	}

	public void iniciarPartida() {		
		boolean emAndamento = false;
		boolean autoriza = true;
		boolean idPeca = true;		
		// String nomeJogador1;
		String nomeJogador2;
	
		if (tab == null){
			tab = new Tabuleiro();
		
		}
		emAndamento = tab.getPartidaemAndamento();
		if (emAndamento) {
			autoriza = interfaceJ.avaliarInterrupcao();			
		}
	
		if (!emAndamento || autoriza){
			nomeJogador1 = interfaceJ.obterNomeJogador(" 1");   
			// idPeca = this.obterSimboloJogador();
			idPeca = true;
			tab.criarJogador(nomeJogador1, idPeca);

		
			nomeJogador2 = interfaceJ.obterNomeJogador(" 2");
			tab.criarJogador(nomeJogador2, !idPeca);	
			}
		
			tab.setPartidaemAndamento(true);
			this.estabelecerSequenciamento();
			this.atualizaInformacoes();
	}  
			
	public void estabelecerSequenciamento() {
		tab.esvaziarTabuleiro();
		tab.reorganizarPecas();
	    int idJog = interfaceJ.definirQuemComeca(nomeJogador1);
	    tab.definirOPrimeiro(idJog);
		ImagemDeTabuleiro estado = tab.informarEstado();
	    interfaceJ.exibirEstado(estado);
	}
		
	public void jogar(int linIni, int colIni, int linFim, int colFim) {		
		int retJogada;
		retJogada = tab.validarJogada(linIni, colIni, linFim, colFim);
		// JOptionPane.showMessageDialog(null, "Passou!" + retJogada);
		if ((retJogada == 0) || (retJogada ==4)) {
			if (retJogada == 4) {
				int tipCaptura = interfaceJ.escolherCaptura();		
				tab.realizarJogada(linIni, colIni, linFim, colFim, tipCaptura);	
			}
			else{
				tab.realizarJogada(linIni, colIni, linFim, colFim, retJogada);				
			}

			ImagemDeTabuleiro estado = tab.informarEstado();
			interfaceJ.emitirSomJogada();
	        interfaceJ.exibirEstado(estado);
	        this.verificaVencedor();
		}
		else {	
		    interfaceJ.notificarIrregularidade(retJogada);
		}	
		atualizaInformacoes();
	}
	
	public boolean verificaVencedor(){
		if (tab.retSeHouveVencedor()) {
			String nomeVencedor = tab.getNomeVencedor();
			interfaceJ.comunicarVencedor(nomeVencedor);
			interfaceJ.emitirSomFinalJogo();
			return true;
		}else{
			return false;
		}
	}
	
	public void atualizaInformacoes() {
		int valor = 0;
		int numPecasVerdesEmJogo = 0;
		int numPecasVerdesCapturadas = 0;
		int numJogadasVerdes = tab.getNumJogadasVerdes();
		int numPecasVermelhasEmJogo = 0;
		int numPecasVermelhasCapturadas = 0;
		int numJogadasVermelhas = tab.getNumJogadasVermelho();
		ImagemDeTabuleiro estado = tab.informarEstado();
		for (int linha = 1; linha < 6; linha++) {
			for (int coluna = 1; coluna < 10; coluna++) {
				valor = estado.getValor(linha, coluna);
				switch (valor) {
				case 0:
					//
					break;
				case 1:
					numPecasVerdesEmJogo++;
					break;
				case 2:
					numPecasVermelhasEmJogo++;
				}
			}
		}
		numPecasVerdesCapturadas = 22 - numPecasVerdesEmJogo;
		numPecasVermelhasCapturadas = 22 - numPecasVermelhasEmJogo;
		//verde
		interfaceJ.setNumPecasVerdesEmJogo(numPecasVerdesEmJogo);
		interfaceJ.setNumPecasVerdesCapturadas(numPecasVerdesCapturadas);
		interfaceJ.setNumJogadasVerde(numJogadasVerdes);
		//vermelho
		interfaceJ.setNumPecasVermelhasEmJogo(numPecasVermelhasEmJogo);
		interfaceJ.setNumPecasVermelhasCapturadas(numPecasVermelhasCapturadas);
		interfaceJ.setNumJogadasVermelhas(numJogadasVermelhas);		
	}
}
