package model;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Card implements Jogada {

    protected String text;
    protected boolean color;

    public Card(String text, boolean color) {
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
