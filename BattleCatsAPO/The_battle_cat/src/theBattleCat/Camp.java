/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theBattleCat;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lapor
 */
public class Camp {

    final List<Personnage> list;
    final boolean allier;
    private boolean endgame;

    public Camp(boolean allier) {
        this.list = new ArrayList<>();
        this.allier = allier;
        this.endgame = false;
    }

    public boolean isAllier() {
        return allier;
    }

    public void add(Personnage p) {
        list.add(p);
    }

    public void remove(Personnage p) {
        list.remove(p);
    }

    public List<Personnage> getList() {
        return list;
    }

    public boolean endgame() {
        /**le jeu se termine quand la base n'as plus de vie, soit quand il n'y a plus de base dans le camp ou que le camp est vide*/
        /**la base ne fait pas de degat et est le premier personnage du camp*/
        if (list.isEmpty() || list.get(0).getDamage()>0){
            endgame=true;
        }
        return endgame;
    }

    public void kills() {
        /**on parcours les personnages du camp et on les efface s'il n'ont plus de vie*/
        for (Personnage p : list) {
            if (p.getHealth() < 0) {
                p.getIv().setVisible(false);
                remove(p);
            }
        }
    }
}
