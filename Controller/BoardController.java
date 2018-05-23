package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

import static Class.Board.*;

import Class.House;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class BoardController implements Initializable {


    @FXML
    GridPane gridPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (i == 0 || i == 7) {
                    if (j == 0 || j == 7) {
                        house[i][j] = new House("Rook");
                        gridPane.add(house[i][j].imageView,j,i);

                    } else if (j == 1 || j == 6) {
                        house[i][j] = new House("Knight");
                        gridPane.add(house[i][j].imageView,j,i);
                    } else if (j == 2 || j == 5) {
                        house[i][j] = new House("Bishop");
                        gridPane.add(house[i][j].imageView,j,i);
                    } else if (j == 3) {
                        house[i][j] = new House("King");
                        gridPane.add(house[i][j].imageView,j,i);
                    } else if (j == 4) {
                        house[i][j] = new House("Queen");
                        gridPane.add(house[i][j].imageView,j,i);
                    }
                } else if (i == 1 || i == 6) {
                    house[i][j] = new House("Pawn");
                    gridPane.add(house[i][j].imageView,j,i);
                } else
                    house[i][j] = new House();
            }

        }
        //imageLoader();

    }


//    public void imageLoader() {
//        for (int i = 0; i <8 ; i++) {
//            for (int j = 0; j <8 ; j++) {
//
//
//            }
//
//        }
//
//    }

}
