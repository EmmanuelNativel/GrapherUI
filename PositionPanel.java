/**
 * Pannel du haut : affichage des coordonnées de la souris et de f(x) en temps réel.
 */

import java.awt.*;
import javax.swing.*;

public class PositionPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    protected JTextField x, y, fx1, fx2;

    public PositionPanel(Color background_color) {

        x = new JTextField("");
        y = new JTextField("");
        fx1 = new JTextField("");
        fx2 = new JTextField("");

        this.setBackground(background_color);
        this.setLayout(new FlowLayout());
        this.add(new JLabel("x = "));
        x.setEditable(false);
        x.setPreferredSize(new Dimension(60,24));
        this.add(x);
        this.add(new JLabel("y = "));
        y.setEditable(false);
        y.setPreferredSize(new Dimension(60,24));
        this.add(y);
        this.add(new JLabel("f1(x) = "));
        fx1.setEditable(false);
        fx1.setPreferredSize(new Dimension(60,24));
        this.add(fx1);
        this.add(new JLabel("f2(x) = "));
        fx2.setEditable(false);
        fx2.setPreferredSize(new Dimension(60,24));
        this.add(fx2);
    }

    public void setX(float x){
        this.x.setText(String.valueOf(x));
    }

    public void setY(float y){
        this.y.setText(String.valueOf(y));
    }

    public void setFX1(float fx){
        this.fx1.setText(String.valueOf(fx));
    }

    public void setFX2(float fx){
        this.fx2.setText(String.valueOf(fx));
    }

}