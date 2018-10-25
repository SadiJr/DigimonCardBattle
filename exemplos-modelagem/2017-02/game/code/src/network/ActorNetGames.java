package network;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;

import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import control.ActorPlayer;
import control.Game;

public class ActorNetGames implements OuvidorProxy {

    protected Proxy proxy;
    protected ActorPlayer actorPlayer;

    public ActorNetGames(ActorPlayer actorPlayer) {
        this.proxy = Proxy.getInstance();
        this.actorPlayer = actorPlayer;
        proxy.addOuvinte(this);
    }

    public Proxy getProxy() {
        return proxy;
    }

    public ActorPlayer getActorPlayer() {
        return actorPlayer;
    }

    public void setActorPlayer(ActorPlayer actorPlayer) {
        this.actorPlayer = actorPlayer;
    }

    public String getPlayerNameByPosition(int position) {
        return proxy.obterNomeAdversario(position);
    }

    public boolean connect(String server, String name) {
        try {
            proxy.conectar(server, name);
            return true;
        } catch (JahConectadoException | NaoPossivelConectarException | ArquivoMultiplayerException e) {
            return false;
        }
    }

    public boolean disconnect() {
        try {
            proxy.desconectar();
            return true;
        } catch (NaoConectadoException e) {
            return false;
        }
    }

    public void sendGame(Game game) {
        try {
            proxy.enviaJogada(game);
            actorPlayer.updateInterface();
        } catch (NaoJogandoException e) {
        }
    }

    public boolean startGame() {
        try {
            proxy.iniciarPartida(actorPlayer.getGame().getPlayers().length);
            return true;
        } catch (NaoConectadoException ex) {
            return false;
        }
    }

    @Override
    public void iniciarNovaPartida(Integer posicao) {
        actorPlayer.setPlayerIndex(posicao);
        if (posicao == 1) {
            actorPlayer.startGame();
        }
    }

    @Override
    public void finalizarPartidaComErro(String message) {
        actorPlayer.notify();
    }

    @Override
    public void receberMensagem(String msg) {
        actorPlayer.notify();
    }

    @Override
    public void receberJogada(Jogada jogada) {
        this.actorPlayer.setGame((Game) jogada);
        this.actorPlayer.updateInterface();
    }

    @Override
    public void tratarConexaoPerdida() {
        actorPlayer.notify();
    }

    @Override
    public void tratarPartidaNaoIniciada(String message) {
        actorPlayer.notify();
    }

}
