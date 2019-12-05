public class Tangente extends Noeud {

    //protected boolean positive;
  
    public Tangente(Noeud f){
      this.value = "tan";
      this.fg = f;
      this.fd = null;
    }
  
    public float eval(float x){
        return (float) Math.tan(this.fg.eval(x));
    }
  
  }
  