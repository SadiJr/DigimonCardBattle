/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package joormanji;

/**
 *
 * @author JooRmanji
 */
public class Tabuleiro {

    private Eventos osEventos;

    private Jogador jogadorCriador;

    private Jogador jogadorConvidado;

    public Tabuleiro() {
            this.osEventos = new Eventos();
    }

    /**
     * Cria um participante na sala.
     * 
     * @param nome
     */
    public void criarJogador(String nome) {
            if (jogadorCriador == null) {
                    jogadorCriador = new Jogador(nome);
                    jogadorCriador.tomarVez();
            } else if (jogadorConvidado == null) {
                    jogadorConvidado = new Jogador(nome);
                    jogadorConvidado.passarVez();
            }
    }

    public boolean trataJogada(int valorDoDado) {

            if (jogadorCriador.ehVez()) {
                int posicaoDestino = jogadorCriador.getPosicao()+valorDoDado;
                jogadorCriador.setPosicao(posicaoDestino);
                /*if(jogadorCriador.getPosicao()>35)
                {
                    return -1; //o jogo acabou!
                }*/
                jogadorCriador.passarVez();
                jogadorConvidado.tomarVez();
                return true;
                
            } else if (jogadorConvidado.ehVez()) {
                int posicaoDestino = jogadorConvidado.getPosicao()+valorDoDado;
                jogadorConvidado.setPosicao(posicaoDestino);
                /*if(jogadorConvidado.getPosicao()>35)
                {
                    return -1;
                }*/
                jogadorConvidado.passarVez();
                jogadorCriador.tomarVez();
                return true;
                //return posicaoDestino;
            }
            //return -1;
            return false;

    }

    /**
     * Informa a última mensagem da lista de mensagens.
     * 
     * @return
     */
    /*public String informaUltimaMensagem() {
            return mensagens.get(mensagens.size() - 1);
    }*/

    public int rolarDados(){
        int valor = (int)(Math.random()*6)+1;
        return valor;
    }
    
    public int getPosicao(){
        if (!jogadorCriador.ehVez()) 
        {
            return this.jogadorCriador.getPosicao();
        }
        else  
        {
            return this.jogadorConvidado.getPosicao();
        }
    }
    
    public Desafio getUmEvento(){
        
        return this.osEventos.getUmDesafio();
        
    }

    /**
     *Retorna o Resultado da Consulta para Ver se Alguém já tirou 5
     * <br/><b>true</b> -> alguem já usou
     * <br/><b>false</b> -> este é o primeiro
     */
    public boolean verificarEstadoEspecial(){
        return this.osEventos.getEspecialUsado();
    }
    
    public void alterarEstadoEspecial(){
        this.osEventos.setEspecialUsado();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
