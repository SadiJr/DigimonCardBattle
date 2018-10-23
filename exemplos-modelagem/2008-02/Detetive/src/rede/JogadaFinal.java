/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rede;

import br.ufsc.inf.leobr.cliente.Jogada;

/**
 *
 * @author Rodrigo
 */
public class JogadaFinal implements Jogada{

    private String logDaPartida;
    private boolean vencedor;

    public JogadaFinal(String logDaPartida, boolean vencedor) {
        this.logDaPartida = logDaPartida;
        this.vencedor = vencedor;
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


    /**
     * Get the value of logDaPartida
     *
     * @return the value of logDaPartida
     */
    public String getLogDaPartida() {
        return logDaPartida;
    }

    /**
     * Set the value of logDaPartida
     *
     * @param logDaPartida new value of logDaPartida
     */
    public void setLogDaPartida(String logDaPartida) {
        this.logDaPartida = logDaPartida;
    }


}
