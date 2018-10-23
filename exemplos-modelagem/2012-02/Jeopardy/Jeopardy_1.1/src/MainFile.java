import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainFile extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;
    private AtorJogador atorJogador;
    // nomes dos jogadores
    private JLabel lblNomejogador1;
    private JLabel lblNomejogador2;
    private JLabel lblX;
    // botoes de funcionalidades
    private JButton btnSair;
    private JButton btnIniciarPartida;
    private JLabel lblAguarde;

    public static void main(String[] args) {
        new MainFile().setVisible(true);
    }

    private void iniciarPartida() {
        this.atorJogador.iniciarPartida();
    }

    private void sair() {
        this.atorJogador.fechar();
    }

    public MainFile() {
        // configura os componentes visuais
        setTitle("Jeopardy!");
        setSize(350, 550);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.blue);
        getContentPane().setForeground(Color.yellow);
        // cria o botão de iniciar partida
        btnIniciarPartida = new JButton("Iniciar partida");
        btnIniciarPartida.setBounds(10, 29, 135, 23);
        btnIniciarPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                iniciarPartida();
            }
        });        
        getContentPane().add(btnIniciarPartida);

        // cria o botão de sair
        btnSair = new JButton("Sair");
        btnSair.setBounds(174, 29, 135, 23);
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sair();
            }
        });

        getContentPane().add(btnSair);

        // cria o label com o nome do jogador
        lblNomejogador1 = new JLabel("NOME_JOGADOR_1");
        lblNomejogador1.setForeground(Color.yellow);
        lblNomejogador1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNomejogador1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNomejogador1.setBounds(10, 4, 135, 14);
        lblNomejogador1.setVisible(false);
        getContentPane().add(lblNomejogador1);

        // cria o label com o nome do oponente
        lblNomejogador2 = new JLabel("NOME_JOGADOR_2");
        lblNomejogador2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNomejogador2.setForeground(Color.yellow);
        lblNomejogador2.setBounds(174, 4, 135, 14);
        lblNomejogador2.setVisible(false);
        getContentPane().add(lblNomejogador2);

        // cria demais componentes visuais
        lblX = new JLabel("X");
        lblX.setBounds(155, 4, 12, 14);
        lblX.setVisible(false);
        getContentPane().add(lblX);

        lblAguarde = new JLabel("Aguarde");
        lblAguarde.setHorizontalAlignment(SwingConstants.CENTER);
        lblAguarde.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblAguarde.setBounds(10, 192, 299, 48);
        lblAguarde.setVisible(false);
        getContentPane().add(lblAguarde);
       
        // cria os botões do tabuleiro
        desenhaTabuleiro();

        // inicializa o tratador do jogo
        this.atorJogador = new AtorJogador(this);
    }

    private void desenhaTabuleiro() {
        // cria os botoes do jogador 1
        criaBotao("Jogador1_0",  10, 452, 135, 31);
        criaBotao("Jogador1_1",  10, 420, 135, 31);
        criaBotao("Jogador1_2",  10, 388, 135, 31);
        criaBotao("Jogador1_3",  10, 356, 135, 31);
        criaBotao("Jogador1_4",  10, 324, 135, 31);
        criaBotao("Jogador1_5",  10, 292, 135, 31);
        criaBotao("Jogador1_6",  10, 260, 135, 31);
        criaBotao("Jogador1_7",  10, 228, 135, 31);
        criaBotao("Jogador1_8",  10, 196, 135, 31);
        criaBotao("Jogador1_9",  10, 164, 135, 31);
        criaBotao("Jogador1_10", 10, 132, 135, 31);
        criaBotao("Jogador1_11", 10, 100, 135, 31);
        criaBotao("Jogador1_12", 10, 68, 135, 31);

        // cria os botoes do jogador 2
        criaBotao("Jogador2_0",  174, 452, 135, 31);
        criaBotao("Jogador2_1",  174, 420, 135, 31);
        criaBotao("Jogador2_2",  174, 388, 135, 31);
        criaBotao("Jogador2_3",  174, 356, 135, 31);
        criaBotao("Jogador2_4",  174, 324, 135, 31);
        criaBotao("Jogador2_5",  174, 292, 135, 31);
        criaBotao("Jogador2_6",  174, 260, 135, 31);
        criaBotao("Jogador2_7",  174, 228, 135, 31);
        criaBotao("Jogador2_8",  174, 196, 135, 31);
        criaBotao("Jogador2_9",  174, 164, 135, 31);
        criaBotao("Jogador2_10", 174, 132, 135, 31);
        criaBotao("Jogador2_11", 174, 100, 135, 31);
        criaBotao("Jogador2_12", 174, 68, 135, 31);
    }

    private void criaBotao(String name, int x, int y, int w, int h) {
        // cria um novo botão em uma posição pré-definida, com o texto vazio
        JButton botao = new JButton("");
        botao.setBounds(x, y, w, h);
        botao.setName(name);
        botao.setBackground(Color.blue);
        botao.setForeground(Color.yellow);
        this.getContentPane().add(botao);
        
    }

    private void tratarAcabou(boolean venceu) {
        // notifica ao usuário se ele é vencedor ou perdedor
        String msg;

        if (venceu) {
            msg = "Você venceu";
        } else {
            msg = "Você perdeu";
        }

       JanelaMensagem window = new JanelaMensagem(msg);
       window.show();
      
    }

    private void setaTextoBotao(String texto, String botao)
    {
        // lista todos os componentes visuais da tela
        Component[] aux = this.getContentPane().getComponents();

        // varre todos os componentes
        for (Component item : aux) {
            // se for um botão, remove o texto (Você ou Oponente)
            if (item.getClass().equals(JButton.class)) {
                String nome = ((JButton) item).getName();
                
                if ( (botao == null) || (botao.equals(nome)))
                    ((JButton) item).setText(texto);
            }
        }
    }
    
    private void atualizaPosicao(Tabuleiro tabuleiro) {
        setaTextoBotao("", null);

        String nomeBotao = "Jogador1_0";

        // pega o nome do botao que irá identificar a posição do jogador1, 
        // o nome dos botões é padronizado como Jogador(X)_(POSICAO), sendo
        // (X) o número do jogador e (POSICAO) a sua posição atual
        Jogador jogador1 = tabuleiro.getJogador1();
        if (jogador1 != null) {
            int posicao = jogador1.getPosicao();
            nomeBotao = "Jogador1_" + posicao;
        }

        setaTextoBotao("Você", nomeBotao);

        nomeBotao = "Jogador2_0";

        // pega o nome do botao que irá identificar a posição do jogador1, 
        // o nome dos botões é padronizado como Jogador(X)_(POSICAO), sendo
        // (X) o número do jogador e (POSICAO) a sua posição atual
        Jogador jogador2 = tabuleiro.getJogador2();
        if (jogador2 != null) {
            int posicao = jogador2.getPosicao();
            nomeBotao = "Jogador2_" + posicao;
        }
        
        setaTextoBotao("Oponente", nomeBotao);
    }

    @Override
    public void atualizar(Tabuleiro tabuleiro) {
        // se acabou, trata na interface
        boolean acabou = tabuleiro.acabou();
        if (acabou) {
            Jogador jogador1 = tabuleiro.getJogador1();
            boolean venceu = jogador1.venceu();
            tratarAcabou(venceu);
            fechar();
        } else {
            boolean emAndamento = tabuleiro.ispartidaEmAndamento();
            this.btnIniciarPartida.setEnabled(emAndamento);
            
            // configura os nomes dos jogadores
            String nomeJogador = tabuleiro.getNomeJogador();
            this.lblNomejogador1.setText(nomeJogador);
            this.lblNomejogador1.setVisible(true);
            String nomeAdversario = tabuleiro.getNomeAdversario();
            this.lblNomejogador2.setText(nomeAdversario);
            this.lblNomejogador2.setVisible(true);
            this.lblX.setVisible(true);

            // habilita/desabilita a tela, conforme turno
            boolean meuTurno = tabuleiro.isMeuTurno();
            this.setEnabled(meuTurno);
            this.lblAguarde.setVisible(!meuTurno);

            // atualiza posição dos jogadores no tabuleiro
            atualizaPosicao(tabuleiro);
        }
          
       
    }

    @Override
    public void fechar() {
        System.exit(0);
    }
}