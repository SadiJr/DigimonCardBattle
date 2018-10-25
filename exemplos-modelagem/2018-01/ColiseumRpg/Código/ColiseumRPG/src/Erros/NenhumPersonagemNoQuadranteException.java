/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Erros;

/**
 *
 * @author Matheus
 */
public class NenhumPersonagemNoQuadranteException extends RuntimeException {

    public NenhumPersonagemNoQuadranteException() {
        super("Esse poder só pode ser usado em quadrantes ocupados por personagens.");
    }

}
