
import javax.swing.JOptionPane;

public class JanelaMensagem {

    private String msg;

    public JanelaMensagem(String msg) {
        this.msg = msg;
    }

    public JanelaMensagem(Exception e) {
        this.msg = e.getMessage();
    }

    public void show() {
        JOptionPane.showMessageDialog(null, this.msg);
    }

    public int choose(String[] opcoes) {
        return JOptionPane.showOptionDialog(null, this.msg, "Seleção", 0, 0, null, opcoes, null);
    }

    public String getInput() {
        return JOptionPane.showInputDialog(this.msg);
    }
    
    public void setText(String text){
        this.msg = text;
    }
}
