
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.IOException;

public class Cenario extends ObjetoJogo {
	int var = 100;
	int posicoes[] = {140,40};
	//int posicoes[] = {100,40,90,180,-80,-110,-130,-180};	
	public Cenario() throws Exception {		
		super.img = Image.createImage("/imagens/arvore.png");
		super.img2 = Image.createImage("/imagens/arbusto.png");
		super.img3 = Image.createImage("/imagens/placa.png");
	}
	
	public void meDesenha(Graphics g, int altura, int vel){
	  /*
	  if (var > altura + 220)
	    var = -90;
	  else
		var += 5;
	  */
	  
	  for(int x=0; x<posicoes.length;x++)
	  {
			if(vel > 5)
				posicoes[x]+=vel/5;
			else if(vel != 0)
				posicoes[x]++;

			if(posicoes[x] > altura + 60)
				posicoes[x]-=330;
	  }
				
	  for(int x=0; x<posicoes.length;x++)
	  {
	  g.drawImage(super.img,5, posicoes[x], Graphics.TOP | Graphics.LEFT);
	  g.drawImage(super.img,190, posicoes[x] - 90, Graphics.TOP | Graphics.LEFT);
	  //g.drawImage(super.img2,200, posicoes[x] - 10, Graphics.TOP | Graphics.LEFT);
	  //g.drawImage(super.img2,10, posicoes[x] + 80, Graphics.TOP | Graphics.LEFT);
	  /*
	  g.drawImage(super.img,190, posicoes[x] - 180, Graphics.TOP | Graphics.LEFT);
	  g.drawImage(super.img2,10, posicoes[x] - 210, Graphics.TOP | Graphics.LEFT);
	  g.drawImage(super.img,10, posicoes[x] - 230, Graphics.TOP | Graphics.LEFT);
	  g.drawImage(super.img,10, posicoes[x] - 280, Graphics.TOP | Graphics.LEFT);
	  */
	  }
	  
	}

}
