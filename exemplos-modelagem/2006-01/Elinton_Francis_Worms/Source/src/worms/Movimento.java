package worms;

import java.util.TimerTask;

/**
 * Classe responsável por movimentar elementos
 * @author Elinton Dozol Machado
 * @since 30/06/06
 */
class Movimento extends TimerTask{
	
	public static int PULAR  = 0;
	public static int ATIRAR = 1;
	private long startTime = System.currentTimeMillis ();
	private long  tempoInicialReal = startTime;
	private CPrincipal cPrincipal;
	private int posElemento[];
	private int velocidade = 0;
	private int angulo = 0;
	private int atividade = PULAR;
	private int aceleracao = 0;
	private boolean direcaoDireita = true;
	private boolean ativo = false;
	
	public Movimento(CPrincipal pCPrincipal,int[] posicao, int atividade, int velocidade,int angulo, boolean ehDireita){
		cPrincipal = pCPrincipal;
		this.atividade = atividade;
		this.velocidade = velocidade;
		this.angulo = angulo;
		this.direcaoDireita = ehDireita;
		posElemento = posicao;
		ativo = true; //sinaliza que a thread está ativa
	}
	
	public boolean cancel() {
		ativo = false; //sinaliza que a thread está desativada
		return super.cancel();
	}
	
	public void setPosicaoInicial(int _x, int _y){
		posElemento[0] = _x;
		posElemento[1] = _y;
	}
	
	public void setVelocidadeInicial(int velocidade){
		this.velocidade = velocidade;
	}
	
	public void setAngulo(int angulo){
		this.angulo = angulo;
	}
	
	public void setDirecao(boolean direita){
		direcaoDireita = direita;
	}
	
	public int getVelocidadeInicial(){
		return velocidade;
	}
	
	public int getAnguloInicial(){
		return angulo;
	}
	
	public void setAceleracao(int _aceleracao){
		aceleracao = _aceleracao;
	}
	public void reiniciaTempo(){
		startTime = System.currentTimeMillis();
	}
	
	public void run(){
		int novaPosicao[] = this.calculaPosicao(posElemento[0],posElemento[1],velocidade,angulo,direcaoDireita);
		if (atividade==PULAR){
			cPrincipal.posicionaMinhoca(novaPosicao[0],novaPosicao[1]);
			cPrincipal.repaint();
		}else if (atividade == ATIRAR){
			cPrincipal.posicionaBomba(novaPosicao[0],novaPosicao[1]);
			cPrincipal.repaint();
		}
	}
	
	/**Método aplica as Leis de Newton de lançamento Oblíquo sobre um ponto e retorna o novo ponto 
	 * 
	 * @param _sx posição inicial de x
	 * @param _sy posição inicial de y
	 * @param speed velcidade do lançamento 
	 * @param degree angulo do lançamento
	 * @param isRight direção do lançamento
	 * @return int[] que é a nova posição [x,y]
	 */
	public int[] calculaPosicao(int _sx, int _sy, int speed,int degree,boolean isRight){
		int posicao[] = new int[2];
		try {
			long miliseconds = (System.currentTimeMillis() - startTime);
			if (isRight)
				posicao[0] = _sx+(int)((speed*(Utils.cos(degree)*miliseconds))/(65536*1000)+(aceleracao*miliseconds*miliseconds)/2000000); //distace = speed*cos(degree)* time; MRU
			else 
				posicao[0] = _sx-(int)((speed*(Utils.cos(degree)*miliseconds))/(65536*1000)+(aceleracao*miliseconds*miliseconds)/2000000); //distace = speed*cos(degree)* time; MRU
			posicao[1] = _sy-(int)(((speed*Utils.sin(degree)*miliseconds)/(65536*1000)) - ((5*miliseconds*miliseconds)/1000000));	//MRUV	 	
		} catch (Exception e) {
			System.out.println("Erro:"+e.getMessage());
		}
		return posicao;
	}
	
	public long getTempoInicialDoMovimento(){
		return tempoInicialReal;		
	}
	
	public boolean estaAtiva(){
		return ativo;
	}
	
	
	
}
