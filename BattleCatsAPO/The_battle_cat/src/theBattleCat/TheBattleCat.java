/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package theBattleCat;

import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TheBattleCat extends Application {

    private static Camp campAllier;
    private static Camp campEnemie;
    /**strategie: on invoque un chien, puis serpent et enfin humains*/
    private int compteurStrat;
    /**le bosse ne peut apparaitre qu'une fois*/
    private boolean flagBoss;

    public TheBattleCat() {
        campAllier = new Camp(true);
        campEnemie = new Camp(false);
        compteurStrat = 0;
        flagBoss = false;
    }

    public Camp getCampAllier() {
        return campAllier;
    }

    public Camp getCampEnemie() {
        return campEnemie;
    }

    public ImageView spawn(Personnage nom, Camp c) {
        /**on ajoute le personnage au camp*/
        c.add(nom);
        /**renvoie l'imageview du personnage*/
        return nom.getIv();
    }

    public void spawnableChien(Personnage nom, Camp c, Argent argent, StackPane sp) {
        /**on teste si on a asse d'argent dans notre besace pour spawn le personnage*/
        if (argent.getArgent() >= nom.getCost()) {
            /**on actualise l'argent*/
            argent.setArgent(argent.getArgent() - nom.getCost());
            /**on fait spawn le personnage*/
            sp.getChildren().add(spawn(new Chien(), c));
            /**pour le camp enemie on evolue la strategie*/
            compteurStrat++;
        }
    }

    public void spawnableSnake(Personnage nom, Camp c, Argent argent, StackPane sp) {
        if (argent.getArgent() >= nom.getCost()) {
            argent.setArgent(argent.getArgent() - nom.getCost());
            sp.getChildren().add(spawn(new Snake(), c));
            compteurStrat++;
        }
    }

    public void spawnableHumans(Personnage nom, Camp c, Argent argent, StackPane sp) {
        if (argent.getArgent() >= nom.getCost()) {
            argent.setArgent(argent.getArgent() - nom.getCost());
            sp.getChildren().add(spawn(new Humans(), c));
            compteurStrat = 0;
        }
    }

    public void spawnableBoss(Camp c, StackPane sp) {
        /**le boss apparait avec un effet de knockback*/
        /**on recule les personnages allies de 200*/
        for (int i = 1; i < campAllier.getList().size(); i++) {
            campAllier.getList().get(i).getIv().setTranslateX(campAllier.getList().get(i).getIv().getTranslateX() + 200);
        }
        /**on fait apparaitre le boss*/
        sp.getChildren().add(spawn(new BossCochon(), c));
        /**on retient que le boss est apparu*/
        flagBoss = true;
    }

    public void spawnableChat(Personnage nom, Camp c, Argent argent, StackPane sp) {
        if (argent.getArgent() >= nom.getCost()) {
            argent.setArgent(argent.getArgent() - nom.getCost());
            sp.getChildren().add(spawn(new Chat(), c));
        }
    }

    public void spawnableTankCat(Personnage nom, Camp c, Argent argent, StackPane sp) {
        if (argent.getArgent() >= nom.getCost()) {
            argent.setArgent(argent.getArgent() - nom.getCost());
            sp.getChildren().add(spawn(new TankCat(), c));
        }
    }

    public void spawnableChatVache(Personnage nom, Camp c, Argent argent, StackPane sp) {
        if (argent.getArgent() >= nom.getCost()) {
            argent.setArgent(argent.getArgent() - nom.getCost());
            sp.getChildren().add(spawn(new ChatVache(), c));
        }
    }

    public void spawnableBirdCat(Personnage nom, Camp c, Argent argent, StackPane sp) {
        if (argent.getArgent() >= nom.getCost()) {
            argent.setArgent(argent.getArgent() - nom.getCost());
            sp.getChildren().add(spawn(new BirdCat(), c));
        }
    }

    public void spawnableLizardCat(Personnage nom, Camp c, Argent argent, StackPane sp) {
        if (argent.getArgent() >= nom.getCost()) {
            argent.setArgent(argent.getArgent() - nom.getCost());
            sp.getChildren().add(spawn(new LizardCat(), c));
        }
    }

    public void animations(Camp cA, Camp cE) {
        /**On tue les personnages morts*/
        cA.kills();
        cE.kills();
        /**On anime les personnages vivants*/
        for (Personnage pA : cA.getList()) {
            /**s'il peut attaquer, il attaque sinon il se deplace*/
            if (pA.outOfRange(cE)) {
                pA.animation();
            } else {
                pA.attaque(cE);

            }
        }
        /**de meme pour le camp enemie*/
        for (Personnage pE : cE.getList()) {
            if (pE.outOfRange(cA)) {
                pE.animation();
            } else {
                pE.attaque(cA);
            }
        }
    }

    public void stratE(Argent argent, StackPane sp, Camp c) {
        /**le boss spawn si la vie de la tour ennemie voit sa vie reduite de moitie*/
        if (c.getList().get(0).getHealth() < 50000 && flagBoss == false) {
            spawnableBoss(campEnemie, sp);
        }
        if (compteurStrat == 0) {
            spawnableChien(new Chien(), campEnemie, argent, sp);
        }
        if (compteurStrat == 1) {
            spawnableSnake(new Snake(), campEnemie, argent, sp);
        } else {
            spawnableHumans(new Humans(), campEnemie, argent, sp);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
