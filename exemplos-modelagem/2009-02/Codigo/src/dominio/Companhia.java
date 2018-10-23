package dominio;

import estatico.PosicoesEnum;

public class Companhia extends Propriedade implements Posicao {

    private int pontos;

    public Companhia(PosicoesEnum posicoesEnum, int valor, int pontos, int hipoteca){
        super(posicoesEnum,valor,hipoteca);
        this.pontos = pontos;
    }


    public int calcularHospedagem(int deslocamento) {
        return pontos*deslocamento;
    }

}