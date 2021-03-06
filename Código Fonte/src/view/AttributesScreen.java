package view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import actor.ActorPlayer;
import model.CardPOJO;

public class AttributesScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private ActorPlayer player;
	private JLabel cardImage;
	private JLabel jcomp2;
	private JLabel jcomp3;
	private JLabel jcomp4;
	private JLabel jcomp5;
	private JLabel jcomp6;
	private JLabel jcomp7;
	private JLabel jcomp8;
	private JLabel jcomp9;
	private JLabel jcomp10;
	private JLabel jcomp11;
	private JLabel jcomp12;
	private JLabel name;
	private JLabel effectName;
	private JLabel descriptionEffect;
	private JLabel hp;
	private JLabel atk1;
	private JLabel atk2;
	private JLabel atk3;
	private JLabel dp;
	private JLabel p;
	private JLabel specialty;
	private JLabel level;
	private JButton downDigimon;
	private JButton downSupport;
	private JButton sacrificeCard;
	private JButton attack1;
	private JButton attack2;
	private JButton attack3;
	
	private ActionListener l = new ActionListener() {
		public void actionPerformed(final ActionEvent e) {
			if (e.getSource() == downDigimon) {
				enableButtonDownDigimon(false, false);
				player.downDigimonCard(name.getText());
			} else if (e.getSource() == downSupport) {
				enableButtonDownDigimon(false, true);
				player.downSupportCard(name.getText());
			} else if (e.getSource() == sacrificeCard) {
				dissableAllButtons();
				player.sacrificeCard(name.getText());
			} else if(e.getSource() == attack1) {
				dissableAllButtons();
				player.attackChoice(1);
			} else if(e.getSource() == attack2) {
				dissableAllButtons();
				player.attackChoice(2);
			} else if(e.getSource() == attack3) {
				dissableAllButtons();
				player.attackChoice(3);
			}
			pack();
			repaint();
		}
	};

	public AttributesScreen(ActorPlayer actorPlayer) {
		this.player = actorPlayer;
		config();
	}
	
	public AttributesScreen() {
		config();
	}

	public void config() {
		setTitle("Detalhes");
		cardImage = new JLabel("");
		jcomp2 = new JLabel("Nome");
		jcomp3 = new JLabel("Efeito");
		jcomp4 = new JLabel("Descrição do Efeito");
		jcomp5 = new JLabel("HP");
		jcomp6 = new JLabel("Ataque 1");
		jcomp7 = new JLabel("Ataque 2");
		jcomp8 = new JLabel("Ataque 3");
		jcomp9 = new JLabel("DP");
		jcomp10 = new JLabel("P");
		jcomp11 = new JLabel("Especialidade");
		jcomp12 = new JLabel("Nível");
		name = new JLabel("Nome");
		effectName = new JLabel("efeito");
		descriptionEffect = new JLabel("descricao");
		hp = new JLabel("hp");
		atk1 = new JLabel("atk1");
		atk2 = new JLabel("atk2");
		atk3 = new JLabel("atk3");
		dp = new JLabel("dp");
		p = new JLabel("p");
		specialty = new JLabel("especialidade");
		level = new JLabel("level");
		downDigimon = new JButton("Baixar Carta");
		downSupport = new JButton("Baixar Carta");
		sacrificeCard = new JButton("Sacrificar Carta");
		attack1 = new JButton("Ataque 1");
		attack2 = new JButton("Ataque 2");
		attack3 = new JButton("Ataque 3");
		
		downDigimon.setVisible(false);
		downDigimon.setEnabled(false);
		downSupport.setEnabled(false);
		downSupport.setVisible(false);
		sacrificeCard.setVisible(false);
		sacrificeCard.setEnabled(false);
		attack1.setVisible(false);
		attack1.setEnabled(false);
		attack2.setVisible(false);
		attack2.setEnabled(false);
		attack3.setVisible(false);
		attack3.setEnabled(false);

		setPreferredSize(new Dimension(830, 570));
		setLayout(null);

		add(cardImage);add(jcomp2);add(jcomp3);add(jcomp4);add(jcomp5);add(jcomp6);add(jcomp7);
		add(jcomp8);add(jcomp9);add(jcomp10);add(jcomp11);add(jcomp12);add(name);add(effectName);
		add(descriptionEffect);add(hp);add(atk1);add(atk2);add(atk3);add(dp);add(p);add(specialty);
		add(level);add(downDigimon);add(sacrificeCard);add(attack1);add(attack2);add(attack3);add(downSupport);
		

		// set component bounds (only needed by Absolute Positioning)
		cardImage.setBounds(480, 20, 300, 390);
		jcomp2.setBounds(5, 20, 100, 25);
		jcomp3.setBounds(5, 55, 100, 25);
		jcomp4.setBounds(5, 100, 140, 25);
		jcomp5.setBounds(5, 165, 100, 25);
		jcomp6.setBounds(5, 200, 100, 25);
		jcomp7.setBounds(5, 235, 100, 25);
		jcomp8.setBounds(5, 271, 100, 25);
		jcomp9.setBounds(5, 315, 100, 25);
		jcomp10.setBounds(5, 350, 100, 25);
		jcomp11.setBounds(5, 385, 100, 25);
		jcomp12.setBounds(5, 420, 100, 25);
		name.setBounds(200, 20, 200, 25);
		effectName.setBounds(200, 55, 201, 25);
		descriptionEffect.setBounds(200, 90, 200, 50);
		hp.setBounds(200, 165, 200, 25);
		atk1.setBounds(200, 200, 200, 25);
		atk2.setBounds(200, 235, 200, 25);
		atk3.setBounds(200, 271, 200, 25);
		dp.setBounds(200, 315, 200, 25);
		p.setBounds(200, 350, 200, 25);
		specialty.setBounds(200, 385, 200, 25);
		level.setBounds(200, 420, 200, 25);
		downDigimon.setBounds(340, 470, 95, 30);
		downSupport.setBounds(340, 470, 95, 30);
		sacrificeCard.setBounds(340, 470, 95, 30);
		attack1.setBounds (175, 470, 95, 30);
		attack2.setBounds(340, 470, 95, 30);
        attack3.setBounds (500, 470, 95, 30);
        
        downDigimon.addActionListener(l);
        downSupport.addActionListener(l);
        sacrificeCard.addActionListener(l);
        attack1.addActionListener(l);
        attack2.addActionListener(l);
        attack3.addActionListener(l);
	}

	public void showAttributes(CardPOJO pojo, boolean opponent) throws Exception {
		if(pojo.isOptionCard()) {
			if(opponent) {
				showOptionCardAttributesOpponent(pojo);
			} else {
				showOptionCardAttributes(pojo);
			}
		} else { 
			if(opponent) {
				showDigimonCardAttributesOpponent(pojo);
			} else {
				showDigimonCardAttributes(pojo);
			}
		}
	}

	public void showOptionCardAttributes(CardPOJO pojo) throws Exception {
		name.setText(pojo.getName());
		effectName.setText(pojo.getEffect());
		descriptionEffect.setText(pojo.getDescription());
		String path = pojo.getPath();
		Image iconLogo = new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream(path))).getImage()
				.getScaledInstance(cardImage.getWidth(), cardImage.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(iconLogo);
		cardImage.setIcon(imageIcon);
		hp.setText("");
		atk1.setText("");
		atk2.setText("");
		atk3.setText("");
		dp.setText("");
		p.setText("");
		specialty.setText("");
		level.setText("");
		repaint();
	}
	
	public void showOptionCardAttributesOpponent(CardPOJO pojo) throws IOException {
		dissableAllButtons();
		name.setText(pojo.getName());
		effectName.setText(pojo.getEffect());
		descriptionEffect.setText(pojo.getDescription());
		String path = pojo.getPath();
		Image iconLogo = new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream(path))).getImage()
				.getScaledInstance(cardImage.getWidth(), cardImage.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(iconLogo);
		cardImage.setIcon(imageIcon);
		hp.setText("");
		atk1.setText("");
		atk2.setText("");
		atk3.setText("");
		dp.setText("");
		p.setText("");
		specialty.setText("");
		level.setText("");
		repaint();
	}

	public void showDigimonCardAttributes(CardPOJO pojo) throws Exception {
		name.setText(pojo.getName());
		effectName.setText("Não Há");
		descriptionEffect.setText("Não Há");
		String path = pojo.getPath();
		Image iconLogo = new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream(path))).getImage()
				.getScaledInstance(cardImage.getWidth(), cardImage.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(iconLogo);
		cardImage.setIcon(imageIcon);
		hp.setText(String.valueOf(pojo.getHp()));
		dp.setText(String.valueOf(pojo.getDp()));
		p.setText(String.valueOf(pojo.getP()));
		atk1.setText(String.valueOf(pojo.getAttack1()));
		atk2.setText(String.valueOf(pojo.getAttack2()));
		atk3.setText(String.valueOf(pojo.getAttack3()));
		specialty.setText(pojo.getSpecialty());
		level.setText(String.valueOf(pojo.getLevel()));
		repaint();
	}
	
	public void showDigimonCardAttributesOpponent(CardPOJO pojo) throws IOException {
		dissableAllButtons();
		name.setText(pojo.getName());
		effectName.setText("Não Há");
		descriptionEffect.setText("Não Há");
		String path = pojo.getPath();
		Image iconLogo = new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream(path))).getImage()
				.getScaledInstance(cardImage.getWidth(), cardImage.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(iconLogo);
		cardImage.setIcon(imageIcon);
		hp.setText(String.valueOf(pojo.getHp()));
		dp.setText(String.valueOf(pojo.getDp()));
		p.setText(String.valueOf(pojo.getP()));
		atk1.setText(String.valueOf(pojo.getAttack1()));
		atk2.setText(String.valueOf(pojo.getAttack2()));
		atk3.setText(String.valueOf(pojo.getAttack3()));
		specialty.setText(pojo.getSpecialty());
		level.setText(String.valueOf(pojo.getLevel()));
		repaint();
	}
	
	public void showAttributesCardInBattleField(CardPOJO pojo) throws IOException {
		name.setText(pojo.getName());
		effectName.setText(pojo.getEffect());
		descriptionEffect.setText(pojo.getDescription());
		String path = pojo.getPath();
		Image iconLogo = new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream(path))).getImage()
				.getScaledInstance(cardImage.getWidth(), cardImage.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(iconLogo);
		cardImage.setIcon(imageIcon);
		if(!pojo.isOptionCard()) {
			hp.setText(String.valueOf(pojo.getHp()));
			dp.setText(String.valueOf(pojo.getDp()));
			p.setText(String.valueOf(pojo.getP()));
			atk1.setText(String.valueOf(pojo.getAttack1()));
			atk2.setText(String.valueOf(pojo.getAttack2()));
			atk3.setText(String.valueOf(pojo.getAttack3()));
			specialty.setText(pojo.getSpecialty());
			level.setText(String.valueOf(pojo.getLevel()));
		} else {
			hp.setText("");
			atk1.setText("");
			atk2.setText("");
			atk3.setText("");
			dp.setText("");
			p.setText("");
			specialty.setText("");
			level.setText("");
		}
		repaint();
	}
	
	public void dissableAllButtons() {
		enableButtonDownDigimon(false, false);
		enableButtonDownDigimon(false, true);
		enableButtonSacrificeCard(false);
		enableButtonsAttack(false);
		pack();
	}
	
	public void enableButtonDownDigimon(boolean enable, boolean support) {
		if(enable) {
			if(support) {
				downSupport.setEnabled(true);
				downSupport.setVisible(true);
			} else {
				downDigimon.setEnabled(true);
				downDigimon.setVisible(true);
			}
		} else {
			if(support) {
				downSupport.setEnabled(false);
				downSupport.setVisible(false);
			} else {
				downDigimon.setEnabled(false);
				downDigimon.setVisible(false);
			}
		}
		repaint();
	}
	
	public void enableButtonSacrificeCard(boolean enable) {
		if(enable) {
			sacrificeCard.setEnabled(true);
			sacrificeCard.setVisible(true);
		} else {
			sacrificeCard.setEnabled(false);
			sacrificeCard.setVisible(false);
		}
	}
	
	public void enableButtonsAttack(boolean enable) {
		if(enable) {
			attack1.setEnabled(true);
			attack1.setVisible(true);
			attack2.setEnabled(true);
			attack2.setVisible(true);
			attack3.setEnabled(true);
			attack3.setVisible(true);
		} else {
			attack1.setEnabled(false);
			attack1.setVisible(false);
			attack2.setEnabled(false);
			attack2.setVisible(false);
			attack3.setEnabled(false);
			attack3.setVisible(false);
		}
	}
}