public class Sinus extends Noeud {

    //protected boolean positive;
  
    public Sinus(Noeud f){
      this.value = "sin";
      this.fg = f;
      this.fd = null;
    }
  
    public float eval(float x){
        return (float) Math.sin(this.fg.eval(x));
    }
  
  }
  