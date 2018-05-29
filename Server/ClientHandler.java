package Server;

import Client.Class.RecentMove;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    ObjectOutputStream clientOutput;
    ObjectInputStream clientInput;
    String username;
    Socket socket;

    @Override
    public void run() {
        RecentMove recentMove;
        while (true) {
            if (clientInput != null) {
                try {
                    recentMove = (RecentMove) clientInput.readObject();
                    for (ClientHandler i : Server.clientArray) {
                        if (i.username != this.username) {
                            System.out.println(i.username);
                            i.clientOutput.writeObject(recentMove);
                        }
                    }
                   // break;?????????? Seriously? This is where all the problems begin.....!
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ClientHandler(Socket socket, String username) throws Exception {
        this.socket = socket;
        this.username = username;
        this.clientInput = new ObjectInputStream(socket.getInputStream());
        this.clientOutput = new ObjectOutputStream(socket.getOutputStream());
    }

}
