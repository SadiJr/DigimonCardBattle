package app;

//<<<<<<< .mine
//=======
//
//>>>>>>> .r7274
public class Tabuleiro{
 
	protected Casa[] casas;
	protected Casa[] repositorios;
	protected int[] estadoAtual;
	 
	public Tabuleiro(){
		casas = new Casa[12];
		repositorios = new Casa[2];
		estadoAtual = new int[14];
		
	}
	
	public int[] getEstadoAtual() {
		for (int i = 0; i < 12; i ++){
			estadoAtual[i] = casas[i].getNumeroPedras();
		}
		for (int i = 0; i < 2; i++){
			estadoAtual[i+12] = repositorios[i].getNumeroPedras();
		}
		return estadoAtual;
	}
	 

	 
	public boolean verificarCasaVazia(int nroCasa) {
		return casas[nroCasa].getNumeroPedras() == 0;
	}
	 
	public void atualizaTabuleiro(int[] estadoFinal) {
		for (int i = 0; i < 12; i ++)
			casas[i].atualizaCasa(estadoFinal[i]);	
		for (int i = 12; i < 14; i++)
			repositorios[i-12].atualizaCasa(estadoFinal[i]);
	}
	 
	public void prepararTabuleiro() {
		
		for(int i = 0; i < 12; i++){
			if(casas[i] == null)
				casas[i] = new Casa();
			casas[i].resetarCasa(0);
		}
		
		for(int i = 0; i < 2 ; i++){
			if(repositorios[i] == null)
				repositorios[i] = new Casa();
			repositorios[i].resetarCasa(1);
		}
		
	}
	
	public int getPedrasCasa(int casa){
		return casas[casa].getNumeroPedras();
	}
	
	public int getPedrasRepositorio(int nroJogador) {
		return repositorios[nroJogador].getNumeroPedras();
	}
}
 