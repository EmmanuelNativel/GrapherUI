public class Atangente extends Noeud {
  
    public Atangente(Noeud f){
      this.value = "Atan";
      this.fg = f;
      this.fd = null;
    }
  
    public float eval(float x){
        return (float) Math.atan(this.fg.eval(x));
    }
  
  }
  