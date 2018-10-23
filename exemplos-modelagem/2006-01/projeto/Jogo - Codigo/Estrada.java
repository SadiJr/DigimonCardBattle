
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.IOException;

public class Estrada extends ObjetoJogo {
	protected int posChegada = -7000;
	protected int linhas[] = {300,250,200,150,100,50,0,-50,-100,-150,-200,-250,-300,-350};
	protected int bordas[] = {30,25,20,15,10,5,0,-5,-10,-15,-20,-25,-30};
	protected Veiculo carro;
	
	public Estrada (Veiculo pCarro) throws Exception {
		carro = pCarro;
		super.img = Image.createImage("/imagens/chegada.png");
	}
	
	public void desenhaBordas(Graphics g, int altura){
		int vel = carro.getVelocidade();
		for(int x=0; x<bordas.length;x++)
		{
			if(vel > 5)
				bordas[x]+=vel/5;
			else if(vel != 0)
				bordas[x]++;

			if(bordas[x] > altura )
				bordas[x]-=300;
		}		
		
		for(int x=0; x<bordas.length;x++)
		{
			g.setColor(255,255,255);
			g.fillRect(63,bordas[x],3,5);
			g.fillRect(165,bordas[x],3,5);
			g.setColor(0xFF0000);
			g.fillRect(63,bordas[x] + 100,3,5);
			g.fillRect(165,bordas[x] + 100,3,5);
		}
	}
	
	public void desenhaLinhas(Graphics g, int altura){
		int vel = carro.getVelocidade();
		for(int x=0; x<linhas.length;x++)
		{
			if(vel > 5)
				linhas[x]+=vel/5;
			else if(vel != 0)
				linhas[x]++;

			if(linhas[x] > altura )
				linhas[x]-=300;
		}		
		
		g.setColor(255,255,255);
		for(int x=0; x<linhas.length;x++)
		{
			g.fillRect(118,linhas[x],3,20);
		}
		
	  /*
	  g.setColor(255,255,0);
      for (int i = -250; i< altura; i+=30) {
    	if (var > altura)
    	  var = 0;
    	else
    	  var += 1;
	    g.fillRect(118, i + var , 3, 20);
      }
     */
	}
	
	public void meDesenha(Graphics g, int largura, int altura){
	  g.setColor(168,168,168);
      g.fillRect(0,0,largura,altura);
      g.setColor(000,114,000);
      g.fillRect(0,0, 64, altura);
      g.fillRect(180, 0, 60, altura);
      g.fillRect(5,10,25,Graphics.LEFT|Graphics.TOP);
	}
	
	
	
	public void desenhaChegada(Graphics g){
		//if (posChegada == 300){
		  int vel = carro.getVelocidade(); 
		  
		  if(vel > 5)
			  posChegada += vel/5;
		  else if(vel != 0)
			  posChegada++;
		
		  g.drawImage(super.img,50, posChegada, Graphics.TOP | Graphics.LEFT);
		//}
	}

	
}
