package Client.Class;

import Client.Controller.BoardController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Client.Class.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import Client.Class.*;

import static Client.Class.Board.house;
import static Client.Class.Client.*;
import static Client.Controller.BoardController.*;

public class House {
    public int x, y;
    public String marble, marbleColor = "";
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
                        ? "/Client/Files/whitePawn.png" : "/Client/Files/blackPawn.png";
                imageView.setImage(new Image(resource, 60, 60, false, true));
            }
            if (marble.equals("Knight")) {
                marbleColor = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "White" : "Black";
                String resource = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "/Client/Files/whiteKnight.png" : "/Client/Files/blackKnight.png";
                imageView.setImage(new Image(resource, 60, 60, false, true));
            }
            if (marble.equals("Bishop")) {
                marbleColor = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "White" : "Black";
                String resource = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "/Client/Files/whiteBishop.png" : "/Client/Files/blackBishop.png";
                imageView.setImage(new Image(resource, 60, 60, false, true));
            }
            if (marble.equals("Rook")) {
                marbleColor = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "White" : "Black";
                String resource = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "/Client/Files/whiteRook.png" : "/Client/Files/blackRook.png";
                imageView.setImage(new Image(resource, 60, 60, false, true));
            }
            if (marble.equals("Queen")) {
                marbleColor = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "White" : "Black";
                String resource = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "/Client/Files/whiteQueen.png" : "/Client/Files/blackQueen.png";
                imageView.setImage(new Image(resource, 70, 64, true, true));
            }
            if (marble.equals("King")) {
                marbleColor = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "White" : "Black";
                String resource = ((playerColor.equals("White") && x == 7) || (playerColor.equals("Black") && x == 0))
                        ? "/Client/Files/whiteKing.png" : "/Client/Files/blackKing.png";
                imageView.setImage(new Image(resource, 70, 64, false, true));
            }
        }

        highlight.setPrefWidth(74);
        highlight.setPrefHeight(74);
        highlight.setVisible(false);
        highlight.setStyle("-fx-background-color: #673444");
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resetHouses();
                Client.recentHouse = Board.house[x][y];
                ArrayList<Coordinates> possibleMoves;
                if (isPlayerMarble(playerColor, marbleColor) && isPlayerTurn) {
                    possibleMoves = BoardController.possibleMoves(marble, marbleColor, x, y);
                    for (int i = 0; i < possibleMoves.size(); i++) {
                        int row = possibleMoves.get(i).x, col = possibleMoves.get(i).y;
                        Board.house[row][col].highlight.setVisible(true);
                        Board.house[row][col].highlight.setOpacity(0.7);
                    }
                }

            }
        });
        highlight.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                move(Client.recentHouse.x, Client.recentHouse.y, x, y);
                RecentMove recentMove = new RecentMove();
                recentMove.initX = Client.recentHouse.x;
                recentMove.initY = Client.recentHouse.y;
                recentMove.finalX = x;
                recentMove.finalY = y;
                isPlayerTurn = false;


                try {
//                    oosF.format("Done!");
//                    oosF.flush();
                    oos.writeObject(recentMove);
                    System.out.println("!!!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                resetHouses();
                //Place for changing isPlayerTurn turn boolean.
            }

        });

    }

    private void resetHouses() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Board.house[i][j].highlight.setVisible(false);
            }
        }
    }
}
