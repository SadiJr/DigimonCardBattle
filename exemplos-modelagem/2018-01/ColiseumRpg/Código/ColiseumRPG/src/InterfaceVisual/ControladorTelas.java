package InterfaceVisual;

import Classes.Classes;
import Mapa.Lugar;
import NetGames.Ato;
import javax.swing.JOptionPane;
import NetGames.Time;
import Poderes.Poder;
import Poderes.TipoDePoderes.PoderAutoModificacao;
import Poderes.TipoDePoderes.PoderLocalAlvo;
import coliseumrpg.ColiseumRPG;
import coliseumrpg.Personagem;
import coliseumrpg.Turno;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.HashMap;

public class ControladorTelas {

    private final Janela janela;
    private final MenuInicial menuInicial;
    private final TelaEmJogo telaEmJogo;
    
    private static final String MENU_INICIAL = "MENU_INICIAL";
    private static final String TELA_EM_JOGO = "TELA_EM_JOGO";
    private static final String TELA_FIM_DE_JOGO = "TELA_FIM_DE_JOGO";

    private static ControladorTelas instance ;

    public static ControladorTelas getInstance() {
        if(instance == null){
            instance = new ControladorTelas();
        }
        return instance;
    }

    private ControladorTelas() {
        janela = new Janela();
        telaEmJogo = new TelaEmJogo();
        menuInicial = new MenuInicial();
        janela.getContentPane().add(MENU_INICIAL, menuInicial);
        janela.getContentPane().add(TELA_EM_JOGO, telaEmJogo);
    }

    public void abrirTelaInicial() {
        janela.setVisible(true);
        janela.menuSuperiorVisivel(false);
        ((CardLayout) (janela.getContentPane().getLayout())).show(janela.getContentPane(), MENU_INICIAL);
        janela.pack();
    }

    public void abrirTelaEmJogo(HashMap<Dimension, Lugar> locaisAlterados, HashMap<Personagem, Lugar> posicaoPersonagens, Time timeDoJogadorLocal) {
        janela.menuSuperiorVisivel(true);
        ((CardLayout) (janela.getContentPane().getLayout())).show(janela.getContentPane(), TELA_EM_JOGO);
        janela.pack();
        telaEmJogo.atualizaMapa(locaisAlterados, posicaoPersonagens, timeDoJogadorLocal);
    }

    public void abrirTelaFimDeJogo() {
        janela.menuSuperiorVisivel(false);
        ((CardLayout) (janela.getContentPane().getLayout())).show(janela.getContentPane(), TELA_FIM_DE_JOGO);
        janela.pack();
    }

    public static void errorDialog(String mensagem) {
        JOptionPane.showMessageDialog(getInstance().janela, mensagem, "Mensagem de Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void infoDialog(String mensagem) {
        JOptionPane.showMessageDialog(getInstance().janela, mensagem, "Mensagem de Erro", JOptionPane.INFORMATION_MESSAGE);
    }

    void comecar( Classes class1, Classes class2) {
        ColiseumRPG.getInstance().comecar(class1, class2);
    }

    void mover(Dimension destino) {
        try {
            ColiseumRPG.getInstance().mover(destino);
        } catch (RuntimeException e) {
            errorDialog(e.getMessage());
        }
    }

    void atacar(Dimension destino) {
        try {
            ColiseumRPG.getInstance().atacar(destino);
        } catch (RuntimeException e) {
            errorDialog(e.getMessage());
        }
    }

    void passarVez() {
        try {
            ColiseumRPG.getInstance().passarVez();
        } catch (RuntimeException err) {
            errorDialog(err.getMessage());
        }
    }

    Poder[] getPoderesPersonagemDoTurno() {
        try {
            return ColiseumRPG.getInstance().getPoderesPersonagemDoTurno();
        } catch (RuntimeException e) {
            errorDialog(e.getMessage());
        }
        return null;
    }

    void usarPoderLocalAlvo(PoderLocalAlvo poder, Dimension destino) {
        try {
            ColiseumRPG.getInstance().usarPoderLocalAlvo(poder, destino);
        } catch (RuntimeException err) {
            errorDialog(err.getMessage());
        }
    }

    void usarPoderAutoModificacao(PoderAutoModificacao autoModificacao) {
        try {
            ColiseumRPG.getInstance().usarPoderAutoModificacao(autoModificacao);
        } catch (RuntimeException err) {
            errorDialog(err.getMessage());
        }
    }

    void desistir() {
        try {
            ColiseumRPG.getInstance().desistir();
        } catch (RuntimeException err) {
            errorDialog(err.getMessage());
        }
    }

    public void mostrarResultadoAto(Ato ato, Time timeDoJogadorLocal) {
        telaEmJogo.atualizaTela(ato, timeDoJogadorLocal);
    }

    public void atualizaInformacoesTurno(Turno turno,  Time timeDoJogadorLocal) {
        telaEmJogo.atualizaTurno(turno, timeDoJogadorLocal);
    }
    
    public void atualizaMapa(HashMap<Dimension, Lugar> locaisAlterados, HashMap<Personagem, Lugar> posicaoPersonagens, Time timeJogadorLocal){
        telaEmJogo.atualizaMapa(locaisAlterados, posicaoPersonagens, timeJogadorLocal);
    }
    
    public void atualizaBotoesEspecialidades(Poder [] poderes){
        telaEmJogo.atualizaBotoesPoderes(poderes);
    }

    public void setClassesJogadores(String pers1Jog1, String pers2Jog1, String pers1Jog2, String pers2Jog2) {
        telaEmJogo.setClassesPersonagensJogadores(pers1Jog1, pers2Jog1, pers1Jog2, pers2Jog2);
    }
}
