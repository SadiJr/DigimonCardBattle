package worms;

import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Abertura extends Canvas{
	
	private String abertura;
	
	public Abertura(String imagemAbertura){
		abertura = imagemAbertura;
	}
	public void paint(Graphics g) {
		setFullScreenMode(true);
		try{
			g.setColor(0);
			g.fillRect(0,0,getWidth(),getHeight());
			g.drawImage(Image.createImage(abertura),getWidth()/2,getHeight()/2,Graphics.VCENTER|Graphics.HCENTER);
		}catch (IOException e) {
			System.out.println("Imagem não encontrada: "+abertura);
		}
	} 
	
	

}
