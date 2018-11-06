package model;

import br.ufsc.inf.leobr.cliente.Jogada;
import java.util.List;

public class Jogador implements Jogada {

        private int id;
	private String nome;
	private Jogador companheiro;
        private Jogador proximoAJogar;
	private List<Carta> cartas;
	private Dupla dupla;

    public Jogador(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public Jogador(String nome) {
        this.nome = nome;

    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public Jogador getCompanheiro() {
        return companheiro;
    }

    public void setCompanheiro(Jogador companheiro) {
        this.companheiro = companheiro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Jogador getProximoAJogar() {
        return proximoAJogar;
    }

    public void setProximoAJogar(Jogador proximoAJogar) {
        this.proximoAJogar = proximoAJogar;
    }

    public Dupla getDupla() {
        return dupla;
    }

    public void setDupla(Dupla dupla) {
        this.dupla = dupla;
    }


    

}
