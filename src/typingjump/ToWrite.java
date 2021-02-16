package typingjump;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @authors Raeda azkoul, Noor Al-Hafez, Fatema Bayat, Aisha Farouque, Fatimah Jabr
 */
public class ToWrite extends HBox {

    private final char[] letters;
    private int correctLetters = 0;
    static boolean algin = true;

    public ToWrite(String word) {
        letters = word.toUpperCase().toCharArray();
        for (char c : letters) {
            Text letter = new Text(c + "");
            letter.setFont(Font.font("Showcard Gothic", FontWeight.BOLD, FontPosture.REGULAR, 20));
            letter.setStyle("-fx-fill: #ffffff");
            letter.setStrokeWidth(1);
            letter.setStroke(Color.BLACK);
            getChildren().add(letter);
        }
        setAlignment(Pos.BOTTOM_CENTER);
//        if (algin) {
//            setAlignment(Pos.BOTTOM_LEFT);
//            algin = false;
//        } else {
//            setAlignment(Pos.BOTTOM_RIGHT);
//            algin = true;
//        }
        setPadding(new Insets(0, 25, 0, 25));
    }

    public boolean handleKeyPress(String letter) {
        
        if (isFinished()) {
            return true;
        }

        char c = letters[correctLetters];

        if (letter.charAt(0) == c) {
            getChildren().get(correctLetters).setStyle("-fx-fill: #e5de0a ;");
            correctLetters++;

        } else {
            if (letter.equals("SPACE")) {
                correctLetters++;
                return true;
            } else {
                getChildren().get(correctLetters).setStyle("-fx-fill:#ff0000;");
                correctLetters++;

            }
        }
        return letter.charAt(0) == c;
    }

    public boolean isFinished() {
        return correctLetters == letters.length;
    }

}
