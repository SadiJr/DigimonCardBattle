package interfaceGrafica;

import javax.swing.*;

public class BancoImobiliario {
    public static void main(String[] args) {

        /*Propriedade p = new Terreno();
        p.setTipoPosicao(PosicoesEnum.PARTIDA);
        p.setNumeroDeCasas(0);
        Jogador j = new Jogador();
        j.setPropriedades(p);*/

        InterfaceBancoImobiliario janela;
        janela = new InterfaceBancoImobiliario();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }
}
