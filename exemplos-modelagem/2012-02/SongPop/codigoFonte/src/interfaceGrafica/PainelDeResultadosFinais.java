package interfaceGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PainelDeResultadosFinais extends PainelSongPop implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel jlJogador, jlAdversario, jlPontuacaoJogador, jlPontuacaoAdversario, jlVencedor;
	private JTextField jtfPontuacaoJogador, jtfPontuacaoAdversario;
	private JButton jbSair, jbJogarNovamente;
	
	public PainelDeResultadosFinais(InterfaceGraficaSongPop frame, 
								   String nomeJogador, String nomeAdversario,										   
								   double pontuacaoTotalJogador, double pontuacaoTotalAdversario, 
								   String nomeVencedor, boolean porDesistencia) {
		super(frame);
		instanciarComponentes(nomeJogador, nomeAdversario, pontuacaoTotalJogador, 
				              pontuacaoTotalAdversario, nomeVencedor, porDesistencia);
		posicionarComponentes();
	}

	private void jogarNovamente() {
		frame.jogarNovamente();
	}
	
	private void sair() {
		frame.desconectar();
		frame.paginaInicial();
	}
		
	public void desabilitarOpcaoJogarNovamente() {
		jbJogarNovamente.setEnabled(false);
	}
		
	private void instanciarComponentes(String nomeJogador, String nomeAdversario,										   
			   						   double pontuacaoTotalJogador, double pontuacaoTotalAdversario, 
			   						   String nomeVencedor, boolean porDesistencia) {
		jlJogador = new JLabel(nomeJogador);
		jlJogador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jlAdversario = new JLabel(nomeAdversario);
		jlAdversario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jlPontuacaoJogador = new JLabel("Pontuação Total");
		jlPontuacaoAdversario = new JLabel("Pontuação Total");
		jtfPontuacaoJogador = new JTextField(doubleFormat.format(pontuacaoTotalJogador));
		jtfPontuacaoJogador.setEnabled(false);
		jtfPontuacaoAdversario = new JTextField(doubleFormat.format(pontuacaoTotalAdversario));
		jtfPontuacaoAdversario.setEnabled(false);
		jbSair = new JButton("Sair");
		jbJogarNovamente = new JButton("Jogar Novamente");
		jbSair.addActionListener(this);
		jbJogarNovamente.addActionListener(this);
		String mensagem = porDesistencia ? "O vencedor do jogo foi "+nomeVencedor+", pois seu adversário desistiu do jogo." 
				                         : "O vencedor do jogo foi "+nomeVencedor+".";
		jlVencedor = new JLabel(mensagem);
		jlVencedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	}
	
	private void posicionarComponentes() {
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jbSair))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jlPontuacaoJogador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtfPontuacaoJogador, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jlPontuacaoAdversario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtfPontuacaoAdversario, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbJogarNovamente)
                        .addGap(130, 130, 130))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jlJogador, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlAdversario, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jlVencedor, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlJogador)
                    .addComponent(jlAdversario))
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlPontuacaoJogador)
                    .addComponent(jtfPontuacaoJogador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlPontuacaoAdversario)
                    .addComponent(jtfPontuacaoAdversario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addComponent(jlVencedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSair)
                    .addComponent(jbJogarNovamente))
                .addGap(95, 95, 95))
        );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbJogarNovamente)
			jogarNovamente();
		else
			sair();
	}
}