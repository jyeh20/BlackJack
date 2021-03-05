package game;

import java.util.*;

public class Hand {
    
    // Fields
    // --------------------------
    private LinkedList<Card> cards;
    private boolean dealer;
    private boolean stand;
    private boolean bust;
    
    // Constructor
    // --------------------------
    
    /**
     * Constructs a new instance of a hand that players/dealers will use
     * in a game of BlackJack
     */
    public Hand(boolean dealer) {
        this.cards = new LinkedList<Card>();
        this.dealer = dealer;
        this.stand = false;
        this.bust = false;
    }
    
    // Methods
    // --------------------------
    
    /**
     * Returns the number of cards in this hand
     * @return the size of this card array
     */
    public int getSize() {
        return this.cards.size();
    }
    
    /**
     * Returns the value of the this hand
     * @return the sum of Card values in this hand
     */
    public int getValue() {
        int value = 0;
        for (Card card : this.cards) {
            value += card.getValue();
        }
        return value;
    }
    
    /**
     * Returns whether this hand's value is over 21
     * @return true if this hand is busted, false otherwise
     */
    public boolean isBust() {
        return this.bust;
    }
    
    /**
     * Returns whether this hand is allowed to draw more cards
     * @return true if this hand is standing
     */
    public boolean isStand() {
        return this.stand;
    }
    
    /**
     * Returns whether this hand is the dealer
     * @return true if this hand is the dealer, false otherwise
     */
    public boolean isDealer() {
        return this.dealer;
    }
    
    /**
     * This hand chooses to or is forced to stand instead of hit
     * @return true if this hand is standing
     */
    public boolean stand() {
        this.stand = true;
        return this.stand;
    }
    
    /**
     * Adds a new card to this hand and returns its new value
     * @param card The new card that is being added to the hand
     * @return the new value of this hand
     */
    public boolean addCard(Card card) {
        if (this.stand) {
            System.out.println("Player is standing");
            return this.bust;
        }
        if (this.getValue() >= 17 && this.dealer) {
            System.out.println("Dealer cannot hit!");
            return this.bust;
        }
        if (card.getFace().equals("Ace")) {
            this.cards.addFirst(card);
        } else {
            this.cards.addLast(card);
        }
        if (this.getValue() > 21) {
            if (this.cards.getFirst().getValue() == 11) {
                this.cards.getFirst().changeValue();
                this.cards.addLast(this.cards.remove());
            } else {
                this.bust = true;
            }
        }
        if (this.getValue() >= 17 && this.dealer) {
            this.stand();
        }
        return this.bust;
    }
    
    /**
     * Returns the hand as a String of the values of the cards in the hand
     * @return the numerical value of the cards as an array
     */
    @Override
    public String toString() {
        return this.cards.toString();
    }
}
