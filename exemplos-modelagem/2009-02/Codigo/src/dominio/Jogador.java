package dominio;

import estatico.TipoPosicao;
import estatico.PosicoesEnum;

import java.util.*;

public class Jogador {

    private boolean saidaLivre;
    private int tentativasSairPrisao;
    private boolean preso;
    private String nome;
    private int ordem;
    private int posicao;
    private int repeticoes;
    private Vector<Propriedade> propriedades;
    private int capital;

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public Jogador(String nome) {
        this.nome = nome;
        this.capital = 1808;
        this.propriedades = new Vector<Propriedade>();

    }

    public Vector<Propriedade> getPropriedades() {
        return propriedades;
    }

    public void comprarPropriedade(Propriedade propriedade) {
        this.propriedades.add(propriedade);
        this.debitar(propriedade.getValor());
        propriedade.setDono(this);
    }

    public void enviaParaAPrisao() {
        this.posicao = PosicoesEnum.PRISAO.getId();
        this.preso = true;
    }
    public boolean isPreso() {
        return preso;
    }
    public void hipotecar(Propriedade propriedade){
        propriedade.hipotecar();
        creditar(propriedade.getValorHipoteca());
    }

    public void desipotecar(Propriedade propriedade){
        /*if(debitarTaxaHipoteca(propriedade)){
            propriedade.desipotecar();
            return true;   
        }
        return false;*/
        propriedade.desipotecar();
    }

    /*public boolean debitarTaxaHipoteca(Propriedade propriedade){
        int debito = propriedade.getValorHipoteca();
        //getValor()*0.20
        debito += propriedade.custoDesipoteca();
        if(capital >= debito){
            capital -= debito;
            return true;
        }
        return false;
    }*/

    public List<Integer> informarHipotecadas() {
        List<Integer> list = new ArrayList<Integer>();
        for (Propriedade propriedade : propriedades) {
            if (propriedade instanceof Terreno) {
                Terreno terreno = (Terreno) propriedade;
                if (terreno.isHipotecada())
                    list.add(propriedade.getTipoPosicao().getId());
            }
        }
        return list;
    }

    public List<Integer> informarHipotecaveis() {
        List<Integer> list = new ArrayList<Integer>();
        for (Propriedade propriedade : propriedades) {
            if (propriedade instanceof Terreno) {
                Terreno terreno = (Terreno) propriedade;
                if (terreno.getNumeroDeCasas() < 1 && !terreno.isHipotecada())
                    list.add(propriedade.getTipoPosicao().getId());
            }
        }
        return list;
    }

    public int vender(Propriedade propriedade){
        if(propriedade instanceof Companhia || ((Terreno)propriedade).getNumeroDeCasas() == 0){
            propriedade.setDono(null);
            this.propriedades.remove(propriedade);
            this.creditar(propriedade.custoVenda());
            return -1;
        }
        else{
            this.creditar(((Terreno)propriedade).custoVenda1Casa());
            return ((Terreno)propriedade).decrementarCasa();
        }
    }

    public List<Integer> informarVendiveis() {

        Map<TipoPosicao,List<Terreno>> quantidadePorCorJogador = quantidadePorCorJogador();
        List<Integer> vendiveis = new ArrayList<Integer>();

        for (Propriedade propriedade : propriedades) {
            if (propriedade instanceof Companhia) {
                Companhia cia = (Companhia) propriedade;
                vendiveis.add(cia.getTipoPosicao().getId());
            }
        }

        Iterator keyValuePairs1 = quantidadePorCorJogador.entrySet().iterator();
        for (int i = 0; i < quantidadePorCorJogador.size(); i++)
        {
            Map.Entry entry = (Map.Entry) keyValuePairs1.next();
            TipoPosicao key = (TipoPosicao)entry.getKey();
            List<Terreno> value = (List<Terreno>)entry.getValue();
            int maior = value.get(0).getNumeroDeCasas();
            for(Terreno t : value)
                if(t.getNumeroDeCasas() > maior)
                    maior = t.getNumeroDeCasas();
            for(Terreno t : value){
                if(maior == 0){
                    vendiveis.add(t.getTipoPosicao().getId());
                }
                else
                if(t.getNumeroDeCasas() == maior){
                    vendiveis.add(t.getTipoPosicao().getId());
                }
            }
        }

        return vendiveis;
    }

    public List<Integer> informarMelhoraveis(Map<TipoPosicao,Integer> quantidadePorCor) {

        Map<TipoPosicao,List<Terreno>> quantidadePorCorJogador = quantidadePorCorJogador();
        List<Integer> melhoraveis = new ArrayList<Integer>();

        Iterator keyValuePairs1 = quantidadePorCorJogador.entrySet().iterator();
        for (int i = 0; i < quantidadePorCorJogador.size(); i++)
        {
            Map.Entry entry = (Map.Entry) keyValuePairs1.next();
            TipoPosicao key = (TipoPosicao)entry.getKey();
            List<Terreno> value = (List<Terreno>)entry.getValue();
            if(value.size() == quantidadePorCor.get(key)){
                int menor = value.get(0).getNumeroDeCasas();
                for(Terreno t : value)
                    if(t.getNumeroDeCasas() < menor)
                        menor = t.getNumeroDeCasas();
                if(menor != 5)
                    for(Terreno t : value)
                        if(t.getNumeroDeCasas() <= menor)
                            melhoraveis.add(t.getTipoPosicao().getId());
            }
        }

        return melhoraveis;
    }

    public Map<TipoPosicao,List<Terreno>> quantidadePorCorJogador(){
        Map<TipoPosicao,List<Terreno>> quantidadePorCorJogador = new HashMap<TipoPosicao,List<Terreno>> ();
        for(Propriedade p : propriedades){
            //adicionei o is Hiptoecada
            if(p instanceof Terreno && !p.isHipotecada()){
                List <Terreno> terrenos = quantidadePorCorJogador.get(p.getTipoPosicao().getTipo());
                if (terrenos == null) {
                    terrenos = new ArrayList<Terreno>();
                    quantidadePorCorJogador.put(p.getTipoPosicao().getTipo(),terrenos);
                }
                terrenos.add((Terreno)p);
            }
        }
        return quantidadePorCorJogador;
    }


//    public boolean hipotecar(int i) {
//        return true;
//    }
    //
    public boolean vender(int i) {
        return true;
    }
//
//    public boolean desipotecar(int i) {
//        return true;
//    }

    public void debitar(double valor) {
        this.capital -= valor;
    }

    public void sairDaPrisao() {
        this.preso = false;
        this.tentativasSairPrisao = 0;
    }

    public void marcarRepetirJogada() {
        repeticoes++;
    }
    public void limparRepetirJogada() {
        repeticoes = 0;
    }
    public int getRepeticoes() {
        return repeticoes;
    }

    public boolean posicionar(int resultadoNoDado) {
        posicao += resultadoNoDado;
        if (posicao >= 40) {
            posicao = posicao % 40;
            return true;
        }
        return false;
    }

    public void creditar(double valor) {
        this.capital += valor;
    }

    public void receberSaidaLivre() {
        saidaLivre = true;
    }
    public boolean temSaidaLivre() {
        return saidaLivre;
    }
    public void usarSaidaLivre() {
        saidaLivre = false;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean esgotouTentativasSairPrisao() {
        return tentativasSairPrisao>=2;
    }

    public void aumentarTentativas() {
        this.tentativasSairPrisao++;
    }


    public String toString() {
        return nome+" - "+ordem;
    }

    public List<Propriedade> informarPropriedades() {
        return propriedades;
    }

    public int[] falir() {
        List<Propriedade> propriedades = this.informarPropriedades();
        int[] posicoes = new int[propriedades.size()];
        for (int j=0;j<propriedades.size();j++) {
            Propriedade p = propriedades.get(j);
            p.setDono(null);
            posicoes[j] = p.getTipoPosicao().getId();
        }
        return posicoes;
    }
}