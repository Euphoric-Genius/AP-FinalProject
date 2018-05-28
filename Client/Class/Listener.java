package Client.Class;

import Client.Controller.BoardController;

import java.io.IOException;
import java.io.StreamCorruptedException;

import static Client.Class.Client.*;

public class Listener implements Runnable {
    @Override
    public void run() {
        RecentMove recentMove;
        while (true) {
            try {
                isPlayerTurn = true;
                System.out.println("1");
                try {
                    recentMove = (RecentMove) Client.ois.readObject();
                    BoardController.move(7 - recentMove.initX, recentMove.initY, 7 - recentMove.finalX, recentMove.finalY);
                } catch (Exception e) {
                    System.err.println("hell");
                    e.printStackTrace();
                }
                System.out.println("2");
            } catch (Exception e) {
                System.err.println("++");
                e.printStackTrace();
            }

        }

    }
}