package dominioProblema;

import ufsc.inf.leobr.annotations.attribute.DirectlyReferredAttribute;
import ufsc.inf.leobr.annotations.classes.Ec;
import ufsc.inf.leobr.enumerations.classes.InheritanceType;


@Ec(inheritance=InheritanceType.WHIT_INHERITANCE)
public class JogadaVelha implements br.ufsc.inf.leobr.cliente.Jogada{

	public int linha;
	
	public int coluna;

	public JogadaVelha(int linha, int coluna) {
		super();
		this.linha = linha;
		this.coluna = coluna;
	}

	public int getLinha() {
		return linha;
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
	
	
}
