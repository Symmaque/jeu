import javax.swing.*;
import java.awt.*;

public class Panneau extends JPanel {
    int ordre  = 0;
    public void paintComponent(Graphics g){
        g.drawString("Joueur"+ordre+" Entrez votre nom:", 10,20);

    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }
}
