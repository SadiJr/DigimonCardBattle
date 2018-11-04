/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import br.ufsc.inf.leobr.cliente.Jogada;

/**
 *
 * @author julia
 */
public enum Naipe implements Jogada{
    COPAS("B", 2), PAUS("D", 4), OURO("A", 1), ESPADA("C", 3);
        String letraReferente;
        int valor;
        int teste;

        Naipe (String letra, int valor) {
            this.letraReferente = letra;
            this.valor = valor;
        }

    public String getLetraReferente() {
        return letraReferente;
    }

    public void setLetraReferente(String letraReferente) {
        this.letraReferente = letraReferente;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }



        public static int getValorNaipe(String valor){
            int retorno = 0;
            for (Naipe n : Naipe.values()){
                if(n.getLetraReferente().equals(valor)){
                    retorno = n.getValor();
                }
            }

            return retorno;
        }

        public static String getLetraPorNumeroNaipe(int valor){
               String retorno = "";
            for (Naipe n : Naipe.values()){
                if(n.getValor() == valor){
                    retorno = n.getLetraReferente();
                }
            }

            return retorno;
        }

        public static Naipe getNaipePorValor(int valor){

           Naipe retorno = null;
            for (Naipe n : Naipe.values()){
                if(n.getValor() == valor){
                    retorno = n;
                }
            }

            return retorno;
        }

        public boolean naipeMaiorQueOutro(Naipe naipe){

            return this.getValor() > naipe.getValor();

        }



        


        
        
}
