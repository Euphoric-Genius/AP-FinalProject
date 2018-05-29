package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static ArrayList<ClientHandler> clientArray = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1234);
        int i = 0;
        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientHandler cli = new ClientHandler(clientSocket, "Client" + i);
            Thread thread = new Thread(cli);
            clientArray.add(cli);
            thread.start();
            i++;
        }
    }
}
