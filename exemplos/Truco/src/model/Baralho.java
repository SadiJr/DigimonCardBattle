package model;

import br.ufsc.inf.leobr.cliente.Jogada;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Baralho implements Jogada{

    private List<Carta> cartas;

    public Baralho() {

        cartas = getAllCartas();

    }

    private List<Carta> getAllCartas() {

        List<Carta> todasAsCartas = new ArrayList<Carta>();

        for (int i = 1; i < 13; i++) {
            boolean ehOitoOuNove = i == 8 || i == 9;
            for (int b = 1; b < 5 && !ehOitoOuNove; b++) {

                String nomeCarta = i +""+ Naipe.getLetraPorNumeroNaipe(b);

                Carta c = new Carta(nomeCarta);
                todasAsCartas.add(c);

            }

        }

        return todasAsCartas;
    }


    public void  embaralharCartas(){

        Collections.shuffle(cartas);

    }

    public Carta getCartaAleatoria(){

        return this.cartas.get((int) (Math.random() * cartas.size()));
    }

    public List<Carta> getCartas() {
        return cartas;
    }



}
