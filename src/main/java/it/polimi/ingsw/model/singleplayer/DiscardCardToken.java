package it.polimi.ingsw.model.singleplayer;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.gameboard.Color;

/**
 * Contains the color and amount of development card to discard.
 */
public class DiscardCardToken extends Token {
    private final String id;
    private final Color color;
    private final Game game;
    private final int discardQuantity;

    public DiscardCardToken(Game game, Color color, int discardQuantity, String id) {
        this.id = id;
        this.game = game;
        this.color = color;
        this.discardQuantity = discardQuantity;
    }

    public String getClientMessage(){return "Lorenzo Il Magnifico removed " + discardQuantity + " " + color + " cards";}

    /**
     * discards as many development cards as discard quantity.
     * (the level of discarded card have to be lowest possible)
     */
    public void activateEffect(){
        game.getBoard().removeLowestLevel(color, discardQuantity);
    }
}
