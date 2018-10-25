package cah;

import control.ActorPlayer;
import view.Screen;

public class Main {

    public static void main(String[] args) {
        ActorPlayer actorPlayer = new ActorPlayer(); 
        Screen screen = new Screen(actorPlayer);
        actorPlayer.setScreen(screen);
        screen.visible();
    }   
}