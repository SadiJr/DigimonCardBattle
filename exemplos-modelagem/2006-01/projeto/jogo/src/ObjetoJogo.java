import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public abstract class ObjetoJogo {
	protected int px;
	protected int py;
	protected int largura;
	protected int altura;
    protected Image img , img2, img3;

  public int getPx() {
    return px;
  }
 
  public void setPx(int x) {
    px = x;
  }

  public int getPy() {
    return py;
  }

  public void setPy(int y) {
    py = y;
  }

  public int getAltura() {
    return altura;
  }

  public int getLargura() {
    return largura;
  }
  

}