package model;

import br.ufsc.inf.leobr.cliente.Jogada;
import java.util.ArrayList;

public class Player implements Jogada {

    protected int id;
    protected ArrayList<Card> deck;
    protected ArrayList<Card> usedCards;
    protected boolean czar;
    protected String name;
    protected int points = 0;
    protected Card choice;
    protected boolean answered;
    protected boolean winner;

    public Player() {
        deck = new ArrayList<>();
        usedCards = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public boolean isCzar() {
        return czar;
    }

    public void setCzar(boolean czar) {
        this.czar = czar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Card getChoice() {
        return choice;
    }

    public void setChoice(Card choice) {
        this.choice = choice;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public ArrayList<Card> getUsedCards() {
        return usedCards;
    }

    public void setUsedCards(ArrayList<Card> usedCards) {
        this.usedCards = usedCards;
    }

}
