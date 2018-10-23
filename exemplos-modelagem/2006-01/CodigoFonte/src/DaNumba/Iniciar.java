package DaNumba;

/**
 * Classe que implementa o m�todo main.
 * @author DaNumba
 */
public class Iniciar {
    
    public static void main(String[] args) {
        try {
            ControlePrincipal objControlePrincipal = new ControlePrincipal();
            objControlePrincipal.mostrarTelaMenu();
        } catch (Exception exc){
            javax.swing.JOptionPane.showMessageDialog(null,exc.getMessage(),"Erro",1);
        }
    }
}
