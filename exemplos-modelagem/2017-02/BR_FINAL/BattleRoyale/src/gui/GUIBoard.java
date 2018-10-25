package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import enums.ActionType;
import game.Board;
import game.Control;
import game.Position;

public class GUIBoard extends JFrame {

	private Control control;
	private JPanel table;
	private JPanel buttons;
	private JPanel hud;
	private GridBagConstraints gbc;

	public GUIBoard(Control c) {
		this.control = c;
		this.table = new JPanel();
		this.buttons = new JPanel();
		this.hud = new JPanel();
		this.setTitle("BATTLE ROYALE - GAME");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout());

		this.gbc = new GridBagConstraints();

		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		setBoard();
		this.add(this.table);

		this.gbc.gridx = 1;
		this.gbc.gridy = 0;
		this.add(this.hud);

		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		setButtons();
		this.add(this.buttons);

		this.pack();
		this.setVisible(true);
	}

	public void setBoard() {
		Board b = this.control.getBoard();
		int nRow = b.getHeight();
		int nCol = b.getWidth();
		this.table.setSize(32 * nRow, 32 * nCol);
		this.table.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < nCol; j++) {
				gbc.gridx = j;
				gbc.gridy = i;
				Sprite sprite = new Sprite(i, j, b.getPosition(i, j));
				this.table.add(sprite, gbc);

				repaint();
			}
		}
	}

	public void setButtons() {
		this.buttons.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 1;
		JButton changeTurn = new JButton("CHANGE TURN");
		this.buttons.add(changeTurn, gbc);
		changeTurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "CHANGE TURN") {
					control.changeTurn();

				}
			}
		});

	}

	public void updateBoard() {
		for (Component c : this.table.getComponents())
			if (c instanceof Sprite) {
				c.repaint();
			}
	}

	public BufferedImage getImage(String path) {
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();

		}
		return bi;
	}

	private class Sprite extends JPanel {
		private Position p;

		public Sprite(final int x, final int y, Position p) {
			super();
			this.p = p;
			this.setSize(32, 32);
			addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					control.clickPosition(x, y);
				}
			});
		}

		public void paint(Graphics g) {
			super.paintComponent(g);
			String path = this.p.getImage();

			BufferedImage bi = getImage(path);
			g.drawImage(bi, 0, 0, null);
		}

	}

}