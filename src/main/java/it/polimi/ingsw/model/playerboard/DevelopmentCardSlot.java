package it.polimi.ingsw.model.playerboard;

import it.polimi.ingsw.model.gameboard.Color;
import it.polimi.ingsw.model.gameboard.development.DevelopmentCard;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the slot of the board where
 * a player place it after a Buy Development Card Action.
 */
public class DevelopmentCardSlot {
    private final ArrayList<DevelopmentCard> slots = new ArrayList<>();
    private int cardCounter=0;
    HashMap<Color,Integer> map;
    int count;

    /**
     * adding a card to the slot
     * @param card to add
     */
    public void addCardOnTop(DevelopmentCard card, PlayerBoard playerBoard) {
        this.slots.add(card);
        this.cardCounter++;
        HashMap<Integer, HashMap<Color, Integer>>  colorRequirements =   new HashMap<>(playerBoard.getColorRequirements());
        map = new HashMap<>(colorRequirements.get(card.getLevel()));
        count = map.get(card.getColor());
        map.put(card.getColor(),count+1);
        colorRequirements.put(card.getLevel(),map);
        playerBoard.setColorRequirements(colorRequirements);

    }

    public boolean validAction(DevelopmentCard card){
        if(card.getLevel()<1 || card.getLevel()>3) return false;

        if(isEmpty() && card.getLevel()==1) return true;
        else if (isEmpty() && card.getLevel()>1) return false;
        else {
            return slots.get(cardCounter - 1).getLevel() == card.getLevel()-1;
        }
    }

    /**
     * @return of the currently active card
     */
    public DevelopmentCard lookTop() {
        if (isEmpty()) return null;
        else {
            return slots.get(cardCounter - 1);
        }
    }

    /**
     * @return of the specific card in place @param position
     */
    public DevelopmentCard getCard(int position){
        return slots.get(position);
    }

    /**
     *
     * @return true if the slot is full
     */
    public boolean isFull(){
        if (cardCounter==0) return false;
        return slots.get(cardCounter - 1).getLevel() == 3;
    }

    /**
     *
     * @return true if the slot is empty
     */
    public boolean isEmpty(){
        return cardCounter == 0;
    }

    /**
     *
     * @return of the number of cards
     */
    public int numOfCards(){return cardCounter;}

    /**
     * @return list of the ids ordered from level 1 to level 3
     */
    public ArrayList<String> getCardsIds(){
        ArrayList<String> ids = new ArrayList<>();
        for(int i = 0; i<cardCounter; i++){
            ids.add(getCard(i).getId());
        }
        return ids;
    }
}
