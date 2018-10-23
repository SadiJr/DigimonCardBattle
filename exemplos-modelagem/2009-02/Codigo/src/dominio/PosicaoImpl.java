package dominio;

import estatico.PosicoesEnum;

public class PosicaoImpl implements Posicao {

    private PosicoesEnum posicaoEnum;

    public PosicaoImpl(PosicoesEnum posicao) {
        this.posicaoEnum = posicao;
    }
    
    public PosicoesEnum getTipoPosicao() {
        return posicaoEnum;
    }    
}
