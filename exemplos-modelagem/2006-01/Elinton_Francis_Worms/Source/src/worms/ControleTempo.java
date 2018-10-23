package worms;

import java.util.TimerTask;

/**
 * Classe conta o tempo
 * @author Elinton e Francis
 *
 */
public class ControleTempo extends TimerTask{
	
	private long tempoInicial;
	
	private CPrincipal cPrincipal;
	
	public ControleTempo(CPrincipal cPrincipal){
		this.cPrincipal = cPrincipal; 
		tempoInicial = System.currentTimeMillis();
	}
	
	public void run() {
		int contador = (int)(tempoInicial - System.currentTimeMillis())/-1000;
		this.cPrincipal.acertaTempoRestante(contador);
	}
	
	public void reInicio(){
		tempoInicial = System.currentTimeMillis();
	}
	
	public void pause(int tempoDecorrido){
		tempoInicial = System.currentTimeMillis();
		tempoInicial += (tempoDecorrido * -1000);
	}
}
