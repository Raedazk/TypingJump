package typingjump;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author Raeda azkoul <Raeda at UQU.org>
 */
public class GameBoardController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private VBox steps_array;
    @FXML
    private ImageView backGround;
    @FXML
    private Label lbLevel;
    @FXML
    private Label lbScore;
    @FXML
    private HBox hboxHart;
    @FXML
    private ImageView imgHeart1;
    @FXML
    private ImageView imgHeart2;
    @FXML
    private ImageView imgHeart3;
    @FXML
    private VBox vbox;
    @FXML
    private Group characterGroup;

    VBox step;
    Timeline timeline;
    private TextSelector selector;
    static boolean alig = true;
    int NoChar = 0;
    private SimpleIntegerProperty score = new SimpleIntegerProperty(0);
    private static int level = 1;
    private int nofWords = level * 10;
    private static player movePlayerData = new player();
    private long startTime = 0;
    private final Media mediaTrue = new Media(getClass().getResource("resourses/jump.mp3").toExternalForm());
    private final Media mediaFalse = new Media(getClass().getResource("resourses/fall.mp3").toExternalForm());

    //GirlLeft
    final ImageView GirlL1 = new ImageView(new Image(GameBoardController.class.getResource("resourses/girlJump1Left.png").toString()));
    final ImageView GirlL2 = new ImageView(new Image(GameBoardController.class.getResource("resourses/girlJump2Left.png").toString()));
    final ImageView GirlL3 = new ImageView(new Image(GameBoardController.class.getResource("resourses/girlJump3Left.png").toString()));
    final ImageView GirlL4 = new ImageView(new Image(GameBoardController.class.getResource("resourses/girlJump4Left.png").toString()));
    final ImageView GirlL5 = new ImageView(new Image(GameBoardController.class.getResource("resourses/girlJump5Left.png").toString()));
    final ImageView GirlL6 = new ImageView(new Image(GameBoardController.class.getResource("resourses/girlJump6Left.png").toString()));

    //Boy Left
    final ImageView BoyL1 = new ImageView(new Image(GameBoardController.class.getResource("resourses/boyJump1Left.png").toString()));
    final ImageView BoyL2 = new ImageView(new Image(GameBoardController.class.getResource("resourses/boyJump2Left.png").toString()));
    final ImageView BoyL3 = new ImageView(new Image(GameBoardController.class.getResource("resourses/boyJump3Left.png").toString()));
    final ImageView BoyL4 = new ImageView(new Image(GameBoardController.class.getResource("resourses/boyJump4Left.png").toString()));
    final ImageView BoyL5 = new ImageView(new Image(GameBoardController.class.getResource("resourses/boyJump5Left.png").toString()));
    final ImageView BoyL6 = new ImageView(new Image(GameBoardController.class.getResource("resourses/boyJump6Left.png").toString()));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        steps_array.setAlignment(Pos.CENTER);
        setLevel();
        setSelector(movePlayerData.getType());
        showNextWord();
        step.getChildren().get(0).setFocusTraversable(true);
        step.getChildren().get(0).requestFocus();
        root.setOnKeyPressed(e -> {
            System.out.println(e.getCode().toString());
            onKeyPress(e.getCode().toString());
        });
        lbScore.textProperty().bind(score.asString());
    }

    @FXML
    private void ExitHun(ActionEvent event) {
        level = 1;
        try {
            //________________Create Session___________________________

            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("registeration.fxml"));
            Parent resultparent = load.load();
            Object controller = load.getController();
            Scene scene = new Scene(resultparent);
            Stage stage = (Stage) ((Node) this.root).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showNextWord() {
        step = new VBox();
        step.setAlignment(Pos.CENTER);
        ImageView im = new ImageView(new Image(getClass().getResource("resourses/step" + (int) (Math.random() * 3) + ".png").toString()));
        im.setFitHeight(100);
        im.setPreserveRatio(true);
        ToWrite view = new ToWrite(selector.getNextWord());
        startTime = System.nanoTime();
        step.getChildren().addAll(view, im);
        steps_array.getChildren().add(0, step);
    }

    private void onKeyPress(String letter) {
        ToWrite view = (ToWrite) step.getChildren().get(0);
        NoChar++;
        if (!view.handleKeyPress(letter)) {
            sound(false);
            if (hboxHart.getChildren().isEmpty()) {//no extra heart
                toResults();
            } else {//remove heart
                hboxHart.getChildren().remove(0);
            }
        } else {
            score.set(10 + score.get());
        }

        if (view.isFinished()) {
            setCharacterImage().play();

            nofWords--;
            sound(true);
            levelsBarPlus();
            translatetran();
            showNextWord();
        }
    }

    public void translatetran() {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(1000));
        translateTransition.setNode(steps_array);
        translateTransition.setByY(65);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        translateTransition.play();
    }

    public void toResults() {
        long endTime = System.nanoTime() - startTime;
        movePlayerData.setScore(score.get());
        movePlayerData.setNo_chars(NoChar);
        movePlayerData.setAccuracy(((movePlayerData.getScore() / 10) / NoChar) * 100);
        movePlayerData.setTime(movePlayerData.getTime() + (endTime / 1000000000.0));
        movePlayerData.setSpeed(NoChar / (movePlayerData.getTime()));
        GameBoardController.setMovePlayerData(movePlayerData);
        level = 1;
        try {
            //________________Create Session___________________________
            final Session s1 = HibernateUtilPlayer.getSessionFactory().openSession();
            final Transaction t1 = s1.beginTransaction();
            s1.save(movePlayerData);
            t1.commit();
            s1.close();

            ResultController.setMovePlayerData(movePlayerData);
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("result.fxml"));
            Parent resultparent = load.load();
            Object controller = load.getController();
            Scene scene = new Scene(resultparent);
            Stage stage = (Stage) ((Node) this.root).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sound(boolean what) {
        MediaPlayer mediaPlayer;
        if (what) {

            mediaPlayer = new MediaPlayer(mediaTrue);
        } else {
            mediaPlayer = new MediaPlayer(mediaFalse);
        }
        mediaPlayer.play();
    }

    public void levelsBarPlus() {
        if (nofWords == -1) {
            if (level < 3) {
                try {
                    level++;

                    long endTime = System.nanoTime() - startTime;
                    movePlayerData.setScore(movePlayerData.getScore() + score.get());
                    movePlayerData.setNo_chars(movePlayerData.getNo_chars() + NoChar);
                    movePlayerData.setTime(movePlayerData.getTime() + (endTime / 1000000000.0));
                    GameBoardController.setMovePlayerData(movePlayerData);
                    //start game
                    FXMLLoader load = new FXMLLoader();
                    load.setLocation(getClass().getResource("GameBoard.fxml"));
                    Parent resultparent = load.load();
                    Object controller = load.getController();
                    System.out.println(controller);
                    Scene scene = new Scene(resultparent);
                    Stage stage = (Stage) root.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                toResults();
            }
        } else {
            vbox.getChildren().get(nofWords).setOpacity(1);
        }
    }

    public void setLevel() {

        vbox.getChildren().removeAll();
        switch (level) {
            case 1:
                backGround.setImage(new Image(getClass().getResource("resourses/background0.jpg").toString()));
                lbLevel.setText("Level 1");
                nofWords = 9;
                setLevelBar(10);
                break;
            case 2:
                backGround.setImage(new Image(getClass().getResource("resourses/background1.jpg").toString()));
                lbLevel.setText("Level 2");
                nofWords = 19;
                setLevelBar(20);
                score.set((int) movePlayerData.getScore());
                NoChar = movePlayerData.getNo_chars();
                break;
            case 3:
                backGround.setImage(new Image(getClass().getResource("resourses/background2.jpg").toString()));
                lbLevel.setText("Level 3");
                alig = false;
                nofWords = 29;
                score.set((int) movePlayerData.getScore());
                NoChar = movePlayerData.getNo_chars();
                setLevelBar(30);
                break;
            default:
                toResults();
        }
    }

    public void setLevelBar(int steps) {
        vbox.setSpacing(0);
        for (int i = 0; i < steps; i++) {
            Rectangle rec = new Rectangle(vbox.getPrefWidth(), vbox.getPrefHeight() / steps);
            rec.setFill(Color.GREEN);
            rec.setOpacity(0);
            vbox.getChildren().add(rec);
        }
    }

    public SimpleIntegerProperty getScore() {
        return score;
    }

    public void setScore(SimpleIntegerProperty score) {
        this.score = score;
    }

    /**
     * ******************* Character Animation
     *
     *******************************
     * @return
     */
    public Timeline setCharacterImage() {

        if (movePlayerData.getGender().equals("male")) {

            BoyL1.setFitHeight(200);
            BoyL1.setPreserveRatio(true);

            BoyL2.setFitHeight(200);
            BoyL2.setPreserveRatio(true);

            BoyL3.setFitHeight(200);
            BoyL3.setPreserveRatio(true);

            BoyL4.setFitHeight(200);
            BoyL4.setPreserveRatio(true);

            BoyL5.setFitHeight(200);
            BoyL5.setPreserveRatio(true);

            BoyL6.setFitHeight(200);
            BoyL6.setPreserveRatio(true);

            characterGroup.getChildren().add(BoyL1);
            Timeline t1 = new Timeline();
            t1.setCycleCount(1);

            ///////////////////////////////////////////////////////////////////////////
            t1.getKeyFrames().add(new KeyFrame(
                    Duration.millis(100),
                    (ActionEvent event) -> {
                        characterGroup.getChildren().setAll(BoyL1);
                    }
            ));

            t1.getKeyFrames().add(new KeyFrame(
                    Duration.millis(250),
                    (ActionEvent event) -> {
                        characterGroup.getChildren().setAll(BoyL2);
                    }
            ));

            t1.getKeyFrames().add(new KeyFrame(
                    Duration.millis(400),
                    (ActionEvent event) -> {
                        characterGroup.getChildren().setAll(BoyL3);
                    }
            ));

            t1.getKeyFrames().add(new KeyFrame(
                    Duration.millis(650),
                    (ActionEvent event) -> {
                        characterGroup.getChildren().setAll(BoyL4);
                    }
            ));
            t1.getKeyFrames().add(new KeyFrame(
                    Duration.millis(800),
                    (ActionEvent event) -> {
                        characterGroup.getChildren().setAll(BoyL5);
                    }
            ));

            t1.getKeyFrames().add(new KeyFrame(
                    Duration.millis(950),
                    (ActionEvent event) -> {
                        characterGroup.getChildren().setAll(BoyL6);
                    }
            ));
            return t1;

        } else {

            GirlL1.setFitHeight(200);
            GirlL1.setPreserveRatio(true);

            GirlL2.setFitHeight(200);
            GirlL2.setPreserveRatio(true);

            GirlL3.setFitHeight(200);
            GirlL3.setPreserveRatio(true);

            GirlL4.setFitHeight(200);
            GirlL4.setPreserveRatio(true);

            GirlL5.setFitHeight(200);
            GirlL5.setPreserveRatio(true);

            GirlL6.setFitHeight(200);
            GirlL6.setPreserveRatio(true);
            characterGroup.getChildren().add(GirlL1);

            Timeline t2 = new Timeline();
            t2.setCycleCount(1);
            ///////////////////////////////////////////////////////////////
            t2.getKeyFrames().add(new KeyFrame(
                    Duration.millis(100),
                    (ActionEvent event) -> {
                        characterGroup.getChildren().setAll(GirlL1);
                    }
            ));

            t2.getKeyFrames().add(new KeyFrame(
                    Duration.millis(250),
                    (ActionEvent event) -> {
                        characterGroup.getChildren().setAll(GirlL2);
                    }
            ));

            t2.getKeyFrames().add(new KeyFrame(
                    Duration.millis(400),
                    (ActionEvent event) -> {
                        characterGroup.getChildren().setAll(GirlL3);
                    }
            ));
            t2.getKeyFrames().add(new KeyFrame(
                    Duration.millis(550),
                    (ActionEvent event) -> {
                        characterGroup.getChildren().setAll(GirlL4);
                    }
            ));
            t2.getKeyFrames().add(new KeyFrame(
                    Duration.millis(700),
                    (ActionEvent event) -> {
                        characterGroup.getChildren().setAll(GirlL5);
                    }
            ));
            t2.getKeyFrames().add(new KeyFrame(
                    Duration.millis(850),
                    (ActionEvent event) -> {
                        characterGroup.getChildren().setAll(GirlL6);
                    }
            ));
            return t2;
        }
    }

    /**
     * ******************* Heart Animation *******************************
     */
    @FXML
    private void handleentered1(MouseEvent event) {
        imgHeart1.setScaleX(1.5);
        imgHeart1.setScaleY(1.5);
    }

    @FXML
    private void handleentered2(MouseEvent event) {
        imgHeart2.setScaleX(1.5);
        imgHeart2.setScaleY(1.5);
    }

    @FXML
    private void handleentered3(MouseEvent event) {
        imgHeart3.setScaleX(1.5);
        imgHeart3.setScaleY(1.5);
    }

    @FXML
    private void handleexited1(MouseEvent event) {
        imgHeart1.setScaleX(1);
        imgHeart1.setScaleY(1);
    }

    @FXML
    private void handleexited2(MouseEvent event) {
        imgHeart2.setScaleX(1);
        imgHeart2.setScaleY(1);
    }

    @FXML
    private void handleexited3(MouseEvent event) {
        imgHeart3.setScaleX(1);
        imgHeart3.setScaleY(1);
    }

    public void setSelector(String select) {
        selector = new TextSelector(select);
    }

    public static void setMovePlayerData(player movePlayerData) {
        GameBoardController.movePlayerData = movePlayerData;
    }

}
