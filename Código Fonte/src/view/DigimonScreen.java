package view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

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

	private String pathDefault = "/pictures/backCard.jpg";

	private ActionListener l = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == card1) {
				System.out.println(card1.getName());
				String name = card1.getName();
				player.viewAttributes(name);
			} else if(e.getSource() == card2) {
				System.out.println(card2.getName());
				String name = card2.getName();
				player.viewAttributes(name);
			} else if(e.getSource() == card3) {
				System.out.println(card3.getName());
				String name = card3.getName();
				player.viewAttributes(name);
			} else if(e.getSource() == card4) {
				System.out.println(card4.getName());
				String name = card4.getName();
				player.viewAttributes(name);
			} else if(e.getSource() == advCard1) {
				System.out.println(advCard1.getName());
				String name = advCard1.getName();
				player.viewAttributes(name);
			} else if(e.getSource() == adCard2) {
				System.out.println(adCard2.getName());
				String name = adCard2.getName();
				player.viewAttributes(name);
			} else if(e.getSource() == adCard3) {
				System.out.println(adCard3.getName());
				String name = adCard3.getName();
				player.viewAttributes(name);
			} else if(e.getSource() == adCard4) {
				System.out.println(adCard4.getName());
				String name = adCard4.getName();
				player.viewAttributes(name);
			} else if(e.getSource() == digimonCard) {
				player.viewAttributesDigimonCard(false);
			} else if(e.getSource() == supportCard) {
				player.viewAttributesOptionCard(false);
			} else if(e.getSource() == advDigimonCard) {
				player.viewAttributesDigimonCard(true);
			} else if(e.getSource() == advSupportCard) {
				player.viewAttributesOptionCard(true);
			} else if(e.getSource() == jump) {
				player.jumpPhase();
			} else if(e.getSource() == discardHand) {
				player.discardHand();
			} else if(e.getSource() == updateCard) {
				player.viewOptionsUpdate();
			}
			
		}
	};
	public DigimonScreen() {
		this.player = null;
		
	}

	public DigimonScreen(ActorPlayer actorPlayer) {
		this.player = actorPlayer;
		config();
	}
	
	private void config() {
		// construct preComponents
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenu menuMenu = new JMenu("Menu");
		JMenuItem conectarItem = new JMenuItem("Conectar");
		menuMenu.add(conectarItem);
		JMenuItem desconectarItem = new JMenuItem("Desconectar");
		menuMenu.add(desconectarItem);
		JMenuItem iniciarItem = new JMenuItem("Iniciar Partida");
		menuMenu.add(iniciarItem);
		JMenuItem sairItem = new JMenuItem("Sair");
		menuMenu.add(sairItem);
		
		// construct components
		updateCard = new JButton("Evoluir Carta");
		discardHand = new JButton("Descartar Mão");
		jump = new JButton("Próxima Fase");
		phaseName = new JLabel("Aguardando");
		jcomp5 = new JMenuBar();
		jcomp5.add(menuMenu);
		opponentName = new JLabel("newLabel");
		advCemiterySize = new JLabel("newLabel");
		advDeckSIze = new JLabel("newLabel");
		advCard1 = new JButton("newButton");
		adCard2 = new JButton("newButton");
		adCard3 = new JButton("newButton");
		adCard4 = new JButton("newButton");
		advVict1 = new JCheckBox("Vitória 1");
		advVict2 = new JCheckBox("Vitória 2");
		advVict3 = new JCheckBox("Vitória 3");
		vict1 = new JCheckBox("Vitória 1");
		vict2 = new JCheckBox("Vitória 2");
		vict3 = new JCheckBox("Vitória 3");
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
		advVict1.setEnabled(false);
		advVict2.setEnabled(false);
		advVict3.setEnabled(false);
		
		vict1.setEnabled(false);
		vict2.setEnabled(false);
		vict3.setEnabled(false);		
		
		Image iconLogo = new ImageIcon(System.getProperty("user.dir") + pathDefault).getImage()
				.getScaledInstance(card1.getWidth() +20, card1.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(iconLogo);
		card1.setIcon(imageIcon);
		card2.setIcon(imageIcon);
		card3.setIcon(imageIcon);
		card4.setIcon(imageIcon);
		advCard1.setIcon(imageIcon);
		adCard2.setIcon(imageIcon);
		adCard3.setIcon(imageIcon);
		adCard4.setIcon(imageIcon);
		advDigimonCard.setIcon(imageIcon);
		advSupportCard.setIcon(imageIcon);
		digimonCard.setIcon(imageIcon);
		supportCard.setIcon(imageIcon);
		card1.addActionListener(l);
		card2.addActionListener(l);
		card3.addActionListener(l);
		card4.addActionListener(l);
		digimonCard.addActionListener(l);
		supportCard.addActionListener(l);
		advCard1.addActionListener(l);
		adCard2.addActionListener(l);
		adCard3.addActionListener(l);
		adCard4.addActionListener(l);
		advDigimonCard.addActionListener(l);
		advSupportCard.addActionListener(l);
		jump.addActionListener(l);
		updateCard.addActionListener(l);
		discardHand.addActionListener(l);
		
		conectarItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				player.connect();
			}
		});
		desconectarItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				player.disconnect();
			}
		});
		sairItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				player.quit();
			}
		});
		iniciarItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				player.start();
			}
		});
	}
	
	public void updateInterface(PlayerMovePOJO remotePlayer, PlayerMovePOJO localPlayer) {
		if(remotePlayer != null) {
			updateRemotePlayer(remotePlayer);
		}
		
		if(localPlayer != null) {
			updateLocalPlayer(localPlayer);
		}
	}
	
	private void updateLocalPlayer(PlayerMovePOJO localPlayer) {
		name.setText(localPlayer.getName());
		cemiterySize.setText(String.valueOf(localPlayer.getDeadCards()));
		deckSize.setText(String.valueOf(localPlayer.getDeckSize()));
		ArrayList<CardPOJO> hand = (ArrayList<CardPOJO>) localPlayer.getHand();
		ArrayList<JButton> buttonsCardsPlayer = (ArrayList<JButton>) buttonsCardsPlayer();
		for(int i = 0; i < hand.size(); i++) {
			CardPOJO pojo = hand.get(i);
			JButton jButton = buttonsCardsPlayer.get(i);
			if(pojo != null) {
				jButton.setName(pojo.getName());
				Image iconLogo = new ImageIcon(System.getProperty("user.dir") + pojo.getPath()).getImage()
						.getScaledInstance(card1.getWidth() +20, card1.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(iconLogo);
				jButton.setIcon(imageIcon);
			} else {
				Image iconLogo = new ImageIcon(System.getProperty("user.dir") + pathDefault).getImage()
						.getScaledInstance(card1.getWidth() +20, card1.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(iconLogo);
				jButton.setIcon(imageIcon);
			}
		}

	}

	public void updateRemotePlayer(PlayerMovePOJO remotePlayer) {
		opponentName.setText(remotePlayer.getName());
		advCemiterySize.setText(String.valueOf(remotePlayer.getDeadCards()));
		advDeckSIze.setText(String.valueOf(remotePlayer.getDeckSize()));
		ArrayList<CardPOJO> hand = (ArrayList<CardPOJO>) remotePlayer.getHand();
		ArrayList<JButton> buttonsCardsAdv = (ArrayList<JButton>) buttonsCardsAdv();
		for(int i = 0; i < hand.size(); i++) {
			CardPOJO pojo = hand.get(i);
			JButton jButton = buttonsCardsAdv.get(i);
			if(pojo != null) {
				jButton.setName(pojo.getName());
				Image iconLogo = new ImageIcon(System.getProperty("user.dir") + pojo.getPath()).getImage()
						.getScaledInstance(card1.getWidth() +20, card1.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(iconLogo);
				jButton.setIcon(imageIcon);
			} else {
				Image iconLogo = new ImageIcon(System.getProperty("user.dir") + pathDefault).getImage()
						.getScaledInstance(card1.getWidth() +20, card1.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(iconLogo);
				jButton.setIcon(imageIcon);
			}
		}
	}
	
	private Collection<JButton> buttonsCardsAdv() {
		Collection<JButton> buttons = new ArrayList<>();
		buttons.add(advCard1);
		buttons.add(adCard2);
		buttons.add(adCard3);
		buttons.add(adCard4);
		return buttons;
	}
	
	private Collection<JButton> buttonsCardsPlayer() {
		Collection<JButton> buttons = new ArrayList<>();
		buttons.add(card1);
		buttons.add(card2);
		buttons.add(card3);
		buttons.add(card4);
		return buttons;
	}
	
	public void notifyPhase(String phase) {
		phaseName.setText(phase);
		repaint();
	}
	
	public void enableButtonsDrawPhase() {
		jump.setEnabled(true);
		discardHand.setEnabled(true);
	}
	
	public void updatePhaseName(String phase) {
		phaseName.setText(phase);
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

	public void enableButtonsDigivolvePhase(boolean b) {
		if(b) {
			updateCard.setEnabled(true);
		} else {
			updateCard.setEnabled(false);
		}
		repaint();
	}

	public void dissableAllButtons() {
		Collection<JButton> buttonsCardsAdv = buttonsCardsAdv();
		Collection<JButton> buttonsCardsPlayer = buttonsCardsPlayer();
		for (JButton jButton : buttonsCardsAdv) {
			jButton.setEnabled(false);
		}
		for (JButton jButton : buttonsCardsPlayer) {
			jButton.setEnabled(false);
		}
		jump.setEnabled(false);
		updateCard.setEnabled(false);
		discardHand.setEnabled(false);
	}

}