package it.polimi.ingsw.messages.serverToClient;

import it.polimi.ingsw.client.ClientView;
import it.polimi.ingsw.client.PingReceiver;

/**
 * this class communicates the dev card grid to the client
 */
public class DevCardGrid implements ServerMessage{
    private final String[][] devCardId;

    public DevCardGrid(String[][] devCardId) {
        this.devCardId = devCardId;
    }

    @Override
    public void handleMessage(ClientView clientView) { clientView.developmentCardGridSetup(devCardId); }

    @Override
    public void handleMessage(PingReceiver pingManager) { }
}
