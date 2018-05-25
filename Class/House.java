package Class;

import Controller.BoardController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Class.*;

import java.util.ArrayList;

import Class.*;

import static Class.Board.house;
import static Class.Client.isPlayerTurn;
import static Class.Client.playerColor;
import static Controller.BoardController.*;

public class House {
    public int x, y;
    public String marble, marbleColor;
    @FXML
    public ImageView imageView = new ImageView();
    @FXML
    public Button highlight = new Button();

    public House(String myMarble, int x, int y) {
        this.marble = myMarble;
        this.x = x;
        this.y = y;
        if (marble != null) {
            if (marble.equals("Pawn")) {
                marbleColor = ((playerColor.equals("White") && x == 6) || (playerColor.equals("Black") && x == 1))
                        ? "White" : "Black";
                String resource = ((playerColor.equals("White") && x == 6) || (playerColor.equals("Black") && x == 1))
                        ? "/Files/whitePawn.png" : "/Files/blackPawn.png";
                imageView.setImage(new Image(resource, 60, 60, false, true));
            }
            if (marble.equals("Knight")) {
                marbleColor = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "White" : "Black";
                String resource = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "/Files/whiteKnight.png" : "/Files/blackKnight.png";
                imageView.setImage(new Image(resource, 60, 60, false, true));
            }
            if (marble.equals("Bishop")) {
                marbleColor = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "White" : "Black";
                String resource = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "/Files/whiteBishop.png" : "/Files/blackBishop.png";
                imageView.setImage(new Image(resource, 60, 60, false, true));
            }
            if (marble.equals("Rook")) {
                marbleColor = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "White" : "Black";
                String resource = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "/Files/whiteRook.png" : "/Files/blackRook.png";
                imageView.setImage(new Image(resource, 60, 60, false, true));
            }
            if (marble.equals("Queen")) {
                marbleColor = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "White" : "Black";
                String resource = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "/Files/whiteQueen.png" : "/Files/blackQueen.png";
                imageView.setImage(new Image(resource, 60, 60, false, true));
            }
            if (marble.equals("King")) {
                marbleColor = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "White" : "Black";
                String resource = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "/Files/whiteKing.png" : "/Files/blackKing.png";
                imageView.setImage(new Image(resource, 60, 60, false, true));
            }
        }
        highlight.setPrefWidth(74);
        highlight.setPrefHeight(74);
        highlight.setVisible(false);
        highlight.setStyle("-fx-background-color: #313163");
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resetHouses();
                Main.recentHouse = Board.house[x][y];
                ArrayList<Coordinates> possibleMoves;
                if (isPlayerMarble(playerColor, marbleColor) && isPlayerTurn) {
                    possibleMoves = BoardController.possibleMoves(marble, marbleColor, x, y);
                    for (int i = 0; i < possibleMoves.size(); i++) {
                        int row = possibleMoves.get(i).x, col = possibleMoves.get(i).y;
                        Board.house[row][col].highlight.setVisible(true);
                        Board.house[row][col].highlight.setOpacity(0.5);
                    }
                }

            }
        });
        highlight.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(highlight.isVisible());
                if (highlight.isVisible()) {
                    System.out.println(Main.recentHouse.x);
                    move(Main.recentHouse.x, Main.recentHouse.y, x, y);
                    resetHouses();
                }
                System.out.println(house[Main.recentHouse.x][Main.recentHouse.y].marble + "!!");
                System.out.println(house[x][y].marble + "!");
            }
        });

    }

//    public House(int i,int j) {
//        this.x=i;
//        this.y=j;
//        highlight.setPrefWidth(30);
//        highlight.setPrefHeight(30);
//        highlight.setVisible(false);
//
//        highlight.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                System.out.println(highlight.isVisible());
//                if(highlight.isVisible()){
//                    move(Main.recentHouse.x,Main.recentHouse.y,x,y);
//
//                }
//                resetHouses();
//                System.out.println(house[Main.recentHouse.x][Main.recentHouse.y].marble+"!!");
//                System.out.println(house[x][y].marble +"!");
//            }
//        });
//        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                resetHouses();
//                ArrayList<Coordinates> possibleMoves;
//                if (isPlayerMarble(playerColor, marbleColor) && isPlayerTurn) {
//                    possibleMoves = BoardController.possibleMoves(marble, marbleColor, x, y);
//                    for (int i = 0; i < possibleMoves.size(); i++) {
//                        int row = possibleMoves.get(i).x, col = possibleMoves.get(i).y;
//                        Board.house[row][col].highlight.setVisible(true);
//                        Board.house[row][col].highlight.setOpacity(0.5);
//                    }
//                }
//            }
//        });
//
//
//    }

    private void resetHouses() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Board.house[i][j].highlight.setVisible(false);
            }
        }
    }
}
