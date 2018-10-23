package interfaceGrafica;

import javax.swing.*;
import java.awt.*;

import estatico.PosicoesEnum;

public class InterfaceComprar extends JDialog{

    private JLabel img;
    private JLabel preco;
    private JButton comprar;
    private JButton cancelar;
    private boolean comprou;

    public boolean isComprou() {
        return comprou;
    }

    private GridBagConstraints constraints;


    public InterfaceComprar(InterfaceBancoImobiliario ib, int posicao) {
        super();
        getContentPane().setLayout(new java.awt.GridBagLayout());
        PosicoesEnum pos = PosicoesEnum.values()[posicao];
        setTitle("Deseja comprar a propriedade "+ pos.getNome()+" ?");
        setModal(true);
        init(ib,pos);
    }

    private void init(InterfaceBancoImobiliario ib, PosicoesEnum pos){
        setLocationRelativeTo(ib);
        JPanel painelTop = new JPanel();
        JPanel painelBottom = new JPanel();
        img = new JLabel(new ImageIcon(getClass().getResource("images/" + pos.getNome() + ".png")));
        preco = new JLabel("R$ 200");
        comprar = new JButton("Comprar");
        cancelar = new JButton("Cancelar");

        painelTop.setLayout(new java.awt.GridBagLayout());

        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        painelTop.add(img, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        painelTop.add(painelBottom, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 175;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(painelTop, gridBagConstraints);


        painelBottom.add(comprar);
        painelBottom.add(cancelar);

        comprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                comprou = true;
                dispose();
            }
        });
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                comprou = false;
                dispose();
            }
        });
        setResizable(false);
        setSize(500,550);
        setVisible(true);
    }

}
