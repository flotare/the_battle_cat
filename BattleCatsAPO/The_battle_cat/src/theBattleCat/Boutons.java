/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theBattleCat;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author lapor
 */
public class Boutons {
    private Rectangle rectangle;
    private Circle cercle;
    private Labels label;
    /**position sur le pane*/
    final double positionXP;
    final double positionYP;
    /**image de fond du bouton*/
    final ImagePattern ip;

    /**pour le spawn de chats*/
    public Boutons(Rectangle rectangle,int cost, String image, double positionXP, double positionYP) {
        this.rectangle=rectangle;
        this.label= new Labels(new Label(cost+"£"),"Algerian",40,Color.YELLOW,positionXP+30,positionYP-60);
        this.positionXP = positionXP;
        this.positionYP = positionYP;
        this.ip = new ImagePattern(new Image(getClass().getResourceAsStream("/resources/" + image)));
    }

    public void setLabels(Labels label) {
        this.label = label;
    }

    public Labels getLabels() {
        return label;
    }
    /**pour l'argent*/
    public Boutons(Circle cercle,Argent a, String image, double positionXP, double positionYP) {
        this.cercle = cercle;
        this.label= new Labels(new Label(a.getCost()+"£"),"Algerian",40,Color.YELLOW,positionXP,positionYP+90);
        this.positionXP = positionXP;
        this.positionYP = positionYP;
        this.ip = new ImagePattern(new Image(getClass().getResourceAsStream("/resources/" + image)));
    }
    
    
    public void setBoutonR(StackPane sp){
        rectangle.setFill(ip);
        rectangle.setStroke(Color.BLACK);
        rectangle.setTranslateX(positionXP);
        rectangle.setTranslateY(positionYP);
        sp.getChildren().add(rectangle);
        label.setLabels(sp);
    }
    public void setBoutonC(StackPane sp){
        cercle.setFill(ip);
        cercle.setStroke(Color.BLACK);
        cercle.setTranslateX(positionXP);
        cercle.setTranslateY(positionYP);
        sp.getChildren().add(cercle);
        label.setLabels(sp);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Circle getCercle() {
        return cercle;
    }
    public void setLabels(String text){
        label.setLabels(text);
    }
}
