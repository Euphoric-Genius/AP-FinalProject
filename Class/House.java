package Class;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static Class.Board.house;
import static Class.Client.*;

public class House {

    String marble;
    @FXML
    ImageView imageView = new ImageView();


    public House(String marble) {

        if (marble.equals("Pawn")) {
            String resource = (playerColor.equals("White")) ? "/Files/whitePawn.png" : "/Files/blackPawn.png";
            imageView.setImage(new Image(resource));
            this.marble = (playerColor.equals("White")) ? "WhitePawn" : "BlackPawn";
        }
        if (marble.equals("Knight")) {
            String resource = (playerColor.equals("White")) ? "/Files/whiteKnight.png" : "/Files/blackKnight.png";
            imageView.setImage(new Image(resource));
            this.marble = (playerColor.equals("White")) ? "WhiteKnight" : "BlackKnight";
        }
        if (marble.equals("Bishop")) {
            String resource = (playerColor.equals("White")) ? "/Files/whiteBishop.png" : "/Files/blackBishop.png";
            imageView.setImage(new Image(resource));
            this.marble = (playerColor.equals("White")) ? "WhiteBishop" : "BlackBishop";
        }
        if (marble.equals("Rook")) {
            String resource = (playerColor.equals("White")) ? "/Files/whiteRook.png" : "/Files/blackRook.png";
            imageView.setImage(new Image(resource));
            this.marble = (playerColor.equals("White")) ? "WhiteRook" : "BlackRook";
        }
        if (marble.equals("Queen")) {
            String resource = (playerColor.equals("White")) ? "/Files/whiteQueen.png" : "/Files/blackQueen.png";
            imageView.setImage(new Image(resource));
            this.marble = (playerColor.equals("White")) ? "WhiteQueen" : "BlackQueen";
        }
        if (marble.equals("King")) {
            String resource = (playerColor.equals("White")) ? "/Files/whiteKing.png" : "/Files/blackKing.png";
            imageView.setImage(new Image(resource));
            this.marble = (playerColor.equals("White")) ? "WhiteKing" : "BlackKing";
        }
    }

    public House (){}


}
