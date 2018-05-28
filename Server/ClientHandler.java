package Server;

import Client.Class.RecentMove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;


public class ClientHandler implements Runnable {
    ObjectOutputStream oos;
    ObjectInputStream ois;
    //    Scanner oisS;
//    Formatter oosF;
    String Username;
    Socket s;
    boolean flag = true;

    @Override
    public void run() {
        RecentMove recentMove;
        while (true) {

            if (ois != null) {
                try {
                    recentMove = (RecentMove) ois.readObject();
                    for (ClientHandler i : Server.clientArray) {
                        if (i.Username != this.Username) {
//                            i.oosF.format(oisS.next());
//                            i.oosF.flush();
                            System.out.println(i.Username);
                            i.oos.writeObject(recentMove);
                        }
                    }
                    break;

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ClientHandler(Socket s, String Useranme) throws Exception {

        this.s = s;
        this.Username = Useranme;
        this.ois = new ObjectInputStream(s.getInputStream());
        this.oos = new ObjectOutputStream(s.getOutputStream());
//        this.oisS = new Scanner(s.getInputStream());
//        this.oosF = new Formatter(s.getOutputStream());
//        System.out.println("hi");
    }

}
