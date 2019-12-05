/**
 * Gestion des évènements de la souris sur le Panel d'affichage de la fonction.
 */
import java.awt.event.*;

public class GrapherAdapter extends MouseAdapter implements MouseMotionListener {

    protected Grapher grapher;
    protected PositionPanel position_panel;
    protected float x,y;    // Pour sauvegarder les positions initiales x et y du clique pour le drag

    public GrapherAdapter(Grapher g, PositionPanel pp){
        this.grapher = g;
        this.position_panel = pp;
    }

    //Quand on click, on passe en mode drag et on sauvegarde la position du clique
    public void mousePressed(MouseEvent e) {
        grapher.drag = true;
        x = e.getX();
        y = e.getY();
    }

    //Quand on relache le click, on sort du mode drag, on met à jour l'afficahge des paramètres
    public void mouseReleased(MouseEvent e){
        grapher.drag = false;
        grapher.reInitCoord();
        grapher.repaint();
    }

    //Quand la souris bouge, on calcul les coordonnées x et y sur le repère et on met à jour l'affichage des coordonnées
    public void mouseMoved(MouseEvent e){
        float x = (e.getX() - grapher.Ox) * grapher.rangeX;
        float y = (e.getY() - grapher.Oy) * grapher.rangeY;

        position_panel.setX(Math.round(1000*x) / 1000f);
        position_panel.setY(-Math.round(1000*y) / 1000f);
        if(grapher.evalIsOk1) position_panel.setFX1(Math.round(grapher.f(grapher.evaluateur1, x)*1000)/1000f);
        if(grapher.evalIsOk2) position_panel.setFX2(Math.round(grapher.f(grapher.evaluateur2, x)*1000)/1000f);
    }

    //Quand on fait un drag, on calcul les déplacements effectués sur les axes x et y (distances)
    //Puis, on on met à jour les paramètres en ajoutant la valeur du déplacement
    //Enfin, on met à jour les nouvelles valeurs de x et y et on actualise l'affichage
    public void mouseDragged(MouseEvent e){
        float dx = (e.getX() - x) * grapher.rangeX;
        float dy = (e.getY() - y) * grapher.rangeY;

        grapher.addMinX(-dx);
        grapher.addMaxX(-dx);
        grapher.addMinY(dy);
        grapher.addMaxY(dy);
        
        x = e.getX();
        y = e.getY();
        grapher.reInitCoord();
        grapher.repaint();

    }
    
}