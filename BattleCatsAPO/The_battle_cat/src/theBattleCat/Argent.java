/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theBattleCat;

/**
 *
 * @author lapor
 */
public class Argent {

    private int argent;
    private int niveau;
    /**valeur limite de replissage de la besace*/
    private int limite;
    /**cout d'amelioration du niveau d'argent*/
    private int cost;
    /**le compteur gere la vitesse de croissance d'argent fonction de son niveau*/
    private int compteurArgent;

    public Argent(int argent, int niveau,int limite,int cost) {
        this.argent = argent;
        this.niveau = niveau;
        this.limite = limite;
        this.cost=cost;
        this.compteurArgent=0;
    }

    public int getArgent() {
        return argent;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public int getNiveau() {
        return niveau;
    }
    
    public int getCost(){
        return cost;
    }
    
    public void setCost(){
        /**pour ameliorer l'argent, il faut debourser 1/3 de besace*/
        cost=limite/3;
    }

    public String nomLab() {
        /**le texte qui est affiche pour l'argent*/
        return argent + "/" + limite + "£";
    }
    /**le joueur veut ameliorer le niveau d'argen*/
    public void levelUp(Boutons b) {
        if (niveau < 8 && argent>cost) {
            /**l'argent n'est pas au niveau max et la besace contient asse d'argent pour amélioration*/
            /**incrementation du niveau*/
            niveau=niveau+1;
            /**actualisation de la limite de la besace*/
            limite=1200 + 400*(niveau-1);
            /**actualisation de l'argent*/
            argent=argent-cost;
            /**actualisation du cout pour le niveau suivant*/
            setCost();
            if (niveau == 8){
                /**le niveau max est atteint on l'indique */
                b.setLabels("Level Max");
                /**le texte prend plus de place et depace du cadre, on le decale*/
                b.getLabels().getLabel().setTranslateX(b.getLabels().getLabel().getTranslateX()+20);
                b.setLabels(b.getLabels());
            }else{
                b.setLabels(getCost()+"£");
            }
        }
    }
    /**l'argent evolu tout au long de la partie*/
    public void grow(){
        /**on incremente le compteur*/
        compteurArgent++;
        if (compteurArgent>=8-niveau){
            /**après un nombre d'appel fonction du niveau d'argent on evolue sa quantite*/
            if (argent<limite){
                argent=argent+1;
            }else {
                /**l'argent ne peut depacer sa limite*/
                argent=limite;
            }
            /**reinitialisation du compteur*/
            compteurArgent=0;
        }
    }
}
