public class Cosinus extends Noeud {

    //protected boolean positive;
  
    public Cosinus(Noeud f){
      this.value = "cos";
      this.fg = f;
      this.fd = null;
    }
  
    public float eval(float x){
        return (float) Math.cos(this.fg.eval(x));
    }
  
  }
  