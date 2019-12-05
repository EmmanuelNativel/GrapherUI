public class Variable extends Noeud {

  //protected boolean positif;
  /*
  public Variable(boolean b){
    this.positif = b;
  }*/

  public Variable(){
    this.value = "x";
    this.fg = null;
    this.fd = null;
  }

  public float eval(float x){
    return x;
  }

}
