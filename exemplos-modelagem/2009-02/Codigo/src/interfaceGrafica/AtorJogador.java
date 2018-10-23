package interfaceGrafica;

import dominio.*;
import estatico.PosicoesEnum;
import rede.AtorNetGames;

import java.util.List;
import java.util.Map;


public class AtorJogador {

    private Jogador jogadorLocal;
    private JogadaImpl jogada;
    private Tabuleiro tabuleiro;
    private AtorNetGames rede;
    private InterfaceBancoImobiliario ib;

    public AtorJogador(InterfaceBancoImobiliario ib) {
        super();
        this.ib = ib;
        rede = new AtorNetGames(this);
    }

    public InterfaceBancoImobiliario informarJanela() {
        return ib;
    }


    public List<Integer> informarHipotecaveis() {
        return jogadorLocal.informarHipotecaveis();
    }

    public List<Integer> informarVendiveis() {
        return jogadorLocal.informarVendiveis();
    }

    public List<Integer> informarHipotecadas() {
        return jogadorLocal.informarHipotecadas();
    }

    public List<Integer> informarMelhoraveis() {
        return jogadorLocal.informarMelhoraveis(tabuleiro.getQuantidadePorCor());
    }

    public void hipotecar(int id) {
        Propriedade p = (Propriedade) tabuleiro.getPosicao(id);
        jogadorLocal.hipotecar(p);
        jogada.adicionarHipoteca(id);
    }

    public int vender(int id) {
        Propriedade p = (Propriedade) tabuleiro.getPosicao(id);
        int quant = jogadorLocal.vender(p);
        jogada.venderPropriedadeOuCasa(id);
        return quant;
    }

    public boolean desipotecar(int id) {
        Propriedade p = (Propriedade)tabuleiro.getPosicao(id);
        double capital = jogadorLocal.getCapital();
        if(capital < p.custoDesipoteca()){
            jogadorLocal.desipotecar(p);
            jogada.adicionarPropriedadeDesipotecada(id);
            return true;
        }
        return false;
    }

    public int melhorar(int id) {
        Terreno p = (Terreno) tabuleiro.getPosicao(id);
        if (jogadorLocal.getCapital() < p.getCustoPorCasa())
            return -1;
        p.incrementar1Casa();
        jogadorLocal.debitar(p.getCustoPorCasa());
        jogada.adicionarMelhora(id, p.getNumeroDeCasas());
        return p.getNumeroDeCasas();
    }

    public boolean conectar() {
        String jogador = ib.obterDadosJogador();
        String servidor = ib.obterIdServidor();
        boolean exito = rede.conectar(servidor, jogador);
        if (exito) {
            this.jogadorLocal = new Jogador(jogador);
        }
        return exito;
    }

    public void iniciarPartida() {
        int motivo = rede.iniciarPartida();
        if (motivo > 0) {
            alertarInicioNegado(motivo);
        }
    }


    public void iniciarJogada() {

        int resultado1 = obterRand();
        int resultado2 = obterRand();
        if (resultado1 == resultado2) {
            jogadorLocal.marcarRepetirJogada();
        } else {
            jogadorLocal.limparRepetirJogada();
        }
        if (jogadorLocal.getRepeticoes() == 3) {
            this.enviarJogadorLocalParaPrisao();
            this.finalizarJogada();
        } else {
            int deslocamento = resultado1 + resultado2;
            ib.alert("Você tirou " + resultado1 + " e " + resultado2);
            boolean passouPeloInicio = jogadorLocal.posicionar(deslocamento);
            if (passouPeloInicio) {
                jogadorLocal.creditar(200);
                ib.alert("Você ganhou $200");
            }
            ib.posicionarPeao(jogadorLocal.getOrdem(), jogadorLocal.getPosicao());
            jogada.setPosicao(jogadorLocal.getPosicao());
            tratarPosicao(deslocamento);
            if (!jogadorLocal.isPreso()) {
                if (jogadorLocal.getRepeticoes() > 0) {
                    ib.abrirJogarDados(true);
                } else {
                    ib.mostrarBotoes();
                }
            }
        }
    }

    public void enviarJogadorLocalParaPrisao() {
        jogadorLocal.enviaParaAPrisao();
        ib.posicionarPeao(jogadorLocal.getOrdem(), PosicoesEnum.PRISAO.getId());
        ib.alert("Você foi para a prisão");
        jogada.setPosicao(PosicoesEnum.PRISAO.getId());
        finalizarJogada();
    }

    public void tratarPosicao(int deslocamento) {
        Posicao posicao = tabuleiro.getPosicao(jogadorLocal.getPosicao());
        if (posicao.getTipoPosicao() == PosicoesEnum.VA_PARA_PRISAO) {
            enviarJogadorLocalParaPrisao();
        } else if (posicao.getTipoPosicao() == PosicoesEnum.IMPOSTO_RENDA) {
            jogadorLocal.debitar(200);
            ib.alert("Você caiu no Imposto de Renda. Pague 200");
        } else if (posicao.getTipoPosicao() == PosicoesEnum.LUCROS_DIVIDENDOS) {
            jogadorLocal.creditar(200);
            ib.alert("Suas ações deram lucro. Receba 200");
        } else if (posicao instanceof SorteOuReves) {
            CartaSorteOuReves carta = SorteOuReves.sortearCarta();
            switch (carta.getTipoDeCarta()) {
                case PRISAO:
                    enviarJogadorLocalParaPrisao();
                    break;
                case SAIDA_LIVRE:
                    jogadorLocal.receberSaidaLivre();
                    ib.alert("Você recebeu saída livre. (Se você já tinha, continuará com apenas uma)");
                    break;
                default:
                    jogadorLocal.creditar(carta.getValor());
                    ib.alert(carta.getDescricao() + " " + carta.getValor());
                    break;
            }
        } else if (posicao instanceof Propriedade) {
            Propriedade propriedade = (Propriedade) posicao;
            if (propriedade.getDono() == null) {
                realizarCompra(propriedade);
            } else if (propriedade.getDono() != jogadorLocal && !propriedade.isHipotecada()) {
                int custoHospedagem = propriedade.calcularHospedagem(deslocamento);
                jogadorLocal.debitar(custoHospedagem);
                Jogador dono = propriedade.getDono();
                dono.creditar(custoHospedagem);
                ib.alert("Você teve que pagar " + custoHospedagem + " por parar na propriedade do jogador " + dono.getNome());
                ib.atualizarValor(dono.getOrdem(), dono.getCapital());
            }
        }
        ib.atualizarValor(jogadorLocal.getOrdem(), jogadorLocal.getCapital());
    }

    public void realizarCompra(Propriedade propriedade) {
        double capital = jogadorLocal.getCapital();
        if (capital > propriedade.getValor()) {
            if (ib.perguntarComprar(jogadorLocal.getPosicao())) {
                jogadorLocal.comprarPropriedade(propriedade);
                ib.marcarPosicao(jogadorLocal.getPosicao(), jogadorLocal.getOrdem());
                ib.atualizarValor(jogadorLocal.getOrdem(), jogadorLocal.getCapital());
                jogada.assumirCompra(jogadorLocal.getPosicao());
            }
        }
    }

    public void finalizarJogada() {
        tabuleiro.passarParaProxJogador();
        jogada.setNovosCaptais(tabuleiro.informarCapitais());
        avisarQuemEstaJogando();
        ib.esconderBotoes();
        double capital = jogadorLocal.getCapital();
        if (capital < 0) {
            falirJogador(jogadorLocal.getOrdem());
            ib.alert("Você foi à falência");
        }
        rede.enviarJogada(jogada);
    }


    public void receberJogada(JogadaImpl jogada) {
        Jogador adversario = tabuleiro.informarJogadorDaVez();

        //compra
        atualizarCompras(jogada, adversario);
        //vendidas
        atualizarVendas(jogada, adversario);
        //hipotecadas
        atualizarHipotecadas(jogada, adversario);
        //desipotecadas
        atualizarDesipotecadas(jogada, adversario);
        //alteradas
        atualizarPropriedadesAlteradas(jogada);
        //capitais
        atualizarCapitaisEFalencias(jogada);
        //posicao
        ib.posicionarPeao(adversario.getOrdem(), jogada.getPosicao());
        adversario.setPosicao(jogada.getPosicao());

        ib.limparFeedBack();

        //fluxo da jogada
        tabuleiro.passarParaProxJogador();
        if (tabuleiro.informarJogadorDaVez() == jogadorLocal) {
            if (tabuleiro.unicoJogador()) {
                ib.alert("Você é o vencedor!!");
                rede.finalizarPartida();
            } else if (jogadorLocal.isPreso()) {
                tentarSairDaPrisao();
            } else {
                this.jogada = new JogadaImpl(jogadorLocal.getOrdem());
                ib.abrirJogarDados(true);
            }
        } else {
            this.avisarQuemEstaJogando();
        }
    }

    private void atualizarCompras(JogadaImpl jogada, Jogador adversario) {
        for (Integer propriedade : jogada.informarCompradas()) {
            Posicao comprada = tabuleiro.getPosicao(propriedade);
            ib.marcarPosicao(propriedade, adversario.getOrdem());
            adversario.comprarPropriedade((Propriedade) comprada);
        }
    }

    private void atualizarVendas(JogadaImpl jogada, Jogador adversario) {
        for (Integer propriedade : jogada.getPropriedadesVendidas()) {
            adversario.vender(propriedade);
            ib.desmarcarPosicao(propriedade);
        }
    }

    private void atualizarHipotecadas(JogadaImpl jogada, Jogador adversario) {
        for (Integer propriedade : jogada.getPropriedadesHipotecadas()) {
            Posicao hipotecada = tabuleiro.getPosicao(propriedade);
            adversario.hipotecar((Propriedade) hipotecada);
        }
    }

    private void atualizarDesipotecadas(JogadaImpl jogada, Jogador adversario) {
        for (Integer propriedade : jogada.getPropriedadesDesipotecadas()) {
            Posicao desipotecada = tabuleiro.getPosicao(propriedade);
            adversario.desipotecar((Propriedade) desipotecada);
        }
    }

    private void atualizarPropriedadesAlteradas(JogadaImpl jogada) {
        for (Map.Entry<Integer, Integer> propriedadeECasas : jogada.getPropriedadesAlteradas().entrySet()) {
            ib.setNumeroDeCasas(propriedadeECasas.getKey(), propriedadeECasas.getValue());
            Terreno alterado = (Terreno) tabuleiro.getPosicao(propriedadeECasas.getKey());
            alterado.setNumeroDeCasas(propriedadeECasas.getValue());
        }
    }

    private void atualizarCapitaisEFalencias(JogadaImpl jogada) {
        int[] capitais = jogada.getNovosCaptais();
        for (int i = 0; i < capitais.length; i++) {
            tabuleiro.alterarCapital(i, capitais[i]);
            ib.atualizarValor(i, capitais[i]);
            if (capitais[i] < 0) {
                falirJogador(i);
            }
        }
    }

    private void falirJogador(int i) {
        int[] posicoesParaRemover = tabuleiro.falirJogador(i);
        for (int aPosicoesParaRemover : posicoesParaRemover) {
            ib.setNumeroDeCasas(aPosicoesParaRemover, 0);
            ib.desmarcarPosicao(aPosicoesParaRemover);
        }
    }

    private int obterRand() {
        return (int) (Math.random() * 6) + 1;
    }

    public void tentarSairDaPrisao() {
        if (jogadorLocal.esgotouTentativasSairPrisao()) {
            jogadorLocal.debitar(50);
            jogadorLocal.sairDaPrisao();
            ib.abrirJogarDados(true);
        } else {
            boolean tentarSair = true;
            if (jogadorLocal.getCapital() >= 50) {
                tentarSair = ib.perguntarFormaSairDaPrisao();
            }

            if (!tentarSair) {
                jogadorLocal.debitar(50);
                jogadorLocal.sairDaPrisao();
                ib.abrirJogarDados(true);
            } else {
                ib.abrirJogarDados(false);
                int resultado1 = obterRand();
                int resultado2 = obterRand();
                if (resultado1 == resultado2) {
                    jogadorLocal.sairDaPrisao();
                    ib.abrirJogarDados(true);
                } else {
                    boolean usar = false;
                    if (jogadorLocal.temSaidaLivre()) {
                        usar = ib.perguntarUsarSaidaLivre();
                        if (usar) {
                            jogadorLocal.sairDaPrisao();
                            jogadorLocal.usarSaidaLivre();
                            ib.abrirJogarDados(true);
                        }
                    }
                    if (!usar) {
                        jogadorLocal.aumentarTentativas();
                        jogada = new JogadaImpl(jogadorLocal.getOrdem());
                        finalizarJogada();
                        ib.alert("Você tirou " + resultado1 + " e " + resultado2 + " e não conseguiu sair.");
                    }
                }
            }
        }
    }

    public void alertarInicioNegado(int motivo) {
        switch (motivo) {
            case 1:
                ib.alert("Você não está conectado");
                break;
            case 2:
                ib.alert("A partida já está em andamento");
                break;
            case 3:
                ib.alert("Não há jogadores suficients");
                break;
        }
    }

    public void processarInicioPartida(List<String> adversarios, int ordemJogadorLocal) {
        ordemJogadorLocal--;
        tabuleiro = new Tabuleiro();
        tabuleiro.adicionarAdversarios(adversarios);
        tabuleiro.adicionarJogador(ordemJogadorLocal, jogadorLocal);
        if (ordemJogadorLocal == 0) {
            jogada = new JogadaImpl(0);
            ib.abrirJogarDados(true);
        } else {
            this.avisarQuemEstaJogando();
        }

        List<String> jogadores = concatJogadores(adversarios,jogadorLocal);
        ib.mostrarJogadores(jogadores);
    }

    private List<String> concatJogadores(List<String> adversarios, Jogador jogador) {
        adversarios.add(jogador.getOrdem(), jogador.getNome());
        return adversarios;
    }


    public void avisarQuemEstaJogando() {
        ib.mensagem("Vez do Jogador " + tabuleiro.informarJogadorDaVez().getNome());
    }

    public void desconectar() {
        rede.desconectar();
    }
}