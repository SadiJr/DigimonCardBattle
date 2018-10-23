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
import dominioProblema.Carta;
import dominioProblema.Jogador;
import interfaceGrafica.AtorJogador;
import javax.swing.JOptionPane;

/**
 *
 * @author Rodrigo
 */
public class AtorRede implements OuvidorProxy {

    AtorJogador atorJogador;
    Proxy proxy;
    boolean euIniciei = false;

    public AtorRede(AtorJogador atorJogador) {
        super();
        this.atorJogador = atorJogador;
        this.proxy = Proxy.getInstance();
        proxy.addOuvinte(this);
    }

    public void conectar(String nome){
        try {
            proxy.conectar("localhost", nome);
        } catch (JahConectadoException ex) {
            JOptionPane.showMessageDialog(null, "Voce ja esta conectado");
        } catch (NaoPossivelConectarException ex) {
           JOptionPane.showMessageDialog(null, "Nao foi possivel conectar");
           System.exit(0);
        } catch (ArquivoMultiplayerException ex) {
           JOptionPane.showMessageDialog(null, "Arquivo de configuração nao encontrado");
           System.exit(0);
        }
        

    }

    public void conectar(String ip, String nomeJogador) {
        try {
            proxy.conectar(ip, nomeJogador);
        } catch (JahConectadoException ex) {
            JOptionPane.showMessageDialog(null, "Voce ja esta conectado");
        } catch (NaoPossivelConectarException ex) {
           JOptionPane.showMessageDialog(null, "Nao foi possivel conectar");
           System.exit(0);
        } catch (ArquivoMultiplayerException ex) {
           JOptionPane.showMessageDialog(null, "Arquivo de configuração nao encontrado");
           System.exit(0);
        }
    }

    public void enviaJogadaFinal(String logDaPartida, boolean vencedor) {
        enviaJogada(new JogadaFinal(logDaPartida, vencedor));
    }

    public void enviaJogadaInicial(Carta[] solucao, Jogador oponente, Jogador eu) {
        enviaJogada(new InicioPartida(oponente, eu, solucao));
    }

    public void enviaJogadaSimples(int[] posicao, String logDaPartida) {
        JogadaSimples js = new JogadaSimples(posicao, logDaPartida);
        enviaJogada(js);
    }

  
    public void iniciarPartidaRede() {
        try {
            //Jogo para 2 jogadores
            proxy.iniciarPartida(2);

        } catch (NaoConectadoException ex) {
           JOptionPane.showMessageDialog(null, "Voce nao esta conectado");
        }
        setEuIniciei(true);

    }



    public void enviaJogada(Jogada jogada){
        try {
            proxy.enviaJogada(jogada);
        } catch (NaoJogandoException ex) {
            JOptionPane.showMessageDialog(null, "Voce não está jogando");
        }

    }



    public void iniciarNovaPartida(Integer posicao) {
        String nomeOponente;
        if(posicao == 1) {
             nomeOponente =  proxy.obterNomeAdversario(2);
        }
        else{
            nomeOponente =  proxy.obterNomeAdversario(1);
        }
       
        atorJogador.iniciarPartida(euIniciei, nomeOponente);
        
    }

    public void finalizarPartidaComErro(String message) {
        JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado");
        System.exit(0);
    }

    public void receberMensagem(String msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void receberJogada(Jogada jogada) {
        if(jogada instanceof InicioPartida) {
            InicioPartida ip = (InicioPartida)jogada;
            atorJogador.recebeInicio(ip.getSolucao(), ip.getEu(), ip.getOponente());
            
        }

        if(jogada instanceof JogadaSimples) {
            JogadaSimples js = (JogadaSimples) jogada;
            atorJogador.recebeJogadaSimples(js.getLogDaPartida(), js.getPosicao());

        }

        if(jogada instanceof JogadaFinal) {
            JogadaFinal jf = (JogadaFinal) jogada;
            atorJogador.recebeJogadaFinal(jf.getLogDaPartida(), jf.isVencedor());

        }

    }

    public void tratarConexaoPerdida() {
        JOptionPane.showMessageDialog(null, "A conexão foi perdida");
        System.exit(0);
    }

    public void tratarPartidaNaoIniciada(String message) {
        JOptionPane.showMessageDialog(null, "O tempo de espera se esgotou e a partida nao foi iniciada");
        System.exit(0);
    }

    private void setEuIniciei(boolean b) {
        euIniciei = b;
    }

}
