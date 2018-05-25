package Class;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static House recentHouse;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/sample.fxml"));
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root, 650, 650));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
