package app;

public class JogadorMaquina extends Jogador {
	
	public JogadorMaquina(){
		this.letraJogador = "B";
	}
	
	int realizarJogada(int[] estadoAtual) {		
		return this.calcularJogada(estadoAtual);
	}
	
	public int calcularJogada(int[] estadoAtual){
		
		int casaFinalJogada = 0;
		int resultadoTab[] = new int[6];
		int casaFinal;
		int pesoTotal=-1;
		int j, i;
			i = 1;
			j = 6;		
			
		for(;j<12;j++){
			if(!(estadoAtual[j]==0)){
				pesoTotal+=estadoAtual[j]*2;
				casaFinal = (j+	estadoAtual[j])%12;
				if(casaFinal  < 6){
					casaFinal--;
					if(casaFinal==-1)
						casaFinal = 13;
				}
				if(casaFinal==13){
					pesoTotal+=20;
					if(j==11)
						pesoTotal+=30;
				}
				resultadoTab[j-6]=j;				
			}
		}			
		
		for(i=1;i<6;i++){
			if(resultadoTab[i]>resultadoTab[i-1])
				casaFinalJogada=i+6;
		}
		return casaFinalJogada;
	}


	@Override
	int realizarJogada() {
		return 0;
	}

}
