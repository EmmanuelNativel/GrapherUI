import java.util.*;

/**
 * Evaluateur
 * 
 * Evaluation de toute expression complexe à 1 variable contenant les fonctions suivantes :
 *      log, cos, sin, tan, Acos, Asin, Atan, sqrt (racine carrée)
 * 
 * --> Les nombres négatifs s'écrivent avec le token ~ (ex : -(x+2) s'écrit ~(x+2))
 * --> Les nombres décimaux peuvent s'écrire avec un '.' ou une ','
 */
public class Evaluateur {

    protected Noeud arbre;
    protected String expression, expression_postfixe;

    // Mise en forme de l'expression infixe pour qu'elle soit conforme aux prérequis de la fonction infixeToPostfixe
    public static String prepareExpression(String exp_infixe){
        // Suppression de tous les caractères blancs
        exp_infixe = exp_infixe.replaceAll("\\s", "");
        // Remplacement des virgules par des points
        exp_infixe = exp_infixe.replace(",", ".");
        // Séparation de chaque caractère par un espace
        exp_infixe = exp_infixe.replace("", " ").trim();
        // Supression des espaces autour des '.' pour garder les nombres décimaux
        exp_infixe = exp_infixe.replace(" . ", ".");

        exp_infixe = exp_infixe.replace("A c o s", "Acos");
        exp_infixe = exp_infixe.replace("A s i n", "Asin");
        exp_infixe = exp_infixe.replace("A t a n", "Atan");
        exp_infixe = exp_infixe.replace("c o s", "cos");
        exp_infixe = exp_infixe.replace("s i n", "sin");
        exp_infixe = exp_infixe.replace("t a n", "tan");
        exp_infixe = exp_infixe.replace("l o g", "log");
        exp_infixe = exp_infixe.replace("s q r t", "sqrt");

        //exp_infixe = exp_infixe.replaceAll("((Acos|Asin|Atan) (\\((.*?)\\)))", "( $0 ) ");

        // Encapsulation des fonctions (ex : cos(x) devient (cos(x)) ) --> Pour gérer la négation ~cos(x) par exemple
        // expression régulière du type : x (...)  avec x un nombre indéfini de lettres et n'importe quoi à la place des ...
        // capture de ce qui est entre parenthèses et récupération de cette capture grace à $0
        exp_infixe = exp_infixe.replaceAll("(([a-zA-Z]*) (\\((.*?)\\)))", "( $0 ) ");

        return exp_infixe;
    }

    // Retourne true si c est un opérateur, false sinon.
    public static boolean isOperator(String c) {
        return (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") || c.equals("^") 
                || c.equals("cos") || c.equals("sin") || c.equals("tan") || c.equals("~") || c.equals("sqrt")
                || c.equals("Acos") || c.equals("Asin") || c.equals("Atan") || c.equals("log") );
    }

    // Retourne la priorité des opérations
    public static int priorite(String operateur) {
        switch (operateur) {
            case "~" :
                return 4;
            case "^" :
            case "cos" :
            case "sin" :
            case "tan" :
            case "Acos" :
            case "Asin" :
            case "Atan" :
            case "log" :
            case "sqrt" :
                return 3;
            case "*" :
            case "/" :
                return 1;
            case "+" :
            case "-" :     
                return 0;
            default :
                return -1;
        }
    }


    // Prend en paramètre une expression en notation postfixe où chaque élément est séparé par un espace
    // et renvoit la forme postfixée de cette expression en séparant les éléments par des espaces egalement.
    public static String infixeToPostfixe(String infix) {
 
        String res = ""; //chaine résultat
        Deque<String> pile = new ArrayDeque<String>();
        
        // On découpe la liste avec le séparateur blanc et on parcourt les élements
        for (String token : infix.split("\\s")) {

            // On ignore les espaces si il en reste
            if (token.isEmpty()) continue;
 
            // Si c'est un opérateur
            if (isOperator(token)) {
                if (pile.isEmpty()) pile.addFirst(token);
 
                else {
                    while (!pile.isEmpty()) {
                        int priorite2 = priorite(pile.peekFirst()); // priorité de l'opérateur en tête de la pile
                        int priorite1 = priorite(token);    // priorité de l'opérateur courant          
                        if (priorite2 > priorite1 || (priorite2 == priorite1 && token.equals("^")))
                            res += pile.removeFirst()+" ";
                        else break;
                    }
                    pile.addFirst(token);
                }
            }
            
            // Si c'est une parenthèse ouverte
            else if (token.equals("(")) {
                pile.addFirst("(");
            } 

            // Si c'est une parenthèse fermée
            else if (token.equals(")")) {
                // On ajoute le premier élément de la pile (un opérateur) à la chaine jusqu'à ce qu'on trouve '('
                while (pile.peekFirst() != "(")
                    res += pile.removeFirst()+" ";
                pile.removeFirst();
            }

            // Opérande
            else {
                res += token+" ";
            }
        }

        // On ajoute le contenu restant dans la pile dans la chaine de caractères
        while (!pile.isEmpty())
            res += pile.removeFirst()+" ";

        return res;
    }
    

    // Retourne la racine de l'arbre construit à partir de l'expression postfixée donnée
    public static Noeud buildTree(String postfix) {
        Deque<Noeud> pile = new ArrayDeque<Noeud>();
        Noeud noeud = null, f1 = null, f2 = null; 
  
        // Parcours de l'expression postfixée caractère par caractère
        for (String token : postfix.split(" ")) {

            // On ignore les espaces (si jamais il en reste)
            if(token.equals(" ")) continue;

            // Si c'est une opérande, on l'ajoute à la pile --> Variable OU constante
            else if (!isOperator(token)) {
                if(token.equals("x")) noeud = new Variable();
                else noeud = new Constante(token); 
                pile.addFirst(noeud); 
            }

            //Opérateurs
            else { 

                // Retirer les 2 premiers éléments de la pile et les stocker
                f2 = pile.removeFirst();
                if(!pile.isEmpty()) f1 = pile.removeFirst(); 


                switch (token) {
                    case "+":
                        noeud = new Additionner(f1, f2);
                        break;
                    case "-":
                        noeud = new Soustraire(f1, f2);
                        break;
                    case "*":
                        noeud = new Multiplier(f1, f2);
                        break;
                    case "/":
                        noeud = new Diviser(f1, f2);
                        break;
                    case "^":
                        noeud = new Puissance(f1, f2);
                        break;
                    case "cos":
                        noeud = new Cosinus(f2);
                        break;
                    case "sin":
                        noeud = new Sinus(f2);
                        break;
                    case "tan":
                        noeud = new Tangente(f2);
                        break;
                    case "Acos":
                        noeud = new Acosinus(f2);
                        break;
                    case "Asin":
                        noeud = new Asinus(f2);
                        break;
                    case "Atan":
                        noeud = new Atangente(f2);
                        break;
                    case "log":
                        noeud = new Log(f2);
                        break;
                    case "sqrt":
                        noeud = new RacineCarre(f2);
                        break;
                    case "~":
                        noeud = new Negation(f2);
                        break;
                }
  
                // Ajouter le sous-arbre à la pile
                pile.addFirst(noeud); 
            }
        }

        // On fait pointer noeud sur l'élément tout en haut de la pile, sans le retirer de la pile. 
        // noeud contient donc la racine de l'arbre
        noeud = pile.peekFirst(); 
        pile.removeFirst();
  
        return noeud; 
    }

    //Lancement de l'évaluation de l'équation
    public float eval(float x){
        return this.arbre.eval(x);
    }


    public void setExpression(String exp){
        this.expression = exp;
        this.expression = prepareExpression(this.expression);   //préparation de l'expression
        this.expression_postfixe = infixeToPostfixe(this.expression);   // Convertion en notation postfixée
        this.arbre = buildTree(this.expression_postfixe);   // Création de l'arbre
    }
    

    /*
    public static void main(String[] args) {

        Evaluateur e = new Evaluateur();

        String infix = "~sin(x)";

        e.setExpression(infix);
        System.out.printf("infixe : %s%n", e.expression);
        System.out.printf("postfix : %s%n", e.expression_postfixe);
        
        float r = e.eval(1);
        System.out.println("resultat ==> " + r); 
        
        
    }
    */ 

}