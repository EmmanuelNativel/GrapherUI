/**
 * Panel de droite contenant tous les paramètres et boutons pour gérer l'affichage de la courbe.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ActionPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    protected static final String[] COLORS = {"bleu", "noir", "rouge", "vert"};

    protected Grapher grapher;
    
    protected JTextField txt_x_min, txt_x_max, txt_y_min, txt_y_max, txt_pas, txt_x_grid, txt_y_grid;
    protected JButton refresh_btn, zoom_plus_btn, zoom_moins_btn;
    protected JCheckBox check_auto_pas;
    protected JComboBox<String> color_list1, color_list2;

    protected Color background_color;

    public ActionPanel(Grapher g, Color background_color){
        this.grapher = g;
        this.background_color = background_color;

        txt_x_min = new JTextField(String.valueOf(grapher.minX));
        txt_x_max = new JTextField(String.valueOf(grapher.maxX));
        txt_y_min = new JTextField(String.valueOf(grapher.minY));
        txt_y_max = new JTextField(String.valueOf(grapher.maxY));
        txt_pas = new JTextField(String.valueOf(grapher.pas));
        txt_x_grid = new JTextField(String.valueOf(grapher.gridX));
        txt_y_grid = new JTextField(String.valueOf(grapher.gridY));
        refresh_btn = new JButton("Refresh");
        zoom_plus_btn = new JButton("+");
        zoom_moins_btn = new JButton("-");
        check_auto_pas = new JCheckBox();
        color_list1 = new JComboBox<String>(COLORS);
        color_list2 = new JComboBox<String>(COLORS);

        this.setBackground(background_color);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel menu_panel = new JPanel();
        menu_panel.setLayout(new GridBagLayout());
        menu_panel.setBackground(null);
        GridBagConstraints constraint = new GridBagConstraints();
        
        constraint.gridx = 0;   constraint.gridy = 0;   constraint.fill = GridBagConstraints.NONE;
        menu_panel.add(new JLabel("X min "), constraint);
        constraint.gridx = 1;   constraint.fill = GridBagConstraints.HORIZONTAL;
        menu_panel.add(txt_x_min, constraint);
        constraint.gridx = 0;   constraint.gridy = 1;   constraint.fill = GridBagConstraints.NONE;
        menu_panel.add(new JLabel("X max "), constraint);
        constraint.gridx = 1;   constraint.fill = GridBagConstraints.HORIZONTAL;
        menu_panel.add(txt_x_max, constraint);
        constraint.gridx = 0;   constraint.gridy = 2;   constraint.fill = GridBagConstraints.NONE;
        menu_panel.add(new JLabel("Y min "), constraint);
        constraint.gridx = 1;   constraint.fill = GridBagConstraints.HORIZONTAL;
        menu_panel.add(txt_y_min, constraint);
        constraint.gridx = 0;   constraint.gridy = 3;   constraint.fill = GridBagConstraints.NONE;
        menu_panel.add(new JLabel("Y max "), constraint);
        constraint.gridx = 1;   constraint.fill = GridBagConstraints.HORIZONTAL;
        menu_panel.add(txt_y_max, constraint);
        constraint.gridx = 0;   constraint.gridy = 4;   constraint.fill = GridBagConstraints.NONE;
        menu_panel.add(new JLabel("Pas "), constraint);
        constraint.gridx = 1;   constraint.fill = GridBagConstraints.HORIZONTAL;
        menu_panel.add(txt_pas, constraint);
        constraint.gridx = 0;   constraint.gridy = 5;   constraint.fill = GridBagConstraints.NONE;
        menu_panel.add(new JLabel("X grid "), constraint);
        constraint.gridx = 1;   constraint.fill = GridBagConstraints.HORIZONTAL;
        menu_panel.add(txt_x_grid, constraint);
        constraint.gridx = 0;   constraint.gridy = 6;   constraint.fill = GridBagConstraints.NONE;
        menu_panel.add(new JLabel("Y grid "), constraint);
        constraint.gridx = 1;   constraint.fill = GridBagConstraints.HORIZONTAL;
        menu_panel.add(txt_y_grid, constraint);
        constraint.gridx = 0;   constraint.gridy = 7;   constraint.gridwidth = 2;
        menu_panel.add(refresh_btn, constraint);
        constraint.gridx = 0;   constraint.gridy = 8;   constraint.gridwidth= 1;
        menu_panel.add(zoom_moins_btn, constraint);
        constraint.gridx = 1;
        menu_panel.add(zoom_plus_btn, constraint);

        constraint.gridx = 0;   constraint.gridy = 9;   constraint.fill = GridBagConstraints.NONE;
        menu_panel.add(new JLabel("Couleur1 : "), constraint);
        constraint.gridx = 1;   constraint.gridy = 9;   constraint.fill = GridBagConstraints.HORIZONTAL;
        menu_panel.add(color_list1, constraint);

        constraint.gridx = 0;   constraint.gridy = 10;   constraint.fill = GridBagConstraints.NONE;
        menu_panel.add(new JLabel("Couleur2 : "), constraint);
        constraint.gridx = 1;   constraint.gridy = 10;   constraint.fill = GridBagConstraints.HORIZONTAL;
        menu_panel.add(color_list2, constraint);

        constraint.gridx = 0;   constraint.gridy = 11;   constraint.fill = GridBagConstraints.NONE;
        constraint.anchor = GridBagConstraints.EAST;    
        menu_panel.add(check_auto_pas, constraint);
        constraint.gridx = 1;   constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.anchor = GridBagConstraints.WEST; 
        menu_panel.add(new JLabel("AutoPas"), constraint);

        this.add(Box.createVerticalGlue());
        this.add(menu_panel);
        this.add(Box.createVerticalGlue());

        ActionListener action_listener = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == zoom_moins_btn) grapher.zoomMoins();
                if(e.getSource() == zoom_plus_btn) grapher.zoomPlus();
                if(e.getSource() == refresh_btn) grapher.onRefresh();
                if(e.getSource() == check_auto_pas) grapher.setAutoPas(!grapher.auto_pas);
                if(e.getSource() == color_list1) grapher.changeFunctionColor1();
                if(e.getSource() == color_list2) grapher.changeFunctionColor2();
            }
        };

        zoom_moins_btn.addActionListener(action_listener);
        zoom_plus_btn.addActionListener(action_listener);
        refresh_btn.addActionListener(action_listener);
        check_auto_pas.addActionListener(action_listener);
        color_list1.addActionListener(action_listener);
        color_list2.addActionListener(action_listener);

    }
}