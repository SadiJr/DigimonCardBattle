/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rede;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AtorJogador;
import model.Jogador;
import model.Lance;
import model.Mesa;
import view.JMesa;

/**
 *
 * @author julia
 */
public class AtorNetGames implements OuvidorProxy {

    private AtorJogador jogador;
    private Proxy proxy;

    public AtorNetGames(AtorJogador jogador) {
        super();
        this.jogador = jogador;
        proxy = Proxy.getInstance();
        proxy.addOuvinte(this);
    }

 

    public void finalizarPartidaComErro(String message) {
       jogador.exibeMensagem(message);
       jogador.limparTodosCampos();
    }

    public void receberMensagem(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void receberJogada(Jogada jogada) {
        System.out.println("Jogada foi recebida");
        jogador.receberJogada(jogada);
    }

    public void tratarConexaoPerdida() {
       jogador.exibeMensagem("Conexão foi perdida!");
       jogador.limparTodosCampos();
    }

    public void tratarPartidaNaoIniciada(String message) {
        jogador.exibeMensagem(message);
    }

    public boolean conectar(String servidor, String nome) {
        System.out.println("Nome" + nome);
        boolean retorno = true;
        try {
            proxy.conectar(servidor, nome);
        } catch (JahConectadoException ex) {
            retorno = false;
            ex.printStackTrace();
        } catch (NaoPossivelConectarException ex) {
            ex.printStackTrace();
            retorno = false;
        } catch (ArquivoMultiplayerException ex) {
            ex.printStackTrace();
            retorno = false;
        }
        return retorno;
    }

    public void desconectar() {
        try {
            proxy.desconectar();
        } catch (NaoConectadoException ex) {
            jogador.exibeMensagem(ex.getMessage());
        }


    }

    public void iniciarPartida() {

        try{
        proxy.iniciarPartida(4);
        }catch (Exception e){
           jogador.exibeMensagem(e.getMessage());
        }
    }

    public void enviarJogada(Jogada jogada)  {
        System.out.println("Jogada foi enviada");
        try{
        proxy.enviaJogada(jogada);
        }catch (Exception e){
           jogador.exibeMensagem(e.getMessage());
        }

    }



    public List<Jogador> getJogadores() {
        List<Jogador> jogadores = new ArrayList<Jogador>();

        
        for (int i = 1; i < 5; i++) {

            Jogador j = null;
            try{
             j = new Jogador(proxy.obterNomeAdversario(i), i);

            System.out.println("NOME" + j.getNome() + " ID "+i);
                jogadores.add(j);
              }catch(Exception e){
                System.out.println("Sem Jogadores Suficientes");
             }
            
        }
      
        return jogadores;

    }

    @Override
    public void iniciarNovaPartida(Integer posicao) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
