package coliseumrpg;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Classes.Cacador;
import Classes.Classes;
import Classes.Guerreiro;
import Classes.Mago;
import Erros.ErroTolo;
import Erros.ForaDoAlcanceException;
import Erros.SemRecursoParaAtoException;
import NetGames.ControladorNetGames;
import InterfaceVisual.ControladorTelas;
import Mapa.Lugar;
import Mapa.Mapa;
import NetGames.Ato;
import NetGames.Jogador;
import NetGames.Time;
import Poderes.Poder;
import Poderes.TipoDePoderes.PoderAutoModificacao;
import Poderes.TipoDePoderes.Custo;
import Poderes.TipoDePoderes.PoderLocalAlvo;
import coliseumrpg.Personagem;
import coliseumrpg.Turno;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus
 */
public class ColiseumRPG {

    protected static final ColiseumRPG instance = new ColiseumRPG();
    protected Jogador jogadores[];
    protected Turno turno;
    protected Mapa mapa;

    private ColiseumRPG() {
        jogadores = new Jogador[2]; //Por definição o jogadores[0] sempre será o da instância do jogo
    }                               //e o jogadores[1] o adversário

    public static void main(String[] args) {
        ControladorTelas.getInstance().abrirTelaInicial();
    }

    /**
     *
     * @param time ao qual o jogador a ser criado deve pertencer
     * @param p1 primeira classe escolhida
     * @param p2 segunda classe escolhida
     */
    public void comecar(Classes p1, Classes p2) {
        jogadores[0] = new Jogador(criarPersonagem(p1), criarPersonagem(p2));
        ControladorNetGames.getInstance().conectar("localhost");
    }

    public void iniciarPartida(boolean minhaVez) {
        mapa = new Mapa();
        if (minhaVez) {
            jogadores[0].setTime(Time.AZUL);
            turno = jogadores[0].tomarVez();
            mapa.adicionaPersonagensPrimeiroAJogar(jogadores[0].getPersonagens()[0], jogadores[0].getPersonagens()[1]);
        } else {
            jogadores[0].setTime(Time.VERMELHO);
            mapa.adicionaPersonagensSegundoAJogar(jogadores[0].getPersonagens()[0], jogadores[0].getPersonagens()[1]);
        }
        ControladorTelas.getInstance().abrirTelaEmJogo(mapa.getAlteracoes(), mapa.getPosicoesPersonagens(), jogadores[0].getTime());
        if (minhaVez) {
            passarVez();
        }
    }

    private void atualizaTelaEEnviaJogada() {
        ControladorTelas.getInstance().mostrarResultadoAto(new Ato(turno, mapa.getPosicoesPersonagens(), mapa.getAlteracoes(), getPersonagens()), jogadores[0].getTime());
        Ato ato = gerarAto();
        enviarJogada(ato);
    }

    private Ato gerarAto() {
        return new Ato(
                turno,
                mapa.getPosicoesPersonagens(),
                mapa.getAlteracoes(),
                getPersonagensInvertidos());
    }

    private Ato enviarJogada(Ato ato) {
        ControladorNetGames.getInstance().enviarJogada(ato);
        mapa.resetAlteracoes();
        return ato;
    }

    /**
     * Atualiza o jogo local com as ações do adversário, algumas coisas são
     * requeridas para que funcine normalmente:
     *
     * -Cada jogo local considera que o jogador 0 é o seu, esse método deve
     * receber ja na ordem certa, com personagens[0] sendo o seu e
     * personagens[1] do advesário
     *
     * -Apenas as instancias de personagens vindas do hashmap
     * novaposicaoPersonagens podem ser usados, pois cada vez que se envia
     * jogada as instancias que chegam do outro lado são outras que ja não se
     * refereciam (se enviarmos duas referencias a mesma classe receberemos duas
     * classes clones do outro lado)
     *
     * @param ato As alterações feitas na ação mais recente do turno inimigo
     */
    public void tratarJogada(Ato ato) {
        //Pega as novas informações de turno e coloca no dessa maquina

        //Se for a primeira jogada recebida desse jogador
        if (jogadores[1] == null) {
            //toma o turno para si
            turno = jogadores[0].tomarVez();

            //Cria o jogador inimigo com os personagens e time recebidos
            Personagem[] adversarios = new Personagem[2];
            for (Personagem p : ato.getNovaPosicoesPersonagens().keySet()) {
                if (p.getTime().equals(ato.getTurno().getTime())) {
                    if (p.ehEssaClasse(ato.getTurno().getPersonagem().getClasse())) {
                        adversarios[0] = p;
                    } else {
                        adversarios[1] = p;
                    }
                }
            }
            jogadores[1] = new Jogador(adversarios[0], adversarios[1]);
            jogadores[1].setTime(ato.getTurno().getTime());

            //Coloca o nome de cada personagem de cada jogador na tela
            ControladorTelas.getInstance()
                    .setClassesJogadores(
                            jogadores[0].getPersonagens()[0].getNome(),
                            jogadores[0].getPersonagens()[1].getNome(),
                            jogadores[1].getPersonagens()[0].getNome(),
                            jogadores[1].getPersonagens()[1].getNome());

            //Atualiza no mapa a nova posição dos personagens
            mapa.atualizar(ato.getAlteracoesMapa(), ato.getNovaPosicoesPersonagens());

            //Atualiza a na tela
            ControladorTelas.getInstance().atualizaMapa(ato.getAlteracoesMapa(), ato.getNovaPosicoesPersonagens(), jogadores[0].getTime());
            ControladorTelas.getInstance().atualizaInformacoesTurno(turno, jogadores[0].getTime());

            //Se for o segundo a jogar passa a vez
            if (jogadores[0].getTime() == Time.VERMELHO) {
                passarVez();
            } else {
                //Se for o primeiro a jogar atualiza a lista de poderes e envia as informações completas sobre o turno
                ControladorTelas.getInstance().atualizaBotoesEspecialidades(turno.getPersonagem().getPoderes());
                atualizaTelaEEnviaJogada();
            }
        } else {
            turno = ato.getTurno();
            //Atualiza os personagens com as alterações
            Personagem[] aliados = new Personagem[2];
            Personagem[] adversarios = new Personagem[2];
            ato.getNovaPosicoesPersonagens().keySet().forEach((p) -> {
                if (p.getTime().equals(jogadores[0].getTime())) {
                    if (p.ehEssaClasse(jogadores[0].getPersonagens()[0].getClasse())) {
                        aliados[0] = p;
                    } else {
                        aliados[1] = p;
                    }
                } else {
                    if (p.ehEssaClasse(jogadores[1].getPersonagens()[0].getClasse())) {
                        adversarios[0] = p;
                    } else {
                        adversarios[1] = p;
                    }
                }
            });
            jogadores[0].setPersonagens(aliados);
            jogadores[1].setPersonagens(adversarios);
            mapa.atualizar(ato.getAlteracoesMapa(), ato.getNovaPosicoesPersonagens());

            //Atualiza na tela as mudanças do turno inimigo
            ControladorTelas.getInstance().mostrarResultadoAto(ato, jogadores[0].getTime());

            //Encerra o jogo caso alguem tenha perdido
            if (isJogoAcabado()) {
                encerrarJogo();
            } else if (!turno.isTurnoAtivo()) {
                //Senão e o turno inimigo estiver encerrado
                //vamos começar o turno do próximo personagem desse jogador
                turno = jogadores[0].tomarVez();
                if (turno.getPersonagem().estaIncapacitado()) {
                    turno.encerrar();
                    ControladorTelas.infoDialog("Seu " + turno.getPersonagem().getNome() + " estava incapacitado e não pode se mover nesse turno.");
                }

                //Envia para o outro jogador o novo turno
                atualizaTelaEEnviaJogada();
            }
        }
    }

    private Personagem criarPersonagem(Classes classe) {
        if (new Cacador().ehEssaClasse(classe)) {
            return new Cacador();
        }
        if (new Guerreiro().ehEssaClasse(classe)) {
            return new Guerreiro();
        }
        if (new Mago().ehEssaClasse(classe)) {
            return new Mago();
        }
        throw new UnsupportedOperationException("A classe selecionada: " + classe + " deve ser adicionada na lista criar Personagem na classe ColiseumRPG.");
    }

    private Personagem[][] getPersonagens() {
        Personagem[][] personagensPosicaoAtual = new Personagem[2][2];

        personagensPosicaoAtual[0] = jogadores[0].getPersonagens();

        //Perceba que invertemos a ordem para manter o jogador [0] como o jogador da maquina
        if (jogadores[1] != null) {
            personagensPosicaoAtual[1] = jogadores[1].getPersonagens();
        }

        return personagensPosicaoAtual;
    }

    private Personagem[][] getPersonagensInvertidos() {
        Personagem[][] personagensInvertidos = new Personagem[2][2];
        //Perceba que invertemos a ordem para manter o jogador [0] como o jogador da maquina
        if (jogadores[1] != null) {
            personagensInvertidos[0] = jogadores[1].getPersonagens();
        }

        personagensInvertidos[1] = jogadores[0].getPersonagens();

        return personagensInvertidos;
    }

    public static ColiseumRPG getInstance() {
        return instance;
    }

    public void pedirInicioDePartida() {
        ControladorNetGames.getInstance().pedirIniciarPartida();
    }

    public void atacar(Dimension destino) {
        if (!podeAgir()) {
            throw new ErroTolo("Não é sua vez.");
        }
        if (turno.has(Custo.AtoMaior)) {
            mapa.atacar(turno.getPersonagem(), destino);
            turno.usar(Custo.AtoMaior);
            atualizaTelaEEnviaJogada();
            if (isJogoAcabado()) {
                encerrarJogo();
            }
        } else {
            throw new SemRecursoParaAtoException("Você ja gastou sua ação maior nesse turno. Não pode mais usar habilidades que consumam esse recurso.");
        }
    }

    public void mover(Dimension destino) {
        if (!podeAgir()) {
            throw new ErroTolo("Não é sua vez.");
        }
        if (turno.getPersonagem().getVelocidade() < 1) {
            throw new ForaDoAlcanceException("O personagem ja andou tudo que podia nesse turno.");
        }
        mapa.mover(turno.getPersonagem(), destino);
        turno.getPersonagem().gastarVelocidade();
        atualizaTelaEEnviaJogada();
        if (isJogoAcabado()) {
            encerrarJogo();
        }
    }

    public void passarVez() {
        if (turno.getTime() == jogadores[0].getTime()) {
            turno.encerrar();
            enviarJogada(gerarAto());
        } else {
            throw new ErroTolo("A vez precisa ser sua para passar.");
        }
    }

    public void usarPoderLocalAlvo(PoderLocalAlvo poder, Dimension destino) {
        if (podeAgir()) {
            for (Custo custo : poder.getCustos()) {
                if (!turno.has(custo)) {
                    throw new ErroTolo("Já gastou seu " + custo.toString() + " desse turno.");
                }
            }
            mapa.poderLocalAlvo(mapa.getPosicaoPersonagem(turno.getPersonagem()), poder, destino);
            for (Custo custo : poder.getCustos()) {
                turno.usar(custo);
            }
            atualizaTelaEEnviaJogada();
            if (isJogoAcabado()) {
                encerrarJogo();
            }
        } else {
            throw new ErroTolo("Não é sua vez.");
        }
    }

    public void usarPoderAutoModificacao(PoderAutoModificacao autoModificacao) {
        if (podeAgir()) {
            for (Custo custo : autoModificacao.getCustos()) {
                if (!turno.has(custo)) {
                    throw new SemRecursoParaAtoException("Você ja gastou seu " + custo.toString() + " desse turno.");
                }
            }
            autoModificacao.usar(turno.getPersonagem());
            for (Custo custo : autoModificacao.getCustos()) {
                turno.usar(custo);
            }
            atualizaTelaEEnviaJogada();
            if (isJogoAcabado()) {
                encerrarJogo();
            }
        } else {
            throw new ErroTolo("Não é sua vez.");
        }
    }

    public void desistir() {
        if (podeAgir()) {
            for (Personagem p : jogadores[0].getPersonagens()) {
                p.receberDano(999);
            }
            passarVez();
            encerrarJogo();
        } else {
            throw new ErroTolo("Você só pode se render na sua vez");
        }
    }

    public void encerrarJogo() {
        ControladorNetGames.getInstance().desconectar();
        ControladorTelas.getInstance().abrirTelaInicial();;
    }

    public Poder[] getPoderesPersonagemDoTurno() {
        return turno.getPersonagem().getPoderes();
    }

    public Turno getTurno() {
        return turno;
    }

    private boolean podeAgir() {
        return turno.isTurnoAtivo() && turno.getTime().equals(jogadores[0].getTime());
    }

    private boolean isJogoAcabado() {
        for (Jogador j : jogadores) {
            boolean personagemUmMorto = false;
            for (Personagem p : j.getPersonagens()) {
                if (p.estaVivo()) {
                    break;
                } else {
                    if (personagemUmMorto) {
                        return true;
                    } else {
                        personagemUmMorto = true;
                    }
                }

            }
        }
        return false;
    }
}
