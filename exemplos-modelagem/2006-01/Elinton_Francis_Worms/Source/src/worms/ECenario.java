package worms;

import java.io.*;
import javax.microedition.lcdui.*;

public class ECenario {
	
	private String imagemName = "/cenario.PNG";
	
	private Image imagem = null;
	
	private int chaoMap[][] = null;
	
	private int corFundo = 0x00FFFF;
	
	public ECenario(String nome){
		imagemName = nome;		
	}
	
	public void setChaoMap(int[][] pMap){
		chaoMap = pMap;
	}
	
	public void setImagem(Image pImagem){
		imagem = pImagem;		
	}
	
	public void setImageName(String pName){
		imagemName = pName;		
	}
	
	public void setCorFundo(int cor){
		corFundo = cor;
	}
	
	public int getCorFundo(){
		return corFundo;
	}
	
	public Image getImagem(){
		return imagem;
	}
	
	public int[][] getChaoMap(){
		return chaoMap;		
	}
	
	
	
	

}
 
