package game;

import java.util.Objects;
import java.util.TreeSet;

public class Card implements Comparable<Card> {
    
    // Fields
    //-------------------------------------
    private String face;
    private String suit;
    private int value;
    
    // Constructor
    //-------------------------------------
    /**
     * Constructs a new numerical card to be used in a Blackjack deck
     * @param value the value of this card
     * @param suit the suit of this card ["Hearts", "Clubs", "Diamonds", "Spades"]
     */
    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
        this.face = String.valueOf(value);
    }
    
    /**
     * Constructs a new "face" card to be used in a Blackjack deck
     * @param value the value of this card
     * @param suit the suit of this card ["Hearts", "Clubs", "Diamonds", "Spades"]
     * @param face the face of this card ["Jack", "Queen", "King", "Ace"]
     */
    public Card(int value, String suit, String face) {
        this.value = value;
        this.suit = suit;
        this.face = face;
    }
    
    
    // Methods
    // -------------------------------------------
    
    /**
     * Returns the value of this card
     * @return the value of this card
     */
    public int getValue() {
        return this.value;
    }
    
    /**
     * Gets the face of this card
     * @return the face of this card
     */
    public String getFace() {
        return this.face;
    }
    
    /**
     * If this card is an Ace, change it's value from 11 to 1
     */
    public void changeValue() {
        if (!this.face.equals("Ace")) {
            System.out.println("This card is not an Ace!");
            return;
        }
        this.value = 1;
    }
    
    @Override
    public String toString() {
        return ("[" + this.value + "] " + this.face + " of " + this.suit);
    }
    
    @Override
    public int compareTo(Card other) {
        return (this.value + this.suitToInt()) - (other.value + other.suitToInt());
    }
    
    private int suitToInt() {
        switch (this.suit) {
            case "Hearts":
                return 4;
            case "Clubs":
                return 3;
            case "Diamonds":
                return 2;
            case "Spades":
                return 1;
            default:
                return 0;
        }
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == this) { return true; }
        if (!(other instanceof Card)) { return false; }
        Card otherCast = (Card) other;
        return this.value == otherCast.value && this.face.equals(otherCast.face) && this.suit.equals(otherCast.suit);
    }
    
    @Override
    public int hashCode () {
        return Objects.hash(this.value, (int)this.suit.charAt(0));
    }
    
    public static void main(String[] args) {
        TreeSet<Card> set = new TreeSet<Card>();
        Card a1 = new Card(11, "Hearts", "Ace");
        Card a2 = new Card(11, "Spades", "Ace");
        set.add(a1);
        set.add(a2);
        System.out.println(set.toString());
        System.out.println(a1.compareTo(a2));
        a2.changeValue();
        System.out.println(set.toString());
        System.out.println(a1.compareTo(a2));
        System.out.println(set.toString());

    }
}
