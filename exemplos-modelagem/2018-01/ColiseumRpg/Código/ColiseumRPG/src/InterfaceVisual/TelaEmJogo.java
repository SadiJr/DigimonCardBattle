/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceVisual;

import Mapa.Lugar;
import NetGames.Ato;
import NetGames.Time;
import Poderes.Poder;
import Poderes.TipoDePoderes.PoderAutoModificacao;
import Poderes.TipoDePoderes.Custo;
import Poderes.TipoDePoderes.PoderLocalAlvo;
import coliseumrpg.Personagem;
import coliseumrpg.Turno;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import resources.Referencia;

public class TelaEmJogo extends javax.swing.JPanel {

    JButton localButtons[][];
    HashMap<JButton, Dimension> posicaoBotoes;
    ActionListener mapButtonsAtacarListener;
    ActionListener mapButtonsMoverListener;
    ActionListener mapButtonsDefaultListener = evt -> {
        ControladorTelas.errorDialog("Selecione uma ação no menu lateral primeiro.");
    };
    ActionListener mapButtonsListener = mapButtonsDefaultListener;
    AbstractAction mapButtonsAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            mapButtonsListener.actionPerformed(e);
        }
    };

    public TelaEmJogo() {
        preInit();
        initComponents();
        postInit();
        mapButtonslistenerInit();
    }

    private void preInit() {
        localButtons = new JButton[12][20]; //Primeiro Autura, então largura
        posicaoBotoes = new HashMap();

    }

    private void postInit() {
        panelMap.setLayout(new GridLayout(12, 20, 1, 1));
        for (int i = 0; i < localButtons.length; i++) {
            for (int j = 0; j < localButtons[i].length; j++) {
                JButton btn = new JButton();
                posicaoBotoes.put(btn, new Dimension(j, i));
                btn.setSize(30, 30);
                btn.setAction(mapButtonsAction);
                try {
                    ImageIcon image = new ImageIcon(ImageIO.read(Referencia.class.getResource("../resources/campoVerde.png")));
                    btn.setIcon(image);
                } catch (IOException ex) {
                    Logger.getLogger(TelaEmJogo.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Problemas ao carregar icone inicia padrão de bottão do mapa");
                }
                btn.setToolTipText("Um lugar aparentemente vazio.");
                panelMap.add(btn);
                localButtons[i][j] = btn;
            }
        }
        panelMenuRotativoInnerPanel.setLayout(new GridLayout(0, 1, 0, 5));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        panelMenuEscolhas = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        btnAtacar = new javax.swing.JButton();
        btnMover = new javax.swing.JButton();
        btnPassarTurno = new javax.swing.JButton();
        panelMenuRotativo = new javax.swing.JScrollPane();
        panelMenuRotativoInnerPanel = new javax.swing.JPanel();
        panelSeusPersonagens = new javax.swing.JPanel();
        lblVoce = new javax.swing.JLabel();
        vidaPersonagemUmVoce = new javax.swing.JProgressBar();
        lblClassePersonagemUmVoce = new javax.swing.JLabel();
        lblClassePersonagemDoisVoce = new javax.swing.JLabel();
        vidaPersonagemDoisVoce = new javax.swing.JProgressBar();
        panelPersonagensAdversario = new javax.swing.JPanel();
        lblAdversario = new javax.swing.JLabel();
        vidaPersonagemUmAdversario = new javax.swing.JProgressBar();
        lblClassePersonagemDoisAdversario = new javax.swing.JLabel();
        lblClassePersonagemUmAdversario = new javax.swing.JLabel();
        vidaPersonagemDoisAdversario = new javax.swing.JProgressBar();
        panelMap = new javax.swing.JPanel();
        panelTurno = new javax.swing.JPanel();
        lblTituloPanelTurno = new javax.swing.JLabel();
        lblPrefixoJogadorTurno = new javax.swing.JLabel();
        lblJogadorTurno = new javax.swing.JLabel();
        lblPrefixoClassePersonagemTurno = new javax.swing.JLabel();
        lblClassePersonagemTurno = new javax.swing.JLabel();
        lblPrefixoAtoMaiorTurno = new javax.swing.JLabel();
        lblAtoMaiorTurno = new javax.swing.JLabel();
        lblPrefixoAtoMenorTurno = new javax.swing.JLabel();
        lblAtoMenorTurno = new javax.swing.JLabel();
        lblPrefixoPassosTurno = new javax.swing.JLabel();
        lblPassosTurno = new javax.swing.JLabel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(815, 525));
        setMinimumSize(new java.awt.Dimension(815, 525));
        setPreferredSize(new java.awt.Dimension(815, 525));

        panelMenuEscolhas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        panelMenuEscolhas.setMaximumSize(new java.awt.Dimension(200, 360));
        panelMenuEscolhas.setMinimumSize(new java.awt.Dimension(200, 360));
        panelMenuEscolhas.setPreferredSize(new java.awt.Dimension(200, 360));

        btnAtacar.setText("Atacar");
        btnAtacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtacarActionPerformed(evt);
            }
        });

        btnMover.setText("Mover");
        btnMover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoverActionPerformed(evt);
            }
        });

        btnPassarTurno.setText("Passar Vez");
        btnPassarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPassarTurnoActionPerformed(evt);
            }
        });

        panelMenuRotativo.setBorder(null);
        panelMenuRotativo.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelMenuRotativo.setHorizontalScrollBar(null);
        panelMenuRotativo.setInheritsPopupMenu(true);
        panelMenuRotativo.setMinimumSize(new java.awt.Dimension(194, 23));

        javax.swing.GroupLayout panelMenuRotativoInnerPanelLayout = new javax.swing.GroupLayout(panelMenuRotativoInnerPanel);
        panelMenuRotativoInnerPanel.setLayout(panelMenuRotativoInnerPanelLayout);
        panelMenuRotativoInnerPanelLayout.setHorizontalGroup(
            panelMenuRotativoInnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );
        panelMenuRotativoInnerPanelLayout.setVerticalGroup(
            panelMenuRotativoInnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 186, Short.MAX_VALUE)
        );

        panelMenuRotativo.setViewportView(panelMenuRotativoInnerPanel);

        javax.swing.GroupLayout panelMenuEscolhasLayout = new javax.swing.GroupLayout(panelMenuEscolhas);
        panelMenuEscolhas.setLayout(panelMenuEscolhasLayout);
        panelMenuEscolhasLayout.setHorizontalGroup(
            panelMenuEscolhasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(panelMenuRotativo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuEscolhasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMenuEscolhasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAtacar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPassarTurno, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(btnMover, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelMenuEscolhasLayout.setVerticalGroup(
            panelMenuEscolhasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuEscolhasLayout.createSequentialGroup()
                .addComponent(panelMenuRotativo, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAtacar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMover, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPassarTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelSeusPersonagens.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        panelSeusPersonagens.setMaximumSize(new java.awt.Dimension(290, 150));
        panelSeusPersonagens.setMinimumSize(new java.awt.Dimension(290, 150));
        panelSeusPersonagens.setPreferredSize(new java.awt.Dimension(290, 150));

        lblVoce.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblVoce.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVoce.setText("Você");

        vidaPersonagemUmVoce.setForeground(new java.awt.Color(255, 0, 0));

        lblClassePersonagemUmVoce.setText("NomeClasse");

        lblClassePersonagemDoisVoce.setText("NomeClasse");

        vidaPersonagemDoisVoce.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout panelSeusPersonagensLayout = new javax.swing.GroupLayout(panelSeusPersonagens);
        panelSeusPersonagens.setLayout(panelSeusPersonagensLayout);
        panelSeusPersonagensLayout.setHorizontalGroup(
            panelSeusPersonagensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSeusPersonagensLayout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addComponent(lblVoce, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(panelSeusPersonagensLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSeusPersonagensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vidaPersonagemUmVoce, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vidaPersonagemDoisVoce, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelSeusPersonagensLayout.createSequentialGroup()
                        .addGroup(panelSeusPersonagensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblClassePersonagemUmVoce)
                            .addComponent(lblClassePersonagemDoisVoce))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelSeusPersonagensLayout.setVerticalGroup(
            panelSeusPersonagensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeusPersonagensLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblVoce)
                .addGap(8, 8, 8)
                .addComponent(lblClassePersonagemUmVoce)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vidaPersonagemUmVoce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblClassePersonagemDoisVoce)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vidaPersonagemDoisVoce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        panelPersonagensAdversario.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        panelPersonagensAdversario.setMaximumSize(new java.awt.Dimension(290, 150));
        panelPersonagensAdversario.setMinimumSize(new java.awt.Dimension(290, 150));
        panelPersonagensAdversario.setName(""); // NOI18N
        panelPersonagensAdversario.setPreferredSize(new java.awt.Dimension(290, 150));

        lblAdversario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAdversario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAdversario.setText("Adversário");

        vidaPersonagemUmAdversario.setForeground(new java.awt.Color(255, 0, 0));

        lblClassePersonagemDoisAdversario.setText("NomeClasse");

        lblClassePersonagemUmAdversario.setText("NomeClasse");

        vidaPersonagemDoisAdversario.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout panelPersonagensAdversarioLayout = new javax.swing.GroupLayout(panelPersonagensAdversario);
        panelPersonagensAdversario.setLayout(panelPersonagensAdversarioLayout);
        panelPersonagensAdversarioLayout.setHorizontalGroup(
            panelPersonagensAdversarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPersonagensAdversarioLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(lblAdversario, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
            .addGroup(panelPersonagensAdversarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPersonagensAdversarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vidaPersonagemUmAdversario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vidaPersonagemDoisAdversario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelPersonagensAdversarioLayout.createSequentialGroup()
                        .addGroup(panelPersonagensAdversarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblClassePersonagemUmAdversario)
                            .addComponent(lblClassePersonagemDoisAdversario))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelPersonagensAdversarioLayout.setVerticalGroup(
            panelPersonagensAdversarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPersonagensAdversarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAdversario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblClassePersonagemUmAdversario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vidaPersonagemUmAdversario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblClassePersonagemDoisAdversario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vidaPersonagemDoisAdversario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        panelMap.setBorder(new javax.swing.border.MatteBorder(null));
        panelMap.setLayout(new java.awt.GridLayout(12, 20));

        panelTurno.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        panelTurno.setMaximumSize(new java.awt.Dimension(200, 150));
        panelTurno.setMinimumSize(new java.awt.Dimension(200, 150));
        panelTurno.setPreferredSize(new java.awt.Dimension(200, 150));

        lblTituloPanelTurno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTituloPanelTurno.setText("Turno");

        lblPrefixoJogadorTurno.setText("Jogador:");

        lblJogadorTurno.setText("QUalJogador");

        lblPrefixoClassePersonagemTurno.setText("Personagem:");

        lblClassePersonagemTurno.setText("Classe");

        lblPrefixoAtoMaiorTurno.setText("Ato Maior:");

        lblAtoMaiorTurno.setText("Possui Ou Não");

        lblPrefixoAtoMenorTurno.setText("Ato Menor:");

        lblAtoMenorTurno.setText("Possui Ou Não");

        lblPrefixoPassosTurno.setText("Passos Sobrando:");

        lblPassosTurno.setText("numPassos");

        javax.swing.GroupLayout panelTurnoLayout = new javax.swing.GroupLayout(panelTurno);
        panelTurno.setLayout(panelTurnoLayout);
        panelTurnoLayout.setHorizontalGroup(
            panelTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTurnoLayout.createSequentialGroup()
                .addGroup(panelTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTurnoLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(lblTituloPanelTurno))
                    .addGroup(panelTurnoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTurnoLayout.createSequentialGroup()
                                .addComponent(lblPrefixoPassosTurno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPassosTurno))
                            .addGroup(panelTurnoLayout.createSequentialGroup()
                                .addComponent(lblPrefixoJogadorTurno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblJogadorTurno))
                            .addGroup(panelTurnoLayout.createSequentialGroup()
                                .addComponent(lblPrefixoClassePersonagemTurno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblClassePersonagemTurno))
                            .addGroup(panelTurnoLayout.createSequentialGroup()
                                .addComponent(lblPrefixoAtoMaiorTurno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAtoMaiorTurno))
                            .addGroup(panelTurnoLayout.createSequentialGroup()
                                .addComponent(lblPrefixoAtoMenorTurno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAtoMenorTurno)))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        panelTurnoLayout.setVerticalGroup(
            panelTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTurnoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloPanelTurno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrefixoJogadorTurno)
                    .addComponent(lblJogadorTurno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrefixoClassePersonagemTurno)
                    .addComponent(lblClassePersonagemTurno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrefixoAtoMaiorTurno)
                    .addComponent(lblAtoMaiorTurno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrefixoAtoMenorTurno)
                    .addComponent(lblAtoMenorTurno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrefixoPassosTurno)
                    .addComponent(lblPassosTurno))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelSeusPersonagens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(panelPersonagensAdversario, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelMap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelMenuEscolhas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(panelMenuEscolhas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelPersonagensAdversario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelSeusPersonagens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addComponent(panelMap, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
    }//GEN-END:initComponents

    private void btnMoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoverActionPerformed
        if (mapButtonsListener != mapButtonsMoverListener) {
            mapButtonsListener = mapButtonsMoverListener;
            btnAtacar.setBackground(new Color(240, 240, 240));
            btnMover.setBackground(Color.GREEN);
            btnAtacar.repaint();
        } else {
            mapButtonsListener = mapButtonsDefaultListener;
            btnMover.setBackground(new Color(240, 240, 240));

        }
        btnMover.repaint();
    }//GEN-LAST:event_btnMoverActionPerformed

    private void btnAtacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtacarActionPerformed
        if (mapButtonsListener != mapButtonsAtacarListener) {
            mapButtonsListener = mapButtonsAtacarListener;
            btnAtacar.setBackground(Color.green);
            btnMover.setBackground(new Color(240, 240, 240));
            btnMover.repaint();
        } else {
            mapButtonsListener = mapButtonsDefaultListener;
            btnAtacar.setBackground(new Color(240, 240, 240));
        }
        btnAtacar.repaint();
    }//GEN-LAST:event_btnAtacarActionPerformed

    private void btnPassarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPassarTurnoActionPerformed
        ControladorTelas.getInstance().passarVez();

    }//GEN-LAST:event_btnPassarTurnoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtacar;
    private javax.swing.JButton btnMover;
    private javax.swing.JButton btnPassarTurno;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAdversario;
    private javax.swing.JLabel lblAtoMaiorTurno;
    private javax.swing.JLabel lblAtoMenorTurno;
    private javax.swing.JLabel lblClassePersonagemDoisAdversario;
    private javax.swing.JLabel lblClassePersonagemDoisVoce;
    private javax.swing.JLabel lblClassePersonagemTurno;
    private javax.swing.JLabel lblClassePersonagemUmAdversario;
    private javax.swing.JLabel lblClassePersonagemUmVoce;
    private javax.swing.JLabel lblJogadorTurno;
    private javax.swing.JLabel lblPassosTurno;
    private javax.swing.JLabel lblPrefixoAtoMaiorTurno;
    private javax.swing.JLabel lblPrefixoAtoMenorTurno;
    private javax.swing.JLabel lblPrefixoClassePersonagemTurno;
    private javax.swing.JLabel lblPrefixoJogadorTurno;
    private javax.swing.JLabel lblPrefixoPassosTurno;
    private javax.swing.JLabel lblTituloPanelTurno;
    private javax.swing.JLabel lblVoce;
    private javax.swing.JPanel panelMap;
    private javax.swing.JPanel panelMenuEscolhas;
    private javax.swing.JScrollPane panelMenuRotativo;
    private javax.swing.JPanel panelMenuRotativoInnerPanel;
    private javax.swing.JPanel panelPersonagensAdversario;
    private javax.swing.JPanel panelSeusPersonagens;
    private javax.swing.JPanel panelTurno;
    private javax.swing.JProgressBar vidaPersonagemDoisAdversario;
    private javax.swing.JProgressBar vidaPersonagemDoisVoce;
    private javax.swing.JProgressBar vidaPersonagemUmAdversario;
    private javax.swing.JProgressBar vidaPersonagemUmVoce;
    // End of variables declaration//GEN-END:variables

    void atualizaTurno(Turno turno, Time timeDoJogadorLocal) {
        lblTituloPanelTurno.setForeground(turno.isTurnoAtivo() ? Color.GREEN : Color.RED);
        lblJogadorTurno.setText(turno.getTime().equals(timeDoJogadorLocal) ? "Você" : "Adversário");
        lblClassePersonagemTurno.setText(turno.getPersonagem().getNome());
        lblAtoMaiorTurno.setText(turno.has(Custo.AtoMaior) ? "Possui" : "Não Possui");
        lblAtoMenorTurno.setText(turno.has(Custo.AtoMenor) ? "Possui" : "Não Possui");
        lblPassosTurno.setText(String.valueOf(turno.getPersonagem().getVelocidade()));
    }

    void atualizaVidaPersonagens(Set<Personagem> personagens, Time timeJogadorLocal) {
        personagens.forEach(p -> {
            if (p.getTime().equals(timeJogadorLocal)) {
                if (p.getNome().equals(lblClassePersonagemUmVoce.getText())) {
                    vidaPersonagemUmVoce.setValue(p.getPercentualVidaAtual());
                } else {
                    vidaPersonagemDoisVoce.setValue(p.getPercentualVidaAtual());
                }
            } else {
                if (p.getNome().equals(lblClassePersonagemUmAdversario.getText())) {
                    vidaPersonagemUmAdversario.setValue(p.getPercentualVidaAtual());
                } else {
                    vidaPersonagemDoisAdversario.setValue(p.getPercentualVidaAtual());
                }
            }
        });
    }

    void atualizaMapa(HashMap<Dimension, Lugar> locaisAlterados, HashMap<Personagem, Lugar> posicaoPersonagens, Time timeDoJogadorLocal) {
        locaisAlterados.forEach((coordenada, lugar) -> {
            JButton btn = localButtons[(int) coordenada.getHeight()][(int) coordenada.getWidth()];
            btn.setToolTipText(lugar.getDescricao(timeDoJogadorLocal));
            try {
                btn.setIcon(
                        new ImageIcon(
                                ImageIO.read(Referencia.class.getResource(
                                        lugar.getIcone(timeDoJogadorLocal)))));
            } catch (IOException ex) {
                Logger.getLogger(TelaEmJogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        posicaoPersonagens.forEach((pers, lugar) -> {
            Dimension coordenada = lugar.getCoordenada();
            JButton btn = localButtons[(int) coordenada.getHeight()][(int) coordenada.getWidth()];
            btn.setToolTipText(lugar.getDescricao(timeDoJogadorLocal));
            try {
                btn.setIcon(
                        new ImageIcon(
                                ImageIO.read(Referencia.class.getResource(
                                        pers.getIcone()))));
            } catch (IOException ex) {
                Logger.getLogger(TelaEmJogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    void atualizaTela(Ato ato, Time timeJogadorLocal) {

        //Panel Turno
        atualizaTurno(ato.getTurno(), timeJogadorLocal);

        //Panel Vida
        atualizaVidaPersonagens(ato.getNovaPosicoesPersonagens().keySet(), timeJogadorLocal);

        //Mapa
        atualizaMapa(ato.getAlteracoesMapa(), ato.getNovaPosicoesPersonagens(), timeJogadorLocal);

        //Atualiza Poderes
        atualizaBotoesPoderes(ato.getTurno().getPersonagem().getPoderes());
    }

    void setClassesPersonagensJogadores(String pers1Jog1, String pers2Jog1, String pers1Jog2, String pers2Jog2) {
        lblClassePersonagemUmVoce.setText(pers1Jog1);
        lblClassePersonagemDoisVoce.setText(pers2Jog1);
        lblClassePersonagemUmAdversario.setText(pers1Jog2);
        lblClassePersonagemDoisAdversario.setText(pers2Jog2);
    }

    void atualizaBotoesPoderes(Poder[] poderes) {
        panelMenuRotativoInnerPanel.removeAll();
        if (poderes != null) {
            for (Poder poder : poderes) {
                JButton btn = new JButton(poder.getNome());
                btn.setToolTipText(poder.getDescricao());
                btn.setSize(194, 40);
                if (poder instanceof PoderLocalAlvo) {
                    ActionListener poderLocalAlvoListener = poderLocalAlvoEvent -> {
                        if (mapButtonsListener == mapButtonsDefaultListener) {
                            mapButtonsListener = mapButtonEvent -> {
                                ControladorTelas.getInstance().usarPoderLocalAlvo((PoderLocalAlvo) poder, posicaoBotoes.get((JButton) mapButtonEvent.getSource()));
                                mapButtonsListener = mapButtonsDefaultListener;
                            };
                            btnMover.setBackground(new Color(240, 240, 240));
                            btnAtacar.setBackground(new Color(240, 240, 240));
                            btn.setBackground(Color.green);
                        } else {
                            mapButtonsListener = mapButtonsDefaultListener;
                            btn.setBackground(new Color(240, 240, 240));
                        }
                    };
                    btn.addActionListener(poderLocalAlvoListener);
                } else if (poder instanceof PoderAutoModificacao) {
                    ActionListener poderAutoModificacaoListener = poderAutoModificacaoEvent -> {
                        ControladorTelas.getInstance().usarPoderAutoModificacao((PoderAutoModificacao) poder);
                    };
                    btn.addActionListener(poderAutoModificacaoListener);
                } else {
                    throw new RuntimeException("O poder " + poder.getNome() + " não"
                            + "tem interface definida em TelaEmJogo, caso ja tenha"
                            + "essa interface criada basta adiciona-la na lista"
                            + "com o instanceof e implementar a forma de usa-lo.");
                }
                panelMenuRotativoInnerPanel.add(btn);
            }
        }
        panelMenuRotativoInnerPanel.revalidate();
    }

    private void mapButtonslistenerInit() {
        mapButtonsMoverListener = event -> {
            ControladorTelas.getInstance().mover(posicaoBotoes.get((JButton) event.getSource()));
        };
        mapButtonsAtacarListener = event -> {
            ControladorTelas.getInstance().atacar(posicaoBotoes.get((JButton) event.getSource()));
        };
    }

}
