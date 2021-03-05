package game;

import java.util.*;

public class Deck {

    // Fields
    // --------------------------------
    private int remainingCards;
    private LinkedList<Card> cards;
    
    final String[] SUITS = {"Hearts", "Clubs", "Diamonds", "Spades"};
    final int STARTING_CARDS = 52;
    
    // Constructor
    // --------------------------------
    /**
     * Constructs a new 52 card deck
     */
    public Deck() {
        this.remainingCards = STARTING_CARDS;
        this.cards = new LinkedList<Card>();
        for (int i = 1; i <= 13; i++) {
            switch(i) {
                case 1:
                    for (int j = 0; j < 4; j++) {
                        this.cards.add(new Card(11, SUITS[j], "Ace"));
                    }
                    break;
                case 11:
                    for (int j = 0; j < 4; j++) {
                        this.cards.add(new Card(10, SUITS[j], "Jack"));
                    }
                    break;
                case 12:
                    for (int j = 0; j < 4; j++) {
                        this.cards.add(new Card(10, SUITS[j], "Queen"));
                    }
                    break;
                case 13:
                    for (int j = 0; j < 4; j++) {
                        this.cards.add(new Card(10, SUITS[j], "King"));
                    }
                    break;
                default:
                    for (int j = 0; j < 4; j++) {
                        this.cards.add(new Card(i, SUITS[j]));
                    }
            }
        }
    }
    
    // Methods
    // --------------------------------
    
    /**
     * Shuffles the deck
     */
    public void shuffle() {
        LinkedList<Card> shuffledDeck = new LinkedList<Card>();
        for (int i = this.remainingCards-1; i >= 0; i--) {
            int index = (i % 2 == 0) ? (int)Math.ceil(Math.random() * i) : (int)Math.floor(Math.random() * i);
            shuffledDeck.add(this.cards.get(index));
            this.cards.remove(index);
        }
        this.cards = shuffledDeck;
    }
    
    /**
     * Draw the top card from the deck
     * @return Removes the "top" card and returns it
     */
    public Card draw() {
        this.remainingCards--;
        return this.cards.remove();
    }
    
    public static void main(String[] args) {
        Deck d1 = new Deck();
        System.out.println(d1.cards.size());
        System.out.println(d1.cards.toString());
        d1.shuffle();
        System.out.println(d1.cards.size());
        System.out.println(d1.cards.toString());
        for (int i = 0; i < 10000; i++) {
            HashSet<Card> visited = new HashSet<Card>();
            for (Card card : d1.cards) {
                if (visited.contains(card)) {
                    System.out.println("dupe");
                    break;
                }
                visited.add(card);
            }
        }
    }
    
}
