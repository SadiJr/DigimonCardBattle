package dominio;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class JogadaImpl implements br.ufsc.inf.leobr.cliente.Jogada{

    private int jogador;
    private List<Integer> propriedadesCompradas; //CASO REPITA JOGADA, pode comprar mais de uma
    private List<Integer> propriedadesVendidas;
    private List<Integer> propriedadesHipotecadas;
    private List<Integer> propriedadesDesipotecadas;
    private Map<Integer,Integer> propriedadesAlteradas; //k = propriedade, v= numero de casas 
    private int[] novosCaptais; //novos capitais de todos os jogadores
    private int posicao;

    public JogadaImpl(int jogador) {
        this.jogador = jogador;
        propriedadesCompradas = new ArrayList<Integer>();
        propriedadesVendidas = new ArrayList<Integer>();
        propriedadesHipotecadas = new ArrayList<Integer>();
        propriedadesDesipotecadas = new ArrayList<Integer>();
        propriedadesAlteradas = new HashMap<Integer,Integer>();
    }

    public void assumirCompra(int propriedadeComprada) {
        this.propriedadesCompradas.add(propriedadeComprada);
        this.propriedadesVendidas.remove(new Integer(propriedadeComprada));
    }

    public void adicionarHipoteca(int propriedadeHipotecada){
        this.propriedadesHipotecadas.add(propriedadeHipotecada);
        this.propriedadesDesipotecadas.remove(new Integer(propriedadeHipotecada));
    }

    public void adicionarPropriedadeDesipotecada(int propriedadeDesipotecada){
        this.propriedadesDesipotecadas.add(propriedadeDesipotecada);
        this.propriedadesHipotecadas.remove(new Integer(propriedadeDesipotecada));
    }

    public void venderPropriedadeOuCasa(int propriedadeVendida){
        this.propriedadesVendidas.add(propriedadeVendida);
        this.propriedadesCompradas.remove(new Integer(propriedadeVendida));
    }

    public void adicionarMelhora(int propriedadeMelhorada, int numeroDeCasas){
        propriedadesAlteradas.put(propriedadeMelhorada,numeroDeCasas);
    }

    public int informarJogador() {
        return jogador;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public List<Integer> informarCompradas() {
        return propriedadesCompradas;
    }

    public int[] getNovosCaptais() {
        return novosCaptais;
    }

    public void setNovosCaptais(int[] novosCaptais) {
        this.novosCaptais = novosCaptais;
    }

    public List<Integer> getPropriedadesVendidas() {
        return propriedadesVendidas;
    }

    public List<Integer> getPropriedadesHipotecadas() {
        return propriedadesHipotecadas;
    }

    public List<Integer> getPropriedadesDesipotecadas() {
        return propriedadesDesipotecadas;
    }

    public Map<Integer, Integer> getPropriedadesAlteradas() {
        return propriedadesAlteradas;
    }


}