public class Multiplier extends Noeud {

  public Multiplier(Noeud fg, Noeud fd){
    this.value = "*";
    this.fg = fg;
    this.fd = fd;
  }

  public float eval(float x){
    return this.fg.eval(x) * this.fd.eval(x);
  }

}
