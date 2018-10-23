package interfaceGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

public class PainelDeEspera extends PainelSongPop implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel jlMensagem;
	private JButton jbCancelar;

	public PainelDeEspera(InterfaceGraficaSongPop frame) {
		super(frame);
		instancieComponentes();
		posicioneComponentes();
	}
	
	private void instancieComponentes() 
	{		
		jlMensagem = new JLabel("Aguardando a conexão de um adversário...");
		jlMensagem.setFont(new java.awt.Font("Tahoma", 0, 18));
		jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(this);
	}
	
	private void posicioneComponentes()
	{ 
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(193, Short.MAX_VALUE)
                .addComponent(jlMensagem)
                .addGap(176, 176, 176))
            .addGroup(layout.createSequentialGroup()
                .addGap(307, 307, 307)
                .addComponent(jbCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(jlMensagem)
                .addGap(71, 71, 71)
                .addComponent(jbCancelar)
                .addContainerGap(163, Short.MAX_VALUE))
        );
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		frame.desconectar();
		frame.paginaInicial();
	}
}