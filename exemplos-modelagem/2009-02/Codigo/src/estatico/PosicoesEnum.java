package estatico;

public enum PosicoesEnum {

    PARTIDA(0,"Partida", TipoPosicao.PARTIDA),LEBLON(1,"Leblon", TipoPosicao.ROSA),SORTE_REVES(2,"Sorte Revés", TipoPosicao.SORTE_REVES),
    AV_PRESIDENTE_VARGAS(3,"Av. Presidente Vargas", TipoPosicao.ROSA),AV_NOSSA_COPACABANA(4,"Av. Nossa S. de Copacabana", TipoPosicao.ROSA),CIA_FERROVIARIA(5,"Companhia Ferroviaria", TipoPosicao.CIA_FERROVIARIA),
    AV_BRIGRADEIRO_FARIA_LIMA(6,"Av. Brigadeiro Faria Lima", TipoPosicao.AZUL_CLARO),CIA_VIACAO(7,"Companhia de Viacao", TipoPosicao.CIA_VIACAO),AV_REBOUCAS(8,"Av. Reboucas", TipoPosicao.AZUL_CLARO),
    AV_9_JULHO(9,"Av. 9 de Julho", TipoPosicao.AZUL_CLARO),PRISAO(10,"Prisão", TipoPosicao.PRISAO),AV_EUROPA(11,"Av. Europa", TipoPosicao.ROXO),SORTE_REVES2(12,"Sorte Revés", TipoPosicao.SORTE_REVES),
    RUA_AUGUSTA(13,"Rua Augusta", TipoPosicao.ROXO),AV_PACAEMBU(14,"Av. Pacaembu", TipoPosicao.ROXO),
    CIA_TAXI(15,"Companhia de Taxi", TipoPosicao.CIA_TAXI),SORTE_REVES3(16,"Sorte Revés", TipoPosicao.SORTE_REVES),INTERLAGOS(17,"Interlagos", TipoPosicao.LARANJA),
    LUCROS_DIVIDENDOS(18,"Lucros ou Dividendos", TipoPosicao.LUCRO),MORUMBI(19,"Morumbi", TipoPosicao.LARANJA),PARTIDA_LIVRE(20,"Partida Livre", TipoPosicao.LIVRE),
    FLAMENGO(21,"Flamengo", TipoPosicao.VERMELHO),SORTE_REVES4(22,"Sorte Revés", TipoPosicao.SORTE_REVES),BOTAFOGO(23,"Botafogo", TipoPosicao.VERMELHO),IMPOSTO_RENDA(24,"Imposto de Renda", TipoPosicao.IMPOSTO),
    CIA_NAVEGACAO(25,"Companhia de Navegacao", TipoPosicao.CIA_NAVEGACAO),AV_BRASIL(26,"Av. Brasil", TipoPosicao.AMARELO),SORTE_REVES5(27,"Sorte Revés", TipoPosicao.SORTE_REVES),AV_PAULISTA(28,"Av. Paulista", TipoPosicao.AMARELO),
    JARDIM_EUROPA(29,"Jardim Europa", TipoPosicao.AMARELO),VA_PARA_PRISAO(30,"Vá para a Prisão", TipoPosicao.VA_PRISAO),COPACABANA(31,"Copacabana", TipoPosicao.VERDE),
    CIA_AVIACAO(32,"Companhia de Aviacao", TipoPosicao.CIA_AVIACAO),AV_VIEIRA_SOUTO(33,"Av. Vieira Souto", TipoPosicao.VERDE),AV_ATLANTICA(34,"Av. Atlântica", TipoPosicao.VERDE),
    CIA_TAXI_AEREO(35,"Companhia de Taxi Aereo", TipoPosicao.CIA_TAXI_AEREA),IPANEMA(36,"Ipanema", TipoPosicao.VERDE),SORTE_REVES6(37,"Sorte Revés", TipoPosicao.SORTE_REVES),JARDIM_PAULISTA(38,"Jardim Paulista", TipoPosicao.AZUL_ESCURO),
    BROOKLIN(39,"Brooklin", TipoPosicao.AZUL_ESCURO);

    private int id;
    private String nome;
    private TipoPosicao posicaoSub;

    PosicoesEnum(int id, String nome, TipoPosicao posicaoSub){
        this.id = id;
        this.nome = nome;
        this.posicaoSub = posicaoSub;
    }

    public String getNome() {
        return nome;
    }
    public TipoPosicao getTipo() {
        return posicaoSub;
    }
    public int getId() {
        return id;
    }
    public int getLine() {
        return id/10;
    }
}