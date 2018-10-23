public class AtorJogador {
    private IGUI gui;
    private AtorRede atorRede;
    private Tabuleiro tabuleiro;

    public AtorJogador(IGUI gui) {
        // cria o tratador de jogo, o tabuleiro e a conexão com o netgames
        this.gui = gui;
        this.tabuleiro = new Tabuleiro();
        this.atorRede = new AtorRede(this);
    }

    public void iniciarPartida() {
        // define o nome do jogador
        defineNomeJogador();

        // define o servidor ao qual será conectado
        String msg = new JanelaMensagem("Digite o IP do servidor, deixe em branco para localhost").getInput();

        if (msg.trim().length() == 0) {
            msg = "localhost";
        }

        // faz a conexão com o netgames
        boolean conectou = this.atorRede.conectar(msg, this.getNomeJogador());

        // se conectou manda iniciar a partida
        if (conectou) {
            this.atorRede.iniciarPartida();
        }
    }

    public void defineNomeJogador() {
        String nome = null;
        JanelaMensagem msg = new JanelaMensagem("Digite o nome do jogador");
        while (nome == null) {
            nome = msg.getInput();
        }
        this.tabuleiro.setNomeJogador(nome);

        atualizaInterface();
    }

    private void atualizaInterface() {
        gui.atualizar(this.tabuleiro);
    }

    public void fechar() {
        boolean emAndamento = this.tabuleiro.ispartidaEmAndamento();
        if (emAndamento) {
            this.atorRede.desconectar();
        }

        new JanelaMensagem("Finalizando jogo.").show();
        gui.fechar();
    }

    public void receberJogada(JogadaCustom jogada) {
        this.tabuleiro.processaJogada(jogada);
        atualizaInterface();

        boolean acabou = this.tabuleiro.acabou();
        if (!acabou) {
            this.jogar();
        }
    }

    public void tratarIniciarPartida(Integer position) {
        // atualiza a interface
        atualizaInterface();

        // se for o jogador de posicao 1 inicia jogando, 
        // caso contrário, aguardar
        if (position == 1) {
            this.tabuleiro.setMeuTurno(true);
            atualizaInterface();
            this.jogar();
        }
    }

    public void continuarJogada(Pista pista){
        // pega o jogador 1 (você) para enviar via rede, no outro lado ele
        // será o jogador 2 (oponente)        
        Jogador jogador1 = this.tabuleiro.getJogador1();

        // cria a jogada a ser transmitida via rede
        JogadaCustom jogada = new JogadaCustom();
        jogada.setJogador(jogador1);
        jogada.setPista(pista);

        // envia a jogada pelo netgames
        this.tabuleiro.setMeuTurno(false);
        atualizaInterface();
        this.atorRede.enviarJogada(jogada);
    }
    
    private void jogar() {
        // manda o tabuleiro realizar o procedimento de lance
        this.tabuleiro.setMeuTurno(true);
        atualizaInterface();
        this.tabuleiro.listarPistas(this);
    }

    public String getNomeJogador() {
        return this.tabuleiro.getNomeJogador();
    }

    public String getNomeAdversario() {
        return this.tabuleiro.getNomeAdversario();
    }

    public void setNomeAdversario(String nomeAdversario) {
        this.tabuleiro.setNomeAdversario(nomeAdversario);
    }
}
