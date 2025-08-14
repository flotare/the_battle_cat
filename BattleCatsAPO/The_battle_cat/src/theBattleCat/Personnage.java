package theBattleCat;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author lapor
 */
public class Personnage {

    /** atribut*/
    final int level;
    private int health;
    final int damage;
    final int range;
    final int kb;
    final double speed;
    final int dps;
    final boolean zone;
    final double timeBetweenAttacks;
    final double attackAnim;
    final double respawnTime;
    final int cost;
    final String image;
    final boolean allier;
    final ImageView iv;

    public Personnage(int level, int health, int damage, int range, int kb, double speed, int dps, boolean zone,
            double timeBetweenAttacks, double attackAnim, double respawnTime, int cost, String image, boolean allier, double scaleXP, double scaleYP, double positionXP, double positionYP) {
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.range = range;
        this.kb = kb;
        this.speed = speed;
        this.dps = dps;
        this.zone = zone;
        this.timeBetweenAttacks = timeBetweenAttacks;
        this.attackAnim = attackAnim;
        this.respawnTime = respawnTime;
        this.cost = cost;
        this.image = image;
        this.allier = allier;
        this.iv = new ImageView(new Image(getClass().getResourceAsStream("/resources/" + image)));
        /** taille du personnage*/
        iv.setScaleX(scaleXP);
        iv.setScaleY(scaleYP);
        /** position du personnage */
        iv.setTranslateY(positionYP);
        iv.setTranslateX(positionXP);

    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public int getKb() {
        return kb;
    }

    public double getSpeed() {
        return speed;
    }

    public int getDps() {
        return dps;
    }

    public boolean isZone() {
        return zone;
    }

    public double getTime_between_attacks() {
        return timeBetweenAttacks;
    }

    public double getAttack_anim() {
        return attackAnim;
    }

    public double getRespawn_time() {
        return respawnTime;
    }

    public int getCost() {
        return cost;
    }

    public String getImage() {
        return image;
    }

    public boolean isAllier() {
        return allier;
    }

    public ImageView getIv() {
        return iv;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void animation() {
        /**on deplace le personnage vers la gauche s'il est allie et droite pour un ennemie*/
        if (allier) {
            iv.setTranslateX(iv.getTranslateX() - speed);
        } else {
            iv.setTranslateX(iv.getTranslateX() + speed);
        }
    }

    public void attaque(Personnage p) {
        /**le personnage baisse la vie d'un autre personnage*/
        p.setHealth(p.getHealth() - damage);
    }

    public boolean outOfRange(Personnage p) {
        /**un personnage p1 n'est pas attaquable s'il se situe derriere notre personnage p2 a 0.5 pres ou s'il est au dela de notre range*/
        if (allier) {
            if (iv.getTranslateX() < p.getIv().getTranslateX() - 0.5 || iv.getTranslateX() > p.getIv().getTranslateX() + range) {
                return true;
            }
        } else {
            if (iv.getTranslateX() < p.getIv().getTranslateX() - range || iv.getTranslateX() > p.getIv().getTranslateX() + 0.5) {
                return true;
            }
        }
        return false;
    }

    public boolean outOfRange(Camp c) {
        /**un personnage avance tant qu'il est outOfRange*/
        /**on initialise la parametre en consequence*/
        boolean outOfRange = true;
        /**puis on teste chaques personnages du camp adverse s'ils sont entre dans la range*/
        for (Personnage p : c.getList()) {
            if (outOfRange(p) == false) {
                outOfRange = false;
            }
        }
        return outOfRange;
    }

    public void attaque(Camp c) {
        /**On initialise la cible*/
        Personnage cible = c.getList().get(0);
        /**On cherche la cible en tête de horde parmis les cibles potentielles*/
        for (Personnage p : c.getList()) {
            if (outOfRange(p) == false) {
                if (allier && p.getIv().getTranslateX() > cible.getIv().getTranslateX() || allier == false && p.getIv().getTranslateX() < cible.getIv().getTranslateX()) {
                    cible = p;
                }
            }
        }
        /**On attaque la ou les cibles*/
        if (zone) {
            for (Personnage p : c.getList()) {
                if (allier && p.getIv().getTranslateX() > cible.getIv().getTranslateX() - 20 || allier == false && p.getIv().getTranslateX() < cible.getIv().getTranslateX() + 20) {
                    attaque(p);
                }
            }
        } else {
            attaque(cible);
        }
    }
}
