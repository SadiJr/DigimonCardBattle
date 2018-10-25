package NetGames;

import InterfaceVisual.ControladorTelas;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import coliseumrpg.ColiseumRPG;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorNetGames implements OuvidorProxy {

    private final Proxy proxy;
    private final ColiseumRPG coliseumRPG;
    private static final ControladorNetGames instance = new ControladorNetGames();

    private ControladorNetGames() {
        this.proxy = Proxy.getInstance();
        this.coliseumRPG = ColiseumRPG.getInstance();
        proxy.addOuvinte(this);
    }

    public void conectar(String ipDoServidor) {
        try {
            proxy.conectar(ipDoServidor, "jogador");
//            pedirIniciarPartida();
            ControladorTelas.infoDialog("Conectado ao servidor, pressione novamente para tentar encontrar partida.");
        } catch (JahConectadoException ex) {
            pedirIniciarPartida();
        } catch (NaoPossivelConectarException ex) {
            Logger.getLogger(ControladorNetGames.class.getName()).log(Level.SEVERE, null, ex);
            ControladorTelas.errorDialog("Não foi possível conectar.\nVerifique sua conexão com a internet e o estado do servidor.");
        } catch (ArquivoMultiplayerException ex) {
            Logger.getLogger(ControladorNetGames.class.getName()).log(Level.SEVERE, null, ex);
            ControladorTelas.errorDialog("ArquivoMultiplayerException - é, deu ruim.");
        }
    }

    public void pedirIniciarPartida() {
        try {
            proxy.iniciarPartida(2);
        } catch (NaoConectadoException ex) {
            Logger.getLogger(ControladorNetGames.class.getName()).log(Level.SEVERE, null, ex);
            ControladorTelas.errorDialog("Você não esta conectado.\nEscolha a cor de seu time e conecte antes de iniciar partida.");
        }
    }

    public void enviarJogada(Jogada jogada) {
        try {
            proxy.enviaJogada(jogada);
        } catch (NaoJogandoException ex) {
            Logger.getLogger(ControladorNetGames.class.getName()).log(Level.SEVERE, null, ex);
            ControladorTelas.errorDialog("Partida não encontrada, possível perda de conexão.");
        }
    }

    public void desconectar() {
        try {
            proxy.desconectar();
        } catch (NaoConectadoException ex) {
            Logger.getLogger(ControladorNetGames.class.getName()).log(Level.SEVERE, null, ex);
            ControladorTelas.errorDialog("Você quer desconectar antes de conectar???...");
        }
    }

    /**
     *
     * @param posicao indica qual a posição na 'fila' do jogador, se for 1 sera
     * o primeiro, se for 2 será o segundo
     */
    @Override
    public void iniciarNovaPartida(Integer posicao) {
        coliseumRPG.iniciarPartida(posicao == 1);
    }

    @Override
    public void finalizarPartidaComErro(String message) {
        ColiseumRPG.getInstance().encerrarJogo();
        ControladorTelas.getInstance().abrirTelaInicial();
    }

    @Override
    public void receberMensagem(String msg) {
        throw new UnsupportedOperationException("Enviar Mensagem não deveria ser usado nesse jogo.");
    }

    @Override
    public void receberJogada(Jogada jogada) {
        try {
            coliseumRPG.tratarJogada((Ato) jogada);
        } catch (RuntimeException e) {
            Logger.getLogger(ControladorNetGames.class.getName()).log(Level.SEVERE, null, e);
            ControladorTelas.errorDialog("Falha ao tratar jogada.\n"+e.getMessage());
        }
    }

    @Override
    public void tratarConexaoPerdida() {
        ControladorTelas.errorDialog("Conexão Perdida : //");
    }

    @Override
    public void tratarPartidaNaoIniciada(String message) {
        ControladorTelas.errorDialog("Partida não iniciada.\n" + message);
    }

    public static ControladorNetGames getInstance() {
        return instance;
    }

    public Time getTimeAdversario(boolean minhaVez) {
        return minhaVez?Time.VERMELHO:Time.AZUL;
    }

}
