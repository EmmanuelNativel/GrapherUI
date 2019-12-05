public class Negation extends Noeud {

    //protected boolean positive;
  
    public Negation(Noeud f){
      this.value = "~";
      this.fg = f;
      this.fd = null;
    }
  
    public float eval(float x){
        return -this.fg.eval(x);
    }
  
  }
  