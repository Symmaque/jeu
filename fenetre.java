import javax.swing.*;
import java.awt.*;

public class fenetre extends JFrame {
    int ordre= 0;



    public fenetre(int ordre){
        this.ordre = ordre;


        setLocation(50,0);
        setSize(400,300);

        Panneau pan = new Panneau();
        pan.setOrdre(this.ordre);
        pan.setBackground(Color.ORANGE);
        this.setContentPane(pan);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    public void setOrdre(int Ordre){
        this.ordre = Ordre;
    }
}
