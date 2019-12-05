/**
 * Panel central : dessin de la fonction.
 */

import java.awt.*;
import javax.swing.*;

public class Grapher extends JPanel {
  
  private static final long serialVersionUID = 1L;

  protected float minX = -10;
  protected float maxX = 10;
  protected float minY = -10;
  protected float maxY = 10;
  protected float pas = 0.01f;
  protected float rangeX = 0;
  protected float rangeY  = 0;
  protected float Ox = 0;
  protected float Oy = 0;
  protected float gridX = 1.0f;
  protected float gridY = 1.0f;

  protected boolean auto_pas = false;
  protected boolean drag = false;
  boolean evalIsOk1 = false;
  boolean evalIsOk2 = false;

  protected ActionPanel action_panel;
  protected PositionPanel position_panel;
  protected Evaluateur evaluateur1, evaluateur2;

  protected Color fct_color1, fct_color2;


  public Grapher(Evaluateur e1, Evaluateur e2, PositionPanel position_panel){
    this.position_panel = position_panel;
    this.evaluateur1 = e1;
    this.evaluateur2 = e2;
    this.fct_color1 = new Color(114,181,217);
    this.fct_color2 = new Color(114,181,217);
    GrapherAdapter grapher_adapter = new GrapherAdapter(this, position_panel);
    this.addMouseListener(grapher_adapter);
    this.addMouseMotionListener(grapher_adapter);
  }

  public void paint(Graphics g){
    //** INITIALISATION 
        //*** Rapport taille graphe / taille écran
    if(!drag){
      int w = getSize().width;
      rangeX = (maxX - minX) / w;
      int h = getSize().height;
      rangeY = (maxY - minY) / h;
    }

        //*** Initialisation de l'origine du repère
    Ox = -minX / rangeX;
    Oy = maxY / rangeY;

    //** DESSIN DES AXES ET DE LA FONCTION
    drawAxes(g);
    if(evalIsOk1) drawFonc(g, evaluateur1, fct_color1);
    if(evalIsOk2) drawFonc(g, evaluateur2, fct_color2);
  }

  // Retourne la valeur de f(x)
  public float f(Evaluateur evaluateur, float x){
    return evaluateur.eval(x);
  }

  // Fonction appelée lors de l'appui sur le bouton refresh
  public void onRefresh(){

    this.minX = Float.valueOf(action_panel.txt_x_min.getText());
    this.maxX = Float.valueOf(action_panel.txt_x_max.getText());
    this.minY = Float.valueOf(action_panel.txt_y_min.getText());
    this.maxY = Float.valueOf(action_panel.txt_y_max.getText());
    this.gridX = Float.valueOf(action_panel.txt_x_grid.getText());
    this.gridY = Float.valueOf(action_panel.txt_y_grid.getText());
    this.pas = Float.valueOf(action_panel.txt_pas.getText());
    this.auto_pas = false;
    this.action_panel.check_auto_pas.setSelected(false);

    repaint();
}

  // Dessine la fonction dans le repère
  public void drawFonc(Graphics g, Evaluateur evaluateur, Color color){
    g.setColor(color);
    if(pas == 0f) pas = 0.01f;

    for(float x = minX; x<maxX;){
      float y = f(evaluateur, x);
      
      float xi = Ox + (x/rangeX);
      float yi = Oy - y / rangeY;

      g.drawOval(Math.round(xi), Math.round(yi), 1, 1);
      x += pas;
      
    }
  }

  // Dessine les axes du repère
  public void drawAxes(Graphics g){

    int taille = 5;
    float taille_axes = (getSize().height/rangeX) + Ox;

    // Dessin des axes x et y
    g.drawLine(Math.round(-taille_axes), Math.round(Oy), Math.round(taille_axes), Math.round(Oy));
    g.drawLine(Math.round(Ox), Math.round(taille_axes), Math.round(Ox), Math.round(-taille_axes));

    //Dessin des graduations
    //x droite
    for(float x = gridX; x<maxX;){
      float xi = (x/rangeX) + Ox;
      float yi = Oy;
      g.drawLine(Math.round(xi), Math.round(yi), Math.round(xi), Math.round(yi) - taille);
      x += gridX;
    }
    //x gauche
    for(float x = -gridX; x>minX;){
      float xi = (x/rangeX) + Ox;
      float yi = Oy;
      g.drawLine(Math.round(xi), Math.round(yi), Math.round(xi), Math.round(yi) - taille);
      x -= gridX;
    }
    
    //y bas
    for(float y = -gridY; y>minY;){
      float yi = -(y/rangeY) + Oy;
      float xi = Ox;
      g.drawLine(Math.round(xi), Math.round(yi), Math.round(xi) + taille, Math.round(yi));
      y -= gridY;
    }
    //y haut
    for(float y = gridY; y<maxY;){
      float yi = -(y/rangeY) + Oy;
      float xi = Ox;
      g.drawLine(Math.round(xi), Math.round(yi), Math.round(xi) + taille, Math.round(yi));
      y += gridY;
    }
  }

  // Maj des valeurs des paramètres de ActionPanel
  public void reInitCoord(){
    action_panel.txt_x_min.setText(String.valueOf(Math.round(minX * 10000)/10000f));
    action_panel.txt_y_min.setText(String.valueOf(Math.round(minY * 10000)/10000f));
    action_panel.txt_x_max.setText(String.valueOf(Math.round(maxX * 10000)/10000f));
    action_panel.txt_y_max.setText(String.valueOf(Math.round(maxY * 10000)/10000f));
    action_panel.txt_x_grid.setText(String.valueOf(gridX));
    action_panel.txt_y_grid.setText(String.valueOf(gridY));
    action_panel.txt_pas.setText(String.valueOf(Math.round(pas * 10000) / 10000f));
  }

  // Fonction appelée lors de l'appui sur le bouton zoom moins
  public void zoomMoins(){
    float dx = (maxX - minX) / 5f;
    float dy = (maxY - minY) / 5f;
    minX = minX - dx;
    maxX = maxX + dx;
    minY = minY - dy;
    maxY = maxY + dy;
    reInitCoord();
    if(auto_pas){
      pas = rangeX / 3f;
      action_panel.txt_pas.setText(String.valueOf(Math.round(pas * 10000) / 10000f));
    }
    repaint();
  }

  // Fonction appelée lors de l'appui sur le bouton zoom plus
  public void zoomPlus(){
    float dx = -(maxX - minX) / 5f;
    float dy = -(maxY - minY) / 5f;
    minX = minX - dx;
    maxX = maxX + dx;
    minY = minY - dy;
    maxY = maxY + dy;
    reInitCoord();
    if(auto_pas){
      pas = rangeX / 3f;
      action_panel.txt_pas.setText(String.valueOf(Math.round(pas * 10000) / 10000f));
    }
    repaint();
  }

  // Fonction appelée lorsque l'option auto pas est sélectionnée
  public void setAutoPas(boolean b){
    auto_pas = b;
    if(auto_pas){
      pas = rangeX / 3f;
      action_panel.txt_pas.setText(String.valueOf(Math.round(pas * 10000) / 10000f));
      repaint();
    }
  }

  // Changement de la couleur de la première fonction
  public void changeFunctionColor1(){
    String couleur = (String) action_panel.color_list1.getSelectedItem();
    switch (couleur) {
      case "bleu":
        fct_color1 = Color.BLUE;
        repaint();
        break;
      case "rouge":
        fct_color1 = Color.RED;
        repaint();
        break;
      case "vert":
        fct_color1 = Color.GREEN;
        repaint();
        break;
      case "noir":
        fct_color1 = Color.BLACK;
        repaint();
        break;
    }
  }

  // Changement de la couleur de la seconde fonction
  public void changeFunctionColor2(){
    String couleur = (String) action_panel.color_list2.getSelectedItem();
    switch (couleur) {
      case "bleu":
        fct_color2 = Color.BLUE;
        repaint();
        break;
      case "rouge":
        fct_color2 = Color.RED;
        repaint();
        break;
      case "vert":
        fct_color2 = Color.GREEN;
        repaint();
        break;
      case "noir":
        fct_color2 = Color.BLACK;
        repaint();
        break;
    }
  }

  // Modification de minX
  public void addMinX(float f){
    this.minX += f;
  }

  // Modification de maxX
  public void addMaxX(float f){
    this.maxX += f;
  }

  // Modification de minY
  public void addMinY(float f){
    this.minY += f;
  }

  // Modification de maxY
  public void addMaxY(float f){
    this.maxY += f;
  }
  
  public void setActionPanel(ActionPanel ap){
    this.action_panel = ap;
  }

}
