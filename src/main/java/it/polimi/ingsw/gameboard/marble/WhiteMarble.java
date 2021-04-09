package it.polimi.ingsw.gameboard.marble;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.Resource;
import it.polimi.ingsw.gameboard.marble.Marbles;

import java.util.ArrayList;

public class WhiteMarble extends Marbles {

    private final Game game;
    public WhiteMarble(Game game){
        this.game=game;
    }
    @Override
    public void drawEffect(ArrayList<Resource> resources) {
        if(game.getActivePlayer().getPlayerBoard().getLeaderCardBuffs().isExchangeBuffActive()){
            resources.add(game.getActivePlayer().getPlayerBoard().getLeaderCardBuffs().getActiveExchangeBuff());
        }
    }
}