package settlers.modelo;

public class Porto {

    private int recurso;
    
    private PosicaoColonia posicoesColonia[];
  
    public Porto(PosicaoColonia posicaoColonia1, PosicaoColonia posicaoColonia2) {
    	posicoesColonia = new PosicaoColonia[2];
    	posicoesColonia[0] = posicaoColonia1;
    	posicoesColonia[1] = posicaoColonia2;
    	posicoesColonia[0].setPorto(this);
    	posicoesColonia[1].setPorto(this);
    	recurso = Tabuleiro.RECURSO_X;
    }
    
    public PosicaoColonia getPosicaoColonia(int lado) {
    	return posicoesColonia[lado %2];
    }
    
    public void setRecurso(int recurso) {
    	this.recurso = recurso;
    }
    
    public int getRecurso() {
    	return recurso;
    }
    
    public int getProporcao() {
    	return (recurso == Tabuleiro.RECURSO_X) ? 3 : 2;
    }

}
