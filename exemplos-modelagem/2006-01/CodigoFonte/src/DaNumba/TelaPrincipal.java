package DaNumba;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.ImageIcon.*;
import javax.swing.table.*;

import java.util.ArrayList;
import java.net.*;

/**
 * Classe que implementa a tela principal do jogo e suas funcionalidades.
 * @author DaNumba
 */
public class TelaPrincipal extends JFrame implements ActionListener, MouseListener {
    
    //componentes da interface
    private JTextField caixaSomatorio;
    private JTextField caixaPontuacao;
    private JTextField caixaNivel;    
    private JTextField caixaProximoNivel;
    private JButton botaoMenu;
    private JLabel labelSomatorio;
    private JLabel labelPontuacao;
    private JLabel labelNivel;
    private JLabel labelProximoNivel;
    private JScrollPane pane;
    private JTable table;
    
    //componentes do jogo
    private Partida objPartida;
    private Object[][] dados;
    private ImageIcon[] valor;
    
    //Construtor da classe
    public TelaPrincipal(Partida pObjPartida) {
        objPartida = pObjPartida;
        initComponents();
        inicializaTabela();
    }

    //Inicializa a tabela com os quadradinhos
    public void inicializaTabela(){
        //inicializa o array de quadradinhos
        valor = new ImageIcon[]{
            new ImageIcon("imagens/valor/valor0.png"),
            new ImageIcon("imagens/valor/valor1.png"),
            new ImageIcon("imagens/valor/valor2.png"),
            new ImageIcon("imagens/valor/valor3.png"),
            new ImageIcon("imagens/valor/valor4.png"),
            new ImageIcon("imagens/valor/valor5.png"),
            new ImageIcon("imagens/valor/valor6.png"),
            new ImageIcon("imagens/valor/valor7.png"),
            new ImageIcon("imagens/valor/valor8.png"),
            new ImageIcon("imagens/valor/valor9.png")
        };
        
        //inicializa a matriz que vai para a tabela
        dados = new Object[][]{
            {valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()]},
            {valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()]},
            {valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()]},
            {valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()]},
            {valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()]},
            {valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()]},
            {valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()]},
            {valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()]},
            {valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()]},
            {valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()],valor[objPartida.sortQ()]}
        };
        //inicializa o título da tabela
        Object[] titulos = new Object[] {"","","","","","","","","",""};
        //cria um tablemodel para a tabela
        AbstractTableModel model = new AbstractTableModel() {
            public int getColumnCount() { return 10; }
            public int getRowCount()    { return 10; }
            public String getColumnName(int col) {
                return ""; 
            }
            public Object getValueAt(int row, int col) { 
                return dados[row][col]; 
            }
            public Class getColumnClass(int col) {
               return getValueAt(0,col).getClass();
            }
            public void setValueAt(Object value, int row, int col) {
                dados[row][col] = value;
                fireTableCellUpdated(row, col);
            }
        };
        //cria uma nova tabela adicionando o modelo
        table = new JTable( model );
        //configura a altura de cada linha
        table.setRowHeight(48);
        //permite a seleção de apenas uma célula
        table.setCellSelectionEnabled(true);
        //configura a cor do fundo da tabela
        table.setBackground(new java.awt.Color(255, 164, 0));
        //retira ou coloca as linhas horizontais da tabela
        table.setShowHorizontalLines(true);
        //retira ou coloca as linhas verticais da tabela
        table.setShowVerticalLines(true);
        //seleciona apenas uma célula
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //coloca cor nas linhas da tabela
        table.setGridColor(new java.awt.Color(255, 100, 0));
        //cria um novo scroll e adiciona a tabela
        pane = new JScrollPane(table);
        //adiciona o scroll no frame
        getContentPane().add(pane);
        //define o tamanho e a localização dentro do frame
        pane.setBounds(10,10,460,481);
        //define para nunca aparecer barra de rolagem
        pane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        //configura o tipo e cor da borda
        pane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        //cria um evento para quando for clicado no mouse
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                //pega a linha clicada
                int linha = table.getSelectedRow();
                //pega a coluna clicada
                int coluna = table.getSelectedColumn();
                //trata o evento
                jTable1MouseClicked(evt,linha,coluna);
            }
        });
    }
    
    public int getValorDoQuadradinho(int linha, int coluna){
        //pega a imagemSelecionada
        ImageIcon selecionado = (ImageIcon)table.getValueAt(linha,coluna);
        //transforma em string        
        String selecao = String.valueOf(selecionado);
        //pega o valor do quadradinho (valor'X'.png)
        char cValor = selecao.charAt(19);
        //transforma em string
        String sValor = Character.toString(cValor);
        //zera a variável
        int valorDoQuadradinho = 0;
        //transforma em int
        valorDoQuadradinho = Integer.parseInt(sValor);
        
        return valorDoQuadradinho;
    }
    
    public int getSomatorio(){
        return Integer.parseInt(caixaSomatorio.getText());    
    }
    
    //método que procede o evento do clique do mouse
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt, int pLinha, int pColuna) {
        int temp = 0;
        try {
            //pega o valor do quadradinho para fazer os cálculos
            temp = this.getValorDoQuadradinho(pLinha,pColuna);
            
            //retira o quadradinho da matriz
            this.retirarQuadradinho(pLinha,pColuna);            

            //continua com os calculos da jogada
            objPartida.procederJogada(temp);
            
            //verifica se há condições de jogar ainda
            objPartida.autorizarJogada();
            
        } catch (Exception exc){
            JOptionPane.showMessageDialog(null,"Problema ao selecionar quadradinho","Erro",1);
        }
    }
    
    //método que re-preenche a matriz com os novos dados
    public void atualizarMatriz(){
        for (int i=0; i<10; i++){
            for (int j=0; j<10; j++){
                table.setValueAt(valor[objPartida.sortQ()],i,j);
            }
        }
    }
    
    //verifica se a matriz está vazia
    public boolean matrizVazia(){
        for (int i=0; i<10; i++){
            for (int j=0; j<10; j++){
                if (this.getValorDoQuadradinho(i,j) != 0) {                   
                    return false;
                }
            }
        }
        return true;
    }
    
    //verifica se a matriz está vazia
    public boolean existeValor(int pValor){
        for (int i=0; i<10; i++){
            for (int j=0; j<10; j++){
                if (this.getValorDoQuadradinho(i,j) == pValor) {                   
                    return true;
                }
            }
        }
        return false;
    }

    //método responsável por fazer a remoçao do quadradinho selecionado
    public void retirarQuadradinho(int pLinha, int pColuna){
        table.setValueAt(valor[0],pLinha,pColuna);
    }
    
    //método responsável por atualizar o somatório
    public void atualizarSomatorio(){
        caixaSomatorio.setText(""+objPartida.getNumeroAleatorio());
        caixaSomatorio.repaint();
    }
    
    //método responsável por atualizar a pontuação
    public void atualizarPontuacao(int pValor){
        caixaPontuacao.setText(""+pValor);
        caixaSomatorio.repaint();
    }
    
    //método responsável por atualizar o nível
    public void atualizarNivel(int pValor){
        caixaNivel.setText(""+pValor);
        caixaNivel.repaint();
    }
    
    //método responsável por atualizar o proximo nivel
    public void atualizarProximoNivel(int pValor){
        caixaProximoNivel.setText(""+pValor);
        caixaProximoNivel.repaint();
    }

    //métodos que inicializa os outros componentes do frame
    private void initComponents() {
        //Criação dos componentes que irão aparecer no frame da telaPrincipal
        //jPanel1 = new JPanel();
        labelSomatorio = new JLabel();
        caixaSomatorio = new JTextField();
        labelProximoNivel = new JLabel();
        caixaProximoNivel = new JTextField();
        labelPontuacao = new JLabel();
        caixaPontuacao = new JTextField();
        labelNivel = new JLabel();
        caixaNivel = new JTextField();
        botaoMenu = new JButton();
        
        //configura o layout como null
        getContentPane().setLayout(null);

        //Seta as opções do frame
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("DaNumba");
        getContentPane().setBackground(new java.awt.Color(255, 164, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("frameTelaPrincipal");
        setResizable(false);
        
        //Label do SOMATÓRIO
        labelSomatorio.setText("Somat\u00f3rio");
        labelSomatorio.setMaximumSize(new java.awt.Dimension(145, 14));
        labelSomatorio.setMinimumSize(new java.awt.Dimension(145, 14));
        labelSomatorio.setPreferredSize(new java.awt.Dimension(145, 15));
        getContentPane().add(labelSomatorio);
        labelSomatorio.setBounds(490, 10, 145, 15);

        //Caixa de texto do SOMATÓRIO
        caixaSomatorio.setBackground(new java.awt.Color(255, 164, 0));
        caixaSomatorio.setEditable(false);
        caixaSomatorio.setFont(new java.awt.Font("Arial Black", 0, 42));
        caixaSomatorio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        caixaSomatorio.setText(""+objPartida.getNumeroAleatorio());
        caixaSomatorio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        caixaSomatorio.setMaximumSize(new java.awt.Dimension(145, 100));
        caixaSomatorio.setMinimumSize(new java.awt.Dimension(145, 66));
        caixaSomatorio.setPreferredSize(new java.awt.Dimension(145, 100));
        getContentPane().add(caixaSomatorio);
        caixaSomatorio.setBounds(490, 30, 145, 100);
        
        //Label da PONTUAÇÃO do jogo
        labelPontuacao.setText("Pontua\u00e7\u00e3o");
        labelPontuacao.setPreferredSize(new java.awt.Dimension(145, 15));
        getContentPane().add(labelPontuacao);
        labelPontuacao.setBounds(490, 160, 145, 14);

        //Caixa de texto da PONTUAÇÃO
        caixaPontuacao.setBackground(new java.awt.Color(255, 164, 0));
        caixaPontuacao.setEditable(false);
        caixaPontuacao.setFont(new java.awt.Font("Arial Black", 0, 24));
        caixaPontuacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        caixaPontuacao.setText(""+objPartida.getPontuacao());
        caixaPontuacao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        caixaPontuacao.setPreferredSize(new java.awt.Dimension(145, 40));
        getContentPane().add(caixaPontuacao);
        caixaPontuacao.setBounds(490, 180, 145, 40);

        //Label da NIVEL ATUAL do jogo
        labelNivel.setText("Nível Atual");
        labelNivel.setPreferredSize(new java.awt.Dimension(145, 15));
        getContentPane().add(labelNivel);
        labelNivel.setBounds(490, 250, 145, 15);

        //Caixa de texto NIVEL ATUAL
        caixaNivel.setBackground(new java.awt.Color(255, 164, 0));
        caixaNivel.setEditable(false);
        caixaNivel.setFont(new java.awt.Font("Arial Black", 0, 24));
        caixaNivel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        caixaNivel.setText(""+objPartida.getNivel());
        caixaNivel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        caixaNivel.setPreferredSize(new java.awt.Dimension(145, 40));
        getContentPane().add(caixaNivel);
        caixaNivel.setBounds(490, 270, 145, 40);
        
        //Label do PROXIMO NIVEL
        labelProximoNivel.setText("Próximo Nível");
        labelProximoNivel.setPreferredSize(new java.awt.Dimension(145, 14));
        getContentPane().add(labelProximoNivel);
        labelProximoNivel.setBounds(490, 340, 145, 15);

        //Caixa de texto do PROXIMO NIVEL
        caixaProximoNivel.setBackground(new java.awt.Color(255, 164, 0));
        caixaProximoNivel.setEditable(false);
        caixaProximoNivel.setFont(new java.awt.Font("Arial Black", 0, 24));
        caixaProximoNivel.setText(""+objPartida.getProximoNivel());
        caixaProximoNivel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        caixaProximoNivel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        caixaProximoNivel.setPreferredSize(new java.awt.Dimension(145, 40));
        getContentPane().add(caixaProximoNivel);
        caixaProximoNivel.setBounds(490, 360, 145, 40);

        //Botão MENU
        botaoMenu.setIcon(new javax.swing.ImageIcon("imagens\\png\\botaoMenuRoxo.png"));
        botaoMenu.setBorderPainted(false);
        botaoMenu.setFocusPainted(false);
        botaoMenu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botaoMenu.setRequestFocusEnabled(false);
        botaoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoMenuActionPerformed(evt);
            }
        });
        getContentPane().add(botaoMenu);
        botaoMenu.setBounds(493, 430, 140, 55);
        
        //configura o tamanho da tela e a posiciona no meio
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-658)/2, (screenSize.height-527)/2, 658, 527);
    }// </editor-fold>   
    
    private void botaoMenuActionPerformed(java.awt.event.ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(null,"Se você retornar ao menu, vai perder esta partida.\nDeseja confirmar?","Confirmação",0);
        if (confirm == 0){
            objPartida.encerrarPartida();
        }
    }

    //tratamento de eventos adicionais
    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
    
    public void actionPerformed(ActionEvent evento) {}    
}
