package interfaceGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import logica.GeneroMusical;

public class PainelEscolhaGenero extends PainelSongPop implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ButtonGroup bgGeneros;
	private JLabel jlNomeJogador, jlMensagem;
	private JButton jbConfirmar, jbSair;
	private JRadioButton jrbRock, jrbMetal, jrbReligioso, jrbRap,
						 jrbAxe, jrbSertanejo, jrbJazz, jrbPop, 
						 jrbJovemGuarda, jrbMPB, jrbCountry, jrbSamba;

	public PainelEscolhaGenero(InterfaceGraficaSongPop frame, String nomeJogador, 
							   List<GeneroMusical> generosJaEscolhidos) {
		super(frame);
		instancieComponentes(nomeJogador, generosJaEscolhidos);
		posicioneComponentes();
	}
	
	private void escolherGenero() {
		GeneroMusical generoSelecionado = identificaGeneroSelecionado();
		int indiceGenero = generoSelecionado.ordinal();
		frame.escolherGenero(indiceGenero);
	}
	
	private void sair() {
		boolean confirmado = frame.desconectar();
		if (confirmado)
			frame.paginaInicial();
	}
	
	private void instancieComponentes(String nomeJogador, List<GeneroMusical> generosJaEscolhidos) 
	{		
		jlNomeJogador = new JLabel(nomeJogador);
		bgGeneros = new ButtonGroup();
		instanciaBotoesDeRadio(generosJaEscolhidos);
		jlMensagem = new JLabel("Escolha o gênero musical do próximo nível:");
		jlMensagem.setFont(new java.awt.Font("Tahoma", 0, 14));
		jbConfirmar = new JButton("Confirmar");
		jbConfirmar.addActionListener(this);
		jbSair = new JButton("Sair");
		jbSair.addActionListener(this);
	}
	
	private void instanciaBotoesDeRadio(List<GeneroMusical> generosJaEscolhidos) { 
		jrbRock = instanciaBotaoDeRadio(GeneroMusical.ROCK, generosJaEscolhidos);
		jrbMetal = instanciaBotaoDeRadio(GeneroMusical.METAL, generosJaEscolhidos);
		jrbReligioso = instanciaBotaoDeRadio(GeneroMusical.RELIGIOSO, generosJaEscolhidos);
		jrbRap = instanciaBotaoDeRadio(GeneroMusical.RAP, generosJaEscolhidos);
		jrbAxe = instanciaBotaoDeRadio(GeneroMusical.AXÉ, generosJaEscolhidos);
		jrbSertanejo = instanciaBotaoDeRadio(GeneroMusical.SERTANEJO, generosJaEscolhidos);
		jrbJazz = instanciaBotaoDeRadio(GeneroMusical.JAZZ, generosJaEscolhidos);
		jrbPop = instanciaBotaoDeRadio(GeneroMusical.POP, generosJaEscolhidos);
		jrbJovemGuarda = instanciaBotaoDeRadio(GeneroMusical.JOVEM_GUARDA, generosJaEscolhidos);
		jrbMPB = instanciaBotaoDeRadio(GeneroMusical.MPB, generosJaEscolhidos);
		jrbCountry = instanciaBotaoDeRadio(GeneroMusical.COUNTRY, generosJaEscolhidos);
		jrbSamba = instanciaBotaoDeRadio(GeneroMusical.SAMBA, generosJaEscolhidos);
		// Seleciona o primeiro botão disponível
		if (jrbRock.isEnabled())
			jrbRock.setSelected(true);
		else if (jrbMetal.isEnabled())
			jrbMetal.setSelected(true);
		else if (jrbReligioso.isEnabled())
			jrbReligioso.setSelected(true);
		else if (jrbRap.isEnabled())
			jrbRap.setSelected(true);
		else if (jrbAxe.isEnabled())
			jrbAxe.setSelected(true);
		else if (jrbSertanejo.isEnabled())
			jrbSertanejo.setSelected(true);
		else if (jrbJazz.isEnabled())
			jrbJazz.setSelected(true);
		else if (jrbPop.isEnabled())
			jrbPop.setSelected(true);
		else if (jrbJovemGuarda.isEnabled())
			jrbJovemGuarda.setSelected(true);
		else if (jrbMPB.isEnabled())
			jrbMPB.setSelected(true);
		else if (jrbCountry.isEnabled())
			jrbCountry.setSelected(true);
		else //if (jrbSamba.isSelected())
			jrbSamba.setSelected(true);		
	}
	
	private JRadioButton instanciaBotaoDeRadio(GeneroMusical genero, List<GeneroMusical> generosJaEscolhidos) {
		JRadioButton rb;
		String nomeGenero = genero.toString();
		boolean jaEscolhido = generosJaEscolhidos.contains(genero);
		rb = new JRadioButton(nomeGenero);
		if (jaEscolhido)
			rb.setEnabled(false);
		bgGeneros.add(rb);
		return rb;
	}
	
	private void posicioneComponentes()
	{
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(jlNomeJogador)
                        .addGap(167, 167, 167))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(62, 62, 62)
                            .addComponent(jlMensagem))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbSair, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jrbRock)
                                    .addComponent(jrbMetal)
                                    .addComponent(jrbReligioso)
                                    .addComponent(jrbRap)
                                    .addComponent(jrbAxe)
                                    .addComponent(jrbSertanejo))
                                .addGap(170, 170, 170)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jrbJazz)
                                    .addComponent(jrbPop)
                                    .addComponent(jrbJovemGuarda)
                                    .addComponent(jrbMPB)
                                    .addComponent(jrbCountry)
                                    .addComponent(jrbSamba))))))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jlNomeJogador)
                .addGap(45, 45, 45)
                .addComponent(jlMensagem)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrbRock)
                    .addComponent(jrbJazz))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrbMetal)
                    .addComponent(jrbPop))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrbReligioso)
                    .addComponent(jrbJovemGuarda))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrbRap)
                    .addComponent(jrbMPB))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrbAxe)
                    .addComponent(jrbCountry))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrbSertanejo)
                    .addComponent(jrbSamba))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbConfirmar)
                    .addComponent(jbSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );
	}

	private GeneroMusical identificaGeneroSelecionado() {
		GeneroMusical genero;
		if (jrbRock.isSelected())
			genero = GeneroMusical.ROCK;
		else if (jrbMetal.isSelected())
			genero = GeneroMusical.METAL;
		else if (jrbReligioso.isSelected())
			genero = GeneroMusical.RELIGIOSO;
		else if (jrbRap.isSelected())
			genero = GeneroMusical.RAP;
		else if (jrbAxe.isSelected())
			genero = GeneroMusical.AXÉ;
		else if (jrbSertanejo.isSelected())
			genero = GeneroMusical.SERTANEJO;
		else if (jrbJazz.isSelected())
			genero = GeneroMusical.JAZZ;
		else if (jrbPop.isSelected())
			genero = GeneroMusical.POP;
		else if (jrbJovemGuarda.isSelected())
			genero = GeneroMusical.JOVEM_GUARDA;
		else if (jrbMPB.isSelected())
			genero = GeneroMusical.MPB;
		else if (jrbCountry.isSelected())
			genero = GeneroMusical.COUNTRY;
		else //if (jrbSamba.isSelected())
			genero = GeneroMusical.SAMBA;
		return genero;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbConfirmar)
			escolherGenero();		
		else
			sair();
	}
}