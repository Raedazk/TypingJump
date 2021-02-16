package typingjump;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
// * @authors Raeda azkoul, Noor Al-Hafez, Fatema Bayat, Aisha Farouque, Fatimah Jabr

public class TipsVideoController implements Initializable {

    @FXML
    private MediaView mv;
    @FXML
    private JFXButton btnNext;
    @FXML
    private JFXButton btnPrevious;

    private int count = 0;
    MediaPlayer mediaPlayer;
    Media media;
    String[] videos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //array of videos
        videos = new String[]{"resourses/Tip one.mp4", "resourses/Tip two.mp4", "resourses/Tip three.mp4"};
        //default video
        media = new Media(getClass().getResource(videos[0]).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mv.setMediaPlayer(mediaPlayer);

        //___________________________________________________________
        btnPrevious.setOnAction(e -> {//previous
            mediaPlayer.stop();
            if (count == 0) {
                media = new Media(getClass().getResource(videos[0]).toExternalForm());
                mediaPlayer = new MediaPlayer(media);
                mv.setMediaPlayer(mediaPlayer);
            } else {
                count--;
                media = new Media(getClass().getResource(videos[count]).toExternalForm());
                mediaPlayer = new MediaPlayer(media);
                mv.setMediaPlayer(mediaPlayer);
            }
        });
        //__________________________________________________________________

        btnNext.setOnAction(e -> {//next
            mediaPlayer.stop();
            if (count == videos.length - 1) {
                media = new Media(getClass().getResource(videos[videos.length - 1]).toExternalForm());
                mediaPlayer = new MediaPlayer(media);
                mv.setMediaPlayer(mediaPlayer);
            } else {
                count++;
                media = new Media(getClass().getResource(videos[count]).toExternalForm());
                mediaPlayer = new MediaPlayer(media);
                mv.setMediaPlayer(mediaPlayer);
            }
        });
    }
    //_____________________________________________

    @FXML
    private void onClick_stop() {//stop button
        mediaPlayer.stop();
    }

    @FXML
    private void onClick_play() {//play button
        if (mediaPlayer.getStatus() == PLAYING) {
            mediaPlayer.stop();
            mediaPlayer.play();
        } else {
            mediaPlayer.play();
        }
    }

    @FXML  //Button back
    private void Back(ActionEvent event) throws IOException {
        //_____Next Scene__________
        mediaPlayer.stop();
        Parent root = FXMLLoader.load(getClass().getResource("Registeration.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
