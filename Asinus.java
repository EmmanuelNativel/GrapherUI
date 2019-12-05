public class Asinus extends Noeud {
  
    public Asinus(Noeud f){
      this.value = "Asin";
      this.fg = f;
      this.fd = null;
    }
  
    public float eval(float x){
        return (float) Math.asin(this.fg.eval(x));
    }
  
  }
  