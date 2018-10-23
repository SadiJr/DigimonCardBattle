package dominioProblema;

public class Lance {
 
	protected int linha;
	 
	protected int coluna ;
	
	public Lance(){
	linha=0;
	coluna=0;
	//System.out.println("lance()"+this.informarLinha()+"lance()"+ this.informarColuna());
	}
	public Lance(Lance t){
		this.assumir(t.informarLinha(), t.informarColuna());
		
	}
	public Lance(int plinha,int pcoluna){
		linha=plinha;
		coluna=pcoluna;
	}
	public int informarLinha() {
		return linha;
	}
	 
	public int informarColuna() {
		return coluna;
	}
	
	 
	public void assumir(int valorLinha, int valorColuna) {
		linha = valorLinha;
		coluna = valorColuna;
		
	}
	 
}
 
