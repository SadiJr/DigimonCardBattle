/**
 * 
 */
package worms;

import java.io.InputStream;
import java.util.Random;
import javax.microedition.media.Player;

/**
 * @author Elinton
 *
 */
public class Som implements Runnable {

	private Player player;
	
	private Thread thread;
	
	private final String EXPLOSAO = "/Explosion.amr";
	
	private final String PASSO = "/Passo.amr";
	
	private final String CONSEGUI = "/Consegui";
	
	private final String ERREI = "/Errei";
	
	private final String PIQUE = "/piqueGranada.amr";
	
	private final String ARMAS = "/Armas.amr";
	
	private static Som instancia = null;
	
	private Fila filaSoms = new Fila(3);
	
	private Som(){
		
	}
	 
	/**
	 * Singleton Parttener
	 * @return instacia da classe
	 */
	public static Som getInstacia(){
		if (instancia==null)
			instancia = new Som();
		return instancia;
	}
	
	/* *
	 * @see java.util.TimerTask#run()
	 */
	public void run() {
		String somNome = "";
		try {
			while (!filaSoms.filaVazia()){//enquanto a fila não estiver vazia toca
				somNome = filaSoms.saida();
				InputStream io = getClass().getResourceAsStream(somNome);
				player = javax.microedition.media.Manager.createPlayer(io,"audio/amr");//x-wav
				player.start();
				Thread.currentThread().sleep(player.getDuration()/1000+800);//800 pra garantir que vai dar tempo entre um som e o outro
				player.stop();
				player.close();		
			}
		} catch (Exception e) {
			System.out.println("erro ao dar play no som "+somNome+". Erro: "+e.getMessage());
		}
	}
	
	public void tocaExplosao(){
		try {
			filaSoms.entrada(EXPLOSAO);
			if (thread==null ||!thread.isAlive()){//se nao estiver rodando
				thread = new Thread(this);
				thread.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	
	public void tocaConsegui(){
		try {
			Random aleatorio = new Random(System.currentTimeMillis());
			filaSoms.entrada(CONSEGUI+Math.abs(aleatorio.nextInt()%9)+".amr");
			if (thread==null || !thread.isAlive()){
				thread = new Thread(this);
				thread.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void tocaErrei(){
		try {
			Random aleatorio = new Random(System.currentTimeMillis());
			filaSoms.entrada(ERREI+Math.abs(aleatorio.nextInt()%3)+".amr");
			if (thread==null || !thread.isAlive()){
				thread = new Thread(this);
				thread.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void tocaPasso(){
		try {
//			se a fila estiver vazia ou se o ultimo nao for um PASSO inclui na fila
			if (filaSoms.filaVazia()||!filaSoms.ultimo().equals(PASSO))
				filaSoms.entrada(PASSO);
			if (thread==null || !thread.isAlive()){
				thread = new Thread(this);
				thread.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}				
	}
	
	public void tocaGranadaPique(){
		try {
//			se a fila estiver vazia ou se o ultimo nao for um PIQUE inclui na fila
			if (filaSoms.filaVazia()||!filaSoms.ultimo().equals(PIQUE))
				filaSoms.entrada(PIQUE);
			if (thread==null || !thread.isAlive()){
				thread = new Thread(this);
				thread.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}				
	}
	
	public void tocaArmas(){
		try {
			filaSoms.entrada(ARMAS);
			if (thread==null ||!thread.isAlive()){
				thread = new Thread(this);
				thread.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}

}
