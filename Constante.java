public class Constante extends Noeud {

  public Constante(String val){
    this.value= val;
    this.fg = null;
    this.fd = null;
  }

  public float eval(float x){
    return Float.valueOf(this.value);
  }

}
