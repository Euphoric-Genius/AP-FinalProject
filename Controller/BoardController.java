package Controller;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

import static Class.Board.*;

import Class.House;


public class BoardController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (i == 0 || i == 7) {
                    if (j == 0 || j == 7) {
                        house[i][j] = new House("Rook");
                    } else if (j == 1 || j == 6) {
                        house[i][j] = new House("Knight");
                    } else if (j == 2 || j == 5) {
                        house[i][j] = new House("Bishop");
                    } else if (j == 3) {
                        house[i][j] = new House("King");
                    } else if (j == 4) {
                        house[i][j] = new House("Queen");
                    }
                }
                else if(i==1 || i==6){
                    house[i][j]=new House("Pawn");
                }
                else
                    house[i][j]=new House();
            }

        }

    }
}
