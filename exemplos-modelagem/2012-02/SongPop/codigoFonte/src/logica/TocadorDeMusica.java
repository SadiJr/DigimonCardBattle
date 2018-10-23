package logica;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class TocadorDeMusica {
	
	private class ThreadTocadora extends Thread {
		protected Player player;
		
		public ThreadTocadora(Player player) {
			this.player = player;
		}
		
		public void pararMusica() {
			player.close();			
		}
		
		public void run() {
			try {
				player.play();
			} catch (JavaLayerException e) {
				
			}	
		}
	}
	
	protected GerenciadorDeMusicas gerenciadorDeMusicas;
	protected boolean isMusicaTocando;
	private ThreadTocadora thread;
	
	public TocadorDeMusica(GerenciadorDeMusicas gerenciadorDeMusicas) {
		this.gerenciadorDeMusicas = gerenciadorDeMusicas;
		isMusicaTocando = false;
	}
	
	public boolean isMusicaTocando () {
		return isMusicaTocando;
	}
		
	public void tocarMusica(int idMusica) throws FileNotFoundException {
		InputStream in = getFile(idMusica);
		Player player;
		try {
			player = new Player(in);
			thread = new ThreadTocadora(player);
			thread.start();
			isMusicaTocando = true;
		} catch (JavaLayerException e) {
			/* não deverá ocorrer */
		}
	}
	
	public void pararMusica() {
		thread.pararMusica();
		isMusicaTocando = false;
	}

	protected InputStream getFile(int idMusica) throws FileNotFoundException {
		Musica m = gerenciadorDeMusicas.getMusica(idMusica);
		GrupoMusical g = m.getGrupoMusical();
		String nomeGenero = g.getGeneroMusical().toString();
		String nomeGrupo = g.getNome().replace("/", "");
		String nomeMusica = m.getNome().replace("?", "");
		String path = System.getProperty("user.dir")+"\\audio\\"+nomeGenero+"\\"+nomeGrupo+" - "+nomeMusica+".MP3";	
		InputStream in = new FileInputStream(path);
		return in;
	}
}