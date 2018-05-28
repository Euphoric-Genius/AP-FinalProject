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
import java.util.Formatter;
import java.util.Scanner;



public class Client extends Application {
    public static String playerColor = System.getenv("CHESS_COLOR");
    public static boolean isPlayerTurn = Boolean.getBoolean(System.getenv("PLAYER_TURN"));
    public static boolean isChecked = false;
    public static House recentHouse;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Thread t = new Thread(new Listener());
        t.start();
        Parent root = FXMLLoader.load(getClass().getResource("/Client/FXML/sample.fxml"));
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root, 650, 650));
        primaryStage.show();
    }

    static Socket socket;
    public static ObjectOutputStream oos;
    public static ObjectInputStream ois;
//    public static Scanner oisS;
//    public static Formatter oosF;

    public static void main(String[] args) throws IOException {
        Thread t = new Thread(() -> {
            try {
                socket = new Socket("127.0.0.1", 5000);
//                oisS = new Scanner(socket.getInputStream());
//                oosF = new Formatter(socket.getOutputStream());
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.start();
        //socket = new Socket("LocalHost", 5000);


        launch(args);


    }


}
