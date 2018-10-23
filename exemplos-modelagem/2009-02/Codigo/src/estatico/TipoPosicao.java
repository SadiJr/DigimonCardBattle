package estatico;

public enum TipoPosicao {

     PARTIDA(0),SORTE_REVES(1),
    PRISAO(2),AMARELO(3),
    VERDE(4),ROSA(5),LARANJA(6),
    AZUL_CLARO(7),AZUL_ESCURO(8),
    ROXO(9),VERMELHO(10),
    VA_PRISAO(11),LIVRE(12),
    CIA_FERROVIARIA(13),CIA_AVIACAO(14),
    CIA_TAXI(15),CIA_VIACAO(16),
    CIA_NAVEGACAO(17),
     CIA_AEREA(18),
    LUCRO(19),IMPOSTO(20),CIA_TAXI_AEREA(21);

    private int id;

    TipoPosicao(int id){
        this.id = id;

    }

    public int getId(){
        return id;
    }
}
