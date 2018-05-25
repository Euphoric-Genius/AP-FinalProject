package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static Class.Board.*;

import Class.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;


public class BoardController implements Initializable {


    @FXML
    GridPane gridPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
            if (isMoveLegal(i - 1, j, marbleColor)) {
                boolean firstEnemyFounded = false;
                temp.x = i - 1;
                temp.y = j;
                coordinates.add(temp);
                if (house[temp.x][temp.y].marbleColor == polarColor(marbleColor)) {
                    firstEnemyFounded = true;
                }
                if (i == 6 && isMoveLegal(4, j, marbleColor) && !firstEnemyFounded) {
                    temp = new Coordinates();
                    temp.x = i - 2;
                    temp.y = j;
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
            if (isMoveLegal(i - 1, j - 1, marbleColor)) {
                temp = new Coordinates();
                temp.x = i - 1;
                temp.y = j - 1;
                coordinates.add(temp);
            }
            if (isMoveLegal(i - 1, j + 1, marbleColor)) {
                temp = new Coordinates();
                temp.x = i - 1;
                temp.y = j + 1;
                coordinates.add(temp);
            }
            if (isMoveLegal(i + 1, j - 1, marbleColor)) {
                temp = new Coordinates();
                temp.x = i + 1;
                temp.y = j - 1;
                coordinates.add(temp);
            }
            if (isMoveLegal(i + 1, j + 1, marbleColor)) {
                temp = new Coordinates();
                temp.x = i + 1;
                temp.y = j + 1;
                coordinates.add(temp);
            }
            if (isMoveLegal(i, j - 1, marbleColor)) {
                temp = new Coordinates();
                temp.x = i;
                temp.y = j - 1;
                coordinates.add(temp);
            }
            if (isMoveLegal(i, j + 1, marbleColor)) {
                temp = new Coordinates();
                temp.x = i;
                temp.y = j + 1;
                coordinates.add(temp);
            }
            if (isMoveLegal(i - 1, j, marbleColor)) {
                temp = new Coordinates();
                temp.x = i - 1;
                temp.y = j;
                coordinates.add(temp);
            }
            if (isMoveLegal(i + 1, j, marbleColor)) {
                temp = new Coordinates();
                temp.x = i + 1;
                temp.y = j;
                coordinates.add(temp);
            }

        }
//        for(int k=0;k<coordinates.size();k++){
//            System.out.println(coordinates.get(k).x+" "+coordinates.get(k).y);
//        }
        return coordinates;
    }

    private static boolean isMoveLegal(int i, int j, String marbleColor) {
        if (i >= 0 && i <= 7 && j >= 0 && j <= 7 && house[i][j].marbleColor != marbleColor) {
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
                        house[i][j] = new House("King", i, j);
                        gridPane.add(house[i][j].imageView, j, i);
                    } else if (j == 4) {
                        house[i][j] = new House("Queen", i, j);
                        gridPane.add(house[i][j].imageView, j, i);
                    }
                } else if (i == 1 || i == 6) {
                    house[i][j] = new House("Pawn", i, j);
                    gridPane.add(house[i][j].imageView, j, i);
                } else {
                    house[i][j] = new House(null, i, j);
                    gridPane.add(house[i][j].imageView, j, i);
                }
                gridPane.add(house[i][j].highlight, j, i);
            }
        }
//        house[3][3].marble = "King";
//        house[3][3].x = 3;
//        house[3][3].y = 3;
//        house[3][3].marbleColor = "Black";
//        house[3][3].imageView.setImage(new Image("/Files/blackKing.png", 60, 60, false, true));
//        gridPane.add(house[3][3].imageView, 3, 3);
    }
}
