import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.io.IOException;

public class Veiculo extends ObjetoJogo {

	protected int tipo, vidas;
	protected int vel_max, velocidade, posicao;
	
	

  
  public Veiculo(int pTipo, int pPx, int pPy, int pVel_Max) throws Exception {
	if (pTipo == 1)
	   super.img = Image.createImage("/imagens/carro1.png");
	if (pTipo == 2)
	   super.img = Image.createImage("/imagens/carro2.png");
	if (pTipo == 3)
	   super.img = Image.createImage("/imagens/carro3.png");
	if (pTipo == 4)
	   super.img = Image.createImage("/imagens/carro4.png");
	if (pTipo == 5)
	   super.img = Image.createImage("/imagens/caminhao.png");
	super.img2 = Image.createImage("/imagens/vida.png");
	this.tipo = pTipo;
	super.px = pPx;
	super.py = pPy;
	super.altura = super.img.getHeight();
	super.largura = super.img.getWidth();
	this.vel_max = pVel_Max;
	this.velocidade = 0;
	this.posicao = 19;
	vidas = 3;
  }  


public void aumentaVelocidade() {
    velocidade += 1;
	if (velocidade >= vel_max)
      velocidade = vel_max;	
  }
	
  public int getVelocidade(){
    return velocidade;
  }
  
  public void diminuiVelocidade(int pVel) {
	velocidade = velocidade + pVel;
	//velocidade -= 3;
	//if (velocidade <= -60)
    //  velocidade = -60;	
	if (velocidade <= 0)
	      velocidade = 0;
  }
  /*
  public void diminui(){
	  velocidade -= 100;
		if (velocidade <= -100)
	      velocidade = -100;	
  }
  */	  
  public void setPosicao(int pPosicao) {
    posicao = pPosicao;
    if (posicao == 0)
    	posicao = 1;
  }
	  
  public int getPosicao() {
    return posicao;
  }
	  
  public int getTipo() {
    return tipo;
  }
	  
  
  public void perdeuVida(){
	vidas -= 1;
	if (vidas < 0)
		vidas = 0;
  }
	  
  public void meDesenha(Graphics g) {
    g.drawImage(super.img, super.px, super.py,  Graphics.TOP | Graphics.LEFT);
  }
  
  public void setVelocidade(int pVel){
	  velocidade = pVel;
  }
  
  public void setVidas(int pVidas){
	  vidas = pVidas;
  }
  
  public int getVidas(){
	  return vidas;
  }
  
  public void movimentaDireita(int pPx){
	  super.px += pPx;
  }
  
  public void movimentaEsquerda(int pPx){
	  super.px -= pPx;
  }
  
  public void desenhaVidas(Graphics g, int altura){
	if (vidas == 3) {
	g.drawImage(super.img2,-8, altura - 23, Graphics.TOP | Graphics.LEFT);
	g.drawImage(super.img2,12, altura - 23, Graphics.TOP | Graphics.LEFT);
	g.drawImage(super.img2,32, altura - 23, Graphics.TOP | Graphics.LEFT);
	} else {
		if (vidas == 2) {
		  g.drawImage(super.img2,-8, altura - 23, Graphics.TOP | Graphics.LEFT);
		  g.drawImage(super.img2,12, altura - 23, Graphics.TOP | Graphics.LEFT);  
		}else {
			if (vidas == 1)
		      g.drawImage(super.img2,-8, altura - 23, Graphics.TOP | Graphics.LEFT);
			
		}
	}
  }
  
  
  
  
}
