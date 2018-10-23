/**
 * 
 */
package br.ufsc.somavetor;

/**
 * @author Ronaldo José Abel Mat 06238044
 * @author Debora Comochina  Mat 
 *
 */
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
public class principal {
	   public static int total=0;
	/**
	 * @param args
	 */
	   public static void settotal(int x){
		   total=x;
	   }
	   public static int gettotal(){
		   return total;
	   }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 // cria e nomeia cada thread
	      int s1=0;
	      int s2=0;
	    
	      int sm=0;
		  meuvetor v1= new meuvetor();
	      meuvetor v2= new meuvetor();
	      soma thread1 = new soma( "thread1",v1 );
	      soma thread2 = new soma( "thread2",v2 );
	      System.out.println( "Iniciando threads" );

	      // cria ExecutorService para gerenciar threads
	      ExecutorService threadExecutor = Executors.newCachedThreadPool();

	      // inicia threads e as coloca no estado executavel
	      threadExecutor.execute( thread1 );
	      threadExecutor.execute( thread2 ); // inicia thread2
          //s1=thread1.getSoma();
          //s2=thread2.getSoma();
	      
          threadExecutor.shutdown(); // encerra threads trabalhadoras
	        
	      System.out.println( "Threads iniciaram, main terminou\n" );
	      for (int cont=0;cont<v1.gettam();cont++){
	    	  System.out.println( ""+ v1.getpos(cont)+"valores passados para Thread 1" );
	    	  sm=sm+ v1.getpos(cont);
	      }
	      for (int cont=0;cont<v2.gettam();cont++){
	    	  System.out.println( ""+ v2.getpos(cont)+"valores passados para Thread 2" ); 
	    	  sm=sm+ v2.getpos(cont);
	      }
	     // total=s1+s2;
	      System.out.printf( "\n O valor da variavel  total agora é =  %d  A soma dos Valores dos vetores sao %d.\n", total,sm );
	      }

	}


