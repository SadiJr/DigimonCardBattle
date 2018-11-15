package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import actor.ActorPlayer;
import model.CardPOJO;

public class UpdateScreen extends JFrame {
	private static final long serialVersionUID = 1L;

	private ActorPlayer player;
	
	private JButton card1;
    private JButton card2;
    private JButton card3;
    private JButton card4;
    private JLabel jcomp5;
    private JLabel jcomp6;
    private JLabel jcomp7;
    private JLabel jcomp8;
    private JLabel jcomp9;
    private JLabel jcomp10;
    private JLabel jcomp11;
    private JLabel jcomp12;
    private JLabel jcomp13;
    private JLabel jcomp14;
    private JLabel jcomp15;
    private JLabel jcomp16;
    private JLabel jcomp17;
    private JLabel jcomp18;
    private JLabel jcomp19;
    private JLabel jcomp20;
    private JLabel jcomp21;
    private JLabel jcomp22;
    private JLabel jcomp23;
    private JLabel jcomp24;
    private JLabel jcomp25;
    private JLabel jcomp26;
    private JLabel jcomp27;
    private JLabel jcomp28;
    private JLabel jcomp29;
    private JLabel jcomp30;
    private JLabel jcomp31;
    private JLabel jcomp32;
    private JLabel jcomp33;
    private JLabel jcomp34;
    private JLabel jcomp35;
    private JLabel jcomp36;
    private JLabel jcomp37;
    private JLabel jcomp38;
    private JLabel jcomp39;
    private JLabel jcomp40;
    private JLabel jcomp41;
    private JLabel jcomp42;
    private JLabel jcomp43;
    private JLabel jcomp44;
    private JLabel jcomp45;
    private JLabel jcomp46;
    private JLabel jcomp47;
    private JLabel jcomp48;
    private JLabel jcomp49;
    private JLabel jcomp50;
    private JLabel jcomp51;
    private JLabel jcomp52;
    private JLabel jcomp53;
    private JLabel jcomp54;
    private JLabel jcomp55;
    private JLabel jcomp56;
    private JLabel jcomp57;
    private JLabel jcomp58;
    private JLabel jcomp59;
    private JLabel jcomp60;
    private JLabel jcomp61;
    private JLabel jcomp62;
    private JLabel jcomp63;
    private JLabel jcomp64;
    private JLabel jcomp65;
    private JLabel jcomp66;
    private JLabel jcomp67;
    private JLabel jcomp68;
    private JLabel jcomp69;
    private JLabel jcomp70;
    private JLabel jcomp71;
    private JLabel jcomp72;
    private JLabel jcomp73;
    private JLabel jcomp74;
    private JLabel jcomp75;
    private JLabel jcomp76;
    private JLabel jcomp77;
    private JLabel jcomp78;
    private JLabel jcomp79;
    private JLabel jcomp80;
    private JLabel jcomp81;
    private JLabel jcomp82;
    private JLabel jcomp83;
    private JLabel jcomp84;
    private JLabel jcomp85;
    private JLabel jcomp86;
    
    private ActionListener l = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == card1) {
				player.updateCard(jcomp45.getText());
			} else if(e.getSource() == card2) {
				player.updateCard(jcomp57.getText());
			} else if(e.getSource() == card3) {
				player.updateCard(jcomp77.getText());
			} else if(e.getSource() == card4) {
				player.updateCard(jcomp67.getText());
			}
			
		}
	};
	
	public UpdateScreen(ActorPlayer actorPlayer) {
		this.player = actorPlayer;
		config();
	}

	private void config() {

		// construct components
		card1 = new JButton("Button 1");
		card2 = new JButton("Button 2");
		card3 = new JButton("Button 3");
		card4 = new JButton("Button 4");
		jcomp5 = new JLabel("Nome");
		jcomp6 = new JLabel("Efeito");
		jcomp7 = new JLabel("HP");
		jcomp8 = new JLabel("Ataque 1");
		jcomp9 = new JLabel("Ataque 2");
		jcomp10 = new JLabel("Ataque 3");
		jcomp11 = new JLabel("DP");
		jcomp12 = new JLabel("P");
		jcomp13 = new JLabel("Especialidade");
		jcomp14 = new JLabel("Level");
		jcomp15 = new JLabel("Nome");
		jcomp16 = new JLabel("Efeito");
		jcomp17 = new JLabel("HP");
		jcomp18 = new JLabel("Ataque 1");
		jcomp19 = new JLabel("Ataque 2");
		jcomp20 = new JLabel("Ataque 3");
		jcomp21 = new JLabel("DP");
		jcomp22 = new JLabel("P");
		jcomp23 = new JLabel("Especialidade");
		jcomp24 = new JLabel("Level");
		jcomp25 = new JLabel("Nome");
		jcomp26 = new JLabel("Efeito");
		jcomp27 = new JLabel("HP");
		jcomp28 = new JLabel("Ataque 1");
		jcomp29 = new JLabel("Ataque 2");
		jcomp30 = new JLabel("Ataque 3");
		jcomp31 = new JLabel("DP");
		jcomp32 = new JLabel("P");
		jcomp33 = new JLabel("Especialidade");
		jcomp34 = new JLabel("Level");
		jcomp35 = new JLabel("Nome");
		jcomp36 = new JLabel("Efeito");
		jcomp37 = new JLabel("HP");
		jcomp38 = new JLabel("Ataque 1");
		jcomp39 = new JLabel("Ataque 2");
		jcomp40 = new JLabel("Ataque 3");
		jcomp41 = new JLabel("DP");
		jcomp42 = new JLabel("P");
		jcomp43 = new JLabel("Especialidade");
		jcomp44 = new JLabel("Level");
		jcomp45 = new JLabel("jcomp45");
		jcomp46 = new JLabel("jcomp46");
		jcomp47 = new JLabel("jcomp47");
		jcomp48 = new JLabel("jcomp48");
		jcomp49 = new JLabel("jcomp49");
		jcomp50 = new JLabel("jcomp50");
		jcomp51 = new JLabel("jcomp51");
		jcomp52 = new JLabel("jcomp52");
		jcomp53 = new JLabel("jcomp53");
		jcomp54 = new JLabel("jcomp54");
		jcomp55 = new JLabel("jcomp55");
		jcomp56 = new JLabel("jcomp56");
		jcomp57 = new JLabel("jcomp57");
		jcomp58 = new JLabel("jcomp58");
		jcomp59 = new JLabel("jcomp59");
		jcomp60 = new JLabel("jcomp60");
		jcomp61 = new JLabel("jcomp61");
		jcomp62 = new JLabel("jcomp62");
		jcomp63 = new JLabel("jcomp63");
		jcomp64 = new JLabel("jcomp64");
		jcomp65 = new JLabel("jcomp65");
		jcomp66 = new JLabel("jcomp66");
		jcomp67 = new JLabel("jcomp67");
		jcomp68 = new JLabel("jcomp68");
		jcomp69 = new JLabel("jcomp69");
		jcomp70 = new JLabel("jcomp70");
		jcomp71 = new JLabel("jcomp71");
		jcomp72 = new JLabel("jcomp72");
		jcomp73 = new JLabel("jcomp73");
		jcomp74 = new JLabel("jcomp74");
		jcomp75 = new JLabel("jcomp75");
		jcomp76 = new JLabel("jcomp76");
		jcomp77 = new JLabel("jcomp77");
		jcomp78 = new JLabel("jcomp78");
		jcomp79 = new JLabel("jcomp79");
		jcomp80 = new JLabel("jcomp80");
		jcomp81 = new JLabel("jcomp81");
		jcomp82 = new JLabel("jcomp82");
		jcomp83 = new JLabel("jcomp83");
		jcomp84 = new JLabel("jcomp84");
		jcomp85 = new JLabel("jcomp85");
		jcomp86 = new JLabel("jcomp86");

		// adjust size and set layout
		setPreferredSize(new Dimension(1088, 664));
		setLayout(null);

		// add components
		add(card1);add(card2);add(card3);add(card4);add(jcomp5);add(jcomp6);
		add(jcomp7);add(jcomp8);add(jcomp9);add(jcomp10);add(jcomp11);
		add(jcomp12);add(jcomp13);add(jcomp14);add(jcomp15);add(jcomp16);
		add(jcomp17);add(jcomp18);add(jcomp19);add(jcomp20);add(jcomp21);
		add(jcomp22);add(jcomp23);add(jcomp24);add(jcomp25);add(jcomp26);
		add(jcomp27);add(jcomp28);add(jcomp29);add(jcomp30);add(jcomp31);
		add(jcomp32);add(jcomp33);add(jcomp34);add(jcomp35);add(jcomp36);
		add(jcomp37);add(jcomp38);add(jcomp39);add(jcomp40);add(jcomp41);
		add(jcomp42);add(jcomp43);add(jcomp44);add(jcomp45);add(jcomp46);
		add(jcomp47);add(jcomp48);add(jcomp49);add(jcomp50);add(jcomp51);
		add(jcomp52);add(jcomp53);add(jcomp54);add(jcomp55);add(jcomp56);
		add(jcomp57);add(jcomp58);add(jcomp59);add(jcomp60);add(jcomp61);
		add(jcomp62);add(jcomp63);add(jcomp64);add(jcomp65);add(jcomp66);
		add(jcomp67);add(jcomp68);add(jcomp69);add(jcomp70);add(jcomp71);
		add(jcomp72);add(jcomp73);add(jcomp74);add(jcomp75);add(jcomp76);
		add(jcomp77);add(jcomp78);add(jcomp79);add(jcomp80);add(jcomp81);
		add(jcomp82);add(jcomp83);add(jcomp84);add(jcomp85);add(jcomp86);

		// set component bounds (only needed by Absolute Positioning)
		card1.setBounds(5, 10, 240, 320);
		card2.setBounds(280, 10, 240, 320);
		card3.setBounds(550, 10, 235, 320);
		card4.setBounds(815, 10, 235, 320);
		jcomp5.setBounds(5, 340, 100, 25);
		jcomp6.setBounds(5, 370, 100, 25);
		jcomp7.setBounds(5, 400, 100, 25);
		jcomp8.setBounds(5, 430, 100, 25);
		jcomp9.setBounds(5, 460, 100, 25);
		jcomp10.setBounds(5, 490, 100, 25);
		jcomp11.setBounds(5, 520, 100, 25);
		jcomp12.setBounds(5, 550, 100, 25);
		jcomp13.setBounds(5, 580, 100, 25);
		jcomp14.setBounds(5, 610, 100, 25);
		jcomp15.setBounds(280, 340, 100, 25);
		jcomp16.setBounds(280, 370, 100, 25);
		jcomp17.setBounds(280, 400, 100, 25);
		jcomp18.setBounds(280, 430, 100, 25);
		jcomp19.setBounds(280, 460, 100, 25);
		jcomp20.setBounds(280, 490, 100, 25);
		jcomp21.setBounds(280, 520, 100, 25);
		jcomp22.setBounds(280, 550, 100, 25);
		jcomp23.setBounds(280, 580, 100, 25);
		jcomp24.setBounds(280, 610, 100, 25);
		jcomp25.setBounds(550, 340, 100, 25);
		jcomp26.setBounds(550, 370, 100, 25);
		jcomp27.setBounds(550, 400, 100, 25);
		jcomp28.setBounds(550, 430, 100, 25);
		jcomp29.setBounds(550, 460, 100, 25);
		jcomp30.setBounds(550, 490, 100, 25);
		jcomp31.setBounds(550, 520, 100, 25);
		jcomp32.setBounds(550, 550, 100, 25);
		jcomp33.setBounds(550, 580, 100, 25);
		jcomp34.setBounds(550, 610, 100, 25);
		jcomp35.setBounds(815, 340, 100, 25);
		jcomp36.setBounds(815, 370, 100, 25);
		jcomp37.setBounds(815, 400, 100, 25);
		jcomp38.setBounds(815, 430, 100, 25);
		jcomp39.setBounds(815, 460, 100, 25);
		jcomp40.setBounds(815, 490, 100, 25);
		jcomp41.setBounds(815, 520, 100, 25);
		jcomp42.setBounds(815, 550, 100, 25);
		jcomp43.setBounds(815, 580, 100, 25);
		jcomp44.setBounds(815, 610, 100, 25);
		jcomp45.setBounds(145, 340, 100, 25);
		jcomp46.setBounds(145, 370, 100, 25);
		jcomp47.setBounds(140, 145, 100, 25);
		jcomp48.setBounds(140, 145, 100, 25);
		jcomp49.setBounds(145, 520, 100, 25);
		jcomp50.setBounds(145, 550, 100, 25);
		jcomp51.setBounds(145, 580, 100, 25);
		jcomp52.setBounds(145, 610, 100, 25);
		jcomp53.setBounds(145, 400, 100, 25);
		jcomp54.setBounds(145, 430, 100, 25);
		jcomp55.setBounds(145, 460, 100, 25);
		jcomp56.setBounds(145, 490, 100, 25);
		jcomp57.setBounds(420, 340, 100, 25);
		jcomp58.setBounds(420, 370, 100, 25);
		jcomp59.setBounds(420, 400, 100, 25);
		jcomp60.setBounds(420, 430, 100, 25);
		jcomp61.setBounds(420, 460, 100, 25);
		jcomp62.setBounds(420, 490, 100, 25);
		jcomp63.setBounds(420, 520, 100, 25);
		jcomp64.setBounds(420, 550, 100, 25);
		jcomp65.setBounds(420, 580, 100, 25);
		jcomp66.setBounds(420, 610, 100, 25);
		jcomp67.setBounds(950, 340, 100, 25);
		jcomp68.setBounds(950, 370, 100, 25);
		jcomp69.setBounds(950, 400, 100, 25);
		jcomp70.setBounds(950, 430, 100, 25);
		jcomp71.setBounds(950, 460, 100, 25);
		jcomp72.setBounds(950, 490, 100, 25);
		jcomp73.setBounds(950, 520, 100, 25);
		jcomp74.setBounds(950, 550, 100, 25);
		jcomp75.setBounds(950, 580, 100, 25);
		jcomp76.setBounds(950, 610, 100, 25);
		jcomp77.setBounds(685, 340, 100, 25);
		jcomp78.setBounds(685, 370, 100, 25);
		jcomp79.setBounds(685, 400, 100, 25);
		jcomp80.setBounds(685, 430, 100, 25);
		jcomp81.setBounds(685, 460, 100, 25);
		jcomp82.setBounds(685, 490, 100, 25);
		jcomp83.setBounds(685, 520, 100, 25);
		jcomp84.setBounds(685, 550, 100, 25);
		jcomp85.setBounds(685, 580, 100, 25);
		jcomp86.setBounds(685, 610, 100, 25);
		card1.addActionListener(l);
		card2.addActionListener(l);
		card3.addActionListener(l);
		card4.addActionListener(l);

	}

	public void viewOptions(Collection<CardPOJO> hand) {
		ArrayList<JButton> buttons = (ArrayList<JButton>) getButtons();
		ArrayList<CardPOJO> hand2 = (ArrayList<CardPOJO>) hand;
		ArrayList<ArrayList<JLabel>> labels = getLabels();
		for(int i =0; i < hand2.size(); i++) {
			CardPOJO pojo = hand2.get(i);
			if(pojo.isOptionCard()) {
				ImageIcon iconLogo = new ImageIcon(System.getProperty("user.dir") + "/pictures/backCard.jpg");//.getImage()
//						.getScaledInstance(card1.getWidth() +20, card1.getHeight(), Image.SCALE_SMOOTH);
//				ImageIcon imageIcon = new ImageIcon(iconLogo);
				buttons.get(i).setIcon(iconLogo);
				buttons.get(i).setEnabled(false);
				ArrayList<JLabel> arrayList = labels.get(i);
				for (JLabel jLabel : arrayList) {
					jLabel.setVisible(false);
				}
			} else {
				ImageIcon iconLogo = new ImageIcon(System.getProperty("user.dir") + pojo.getPath());
				buttons.get(i).setIcon(iconLogo);
				ArrayList<JLabel> arrayList = labels.get(i);
				arrayList.get(0).setText(pojo.getName());
				arrayList.get(1).setText("Não há");
				arrayList.get(2).setText(String.valueOf(pojo.getHp()));
				arrayList.get(3).setText(String.valueOf(pojo.getAttack1()));
				arrayList.get(4).setText(String.valueOf(pojo.getAttack2()));
				arrayList.get(5).setText(String.valueOf(pojo.getAttack3()));
				arrayList.get(6).setText(String.valueOf(pojo.getDp()));
				arrayList.get(7).setText(String.valueOf(pojo.getP()));
				arrayList.get(8).setText(pojo.getSpecialty());
				arrayList.get(9).setText(String.valueOf(pojo.getLevel()));
			}
		}

	}
	
	private Collection<JButton> getButtons() {
		Collection<JButton> buttons = new ArrayList<>();
		buttons.add(card1);
		buttons.add(card2);
		buttons.add(card3);
		buttons.add(card4);
		return buttons;
	}
	
	private ArrayList<ArrayList<JLabel>> getLabels() {
		ArrayList<ArrayList<JLabel>> labels = new ArrayList<>();
		ArrayList<JLabel> labelsCard1 = new ArrayList<>();
		ArrayList<JLabel> labelsCard2 = new ArrayList<>();
		ArrayList<JLabel> labelsCard3 = new ArrayList<>();
		ArrayList<JLabel> labelsCard4 = new ArrayList<>();
		
		labelsCard1.add(jcomp45);labelsCard1.add(jcomp46);labelsCard1.add(jcomp53);
		labelsCard1.add(jcomp54);labelsCard1.add(jcomp55);labelsCard1.add(jcomp56);
		labelsCard1.add(jcomp49);labelsCard1.add(jcomp50);labelsCard1.add(jcomp51);
		labelsCard1.add(jcomp52);
		
		labelsCard2.add(jcomp57);labelsCard2.add(jcomp58);labelsCard2.add(jcomp59);
		labelsCard2.add(jcomp60);labelsCard2.add(jcomp61);labelsCard2.add(jcomp62);
		labelsCard2.add(jcomp63);labelsCard2.add(jcomp64);labelsCard2.add(jcomp65);
		labelsCard2.add(jcomp66);
		
		labelsCard3.add(jcomp77);labelsCard3.add(jcomp78);labelsCard3.add(jcomp79);
		labelsCard3.add(jcomp80);labelsCard3.add(jcomp81);labelsCard3.add(jcomp82);
		labelsCard3.add(jcomp83);labelsCard3.add(jcomp84);labelsCard3.add(jcomp85);
		labelsCard3.add(jcomp86);
		
		labelsCard4.add(jcomp67);labelsCard4.add(jcomp68);labelsCard4.add(jcomp79);
		labelsCard4.add(jcomp70);labelsCard4.add(jcomp71);labelsCard4.add(jcomp72);
		labelsCard4.add(jcomp73);labelsCard4.add(jcomp74);labelsCard4.add(jcomp75);
		labelsCard4.add(jcomp76);
		labels.add(labelsCard1);
		labels.add(labelsCard2);
		labels.add(labelsCard3);
		labels.add(labelsCard4);
		return labels;
	}
}
