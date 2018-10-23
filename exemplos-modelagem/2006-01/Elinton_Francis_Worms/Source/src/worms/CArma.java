package worms;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Image;

public class CArma {
 	 	 
	private LArma lArma;
	 
	private CPrincipal cPrincipal;
	
	private String armas[] = {"LancaMissel","Granada"};
	
	public CArma(CPrincipal cPrincipal) {
		this.cPrincipal = cPrincipal;
		
	}
	 
	public void mostraInterfaceEscolha(Display d) {
		lArma = new LArma(armas,this);
		lArma.mostraMenu(d);
	}
	 
	public void setArmaMinhoca(String nomeArma) {
		EArma arma = null;
		try{
			if (nomeArma.equals(armas[0])){
				Image imagem =     Image.createImage("/"+nomeArma+".PNG");
				Image imagemBomba= Image.createImage("/Missel.PNG");
				Image img15graus = Image.createImage("/"+nomeArma+"15.PNG");
				Image img30graus = Image.createImage("/"+nomeArma+"30.PNG");
				Image img45graus = Image.createImage("/"+nomeArma+"45.PNG");
				Image img60graus = Image.createImage("/"+nomeArma+"60.PNG");
				Image img75graus = Image.createImage("/"+nomeArma+"75.PNG");
				arma = new ELancaMissel(imagem,1,new EMissel(imagemBomba,6,5),img15graus,img30graus,img45graus,img60graus,img75graus);
			}else if (nomeArma.equals(armas[1])){
				Image imagem = Image.createImage("/Granada.PNG");
				arma = new ELancaGranada(imagem,1,new EGranada(imagem,1,5));
			}
			}catch (Exception e) {
			System.out.println("imagem não enontrada "+nomeArma);
		}
		cPrincipal.setArmaMinhoca(arma);
	}
	
	public void voltaProJogo(Display d){
		d.setCurrent(cPrincipal);
	}
	 
}
 
