
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.game.GameCanvas;

public class ControleJogo extends GameCanvas implements Runnable{

	protected Cenario objCenario;
	protected Veiculo jogador;
    protected Sinaleira sinal;
	protected Veiculo carrosInimigos[] = new Veiculo[18];
	protected Estrada objEstrada;
	protected int mapa[] = {col1, 90, 2, col3, 140, 3, col2, -40, 2, col3, -140, 5, col1, -200, 4, col2, -270, 3,
			                col2, -500, 5, col1, -580, 1, col2, -630, 1, col3, -730, 2, col3, -780, 4, col1, -850, 3,
			                col3, -930, 5, col2, -990, 1, col1, -1100, 1, col2, -1200, 2, col2, -1300, 4, col3, -1400, 3};
	
	private final static int 	col1 = 70,
	                            col2 = 102,
	                            col3 = 146;
	
	protected int numCarros = 18;
	protected int tempo;
	protected int cont;
	protected boolean pause = false;
	
	protected Thread thrd = new Thread (this);	;
  
	public ControleJogo(int pTipo) {
	  super(true);
	  sinal = new Sinaleira();
	  try {
		objCenario = new Cenario();
		jogador = new Veiculo(pTipo, 120,200, 120);
		objEstrada = new Estrada(jogador);
	  }
	  catch (Exception e){
	  }
	  
	  configurarInicio();
      thrd.start();
	}
	
	public void configurarInicio() {
	  int x = 0;
	  objEstrada.posChegada = -7000;
	  cont = 0;
	  jogador.setPy(200);
	  jogador.setPx(120);
	  jogador.setPosicao(numCarros + 1);
	  jogador.setVelocidade(0);
	  tempo = 400;
	  jogador.setVidas(3);
	  sinal.setEstado(0);
	  for (int i=0; i<numCarros * 3; i+=3) {
	    try{
	      carrosInimigos[x] = new Veiculo(mapa[i+2], mapa[i], mapa[i+1], 100);
	    }
	    catch (Exception e){	  
	    }
	    x += 1;
	  }  
	}
	
	public void run()
	{
		
		try
	    {
	      int sleep_time = 90;
	      cont = 0;
	     
	      
	      while (!pause)
	      {
	    	cont += 1;
	        Thread.sleep(sleep_time);
	        processaTeclas();
	        if (cont == 50)
	        	sinal.setEstado(1);
	        if (sinal.getEstado() == 1) {
	        	movimentaCarrosInimigos();
	          repaint();
	        }
	      }
	    }
	    catch(Exception e){}
	}
	
	public void movimentaCarrosInimigos(){
	  
	  
	  for (int i=0; i<numCarros; i++) {
		  carrosInimigos[i].aumentaVelocidade();
		  carrosInimigos[i].setPy((carrosInimigos[i].getPy() + -3 +((jogador.getVelocidade() - carrosInimigos[i].getVelocidade())/5)));
		  
		  if (colisaoCarros() ){
				//carrosInimigos[i].setVelocidade(30);
				jogador.setVelocidade(20);
				//jogador.diminuiVelocidade(-10);
		  }
		  
		  if (((i == 8) || ((i == 11))) && (carrosInimigos[i].getPy() > 0) && (carrosInimigos[i].getPy() < 90))
			if (carrosInimigos[i].getPx() > 145)  
			  carrosInimigos[i].setPx( carrosInimigos[i].getPx() - 30);
			else 
				carrosInimigos[i].setPx( carrosInimigos[i].getPx() + 20);
		  ultrapassou();
	  }
	  
	}
	
   public void ultrapassou(){
	  int ultrapassados = 0;
	  int jog_y = jogador.getPy();
	      
	  for (int i=0; i<numCarros; i++) {
		int ini_y = carrosInimigos[i].getPy();
		
		if (jog_y < ini_y)
		  ultrapassados += 1;
	    
	  }
	  jogador.setPosicao(numCarros - ultrapassados + 1);
   }
	
   public boolean verificaGanhador(){
	   if (jogador.getPy() < objEstrada.posChegada + 105)
		   return true;
	   return false;
   }
   
   
	public String decorrerTempo(){
		tempo -= 1;
		return String.valueOf((tempo));
	}
	
	public boolean colisaoCarros(){
	  
	  int jog_x = jogador.getPx();
      int jog_y = jogador.getPy();
      int jog_comp = jogador.getAltura();
      int jog_larg = jogador.getLargura();
      int jog_meio = (jog_larg/2);
      
	  for (int i=0; i<numCarros; i++) {
	    int ini_x = carrosInimigos[i].getPx();
		int ini_y = carrosInimigos[i].getPy();
		int ini_comp = carrosInimigos[i].getAltura();
		int ini_larg = carrosInimigos[i].getLargura();
		
	
        if ((jog_x + jog_larg >= ini_x) && (jog_x + jog_larg < ini_x + ini_larg) && (jog_y > ini_y) && (jog_y <= ini_y + ini_comp)
		    || (jog_x > ini_x) && (jog_x <= ini_x + ini_larg) && (jog_y > ini_y) && (jog_y <= ini_y + ini_comp)
			|| (jog_x + jog_larg >= ini_x) && (jog_x + jog_larg < ini_x + ini_larg) && (jog_y + jog_comp >= ini_y) && (jog_y + jog_comp < ini_y + ini_comp)
			|| (jog_x > ini_x) && (jog_x <= ini_x + ini_larg) && (jog_y + jog_comp >= ini_y) && (jog_y + jog_comp < ini_y + ini_comp)
			|| (jog_x + jog_meio > ini_x) && (jog_x + jog_meio < ini_x + ini_larg) && (jog_y <= ini_y + ini_comp) && (jog_y > ini_y)) {	  
	          return true;
	          
	    }
	    
		
	
	  }
	  return false;
	}
	
	public boolean tempoEsgotado(){
		if (tempo <= 0) {
			return true;
		}
		return false;
	}
	
	
	
	public void paint(Graphics g){
	  g.setColor(0,0,0);
	  g.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
	  if (verificaGanhador() == false) {
	    if (jogador.getVidas() == 0) {
		  g.setColor(255,255,0);
		  g.drawString("VOCE É UM FRACASSO", 50, 100, 20);  
	    }
	    else {
	      if (tempoEsgotado()){
		    g.setColor(255,255,0);
		    g.drawString("TEMPO ESGOTADO", 65, 100, 20);
	      }else { 
	        objEstrada.meDesenha(g,getWidth(),getHeight());
	        objEstrada.desenhaLinhas(g, getHeight());
	        objEstrada.desenhaChegada(g);
	        objCenario.meDesenha(g, getHeight(), jogador.velocidade);
	        jogador.meDesenha(g);
	        jogador.desenhaVidas(g, getHeight());
	        for (int i=0; i<numCarros; i++)
		      carrosInimigos[i].meDesenha(g);
	        sinal.meDesenha(g, getHeight());
	        g.setColor(255,255,0);
	        g.drawString("Tempo", 10, 5, 20);
	        g.drawString(decorrerTempo(), 12, 15, 20);
	        g.drawString("Posição", 6, 25, 20);
	        g.drawString(String.valueOf(jogador.getPosicao()), 19, 35, 20);
	        g.drawString("Vel.", 12, 45, 20);
	        g.drawString(String.valueOf(jogador.getVelocidade()), 19, 55, 20);
	      
	      }
	    }
	    } else { 
		    if (jogador.getPosicao() == 1) {
		      g.setColor(255,255,0);
		      g.drawString("1", 19, 35, 20);
		      g.drawString("VOCE É O CARA, PARABENS", 50, 100, 20);
		      pause = true;
		    }
		    else {
			  g.setColor(255,255,0);
			  g.drawString("QUE PENA, A " + jogador.getPosicao() + "º POSIÇÃO NÃO É O BASTANTE", 10, 100, 20);  
		    }
	    }
	}
	
	
	
	private void processaTeclas()
	{
		int keyStates = getKeyStates();
		

		if (((keyStates & UP_PRESSED)!=0) && (sinal.getEstado() == 1))
		{
			for (int i=0; i<numCarros; i++) {
				if (jogador.getVelocidade() > 30)
				  carrosInimigos[i].diminuiVelocidade(-1);
				//jogador.velocidade += 1;
				//jogador.velocidade = 0;
			    
				//if (colisaoCarros()){
				//	jogador.perdeuVida();	
				//	break;
				//}
			}
			if (colisaoCarros()){
				jogador.perdeuVida();
			}
			jogador.aumentaVelocidade();
		} else {
			jogador.diminuiVelocidade(-1);
		}

		if((keyStates & DOWN_PRESSED)!=0)
		{
			jogador.diminuiVelocidade(-1);
		}

		if (((keyStates & LEFT_PRESSED)!=0) && (sinal.getEstado() == 1))
		{
		  if (jogador.getPx() > 64)
			jogador.movimentaEsquerda(3);
		  
		  if (colisaoCarros()){
			jogador.perdeuVida();  
			jogador.movimentaDireita(15);
		  }  
		}

		if (((keyStates & RIGHT_PRESSED)!=0) && (sinal.getEstado() == 1))
		{
		  if (jogador.getPx() < 165)
			jogador.movimentaDireita(3);
		  
		  if (colisaoCarros()){
			  jogador.perdeuVida();
			  jogador.movimentaEsquerda(15);
		  }  
		}
		
		
			
	}


	/*
	protected void keyPressed(int keyCode) {
		int action = getGameAction(keyCode);
		
		
		
		switch (action) 
		{
		case FIRE: 
			cont = 1;
			configurarInicio();
		break;
		case DOWN: 
		  //System.out.println("Pressed the DOWN");
		break;
		case UP :
			for (int i=0; i<numCarros; i++) {
				carrosInimigos[i].diminuiVelocidade();
				jogador.velocidade = 0;
			
				if (colisaoCarros() ){
					jogador.perdeuVida();	
					break;
				}
				
				/*if (colisaoCarros()) {
					carrosInimigos[i].velocidade = 0;
					jogador.diminuiVelocidade();
					
					movimentaCarros();
					
				}
				
			
			}
		break;
		case LEFT:
			jogador.px -= 3;
		}
        
		switch (keyCode) 
		{
		
		case KEY_NUM0:
			tempo = 1000;
		break;
		case KEY_NUM1:
			tempo = 2000;
		break;
		case KEY_NUM3:
			tempo = 3000;
		break;
		
		}
		
	}
	*/
}
