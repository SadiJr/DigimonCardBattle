/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dominioProblema;

import java.io.Serializable;

/**
 *
 * @author Rodrigo
 */
public class Jogador implements Serializable {

    protected Carta[] cartas;


        private boolean vencedor;
        private boolean daVez;
        private String nome;
        private int numeroPassos;
        private int peao;


    Jogador(String nomeJogador, int peao) {
        vencedor = false;
        daVez = false;
        nome = nomeJogador;
        numeroPassos = 0;
        this.peao = peao;
    }

     /**
     * Get the value of cartas
     *
     * @return the value of cartas
     */
    public Carta[] getCartas() {
        return cartas;
    }

    /**
     * Set the value of cartas
     *
     * @param cartas new value of cartas
     */
    public void setCartas(Carta[] cartas) {
        this.cartas = cartas;
    }

    /**
     * Get the value of peao
     *
     * @return the value of peao
     */
    public int getPeao() {
        return peao;
    }

    /**
     * Set the value of peao
     *
     * @param peao new value of peao
     */
    public void setPeao(int peao) {
        this.peao = peao;
    }

   /**
     * Get the value of numeroPassos
     *
     * @return the value of numeroPassos
     */
    public int getNumeroPassos() {
        return numeroPassos;
    }

    /**
     * Set the value of numeroPassos
     *
     * @param numeroPassos new value of numeroPassos
     */
    public void setNumeroPassos(int numeroPassos) {
        this.numeroPassos = numeroPassos;
    }


    /**
     * Get the value of nome
     *
     * @return the value of nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Set the value of nome
     *
     * @param nome new value of nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

   
    /**
     * Get the value of daVez
     *
     * @return the value of daVez
     */
    public boolean isDaVez() {
        return daVez;
    }

    /**
     * Set the value of daVez
     *
     * @param daVez new value of daVez
     */
    public void setDaVez(boolean daVez) {
        this.daVez = daVez;
    }


    /**
     * Get the value of vencedor
     *
     * @return the value of vencedor
     */
    public boolean isVencedor() {
        return vencedor;
    }

    /**
     * Set the value of vencedor
     *
     * @param vencedor new value of vencedor
     */
    public void setVencedor(boolean vencedor) {
        this.vencedor = vencedor;
    }


}
