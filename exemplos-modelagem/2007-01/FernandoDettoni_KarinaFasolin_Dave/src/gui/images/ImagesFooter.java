package gui.images;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImagesFooter {
	protected BufferedImage win, gun2, jetpackPlac, jetpackFuel;
	
public ImagesFooter(){
	try {
		this.win = ImageIO.read(this.getClass().getResource("/imagens/win.jpg"));
		this.gun2 = ImageIO.read(this.getClass().getResource("/imagens/gun2.gif"));
		this.jetpackPlac = ImageIO.read(this.getClass().getResource("/imagens/jetpackPlac.gif"));
		this.jetpackFuel = ImageIO.read(this.getClass().getResource("/imagens/jetpackFuel.gif"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	public BufferedImage getWin() {
		return this.win;
	}

	public BufferedImage getGun2() {
		return this.gun2;
	}


	public BufferedImage getJetpackPlac() {
		return this.jetpackPlac;
	}


	public BufferedImage getJetpackFuel() {
		return this.jetpackFuel;
	}
}
