import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaCustom implements Jogada {

    private static final long serialVersionUID = 1L;
    private Pista pista;
    private Jogador jogador;

    public Pista getPista() {
        return pista;
    }

    public void setPista(Pista pista) {
        this.pista = pista;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }
}