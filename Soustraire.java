public class Soustraire extends Noeud {

  public Soustraire(Noeud fg, Noeud fd){
    this.value = "-";
    this.fg = fg;
    this.fd = fd;
  }

  public float eval(float x){
    return this.fg.eval(x) - this.fd.eval(x);
  }

}
