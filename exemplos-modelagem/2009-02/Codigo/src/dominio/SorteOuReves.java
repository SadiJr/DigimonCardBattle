package dominio;

import estatico.PosicoesEnum;
import estatico.TipoSorteReves;

public class SorteOuReves implements Posicao {
    private PosicoesEnum posicaoEnum;


	private static CartaSorteOuReves[] cartas = {
            new CartaSorteOuReves(TipoSorteReves.SAIDA_LIVRE,0,"Saída Livre da Prisão"),
            new CartaSorteOuReves(TipoSorteReves.PRISAO,0,"Va para a Prisão"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,500,"Você ganhou"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,200,"Você ganhou"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,100,"Você ganhou"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,100,"Você ganhou"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,100,"Você ganhou"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,100,"Você ganhou"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,50,"Você ganhou"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,150,"Você ganhou"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,150,"Você ganhou"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,150,"Você ganhou"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,250,"Você ganhou"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,300,"Você ganhou"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,50,"Você ganhou"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,100,"Você ganhou"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,200,"Você ganhou"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,100,"Você ganhou"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,50,"Você ganhou"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,100,"Você ganhou"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,-300,"Você perdeu"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,-200,"Você perdeu"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,-100,"Você perdeu"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,-100,"Você perdeu"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,-100,"Você perdeu"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,-50,"Você perdeu"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,-50,"Você perdeu"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,-50,"Você perdeu"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,-150,"Você perdeu"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,-100,"Você perdeu"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,-100,"Você perdeu"),
            new CartaSorteOuReves(TipoSorteReves.MONEY,-50,"Você perdeu"),
    };
    public SorteOuReves() {
        this.posicaoEnum = PosicoesEnum.SORTE_REVES;
    }

    public PosicoesEnum getTipoPosicao() {
        return posicaoEnum;
    }

    public static CartaSorteOuReves sortearCarta() {
        int carta = (int)(Math.random()*cartas.length);
        return cartas[carta];
    }
}
