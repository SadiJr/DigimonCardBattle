package model;

import br.ufsc.inf.leobr.cliente.Jogada;
import java.util.ArrayList;
import java.util.List;

public class Mao implements  Jogada{

    private List<Rodada> rodadas;
    int valorDaMao = 0;

    public Mao(){
        rodadas = new ArrayList<Rodada>();
        valorDaMao = 1;
    }


    /**
     * 1 ponto para todos quando der empate
     * 2 pontos para dupla vencedora
     * @return
     */
    public Dupla getVencedorMao(){

        int pontosA = 0;
        int pontosB = 0;

        for (Rodada rodada : rodadas){

            if (rodada.getVencedorRodada().getNome().equals("Empate")){
                pontosA += 1;
                pontosB += 1;
            }else if (rodada.getVencedorRodada().getDupla().equals(Dupla.DUPLA_A)){

                pontosA += 2;

            }else if (rodada.getVencedorRodada().getDupla().equals(Dupla.DUPLA_B)){
                pontosB += 2;
            }


        }

        //se empatou vai retornar null;
        Dupla vencedora = null;

        if (pontosA > pontosB){
          vencedora = Dupla.DUPLA_A;
        }else if (pontosA < pontosB){
            vencedora = Dupla.DUPLA_B;
        }else if (pontosB == pontosA){
            vencedora = null;
        }



        return vencedora;
    }

    public Dupla verificaSeUmaDuplaGanhouDuasRodadas(){
        Rodada rodada1 = rodadas.get(0);
        Rodada rodada2 = rodadas.get(1);


        Jogador primeiroVencedor = rodada1.getVencedorRodada();
        Jogador segundoVencedor = rodada2.getVencedorRodada();

        Dupla vencedorRodada = primeiroVencedor.getDupla();
        Dupla vencedorRodada2 = segundoVencedor.getDupla();



        Dupla retorno = null;

        if (vencedorRodada != null && vencedorRodada2 != null && vencedorRodada.equals(vencedorRodada2)){
            retorno = vencedorRodada;
        }else{
            System.out.println("retorno");
            retorno = verificaSeEhEmpateEOutraVitoria(primeiroVencedor, segundoVencedor);

        }


        return retorno;
    }

    private Dupla verificaSeEhEmpateEOutraVitoria(Jogador vencedor1, Jogador vencedor2){

        System.out.println("ven1" + vencedor1.getNome());
        System.out.println("ven2" +vencedor2.getNome());


          if (vencedor1.getNome().equals("Empate") && !vencedor2.getNome().equals("Empate")){

            return vencedor2.getDupla();

        }else if (!vencedor1.getNome().equals("Empate") && vencedor2.getNome().equals("Empate")){
            
            return vencedor1.getDupla();
        }

        return null;

    }


    public List<Rodada> getRodadas() {
        return rodadas;
    }

    public void setRodadas(List<Rodada> rodadas) {
        this.rodadas = rodadas;
    }

    public int getValorDaMao() {
        return valorDaMao;
    }

    public void setValorDaMao(int valorDaMao) {
        this.valorDaMao = valorDaMao;
    }



}
