import java.io.FileNotFoundException;

import com.google.gson.stream.MalformedJsonException;

import controll.TableController;
import model.Player;

public class Main {

	public static void main(String[] args) {
		TableController tr = new TableController();
		tr.getTable().setLocalPlayer(new Player("Sadi", new Integer(1)));
		tr.getTable().setRemotePlayer(new Player("Sado", new Integer(0)));
		try {
			tr.getTable().createDeck();
			tr.getTable().distributeCards();
			tr.getPlayer().init();
		} catch (FileNotFoundException | MalformedJsonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
