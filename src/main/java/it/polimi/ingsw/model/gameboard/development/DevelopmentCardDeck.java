package it.polimi.ingsw.model.gameboard.development;

import it.polimi.ingsw.model.exceptions.NoCardsLeftException;
import it.polimi.ingsw.model.gameboard.Color;

import java.util.ArrayList;
import java.util.Random;
/**
 * class that represents a deck of up to 4 development cards
 */
public class DevelopmentCardDeck {
    private final ArrayList<DevelopmentCard> deck = new ArrayList<>();
    private final int level;
    private final Color color;
    private int size;

    public DevelopmentCardDeck(ArrayList<DevelopmentCard> deck, int level, Color color){
        this.level = level;
        this.color = color;
        this.size = deck.size();

        Random rand = new Random();
        int randomIndex;

        for(int i=0; i < size; i++) {
            randomIndex = rand.nextInt(deck.size());
            this.deck.add(deck.get(randomIndex));
            deck.remove(randomIndex);
        }
    }

    public ArrayList<DevelopmentCard> getDeck() {
        return deck;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public int getLevel() {
        return level;
    }

    /**
     *
     * @return true if the deck is empty, false otherwise
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     *
     * @return returns the first card of the deck
     * @throws NoCardsLeftException
     */
    public DevelopmentCard lookFirst() throws NoCardsLeftException {
        if(isEmpty()) throw new NoCardsLeftException();
        else return deck.get(0);
    }

    /**
     * removes the first card of the deck
     * @throws NoCardsLeftException
     */
    public DevelopmentCard removeFirst() throws NoCardsLeftException{
        if(isEmpty()) throw new NoCardsLeftException();
        else {
            size = size - 1;
            return deck.remove(0);
        }
    }

}
