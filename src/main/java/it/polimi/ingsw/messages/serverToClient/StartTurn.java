package it.polimi.ingsw.messages.serverToClient;

import it.polimi.ingsw.client.ClientView;
import it.polimi.ingsw.client.PingReceiver;

public class StartTurn implements ServerMessage{
    private final String turn = "START TURN!";


    @Override
    public void handleMessage(ClientView clientView) {


    }

    @Override
    public void handleMessage(PingReceiver pingManager) {

    }
}
