/**
 * Puissance
 */
public class Puissance extends Noeud {

    public Puissance(Noeud fg, Noeud fd){
        this.value = "^";
        this.fg = fg;
        this.fd = fd;
      }
    
    public float eval(float x){
        return (float) Math.pow( this.fg.eval(x), this.fd.eval(x));
    }

    
}