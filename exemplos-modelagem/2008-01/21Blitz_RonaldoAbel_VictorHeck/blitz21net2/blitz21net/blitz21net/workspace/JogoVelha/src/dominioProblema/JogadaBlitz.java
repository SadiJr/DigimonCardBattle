package dominioProblema;

import ufsc.inf.leobr.annotations.attribute.DirectlyReferredAttribute;
import ufsc.inf.leobr.annotations.classes.Ec;
import ufsc.inf.leobr.enumerations.classes.InheritanceType;


@Ec(inheritance=InheritanceType.WHIT_INHERITANCE)
public class JogadaBlitz implements br.ufsc.inf.leobr.cliente.Jogada{

	public int linha;
	
	public int coluna;
	
	public int carta;
	

	public JogadaBlitz(int linha, int coluna,int carta) {
		super();
		
		this.linha = linha;
		this.coluna = coluna;
		
		this.carta=carta;
	}
	public JogadaBlitz(int linha, int coluna ) {
		super();
		this.linha = linha;
		this.coluna = coluna;
		
	}

	public int getLinha() {
		return linha;
	}
	public int getCarta() {
		return carta;
	}
	
	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	public void setCarta(int carta) {
		this.carta = carta;
	}
	
	
}
