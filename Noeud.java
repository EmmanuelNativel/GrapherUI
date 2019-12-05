/**
 * Arbre
 */
public abstract class Noeud {

    String value;
    Noeud fg, fd;
    /*
    public Noeud(String val, Noeud fg, Noeud fd){
       this.value = val;
       this.fg = fd;
       this.fd = fd;
    }

    public Noeud(Noeud fg, Noeud fd){
        this.fg = fd;
        this.fd = fd;
    }

    public Noeud(String val){
        this.value = val;
        this.fg = null;
        this.fd = null;
    }

    public Noeud(char val){
        this.value = String.valueOf(val);
        this.fg = null;
        this.fd = null;
    } */
    
    public abstract float eval(float x);


}