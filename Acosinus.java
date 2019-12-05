public class Acosinus extends Noeud {
  
    public Acosinus(Noeud f){
      this.value = "Acos";
      this.fg = f;
      this.fd = null;
    }
  
    public float eval(float x){
        return (float) Math.acos(this.fg.eval(x));
    }
  
  }
  