package interfaceGrafica;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class InterfaceBancoImobiliario extends JFrame {

    //largura e altura do tabuleir
    private final int ALTURA_POSICAO = 60;
    private final int LARGURA_POSICAO = 60;
    private final int DIM_TABULEIRO = ALTURA_POSICAO * 11;

    private final int LARGURA_CARTA_CENTRAL = 350;
    private final int ALTURA_CARTA_CENTRAL = 418;
    //footer onde ficam os botões
    private final int LARGURA_FOOTER = DIM_TABULEIRO;
    private final int ALTURA_FOOTER = 100;

    //onde ficam as informações do jogador
    private final int LARGURA_SIDEBAR = 200;
    private final int ALTURA_SIDEBAR = DIM_TABULEIRO + ALTURA_FOOTER;

    //interface inteira
    private final int LARGURA_INTERFACE = DIM_TABULEIRO + LARGURA_SIDEBAR + 5;
    private final int ALTURA_INTERFACE = DIM_TABULEIRO + ALTURA_FOOTER + 5;

    private JLayeredPane tabuleiro;
    private JPanel sidebar;
    private JLabel[] valores;
    private JPanel buttonsContainer;
    private JMenuBar menuBar;
    private JMenu menuDoJogo;
    private JMenuItem menuIniciar;
    private JMenuItem menuConectar;
    private JLabel centralTabuleiro;
    private JLabel zoomPropriedade;
    private InterfacePosicao[] posicoes;
    private JLabel[] peoes;

    private JLabel hipotecar;
    private JLabel desipotecar;
    private JLabel finalizar;
    private JLabel vender;
    private JLabel melhorar;
    private JLabel cancelar;

    private AtorJogador atorJogador;
    private JLabel feedback = new JLabel();

    public InterfaceBancoImobiliario() {
        super("Banco Imobiliário");
        init();
        atorJogador = new AtorJogador(this);
    }


    public void posicionarPeao(int peao, int posicao) {
        posicao = posicao % 40;
        int x = 4;
        int y = 4;
        switch (peao) {
            case 1:
                x += 27;
                break;
            case 2:
                y += 27;
                break;
            case 3:
                x += 27;
                y += 27;
                break;
        }
        x += posicoes[posicao].getBounds().getMinX();
        y += posicoes[posicao].getBounds().getMinY();
        peoes[peao].setBounds(x, y, 27, 27);
    }

    public boolean perguntarComprar(int posicao) {
        InterfaceComprar i = new InterfaceComprar(this, posicao);
        return i.isComprou();
    }

    public void mostrarJogadores(java.util.List<String> jogadores) {
        int numJogadores = jogadores.size();
        peoes = new JLabel[numJogadores];
        valores = new JLabel[numJogadores];
        for (int i = 0; i < peoes.length; i++) {
            peoes[i] = new JLabel(new ImageIcon(getClass().getResource("images/peao" + i + ".png")));
            tabuleiro.add(peoes[i], 0);
            this.posicionarPeao(i, 0);
            this.addJogadorSidebar(i, jogadores.get(i));
        }
    }

    private void addJogadorSidebar(int jogador, String nome) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panel.setAlignmentX(0);
        panel.setOpaque(true);
        panel.setBounds(0, jogador * 80, LARGURA_SIDEBAR, 80);

        panel.add(new JLabel(new ImageIcon(getClass().getResource("images/peao" + jogador + ".png"))));
        panel.add(new JLabel(nome));

        valores[jogador] = new JLabel("$ 1808");
        panel.add(valores[jogador]);

        this.sidebar.add(panel);
    }

    private Color getCor(int jogador) {
        Color cor;
        switch (jogador) {
            case 0:
                cor = Color.GREEN;
                break;
            case 1:
                cor = Color.RED;
                break;
            case 2:
                cor = Color.BLUE;
                break;
            case 3:
                cor = Color.YELLOW;
                break;
            default:
                cor = Color.BLACK;
        }
        return cor;
    }

    public void setNumeroDeCasas(int posicao, int numero) {
        posicoes[posicao].setCasas(numero);
    }

    public void desmarcarPosicao(int posicao) {
        marcarPosicao(posicao, -1);
    }

    public void marcarPosicao(int posicao, int jogador) {
        Color cor = this.getCor(jogador);

        Border b;
        posicao = posicao % 40;
        if (cor != Color.BLACK)
            b = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createMatteBorder(0, 0, 4, 0, cor));
        else
            b = BorderFactory.createLineBorder(cor);
        posicoes[posicao].setBorda(b);
        tabuleiro.repaint();
    }

    public String obterIdServidor() {
        String idServidor = ("venus.inf.ufsc.br");
        idServidor = JOptionPane.showInputDialog(this, ("Insira o endereço do servidor"), idServidor);
        return idServidor;
    }

    public String obterDadosJogador() {
        return JOptionPane.showInputDialog(this, ("Insira o nome do Jogador"));
    }

    public void atualizarValor(int jogador, int capital) {
        valores[jogador].setText("$ " + capital);

    }

    private void init() {
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(LARGURA_INTERFACE, ALTURA_INTERFACE);

        menuBar = new JMenuBar();
        menuBar.add(getMenu());
        this.setJMenuBar(menuBar);

        tabuleiro = new JLayeredPane();
        tabuleiro.setOpaque(true);
        tabuleiro.setLayout(null);
        tabuleiro.setSize(DIM_TABULEIRO, DIM_TABULEIRO);
        this.add(tabuleiro);
        addTabuleiroPositions();

        ImageIcon centro = new ImageIcon(getClass().getResource("images/central.png"));
        centralTabuleiro = new JLabel(centro);
        centralTabuleiro.setBounds((int) tabuleiro.getBounds().getMinX() + LARGURA_POSICAO,
                (int) tabuleiro.getBounds().getMinY() + ALTURA_POSICAO,
                tabuleiro.getWidth() - 2 * LARGURA_POSICAO, tabuleiro.getHeight() - 2 * ALTURA_POSICAO);
        tabuleiro.add(centralTabuleiro, -1);


        zoomPropriedade = new JLabel();
        zoomPropriedade.setBounds((int) tabuleiro.getBounds().getCenterX() - LARGURA_CARTA_CENTRAL / 2,
                (int) tabuleiro.getBounds().getCenterY() - ALTURA_CARTA_CENTRAL / 2,
                LARGURA_CARTA_CENTRAL, ALTURA_CARTA_CENTRAL);
        tabuleiro.add(zoomPropriedade, 0);


        sidebar = new JPanel(null);
        sidebar.setBounds(DIM_TABULEIRO, 0, LARGURA_SIDEBAR, ALTURA_SIDEBAR);
        this.add(sidebar);

        buttonsContainer = new JPanel();
        buttonsContainer.setBounds(0, DIM_TABULEIRO, LARGURA_FOOTER, ALTURA_FOOTER);
        this.add(buttonsContainer);

        //Instaciar botoes casos de uso

        hipotecar = new JLabel(new ImageIcon(getClass().getResource("images/hipotecar.png")));
        vender = new JLabel(new ImageIcon(getClass().getResource("images/vender.png")));
        desipotecar = new JLabel(new ImageIcon(getClass().getResource("images/desipotecar.png")));
        melhorar = new JLabel(new ImageIcon(getClass().getResource("images/melhorar.png")));
        finalizar = new JLabel(new ImageIcon(getClass().getResource("images/finalizar.png")));
        cancelar = new JLabel(new ImageIcon(getClass().getResource("images/finalizar.png")));
        cancelar.setVisible(false);

        TratadorMouse mouse = new TratadorMouse();
        hipotecar.addMouseListener(mouse);
        vender.addMouseListener(mouse);
        desipotecar.addMouseListener(mouse);
        melhorar.addMouseListener(mouse);
        finalizar.addMouseListener(mouse);
        cancelar.addMouseListener(mouse);

        buttonsContainer.add(hipotecar);
        buttonsContainer.add(vender);
        buttonsContainer.add(desipotecar);
        buttonsContainer.add(melhorar);
        buttonsContainer.add(finalizar);
        buttonsContainer.add(cancelar);
        this.add(buttonsContainer);
        esconderBotoes();

        centerWindow(this);

    }

    public void abrirJogarDados(final Boolean jogada) {
        final JDialog dialog = new JDialog();
        dialog.setModal(true);
        ImageIcon icon = new ImageIcon(getClass().getResource("images/dados.gif"));
        JLabel dados = new JLabel(icon);
        dados.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                dialog.dispose();
                if (jogada)
                    atorJogador.iniciarJogada();
            }
        });
        dialog.setSize(350, 195);
        dialog.add(dados);
        dialog.add(dados);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public boolean perguntarFormaSairDaPrisao() {
        int n = JOptionPane.showOptionDialog(this,
                "Você está na Prisão. Deseja pagar 50 e sair, ou tentar a sorte?",
                "",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                new String[]{"Tentar sair!", "Pagar!"},  //the titles of buttons
                "Tentar sair!"); //default button title
        return n == 0;
    }

    public boolean perguntarUsarSaidaLivre() {
        int n = JOptionPane.showOptionDialog(this,
                "Você têm saída livre, deseja usar?",
                "",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                new String[]{"Sim!", "Não!"},  //the titles of buttons
                "Sim!"); //default button title
        return n == 0;
    }

    private void centerWindow(Component frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();

        if (frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;

        if (frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;

        frame.setLocation((screenSize.width - frameSize.width) >> 1, (screenSize.height - frameSize.height) >> 1);
    }

    private void addTabuleiroPositions() {
        posicoes = new InterfacePosicao[40];
        int posX = DIM_TABULEIRO - ALTURA_POSICAO;
        int posY = DIM_TABULEIRO - ALTURA_POSICAO;
        int linha = -1; //QUAL A DIREÇÃO DA LINHA
        for (int i = 0; i < 40; i++) {
            int width = LARGURA_POSICAO;
            int height = ALTURA_POSICAO;
            switch (linha) {
                case 0:
                    posX -= width; //HORIZONTAL PRA ESQUERDA
                    break;
                case 1:
                    posY -= height; //VERTICAL PRA CIMA
                    break;
                case 2:
                    posX += width; //HORIZONTAL PRA DIREITA;
                    break;
                case 3:
                    posY += height; //VERTICAL PRA BAIXO;
                    break;
            }
            if (i % 10 == 0) {//PASSANDO O CORNER AVANÇAR PRA PRÓXIMA LINHA
                linha++;
            }
            posicoes[i] = new InterfacePosicao(i);


            posicoes[i].setBounds(posX, posY, width, height);
            tabuleiro.add(posicoes[i]);
            //STYLE
            Border b = BorderFactory.createLineBorder(Color.BLACK);
            posicoes[i].setBorder(b);

            //evento para a carta aparecer no centro do tabuleiro
            posicoes[i].addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    aplicarZoom(((InterfacePosicao) e.getSource()).getTipoPosicao().getNome());
                }

                public void mouseExited(MouseEvent e) {
                    removerZoom();
                }
            });
        }
    }

    private void aplicarZoom(String nome) {
        URL url = getClass().getResource("images/" + nome + ".png");
        if (url != null) {
            zoomPropriedade.setIcon(new ImageIcon(url));
        }
    }

    private void removerZoom() {
        zoomPropriedade.setIcon(null);
    }

    private JMenu getMenu() {
        if (menuDoJogo == null) {
            menuDoJogo = new JMenu();
            menuDoJogo.setText("Jogo");
            menuDoJogo.setBounds(new Rectangle(1, 0, 57, 21));
            menuDoJogo.add(getMenuIniciar());
            menuDoJogo.add(getJMenuConectar());
        }
        return menuDoJogo;
    }

    private JMenuItem getMenuIniciar() {
        if (menuIniciar == null) {
            menuIniciar = new JMenuItem();
            menuIniciar.setText("iniciar nova partida");
            menuIniciar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    iniciarPartida();
                }
            });
        }
        return menuIniciar;
    }

    private JMenuItem getJMenuConectar() {
        if (menuConectar == null) {
            menuConectar = new JMenuItem();
            menuConectar.setText("conectar");
            menuConectar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    conectar();
                }
            });
        }
        return menuConectar;
    }


    private void iniciarPartida() {
        atorJogador.iniciarPartida();
    }

    private void conectar() {
        if (atorJogador.conectar())
            alert("Conexão efetuada com sucesso");
    }

    private void hipotecarActionPerformed() {
        java.util.List<Integer> list = atorJogador.informarHipotecaveis();
        if(list.size() == 0){
            alert("Você não possui propriedades válidas para hipotecar");
        }
        else{
            mensagem("<html><center>Por favor, clique na propriedade que deseja Hipotecar.<br/>Caso deseja cancelar a operação, clique em cancelar.</center></html>");
            mostrarPosicoes(list, 0);
            esconderBotoes();
            mostrarCancelar();
        }
    }

    private void mostrarPosicoes(java.util.List<Integer> list, int tipo) {
        MouseListener evento;
        switch (tipo) {
            case 0:evento = hipotecarListener;
                break;
            case 1:evento = desipotecarListener;
                break;
            case 2:evento = venderListener;
                break;
            default:evento = melhorarListener;
        }
        for (Integer pos : list) {
            posicoes[pos].setHightlight(true);
            posicoes[pos].addMouseListener(evento);
        }
    }

    private MouseAdapter hipotecarListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            InterfacePosicao posicao = ((InterfacePosicao) e.getSource());
            clickHipotecar(posicao);
        }
    };

    public void clickHipotecar(InterfacePosicao posicao) {
        atorJogador.hipotecar(posicao.getTipoPosicao().getId());
        limparFeedBack();
        limparEventosEHighlights();
        mostrarBotoes();
    }

    private void venderActionPerformed() {
        java.util.List<Integer> list = atorJogador.informarVendiveis();
        if(list.size() == 0){
            alert("Você não possui propriedades e/ou propriedades válidas para vender");
        }
        else{
            mensagem("<html><center>Por favor, clique na propriedade que deseja Vender.<br/>Caso deseja cancelar a operação, clique em cancelar.</center></html>");
            mostrarPosicoes(list, 2);
            esconderBotoes();
            mostrarCancelar();
        }
    }

    private MouseAdapter venderListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            InterfacePosicao posicao = ((InterfacePosicao) e.getSource());
            clickVender(posicao);
        }
    };

    public void clickVender(InterfacePosicao posicao) {
        int casas = atorJogador.vender(posicao.getTipoPosicao().getId());
        if (casas >= 0) {
            setNumeroDeCasas(posicao.getTipoPosicao().getId(), casas);
        } else
            desmarcarPosicao(posicao.getTipoPosicao().getId());
        limparFeedBack();
        limparEventosEHighlights();
        mostrarBotoes();
    }

    private MouseAdapter desipotecarListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            InterfacePosicao posicao = ((InterfacePosicao) e.getSource());
            clickDesipotecar(posicao);
        }
    };

    public void clickDesipotecar(InterfacePosicao posicao) {
        boolean temMoney = atorJogador.desipotecar(posicao.getTipoPosicao().getId());
        if (!temMoney) {
            alert("Você não tem dinheiro suficiente para esta ação");
        }
        limparFeedBack();
        limparEventosEHighlights();
        mostrarBotoes();
    }

    private void desipotecarActionPerformed() {
        java.util.List<Integer> list = atorJogador.informarHipotecadas();
        if(list.size() == 0){
            alert("Você não possui propriedades hipotecadas");
        }
        else{
            mensagem("<html><center>Por favor, clique na propriedade que deseja Desipotecar.<br/>Caso deseja cancelar a operação, clique em cancelar.</center></html>");
            mostrarPosicoes(list, 1);
            esconderBotoes();
            mostrarCancelar();
        }
    }

    private MouseAdapter melhorarListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            InterfacePosicao posicao = ((InterfacePosicao) e.getSource());
            clickMelhorar(posicao);
        }
    };

    public void clickMelhorar(InterfacePosicao posicao) {
        int casas = atorJogador.melhorar(posicao.getTipoPosicao().getId());
        if (casas < 0) {
            alert("Você não tem dinheiro suficiente para esta ação");
        } else
            setNumeroDeCasas(posicao.getTipoPosicao().getId(), casas);
        limparFeedBack();
        limparEventosEHighlights();
        mostrarBotoes();
    }

    private void melhorarActionPerformed() {
        java.util.List<Integer> list = atorJogador.informarMelhoraveis();
        if(list.size() == 0){
            alert("Você não possui um grupo de propriedades válidos para melhorá-los");
        }
        else{
            mensagem("<html><center>Por favor, clique na propriedade que deseja melhorar.<br/>Caso deseja cancelar a operação, clique em cancelar.</center></html>");
            mostrarPosicoes(list, 3);
            esconderBotoes();
            mostrarCancelar();
        }
    }

    private void mostrarCancelar() {
        cancelar.setVisible(true);
    }

    public void esconderBotoes() {
        hipotecar.setVisible(false);
        vender.setVisible(false);
        finalizar.setVisible(false);
        desipotecar.setVisible(false);
        melhorar.setVisible(false);
    }

    public void mostrarBotoes() {
        hipotecar.setVisible(true);
        vender.setVisible(true);
        finalizar.setVisible(true);
        desipotecar.setVisible(true);
        melhorar.setVisible(true);
        cancelar.setVisible(false);
    }

    public void limparFeedBack() {
        feedback.setVisible(false);
        tabuleiro.remove(feedback);
    }

    private void cancelarActionPerformed() {
        limparFeedBack();
        limparEventosEHighlights();
        mostrarBotoes();
    }

    private void limparEventosEHighlights() {
        for (InterfacePosicao posicao : posicoes) {
            posicao.setHightlight(false);
            posicao.removeMouseListener(hipotecarListener);
            posicao.removeMouseListener(venderListener);
            posicao.removeMouseListener(desipotecarListener);
            posicao.removeMouseListener(melhorarListener);
        }
    }

    public void mensagem(String s) {
        limparFeedBack();
        feedback = new JLabel(s);
        feedback.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY));
        feedback.setOpaque(true);

        feedback.setBackground(Color.WHITE);
        int width = (int) feedback.getPreferredSize().getWidth();
        int height = (int) feedback.getPreferredSize().getHeight();
        feedback.setBounds((int) tabuleiro.getBounds().getCenterX() - width / 2,
                (int) zoomPropriedade.getBounds().getMaxY(),
                width, height);
        tabuleiro.add(feedback, 0);
    }

    public void alert(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    private void clickFinalizar() {
        atorJogador.finalizarJogada();
    }


    private class TratadorMouse extends MouseAdapter implements MouseListener, MouseMotionListener {
        @Override
        public void mouseEntered(MouseEvent event) {
            if (event.getSource() == hipotecar)
                hipotecar.setIcon(new ImageIcon(getClass().getResource("images/hipotecarhover.png")));
            else if (event.getSource() == desipotecar)
                desipotecar.setIcon(new ImageIcon(getClass().getResource("images/desipotecarhover.png")));
            else if (event.getSource() == finalizar)
                finalizar.setIcon(new ImageIcon(getClass().getResource("images/finalizarhover.png")));
            else if (event.getSource() == vender)
                vender.setIcon(new ImageIcon(getClass().getResource("images/venderhover.png")));
            else if (event.getSource() == cancelar)
                cancelar.setIcon(new ImageIcon(getClass().getResource("images/cancelarhover.png")));
            else if (event.getSource() == melhorar)
                melhorar.setIcon(new ImageIcon(getClass().getResource("images/melhorarhover.png")));
        }

        @Override
        public void mouseExited(MouseEvent event) {
            if (event.getSource() == hipotecar)
                hipotecar.setIcon(new ImageIcon(getClass().getResource("images/hipotecar.png")));
            else if (event.getSource() == desipotecar)
                desipotecar.setIcon(new ImageIcon(getClass().getResource("images/desipotecar.png")));
            else if (event.getSource() == finalizar)
                finalizar.setIcon(new ImageIcon(getClass().getResource("images/finalizar.png")));
            else if (event.getSource() == vender)
                vender.setIcon(new ImageIcon(getClass().getResource("images/vender.png")));
            else if (event.getSource() == cancelar)
                cancelar.setIcon(new ImageIcon(getClass().getResource("images/cancelar.png")));
            else if (event.getSource() == melhorar)
                melhorar.setIcon(new ImageIcon(getClass().getResource("images/melhorar.png")));
        }

        public void mousePressed(MouseEvent event) {

            if (event.getSource() == hipotecar)
                hipotecarActionPerformed();
            else if (event.getSource() == desipotecar)
                desipotecarActionPerformed();
            else if (event.getSource() == finalizar)
                clickFinalizar();
            else if (event.getSource() == vender)
                venderActionPerformed();
            else if (event.getSource() == cancelar)
                cancelarActionPerformed();
            else if (event.getSource() == melhorar)
                melhorarActionPerformed();
        }
    }
}