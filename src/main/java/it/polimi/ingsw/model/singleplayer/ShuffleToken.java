package it.polimi.ingsw.model.singleplayer;

import it.polimi.ingsw.model.Game;

/**
 * Contains how many times the token must be shuffled.
 */
public class ShuffleToken extends Token {
    private final String id;
    private final Game game;
    private final int faithGain;
    private final int shuffle;

    public ShuffleToken(Game game, int faithGain, int shuffle, String id) {
        this.id = id;
        this.game = game;
        this.faithGain = faithGain;
        this.shuffle = shuffle;
    }

    public String getClientMessage(){return "Lorenzo Il Magnifico gained " + faithGain +  " faith points and shuffled his token deck";}

    /**
     * increases the position of Lorenzo of a faithGain quantity
     * and shuffles the tokenDeck.
     */
    public void activateEffect(){
        for(int i=0; i<faithGain; i++){
            game.getLorenzo().getTrack().increasePosition();
        }
        for(int i=0; i<shuffle; i++){
            game.getLorenzo().getTokens().shuffle();
        }
    }
}
