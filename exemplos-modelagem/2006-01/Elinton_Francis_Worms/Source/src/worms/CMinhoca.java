package worms;

import javax.microedition.lcdui.Image;

public class CMinhoca {
	 
	private EMinhoca eMinhoca;
	
	public static int ANDANDO_DIREITA   = 0;
	public static int ANDANDO_ESQUERDA  = 1;
	public static int PULANDO_DIREITA   = 2;
	public static int PULANDO_ESQUERDA  = 3;
	public static int ARMADO_DIREITA    = 4;
	public static int ARMADO_EQUERDA    = 5;
	public static int MIRANDO_DIREITA   = 6;
	public static int MIRANDO_ESQUERDA  = 7;
	public static int ATIRANDO_DIREITA  = 8;
	public static int ATIRANDO_ESQUERDA = 9;
	
	private int status  = 	ANDANDO_DIREITA;
	 
	public CMinhoca(String nome){
		eMinhoca = new EMinhoca(nome);
		createImages("/minhoca.PNG");//cria as duas imagens da mesma minhoca
	}
	
	private void createImages(String imageName){
		Image image_right=null, image_left=null;
		try{
			image_left = Image.createImage(imageName);
			image_right = inverteImagem(image_left);	
		}catch (NullPointerException e) {
			System.out.println("erro ao criar imagens: "+e.getMessage());
		}catch (Exception e) {
			System.out.println("imagem não encontrada "+imageName);
		}
		eMinhoca.setImages(image_right,image_left);
	}
	
	private Image inverteImagem(Image image){
		if (image==null)return null;
		int rgb[];
		int newRGB[]=null;
		rgb = new int[image.getWidth()*image.getHeight()];
		newRGB = new int[image.getWidth()*image.getHeight()+1];
		image.getRGB(rgb,0,image.getWidth(),0,0,image.getWidth(),image.getHeight());
		for (int i = 0; i < image.getHeight(); i++)
			for (int j = 0; j < image.getWidth();j++)
				newRGB[i*image.getWidth()+(image.getWidth()-(j+1))] = rgb[i*image.getWidth()+j];
		return Image.createRGBImage(newRGB,image.getWidth(),image.getHeight(),true);		
	}
	
	public void setStatus(int status){
		this.status = status;
	}
		
	public void setDemage(int demage){
		eMinhoca.setDemage(demage);
	}
	
	public void setArma(EArma arma) {
		eMinhoca.setArma(arma);
	}
	
	public void posicionaMinhoca(int x, int y){
		eMinhoca.setPosicaoX(x);
		eMinhoca.setPosicaoY(y);
	}
	
	public void setAnguloDisparo(int angulo){
		if (eMinhoca.getArma()!=null)
			eMinhoca.getArma().setAnguloDisparo(angulo);
	}
	
	public void setVelocidadeDisparo(int velocidade){
		if (eMinhoca.getArma()!=null)
			eMinhoca.getArma().setVelocidadeDisparo(velocidade);
	}	
	
	public int getStatus(){
		return status;
	}
	
	public int getPosicaoX(){
		return eMinhoca.getPosicaoX();
	}
	
	public int getPosicaoY(){
		return eMinhoca.getPosicaoY();
	}
	
	public int getVelocidadePulo(){
		return eMinhoca.getJump();
	}
	
	public int getQuantidadeVida(){
		return eMinhoca.getLife();
	}
	
	public Image getImage(){
		if(viradaDireita())
			return eMinhoca.getImagem();
		return eMinhoca.getImageLeft();
	}
	
	public int getAnguloDisparo(){
		if (eMinhoca.getArma()!=null)
			return eMinhoca.getArma().getAnguloDisparo();
		return 0;
	}
	
	public int getVelocidadeDisparo(){
		if (eMinhoca.getArma()!=null)
			return eMinhoca.getArma().getVelocidadeDisparo();
		return 0;
	}
	
	public Image getArma(){
		if (eMinhoca.getArma()!=null){
			if (viradaDireita())
				return eMinhoca.getArma().getImagem();
			return inverteImagem(eMinhoca.getArma().getImagem());
		}
		return null;
	}
	
	public Image getArma15graus(){
		if (eMinhoca.getArma()!=null){
			if (viradaDireita())
				return eMinhoca.getArma().getImagem15graus();
			return inverteImagem(eMinhoca.getArma().getImagem15graus());
		}
		return null;
	}
	
	public Image getArma30graus(){
		if (eMinhoca.getArma()!=null){
			if (viradaDireita())
				return eMinhoca.getArma().getImagem30graus();
			return inverteImagem(eMinhoca.getArma().getImagem30graus());
		}
		return null;
	}
	
	public Image getArma45graus(){
		if (eMinhoca.getArma()!=null){
			if (viradaDireita())
				return eMinhoca.getArma().getImagem45graus();
			return inverteImagem(eMinhoca.getArma().getImagem45graus());
		}
		return null;
	}
	
	public Image getArma60graus(){
		if (eMinhoca.getArma()!=null){
			if (viradaDireita())
				return eMinhoca.getArma().getImagem60graus();
			return inverteImagem(eMinhoca.getArma().getImagem60graus());
		}
		return null;
	}
	
	public Image getArma75graus(){
		if (eMinhoca.getArma()!=null){
			if (viradaDireita())
				return eMinhoca.getArma().getImagem75graus();
			return inverteImagem(eMinhoca.getArma().getImagem75graus());
		}
		return null;
	}
	
	public Image getBomba(){
		if (eMinhoca.getArma()!=null){
			if (viradaDireita())
				return eMinhoca.getArma().getBomba().getImagem();
			return inverteImagem(eMinhoca.getArma().getBomba().getImagem());
		}
		return null;
	}
	
	
	public void posicionaBomba(int _x, int _y){
		if (eMinhoca.getArma()!=null)
			eMinhoca.getArma().getBomba().setPosicao(_x,_y);
	}
	
	public int[] getPosicaoBomba(){
		int posicao[]= {0,0};
		if (eMinhoca.getArma()!=null)
			return eMinhoca.getArma().getBomba().getPosicao();
		return posicao;//se informar esse é porque deu merda 
	}
	
	public int getContagemRegressivaBomba(){
		if (eMinhoca.getArma()!=null)
			return eMinhoca.getArma().getBomba().getContagemRegressiva();
		return 0;
	}
	
	public int getAceleracaoBomba(){
		if (eMinhoca.getArma()!=null)
			return eMinhoca.getArma().getBomba().getAceleracao();
		return 0;
	}
	
	public int getSteep(){
		return eMinhoca.getSteep();
	}
	
	public String getNome(){
		return eMinhoca.getNome();
	}
	
	public boolean viradaDireita(){
		return (status%2==0);
	}
	
	public int[] getPosicao(){
		int posicao[] = {getPosicaoX(),getPosicaoY()};
		return posicao;
	}
}
 
