package app;

public class Casa {
	 
	protected int numeroPedras;
	
	public Casa(){
		numeroPedras = 0;
	}
	 
	public int getNumeroPedras() {
		return numeroPedras;
	}
	 
	public void atualizaCasa(int nroPedras) {
		this.numeroPedras = nroPedras;
	}
	 
	public void resetarCasa(int tipoCasa) {
		if (tipoCasa == 0){
			this.numeroPedras = 4;
		} else
			this.numeroPedras = 0;
	}	 
}
 
