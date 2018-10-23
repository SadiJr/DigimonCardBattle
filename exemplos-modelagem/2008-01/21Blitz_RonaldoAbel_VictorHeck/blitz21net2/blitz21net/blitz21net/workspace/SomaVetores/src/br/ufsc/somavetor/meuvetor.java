package br.ufsc.somavetor;
import java.util.Random;
public class meuvetor {
	private int v[];
	private int tam=5;
	private static Random generator = new Random();
	public meuvetor(){
	v=new int[tam];
	for (int cont=0;cont<v.length;cont++)
		v[cont]=generator.nextInt( 10 );
	
	}
	public int getpos(int pos){
		return v[pos];
	}
	public int gettam(){
		return tam;
	}
	public int getsoma1(){
		int x=0;
		for (int cont=0;cont<this.gettam();cont++){
	    	 x=x+ this.getpos(cont); }
	    	  
		return x;
	}
	

}
