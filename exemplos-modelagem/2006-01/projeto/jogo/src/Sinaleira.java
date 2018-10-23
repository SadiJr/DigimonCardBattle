
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class Sinaleira extends ObjetoJogo {
  
	protected int estado; // 0 significa vermelho
	                      // 1 significa que começou corrida

    
	public Sinaleira(){
		estado = 0;
	}
	
	public int getEstado(){
		return estado;
	}
	
	public void setEstado(int pEstado){
		estado = pEstado;
	}
	
	public void meDesenha(Graphics g, int altura){
	 if (estado == 0) {
		 g.setColor(0,0,0); 
		g.fillRect(100, altura- 27, 38, 14);
		g.setColor(0xFF0000); // VERMELHO
		g.fillArc(102,altura - 25,10,10,0,360);
		g.fillArc(114,altura - 25,10,10,0,360);
		g.fillArc(126,altura - 25,10,10,0,360);
	 
	 }else {
	 
	 }
	}

}
