package dominio;

import estatico.PosicoesEnum;

public abstract class Propriedade {

    private Jogador dono;
    private Integer custoPropriedade;
    private Integer custoHipoteca;
    private boolean hipotecada;
    private PosicoesEnum posicaoEnum;

    public Propriedade(PosicoesEnum posicao, Integer custoPopriedade, Integer custoHipoteca) {
        this.custoHipoteca = custoHipoteca;
        this.custoPropriedade = custoPopriedade;
        this.posicaoEnum = posicao;
    }

    public Jogador getDono() {
        return dono;
    }

    public void setDono(Jogador jogador) {
        this.dono = jogador;
    }

    public PosicoesEnum getTipoPosicao() {
        return posicaoEnum;
    }

    public double custoVenda(){
        return custoPropriedade*0.50;
    }
    //
    public double custoDesipoteca(){
        return custoPropriedade*0.20;
    }

    public Integer getValor() {
        return custoPropriedade;
    }

    public abstract int calcularHospedagem(int deslocamento);


    int getValorHipoteca() {
        return custoHipoteca;
    }


    public boolean isHipotecada() {
        return hipotecada;
    }

    public void hipotecar() {
        this.hipotecada = true;
    }

    public void desipotecar() {
        this.hipotecada = false;
    }
}