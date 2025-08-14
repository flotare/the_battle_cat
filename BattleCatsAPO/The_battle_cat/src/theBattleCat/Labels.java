/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theBattleCat;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author lapor
 */
public class Labels {
    private Label label;
    final String style;
    final int size;
    final Color couleur;
    /**position sur le pane*/
    private double positionXP;
    final double positionYP;

    public Labels(Label label, String style, int size, Color couleur, double positionXP, double positionYP) {
        this.label = label;
        this.style = style;
        this.size = size;
        this.couleur = couleur;
        this.positionXP = positionXP;
        this.positionYP = positionYP;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public double getPositionXP() {
        return positionXP;
    }

    public void setPositionXP(double positionXP) {
        this.positionXP = positionXP;
    }
    
    public void setLabels(StackPane sp){
        label.setFont(new Font(style, size));
        label.setTranslateX(positionXP);
        label.setTranslateY(positionYP);
        label.setTextFill(couleur);
        sp.getChildren().add(label);
    }
    public void setLabels(String text){
        label.setText(text);
    }
}
