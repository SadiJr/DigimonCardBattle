import java.io.FileNotFoundException;

import com.google.gson.stream.MalformedJsonException;

import controll.TableController;
import model.Player;
import model.Table;

public class Main {

	public static void main(String[] args) {
		Table t = new Table();
		t.setLocalPlayer(new Player("Sadi", new Integer(1)));
		t.setRemotePlayer(new Player("Sado", new Integer(0)));
		try {
			t.createDeck();
			t.distributeCards();
		} catch (FileNotFoundException | MalformedJsonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
