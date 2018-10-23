package interfaceGrafica;

import estatico.PosicoesEnum;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Felipe
 * Date: 06/11/2009
 * Time: 22:28:59
 * To change this template use File | Settings | File Templates.
 */

public class InterfacePosicao extends JPanel {
    private PosicoesEnum posicao;
    private Border defaultBorder;
    public InterfacePosicao(int posicao) {
        super(null);
        this.posicao = PosicoesEnum.values()[posicao];

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Image imagem = new ImageIcon(getClass().getResource("images/" + posicao.getTipo().getId() + ".png")).getImage();
        g2.drawImage(imagem, 1, 1, null);
    }

    public void setCasas(int quant) {
        this.removeAll();
        ImageIcon casa = new ImageIcon(getClass().getResource("images/casa.png"));
        ImageIcon hotel = new ImageIcon(getClass().getResource("images/hotel.png"));
        switch (quant) {
            case 5:
                this.add(new JLabel(hotel)).setBounds(14, 30, 30, 20);
                break;
            case 4:
                this.add(new JLabel(casa)).setBounds(13, 28, 15, 14);
            case 3:
                this.add(new JLabel(casa)).setBounds(30, 28, 15, 14);
            case 2:
                this.add(new JLabel(casa)).setBounds(13, 43, 15, 14);
            case 1:
                this.add(new JLabel(casa)).setBounds(30, 43, 15, 14);
        }
        this.repaint();
    }

    public PosicoesEnum getTipoPosicao() {
        return posicao;
    }


    public void setHightlight(boolean b) {
        if (b) {
            defaultBorder = this.getBorder();
            this.setBorder(BorderFactory.createLineBorder(Color.YELLOW,5));
        } else if (defaultBorder!=null){
            this.setBorder(defaultBorder);
        }
    }
    public void setBorda(Border b) {
        this.setBorder(b);
        this.defaultBorder = b;
    }
}
