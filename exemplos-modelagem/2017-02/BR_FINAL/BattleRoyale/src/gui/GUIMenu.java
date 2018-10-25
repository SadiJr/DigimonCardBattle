package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.Control;

public class GUIMenu extends JFrame {
	protected JTextField name;
	protected JPanel buttons;
	protected Control control;
	protected GridBagConstraints gbc;

	public GUIMenu(Control control) {
		this.control = control;
		this.buttons = new JPanel();

		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(300, 400));
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.gbc = new GridBagConstraints();

		setButtons();

		this.add(this.buttons);
		this.setVisible(true);
		this.pack();

	}

	private void setButtons() {

		this.buttons.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;

		JButton start = new JButton("begin game");
		this.buttons.add(start, gbc);
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "begin game") {
					control.beginGame();
				}
			}
		});

		gbc.gridx = 0;
		gbc.gridy = 2;
		JButton connect = new JButton("connect");
		this.buttons.add(connect, gbc);
		connect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "connect") {
					control.connect();
				}
			}
		});

		gbc.gridx = 0;
		gbc.gridy = 3;
		JButton exit = new JButton("exit");
		this.buttons.add(exit, gbc);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				if (e.getActionCommand() == "exit") {
					System.exit(0);
				}
			}
		});
	}

}
