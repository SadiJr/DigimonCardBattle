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

public class DigimonScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private final ActorPlayer player;
	private JButton card4;
	private JButton oppDigimonCard;
	private JButton oppCard4;
	private JButton oppCard3;
	private JButton oppCard2;
	private JButton oppCard1;
	private JMenuBar jcomp7;
	private JLabel phaseName;
	private JLabel opponentName;
	private JCheckBox oppVict1;
	private JCheckBox oppVict2;
	private JCheckBox oppVict3;
	private JLabel oppDP;
	private JLabel oppDPIcon;
	private JLabel oppAtk1;
	private JLabel oppAtk2;
	private JLabel oppAtk3;
	private JLabel oppDeadCards;
	private JLabel oppCemiteryIcon;
	private JLabel oppDeckSize;
	private JLabel oppDeckSizeIcon;
	private JButton digimonCard;
	private JButton supportCard;
	private JButton oppSupportCard;
	private JButton card1;
	private JButton card2;
	private JButton card3;
	private JLabel deckSize;
	private JLabel deckSizeIcon;
	private JLabel deadCards;
	private JLabel deadCardsIcon;
	private JLabel atk1;
	private JLabel atk2;
	private JLabel atk3;
	private JCheckBox vict1;
	private JCheckBox vict2;
	private JCheckBox vict3;
	private JLabel dp;
	private JLabel dpIcon;
	private JButton evolute;
	private JButton discardHand;
	private JButton jump;
	private JLabel playerName;

	private String pathDefault = "pictures/backCard.jpg";
	private String pathCemitery = "pictures/cemitery.png";
	private String pathDp = "pictures/power.png";

	private ActionListener l = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == card1) {
				System.out.println(card1.getName());
				String name = card1.getName();
				player.viewAttributes(name, false);
			} else if (e.getSource() == card2) {
				System.out.println(card2.getName());
				String name = card2.getName();
				player.viewAttributes(name, false);
			} else if (e.getSource() == card3) {
				System.out.println(card3.getName());
				String name = card3.getName();
				player.viewAttributes(name, false);
			} else if (e.getSource() == card4) {
				System.out.println(card4.getName());
				String name = card4.getName();
				player.viewAttributes(name, false);
			} else if (e.getSource() == oppCard1) {
				System.out.println(oppCard1.getName());
				String name = oppCard1.getName();
				player.viewAttributes(name, true);
			} else if (e.getSource() == oppCard2) {
				System.out.println(oppCard2.getName());
				String name = oppCard2.getName();
				player.viewAttributes(name, true);
			} else if (e.getSource() == oppCard3) {
				System.out.println(oppCard3.getName());
				String name = oppCard3.getName();
				player.viewAttributes(name, true);
			} else if (e.getSource() == oppCard4) {
				System.out.println(oppCard4.getName());
				String name = oppCard4.getName();
				player.viewAttributes(name, true);
			} else if (e.getSource() == digimonCard) {
				player.viewAttributesDigimonCardInBattleField(digimonCard.getName());
			} else if (e.getSource() == supportCard) {
				player.viewAttributesSupportCardInBattleField(supportCard.getName());
			} else if (e.getSource() == oppDigimonCard) {
				player.viewAttributesDigimonCard(true);
			} else if (e.getSource() == oppSupportCard) {
				player.viewAttributesOptionCard(true);
			} else if (e.getSource() == jump) {
				player.jumpPhase();
			} else if (e.getSource() == discardHand) {
				player.discardHand();
			} else if (e.getSource() == evolute) {
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
		setTitle("Digimon Card Battle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenu menuMenu = new JMenu("Menu");
		JMenuItem conectarItem = new JMenuItem("Conectar");
		menuMenu.add(conectarItem);
		JMenuItem desconectarItem = new JMenuItem("Desconectar");
		menuMenu.add(desconectarItem);
		JMenuItem iniciar_partidaItem = new JMenuItem("Iniciar Partida");
		menuMenu.add(iniciar_partidaItem);
		JMenuItem sairItem = new JMenuItem("Sair");
		menuMenu.add(sairItem);

		// construct components
		card4 = new JButton("Card4");
		oppDigimonCard = new JButton("Digimon");
		oppCard4 = new JButton("Card 4");
		oppCard3 = new JButton("card3");
		oppCard2 = new JButton("card2");
		oppCard1 = new JButton("card1");
		jcomp7 = new JMenuBar();
		jcomp7.add(menuMenu);
		phaseName = new JLabel("Atualmente Aguardando");
		opponentName = new JLabel("Seu Inimigo");
		oppVict1 = new JCheckBox("Vitória 1");
		oppVict2 = new JCheckBox("Vitória 2");
		oppVict3 = new JCheckBox("Vitória 3");
		oppDP = new JLabel("DP");
		oppDPIcon = new JLabel("Poderzinho");
		oppAtk1 = new JLabel("Atk1");
		oppAtk2 = new JLabel("Atk2");
		oppAtk3 = new JLabel("Atk3");
		oppDeadCards = new JLabel("Cartas mortas");
		oppCemiteryIcon = new JLabel("Caveirinha");
		oppDeckSize = new JLabel("Deck size");
		oppDeckSizeIcon = new JLabel("Deck Icon");
		digimonCard = new JButton("Digimon");
		supportCard = new JButton("Suporte");
		oppSupportCard = new JButton("Suporte");
		card1 = new JButton("Card1");
		card2 = new JButton("Card2");
		card3 = new JButton("Card3");
		deckSize = new JLabel("Deck size");
		deckSizeIcon = new JLabel("Deck size icon");
		deadCards = new JLabel("Cartas Mortas");
		deadCardsIcon = new JLabel("Caveirinha");
		atk1 = new JLabel("Atk1");
		atk2 = new JLabel("Atk2");
		atk3 = new JLabel("Atk3");
		vict1 = new JCheckBox("Vitória 1");
		vict2 = new JCheckBox("Vitória 2");
		vict3 = new JCheckBox("Vitória 3");
		dp = new JLabel("DP");
		dpIcon = new JLabel("Poderzinho");
		evolute = new JButton("Evoluir Carta");
		discardHand = new JButton("Descartar Mão");
		jump = new JButton("Pular Fase");
		playerName = new JLabel("Você");

		// set components properties
		oppVict1.setEnabled(false);
		oppVict2.setEnabled(false);
		oppVict3.setEnabled(false);
		vict1.setEnabled(false);
		vict2.setEnabled(false);
		vict3.setEnabled(false);

		// adjust size and set layout
		setPreferredSize(new Dimension(1121, 583));
		setLayout(null);

		// add components
		add(card4); add(oppDigimonCard);add(oppCard4);add(oppCard3);add(oppCard2);
		add(oppCard1);add(jcomp7);add(phaseName);add(opponentName);add(oppVict1);
		add(oppVict2);add(oppVict3);add(oppDP);add(oppDPIcon);add(oppAtk1);
		add(oppAtk2);add(oppAtk3);add(oppDeadCards);add(oppCemiteryIcon);
		add(oppDeckSize);add(oppDeckSizeIcon);add(digimonCard);add(supportCard);
		add(oppSupportCard);add(card1);add(card2);add(card3);add(deckSize);
		add(deckSizeIcon);add(deadCards);add(deadCardsIcon);add(atk1);
		add(atk2);add(atk3);add(vict1);add(vict2);add(vict3);add(dp);add(dpIcon);
		add(evolute);add(discardHand);add(jump);add(playerName);

		// set component bounds (only needed by Absolute Positioning)
		card4.setBounds(895, 455, 105, 150);
		oppDigimonCard.setBounds(895, 290, 105, 150);
		oppCard4.setBounds(895, 125, 105, 150);
		oppCard3.setBounds(725, 125, 105, 150);
		oppCard2.setBounds(555, 125, 105, 150);
		oppCard1.setBounds(385, 125, 105, 150);
		jcomp7.setBounds(0, 0, 1365, 25);
		phaseName.setBounds(600, 25, 260, 35);
		opponentName.setBounds(900, 95, 100, 25);
		oppVict1.setBounds(1070, 125, 100, 25);
		oppVict2.setBounds(1070, 155, 100, 25);
		oppVict3.setBounds(1070, 185, 100, 25);
		oppDP.setBounds(1225, 120, 50, 25);
		oppDPIcon.setBounds(1190, 145, 100, 90);
		oppAtk1.setBounds(1010, 335, 100, 25);
		oppAtk2.setBounds(1010, 355, 100, 25);
		oppAtk3.setBounds(1010, 375, 100, 25);
		oppDeadCards.setBounds(55, 125, 50, 25);
		oppCemiteryIcon.setBounds(30, 145, 100, 125);
		oppDeckSize.setBounds(195, 125, 50, 25);
		oppDeckSizeIcon.setBounds(180, 145, 100, 125);
		digimonCard.setBounds(385, 290, 105, 150);
		supportCard.setBounds(555, 290, 105, 150);
		oppSupportCard.setBounds(725, 290, 105, 150);
		card1.setBounds(385, 455, 105, 150);
		card2.setBounds(555, 455, 105, 150);
		card3.setBounds(725, 455, 105, 150);
		deckSize.setBounds(1055, 460, 50, 25);
		deckSizeIcon.setBounds(1030, 480, 100, 126);
		deadCards.setBounds(1185, 460, 50, 25);
		deadCardsIcon.setBounds(1160, 480, 100, 125);
		atk1.setBounds(320, 335, 65, 25);
		atk2.setBounds(320, 355, 65, 25);
		atk3.setBounds(320, 375, 65, 25);
		vict1.setBounds(195, 495, 100, 25);
		vict2.setBounds(195, 525, 100, 25);
		vict3.setBounds(195, 555, 100, 25);
		dp.setBounds(75, 455, 45, 25);
		dpIcon.setBounds(35, 475, 100, 90);
		evolute.setBounds(335, 660, 135, 25);
		discardHand.setBounds(610, 660, 135, 25);
		jump.setBounds(910, 660, 135, 25);
		playerName.setBounds(385, 610, 100, 25);
		Image iconLogo = new ImageIcon(pathDefault).getImage()
				.getScaledInstance(card1.getWidth() + 20, card1.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(iconLogo);
		card1.setIcon(imageIcon);
		card2.setIcon(imageIcon);
		card3.setIcon(imageIcon);
		card4.setIcon(imageIcon);
		oppCard1.setIcon(imageIcon);
		oppCard2.setIcon(imageIcon);
		oppCard3.setIcon(imageIcon);
		oppCard4.setIcon(imageIcon);
		oppDigimonCard.setIcon(imageIcon);
		oppSupportCard.setIcon(imageIcon);
		digimonCard.setIcon(imageIcon);
		supportCard.setIcon(imageIcon);
		iconLogo.getScaledInstance(deckSizeIcon.getWidth(), deckSizeIcon.getHeight(), Image.SCALE_SMOOTH);
		deckSizeIcon.setIcon(new ImageIcon(iconLogo));
		oppDeckSizeIcon.setIcon(new ImageIcon(iconLogo));
		Image iconCemitery = new ImageIcon(pathCemitery).getImage()
				.getScaledInstance(oppCemiteryIcon.getWidth(), oppCemiteryIcon.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon cemitery = new ImageIcon(iconCemitery);
		deadCardsIcon.setIcon(cemitery);
		oppCemiteryIcon.setIcon(cemitery);
		
		Image iconDp = new ImageIcon(pathDp).getImage()
				.getScaledInstance(dpIcon.getWidth(), dpIcon.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon dpIc = new ImageIcon(iconDp);
		dpIcon.setIcon(dpIc);
		oppDPIcon.setIcon(dpIc);
		
		card1.addActionListener(l);
		card2.addActionListener(l);
		card3.addActionListener(l);
		card4.addActionListener(l);
		digimonCard.addActionListener(l);
		supportCard.addActionListener(l);
		oppCard1.addActionListener(l);
		oppCard2.addActionListener(l);
		oppCard3.addActionListener(l);
		oppCard4.addActionListener(l);
		oppDigimonCard.addActionListener(l);
		oppSupportCard.addActionListener(l);
		jump.addActionListener(l);
		evolute.addActionListener(l);
		discardHand.addActionListener(l);

		setPreferredSize(new Dimension(1368, 768));
		setLayout(null);
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
		iniciar_partidaItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player.start();
			}
		});
	}

	public void updateInterface(PlayerMovePOJO remotePlayer, PlayerMovePOJO localPlayer) {
		if (remotePlayer != null) {
			updateRemotePlayer(remotePlayer);
		} else {
			Image iconLogo = new ImageIcon(pathDefault).getImage()
					.getScaledInstance(card1.getWidth() + 20, card1.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(iconLogo);
			
			oppCard1.setIcon(imageIcon);
			oppCard2.setIcon(imageIcon);
			oppCard3.setIcon(imageIcon);
			oppCard4.setIcon(imageIcon);
			oppDigimonCard.setIcon(imageIcon);
			oppSupportCard.setIcon(imageIcon);
			oppDP.setText("0");
			oppDeckSize.setText("0");
			opponentName.setText("Seu inimigo");
			oppDeadCards.setText("0");
			oppDeckSize.setText("0");
			oppVict1.setSelected(false);
			oppVict2.setSelected(false);
			oppVict3.setSelected(false);
		}

		if (localPlayer != null) {
			updateLocalPlayer(localPlayer);
		}
	}

	private void updateLocalPlayer(PlayerMovePOJO localPlayer) {
		playerName.setText(localPlayer.getName());
		deadCards.setText(String.valueOf(localPlayer.getDeadCards()));
		deckSize.setText(String.valueOf(localPlayer.getDeckSize()));
		dp.setText(String.valueOf(localPlayer.getDp()));
		ArrayList<CardPOJO> hand = (ArrayList<CardPOJO>) localPlayer.getHand();
		ArrayList<JButton> buttonsCardsPlayer = (ArrayList<JButton>) buttonsCardsPlayer();
		for (int i = 0; i < hand.size(); i++) {
			System.out.println(hand.size());
			CardPOJO pojo = hand.get(i);
			JButton jButton = buttonsCardsPlayer.get(i);
			if (pojo != null) {
				jButton.setName(pojo.getName());
				Image iconLogo = new ImageIcon(pojo.getPath()).getImage()
						.getScaledInstance(card1.getWidth() + 20, card1.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(iconLogo);
				jButton.setIcon(imageIcon);
			} else {
				Image iconLogo = new ImageIcon(pathDefault).getImage()
						.getScaledInstance(card1.getWidth() + 20, card1.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(iconLogo);
				jButton.setIcon(imageIcon);
			}
		}
		if(localPlayer.getDigimonCard() != null) {
			Image iconLogo = new ImageIcon(localPlayer.getDigimonCard().getPath())
					.getImage().getScaledInstance(card1.getWidth() + 20, card1.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(iconLogo);
			digimonCard.setIcon(imageIcon);
			digimonCard.setName(localPlayer.getDigimonCard().getName());
			atk1.setText(String.valueOf(localPlayer.getDigimonCard().getAttack1()));
			atk2.setText(String.valueOf(localPlayer.getDigimonCard().getAttack2()));
			atk3.setText(String.valueOf(localPlayer.getDigimonCard().getAttack3()));
		} else {
			Image iconLogo = new ImageIcon(pathDefault)
					.getImage().getScaledInstance(card1.getWidth() + 20, card1.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(iconLogo);
			digimonCard.setIcon(imageIcon);
			atk1.setText("Atk1");
			atk2.setText("Atk2");
			atk3.setText("Atk3");
		}
		if(localPlayer.getSupportCard() != null) {
			Image iconLogo = new ImageIcon(localPlayer.getSupportCard().getPath())
					.getImage().getScaledInstance(card1.getWidth() + 20, card1.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(iconLogo);
			supportCard.setIcon(imageIcon);
			supportCard.setName(localPlayer.getSupportCard().getName());
		} else {
			Image iconLogo = new ImageIcon(pathDefault)
					.getImage().getScaledInstance(card1.getWidth() + 20, card1.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(iconLogo);
			supportCard.setIcon(imageIcon);
		}
		
		int victories = localPlayer.getVictories();
		switch (victories) {
		case 1:
			vict1.setSelected(true);
			break;
		case 2 :
			vict1.setSelected(true);
			vict2.setSelected(true);
			break;
		case 3:
			vict1.setSelected(true);
			vict2.setSelected(true);
			vict3.setSelected(true);
			break;
		default:
			vict1.setSelected(false);
			vict2.setSelected(false);
			vict3.setSelected(false);
			break;
		}
		digimonCard.repaint();
		supportCard.repaint();
		repaint();
	}

	public void updateRemotePlayer(PlayerMovePOJO remotePlayer) {
		opponentName.setText(remotePlayer.getName());
		oppDeadCards.setText(String.valueOf(remotePlayer.getDeadCards()));
		oppDeckSize.setText(String.valueOf(remotePlayer.getDeckSize()));
		oppDP.setText(String.valueOf(remotePlayer.getDp()));
		ArrayList<CardPOJO> hand = (ArrayList<CardPOJO>) remotePlayer.getHand();
		ArrayList<JButton> buttonsCardsAdv = (ArrayList<JButton>) buttonsCardsAdv();
		for (int i = 0; i < hand.size(); i++) {
			CardPOJO pojo = hand.get(i);
			JButton jButton = buttonsCardsAdv.get(i);
			if (pojo != null) {
				jButton.setName(pojo.getName());
				Image iconLogo = new ImageIcon(pojo.getPath()).getImage()
						.getScaledInstance(card1.getWidth() + 20, card1.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(iconLogo);
				jButton.setIcon(imageIcon);
			} else {
				jButton.setName(null);
				Image iconLogo = new ImageIcon(pathDefault).getImage()
						.getScaledInstance(card1.getWidth() + 20, card1.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(iconLogo);
				jButton.setIcon(imageIcon);
			}
			repaint();
		}
		if(remotePlayer.getDigimonCard() != null) {
			oppDigimonCard.setName(remotePlayer.getDigimonCard().getName());
			Image iconLogo = new ImageIcon(remotePlayer.getDigimonCard().getPath()).getImage()
					.getScaledInstance(card1.getWidth() + 20, card1.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(iconLogo);
			oppDigimonCard.setIcon(imageIcon);
			oppAtk1.setText(String.valueOf(remotePlayer.getDigimonCard().getAttack1()));
			oppAtk2.setText(String.valueOf(remotePlayer.getDigimonCard().getAttack2()));
			oppAtk3.setText(String.valueOf(remotePlayer.getDigimonCard().getAttack3()));
		} else {
			oppDigimonCard.setName(null);
			Image iconLogo = new ImageIcon(pathDefault).getImage()
					.getScaledInstance(card1.getWidth() + 20, card1.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(iconLogo);
			oppDigimonCard.setIcon(imageIcon);
			oppAtk1.setText("Atk1");
			oppAtk2.setText("Atk2");
			oppAtk3.setText("Atk3");
		}
		repaint();
		if(remotePlayer.getSupportCard() != null) {
			oppSupportCard.setName(remotePlayer.getSupportCard().getName());
			Image iconLogo = new ImageIcon(remotePlayer.getSupportCard().getPath()).getImage()
					.getScaledInstance(card1.getWidth() + 20, card1.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(iconLogo);
			oppSupportCard.setIcon(imageIcon);
		} else {
			oppDigimonCard.setName(null);
			Image iconLogo = new ImageIcon(pathDefault).getImage()
					.getScaledInstance(card1.getWidth() + 20, card1.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(iconLogo);
			oppDigimonCard.setIcon(imageIcon);
		}
		int victories = remotePlayer.getVictories();
		switch (victories) {
		case 1:
			oppVict1.setSelected(true);
			break;
		case 2 :
			oppVict1.setSelected(true);
			oppVict2.setSelected(true);
			break;
		case 3:
			oppVict1.setSelected(true);
			oppVict2.setSelected(true);
			oppVict3.setSelected(true);
			break;
		default:
			oppVict1.setSelected(false);
			oppVict2.setSelected(false);
			oppVict3.setSelected(false);
			break;
		}
		oppDigimonCard.repaint();
		oppSupportCard.repaint();
		repaint();
	}

	private Collection<JButton> buttonsCardsAdv() {
		Collection<JButton> buttons = new ArrayList<>();
		buttons.add(oppCard1);
		buttons.add(oppCard2);
		buttons.add(oppCard3);
		buttons.add(oppCard4);
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
		pack();
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
		return JOptionPane.showInputDialog("Digite o nome do servidor", "netgames.labsoft.ufsc.br");
	}

	public void sendMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void informError(String error) {
		JOptionPane.showMessageDialog(null, error, "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public void enableButtonsDigivolvePhase(boolean b) {
		if (b) {
			evolute.setEnabled(true);
		} else {
			evolute.setEnabled(false);
		}
		repaint();
	}

	public void dissableAllButtons() {
		jump.setEnabled(false);
		evolute.setEnabled(false);
		discardHand.setEnabled(false);
		pack();
	}

	public void dissableButtonsDrawPhase() {
		discardHand.setEnabled(false);
	}

}