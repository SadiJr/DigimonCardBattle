package settlers.limite.posicoes;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class PosicaoOceano extends PosicaoHexagono {

	private static final long serialVersionUID = 1L;
	
	private PosicaoPorto porto;

    public PosicaoOceano(PosicaoHexagono hexagono, int lado, int distancia, int camada, int identificador) {
        super(hexagono, lado, distancia, camada, identificador);
    }

    public void setPorto(PosicaoPorto porto) {
        this.porto = porto;
        repaint();
    }

    public void setPorto(int tipoPorto) {
        setPorto(new PosicaoPorto(this));
    }

    public PosicaoPorto getPorto() {
        return porto;
    }

    @Override
    public void paintComponent(Graphics g) {
        if (porto != null) {
            porto.desenhaPorto((Graphics2D) g);
        }
    }

}
