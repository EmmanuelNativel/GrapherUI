public class Additionner extends Noeud {


  public Additionner(Noeud fg, Noeud fd){
    this.value = "+";
    this.fg = fg;
    this.fd = fd;
  }

  public float eval(float x){
    return this.fg.eval(x) + this.fd.eval(x);
  }

}
