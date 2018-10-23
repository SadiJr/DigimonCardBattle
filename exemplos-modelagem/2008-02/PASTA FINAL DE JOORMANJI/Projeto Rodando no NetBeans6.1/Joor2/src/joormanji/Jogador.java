package joormanji;

/**
 *
 * @author JooRmanji
 */
public class Jogador {

    private String nome;

    private boolean vez;
    
    private int posicaoAtual;

    
    public String getNome() {
            return nome;
    }

    public Jogador(String nome) {
            super();
            this.nome = nome;
            this.posicaoAtual = 0;
            this.vez = false;
    }

    public void tomarVez(){
            vez = true;
    }

    public void passarVez(){
            vez = false;
    }

    public boolean ehVez(){
            return vez;
    }

    public int getPosicao() {
        return posicaoAtual;
    }

    public void setPosicao(int posicao) {
        this.posicaoAtual = posicao;
    }

}
