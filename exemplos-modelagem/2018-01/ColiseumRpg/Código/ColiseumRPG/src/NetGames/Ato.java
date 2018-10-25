package NetGames;

import Mapa.Lugar;
import br.ufsc.inf.leobr.cliente.Jogada;
import coliseumrpg.Personagem;
import coliseumrpg.Turno;
import java.awt.Dimension;
import java.util.HashMap;

public class Ato implements Jogada {

    protected final Turno turno;
    protected final HashMap<Dimension, Lugar> lugaresRelevantes;

    public Ato(Turno turno, HashMap<Personagem, Lugar> novaPosicoesPersonagens, HashMap<Dimension, Lugar> alteracoesMapa, Personagem[][] personagens) {
        this.turno = turno;
        this.lugaresRelevantes = alteracoesMapa;
        novaPosicoesPersonagens.forEach((pers, lugar) -> {
            this.lugaresRelevantes.put(lugar.getCoordenada(), lugar);
        });
    }

    public Turno getTurno() {
        return turno;
    }

    public HashMap<Dimension, Lugar> getAlteracoesMapa() {
        return lugaresRelevantes;
    }

    public HashMap<Personagem, Lugar> getNovaPosicoesPersonagens() {
        HashMap<Personagem, Lugar> novaPosicoesPersonagens = new HashMap();
        lugaresRelevantes.forEach((coordenada, lugar) -> {
            if(lugar.estaOcupado()){
                novaPosicoesPersonagens.put(lugar.getPersonagem(), lugar);
            }
        });
        return novaPosicoesPersonagens;
    }

}
