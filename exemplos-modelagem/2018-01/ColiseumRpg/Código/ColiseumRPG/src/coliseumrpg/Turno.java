package coliseumrpg;

import Erros.SemRecursoParaAtoException;
import NetGames.Time;
import Poderes.TipoDePoderes.Custo;
import java.io.Serializable;
import java.util.HashMap;
/**
 *
 * @author Matheus
 */
public class Turno implements Serializable, Cloneable {

    private Time time;
    private final Personagem personagem;
    private HashMap<Custo, Boolean> atos;
    private boolean turnoAtivo;

    public Turno(Time time, Personagem personagem) {
        atos = new HashMap();
        this.personagem = personagem;
        this.time = time;
        boolean incapacitado = personagem.estaIncapacitado();
        atos.put(Custo.AtoMenor, !incapacitado);
        atos.put(Custo.AtoMaior, !incapacitado);
        this.turnoAtivo = true;
    }

    public Time getTime() {
        return time;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void usar(Custo custo) {
        if (has(custo)) {
            atos.put(custo, false);
        } else {
            throw new SemRecursoParaAtoException("Você ja gastou seu " + custo.toString() + " nesse turno.");
        }
    }

    public boolean isTurnoAtivo() {
        return turnoAtivo;
    }

    public void encerrar() {
        if (isTurnoAtivo()) {
            personagem.passarTempo();
        }
        turnoAtivo = false;
    }

    public boolean has(Custo custo) {
        return atos.get(custo);
    }

    public void setTime(Time time) {
        this.time = time;
    }

}
