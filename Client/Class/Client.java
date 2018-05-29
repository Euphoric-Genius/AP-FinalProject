package Client.Class;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;



public class Client extends Application {
    public static String playerColor = System.getenv("Color");
    public static boolean isPlayerTurn = Boolean.valueOf(System.getenv("Turn"));
    public static boolean isChecked = false;
    public static House recentHouse;
    public static Socket socket;
    public static ObjectOutputStream outputStream;
    public static ObjectInputStream inputStream;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Client/FXML/sample.fxml"));
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root, 650, 650));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        Client.socket = new Socket("127.0.0.1", 1234);
        Client.outputStream = new ObjectOutputStream(Client.socket.getOutputStream());
        Client.inputStream = new ObjectInputStream(Client.socket.getInputStream());
        launch(args);
    }


}
