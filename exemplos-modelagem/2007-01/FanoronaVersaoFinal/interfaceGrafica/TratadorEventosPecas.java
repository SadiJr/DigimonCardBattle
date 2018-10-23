package interfaceGrafica;

import java.awt.event.MouseAdapter;

import javax.swing.JLabel;

import dominioProblema.Posicao;

/**
 * @author Wanderson Rigo
 * 
 */
public class TratadorEventosPecas extends MouseAdapter{
	protected JLabel posicaoFinal;	
	protected JLabel posicaoInicial;	
	protected InterfaceJogador inter;
	protected JLabel[][] mapaVPosicao;
	protected int linIni;
	protected int linFim;
	protected int colIni;
	protected int colFim;
	
	public TratadorEventosPecas(InterfaceJogador i, JLabel[][] mapa){		
		inter = i;
		mapaVPosicao = mapa;		
	}
	
	public void mouseClicked(java.awt.event.MouseEvent e) {
		if(inter.controlador != null){
			if (!inter.controlador.tab.retSeHouveVencedor()) {
				for (int linha = 1; linha < 6; linha++) {
					for (int coluna = 1; coluna < 10; coluna++) {
						if (mapaVPosicao[(linha - 1)][(coluna - 1)] == e.getSource()) {				

							if (!inter.isPosicaoInicialSelecionada()) {
								posicaoInicial = mapaVPosicao[(linha - 1)][(coluna - 1)];
								linIni = linha;
								colIni = coluna;
								inter.setPosicaoInicialSelecionada(true);
								alterarAparenciaPecaInicial();
							} else {
								posicaoFinal = mapaVPosicao[(linha - 1)][(coluna - 1)];
								linFim = linha;
								colFim = coluna;
								inter.setPosicaoFinalSelecionada(true);
								alterarAparenciaPecaFinal();
							}
						}
					}					
				}			

				if (inter.isPosicaoInicialSelecionada() == true 
						&& inter.isPosicaoFinalSelecionada() == true) {
					inter.controlador.jogar(linIni, colIni, linFim, colFim);
					inter.setPosicaoInicialSelecionada(false);
					inter.setPosicaoFinalSelecionada(false);
				}
			} else {
				inter.notificarIrregularidade(4);
			}
		}else{
			inter.notificarIrregularidade(5);
		}
	}
	
	public void alterarAparenciaPecaFinal(){		
		Posicao posicaoFinalSelecionada = inter.controlador.tab.getPosicao(linFim, colFim);		
		if (inter.controlador.tab.getJogadorDaVez().getPeca() == posicaoFinalSelecionada.getOcupante()) {
			if(posicaoFinal.getIcon().equals(InterfaceJogador.xis)){
				posicaoFinal.setIcon(InterfaceJogador.xisMudado);
			}else if(posicaoFinal.getIcon().equals(InterfaceJogador.xisMudado)){
				posicaoFinal.setIcon(InterfaceJogador.xis);		
			}else if(posicaoFinal.getIcon().equals(InterfaceJogador.bola)){
				posicaoFinal.setIcon(InterfaceJogador.bolaMudada);
			}else if(posicaoFinal.getIcon().equals(InterfaceJogador.bolaMudada)){
				posicaoFinal.setIcon(InterfaceJogador.bola);
			}
		}		
	}
	
	public void alterarAparenciaPecaInicial(){		
		Posicao posicaoIniSelecionada = inter.controlador.tab.getPosicao(linIni, colIni);		
		if (inter.controlador.tab.getJogadorDaVez().getPeca() == posicaoIniSelecionada.getOcupante()) {
			if(posicaoInicial.getIcon().equals(InterfaceJogador.xis)){
				posicaoInicial.setIcon(InterfaceJogador.xisMudado);
			}else if(posicaoInicial.getIcon().equals(InterfaceJogador.xisMudado)){
				posicaoInicial.setIcon(InterfaceJogador.xis);		
			}else if(posicaoInicial.getIcon().equals(InterfaceJogador.bola)){
				posicaoInicial.setIcon(InterfaceJogador.bolaMudada);
			}else if(posicaoInicial.getIcon().equals(InterfaceJogador.bolaMudada)){
				posicaoInicial.setIcon(InterfaceJogador.bola);
			}
		}		
	}
}
