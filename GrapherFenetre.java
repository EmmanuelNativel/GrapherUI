/**
 * Fenetre principale du logiciel
 * 
 * --> contient la fonction Main
 */

import javax.swing.*;
import java.awt.*;

public class GrapherFenetre extends JFrame{
    
    private static final long serialVersionUID = 1L;
    
    protected JPanel void_panel;
    protected PositionPanel position_panel;
    protected ActionPanel action_panel;
    protected EvalPanel eval_panel;
    protected Grapher grapher;
    protected Evaluateur evaluateur1, evaluateur2;

    protected Color background_color;


    public GrapherFenetre() {
        this.setTitle("Grapher");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1,2));
        Container content = this.getContentPane();
        content.setLayout(new BorderLayout());

        this.evaluateur1 = new Evaluateur();
        this.evaluateur2 = new Evaluateur();

        //Couleurs : 
        background_color = new Color(58,88,119); //bleu gris

        //Initialisation des éléments :
        position_panel = new PositionPanel(background_color);
        grapher = new Grapher(evaluateur1, evaluateur2, position_panel);
        action_panel = new ActionPanel(grapher, background_color);
        grapher.setActionPanel(action_panel);
        eval_panel = new EvalPanel(grapher, background_color);
        void_panel = new JPanel();

            //-- void_panel
        void_panel.setBackground(background_color);

        content.add(position_panel, BorderLayout.NORTH);
        content.add(action_panel, BorderLayout.WEST);
        content.add(eval_panel, BorderLayout.SOUTH);
        content.add(grapher, BorderLayout.CENTER);
        content.add(void_panel, BorderLayout.EAST);
        

        this.setVisible(true);  
    }

    public static void main(String[] args) {
        new GrapherFenetre();
    }
    
}