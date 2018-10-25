/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import NetGames.Time;
import Poderes.Adaptavel;
import Poderes.Armadilhas;
import coliseumrpg.Personagem;
import Poderes.Poder;

/**
 *
 * @author Matheus
 */
public class Cacador extends Personagem {

    public Cacador() {
        super("Caçador",
                "Ágil e sorrateiro ele é habilidoso tanto com a faca"
                + " quanto com o arco.\nCuidado onde pisa, "
                + "ele esconde armadilhas mortais!", Classes.Cacador,
                8, 3, 3, 1, "Cacador.png", "CacadorMorto.png");
        poderes = new Poder[2];
        poderes[0] = new Armadilhas();
        poderes[1] = new Adaptavel();

    }
    
    @Override
    public void setTime(Time time){
        this.time = time;
        ((Armadilhas )poderes[0]).setTime(time);
    }

}
