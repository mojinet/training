import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        // Java recommande d'excuter l'ecran dans un thread spécifique tel que :
        SwingUtilities.invokeLater(new Runnable(){  // On utilise une classe anonyme

            @Override
            public void run(){
                MaFenetre frame = new MaFenetre();   // Appel a notre classe conteneur
                frame.setVisible(true);              // on rend la fenetre visible
            }
        });
    }
}
