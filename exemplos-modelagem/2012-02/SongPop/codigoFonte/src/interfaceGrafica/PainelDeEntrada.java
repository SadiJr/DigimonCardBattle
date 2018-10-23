package interfaceGrafica;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PainelDeEntrada extends PainelSongPop implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel jlNome, jlImagem;
	private JButton jbConectar;
	private JTextField jtNome;

	public PainelDeEntrada(InterfaceGraficaSongPop frame) {
		super(frame);
		instancieComponentes();
		posicioneComponentes();
	}
	
	public void conectar() {
		String nomeJogador = obterNomeInformado();
		boolean nomeValido = isNomeValido(nomeJogador);
		if (nomeValido) 
			frame.conectar(nomeJogador);
		else
			frame.notificarNomeInvalido();
	}

	public String obterNomeInformado() {
		return jtNome.getText();
	}

	protected boolean isNomeValido(String nomeJogador) {
		return nomeJogador != null &&
			   nomeJogador.trim().length() != 0;
	}
	
	private void instancieComponentes() 
	{		
		ImageIcon imageIcon = new ImageIcon(System.getProperty("user.dir")+"\\imagens\\songpop.jpg");
		Image image = imageIcon.getImage().getScaledInstance(283, 172, Image.SCALE_DEFAULT);		
		imageIcon = new ImageIcon(image);
		jlImagem = new JLabel(imageIcon);
		jlNome = new JLabel("Nome:");		
		jtNome = new JTextField();		
		jbConectar = new JButton("Conectar");
		jbConectar.addActionListener(this);
	}
	
	private void posicioneComponentes()
	{
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(218, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jlNome)
                        .addGap(18, 18, 18)
                        .addComponent(jtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(270, 270, 270))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbConectar)
                        .addGap(300, 300, 300))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jlImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(191, 191, 191))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jlImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlNome))
                .addGap(43, 43, 43)
                .addComponent(jbConectar)
                .addGap(102, 102, 102))
        );
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		conectar();
	}
}