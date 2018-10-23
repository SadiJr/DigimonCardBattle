package DaNumba;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import java.util.Vector;

//~--- classes ----------------------------------------------------------------

public class ControlePrincipal extends JFrame implements WindowListener {
    private Partida  objPartida;
    private TelaMenu objTelaMenu;
    private TelaMensagem objTelaMensagem;
    private TelaInstrucoes objTelaInstrucoes;
    private TelaCreditos objTelaCreditos;
    private Som objSom;

    //~--- constructors -------------------------------------------------------
    
    //construtor desta classe
    public ControlePrincipal() {
        objTelaMenu = new TelaMenu(this);
        this.addWindowListener(this);
    }

    //~--- methods ------------------------------------------------------------
    
    //reproduz o som passado como argumento
    public void reproduzSom(String pNome) {
        objSom = new Som();
        Thread threadReproduzSom = new Thread(objSom , "somTocado");
        objSom.setAudio(pNome);
        threadReproduzSom.start();
    }
    
    //mostra a tela de Créditos
    public void mostrarCreditos() {
        if (objTelaCreditos == null) {
            objTelaCreditos = new TelaCreditos(new JFrame(),true);
            objTelaCreditos.mostrarTela();
        } else {
            objTelaCreditos.mostrarTela();
        }
    }

    //mostra a tela da mensagem com a mensagem passada por parametro
    public void mostrarMensagem(String pMensagem) {
        if (objTelaMensagem == null) {
            objTelaMensagem = new TelaMensagem(new JFrame(),true);
            objTelaMensagem.mostrarTela(pMensagem);
        } else {
            objTelaMensagem.mostrarTela(pMensagem);
        }
    }
    
    //mostra a tela do ranking
    public void mostrarRanking() {
        if (objTelaInstrucoes == null){
            objTelaInstrucoes = new TelaInstrucoes(new JFrame(),true);
            objTelaInstrucoes.mostrarTela();
        } else {
            objTelaInstrucoes.mostrarTela();
        }
    }

    //mostra a tela do menu
    public void mostrarTelaMenu() throws Exception {
        setTitle("DaNumba");

        java.awt.Dimension screenSize =
            java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - 400) / 2, (screenSize.height - 400) / 2,
                  400, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Mostra o menu geral do jogo
        if (objTelaMenu == null) {
            objTelaMenu = new TelaMenu(this);
            setContentPane(objTelaMenu);
            setVisible(true);
        } else {
            setContentPane(objTelaMenu);
            setVisible(true);
        }
    }
    
    //inicia um novo jogo
    public void novoJogo() {
        objPartida = new Partida(this);
    }
    
    //sai do programa
    public void sairDoPrograma() {
        System.exit(0);
    }

    public void windowActivated(WindowEvent e) {}

    public void windowClosed(WindowEvent e) {}

    public void windowClosing(WindowEvent e) {}

    public void windowDeactivated(WindowEvent e) {}

    public void windowDeiconified(WindowEvent e) {}

    public void windowIconified(WindowEvent e) {}

    public void windowOpened(WindowEvent e) {}
}


//~ Formatted by Jindent --- http://www.jindent.com
