package Client.Class;

import Client.Controller.BoardController;

public class Listener implements Runnable {
    @Override
    public void run() {
        RecentMove recentMove;
        while (true) {
//            if (Client.SinputStream!=null)
//            {
//                Client.SinputStream.nextLine();
//            }
            if (Client.inputStream != null) {
                try {
                    recentMove = (RecentMove) Client.inputStream.readObject();
                    Client.isPlayerTurn = true;
                    BoardController.move(7 - recentMove.initX, 7 - recentMove.initY, 7 - recentMove.finalX, 7 - recentMove.finalY);
                    BoardController.OpponentTime.stop();
                    Client.OpponentfirstMove=false;
                    if (!Client.OwnfirstMove)
                        BoardController.OwnTime.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}