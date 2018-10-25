package view;

import control.ActorPlayer;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Screen extends JFrame {

    protected ActorPlayer actorPlayer;
    protected JLabel data;
    protected JLabel blackCard;
    protected ArrayList<JLabel> whiteCards;
    protected int choice;
    protected JLabel aux;
    protected String user;
    protected JLabel round;
    protected int points;
    protected int rounds;

    public Screen() {
    }

    public Screen(ActorPlayer actorPlayer) {
        this.actorPlayer = actorPlayer;
        data = new JLabel();
        blackCard = new JLabel();
        choice = -1;
        round = new JLabel();

        whiteCards = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            whiteCards.add(new JLabel());
        }

        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        setTitle("Cards Against Humanity");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        ButtonManager manager = new ButtonManager();
        JButton connectButton = new JButton("Connect");
        connectButton.setOpaque(false);
        connectButton.setContentAreaFilled(false);
        connectButton.setBorderPainted(false);
        menuBar.add(connectButton);
        connectButton.addActionListener(manager);
        JButton startGameButton = new JButton("Start game");
        startGameButton.setOpaque(false);
        startGameButton.setContentAreaFilled(false);
        startGameButton.setBorderPainted(false);
        menuBar.add(startGameButton);
        startGameButton.addActionListener(manager);
        JButton makeAMoveButton = new JButton("Make a move");
        makeAMoveButton.setOpaque(false);
        makeAMoveButton.setContentAreaFilled(false);
        makeAMoveButton.setBorderPainted(false);
        menuBar.add(makeAMoveButton);
        makeAMoveButton.addActionListener(manager);
        JButton disconnectButton = new JButton("Disconnect");
        disconnectButton.setOpaque(false);
        disconnectButton.setContentAreaFilled(false);
        disconnectButton.setBorderPainted(false);
        menuBar.add(disconnectButton);
        disconnectButton.addActionListener(manager);
        JButton exitButton = new JButton("Exit");
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        menuBar.add(exitButton);
        exitButton.addActionListener(manager);
        round.setHorizontalAlignment(SwingConstants.CENTER);
        round.setVerticalAlignment(SwingConstants.CENTER);
        if (actorPlayer.getGame().isOngoingMatch()) {
            round.setText("     " + user + ", Round " + rounds + ", " + points + " points     ");
        }
        round.setOpaque(false);
        round.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(round);

        final String html1 = "<html><body style='width: ";
        final String html2 = "px'>";
        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        javax.swing.border.Border border2 = BorderFactory.createLineBorder(Color.RED, 1);

        blackCard.setPreferredSize(new Dimension(180, 180));
        blackCard.setBackground(Color.black);
        blackCard.setForeground(Color.white);
        blackCard.setOpaque(true);
        blackCard.setHorizontalAlignment(SwingConstants.CENTER);
        blackCard.setVerticalAlignment(SwingConstants.CENTER);
        container.add(blackCard);

        data.setPreferredSize(new Dimension(550, 180));
        data.setHorizontalAlignment(SwingConstants.CENTER);
        data.setVerticalAlignment(SwingConstants.CENTER);
        data.setBorder(border);
        container.add(data);

        whiteCards.get(0).setPreferredSize(new Dimension(180, 180));
        whiteCards.get(0).setBackground(Color.white);
        whiteCards.get(0).setOpaque(true);
        whiteCards.get(0).setHorizontalAlignment(SwingConstants.CENTER);
        whiteCards.get(0).setVerticalAlignment(SwingConstants.CENTER);
        whiteCards.get(0).setBorder(border);
        whiteCards.get(0).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                whiteCards.get(0).setBorder(border2);
                if (aux != null) {
                    aux.setBorder(border);
                }
                aux = whiteCards.get(0);
                choice = 0;
            }
        });
        container.add(whiteCards.get(0));

        whiteCards.get(1).setPreferredSize(new Dimension(180, 180));
        whiteCards.get(1).setBackground(Color.white);
        whiteCards.get(1).setOpaque(true);
        whiteCards.get(1).setHorizontalAlignment(SwingConstants.CENTER);
        whiteCards.get(1).setVerticalAlignment(SwingConstants.CENTER);
        whiteCards.get(1).setBorder(border);
        whiteCards.get(1).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                whiteCards.get(1).setBorder(border2);
                if (aux != null) {
                    aux.setBorder(border);
                }
                aux = whiteCards.get(1);
                choice = 1;
            }
        });
        container.add(whiteCards.get(1));

        whiteCards.get(2).setPreferredSize(new Dimension(180, 180));
        whiteCards.get(2).setBackground(Color.white);
        whiteCards.get(2).setOpaque(true);
        whiteCards.get(2).setHorizontalAlignment(SwingConstants.CENTER);
        whiteCards.get(2).setVerticalAlignment(SwingConstants.CENTER);
        whiteCards.get(2).setBorder(border);
        whiteCards.get(2).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                whiteCards.get(2).setBorder(border2);
                if (aux != null) {
                    aux.setBorder(border);
                }
                aux = whiteCards.get(2);
                choice = 2;
            }
        });
        container.add(whiteCards.get(2));

        whiteCards.get(3).setPreferredSize(new Dimension(180, 180));
        whiteCards.get(3).setBackground(Color.white);
        whiteCards.get(3).setOpaque(true);
        whiteCards.get(3).setHorizontalAlignment(SwingConstants.CENTER);
        whiteCards.get(3).setVerticalAlignment(SwingConstants.CENTER);
        whiteCards.get(3).setBorder(border);
        whiteCards.get(3).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                whiteCards.get(3).setBorder(border2);
                if (aux != null) {
                    aux.setBorder(border);
                }
                aux = whiteCards.get(3);
                choice = 3;
            }
        });
        container.add(whiteCards.get(3));

        whiteCards.get(4).setPreferredSize(new Dimension(180, 180));
        whiteCards.get(4).setBackground(Color.white);
        whiteCards.get(4).setOpaque(true);
        whiteCards.get(4).setHorizontalAlignment(SwingConstants.CENTER);
        whiteCards.get(4).setVerticalAlignment(SwingConstants.CENTER);
        whiteCards.get(4).setBorder(border);
        whiteCards.get(4).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                whiteCards.get(4).setBorder(border2);
                if (aux != null) {
                    aux.setBorder(border);
                }
                aux = whiteCards.get(4);
                choice = 4;
            }
        });
        container.add(whiteCards.get(4));

        whiteCards.get(5).setPreferredSize(new Dimension(180, 180));
        whiteCards.get(5).setBackground(Color.white);
        whiteCards.get(5).setOpaque(true);
        whiteCards.get(5).setHorizontalAlignment(SwingConstants.CENTER);
        whiteCards.get(5).setVerticalAlignment(SwingConstants.CENTER);
        whiteCards.get(5).setBorder(border);
        whiteCards.get(5).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                whiteCards.get(5).setBorder(border2);
                if (aux != null) {
                    aux.setBorder(border);
                }
                aux = whiteCards.get(5);
                choice = 5;
            }
        });
        container.add(whiteCards.get(5));

        whiteCards.get(6).setPreferredSize(new Dimension(180, 180));
        whiteCards.get(6).setBackground(Color.white);
        whiteCards.get(6).setOpaque(true);
        whiteCards.get(6).setHorizontalAlignment(SwingConstants.CENTER);
        whiteCards.get(6).setVerticalAlignment(SwingConstants.CENTER);
        whiteCards.get(6).setBorder(border);
        whiteCards.get(6).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                whiteCards.get(6).setBorder(border2);
                if (aux != null) {
                    aux.setBorder(border);
                }
                aux = whiteCards.get(6);
                int index = 6;
            }
        });
        container.add(whiteCards.get(6));

        whiteCards.get(7).setPreferredSize(new Dimension(180, 180));
        whiteCards.get(7).setBackground(Color.white);
        whiteCards.get(7).setOpaque(true);
        whiteCards.get(7).setHorizontalAlignment(SwingConstants.CENTER);
        whiteCards.get(7).setVerticalAlignment(SwingConstants.CENTER);
        whiteCards.get(7).setBorder(border);
        whiteCards.get(7).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                whiteCards.get(7).setBorder(border2);
                if (aux != null) {
                    aux.setBorder(border);
                }
                aux = whiteCards.get(7);
                choice = 7;
            }
        });
        container.add(whiteCards.get(7));
    }

    private class ButtonManager implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("Exit")) {
                actorPlayer.handle(0);
            }
            if (ae.getActionCommand().equals("Connect")) {
                actorPlayer.handle(1);
            }
            if (ae.getActionCommand().equals("Start game")) {
                actorPlayer.handle(2);
            }
            if (ae.getActionCommand().equals("Make a move")) {
                actorPlayer.handle(3);
            }
            if (ae.getActionCommand().equals("Disconnect")) {
                actorPlayer.handle(4);
            }
        }
    }

    public void updatePoints(int points) {
        this.points = points;
        round.setText("     " + user + ", Round " + rounds + ", " + points + " points     ");
    }

    public void updateRound(int round) {
        this.rounds = round;
        this.round.setText("     " + user + ", Round " + rounds + ", " + points + " points     ");
    }

    public void setBlackCard(String text) {
        blackCard.setText(text);
    }

    public void visible() {
        String name = null;
        while (name == null || name.equals("")) {
            name = JOptionPane.showInputDialog(null, "What's your name?", "Cards Against Humanity", JOptionPane.PLAIN_MESSAGE);
            if (name == null || name.equals("")) {
                JOptionPane.showMessageDialog(null, "You haven't answered the question.");
            }
        }
        actorPlayer.setName(name);
        user = name;
        round.setText("     " + user + ", Round 0, 0 points     ");
        setVisible(true);
    }

    public ArrayList<JLabel> getWhiteCards() {
        return whiteCards;
    }

    public void setWhiteCards(ArrayList<JLabel> whiteCards) {
        this.whiteCards = whiteCards;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }
    
    public String getUser() {
        return this.user;
    }

    public void notifyConnection(int cod) {
        switch (cod) {
            case 0:
                JOptionPane.showMessageDialog(this, "Connection successful");
                break;
            case 1:
                JOptionPane.showMessageDialog(this, "Disconnection successful");
                break;
        }
    }

    public void notifyIrregularity(int cod) {

        switch (cod) {
            case 0:
                JOptionPane.showMessageDialog(this, "Failed to connect");
                break;
            case 1:
                JOptionPane.showMessageDialog(this, "You are already connected");
                break;
            case 2:
                JOptionPane.showMessageDialog(this, "Failed to disconnect");
                break;
            case 3:
                JOptionPane.showMessageDialog(this, "You are already diconnected");
                break;
            case 4:
                JOptionPane.showMessageDialog(this, "There are not enough players to start a game");
                break;
            case 5:
                JOptionPane.showMessageDialog(this, "It's not your turn.");
                break;
        }
    }

    public void showWinner(String name) {
        JOptionPane.showMessageDialog(this, "The winner is " + name + "!");
        System.exit(0);
    }

    public void endGame() {
        JOptionPane.showMessageDialog(this, "Someone disconnected. The game will be closed.");
        System.exit(0);
    }

    public JLabel getData() {
        return data;
    }

    public void setData(JLabel data) {
        this.data = data;
    }

}
