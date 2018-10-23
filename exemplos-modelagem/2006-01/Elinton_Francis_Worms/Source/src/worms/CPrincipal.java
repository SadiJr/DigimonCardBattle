package worms;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.util.*;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.game.Sprite;


public class CPrincipal extends Canvas{
	//instancias 
	private LAtor lAtor; 
	private CArma cArma;
	//elementos do jogo
	private CCenario cCenario;	
	private CMinhoca cMinhoca1;	
	private CMinhoca cMinhoca2;	
	private CMinhoca minhocaAtual;//ponteiro para a minhoca atual	
	private CMinhoca minhocaInimiga;//ponteiro para a minhoca inimiga
  	//threads
	private Movimento movimentoPulo;//calcula movimento do pulo	
	private Movimento movimentoTiro;//calcula movimento do tiro	
	private ControleTempo cTempo;//conta o tempo restante por jogada	
	private Timer timer,timerControle;//escalonam execucao de threads
	//mensagens mostradas na tela
	private String message = "";
	//armazena o tempo restante por jogada
	private String tempoRestante = "50";
	//guarda as posicoes de todas as explosoes	
	private int explosao[][]= new int[50][2]; 
	//conta as explosoes
	private int indiceExplosao = 0;
	//configuracoes
	private int TEMPO_POR_JOGADA = 50;
	private boolean somHabilitado = true;
	private boolean twoPlayers = true;
	private boolean humanTime = true;
	private int dificudade = 5;
	//sinaliza fim de partida
	private boolean partidaFinalizada = false;
	
	public CPrincipal(LAtor pAtor, String cenarioNome){
		setFullScreenMode(true);
		lAtor = pAtor;
		cCenario = new CCenario(cenarioNome,getWidth(),getHeight()+21);
		cMinhoca1 = new CMinhoca("PLAYER 1");
		cMinhoca1.posicionaMinhoca(2,cCenario.getY(2));
		cMinhoca2 = new CMinhoca("PLAYER 2");
		cMinhoca2.posicionaMinhoca(120,cCenario.getY(120));
		cTempo = new ControleTempo(this);
		timerControle  = new Timer();
		timerControle.schedule(cTempo,0,1000);//comece agora e repita a cada um segundo	
		minhocaAtual   = cMinhoca1;
		minhocaInimiga = cMinhoca2;
	}
	
	public void ativaSom(boolean somAtivo){
		somHabilitado = somAtivo;
	}
	
	public void setTwoPlayers(boolean isTwo){
		twoPlayers = isTwo;			
	}
	
	public void setDificudade(int level){
		dificudade = 5 - level; 
	}
	
	public void paint(Graphics g) {
		if (partidaFinalizada){
			lAtor.mostraMenuPrincipal();
			return;
		}
		int status = minhocaAtual.getStatus();
        int x = minhocaAtual.getPosicaoX();
        int y = minhocaAtual.getPosicaoY();
		//desenha fundo
        g.drawImage(cCenario.getImage(),0,0,0);
		//pega cor do fundo do cenário
		g.setColor(cCenario.informaCorFundo());
		//desenha os buracos feitos no cenário com a cor do fundo
		for (int i = 0; i < indiceExplosao; i++) {
			g.fillArc(explosao[i][0]-10,explosao[i][1]-10, 20, 20,0,360);
		}
		g.setColor(0,0,0);
		g.drawString (tempoRestante, getWidth()/2, 2, Graphics.TOP|Graphics.HCENTER);
		if (message!=""){
			g.drawString (message, getWidth()/2, getHeight()/5, Graphics.TOP|Graphics.HCENTER);
		 	message = "";
		}
		desenhaBarraDeVida(g);
		Image minhoca;
		//pega imagem da minhoca
        minhoca = minhocaAtual.getImage();
        //se estiver pulando...
        if(status == CMinhoca.PULANDO_DIREITA || status == CMinhoca.PULANDO_ESQUERDA){
	        if (y<=cCenario.getY(x)){
	        	g.drawImage(minhoca,x,y,Graphics.VCENTER|Graphics.HCENTER);
			}else{//caiu no chao
				if (status == CMinhoca.PULANDO_DIREITA)
					status = CMinhoca.ANDANDO_DIREITA;
				else
					status = CMinhoca.ANDANDO_ESQUERDA;
				y = cCenario.getY(x);
				minhocaAtual.posicionaMinhoca(x,y);
				minhocaAtual.setStatus(status);
				timer.cancel();
				movimentoPulo.cancel();
				g.drawImage(minhoca,x,y,Graphics.VCENTER|Graphics.HCENTER);
			}
        }else if ((status==CMinhoca.ANDANDO_DIREITA)||(status==CMinhoca.ANDANDO_ESQUERDA)||(status>=4)){
        	if (x<getWidth()&&y<getHeight()){
        		g.drawImage(minhoca,x,y,Graphics.VCENTER|Graphics.HCENTER);
        		if (minhocaAtual.getArma()!=null)//se minhoca armada
        			desenhaArma(g);        		
        	}
        }if (status==CMinhoca.ATIRANDO_DIREITA||status==CMinhoca.ATIRANDO_ESQUERDA) {
			desenhaBomba(g);
		}
        int xInimiga = minhocaInimiga.getPosicaoX();
		int yInimiga = cCenario.getY(xInimiga);
		minhocaInimiga.posicionaMinhoca(xInimiga,yInimiga);
		g.drawImage(minhocaInimiga.getImage(),xInimiga,yInimiga,Graphics.VCENTER|Graphics.HCENTER);
		g.drawImage(cCenario.getAgua(),0,cCenario.getImage().getHeight(),Graphics.VCENTER|Graphics.LEFT);
		if (getWidth()>128)
			g.drawImage(cCenario.getAgua(),128,cCenario.getImage().getHeight(),Graphics.VCENTER|Graphics.LEFT);
		if (getHeight()>128){
		    g.setColor(27,28,59);	
		    g.fillRect(0,cCenario.getImage().getHeight()+12,getWidth(),getHeight()-(cCenario.getImage().getHeight()+12));
		}
		setFullScreenMode(true); 
	}
	
	public void acertaTempoRestante(int tempo){
		tempo = TEMPO_POR_JOGADA - tempo;
		tempoRestante = tempo+"";
		if (tempo == 0){
			trocaVez();
		}
		repaint();
	}
	
	private void trocaVez(){
		int status = minhocaAtual.getStatus();
		timerControle.cancel();
		cTempo.cancel();//cancela o tempo
		while (status == CMinhoca.ATIRANDO_DIREITA||status == CMinhoca.ATIRANDO_ESQUERDA||
			   status == CMinhoca.PULANDO_DIREITA ||status == CMinhoca.PULANDO_ESQUERDA){//espera o acabar tiro
			status = minhocaAtual.getStatus();
			try {
				Thread.currentThread().sleep(200);
			} catch (Exception e) {
				System.out.println("problema ao tentar juntar as threads");
			}			
		}
		if (minhocaAtual.getArma()==null)
			status = minhocaAtual.viradaDireita()?CMinhoca.ANDANDO_DIREITA:CMinhoca.ANDANDO_ESQUERDA;
		else
			status = minhocaAtual.viradaDireita()?CMinhoca.ARMADO_DIREITA:CMinhoca.ARMADO_EQUERDA;
		minhocaAtual.setStatus(status);
		CMinhoca aux = minhocaAtual;
		minhocaAtual = minhocaInimiga;//minhoca atual passa a ser a minhoca inimiga
		minhocaInimiga = aux;
		message = minhocaAtual.getNome();
		cTempo = new ControleTempo(this);
		timerControle  = new Timer();
		timerControle.schedule(cTempo,0,1000);//comece agora e repita de 1000 milisegundos
		if (!twoPlayers && humanTime && !partidaFinalizada){
			int pos[] = minhocaInimiga.getPosicao();
			JogadorAutomatico computer = new JogadorAutomatico(this,5);
			computer.procederJogada(minhocaAtual,pos);
			humanTime = false;
		}else{
			humanTime = true;
		}
			
	}
	
	private void desenhaBarraDeVida(Graphics g){
		int xBarra1 = getWidth()/16;
		int xBarra2 = getWidth()/2 + xBarra1;
		g.drawRect(xBarra1,2,50,5);
		g.drawRect(xBarra2,2,50,5);
		g.setColor(255,0,0);
		int life = cMinhoca1.getQuantidadeVida()/2;
		verificaVencedor(cMinhoca2,life,g);
		g.fillRect(xBarra1,2,life,5);
		life = cMinhoca2.getQuantidadeVida()/2;
		verificaVencedor(cMinhoca1,life,g);
		g.fillRect(xBarra2,2,life,5);
		g.setColor(0);//set a cor para preto
	}
	
	private void verificaVencedor(CMinhoca minhoca,int life, Graphics g) {
		if (life<=0){
			message = minhoca.getNome()+" venceu!";
			g.fillRect(getWidth()/2-60, getHeight()/5,120,15);
			g.setColor(0);
			g.drawString (message, getWidth()/2, getHeight()/5, Graphics.TOP|Graphics.HCENTER);
			timerControle.cancel();
			cTempo.cancel();//cancela o tempo
			partidaFinalizada = true;
		}
	}
	
	private void desenhaArma(Graphics g){
		int status = minhocaAtual.getStatus();
		int anguloDaArma = minhocaAtual.getAnguloDisparo();
		int x = minhocaAtual.getPosicaoX();
        int y = minhocaAtual.getPosicaoY();
		Image arma = null;
		int sprAngulo = Sprite.TRANS_NONE;
	    if (status==CMinhoca.ARMADO_DIREITA||status==CMinhoca.ARMADO_DIREITA||
	       status==CMinhoca.MIRANDO_DIREITA||status==CMinhoca.MIRANDO_ESQUERDA){
			if (anguloDaArma<=15){
		    	arma = minhocaAtual.getArma();
		    	g.drawImage(arma,x,y,Graphics.VCENTER|Graphics.HCENTER);
		    	return;
		    }else if (anguloDaArma <= 30){
		    	arma = minhocaAtual.getArma30graus();
		    	g.drawImage(arma,x,y,Graphics.VCENTER|Graphics.HCENTER);
		    	return;
		    }else if (anguloDaArma <= 45){
		     	arma = minhocaAtual.getArma45graus();
		    	g.drawImage(arma,x,y,Graphics.VCENTER|Graphics.HCENTER);
		    	return;
		    }else if (anguloDaArma <= 60){
		    	arma = minhocaAtual.getArma60graus();
		    	g.drawImage(arma,x,y,Graphics.VCENTER|Graphics.HCENTER);
		    	return;
		    }else if (anguloDaArma <= 75){
		    	arma = minhocaAtual.getArma75graus();
		    	g.drawImage(arma,x,y,Graphics.VCENTER|Graphics.HCENTER);
		    	return;
		    }else if (anguloDaArma <= 90){
		    	arma = minhocaAtual.getArma();
		    	sprAngulo = Sprite.TRANS_ROT90;
		    }	
			if (arma!=null){//se chegar até aqui é porque é um angulo reto
				Sprite spr = new Sprite(arma);
				spr.setTransform(sprAngulo) ;
				spr.defineReferencePixel(arma.getWidth()/2,arma.getHeight()/2);
				spr.setRefPixelPosition(x,y);     			
				spr.paint(g);
			}
	    }
}
	
	private void desenhaBomba(Graphics g) {
		int status = minhocaAtual.getStatus();
		int posicao[] = minhocaAtual.getPosicaoBomba();
		int posicaoInimiga[] = new int[2];
		posicaoInimiga[0] = minhocaInimiga.getPosicaoX();
		posicaoInimiga[1] = minhocaInimiga.getPosicaoY();
		int chao = cCenario.getY(posicao[0]);
		
		if (verificaContagemRegressiva(g,posicao,posicaoInimiga)){
			return;//a bomba já explodiu
		}else			
		if (posicao[0]>getWidth()||posicao[0] < 0){//se está fora dos limites da tela
			timer.cancel();
			movimentoTiro.cancel();
			status = minhocaAtual.viradaDireita()?CMinhoca.ANDANDO_DIREITA:CMinhoca.ANDANDO_ESQUERDA;
			minhocaAtual.setStatus(status);
			trocaVez();
		}else if (posicao[1]<=chao){//se está a cima do chão
			if ((posicao[0]<= posicaoInimiga[0]+6)&&(posicao[0]>= posicaoInimiga[0]-6)&&
			    (posicao[1]<= posicaoInimiga[1]+6)&&(posicao[1]>= posicaoInimiga[1]-6)){//se está na minhoca
				minhocaInimiga.setDemage(20);
				fazExplosao(g,posicao,true);
				if (somHabilitado)
					Som.getInstacia().tocaConsegui();
			}else //se nao ecertou a minhoca
				g.drawImage(minhocaAtual.getBomba(),posicao[0],posicao[1],Graphics.VCENTER|Graphics.HCENTER);
		}else{//bomba está abaixo da chão
			if (minhocaAtual.getContagemRegressivaBomba()!=0){
				if(movimentoTiro.getVelocidadeInicial()>= 1){		
					pique(posicao,chao);					
				}
				else timer.cancel();
				g.drawImage(minhocaAtual.getBomba(),posicao[0],posicao[1],Graphics.VCENTER|Graphics.HCENTER);
			}else{
				fazExplosao(g,posicao,true);
				if(somHabilitado)
					Som.getInstacia().tocaErrei();
			}
		}			
	}
	
	/**
	 * faz o pique da granada 
	 * @param posicao int[] com a posicao atual da granada
	 * @param chao posicao y do chaos
	 */
	private void pique(int posicao[],int chao){
		int status = minhocaAtual.getStatus();
		if (somHabilitado)Som.getInstacia().tocaGranadaPique();			
		minhocaAtual.posicionaBomba(posicao[0],posicao[1]);
		movimentoTiro.setPosicaoInicial(posicao[0],posicao[1]);
		if ((chao != cCenario.getY(posicao[0] + 1))){//se chao nao assim: --
				int anguloTiro = movimentoTiro.getAnguloInicial();
				
				if(anguloTiro < 30)
					anguloTiro = 0;
				else
					anguloTiro -= 30;
				movimentoTiro.setAngulo(anguloTiro);
				if (chao + 1 == cCenario.getY(posicao[0] + 1)){//  Chao: / 
					if (status == CMinhoca.ATIRANDO_DIREITA)
						movimentoTiro.setVelocidadeInicial(movimentoTiro.getVelocidadeInicial()/-2);
					else
						movimentoTiro.setVelocidadeInicial(movimentoTiro.getVelocidadeInicial()/2);
				
				}else if (chao-1 == cCenario.getY(posicao[0] + 1)){//   Chao: \
					if (status == CMinhoca.ATIRANDO_DIREITA)
						movimentoTiro.setVelocidadeInicial(movimentoTiro.getVelocidadeInicial()/2);
					else
						movimentoTiro.setVelocidadeInicial(movimentoTiro.getVelocidadeInicial()/-2);
				}else // chao: |
					movimentoTiro.setVelocidadeInicial(movimentoTiro.getVelocidadeInicial()/-2);
		
		}else{//se o chao: --
			movimentoTiro.setVelocidadeInicial(movimentoTiro.getVelocidadeInicial()/2);
		}
		movimentoTiro.reiniciaTempo();
	}
	
	private boolean verificaContagemRegressiva(Graphics g, int []posicaoBomba, int[]posicaoInimiga){
		boolean explodiu = false;
		int contagemRegressiva = minhocaAtual.getContagemRegressivaBomba();
		if (contagemRegressiva!=0){//se é granada
			if((System.currentTimeMillis() - movimentoTiro.getTempoInicialDoMovimento())
			>= contagemRegressiva*1000){
				if(somHabilitado)
					Som.getInstacia().tocaExplosao();
				if ((posicaoInimiga[0]<= posicaoBomba[0]+6)&&(posicaoInimiga[0]>= posicaoBomba[0]-6)&&
					(posicaoInimiga[1]<= posicaoBomba[1]+6)&&(posicaoInimiga[1]>= posicaoBomba[1]-6)){//se a minhoca está na zona de impacto
					minhocaInimiga.setDemage(20);
					if (somHabilitado)
						Som.getInstacia().tocaConsegui();
				}
				else
					if (somHabilitado)
						Som.getInstacia().tocaErrei();
				fazExplosao(g,posicaoBomba,false);
				explodiu = true;//explodiu pq acabou o tempo
			}
		}
		return explodiu;
	}
	
	private void fazExplosao(Graphics g,int[] posicao, boolean tocaSom) {
		int status = minhocaAtual.getStatus();
		timer.cancel();
		movimentoTiro.cancel();
		g.setColor(cCenario.informaCorFundo());
		g.fillArc(posicao[0]-10,posicao[1]-10, 20, 20,0,360);
		explosao[indiceExplosao][0]=posicao[0];
		explosao[indiceExplosao][1]=posicao[1];
		cCenario.alteraChao(posicao[0],posicao[1],10);
		if (somHabilitado && tocaSom)
			Som.getInstacia().tocaExplosao();
		indiceExplosao++;
		g.setColor(0);//set a cor preta
		status = minhocaAtual.viradaDireita()?CMinhoca.ARMADO_DIREITA:CMinhoca.ARMADO_EQUERDA;
		minhocaAtual.setStatus(status);
		//atualiza posicao da minhoca
		int x = minhocaInimiga.getPosicaoX();
		int y = cCenario.getY(x);
		minhocaInimiga.posicionaMinhoca(x,y);
		
		if (!tempoRestante.equals("0")) {//se o tempo for igual a zero já foi trocada a vez
			trocaVez();
		}
	}
 
	public void keyPressed(int keyCode) { 
		try {
			int status = minhocaAtual.getStatus();
			if((status == CMinhoca.ATIRANDO_DIREITA||status == CMinhoca.ATIRANDO_ESQUERDA||
		       status == CMinhoca.PULANDO_DIREITA ||status == CMinhoca.PULANDO_ESQUERDA)
		       && !humanTime)
				return;
			int gameAction = getGameAction(keyCode);
			if(gameAction == RIGHT) {
				if (status >= 6 && !estaAtirando()){
					int velocidadeDisparo = minhocaAtual.getVelocidadeDisparo();
					if(minhocaAtual.viradaDireita())//se minhoca virada para direita aumenta a força
						velocidadeDisparo++;
					else
						velocidadeDisparo--;
					message = "Velocidade: "+ velocidadeDisparo;
					minhocaAtual.setVelocidadeDisparo(velocidadeDisparo);
					repaint();
				}else if (!estaPulando()&&!estaAtirando())//(status!=CMinhoca.PULANDO_DIREITA&&status != CMinhoca.PULANDO_ESQUERDA)
					andaDireita();
			} else if(gameAction == LEFT) {
				if (status >= 6 && !estaAtirando()){
					int velocidadeDisparo = minhocaAtual.getVelocidadeDisparo();
					if(minhocaAtual.viradaDireita())//se minhoca virada para direita diminui a força
						velocidadeDisparo--;
					else
						velocidadeDisparo++;
					message = "Velocidade: "+ velocidadeDisparo;
					minhocaAtual.setVelocidadeDisparo(velocidadeDisparo);
					repaint();
				}else if (!estaPulando()&&!estaAtirando())//(status!=CMinhoca.PULANDO_DIREITA&&status != CMinhoca.PULANDO_ESQUERDA)
					andaEsquerda();
			} else if(gameAction == UP) {
				if (status >= 6){
					int anguloDaArma = minhocaAtual.getAnguloDisparo();
					anguloDaArma++;
					message = "Ângulo: "+ anguloDaArma;
					minhocaAtual.setAnguloDisparo(anguloDaArma);
					repaint();
				} else if (status==CMinhoca.ANDANDO_DIREITA||status==CMinhoca.ANDANDO_ESQUERDA)//pula
					pular();
				
			} else if(gameAction == DOWN) {
				int anguloDaArma = minhocaAtual.getAnguloDisparo();
				if (status>=6 && anguloDaArma > 0){
					anguloDaArma--;
					minhocaAtual.setAnguloDisparo(anguloDaArma);
					message = "Ângulo: "+ anguloDaArma;
					repaint();
				}
				minhocaAtual.setStatus(status);
			}else if (keyCode == KEY_NUM9){
				message = "Armas!!!";
				if (cArma==null)
					cArma = new CArma(this);
				cArma.mostraInterfaceEscolha(Display.getDisplay(lAtor));
				status = minhocaAtual.viradaDireita()?CMinhoca.MIRANDO_DIREITA:CMinhoca.MIRANDO_ESQUERDA;
				minhocaAtual.setStatus(status);
			}else if(keyCode == KEY_NUM0){//mudar status de atirando para andando ou vice-versa
				if (minhocaAtual.getArma()==null)
					message = "Sem Arma!!!";
				else
				if (status==CMinhoca.ARMADO_DIREITA||status==CMinhoca.ANDANDO_DIREITA){
					status = CMinhoca.MIRANDO_DIREITA;
					message="Mirando...";
				}
				else if (status==CMinhoca.ARMADO_EQUERDA||status==CMinhoca.ANDANDO_ESQUERDA){
					status = CMinhoca.MIRANDO_ESQUERDA;
					message="Mirando...";
				}else 
					status = minhocaAtual.viradaDireita()?CMinhoca.ANDANDO_DIREITA:CMinhoca.ANDANDO_ESQUERDA;
				repaint();
				minhocaAtual.setStatus(status);
			} else if(keyCode == KEY_NUM5||keyCode == -5){//atirar
				atirar();
			}
		} catch (Exception e) {
			System.out.println("Erro: "+e.getMessage());
		}
	
	}
	/**
	 * Escalona a classe Movimento para posicionar o tiro a cada 200 milisegundos
	 */
	public void atirar(){
		int status = minhocaAtual.getStatus();
		if ((status >= 6) && (status != CMinhoca.ATIRANDO_DIREITA) && (status != CMinhoca.ATIRANDO_ESQUERDA)){
			int posicao[] = minhocaAtual.getPosicao();
			posicao[1]-=3;//só pra garantir que nao vai cair no chao logo
			//inicia o movimento da bomba
			movimentoTiro = new Movimento(this,posicao,Movimento.ATIRAR,minhocaAtual.getVelocidadeDisparo(),
										  minhocaAtual.getAnguloDisparo(),minhocaAtual.viradaDireita());
			movimentoTiro.setAceleracao(minhocaAtual.getAceleracaoBomba());
			timer = new Timer();
			timer.schedule(movimentoTiro,50,150);//comece daqui a 5 mili e repita a cada 150 milisegundos a atividade
			if (status == CMinhoca.MIRANDO_DIREITA)
				status =  CMinhoca.ATIRANDO_DIREITA;
			else
				status = CMinhoca.ATIRANDO_ESQUERDA;
			posicionaBomba(minhocaAtual.getPosicaoX(),minhocaAtual.getPosicaoY()-3);
			minhocaAtual.setStatus(status);
		}
	}
	
	public void pular(){
		int status = minhocaAtual.getStatus();
		int posicao[] = minhocaAtual.getPosicao();
		posicao[1]--;//só pra garantir 
		movimentoPulo = new Movimento(this,posicao,Movimento.PULAR,
									  minhocaAtual.getVelocidadePulo(),70,minhocaAtual.viradaDireita());
		timer = new Timer();
		timer.schedule(movimentoPulo,50,200);//comece daqui a 100 mili e repita a cada 200 milisegundos a atividade
		if (status == CMinhoca.ANDANDO_DIREITA)
			status = CMinhoca.PULANDO_DIREITA;
		else
			status = CMinhoca.PULANDO_ESQUERDA;
		minhocaAtual.setStatus(status);
	}
	
	private boolean estaPulando(){
		if (movimentoPulo!=null)//se está pulando
			return movimentoPulo.estaAtiva();
		return false;	
	}
	
	private boolean estaAtirando(){
		if (movimentoTiro!=null)//se está atirando
			return movimentoTiro.estaAtiva();
		return false;//não tem nada em movimento
		
	}
	
	public void andaDireita(){
		int status = minhocaAtual.getStatus();
		int x = minhocaAtual.getPosicaoX();
		int y = minhocaAtual.getPosicaoY();
		x+=2;
		int cy = cCenario.getY(x);
		if ((cy!=-1)&&!(cy<y-5)){//se nao está no limite da tela ou se não está na frente de uma parede
			minhocaAtual.posicionaMinhoca(x,cy);
		}
		status = CMinhoca.ANDANDO_DIREITA;
		minhocaAtual.setStatus(status);
		if(somHabilitado)
			Som.getInstacia().tocaPasso();
		repaint();
	}
	
	public void andaEsquerda() {
		int status = minhocaAtual.getStatus();
		int x = minhocaAtual.getPosicaoX();
		int y = minhocaAtual.getPosicaoY();
		x-=2;
		int cy = cCenario.getY(x);
		if ((cy!=-1)&&!(cy<y-5)){//se nao está no limite da tela ou se não está na frente de uma parede
			minhocaAtual.posicionaMinhoca(x,cy);
		}
		status = CMinhoca.ANDANDO_ESQUERDA;
		minhocaAtual.setStatus(status);
		if(somHabilitado)
			Som.getInstacia().tocaPasso();
		repaint();
	}
	
	/**
	 * Método posiciona a minhoca atual com as coordenadas x e y
	 * método chamado pela classe movimento para movimentar a minhoca
	 * @param _x coordenada x
	 * @param _y coordenada y
	 */
	public void posicionaMinhoca(int _x, int _y){
		minhocaAtual.posicionaMinhoca(_x,_y);
		repaint();
	}
	
	/**
	 * Método posiciona a bomba atual com as coordenadas x e y
	 * método chamado pela classe movimento para movimentar a bomba
	 * @param _x coordenada x
	 * @param _y coordenada y
	 */
	public void posicionaBomba(int _x, int _y){
		minhocaAtual.posicionaBomba(_x,_y);
		repaint();
	}
	
	public void setArmaMinhoca(EArma arma){
		minhocaAtual.setArma(arma);
		Display.getDisplay(lAtor).setCurrent(this);
		if (somHabilitado)
			Som.getInstacia().tocaArmas();
	}
	
	public void setArmaMinhoca(String nomeArma){
		cArma.setArmaMinhoca(nomeArma);
	}	 
}