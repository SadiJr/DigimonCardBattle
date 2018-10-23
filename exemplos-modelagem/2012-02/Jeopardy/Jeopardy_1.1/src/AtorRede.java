import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

public class AtorRede implements OuvidorProxy {
    private static final long serialVersionUID = 1L;
    protected AtorJogador atorJogador;
    protected Proxy proxy;

    public AtorRede(AtorJogador atorJogador) {
        // configura o objeto para enviar/receber notificacoes do netgames
        super();
        this.atorJogador = atorJogador;
        this.proxy = Proxy.getInstance();
        proxy.addOuvinte(this);
    }

    public boolean conectar(String servidor, String nome) {
        // faz a conexão com o netgames ou avisa caso não seja possível
        try {
          proxy.conectar(servidor, nome);
            return true;
        } catch (JahConectadoException | NaoPossivelConectarException | ArquivoMultiplayerException e) {
            new JanelaMensagem(e).show();
            return false;
        }
    }

    public boolean desconectar() {
        // desconecta do netgames
        try {
            proxy.desconectar();
            return true;
        } catch (NaoConectadoException e) {
            new JanelaMensagem(e).show();
            return false;
        }
    }

    public void iniciarPartida() {
        // manda iniciar uma nova partida com 2 jogadores
        try {
            proxy.iniciarPartida(new Integer(2));
        } catch (NaoConectadoException e) {
            new JanelaMensagem(e).show();
        }
    }

    public void enviarJogada(Jogada jogada) {
        // envia uma jogada pela rede
        try {
            proxy.enviaJogada(jogada);
        } catch (NaoJogandoException e) {
            new JanelaMensagem(e).show();
        }
    }

    public String informarNomeAdversario(String idUsuario) {
        // retorna o nome dos dois jogadores, o que não for o ser é o oponente
        String aux1 = proxy.obterNomeAdversario(new Integer(1));
        String aux2 = proxy.obterNomeAdversario(new Integer(2));
        if (aux1.equals(idUsuario)) {
            return aux2;
        } else {
            return aux1;
        }
    }

    @Override
    public void receberJogada(Jogada jogada) {
        // converte a jogada no tipo especializado e envia para processamento
        JogadaCustom lance = (JogadaCustom) jogada;
        this.atorJogador.receberJogada(lance);
    }

    @Override
    public void finalizarPartidaComErro(String message) {
        this.atorJogador.fechar();
    }

    @Override
    public void receberMensagem(String msg) {
        // TODO Auto-generated method stub
    }

    @Override
    public void tratarConexaoPerdida() {
        this.atorJogador.fechar();
    }

    @Override
    public void tratarPartidaNaoIniciada(String message) {
        //new JanelaMensagem(message).show();
    }

    @Override
    public void iniciarNovaPartida(Integer posicao) {
        this.atorJogador.tratarIniciarPartida(posicao);
    }
}