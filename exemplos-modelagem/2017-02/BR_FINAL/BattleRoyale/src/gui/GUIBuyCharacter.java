package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import enums.CharacterType;
import game.Control;

public class GUIBuyCharacter extends JFrame {

	private Control control;
	private JPanel buttons;
	private GridBagConstraints gbc;

	public GUIBuyCharacter(Control control) {
		this.control = control;
		this.buttons = new JPanel();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(600, 300));
		this.setLayout(new GridBagLayout());
		this.gbc = new GridBagConstraints();

		this.gbc.gridx = 0;
		this.gbc.gridy = 0;

		setButtons();

		this.add(this.buttons);
		this.pack();
		this.setVisible(true);
	}

	public void setButtons() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		JButton scout = new JButton("SCOUT");
		scout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				control.buyCharacter(CharacterType.SCOUT);
			}
		});
		this.buttons.add(scout, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		JButton rifleman = new JButton("RIFLEMAN");
		rifleman.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				control.buyCharacter(CharacterType.RIFLEMAN);
			}
		});
		this.buttons.add(rifleman, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		JButton sub = new JButton("SUBMACHINE GUNNER");
		sub.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				control.buyCharacter(CharacterType.SUBMACHINE);
			}
		});
		this.buttons.add(sub, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		JButton sniper = new JButton("SNIPER");
		sniper.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				control.buyCharacter(CharacterType.SNIPER);

			}
		});
		this.buttons.add(sniper, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		JButton finish = new JButton("FINISH BUY");
		finish.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				control.finishBuy();
				dispose();
			}
		});
		this.buttons.add(finish, gbc);

	}

}
