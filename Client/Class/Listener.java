package Client.Class;

import Client.Controller.BoardController;

public class Listener implements Runnable {
    @Override
    public void run() {
        RecentMove recentMove;
        while (true) {
            if (Client.inputStream != null) {
                try {
                    recentMove = (RecentMove) Client.inputStream.readObject();
                    Client.isPlayerTurn = true;
                    BoardController.move(7 - recentMove.initX, 7 - recentMove.initY
                            , 7 - recentMove.finalX, 7 - recentMove.finalY);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}