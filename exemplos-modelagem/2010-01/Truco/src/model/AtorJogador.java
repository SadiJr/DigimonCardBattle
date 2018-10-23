package model;

import br.ufsc.inf.leobr.cliente.Jogada;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rede.AtorNetGames;
import view.JMesa;

public class AtorJogador {

    private Mesa mesa;
    private AtorNetGames rede;
    private Jogador jogadorAtual;
    private JMesa interfaceMesa;
    private List<Lance> lancesRecebidosRespostasTruco;
    private boolean conectado;

    public AtorJogador(JMesa j) {
        this.rede = new AtorNetGames(this);
        this.mesa = new Mesa();
        this.interfaceMesa = j;
        lancesRecebidosRespostasTruco = new ArrayList<Lance>();

    }
   
    public void criarJogadorAtual(String nome){
        jogadorAtual = new Jogador(nome);
    
    }



    public void pedirTruco() {

        if (this.tratarExecucaoJogada() && verificaSeDuplaNaoPediuTruco() && !verificaSePartidadaValeMaisDeDoze()) {
            Lance l = new Lance();
            l.setJogador(this.getJogadorAtual());
            l.setTruco(true);

            rede.enviarJogada(l);
        }

    }

    public void limparTodosCampos(){

        interfaceMesa.limpaTodosCampos();

    }


    private boolean verificaSePartidadaValeMaisDeDoze(){

        return mesa.getValorMao() == 12;

    }

    private boolean verificaSeDuplaNaoPediuTruco(){

        boolean retorno = true;

     
         if (this.getJogadorAtual().getDupla().equals(mesa.getDuplaQuePediuTruco())){
            retorno =  false;
        }


        return retorno;
    }


    public boolean conectar(String nome, String servidor) {
        boolean retorno =  rede.conectar(nome, servidor);
        conectado = retorno;
        if (retorno) {
            this.criarJogadorAtual(nome);
        }

        return retorno;
    }

    public void desconectar() {
        if (conectado) {
            rede.desconectar();
            this.atualizarVisibilidadeTela(1);
            conectado = false;
        }
    }

    public void atualizaVisibilidadeTelaCasoExecao() {
    }

    public void iniciarPartida() {

        rede.iniciarPartida();

        List<Jogador> jogadores = rede.getJogadores();

        if (jogadores.size() == 4){
         mesa.setJogadores(jogadores);
         mesa.defineDuplas();
         this.iniciarNovaPartida();
        }

    }

    public void iniciarNovaPartida() {

        mesa.iniciarMao();
        mesa.setStatus(Mesa.StatusMesa.INICAR_PARTIDA);
        mesa.zerarPlacar();
       // rede.iniciarNovaPartida(jogadorAtual.getId());
        this.enviarJogada(mesa);
        this.receberJogada(mesa);


    }

    public void enviarJogada(Jogada jogada) {
        rede.enviarJogada(jogada);
    }

    public void tratarIniciarPartida(int posicao) {
    }

    protected void setJogadorAtualIniciarPartida(Mesa mesa) {
            if (mesa.getStatus().equals(Mesa.StatusMesa.INICAR_PARTIDA)) {
                for (Jogador jog : mesa.getJogadores()) {
                    if (jog.getNome().equals(jogadorAtual.getNome())) {
                        jogadorAtual = jog;
                    }

                }
            }
    }

    public void receberJogada(Jogada jogada) {

        if (jogada instanceof Mesa) {

            this.mesa = (Mesa) jogada;

            this.setJogadorAtualIniciarPartida(mesa);

            interfaceMesa.recebeMesa(mesa);

        } else if (jogada instanceof Lance) {

            Lance lance = (Lance) jogada;

            if (lance.isTruco()) {
               
                if (lance.getRespostaTruco() != null) {
                  
                    this.adicionarLanceRecebido(lance);
                     System.out.println(lancesRecebidosRespostasTruco.size()+ "size da lista");
                    if (lancesRecebidosRespostasTruco.size() == 2) {
                       
                        this.recebeRespostaTruco(lancesRecebidosRespostasTruco);
                        // this.limparLancesRecebidos();

                    }

                } else {
                    interfaceMesa.recebeTruco(lance);
                   
                }


            } else {
                mesa.setJogadorDaVez(lance.getJogador().getProximoAJogar());
                interfaceMesa.atualizaJogadorVez(mesa);
                mesa.removeCartaDeJogador(lance);
                interfaceMesa.recebeLance(lance);


                mesa.addLance(lance);
                this.verificaFimRodada();

            }

        }

    }

    private void adicionarLanceRecebido(Lance lance) {
        lancesRecebidosRespostasTruco.add(lance);
    }

    public void limparLancesRecebidos() {
        lancesRecebidosRespostasTruco = new ArrayList<Lance>();
    }

    public boolean chegouFimDaRodada() {
        return (mesa.getJogadorDaVez().getNome().equals(jogadorAtual.getNome())
                && mesa.getRodadaAtual() != null && mesa.getRodadaAtual().getQuantidadeLances() == 4);
    }

    public Jogador atualizaVencedorNaMesaAoFinalDaRodada() {
        Jogador jog = mesa.getVencedorRodada();
        mesa.getRodadaAtual().setVencedorRodada(jog);
        mesa.getMao().getRodadas().add(mesa.getRodadaAtual());

        return jog;
    }

    public void setJogadorDaVez(Jogador jog) {
           Jogador jogadorTemp = null;
           if (!jog.getNome().equals("Empate")) {
               jogadorTemp = jog;
                //mesa.setJogadorDaVez(jog);
            } else {
               jogadorTemp = this.getJogadorAtual();
                //mesa.setJogadorDaVez(jogadorAtual);
            }
           mesa.iniciarRodada(jogadorTemp);
    }

    public void verificaFimRodada() {


        if (this.chegouFimDaRodada()) {

            Jogador jog = this.atualizaVencedorNaMesaAoFinalDaRodada();

            this.setJogadorDaVez(jog);

            interfaceMesa.atualizaJogadorVez(mesa);
            mesa.setStatus(Mesa.StatusMesa.INICIAR_RODADA);

            rede.enviarJogada(mesa);
            this.receberJogada(mesa);

            try {
                Thread.sleep(1500L);
            } catch (InterruptedException ex) {
                Logger.getLogger(AtorJogador.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.verificaFimMao();

        }

    }

    public void verificaFimMao() {
        int size = mesa.getMao().getRodadas().size();
        Dupla vencedora = null;

        if (size >= 2) {

            // System.out.println("termino a mao");

            boolean venceuDuasPartidas = false;
            if (size == 2) {

                vencedora = mesa.verificaSeUmaDuplaGanhouDuasRodadas();

                if (vencedora != null) {
                    venceuDuasPartidas = true;
                }
            }

            if (size == 3 || venceuDuasPartidas) {

                if (vencedora == null) {
                    vencedora = mesa.getDuplaVencedoraMao();
                }

                int valorMesa = mesa.getValorMao();

                this.setFimMao(vencedora, valorMesa);

            }

        }
    }

    public void atualizaPlacarDuplaVencedora(Dupla vencedora, int valorMesa) {
            if (vencedora.equals(Dupla.DUPLA_A)) {
                mesa.setPlacarA(mesa.getPlacarA() + valorMesa);
            } else {
                mesa.setPlacarB(mesa.getPlacarB() + valorMesa);
            }
    }

    public void setFimMao(Dupla vencedora, int valorMesa, Mesa.StatusMesa status) {

        if (vencedora != null) {

           this.atualizaPlacarDuplaVencedora(vencedora, valorMesa);

        }

        mesa.setStatus(status);

        mesa.iniciarMao();
        rede.enviarJogada(mesa);
        this.receberJogada(mesa);
        this.verificaFimPartida();
    }

    private void verificaFimPartida() {

        if (mesa.acabouPartida()) {

            try {
                Thread.sleep(1500L);
            } catch (InterruptedException ex) {
                Logger.getLogger(AtorJogador.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.iniciarNovaPartida();

        }

    }

    public void setFimMao(Dupla vencedora, int valorMesa) {


        this.setFimMao(vencedora, valorMesa, Mesa.StatusMesa.INICIAR_MAO);
    }

    public boolean tratarExecucaoJogada() {
        return this.ehJogadorDaVez(jogadorAtual) && this.isConectado();
    }

    public boolean ehJogadorDaVez(Jogador jogador) {
        return (jogador.getNome().equals(mesa.getJogadorDaVez().getNome()));
    }

    public boolean efetuarJogada(Carta carta) {
        boolean retorno = false;

        if (tratarExecucaoJogada()) {
            Lance lance = new Lance();
            lance.setCarta(carta);
            lance.setJogador(jogadorAtual);
            retorno = true;
            rede.enviarJogada(lance);

            this.receberJogada(lance);
        }



        return retorno;
    }

    public void atualizarVisibilidadeTela(int mode) {

        interfaceMesa.atualizarVisibilidadeTela(mode);
    }

     public Jogador getJogadorAtual() {
        return jogadorAtual;
    }

    public void setJogadorAtual(Jogador jogadorAtual) {
        this.jogadorAtual = jogadorAtual;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public AtorNetGames getRede() {
        return rede;
    }

    public void setRede(AtorNetGames rede) {
        this.rede = rede;
    }

    public void exibeMensagem(String mensagem) {
        interfaceMesa.exibeMensagem(mensagem);
    }

    public void setTruco(Dupla dupla) {

        mesa.setStatus(Mesa.StatusMesa.TRUCO);

        if (mesa.getDuplaQuePediuTruco() != null) {
            
            mesa.setValorMao(mesa.getValorMao() + 3);

        } else {
           
            mesa.setValorMao(3);
        }

        
        mesa.setDuplaQuePediuTruco(dupla);
        this.limparLancesRecebidos();
        rede.enviarJogada(mesa);
        this.receberJogada(mesa);
    }

     public void recebeRespostaTruco(List<Lance> lances) {


        Lance primeiro = lances.get(0);
        Lance segundo = lances.get(1);

        if (primeiro.getJogador().getNome().equals(this.getJogadorAtual().getNome())) {
            if (primeiro.getRespostaTruco().isAceitou() && segundo.getRespostaTruco().isAceitou()) {

               this.setTruco(primeiro.getJogador().getDupla());

            } else {

               this.setFimMao(Dupla.getDuplaAdiversaria(primeiro.getRespostaTruco().getJogadorQueRespondeu().getDupla()), mesa.getValorMao());
            }

        }

    }

    public void desistir() {
        if (tratarExecucaoJogada()) {

            this.setFimMao(this.getJogadorAtual().getProximoAJogar().getDupla(), this.getMesa().getMao().getValorDaMao(), Mesa.StatusMesa.DESISTIR);


        }
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

}
