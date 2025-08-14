/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theBattleCat;

import java.io.File;
import javafx.animation.AnimationTimer;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author lapor
 */
public class Main extends javafx.application.Application {

    public static void main(String args[]) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        try {
            final File file = new File(getClass().getResource("/resources/DragonBallNes.mp3").getPath());
            final Media media = new Media(file.toURI().toString());
            final MediaPlayer mp = new MediaPlayer(media);
            mp.setCycleCount(100);
            mp.play();

            StackPane stackPane = new StackPane();
            Scene scene = new Scene(stackPane);

            /** on ajoute les fonds*/
            ImageView viLand = new ImageView(new Image(getClass().getResourceAsStream("/resources/land.png")));
            ImageView fondNoir = new ImageView(new Image(getClass().getResourceAsStream("/resources/FondNoir.png")));
            /** taille terrain */
            viLand.setScaleX(1);
            viLand.setScaleY(1);
            fondNoir.setScaleX(1);
            fondNoir.setScaleY(1);
            /** position du terrain*/
            viLand.setTranslateX(0);
            viLand.setTranslateY(0);
            fondNoir.setTranslateX(0);
            fondNoir.setTranslateY(0);
            /**ajout du fond sur le pane*/
            stackPane.getChildren().add(viLand);
            TheBattleCat TBC = new TheBattleCat();

            /** on ajoute la base */
            stackPane.getChildren().add(TBC.spawn(new Base(), TBC.getCampAllier()));
            Labels labelBase = new Labels(new Label(TBC.getCampAllier().getList().get(0).getHealth() + "/8000"), "Algerian", 30, Color.GREENYELLOW, 370, -110);
            labelBase.setLabels(stackPane);
            /** onajoute la base enemis*/
            stackPane.getChildren().add(TBC.spawn(new BaseEnemis(), TBC.getCampEnemie()));
            Labels labelBaseE = new Labels(new Label(TBC.getCampEnemie().getList().get(0).getHealth() + "/100000"), "Algerian", 30, Color.GREENYELLOW, -370, -110);
            labelBaseE.setLabels(stackPane);

            /**On ajoute l'argent Allier et Ennemeie*/
            Argent argent = new Argent(0, 1, 1200, 400);
            Argent argentE = new Argent(200, 1, 1200, 400);

            /**On ajoute le compteur d'argent (Allier)*/
            Labels labelArgent = new Labels(new Label(argent.nomLab()), "Algerian", 50, Color.YELLOW, 300, -300);
            labelArgent.setLabels(stackPane);

            /**On ajoute le bouton de niveau d'argent*/
            Boutons boutonArgent = new Boutons(new Circle(80), argent, "argent.png", -400, -265);
            boutonArgent.setBoutonC(stackPane);
            /** action quand le bouton est cliker*/
            boutonArgent.getCercle().setOnMouseClicked(event -> argent.levelUp(boutonArgent));

            /**on ajoute Bouton Chat*/
            Boutons boutonChat = new Boutons(new Rectangle(150, 100), 100, "Chat.png", -400, 300);
            boutonChat.setBoutonR(stackPane);
            /** action quand le bouton est cliker*/
            boutonChat.getRectangle().setOnMouseClicked(event -> TBC.spawnableChat(new Chat(), TBC.getCampAllier(), argent, stackPane));

            /**on ajoute Bouton TankCat*/
            Boutons boutonTankCat = new Boutons(new Rectangle(150, 100), 200, "TankCat.png", -200, 300);
            boutonTankCat.setBoutonR(stackPane);
            /** action quand le bouton est cliker*/
            boutonTankCat.getRectangle().setOnMouseClicked(event -> TBC.spawnableTankCat(new TankCat(), TBC.getCampAllier(), argent, stackPane));

            /**on ajoute Bouton ChatVache*/
            Boutons boutonChatVache = new Boutons(new Rectangle(150, 100), 4000, "ChatVache.png", 400, 300);
            boutonChatVache.setBoutonR(stackPane);
            /** action quand le bouton est cliker*/
            boutonChatVache.getRectangle().setOnMouseClicked(event -> TBC.spawnableChatVache(new ChatVache(), TBC.getCampAllier(), argent, stackPane));

            /**on ajoute Bouton BirdCat*/
            Boutons boutonBirdCat = new Boutons(new Rectangle(150, 100), 300, "BirdCat.png", 0, 300);
            boutonBirdCat.setBoutonR(stackPane);
            /** action quand le bouton est cliker*/
            boutonBirdCat.getRectangle().setOnMouseClicked(event -> TBC.spawnableBirdCat(new BirdCat(), TBC.getCampAllier(), argent, stackPane));

            /**on ajoute Bouton LizardCat*/
            Boutons boutonLizardCat = new Boutons(new Rectangle(150, 100), 500, "LizardCat.png", 200, 300);
            boutonLizardCat.setBoutonR(stackPane);
            /** action quand le bouton est cliker*/
            boutonLizardCat.getRectangle().setOnMouseClicked(event -> TBC.spawnableLizardCat(new LizardCat(), TBC.getCampAllier(), argent, stackPane));

            /**animation*/
            AnimationTimer timer = new AnimationTimer() {
                @Override
                /**action event*/
                public void handle(long now) {
                    /**test si le jeu est termine*/
                    if (TBC.getCampAllier().endgame() || TBC.getCampEnemie().endgame()) {
                        /**le jeu est termine*/
                        /**lancer l'affichage de fin de jeu*/
                        stackPane.getChildren().clear();
                        stackPane.getChildren().add(fondNoir);
                        if (TBC.getCampAllier().endgame()) {
                            /**si defaite*/
                            Labels labelLoose = new Labels(new Label("Bah T Nul... :|"), "Algerian", 100, Color.RED, 0, 0);
                            labelLoose.setLabels(stackPane);
                        } else {
                            /**si victoire*/
                            Labels labelWin = new Labels(new Label("Wow trop fort ! :0"), "Algerian", 100, Color.FORESTGREEN, 0, 0);
                            labelWin.setLabels(stackPane);
                        }
                    } else {
                        /**le jeu n'est pas termine on continu la partie*/
                        /**On anime le jeu*/
                        /**Evolution de l'argent*/
                        argentE.grow();
                        argent.grow();
                        /**Gestion du camp enemie*/
                        TBC.stratE(argentE, stackPane, TBC.getCampEnemie());
                        /**Actualisation de l'affichage de largent, de la vie des camps*/
                        labelArgent.setLabels(argent.nomLab());
                        labelBase.setLabels(TBC.getCampAllier().getList().get(0).getHealth() + "/8000");
                        labelBaseE.setLabels(TBC.getCampEnemie().getList().get(0).getHealth() + "/100000");
                        /**Animation des personnages*/
                        TBC.animations(TBC.getCampAllier(), TBC.getCampEnemie());
                    }
                }
            };
            timer.start();

            /** mettre la scene dans le stage */
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
