package app;
//
//<<<<<<< .mine
//=======
//
//>>>>>>> .r7274
public class ControladorTabuleiro {
 
	protected Jogador[] jogadores;
	protected Tabuleiro tab;
	protected int[] estadoTab;
	protected boolean partidaEmAndamento;
	protected int interf;
	
	public ControladorTabuleiro(){
		partidaEmAndamento = false;
		this.tab = new Tabuleiro();
		interf = 0;
	}
	
	public void instanciarJogadores(int interf){
		jogadores = new Jogador[2];
		jogadores[0] = new JogadorHumano("A");
		if (this.interf == 0)
			jogadores[1] = new JogadorHumano("B");
		else		
			jogadores[1] = new JogadorMaquina();
		
	}
	
	public int[] getEstadoAtual(){
		return tab.getEstadoAtual();
	}
	
	public void prepararTabuleiro() {
		tab.prepararTabuleiro();
		estadoTab = this.getEstadoAtual();
	}
	
	public int getPedrasCasa(int casa){
		return tab.getPedrasCasa(casa);
	}
	
	public int getPedrasRepositorio(int nroJogador){
		return tab.getPedrasRepositorio(nroJogador);
	}
	 
	public void setInterf(int tipoInterface) {
		this.interf = tipoInterface;
	}
	
	public void setpartidaEmAndamento(boolean andamento){
		this.partidaEmAndamento = andamento;
	}
	 
	public void verificarContinuidade() {
		int casasVazias = 0;
		estadoTab = this.getEstadoAtual();
		
		for(int i = 0; i < 6; i ++){
			if(estadoTab[i] == 0){
				casasVazias ++;
			}
		}
		if(!(casasVazias == 6)){
			casasVazias = 0;
			for(int i = 6; i < 12; i ++){
				if(estadoTab[i] == 0){
					casasVazias ++;
				}
			}
		}
		
		if (casasVazias == 6){
			this.setpartidaEmAndamento(false);
			this.verificarVencedor();	
		}
	}
	 
	public void verificarVencedor() {
		
		int repJog0 = tab.getPedrasRepositorio(0);
		int repJog1 = tab.getPedrasRepositorio(1);
		
		if (repJog0 > repJog1){
			jogadores[0].setVencedor();
		} else if (repJog0 < repJog1){
			jogadores[1].setVencedor();
		}
	}
	 
	public boolean getpartidaEmAndamento() {
		return partidaEmAndamento;
	}
	 	 
	public int[] distribuirPedras(int[] estadoAtual, int casa, Jogador jogadorDaVez) {
		
		int nroPedraCasa = tab.getPedrasCasa(casa);
		int PedrasRestantes = nroPedraCasa;
		int casaFinal = (casa+nroPedraCasa)%12;
		estadoAtual[casa]=0;
		estadoAtual = this.tratarCasaFinal(casaFinal, estadoAtual, jogadorDaVez);
		
		for(int i = 0; i < nroPedraCasa; i++){
			if(casa+i == 5 && jogadorDaVez == jogadores[0]){
				estadoAtual[12] = estadoAtual[12]+1;
				PedrasRestantes--;
			}
			
			if(casa+i == 11 && jogadorDaVez == jogadores[1]){
				estadoAtual[13] = estadoAtual[13]+1;
				PedrasRestantes--;
			}
			
			if(!(PedrasRestantes==0)){
				estadoAtual[(casa+1+i)%12] = estadoAtual[(casa+1+i)%12]+1;
				PedrasRestantes--;	
			}			
		}
				
		return estadoAtual;
	}
	
	public int[] tratarCasaFinal(int casaFinal, int estadoAtual[], Jogador jogadorDaVez){
		
		if(casaFinal > 5 && jogadorDaVez == jogadores[0]){
			casaFinal--;
			if(casaFinal==5){
				casaFinal = 12;
				this.trocarVezJogador(jogadorDaVez);
			}
		}else{
			if(estadoAtual[casaFinal]==0 && jogadorDaVez == jogadores[0]){
				estadoAtual[12]= estadoAtual[12]+estadoAtual[11-casaFinal];
				estadoAtual[11-casaFinal]=0;
			}
		} 
		
		if(casaFinal  < 6 && jogadorDaVez == jogadores[1]){
			casaFinal--;
			if(casaFinal==-1){
				casaFinal = 13;
				this.trocarVezJogador(jogadorDaVez);
			}
		}else{
			if(estadoAtual[casaFinal]==0 && jogadorDaVez == jogadores[1]){
				estadoAtual[13]= estadoAtual[13]+estadoAtual[11-casaFinal];
				estadoAtual[11-casaFinal]=0;
			}
		}	
		return estadoAtual;
	}
	 
	public void trocarVezJogador(Jogador jogadorDaVez) {
		if(jogadores[0].getDaVez() == true){
			jogadores[0].setDaVez(false);
			jogadores[1].setDaVez(true);
		} else{
			jogadores[0].setDaVez(true);
			jogadores[1].setDaVez(false);
		}
	}
	 
	public void iniciarPartida() {
		this.setpartidaEmAndamento(true);
		this.instanciarJogadores(interf);
		jogadores[0].setDaVez(true);
		this.prepararTabuleiro();
	}
	
	public int realizarJogadaHumano(Jogador jogadorDaVez){	
		return jogadorDaVez.realizarJogada();
	}
	
	public int realizarJogadaMaquina(){
		int[] estadoAtual = this.getEstadoAtual();			
		return jogadores[1].realizarJogada(estadoAtual);
	}
	
	public int[] tratarJogada (int casaJogada, Jogador jogadorDaVez){
		int[] estadoAtual = this.getEstadoAtual();
		int[] estadoFinal;
		
		estadoFinal = this.distribuirPedras(estadoAtual, casaJogada, jogadorDaVez);
				
		return estadoFinal;
	}
	
	public void pedirJogada(Jogador jogadorDaVez){
		
		if(this.getpartidaEmAndamento() == true){
			int casaJogada;
			int[] estadoFinal;
			boolean continuidade = false;
		
			if(interf == 1 && jogadorDaVez == jogadores[1])
				casaJogada = this.realizarJogadaMaquina();				
			else{
				casaJogada = this.realizarJogadaHumano(jogadorDaVez);
			
			}
			estadoFinal = this.tratarJogada(casaJogada, jogadorDaVez);
			tab.atualizaTabuleiro(estadoFinal);
			this.verificarContinuidade();
			continuidade = this.getpartidaEmAndamento();
			
			if(continuidade)
				this.trocarVezJogador(jogadorDaVez);
			
		}
	}
	
	public Jogador getJogadorDaVez(){
		Jogador jogadorDaVez;
		
		if(jogadores[0].getDaVez() == true)
			jogadorDaVez = jogadores[0];
		else
			jogadorDaVez = jogadores[1];
		
		return jogadorDaVez;
	}
	
	public String getJogador(Jogador jogador){
		return jogador.retorneLetraJogador();
	}
	
	public int getInterface(){
		return this.interf;
	}
	
	public Jogador getVencedor(){
		Jogador vencedor = null;
		
		if(jogadores[0].vencedor==true)
			vencedor = jogadores[0];
		else if(jogadores[1].vencedor==true)
			vencedor = jogadores[1];
		
		return vencedor;
	}
}
 