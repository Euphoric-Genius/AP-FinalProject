package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    static int i = 1;
    public static ArrayList<ClientHandler> clientArray = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        while (true) {

            Socket Currentsocket = serverSocket.accept();



            ClientHandler cli = new ClientHandler(Currentsocket, "Client" + i);

            Thread t = new Thread(cli);

            System.out.println("Adding this client to active client list");
            clientArray.add(cli);
            t.start();
            System.out.println(t.isAlive());
            i++;

        }

    }

}
