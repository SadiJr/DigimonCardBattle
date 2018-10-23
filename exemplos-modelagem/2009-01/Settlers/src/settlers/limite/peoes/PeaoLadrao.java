package settlers.limite.peoes;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import settlers.limite.posicoes.PosicaoTerreno;

public class PeaoLadrao extends JComponent {

	private static final long serialVersionUID = 1L;
	
	private ImageIcon imagemLadrao;
    private PosicaoTerreno terreno;
    private int transparencia;
    private boolean ativo;

    public PeaoLadrao() {
        imagemLadrao = new ImageIcon(ClassLoader.getSystemResource("peoes/ladrao.png"));
        imagemLadrao = new ImageIcon(imagemLadrao.getImage().getScaledInstance(15, imagemLadrao.getIconHeight() * 15 / imagemLadrao.getIconWidth(), Image.SCALE_SMOOTH));
        setBounds(20, 20, imagemLadrao.getIconWidth(), imagemLadrao.getIconHeight());
        transparencia = 0;
    }

    public PosicaoTerreno getTerreno() {
        return terreno;
    }

    public void setTerreno(PosicaoTerreno terreno) {
        setAtivo(false);
        if (this.terreno != null) {
            this.terreno.remove(this);
            this.terreno.setAtivo(false);
        }
        this.terreno = terreno;
        if (this.terreno != null) {
            this.terreno.add(this);
            this.terreno.setAtivo(false);
        }
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        if (ativo && !this.ativo) {
            this.ativo = true;
            new ThreadPiscar().start();
        } else {
            this.ativo = ativo;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f + ((float) transparencia / 100)));
        g2.drawImage(imagemLadrao.getImage(), 0, 0, this);
    }

    private void setTransparencia(int transparencia) {
        this.transparencia = transparencia;
        repaint();
    }

    private class ThreadPiscar extends Thread {

        private boolean fade = true;

        @Override
        public void run() {
            setTransparencia(0);
            while (ativo) {
                if (transparencia >= 60 || transparencia <= -60) {
                    fade = false;
                }
                if (transparencia == 0) {
                    fade = true;
                }
                if (fade) {
                    setTransparencia(transparencia - 5);
                } else {
                    setTransparencia(transparencia + 5);
                }
                dormir(10);
            }
            setTransparencia(0);
        }

        private void dormir(int tempo) {
            try {
                sleep(tempo);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadPiscar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

}
