import java.io.Serializable;

public class QuebraCabeca implements Serializable{

	/**
	 * 
	 */
	protected static final long serialVersionUID = 482344666991915660L;

	/**
	 *Código do quebra-cabeça (De 1 a 50)
	 */
	protected byte codigo;

	/**
	 *Inidica se o quebra-cabeça já foi finalizado
	 */
	protected boolean finalizado = false;

	/**
	 *Número de vezes que o jogador iniciou o quebra-cabeça
	 */
	protected int tentativas = 0;

	/**
	 * Array de pecas
	 */
	protected Peca[] pecas;
	
	/**
	 * @param vCodigo Código para inicialização das peças
	 * @throws Exception Gera execeção se o código é menor que 1 ou maior que 50
	 */	
	public QuebraCabeca(byte vCodigo) throws Exception{
		//Verifica se o código informado é válido
		if(vCodigo < 1 || vCodigo > 50)
			throw new Exception("Código inválido");
		codigo = vCodigo;
	}	
	
	/**
	 * Inicializa o quebra cabeças de acordo com o código
	 *
	 */
	public void inicializar(){
		//Inicializa as pecas de acordo com o código
		switch(codigo){
			case 1: inicializar1(); break;
			case 2: inicializar2(); break;
			case 3: inicializar3(); break;
			case 4: inicializar4(); break;
			case 5: inicializar5(); break;
			case 6: inicializar6(); break;
			case 7: inicializar7(); break;
			case 8: inicializar8(); break;
			case 9: inicializar9(); break;
			case 10: inicializar10(); break;
			case 11: inicializar11(); break;
			case 12: inicializar12(); break;
			case 13: inicializar13(); break;
			case 14: inicializar14(); break;
			case 15: inicializar15(); break;
			case 16: inicializar16(); break;
			case 17: inicializar17(); break;
			case 18: inicializar18(); break;
			case 19: inicializar19(); break;
			case 20: inicializar20(); break;
			case 21: inicializar21(); break;
			case 22: inicializar22(); break;
			case 23: inicializar23(); break;
			case 24: inicializar24(); break;
			case 25: inicializar25(); break;
			case 26: inicializar26(); break;
			case 27: inicializar27(); break;
			case 28: inicializar28(); break;
			case 29: inicializar29(); break;
			case 30: inicializar30(); break;
			case 31: inicializar31(); break;
			case 32: inicializar32(); break;
			case 33: inicializar33(); break;
			case 34: inicializar34(); break;
			case 35: inicializar35(); break;
			case 36: inicializar36(); break;
			case 37: inicializar37(); break;
			case 38: inicializar38(); break;
			case 39: inicializar39(); break;
			case 40: inicializar40(); break;
			case 41: inicializar41(); break;
			case 42: inicializar42(); break;
			case 43: inicializar43(); break;
			case 44: inicializar44(); break;
			case 45: inicializar45(); break;
			case 46: inicializar46(); break;
			case 47: inicializar47(); break;
			case 48: inicializar48(); break;
			case 49: inicializar49(); break;
			case 50: inicializar50(); break;				
		}		
	}

	/**
	 *Retorna o código do quebra-cabeça
	 */
	public byte getCodigo() {
		return codigo;
	}

	/**
	 *Seta o quebra-cabeça como finalizado
	 */
	public void setFinalizado(boolean value) {
		finalizado = value;
	}

	/**
	 *Informa se o quebra-cabeça já foi finalizado
	 */
	public boolean getFinalizado() {
		return finalizado;
	}

	/**
	 *Atualiza (soma 1) o número de vezes que o
	 *quebra-cabeça foi iniciado
	 */
	public void atualizaTentativas() {
		tentativas++;
	}

	/**
	 *Informa o número de vezes que o quebra-cabeça
	 *foi iniciado
	 */
	public int numTentativas() {
		return tentativas;
	}
	
	/**
	 * Informa as peças que serão utilizadas no quebra-cabeças
	 * @return Array com as peças do quebra-cabeças
	 */
	public Peca[] informarPecas() {
		return pecas;
	}

	protected void inicializar1(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.C,TipoPeca.D,TipoPeca.D,TipoPeca.E,TipoPeca.F,TipoPeca.G,TipoPeca.I,TipoPeca.K,TipoPeca.N};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar2(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.B,TipoPeca.C,TipoPeca.D,TipoPeca.D,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.G,TipoPeca.J,TipoPeca.K,TipoPeca.M};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar3(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.I,TipoPeca.J,TipoPeca.M,TipoPeca.N};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar4(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.B,TipoPeca.D,TipoPeca.D,TipoPeca.E,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.I,TipoPeca.K,TipoPeca.M,TipoPeca.N};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar5(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.D,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.I,TipoPeca.J,TipoPeca.K,TipoPeca.M};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar6(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.F,TipoPeca.I,TipoPeca.J,TipoPeca.K,TipoPeca.M};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar7(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.D,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.J,TipoPeca.K};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar8(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.D,TipoPeca.H,TipoPeca.K,TipoPeca.L,TipoPeca.N};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar9(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.D,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.H,TipoPeca.L};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar10(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.D,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.L,TipoPeca.N};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar11(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.D,TipoPeca.F,TipoPeca.F,TipoPeca.G,TipoPeca.I};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar12(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.C,TipoPeca.D,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.G,TipoPeca.I,TipoPeca.J,TipoPeca.M};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar13(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.C,TipoPeca.D,TipoPeca.D,TipoPeca.E,TipoPeca.F,TipoPeca.G,TipoPeca.K,TipoPeca.M,TipoPeca.N};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar14(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.C,TipoPeca.D,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.G,TipoPeca.H,TipoPeca.I,TipoPeca.J};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar15(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.C,TipoPeca.D,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.G,TipoPeca.J,TipoPeca.M,TipoPeca.N};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar16(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.E,TipoPeca.F,TipoPeca.I,TipoPeca.J,TipoPeca.K,TipoPeca.M};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar17(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.D,TipoPeca.E,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.H,TipoPeca.N};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar18(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.D,TipoPeca.G,TipoPeca.H, TipoPeca.I,TipoPeca.L};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar19(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.F,TipoPeca.G,TipoPeca.I,TipoPeca.J,TipoPeca.K};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar20(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.F,TipoPeca.G,TipoPeca.J,TipoPeca.L,TipoPeca.N};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar21(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.I,TipoPeca.J};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar22(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.J,TipoPeca.N};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar23(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.J,TipoPeca.K,TipoPeca.L,TipoPeca.M};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar24(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.D,TipoPeca.E,TipoPeca.F,TipoPeca.H,TipoPeca.K};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar25(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.I,TipoPeca.J};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar26(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.F,TipoPeca.G,TipoPeca.I,TipoPeca.J,TipoPeca.K,TipoPeca.M,TipoPeca.N};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar27(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.F,TipoPeca.F,TipoPeca.G,TipoPeca.J,TipoPeca.M,TipoPeca.N};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar28(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.D,TipoPeca.F,TipoPeca.G,TipoPeca.L};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar29(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.E,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.I,TipoPeca.J};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar30(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.E,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.H,TipoPeca.J};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar31(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.C,TipoPeca.D,TipoPeca.D,TipoPeca.E,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.G,TipoPeca.N};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar32(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.D,TipoPeca.E,TipoPeca.E,TipoPeca.F,TipoPeca.K,TipoPeca.M};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar33(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.E,TipoPeca.F,TipoPeca.J,TipoPeca.K,TipoPeca.M,TipoPeca.N};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar34(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.G,TipoPeca.J,TipoPeca.M,TipoPeca.N};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar35(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.F,TipoPeca.G,TipoPeca.H};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar36(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.I,TipoPeca.J};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar37(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.C,TipoPeca.D,TipoPeca.E,TipoPeca.F,TipoPeca.G,TipoPeca.I,TipoPeca.M,TipoPeca.N};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar38(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.D,TipoPeca.E,TipoPeca.E,TipoPeca.F,TipoPeca.L};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar39(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.G,TipoPeca.I,TipoPeca.J,TipoPeca.L};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar40(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.E,TipoPeca.F,TipoPeca.I,TipoPeca.J,TipoPeca.K,TipoPeca.M};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar41(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.D,TipoPeca.E,TipoPeca.E,TipoPeca.F,TipoPeca.F};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar42(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.G,TipoPeca.K,TipoPeca.H,TipoPeca.M};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar43(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.E,TipoPeca.F,TipoPeca.G,TipoPeca.I};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar44(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.C,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.E,TipoPeca.I,TipoPeca.J,TipoPeca.L,TipoPeca.N};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar45(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.C,TipoPeca.I,TipoPeca.J,TipoPeca.L,TipoPeca.N};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar46(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.E,TipoPeca.E,TipoPeca.F,TipoPeca.H,TipoPeca.M};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar47(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.E,TipoPeca.E,TipoPeca.F,TipoPeca.I,TipoPeca.M};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar48(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.C,TipoPeca.F,TipoPeca.G,TipoPeca.J};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar49(){
		char[] tipoPecas = {TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.A,TipoPeca.B,TipoPeca.B,TipoPeca.B,TipoPeca.C,TipoPeca.C,TipoPeca.D,TipoPeca.E,TipoPeca.E,TipoPeca.F,TipoPeca.F};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}

	protected void inicializar50(){
		char[] tipoPecas = {TipoPeca.B,TipoPeca.B,TipoPeca.B,TipoPeca.D,TipoPeca.D,TipoPeca.E,TipoPeca.F,TipoPeca.F,TipoPeca.K,TipoPeca.L,TipoPeca.M,TipoPeca.N};
		int numPecas = tipoPecas.length;
		pecas = new Peca[numPecas];
		for(int i = 0; i < numPecas; i++){
			pecas[i] = new Peca(tipoPecas[i]);
		}
	}
	
	/**
	 * Seta todas as peças como disponíveis
	 */
	public void limparPecas(){
		for(int i = 0; i < pecas.length; i++){
			pecas[i].setDisponivel(true);
		}
	}

}
