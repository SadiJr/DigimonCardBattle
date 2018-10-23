package interfaceGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import rede.Resultado;

public class PainelDeResultadosParciais extends PainelSongPop implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel jlJogador, jlAdversario, jlPontuacaoJogador, jlPontuacaoAdversario;
	private JTextField jtfPontuacaoJogador, jtfPontuacaoAdversario;
	private JScrollPane jScrollPane1, jScrollPane2;
	private ModeloDeTabela modeloTabelaJogador, modeloTabelaAdversario;
	private JButton jbDesistir, jbContinuar;

	public PainelDeResultadosParciais(InterfaceGraficaSongPop frame, String nomeJogador, String nomeAdversario,
							  Resultado[][] resultadosJogador, Resultado[][] resultadosAdversario, 
							  double pontuacaoTotalJogador, double pontuacaoTotalAdversario,
							  boolean botaoContinuarAtivo, boolean botaoDesistirAtivo) {
		super(frame);
		instancieComponentes(nomeJogador, nomeAdversario, 
							 pontuacaoTotalJogador, pontuacaoTotalAdversario, 
							 botaoContinuarAtivo, botaoDesistirAtivo);
		preencheTabelas(resultadosJogador, resultadosAdversario);
		posicioneComponentes();
	}
	
	private void continuar() {
		frame.continuar();
	}
	
	private void desistirDoJogo() {
		boolean confirmado = frame.pedirConfirmacaoDesistencia();
		if (confirmado)
			frame.desistirDoJogo();	
	}
		
	private void preencheTabelas(Resultado[][] resultadosJogador, Resultado[][] resultadosAdversario) {
		String[] cabecalho = new String[]{"Nível", "Rodada", "Acerto", "Tempo de resposta", "Pontuação"};
		String[][] dados1 = pegaDadosResultados(resultadosJogador);
		String[][] dados2 = pegaDadosResultados(resultadosAdversario);

		modeloTabelaJogador.setCabecalho(cabecalho);
		modeloTabelaAdversario.setCabecalho(cabecalho);
		modeloTabelaJogador.setDados(dados1);
		modeloTabelaAdversario.setDados(dados2);
	}
	
	private String[][] pegaDadosResultados(Resultado[][] resultados) {
		String[][] dados = new String[resultados.length*resultados[0].length][5];
		for (int i = 0; i < resultados.length; i++) {
			for (int j = 0; j < resultados[i].length; j++) {
				int iTabela = 4*i + j;
				dados[iTabela] = pegaLinhaResultado(i, j, resultados[i][j]);
			}
		}
		return dados;
	}
	
	private String[] pegaLinhaResultado(int nivel, int rodada, Resultado r) {
		String[] linha = new String[5];
		linha[0] = rodada == 0 ? (nivel+1)+"" : "";
		linha[1] = (rodada+1)+"";
		if (r != null) {										
			linha[2] = r.isCorreta() ? "C" : "X";
			linha[3] = r.getTempoDeResposta() == -1 ? "X" : doubleFormat.format(r.getTempoDeResposta());
			linha[4] = doubleFormat.format(r.getPontuacao());
		}
		else {
			linha[2] = "-";
			linha[3] = "-";
			linha[4] = "-";
		}
		return linha;
	}
	
	public void habilitarOpcaoContinuar() {
		jbContinuar.setEnabled(true);
	}
	
	public void desabilitarOpcaoDesistir() {
		jbDesistir.setEnabled(false);
	}

	public void atualizaResultadosAdversario(Resultado resultado, double pontuacaoTotalAdversario) {
		String pontuacaoString = doubleFormat.format(pontuacaoTotalAdversario);
		jtfPontuacaoAdversario.setText(pontuacaoString);
		int numeroNivel = resultado.getNumeroNivel();
		int numeroRodada = resultado.getNumeroRodada();
		int iTabela = 4*numeroNivel + numeroRodada;
		String[] linha = pegaLinhaResultado(numeroNivel, numeroRodada, resultado);		
		for (int i = 0; i < 5; i++)
			modeloTabelaAdversario.setValueAt(linha[i], iTabela, i);
	}
	
	private void instancieComponentes(String nomeJogador, String nomeAdversario, 
									 double pontuacaoTotalJogador, double pontuacaoTotalAdversario,
									 boolean botaoContinuarAtivo, boolean botaoDesistirAtivo) 
	{					
		jlJogador = new JLabel(nomeJogador);
		jlAdversario = new JLabel(nomeAdversario);
		jlPontuacaoJogador = new JLabel("Pontuação Total");
		jtfPontuacaoJogador = new JTextField(doubleFormat.format(pontuacaoTotalJogador));
		jtfPontuacaoJogador.setHorizontalAlignment(JTextField.RIGHT);
		jtfPontuacaoJogador.setEnabled(false);		
		jlPontuacaoAdversario = new JLabel("Pontuação Total");					
		jtfPontuacaoAdversario = new JTextField(doubleFormat.format(pontuacaoTotalAdversario));
		jtfPontuacaoAdversario.setHorizontalAlignment(JTextField.RIGHT);
		jtfPontuacaoAdversario.setEnabled(false);
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        modeloTabelaJogador = new ModeloDeTabela();
        JTable jTable1 = new JTable(modeloTabelaJogador);
        jTable1.getTableHeader().setReorderingAllowed(false);        
        jScrollPane1.setViewportView(jTable1);
//        jTable1.getColumnModel().getColumn(0).setResizable(false);
//        jTable1.getColumnModel().getColumn(0).setPreferredWidth(35);
//        jTable1.getColumnModel().getColumn(1).setResizable(false);
//        jTable1.getColumnModel().getColumn(1).setPreferredWidth(55);
//        jTable1.getColumnModel().getColumn(2).setResizable(false);
//        jTable1.getColumnModel().getColumn(2).setPreferredWidth(45);
//        jTable1.getColumnModel().getColumn(3).setResizable(false);
//        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
//        jTable1.getColumnModel().getColumn(4).setResizable(false);
//        jTable1.getColumnModel().getColumn(4).setPreferredWidth(60);
        modeloTabelaAdversario = new ModeloDeTabela();
        JTable jTable2 = new JTable(modeloTabelaAdversario);
        jTable2.getTableHeader().setReorderingAllowed(false);              
        jScrollPane2.setViewportView(jTable2);        
//        jTable2.getColumnModel().getColumn(0).setResizable(false);
//        jTable2.getColumnModel().getColumn(0).setPreferredWidth(35);
//        jTable2.getColumnModel().getColumn(1).setResizable(false);
//        jTable2.getColumnModel().getColumn(1).setPreferredWidth(55);
//        jTable2.getColumnModel().getColumn(2).setResizable(false);
//        jTable2.getColumnModel().getColumn(2).setPreferredWidth(45);
//        jTable2.getColumnModel().getColumn(3).setResizable(false);
//        jTable2.getColumnModel().getColumn(3).setPreferredWidth(100);
//        jTable2.getColumnModel().getColumn(4).setResizable(false);
//        jTable2.getColumnModel().getColumn(4).setPreferredWidth(60);
        jbContinuar = new JButton("Continuar");
		jbDesistir = new JButton("Desistir do Jogo");
		jbContinuar.addActionListener(this);
		jbDesistir.addActionListener(this);
		jbContinuar.setEnabled(botaoContinuarAtivo);
		jbDesistir.setEnabled(botaoDesistirAtivo);
	}
	
	private void posicioneComponentes()
	{        
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jlJogador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlAdversario)
                .addGap(163, 163, 163))
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jlPontuacaoJogador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtfPontuacaoJogador, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlPontuacaoAdversario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtfPontuacaoAdversario, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jbDesistir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlJogador)
                    .addComponent(jlAdversario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfPontuacaoJogador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlPontuacaoJogador))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlPontuacaoAdversario)
                        .addComponent(jtfPontuacaoAdversario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbContinuar)
                    .addComponent(jbDesistir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbDesistir)
			desistirDoJogo();
		else
			continuar();
	}
}