/*
 * Created on 2 de Dezembro de 2008, 17:40
 */

package joormanji;

import aRede.AtorARede;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author  JooRmanji
 */
public class AtorJogador extends javax.swing.JFrame {
    
    //joor
    private String nome = "";
    private Tabuleiro tabuleiro;
    private AtorARede atorRede;
    private JFrame frame;
    private int exPosicao = 0; //para interface,
    private int exAdversario = 0; //para interface,
    
    /** Creates new form AtorJogador */
    public AtorJogador() {
        super();
        atorRede = new AtorARede(this);    
    }
    
    public void go(){
        
        frame = new JFrame("JChat");
        frame.setDefaultCloseOperation(JFrame.NORMAL);
        nome = JOptionPane.showInputDialog(frame, "Digite Seu Nome Para Iniciar o Jogo!");
		
        atorRede.conectar(nome, "venus.inf.ufsc.br");
                
        initComponents();
        // <editor-fold defaultstate="collapsed">
        a1.setVisible(false);
        a2.setVisible(false);
        a3.setVisible(false);
        a4.setVisible(false);
        a5.setVisible(false);
        a6.setVisible(false);
        a7.setVisible(false);
        a8.setVisible(false);
        a9.setVisible(false);
        a10.setVisible(false);
        a11.setVisible(false);
        a12.setVisible(false);
        a13.setVisible(false);
        a14.setVisible(false);
        a15.setVisible(false);
        a16.setVisible(false);
        a17.setVisible(false);
        a18.setVisible(false);
        a19.setVisible(false);
        a20.setVisible(false);
        a21.setVisible(false);
        a22.setVisible(false);
        a23.setVisible(false);
        a24.setVisible(false);
        a25.setVisible(false);
        a26.setVisible(false);
        a27.setVisible(false);
        a28.setVisible(false);
        a29.setVisible(false);
        a30.setVisible(false);
        a31.setVisible(false);
        a32.setVisible(false);
        a33.setVisible(false);
        a34.setVisible(false);
        a35.setVisible(false);
        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
        b4.setVisible(false);
        b5.setVisible(false);
        b6.setVisible(false);
        b7.setVisible(false);
        b8.setVisible(false);
        b9.setVisible(false);
        b10.setVisible(false);
        b11.setVisible(false);
        b12.setVisible(false);
        b13.setVisible(false);
        b14.setVisible(false);
        b15.setVisible(false);
        b16.setVisible(false);
        b17.setVisible(false);
        b18.setVisible(false);
        b19.setVisible(false);
        b20.setVisible(false);
        b21.setVisible(false);
        b22.setVisible(false);
        b23.setVisible(false);
        b24.setVisible(false);
        b25.setVisible(false);
        b26.setVisible(false);
        b27.setVisible(false);
        b28.setVisible(false);
        b29.setVisible(false);
        b30.setVisible(false);
        b31.setVisible(false);
        b32.setVisible(false);
        b33.setVisible(false);
        b34.setVisible(false);
        b35.setVisible(false);
        //</editor-fold>
        
    }
    
    public JFrame getFrame(){
	return this.frame;
    }
    
    public void iniciarPartidaJumanji(){ 
        atorRede.iniciarPartidaRede();
    }
    
    public void iniciarPartidaRede(boolean comecoJogando) {

        String nomeOutroJogador = atorRede.obterNomeAdversario();
        tabuleiro = new Tabuleiro();

        if (comecoJogando){
                tabuleiro.criarJogador(this.nome);
                tabuleiro.criarJogador(nomeOutroJogador);
                jLabel2.setText("Olá "+nome+" você está jogando contra "+nomeOutroJogador);
                JOptionPane.showMessageDialog(this.getFrame(), "É sua vez, pode rolar os Dados...");
        }else{
                tabuleiro.criarJogador(nomeOutroJogador);
                tabuleiro.criarJogador(this.nome);
                jLabel2.setText("Olá "+nome+" você está jogando contra "+nomeOutroJogador);
                JOptionPane.showMessageDialog(this.getFrame(), "Espere o outro jogar...");
        }	
    }
    
    public boolean novaJogada(int valorDoDado){
        
        if (atorRede.ehMinhaVez()){
                //boolean tratou = tabuleiro.trataMensagem(valorDoDado);
            
            boolean jogoNormal = true;
            if( valorDoDado == 5){              //SE O BONUS JA FOI USADO ELE FICA TRUE PRA SEMPRE SEM PODER SER USADO
                jogoNormal = tabuleiro.verificarEstadoEspecial();
                if(jogoNormal){
                    System.out.println("Entro Bonus, mas já tinha USADO BONUS= "+valorDoDado);
                }
                else{
                    tabuleiro.alterarEstadoEspecial();
                    System.out.println(" BONUS -- BONUS -- BONUS "+valorDoDado);
                }
            }
            
            if(jogoNormal)
            {
                //se ele acerta o desafio retorno true
                Desafio oDesafio = tabuleiro.getUmEvento();
                int i = dialogoConfirmacao(null, oDesafio.getTexto(), "Desafio!!!", "É VERDADE", "MENTIRA");
                System.out.println("Valor de retorno JPanel= "+i);
                boolean desafioUsado = oDesafio.isResposta();
                if(desafioUsado)
                {
                    if(i!=0)
                    {
                        valorDoDado = 0;
                        System.out.println("Erro a Pergunta!");
                    }
                }else
                {
                    if(i!=1)
                    {
                        valorDoDado = 0;
                        System.out.println("Erro a Pergunta!");
                    }
                }
            }
            
            boolean tratou = tabuleiro.trataJogada(valorDoDado);
            
            int posicaoMinha = tabuleiro.getPosicao();
            System.out.println("A Minha Posicao eh: "+posicaoMinha);
            
            if(posicaoMinha>35)
            {
                //JOptionPane.showConfirmDialog(null, "Você Ganhou!!");    
                JOptionPane.showMessageDialog(this, "Parabens, Continue Assim", "Fim da Partida", WIDTH);
                AtorJogador.this.atorRede.desconectar();
            }
            
            // <editor-fold defaultstate="collapsed">
        switch (exPosicao){
            case 0: b0.setVisible(false);break;
            case 1: b1.setVisible(false);break;
            case 2: b2.setVisible(false);break;
            case 3: b3.setVisible(false);break;
            case 4: b4.setVisible(false);break;
            case 5: b5.setVisible(false);break;
            case 6: b6.setVisible(false);break;
            case 7: b7.setVisible(false);break;
            case 8: b8.setVisible(false);break;
            case 9: b9.setVisible(false);break;
            case 10: b10.setVisible(false);break;
            case 11: b11.setVisible(false);break;
            case 12: b12.setVisible(false);break;
            case 13: b13.setVisible(false);break;
            case 14: b14.setVisible(false);break;
            case 15: b15.setVisible(false);break;
            case 16: b16.setVisible(false);break;
            case 17: b17.setVisible(false);break;
            case 18: b18.setVisible(false);break;
            case 19: b19.setVisible(false);break;
            case 20: b20.setVisible(false);break;
            case 21: b21.setVisible(false);break;
            case 22: b22.setVisible(false);break;
            case 23: b23.setVisible(false);break;
            case 24: b24.setVisible(false);break;
            case 25: b25.setVisible(false);break;
            case 26: b26.setVisible(false);break;
            case 27: b27.setVisible(false);break;
            case 28: b28.setVisible(false);break;
            case 29: b29.setVisible(false);break;
            case 30: b30.setVisible(false);break;
            case 31: b31.setVisible(false);break;
            case 32: b32.setVisible(false);break;
            case 33: b33.setVisible(false);break;
            case 34: b34.setVisible(false);break;
            case 35: b35.setVisible(false);break;
        }
         // </editor-fold>
            exPosicao=posicaoMinha;
            // <editor-fold defaultstate="collapsed">
        switch (exPosicao){
            case 0: b0.setVisible(true);break;
            case 1: b1.setVisible(true);break;
            case 2: b2.setVisible(true);break;
            case 3: b3.setVisible(true);break;
            case 4: b4.setVisible(true);break;
            case 5: b5.setVisible(true);break;
            case 6: b6.setVisible(true);break;
            case 7: b7.setVisible(true);break;
            case 8: b8.setVisible(true);break;
            case 9: b9.setVisible(true);break;
            case 10: b10.setVisible(true);break;
            case 11: b11.setVisible(true);break;
            case 12: b12.setVisible(true);break;
            case 13: b13.setVisible(true);break;
            case 14: b14.setVisible(true);break;
            case 15: b15.setVisible(true);break;
            case 16: b16.setVisible(true);break;
            case 17: b17.setVisible(true);break;
            case 18: b18.setVisible(true);break;
            case 19: b19.setVisible(true);break;
            case 20: b20.setVisible(true);break;
            case 21: b21.setVisible(true);break;
            case 22: b22.setVisible(true);break;
            case 23: b23.setVisible(true);break;
            case 24: b24.setVisible(true);break;
            case 25: b25.setVisible(true);break;
            case 26: b26.setVisible(true);break;
            case 27: b27.setVisible(true);break;
            case 28: b28.setVisible(true);break;
            case 29: b29.setVisible(true);break;
            case 30: b30.setVisible(true);break;
            case 31: b31.setVisible(true);break;
            case 32: b32.setVisible(true);break;
            case 33: b33.setVisible(true);break;
            case 34: b34.setVisible(true);break;
            case 35: b35.setVisible(true);break;
         }
         // </editor-fold>
        
        
        
            if (tratou){
                    //incoming.append(sala.informaUltimaMensagem());
                atorRede.enviarJogada(valorDoDado, tabuleiro.verificarEstadoEspecial());
                    return true;
            }else{
                    //JOptionPane.showMessageDialog(frame, "O jogo acabou!! O jogador ganhou.");
                    return false;
            }
            
        }else{
            return false;
        }
    }
    
    public static int dialogoConfirmacao(JFrame chamador, String mensagem, String titulo, String opcaoUm, String opcaoDois){  
              
        int opcao = 0;  
                     
        Object[] options = { opcaoUm, opcaoDois };              
        Object msg = mensagem;  
        JOptionPane pane = new JOptionPane(msg, JOptionPane.QUESTION_MESSAGE,   
                JOptionPane.DEFAULT_OPTION, null, options, options[0]);              
        JDialog dialog = pane.createDialog(chamador, titulo);
        dialog.setVisible(true);  
        Object selectedValue = pane.getValue();
        
        for(int counter = 0, maxCounter = options.length; counter < maxCounter; counter++) {  
            if(options[counter].equals(selectedValue))
                    opcao = counter;
        }      
        return opcao;  
    }   
  
    public void receberLanceRede(int valorDoDado, boolean tirouCinco) {
        
        if(tirouCinco){
            tabuleiro.alterarEstadoEspecial();
            System.out.println("...bonus já foi usado......");
        }
        
        tabuleiro.trataJogada(valorDoDado);
        int posicaoAdversario = tabuleiro.getPosicao();
        System.out.println("A Posicao do Adversario eh: "+posicaoAdversario);
        if(posicaoAdversario>35)
        {
            //adversario ganhou
        }
        // <editor-fold defaultstate="collapsed">
            switch (exAdversario){
                case 0: a0.setVisible(false);break;
                case 1: a1.setVisible(false);break;
                case 2: a2.setVisible(false);break;
                case 3: a3.setVisible(false);break;
                case 4: a4.setVisible(false);break;
                case 5: a5.setVisible(false);break;
                case 6: a6.setVisible(false);break;
                case 7: a7.setVisible(false);break;
                case 8: a8.setVisible(false);break;
                case 9: a9.setVisible(false);break;
                case 10: a10.setVisible(false);break;
                case 11: a11.setVisible(false);break;
                case 12: a12.setVisible(false);break;
                case 13: a13.setVisible(false);break;
                case 14: a14.setVisible(false);break;
                case 15: a15.setVisible(false);break;
                case 16: a16.setVisible(false);break;
                case 17: a17.setVisible(false);break;
                case 18: a18.setVisible(false);break;
                case 19: a19.setVisible(false);break;
                case 20: a20.setVisible(false);break;
                case 21: a21.setVisible(false);break;
                case 22: a22.setVisible(false);break;
                case 23: a23.setVisible(false);break;
                case 24: a24.setVisible(false);break;
                case 25: a25.setVisible(false);break;
                case 26: a26.setVisible(false);break;
                case 27: a27.setVisible(false);break;
                case 28: a28.setVisible(false);break;
                case 29: a29.setVisible(false);break;
                case 30: a30.setVisible(false);break;
                case 31: a31.setVisible(false);break;
                case 32: a32.setVisible(false);break;
                case 33: a33.setVisible(false);break;
                case 34: a34.setVisible(false);break;
                case 35: a35.setVisible(false);break;
             }
            //</editor-fold>
        exAdversario = posicaoAdversario;
        // <editor-fold defaultstate="collapsed">
        switch (exAdversario){
            case 0: a0.setVisible(true);break;
            case 1: a1.setVisible(true);break;
            case 2: a2.setVisible(true);break;
            case 3: a3.setVisible(true);break;
            case 4: a4.setVisible(true);break;
            case 5: a5.setVisible(true);break;
            case 6: a6.setVisible(true);break;
            case 7: a7.setVisible(true);break;
            case 8: a8.setVisible(true);break;
            case 9: a9.setVisible(true);break;
            case 10: a10.setVisible(true);break;
            case 11: a11.setVisible(true);break;
            case 12: a12.setVisible(true);break;
            case 13: a13.setVisible(true);break;
            case 14: a14.setVisible(true);break;
            case 15: a15.setVisible(true);break;
            case 16: a16.setVisible(true);break;
            case 17: a17.setVisible(true);break;
            case 18: a18.setVisible(true);break;
            case 19: a19.setVisible(true);break;
            case 20: a20.setVisible(true);break;
            case 21: a21.setVisible(true);break;
            case 22: a22.setVisible(true);break;
            case 23: a23.setVisible(true);break;
            case 24: a24.setVisible(true);break;
            case 25: a25.setVisible(true);break;
            case 26: a26.setVisible(true);break;
            case 27: a27.setVisible(true);break;
            case 28: a28.setVisible(true);break;
            case 29: a29.setVisible(true);break;
            case 30: a30.setVisible(true);break;
            case 31: a31.setVisible(true);break;
            case 32: a32.setVisible(true);break;
            case 33: a33.setVisible(true);break;
            case 34: a34.setVisible(true);break;
            case 35: a35.setVisible(true);break;
        }
        // </editor-fold>
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        a1 = new javax.swing.JLabel();
        a2 = new javax.swing.JLabel();
        a3 = new javax.swing.JLabel();
        a4 = new javax.swing.JLabel();
        a5 = new javax.swing.JLabel();
        a6 = new javax.swing.JLabel();
        a7 = new javax.swing.JLabel();
        a8 = new javax.swing.JLabel();
        a9 = new javax.swing.JLabel();
        a10 = new javax.swing.JLabel();
        a11 = new javax.swing.JLabel();
        a12 = new javax.swing.JLabel();
        a13 = new javax.swing.JLabel();
        a14 = new javax.swing.JLabel();
        a15 = new javax.swing.JLabel();
        a16 = new javax.swing.JLabel();
        a17 = new javax.swing.JLabel();
        a18 = new javax.swing.JLabel();
        a19 = new javax.swing.JLabel();
        a20 = new javax.swing.JLabel();
        a21 = new javax.swing.JLabel();
        a22 = new javax.swing.JLabel();
        a23 = new javax.swing.JLabel();
        a24 = new javax.swing.JLabel();
        a25 = new javax.swing.JLabel();
        a26 = new javax.swing.JLabel();
        a27 = new javax.swing.JLabel();
        a28 = new javax.swing.JLabel();
        a29 = new javax.swing.JLabel();
        a30 = new javax.swing.JLabel();
        a31 = new javax.swing.JLabel();
        a32 = new javax.swing.JLabel();
        a33 = new javax.swing.JLabel();
        a34 = new javax.swing.JLabel();
        a35 = new javax.swing.JLabel();
        b1 = new javax.swing.JLabel();
        b2 = new javax.swing.JLabel();
        b3 = new javax.swing.JLabel();
        b4 = new javax.swing.JLabel();
        b5 = new javax.swing.JLabel();
        b6 = new javax.swing.JLabel();
        b7 = new javax.swing.JLabel();
        b8 = new javax.swing.JLabel();
        b9 = new javax.swing.JLabel();
        b10 = new javax.swing.JLabel();
        b11 = new javax.swing.JLabel();
        b12 = new javax.swing.JLabel();
        b13 = new javax.swing.JLabel();
        b14 = new javax.swing.JLabel();
        b15 = new javax.swing.JLabel();
        b16 = new javax.swing.JLabel();
        b17 = new javax.swing.JLabel();
        b18 = new javax.swing.JLabel();
        b19 = new javax.swing.JLabel();
        b20 = new javax.swing.JLabel();
        b21 = new javax.swing.JLabel();
        b22 = new javax.swing.JLabel();
        b23 = new javax.swing.JLabel();
        b24 = new javax.swing.JLabel();
        b25 = new javax.swing.JLabel();
        b26 = new javax.swing.JLabel();
        b27 = new javax.swing.JLabel();
        b28 = new javax.swing.JLabel();
        b29 = new javax.swing.JLabel();
        b30 = new javax.swing.JLabel();
        b31 = new javax.swing.JLabel();
        b32 = new javax.swing.JLabel();
        b33 = new javax.swing.JLabel();
        b34 = new javax.swing.JLabel();
        b35 = new javax.swing.JLabel();
        b0 = new javax.swing.JLabel();
        a0 = new javax.swing.JLabel();
        buttonIniciarJogo = new javax.swing.JButton();
        ButtonSairJogo = new javax.swing.JButton();
        jButtonRolarDados = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JooRmanji ----- JooRmanji");
        setForeground(java.awt.Color.white);
        setMinimumSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/Rotolo1.png"))); // NOI18N
        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 670, 300));

        a1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a1.setDisabledIcon(null);
        getContentPane().add(a1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 130, 30, 30));

        a2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a2.setDisabledIcon(null);
        getContentPane().add(a2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 130, 30, 30));

        a3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a3.setDisabledIcon(null);
        getContentPane().add(a3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, 30, 30));

        a4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a4.setDisabledIcon(null);
        getContentPane().add(a4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 30, 30));

        a5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a5.setDisabledIcon(null);
        getContentPane().add(a5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, 30, 30));

        a6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a6.setDisabledIcon(null);
        getContentPane().add(a6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 30, 30));

        a7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a7.setDisabledIcon(null);
        getContentPane().add(a7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 30, 30));

        a8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a8.setDisabledIcon(null);
        getContentPane().add(a8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 30, 30));

        a9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a9.setDisabledIcon(null);
        getContentPane().add(a9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, 30, 30));

        a10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a10.setDisabledIcon(null);
        getContentPane().add(a10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 30, 30));

        a11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a11.setDisabledIcon(null);
        getContentPane().add(a11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 30, 30));

        a12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a12.setDisabledIcon(null);
        getContentPane().add(a12, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 30, 30));

        a13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a13.setDisabledIcon(null);
        getContentPane().add(a13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 30, 30));

        a14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a14.setDisabledIcon(null);
        getContentPane().add(a14, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 30, 20));

        a15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a15.setDisabledIcon(null);
        getContentPane().add(a15, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 30, 30));

        a16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        a16.setDisabledIcon(null);
        getContentPane().add(a16, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 30, 20));

        a17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        a17.setDisabledIcon(null);
        getContentPane().add(a17, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, 20, 30));

        a18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        a18.setDisabledIcon(null);
        getContentPane().add(a18, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 30, 30));

        a19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        a19.setDisabledIcon(null);
        getContentPane().add(a19, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 30, 30));

        a20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        a20.setDisabledIcon(null);
        getContentPane().add(a20, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 30, 30));

        a21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        a21.setDisabledIcon(null);
        getContentPane().add(a21, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 30, 30));

        a22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        a22.setDisabledIcon(null);
        getContentPane().add(a22, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 30, 30));

        a23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        a23.setDisabledIcon(null);
        getContentPane().add(a23, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, 30, 30));

        a24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        a24.setDisabledIcon(null);
        getContentPane().add(a24, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, 30, 30));

        a25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        a25.setDisabledIcon(null);
        getContentPane().add(a25, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, 20, 30));

        a26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        a26.setDisabledIcon(null);
        getContentPane().add(a26, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, 30, 30));

        a27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        a27.setDisabledIcon(null);
        getContentPane().add(a27, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, 30, 30));

        a28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        a28.setDisabledIcon(null);
        getContentPane().add(a28, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, 30, 30));

        a29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        a29.setDisabledIcon(null);
        getContentPane().add(a29, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 30, 30));

        a30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        a30.setDisabledIcon(null);
        getContentPane().add(a30, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 220, 40, 30));

        a31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a31.setDisabledIcon(null);
        getContentPane().add(a31, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 30, 30));

        a32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a32.setDisabledIcon(null);
        getContentPane().add(a32, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, 30, 30));

        a33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a33.setDisabledIcon(null);
        getContentPane().add(a33, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, 30, 30));

        a34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a34.setDisabledIcon(null);
        getContentPane().add(a34, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 30, 30));

        a35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        a35.setDisabledIcon(null);
        getContentPane().add(a35, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, 30, 30));

        b1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b1.setDisabledIcon(null);
        getContentPane().add(b1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 30, 30));

        b2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b2.setDisabledIcon(null);
        getContentPane().add(b2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 30, 30));

        b3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b3.setDisabledIcon(null);
        getContentPane().add(b3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 30, 30));

        b4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b4.setDisabledIcon(null);
        getContentPane().add(b4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 30, 30));

        b5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b5.setDisabledIcon(null);
        getContentPane().add(b5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 360, 30, 30));

        b6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b6.setDisabledIcon(null);
        getContentPane().add(b6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 370, 30, 30));

        b7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b7.setDisabledIcon(null);
        getContentPane().add(b7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, 30, 30));

        b8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b8.setDisabledIcon(null);
        getContentPane().add(b8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, 30, 30));

        b9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b9.setDisabledIcon(null);
        getContentPane().add(b9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 410, 30, 30));

        b10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b10.setDisabledIcon(null);
        getContentPane().add(b10, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, 30, 30));

        b11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b11.setDisabledIcon(null);
        getContentPane().add(b11, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 430, 30, 30));

        b12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b12.setDisabledIcon(null);
        getContentPane().add(b12, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 440, 30, 30));

        b13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b13.setDisabledIcon(null);
        getContentPane().add(b13, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 450, 30, 30));

        b14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b14.setDisabledIcon(null);
        getContentPane().add(b14, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 440, 30, 30));

        b15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b15.setDisabledIcon(null);
        getContentPane().add(b15, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 430, 30, 30));

        b16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b16.setDisabledIcon(null);
        getContentPane().add(b16, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 410, 30, 30));

        b17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        b17.setDisabledIcon(null);
        getContentPane().add(b17, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 390, 30, 30));

        b18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        b18.setDisabledIcon(null);
        getContentPane().add(b18, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 360, 30, 30));

        b19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        b19.setDisabledIcon(null);
        getContentPane().add(b19, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 340, 30, 30));

        b20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        b20.setDisabledIcon(null);
        getContentPane().add(b20, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 320, 30, 30));

        b21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        b21.setDisabledIcon(null);
        getContentPane().add(b21, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 310, 30, 30));

        b22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        b22.setDisabledIcon(null);
        getContentPane().add(b22, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 310, 30, 30));

        b23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        b23.setDisabledIcon(null);
        getContentPane().add(b23, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, 30, 30));

        b24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        b24.setDisabledIcon(null);
        getContentPane().add(b24, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, 30, 30));

        b25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        b25.setDisabledIcon(null);
        getContentPane().add(b25, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, 30, 30));

        b26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        b26.setDisabledIcon(null);
        getContentPane().add(b26, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 290, 30, 30));

        b27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        b27.setDisabledIcon(null);
        getContentPane().add(b27, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 30, 30));

        b28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        b28.setDisabledIcon(null);
        getContentPane().add(b28, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, 30, 30));

        b29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        b29.setDisabledIcon(null);
        getContentPane().add(b29, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, 30, 30));

        b30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2v.png"))); // NOI18N
        b30.setDisabledIcon(null);
        getContentPane().add(b30, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 30, 30));

        b31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b31.setDisabledIcon(null);
        getContentPane().add(b31, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 30, 30));

        b32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b32.setDisabledIcon(null);
        getContentPane().add(b32, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 20, 30));

        b33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b33.setDisabledIcon(null);
        getContentPane().add(b33, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 30, 30));

        b34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b34.setDisabledIcon(null);
        getContentPane().add(b34, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, 30, 30));

        b35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone2.png"))); // NOI18N
        b35.setDisabledIcon(null);
        getContentPane().add(b35, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, 30, 30));

        b0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone50.png"))); // NOI18N
        getContentPane().add(b0, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 40, 50));

        a0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/icone50v.png"))); // NOI18N
        a0.setDisabledIcon(null);
        getContentPane().add(a0, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 140, 40, 50));

        buttonIniciarJogo.setText("Iniciar Jogo");
        buttonIniciarJogo.setDisabledIcon(null);
        buttonIniciarJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIniciarJogoActionPerformed(evt);
            }
        });
        getContentPane().add(buttonIniciarJogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 530, 100, -1));

        ButtonSairJogo.setText("Sair do Jogo");
        ButtonSairJogo.setDisabledIcon(null);
        ButtonSairJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSairJogoActionPerformed(evt);
            }
        });
        getContentPane().add(ButtonSairJogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 530, -1, -1));

        jButtonRolarDados.setText("Rolar Dados");
        jButtonRolarDados.setDisabledIcon(null);
        jButtonRolarDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRolarDadosActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonRolarDados, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 160, 70));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 290, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/joormanji/Fundoo.jpg"))); // NOI18N
        jLabel1.setAlignmentY(0.0F);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void buttonIniciarJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIniciarJogoActionPerformed
// TODO add your handling code here:
    //jLabel2.setVisible(false);
    AtorJogador.this.atorRede.iniciarPartidaRede();
}//GEN-LAST:event_buttonIniciarJogoActionPerformed

private void ButtonSairJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSairJogoActionPerformed
// TODO add your handling code here:
    AtorJogador.this.atorRede.desconectar();
    System.exit(0);
}//GEN-LAST:event_ButtonSairJogoActionPerformed

private void jButtonRolarDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRolarDadosActionPerformed
// TODO add your handling code here:
    
    //obtem o novo numeros de casas que pode avancar
    int valorDoDado = tabuleiro.rolarDados();
    System.out.println("\nApertando meu Botão, o DADO VALE= "+valorDoDado);
    boolean tratou = AtorJogador.this.novaJogada(valorDoDado);
    if (tratou){
            //outgoing.setText("");
            //outgoing.requestFocus();
        }else{
            JOptionPane.showMessageDialog(AtorJogador.this.getFrame(), "Não é sua vez, aguarde!");
        }
}//GEN-LAST:event_jButtonRolarDadosActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    jButton1.setVisible(false);
}//GEN-LAST:event_jButton1ActionPerformed
    
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AtorJogador novo = new AtorJogador();
                novo.go();
                novo.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonSairJogo;
    private javax.swing.JLabel a0;
    private javax.swing.JLabel a1;
    private javax.swing.JLabel a10;
    private javax.swing.JLabel a11;
    private javax.swing.JLabel a12;
    private javax.swing.JLabel a13;
    private javax.swing.JLabel a14;
    private javax.swing.JLabel a15;
    private javax.swing.JLabel a16;
    private javax.swing.JLabel a17;
    private javax.swing.JLabel a18;
    private javax.swing.JLabel a19;
    private javax.swing.JLabel a2;
    private javax.swing.JLabel a20;
    private javax.swing.JLabel a21;
    private javax.swing.JLabel a22;
    private javax.swing.JLabel a23;
    private javax.swing.JLabel a24;
    private javax.swing.JLabel a25;
    private javax.swing.JLabel a26;
    private javax.swing.JLabel a27;
    private javax.swing.JLabel a28;
    private javax.swing.JLabel a29;
    private javax.swing.JLabel a3;
    private javax.swing.JLabel a30;
    private javax.swing.JLabel a31;
    private javax.swing.JLabel a32;
    private javax.swing.JLabel a33;
    private javax.swing.JLabel a34;
    private javax.swing.JLabel a35;
    private javax.swing.JLabel a4;
    private javax.swing.JLabel a5;
    private javax.swing.JLabel a6;
    private javax.swing.JLabel a7;
    private javax.swing.JLabel a8;
    private javax.swing.JLabel a9;
    private javax.swing.JLabel b0;
    private javax.swing.JLabel b1;
    private javax.swing.JLabel b10;
    private javax.swing.JLabel b11;
    private javax.swing.JLabel b12;
    private javax.swing.JLabel b13;
    private javax.swing.JLabel b14;
    private javax.swing.JLabel b15;
    private javax.swing.JLabel b16;
    private javax.swing.JLabel b17;
    private javax.swing.JLabel b18;
    private javax.swing.JLabel b19;
    private javax.swing.JLabel b2;
    private javax.swing.JLabel b20;
    private javax.swing.JLabel b21;
    private javax.swing.JLabel b22;
    private javax.swing.JLabel b23;
    private javax.swing.JLabel b24;
    private javax.swing.JLabel b25;
    private javax.swing.JLabel b26;
    private javax.swing.JLabel b27;
    private javax.swing.JLabel b28;
    private javax.swing.JLabel b29;
    private javax.swing.JLabel b3;
    private javax.swing.JLabel b30;
    private javax.swing.JLabel b31;
    private javax.swing.JLabel b32;
    private javax.swing.JLabel b33;
    private javax.swing.JLabel b34;
    private javax.swing.JLabel b35;
    private javax.swing.JLabel b4;
    private javax.swing.JLabel b5;
    private javax.swing.JLabel b6;
    private javax.swing.JLabel b7;
    private javax.swing.JLabel b8;
    private javax.swing.JLabel b9;
    private javax.swing.JButton buttonIniciarJogo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonRolarDados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

}
