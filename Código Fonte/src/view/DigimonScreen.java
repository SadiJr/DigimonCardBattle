package view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import actor.ActorPlayer;
import model.CardPOJO;
import model.PlayerMovePOJO;

@SuppressWarnings("serial")
public class DigimonScreen extends JFrame {
	private final ActorPlayer player;
	private JButton updateCard;
	private JButton discardHand;
	private JButton jump;
	private JLabel phaseName;
	private JMenuBar jcomp5;
	private JLabel opponentName;
	private JLabel advCemiterySize;
	private JLabel advDeckSIze;
	private JButton advCard1;
	private JButton adCard2;
	private JButton adCard3;
	private JButton adCard4;
	private JCheckBox advVict1;
	private JCheckBox advVict2;
	private JCheckBox advVict3;
	private JLabel advDp;
	private JButton digimonCard;
	private JButton supportCard;
	private JButton advSupportCard;
	private JButton advDigimonCard;
	private JLabel atk1;
	private JLabel atk2;
	private JLabel atk3;
	private JLabel advAtk2;
	private JLabel advAtk3;
	private JLabel advAtk1;
	private JLabel dp;
	private JCheckBox vict1;
	private JCheckBox vict2;
	private JCheckBox vict3;
	private JButton card1;
	private JButton card2;
	private JButton card3;
	private JButton card4;
	private JLabel deckSize;
	private JLabel cemiterySize;
	private JLabel name;
	
	private String nameCard1;
	private String nameCard2;
	private String nameCard3;
	private String nameCard4;
	private String advNameCard1;
	private String advNameCard2;
	private String advNameCard3;
	private String advNameCard4;

	private ActionListener l = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == card1) {
				System.out.println(card1.getName());
				String name = card1.getName();
				player.viewAttributes(name);
			}
			
		}
	};
	public DigimonScreen() {
		this.player = null;
		
	}

	public static void main(String[] args) {
		DigimonScreen digimonScreen = new DigimonScreen(null);
		digimonScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		digimonScreen.pack();
		digimonScreen.setVisible(true);
	}

	public DigimonScreen(ActorPlayer actorPlayer) {
		this.player = actorPlayer;
		config();
	}
	
	
	
	
	
	
	
	private void config() {
		// construct preComponents
		JMenu menuMenu = new JMenu("Menu");
		JMenuItem conectarItem = new JMenuItem("Conectar");
		menuMenu.add(conectarItem);
		JMenuItem desconectarItem = new JMenuItem("Desconectar");
		menuMenu.add(desconectarItem);
		JMenuItem sairItem = new JMenuItem("Sair");
		menuMenu.add(sairItem);

		// construct components
		updateCard = new JButton("Evoluir Carta");
		discardHand = new JButton("Descartar Mão");
		jump = new JButton("Próxima Fase");
		phaseName = new JLabel("newLabel");
		jcomp5 = new JMenuBar();
		jcomp5.add(menuMenu);
		opponentName = new JLabel("newLabel");
		advCemiterySize = new JLabel("newLabel");
		advDeckSIze = new JLabel("newLabel");
		advCard1 = new JButton("newButton");
		adCard2 = new JButton("newButton");
		adCard3 = new JButton("newButton");
		adCard4 = new JButton("newButton");
		advVict1 = new JCheckBox("newCheckBox");
		advVict2 = new JCheckBox("newCheckBox");
		advVict3 = new JCheckBox("newCheckBox");
		advDp = new JLabel("newLabel");
		digimonCard = new JButton("newButton");
		supportCard = new JButton("newButton");
		advSupportCard = new JButton("newButton");
		advDigimonCard = new JButton("newButton");
		atk1 = new JLabel("newLabel");
		atk2 = new JLabel("newLabel");
		atk3 = new JLabel("newLabel");
		advAtk2 = new JLabel("newLabel");
		advAtk3 = new JLabel("newLabel");
		advAtk1 = new JLabel("newLabel");
		dp = new JLabel("newLabel");
		vict1 = new JCheckBox("newCheckBox");
		vict2 = new JCheckBox("newCheckBox");
		vict3 = new JCheckBox("newCheckBox");
		card1 = new JButton("newButton");
		card2 = new JButton("newButton");
		card3 = new JButton("newButton");
		card4 = new JButton("newButton");
		deckSize = new JLabel("newLabel");
		cemiterySize = new JLabel("newLabel");
		name = new JLabel("newLabel");
		
		// adjust size and set layout
		setPreferredSize(new Dimension(922, 589));
		setLayout(null);

		// add components
		add(updateCard);
		add(discardHand);
		add(jump);
		add(phaseName);
		add(jcomp5);
		add(opponentName);
		add(advCemiterySize);
		add(advDeckSIze);
		add(advCard1);
		add(adCard2);
		add(adCard3);
		add(adCard4);
		add(advVict1);
		add(advVict2);
		add(advVict3);
		add(advDp);
		add(digimonCard);
		add(supportCard);
		add(advSupportCard);
		add(advDigimonCard);
		add(atk1);
		add(atk2);
		add(atk3);
		add(advAtk2);
		add(advAtk3);
		add(advAtk1);
		add(dp);
		add(vict1);
		add(vict2);
		add(vict3);
		add(card1);
		add(card2);
		add(card3);
		add(card4);
		add(deckSize);
		add(cemiterySize);
		add(name);

		// set component bounds (only needed by Absolute Positioning)
		updateCard.setBounds(155, 565, 135, 20);
		discardHand.setBounds(390, 565, 150, 20);
		jump.setBounds(680, 565, 140, 20);
		phaseName.setBounds(390, 20, 285, 25);
		jcomp5.setBounds(0, 0, 1350, 25);
		opponentName.setBounds(725, 45, 155, 25);
		advCemiterySize.setBounds(15, 80, 65, 35);
		advDeckSIze.setBounds(15, 120, 70, 30);
		advCard1.setBounds(130, 70, 100, 125);
		adCard2.setBounds(275, 70, 100, 125);
		adCard3.setBounds(425, 70, 100, 125);
		adCard4.setBounds(590, 65, 100, 125);
		advVict1.setBounds(780, 90, 100, 25);
		advVict2.setBounds(780, 125, 100, 25);
		advVict3.setBounds(780, 155, 100, 25);
		advDp.setBounds(780, 190, 95, 35);
		digimonCard.setBounds(130, 250, 100, 125);
		supportCard.setBounds(275, 250, 100, 125);
		advSupportCard.setBounds(425, 250, 100, 125);
		advDigimonCard.setBounds(590, 250, 100, 125);
		atk1.setBounds(30, 250, 100, 25);
		atk2.setBounds(30, 275, 100, 25);
		atk3.setBounds(30, 295, 100, 25);
		advAtk2.setBounds(695, 325, 100, 25);
		advAtk3.setBounds(695, 345, 100, 25);
		advAtk1.setBounds(695, 305, 100, 25);
		dp.setBounds(15, 405, 100, 25);
		vict1.setBounds(10, 435, 100, 25);
		vict2.setBounds(10, 465, 100, 25);
		vict3.setBounds(10, 500, 100, 25);
		card1.setBounds(130, 405, 100, 125);
		card2.setBounds(275, 405, 100, 125);
		card3.setBounds(425, 410, 100, 125);
		card4.setBounds(590, 410, 100, 125);
		deckSize.setBounds(720, 470, 100, 25);
		cemiterySize.setBounds(720, 505, 100, 25);
		name.setBounds(40, 535, 100, 25);
		card1.addActionListener(l);
		Image iconLogo = new ImageIcon(System.getProperty("user.dir") + "/pictures/fire/agumon.jpg").getImage()
				.getScaledInstance(card1.getWidth() +20, card1.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(iconLogo);
		card1.setName("AGUMON");
		card1.setIcon(imageIcon);
	}
	
	public void updateInterface(PlayerMovePOJO remotePlayer, PlayerMovePOJO localPlayer) {
		if(remotePlayer != null) {
			
		}
	}
	
	public void updateRemotePlayer(PlayerMovePOJO remotePlayer) {
		opponentName.setText(remotePlayer.getName());
		advCemiterySize.setText(String.valueOf(remotePlayer.getDeadCards()));
		advDeckSIze.setText(String.valueOf(remotePlayer.getDeckSize()));
		ArrayList<CardPOJO> hand = (ArrayList<CardPOJO>) remotePlayer.getHand();
		for(int i = 0; i < hand.size(); i++) {
			CardPOJO pojo = hand.get(i);
			if(pojo == null) {
			}
		}
	}
	

	public String getNamePlayer() {
		return JOptionPane.showInputDialog("Digite seu nome");
	}

	public String getNameServer() {
		return JOptionPane.showInputDialog("Digite o nome do servidor", "ufsc.algumacoisa-alterar-depois");
	}

	public void sendMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void informError(String error) {
		JOptionPane.showMessageDialog(null, error, "Erro", JOptionPane.ERROR_MESSAGE);
	}

}