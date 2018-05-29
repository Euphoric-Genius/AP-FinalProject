package Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static Client.Class.Board.*;
import static Client.Class.Client.playerColor;

import Client.Class.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import javax.swing.*;


public class BoardController implements Initializable {

    public static Thread thread = new Thread(new Listener());
    @FXML
    GridPane gridPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        thread.start();
        houseInitializer();

    }

    public static void move(int initI, int intiJ, int destI, int destJ) {
        house[destI][destJ].marble = house[initI][intiJ].marble;
        house[destI][destJ].marbleColor = house[initI][intiJ].marbleColor;
        house[destI][destJ].imageView.setImage(house[initI][intiJ].imageView.getImage());
        house[initI][intiJ].imageView.setImage(null);
        house[initI][intiJ].marbleColor = "";
        house[initI][intiJ].marble = "";
    }

    public static boolean isPlayerMarble(String playerColor, String marbleColor) {
        if (playerColor.equals(marbleColor)) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Coordinates> possibleMoves(String marble, String marbleColor, int i, int j) {
        ArrayList<Coordinates> coordinates = new ArrayList<>();
        Coordinates temp = new Coordinates();
        if (marble.equals("Pawn")) {
            if (marbleColor.equals(playerColor)) {
                if (isMoveLegal(i - 1, j, marbleColor)
                        && !house[i - 1][j].marbleColor.equals(polarColor(marbleColor))) {
                    temp.x = i - 1;
                    temp.y = j;
                    coordinates.add(temp);
                    if (i == 6 && isMoveLegal(4, j, marbleColor)
                            && !house[i - 1][j].marbleColor.equals(polarColor(marbleColor))) {
                        temp = new Coordinates();
                        temp.x = i - 2;
                        temp.y = j;
                        coordinates.add(temp);
                    }
                }
                if (isMoveLegal(i - 1, j - 1, marbleColor)
                        && house[i - 1][j - 1].marbleColor.equals(polarColor(marbleColor))) {
                    temp = new Coordinates();
                    temp.x = i - 1;
                    temp.y = j - 1;
                    coordinates.add(temp);
                }
                if (isMoveLegal(i - 1, j + 1, marbleColor)
                        && house[i - 1][j + 1].marbleColor.equals(polarColor(marbleColor))) {
                    temp = new Coordinates();
                    temp.x = i - 1;
                    temp.y = j + 1;
                    coordinates.add(temp);
                }
            }
        }
        if (marble.equals("Rook") || marble.equals("Queen")) {
            boolean[] firstEnemyFounded = new boolean[4];
            for (int k = 1; k <= 7; k++) {
                if (isMoveLegal(i + k, j, marbleColor) && !firstEnemyFounded[0]) {
                    temp = new Coordinates();
                    temp.x = i + k;
                    temp.y = j;
                    coordinates.add(temp);
                    if (house[temp.x][temp.y].marbleColor == polarColor(marbleColor)) {
                        firstEnemyFounded[0] = true;
                    }
                } else {
                    break;
                }
            }
            for (int k = 1; k <= 7; k++) {
                if (isMoveLegal(i - k, j, marbleColor) && !firstEnemyFounded[1]) {
                    temp = new Coordinates();
                    temp.x = i - k;
                    temp.y = j;
                    coordinates.add(temp);
                    if (house[temp.x][temp.y].marbleColor == polarColor(marbleColor)) {
                        firstEnemyFounded[1] = true;
                    }
                } else {
                    break;
                }
            }
            for (int k = 1; k <= 7; k++) {
                if (isMoveLegal(i, j + k, marbleColor) && !firstEnemyFounded[2]) {
                    temp = new Coordinates();
                    temp.x = i;
                    temp.y = j + k;
                    coordinates.add(temp);
                    if (house[temp.x][temp.y].marbleColor == polarColor(marbleColor)) {
                        firstEnemyFounded[2] = true;
                    }
                } else {
                    break;
                }
            }
            for (int k = 1; k <= 7; k++) {
                if (isMoveLegal(i, j - k, marbleColor) && !firstEnemyFounded[3]) {
                    temp = new Coordinates();
                    temp.x = i;
                    temp.y = j - k;
                    coordinates.add(temp);
                    if (house[temp.x][temp.y].marbleColor == polarColor(marbleColor)) {
                        firstEnemyFounded[3] = true;
                    }
                } else {
                    break;
                }
            }
        }
        if (marble.equals("Knight")) {
            if (isMoveLegal(i + 1, j + 2, marbleColor)) {
                temp = new Coordinates();
                temp.x = i + 1;
                temp.y = j + 2;
                coordinates.add(temp);
            }
            if (isMoveLegal(i - 1, j + 2, marbleColor)) {
                temp = new Coordinates();
                temp.x = i - 1;
                temp.y = j + 2;
                coordinates.add(temp);
            }
            if (isMoveLegal(i - 1, j - 2, marbleColor)) {
                temp = new Coordinates();
                temp.x = i - 1;
                temp.y = j - 2;
                coordinates.add(temp);
            }
            if (isMoveLegal(i + 1, j - 2, marbleColor)) {
                temp = new Coordinates();
                temp.x = i + 1;
                temp.y = j - 2;
                coordinates.add(temp);
            }
            if (isMoveLegal(i - 2, j + 1, marbleColor)) {
                temp = new Coordinates();
                temp.x = i - 2;
                temp.y = j + 1;
                coordinates.add(temp);
            }
            if (isMoveLegal(i - 2, j - 1, marbleColor)) {
                temp = new Coordinates();
                temp.x = i - 2;
                temp.y = j - 1;
                coordinates.add(temp);
            }
            if (isMoveLegal(i + 2, j + 1, marbleColor)) {
                temp = new Coordinates();
                temp.x = i + 2;
                temp.y = j + 1;
                coordinates.add(temp);
            }
            if (isMoveLegal(i + 2, j - 1, marbleColor)) {
                temp = new Coordinates();
                temp.x = i + 2;
                temp.y = j - 1;
                coordinates.add(temp);
            }

        }
        if (marble.equals("Bishop") || marble.equals("Queen")) {
            boolean[] firstEnemyFounded = new boolean[4];
            for (int k = 1; k <= 7; k++) {
                if (isMoveLegal(i + k, j - k, marbleColor) && !firstEnemyFounded[0]) {
                    temp = new Coordinates();
                    temp.x = i + k;
                    temp.y = j - k;
                    coordinates.add(temp);
                    if (house[temp.x][temp.y].marbleColor == polarColor(marbleColor)) {
                        firstEnemyFounded[0] = true;
                    }
                } else {
                    break;
                }
            }
            for (int k = 1; k <= 7; k++) {
                if (isMoveLegal(i - k, j + k, marbleColor) && !firstEnemyFounded[1]) {
                    temp = new Coordinates();
                    temp.x = i - k;
                    temp.y = j + k;
                    coordinates.add(temp);
                    if (house[temp.x][temp.y].marbleColor == polarColor(marbleColor)) {
                        firstEnemyFounded[1] = true;
                    }
                } else {
                    break;
                }
            }
            for (int k = 1; k <= 7; k++) {
                if (isMoveLegal(i - k, j - k, marbleColor) && !firstEnemyFounded[2]) {
                    temp = new Coordinates();
                    temp.x = i - k;
                    temp.y = j - k;
                    coordinates.add(temp);
                    if (house[temp.x][temp.y].marbleColor == polarColor(marbleColor)) {
                        firstEnemyFounded[2] = true;
                    }
                } else {
                    break;
                }
            }
            for (int k = 1; k <= 7; k++) {
                if (isMoveLegal(i + k, j + k, marbleColor) && !firstEnemyFounded[3]) {
                    temp = new Coordinates();
                    temp.x = i + k;
                    temp.y = j + k;
                    coordinates.add(temp);
                    if (house[temp.x][temp.y].marbleColor == polarColor(marbleColor)) {
                        firstEnemyFounded[3] = true;
                    }
                } else {
                    break;
                }
            }


        }
        if (marble.equals("King")) {
            if (isMoveLegal(i - 1, j - 1, marbleColor) && !checkCheck(i - 1, j - 1, marbleColor)) {
                temp = new Coordinates();
                temp.x = i - 1;
                temp.y = j - 1;
                coordinates.add(temp);
            }
            if (isMoveLegal(i - 1, j + 1, marbleColor) && !checkCheck(i - 1, j + 1, marbleColor)) {
                temp = new Coordinates();
                temp.x = i - 1;
                temp.y = j + 1;
                coordinates.add(temp);
            }
            if (isMoveLegal(i + 1, j - 1, marbleColor) && !checkCheck(i + 1, j - 1, marbleColor)) {
                temp = new Coordinates();
                temp.x = i + 1;
                temp.y = j - 1;
                coordinates.add(temp);
            }
            if (isMoveLegal(i + 1, j + 1, marbleColor) && !checkCheck(i + 1, j + 1, marbleColor)) {
                temp = new Coordinates();
                temp.x = i + 1;
                temp.y = j + 1;
                coordinates.add(temp);
            }
            if (isMoveLegal(i, j - 1, marbleColor) && !checkCheck(i, j - 1, marbleColor)) {
                temp = new Coordinates();
                temp.x = i;
                temp.y = j - 1;
                coordinates.add(temp);
            }
            if (isMoveLegal(i, j + 1, marbleColor) && !checkCheck(i, j + 1, marbleColor)) {
                temp = new Coordinates();
                temp.x = i;
                temp.y = j + 1;
                coordinates.add(temp);
            }
            if (isMoveLegal(i - 1, j, marbleColor) && !checkCheck(i - 1, j, marbleColor)) {
                temp = new Coordinates();
                temp.x = i - 1;
                temp.y = j;
                coordinates.add(temp);
            }
            if (isMoveLegal(i + 1, j, marbleColor) && !checkCheck(i + 1, j, marbleColor)) {
                temp = new Coordinates();
                temp.x = i + 1;
                temp.y = j;
                coordinates.add(temp);
            }

        }
        return coordinates;
    }

    private static boolean isMoveLegal(int i, int j, String marbleColor) {
        if (i >= 0 && i <= 7 && j >= 0 && j <= 7 && !house[i][j].marbleColor.equals(marbleColor)) {
            return true;
        }
        return false;
    }

    private static String polarColor(String color) {
        if (color.equals("White")) {
            return "Black";
        }
        return "White";
    }

    private static boolean checkCheck(int row, int column, String marbleColor) {
        ArrayList<Coordinates> temp;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (house[i][j].marbleColor.equals(polarColor(marbleColor)) && !house[i][j].marble.equals("Pawn")
                        && !house[i][j].marble.equals("King")) {
                    temp = possibleMoves(house[i][j].marble, house[i][j].marbleColor, i, j);
                    System.err.println(temp.size());
                    for (int k = 0; k < temp.size(); k++) {
                        if (temp.get(k).x == row && temp.get(k).y == column) {
                            return true;
                        }
                    }
                }
            }
        }
        if ((house[row - 1][column - 1].marble.equals("Pawn")
                && house[row - 1][column - 1].marbleColor.equals(polarColor(playerColor)))
                || (house[row - 1][column + 1].marble.equals("Pawn")
                && house[row - 1][column + 1].marbleColor.equals(polarColor(playerColor)))) {
            return true;
        }
        return false;
    }

    private void houseInitializer() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (i == 0 || i == 7) {
                    if (j == 0 || j == 7) {
                        house[i][j] = new House("Rook", i, j);
                        gridPane.add(house[i][j].imageView, j, i);
                    } else if (j == 1 || j == 6) {
                        house[i][j] = new House("Knight", i, j);
                        gridPane.add(house[i][j].imageView, j, i);
                    } else if (j == 2 || j == 5) {
                        house[i][j] = new House("Bishop", i, j);
                        gridPane.add(house[i][j].imageView, j, i);
                    } else if (j == 3) {
                        if (Client.playerColor.equals("Black")) {
                            house[i][j] = new House("King", i, j);
                            gridPane.add(house[i][j].imageView, j, i);
                        } else {
                            house[i][j] = new House("Queen", i, j);
                            gridPane.add(house[i][j].imageView, j, i);
                        }
                    } else if (j == 4) {
                        if (Client.playerColor.equals("Black")) {
                            house[i][j] = new House("Queen", i, j);
                            gridPane.add(house[i][j].imageView, j, i);
                        } else {
                            house[i][j] = new House("King", i, j);
                            gridPane.add(house[i][j].imageView, j, i);
                        }
                    }
                } else if (i == 1 || i == 6) {
                    house[i][j] = new House("Pawn", i, j);
                    gridPane.add(house[i][j].imageView, j, i);
                } else {
                    house[i][j] = new House("", i, j);
                    gridPane.add(house[i][j].imageView, j, i);
                }
                gridPane.add(house[i][j].highlight, j, i);
            }
        }
    }
//    Timer t = new Timer(1000, new ClockListener());
}

//class ClockListener implements ActionListener {
//    int counter = 600;
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        System.out.println(co);
//    }
//}