package worms;

import java.util.Random;

public class JogadorAutomatico {
	
	private int level = 5;
	private CMinhoca minhoca = null;
	private CPrincipal cPrincipal = null;
	
	public JogadorAutomatico(CPrincipal cPrincipal, int level) {
		this.cPrincipal = cPrincipal;
		this.level = level;		
	}
	
	public void procederJogada(CMinhoca minhoca,int posicaoInimiga[]){
		try{
			boolean direita = false;
			Random aleatorio = new Random(System.currentTimeMillis());
			int posicao[] = new int[2];
			this.minhoca = minhoca;
			posicao[0] = minhoca.getPosicaoX();
			posicao[1] = minhoca.getPosicaoY();
			//anda aleatoriamente
			do{
				if (posicaoInimiga[0] <= posicao[0]){//anda em direcao da minhoca inimiga
					cPrincipal.andaEsquerda();
					direita = false;
				} else{
					cPrincipal.andaDireita();
					direita = true;
				}
			}while (Math.abs(aleatorio.nextInt()%2) == 0);
			Thread.currentThread().sleep(500);
			//escolha arma eleatoriamente
			int desconto = 0;
			if (Math.abs(aleatorio.nextInt()%2) == 0){
				cPrincipal.setArmaMinhoca("LancaMissel");
				desconto = 5;
			}
			else
				cPrincipal.setArmaMinhoca("Granada");
			//mira aleatoriamente com prisicao depenente do level
			int status;
			if (direita)
				status = CMinhoca.MIRANDO_DIREITA;
			else
				status = CMinhoca.MIRANDO_ESQUERDA;
			minhoca.setStatus(status);
			posicao[0] = minhoca.getPosicaoX();
			posicao[1] = minhoca.getPosicaoY();
			int angulo = 40+Math.abs(aleatorio.nextInt())%level;
			minhoca.setAnguloDisparo(angulo);
			int distancia = Math.abs(posicao[0]-posicaoInimiga[0]);
			int altura = posicao[1] - posicaoInimiga[1];
			distancia += altura;
			distancia -= desconto;				
			int velocidade = Utils.SQRT((int)((10*distancia*65536)/Utils.sin(2*angulo)));
			minhoca.setVelocidadeDisparo(velocidade);//-level+Math.abs(aleatorio.nextInt())%level);
			//atira
			cPrincipal.atirar();
		}catch (Exception e) {
			System.err.println(e.getMessage()+e.getClass());
		}
	}
		
}
