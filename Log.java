public class Log extends Noeud {
  
    public Log(Noeud f){
      this.value = "log";
      this.fg = f;
      this.fd = null;
    }
  
    public float eval(float x){
        return (float) Math.log(this.fg.eval(x));
    }
  
  }
  