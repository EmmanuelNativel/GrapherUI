/**
 * Pannel du bas : Entrée de la fonction + bouton d'évaluation de la fonction
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SpringLayout.Constraints;
import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;

public class EvalPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    protected Grapher grapher;
    protected JButton eval_btn1; //eval_btn2;
    protected JTextField txt_fonction1, txt_fonction2;
    protected Color background_color, bleu_fonce;

    
    public EvalPanel(Grapher g, Color background_color){
        this.grapher = g;
        this.background_color = background_color;
        //this.bleu_fonce = Color.BLUE;
        
        eval_btn1 = new JButton("Evaluer");
        eval_btn1.setForeground(Color.WHITE);
        txt_fonction1 = new JTextField();

        // eval_btn2 = new JButton("Eval2");
        // eval_btn2.setForeground(Color.WHITE);
        txt_fonction2 = new JTextField();

        this.setBackground(background_color);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel fonctions_input = new JPanel();
        fonctions_input.setLayout(new GridBagLayout());
        fonctions_input.setBackground(new Color(50,50,50));
        GridBagConstraints constraint = new GridBagConstraints();

        eval_btn1.setBackground(new Color(50,50,50));
        eval_btn1.setUI((ButtonUI)MetalButtonUI.createUI(eval_btn1)); //pour mettre la couleur de fond du bouton
        txt_fonction1.setPreferredSize(new Dimension(200,24));
        txt_fonction2.setPreferredSize(new Dimension(200,24));

        constraint.ipadx = 5;
        constraint.ipady = 5;
        constraint.gridx = 0;   constraint.gridy = 0;
        JLabel label = new JLabel("   f1(x) = ");
        label.setForeground(Color.WHITE);
        fonctions_input.add(label, constraint);
        constraint.gridx = 1;
        fonctions_input.add(txt_fonction1, constraint);

        constraint.gridx = 2;
        label = new JLabel("      f2(x) = ");
        label.setForeground(Color.WHITE);
        fonctions_input.add(label, constraint);
        constraint.gridx = 3;
        fonctions_input.add(txt_fonction2, constraint);

        this.add(fonctions_input);
        this.add(eval_btn1);

        ActionListener action_listener = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == eval_btn1) evalFonction();
            }
        };

        eval_btn1.addActionListener(action_listener);

    }

    // Fonction appelée quand on appui sur le bouton éval
    public void evalFonction(){
        String expression1 = this.txt_fonction1.getText();
        String expression2 = this.txt_fonction2.getText();
        
        if(!expression1.isEmpty()){
            this.grapher.evaluateur1.setExpression(expression1);
            this.grapher.evalIsOk1 = true;
            this.grapher.repaint();
        } else {
            this.grapher.evaluateur1.setExpression(expression1);
            this.grapher.evalIsOk1 = false;
            this.grapher.position_panel.setFX1(0f);
            this.grapher.repaint();
        }

        if(!expression2.isEmpty()){
            this.grapher.evaluateur2.setExpression(expression2);
            this.grapher.evalIsOk2 = true;
            this.grapher.repaint();
        } else {
            this.grapher.evaluateur2.setExpression(expression1);
            this.grapher.evalIsOk2 = false;
            this.grapher.position_panel.setFX2(0f);
            this.grapher.repaint();
        }

    }


}