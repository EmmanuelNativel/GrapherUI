public class RacineCarre extends Noeud {
  
    public RacineCarre(Noeud f){
      this.value = "sqrt";
      this.fg = f;
      this.fd = null;
    }
  
    public float eval(float x){
        return (float) Math.sqrt(this.fg.eval(x));
    }
  
  }
  