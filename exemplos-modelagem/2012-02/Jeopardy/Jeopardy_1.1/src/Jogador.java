import br.ufsc.inf.leobr.cliente.Jogada;

public class Jogador implements Jogada {

    private static final long serialVersionUID = 1L;
    private String nome;
    private int posicao;
    private boolean daVez;
    private int pontos;

    public Jogador() {
        this.posicao = 0;
        this.daVez = false;
        this.nome = null;
        this.pontos = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public boolean venceu() {
        return this.posicao >= 12;
    }

    public boolean isDaVez() {
        return daVez;
    }

    public void setDaVez(boolean daVez) {
        this.daVez = daVez;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
}