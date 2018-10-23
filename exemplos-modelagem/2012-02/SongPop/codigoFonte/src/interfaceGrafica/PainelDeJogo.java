package interfaceGrafica;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

public class PainelDeJogo extends PainelSongPop implements ActionListener {
	private static final long serialVersionUID = 1L;	
	private JLabel jlImagem, jlPergunta, jlTimer;
	private JRadioButton rbAlternativa1, rbAlternativa2, rbAlternativa3, rbAlternativa4, rbAlternativa5;
	private JButton jbIniciarRodada, jbDesistirDaRodada, jbVisualizarResultados;
	private JLabel jlNomeJogador;
	private ButtonGroup bgAlternativas;
	private Timer timer;
	
	public PainelDeJogo(InterfaceGraficaSongPop frame, String nomeJogador, 
			            String pergunta, int tempoMax, boolean rodadaMusical) {
		super(frame);
		instancieComponentes(nomeJogador, pergunta, tempoMax, rodadaMusical);
		posicioneComponentes();
	}

	public void responder(int alternativaRespondida) {
		pararContagemRegressiva();
		desabilitarAlternativas();
		desabilitarOpcaoDesistir();		
		double tempoDaContagem = obterTempoDaContagem();
		frame.responder(alternativaRespondida, tempoDaContagem);
		habilitarOpcaoContinuar();
	}

	private void desabilitarAlternativas() {
		setEnabledAlternativas(false);
	}
	
	private void habilitarAlternativas() {
		setEnabledAlternativas(true);
	}
	
	private void setEnabledAlternativas(boolean enabled) {
		rbAlternativa1.setEnabled(enabled);
		rbAlternativa2.setEnabled(enabled);
		rbAlternativa3.setEnabled(enabled);
		rbAlternativa4.setEnabled(enabled);
		rbAlternativa5.setEnabled(enabled);
	}
	
	public void habilitarOpcaoDesistir() {
		jbDesistirDaRodada.setEnabled(true);
	}

	public void desabilitarOpcaoDesistir() {
		jbDesistirDaRodada.setEnabled(false);
	}

	public void habilitarOpcaoContinuar() {
		jbVisualizarResultados.setEnabled(true);
	}

	public void desabilitarOpcaoContinuar() {
		jbVisualizarResultados.setEnabled(false);
	}

	public void pararContagemRegressiva() {
		timer.stop();
	}

	public void iniciarContagemRegressiva() {
		timer.start();
	}

	public void mostrarAlternativaCorreta(int alternativaCorreta) {
		switch (alternativaCorreta) {
			case 0: rbAlternativa1.setBackground(Color.GREEN); break;
			case 1: rbAlternativa2.setBackground(Color.GREEN); break;
			case 2: rbAlternativa3.setBackground(Color.GREEN); break;
			case 3: rbAlternativa4.setBackground(Color.GREEN); break;
			case 4: rbAlternativa5.setBackground(Color.GREEN); break;
		}
	}

	public void mostrarAlternativas(String[] alternativas) {
		rbAlternativa1.setText(alternativas[0]);
		rbAlternativa2.setText(alternativas[1]);
		rbAlternativa3.setText(alternativas[2]);
		rbAlternativa4.setText(alternativas[3]);
		rbAlternativa5.setText(alternativas[4]);
		habilitarAlternativas();
	}

	public void iniciarRodada() {
		jbIniciarRodada.setEnabled(false);
		frame.iniciarRodada();
	}

	public void visualizarResultados() {
		frame.visualizarResultados();
	}

	public double obterTempoDaContagem() {
		double tempoDaContagem;
		try {
			String numero = jlTimer.getText();
			Number number = doubleFormat.parse(numero);
			tempoDaContagem = number.doubleValue();			
		} catch (ParseException e) {
			tempoDaContagem = 0; /* não deverá ocorrer */
		}
		return tempoDaContagem;
	}

	public void desistirDaRodada() {
		boolean confirmado = frame.pedirConfirmacaoDesistencia();
		if (confirmado)
			frame.desistirDaRodada();					
	}

	public void mostrarComplementoPergunta(String complementoPergunta) {
		String pergunta = jlPergunta.getText();
		pergunta = pergunta + " " + complementoPergunta;
		jlPergunta.setText(pergunta);
	}

	public void acabouOTempo() {		
		timer.stop();
		desabilitarAlternativas();
		desabilitarOpcaoDesistir();
		frame.responder(-1, -1);
		habilitarOpcaoContinuar();
	}
	
	private void instancieComponentes(String nomeJogador, String pergunta, int tempoMax, boolean rodadaMusical) 
	{		
		timer = new Timer(10, this);
		String path = rodadaMusical ? "clave.jpg" : "clave_x.jpg";
		path = System.getProperty("user.dir")+"\\imagens\\"+path;
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage().getScaledInstance(85, 86, Image.SCALE_DEFAULT);		
		imageIcon = new ImageIcon(image);
		jlImagem = new JLabel(imageIcon);
		jlTimer = new JLabel(tempoMax+".00");
		jlPergunta = new JLabel(pergunta);
		jlPergunta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jlNomeJogador = new JLabel(nomeJogador);
		jlNomeJogador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jbIniciarRodada = new JButton("Iniciar Rodada");		
		jbDesistirDaRodada = new JButton("Desistir da Rodada");
		jbVisualizarResultados = new JButton("Visualizar Resultados");				
		rbAlternativa1 = new JRadioButton("----------------");	
		rbAlternativa2 = new JRadioButton("----------------");
		rbAlternativa3 = new JRadioButton("----------------");
		rbAlternativa4 = new JRadioButton("----------------");
		rbAlternativa5 = new JRadioButton("----------------");
		bgAlternativas = new ButtonGroup();
		bgAlternativas.add(rbAlternativa1);
		bgAlternativas.add(rbAlternativa2);
		bgAlternativas.add(rbAlternativa3);
		bgAlternativas.add(rbAlternativa4);
		bgAlternativas.add(rbAlternativa5);
		jbIniciarRodada.addActionListener(this);
		jbDesistirDaRodada.addActionListener(this);
		jbVisualizarResultados.addActionListener(this);		
		rbAlternativa1.addActionListener(this);
		rbAlternativa2.addActionListener(this);
		rbAlternativa3.addActionListener(this);
		rbAlternativa4.addActionListener(this);
		rbAlternativa5.addActionListener(this);
		desabilitarAlternativas();
		desabilitarOpcaoContinuar();
	}
	
	private void posicioneComponentes()
	{
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(288, 288, 288)
                        .addComponent(jlImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(132, 132, 132)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rbAlternativa2)
                                .addComponent(rbAlternativa1)
                                .addComponent(rbAlternativa3)
                                .addComponent(rbAlternativa4)
                                .addComponent(rbAlternativa5)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jbDesistirDaRodada)
                                    .addGap(178, 178, 178)
                                    .addComponent(jbVisualizarResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(86, 86, 86)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlNomeJogador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(185, 185, 185)
                                        .addComponent(jbIniciarRodada))
                                    .addComponent(jlPergunta, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(316, 316, 316)
                        .addComponent(jlTimer)))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jlNomeJogador)
                .addGap(7, 7, 7)
                .addComponent(jlImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbIniciarRodada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlTimer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlPergunta)
                .addGap(18, 18, 18)
                .addComponent(rbAlternativa1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbAlternativa2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbAlternativa3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbAlternativa4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbAlternativa5)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbVisualizarResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbDesistirDaRodada))
                .addContainerGap(31, Short.MAX_VALUE))
        );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			double tempoDaContagem = obterTempoDaContagem() - 0.01;			
			if (Double.compare(tempoDaContagem, 0.0) <= 0)
				acabouOTempo();
			jlTimer.setText(doubleFormat.format(tempoDaContagem));
		}
		else if (e.getSource() == jbIniciarRodada) {
			iniciarRodada();
		}		
		else if (e.getSource() == jbDesistirDaRodada) {
			desistirDaRodada();
		}
		else if (e.getSource() == jbVisualizarResultados) {
			visualizarResultados();
		}
		else {
			JRadioButton b = (JRadioButton)e.getSource();
			if (b == rbAlternativa1)
				responder(0);
			else if (b == rbAlternativa2)
				responder(1);
			else if (b == rbAlternativa3)
				responder(2);
			else if (b == rbAlternativa4)
				responder(3);
			else
				responder(4);
		}
	}
}