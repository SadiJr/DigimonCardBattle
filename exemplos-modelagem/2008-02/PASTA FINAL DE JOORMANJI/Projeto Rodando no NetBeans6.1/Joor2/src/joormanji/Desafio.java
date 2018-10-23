/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package joormanji;

/**
 *
 * @author adm
 */
public class Desafio extends Evento{
    
    private boolean resposta;
    
    public Desafio(String texto, boolean resposta){
        super(texto);
        this.resposta = resposta;
    }

    public boolean isResposta() {
        return resposta;
    }

    public void setResposta(boolean resposta) {
        this.resposta = resposta;
    }    
}
