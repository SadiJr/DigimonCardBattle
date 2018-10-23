/**
 * 
 */
package br.ufsc.somavetor;

/**
 * @author User
 *
 */
import java.util.Random;
public class soma implements Runnable {
	 private int sleepTime; // tempo de adormecimento aleatorio para a thread
	   private String threadName; // nome da thread
	   private static Random generator = new Random();
	   private meuvetor b; 
	   private int somador[];
	   // atribui nome a thread
	   public soma( String name )
	   {
	      threadName = name; // define o nome da thread
	        
	      // seleciona tempo de adormecimento aleatorio entre 0 e 5 segundos
	      sleepTime = generator.nextInt( 5000 );
	   } // fim construtor PrintThread 
	   public soma( String name, meuvetor c )
	   {
	      threadName = name; // define o nome da thread
	        b=c;
	        somador=new int[2];
	        somador[0]=0;
	        somador[1]=0;
	      // seleciona tempo de adormecimento aleatorio entre 0 e 5 segundos
	      sleepTime = generator.nextInt( 5000 );
	   } // fim construtor PrintThread 
	    //public int getSoma(){
	    //	return somador;
	    //}
	   // metodo run e o codigo a ser executado pela nova thread
	   public void run()
	   {
	      try // coloca a thread para dormir pela quantidade de tempo sleepTime 
	      {
	    	  
	         System.out.printf( "%s esta indo dormir por %d milissegundos.\n a soma e %d \n", 
	            threadName, sleepTime,b.getsoma1());
	            if(somador[0]==-5000){somador[0]=b.getsoma1();}else {somador[1]=b.getsoma1();}
	            int t=principal.gettotal();
	            t=t+b.getsoma1();
	            principal.settotal(t);
	            Thread.sleep( sleepTime ); // coloca a thread para dormir
	      } // fim try        
	      // se a thread eh interrompida enquanto dormia, imprime o rastreamento de pilha 
	      catch ( InterruptedException exception )
	      {
	         exception.printStackTrace();
	      } // fim catch
	        
	      // imprime o nome da thread
	      
	      System.out.printf( "\n %d foi o somatorio\n",principal.gettotal() );
	      
	   } // fim metodo run
}
