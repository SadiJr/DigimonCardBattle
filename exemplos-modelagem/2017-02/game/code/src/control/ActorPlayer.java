package control;

import network.ActorNetGames;
import model.Card;
import view.Screen;

public class ActorPlayer {

    protected ActorNetGames actorNetGames;
    protected Game game;
    protected Screen screen;
    protected String name;
    protected final String html1 = "<html><body style='width: ";
    protected final String html2 = "px'>";
    protected int playerIndex;
    protected int winner;

    public ActorPlayer() {
        //Quantidade de jogadores:
        this.game = new Game(3);
        this.screen = new Screen(this);
        this.actorNetGames = new ActorNetGames(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public ActorNetGames getActorNetGames() {
        return actorNetGames;
    }

    public void setActorNetGames(ActorNetGames actorNetGames) {
        this.actorNetGames = actorNetGames;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void handle(int i) {
        switch (i) {
            case 0:
                screen.setVisible(false);
                System.exit(0);
                break;
            case 1:
                connect();
                break;
            case 2:
                actorNetGames.startGame();
                break;
            case 3:
                makeAMove();
                break;
            case 4:
                disconnect();
                break;
        }
    }

    public void connect() {
        boolean connected = game.isConnected();
        if (!connected) {
            boolean success = actorNetGames.connect("localhost", this.name);
            if (success) {
                game.setConnected(true);
                screen.notifyConnection(0);
            } else {
                screen.notifyIrregularity(0);
            }
        } else {
            screen.notifyIrregularity(1);
        }
    }

    public void disconnect() {
        boolean connected = game.isConnected();
        if (connected) {
            boolean success = actorNetGames.disconnect();
            if (success) {
                game.setConnected(false);
                screen.notifyConnection(1);
            } else {
                screen.notifyIrregularity(2);
            }
        } else {
            screen.notifyIrregularity(3);
        }
    }

    public void startGame() {
        boolean ongoingMatch = game.isOngoingMatch();
        boolean connected = game.isConnected();
        if (!ongoingMatch && connected) {
            game.prepare();
            game.randomBlack();
            actorNetGames.sendGame(this.game);
        }
    }

    public void updateUICzar() {
        screen.getData().setText("Wait for all the players to answer.");
        for (int i = 0; i < 8; i++) {
            screen.getWhiteCards().get(i).setText("");
        }
        for (int i = 0; i < game.getAnswers().size(); i++) {
            screen.getWhiteCards().get(i).setText(html1 + "100" + html2 + game.getAnswers().get(i).getText());
        }
    }

    public void updateUIPleb() {
        for (int i = 0; i < 8; i++) {
            screen.getWhiteCards().get(i).setText(html1 + "100" + html2 + game.getActualPlayer(playerIndex).getDeck().get(i).getText());
        }
    }

    public void updateInterface() {        
        if (!game.end) {
            screen.setBlackCard(html1 + "100" + html2 + game.blackCard.getText());
            screen.updatePoints(this.getGame().getActualPlayer(this.getPlayerIndex()).getPoints());
            screen.updateRound(this.getGame().getRound());
            if (this.playerIndex == game.getCurrentCzar()) {
                updateUICzar();
            } else {
                updateUIPleb();
            }
        } else {
            screen.showWinner(game.players[game.championPosition].getName());
        }
    }

    public void czarMove(int choice) {
        String cardText = game.answers.get(choice).getText();
        for (int i = 0; i < game.players.length; i++) {
            for (int j = 0; j < game.players[i].getUsedCards().size(); j++) {
                if (cardText.equals(game.players[i].getUsedCards().get(j).getText())) {
                    winner = i;
                }
            }
        }
        game.updateRound(winner);
        screen.getData().setText("");
    }

    public void plebMove(int choice) {
        Card aux = game.getActualPlayer(playerIndex).getDeck().get(choice);
        screen.getData().setText("You chose your answer!\n" + " " + aux.getText());
        game.getActualPlayer(playerIndex).getUsedCards().add(aux);
        game.answers.add(aux);
        game.whiteCards.remove(aux);
        game.getActualPlayer(playerIndex).getDeck().set(choice, game.randomWhite());
        actorNetGames.sendGame(this.game);
    }

    public void makeAMove() {
        if (this.playerIndex != game.getCurrentPosition()) {
            screen.notifyIrregularity(5);
            return;
        }
        int choice = screen.getChoice();
        if (this.playerIndex == game.getCurrentCzar() && game.answers.size() == game.players.length - 1) {
            czarMove(choice);
            sendGame();
        } else if (this.playerIndex != game.getCurrentCzar()) {
            plebMove(choice);
            sendGame();
        }
    }

    private void sendGame() {
        game.getActualPlayer(playerIndex).setName(name);
        game.updateCurrent();
        actorNetGames.sendGame(this.game);
    }

}
