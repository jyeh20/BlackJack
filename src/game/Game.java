package game;

import java.util.*;

public class Game {
    
    /**
     * Starts the game by giving the dealer and player starting hand of
     * 2 cards
     * @param deck The deck of this game
     * @param dealer The dealer of this game
     * @param player The player of this game
     */
    private static void startGame(Deck deck, Hand dealer, Hand player) {
        deck.shuffle();
        player.addCard(deck.draw());
        dealer.addCard(deck.draw());
        player.addCard(deck.draw());
        Card visible = deck.draw();
        dealer.addCard(visible);
        System.out.println("Dealer is showing a " + visible.toString() + "\n");
        report(player);
    }
    
    private static int playerWin(Hand dealer, Hand player) {
        if (player.getValue() > dealer.getValue() && !player.isBust()) {
            return 1;
        } if (player.getValue() < dealer.getValue() && !dealer.isBust()) {
            return -1;
        } if (dealer.isBust()) {
            return 1;
        }
        return 0;
    }
    
    private static void report(Hand hand) {
        if (hand.isDealer()) {
            System.out.println("Dealer's hand : " + hand.toString());
        } else {
            System.out.println("Your hand: " + hand.toString());
        }
        System.out.println("Value: " + hand.getValue());
        System.out.println();
    }
    
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        
        Deck deck = new Deck();
        Hand dealer = new Hand(true);
        Hand player = new Hand(false);
        
        System.out.println("Starting a new game of BlackJack! \n");
        startGame(deck, dealer, player);
        
        // Player's Turn
        while (!player.isBust() && !player.isStand()) {
            System.out.println("Hit [H] or Stand [S]?");
            String playerAct = input.nextLine();
            switch(playerAct.toLowerCase()) {
                case "h":
                    Card card = deck.draw();
                    if (player.addCard(card)) {
                        System.out.println("Busted! Hand Value: " + player.getValue());
                        return;
                    } else {
                        System.out.println("You got the " + card.toString());
                        report(player);
                    }
                    break;
                case "s":
                    player.stand();
                    break;
                default:
                    System.out.println("Invalid move!");
            }
        }
        
        System.out.println("\n============================");
        System.out.println("Dealer's Turn");
        System.out.println("============================ \n");
        
        report(dealer);
        while (!dealer.isBust() && !dealer.isStand() && dealer.getValue() < 17) {
            dealer.addCard(deck.draw());
            report(dealer);
        }
        
        int win = playerWin(dealer, player);
        switch (win) {
            case 1:
                System.out.println("Player wins!");
                break;
            case -1:
                System.out.println("Dealer wins!");
                break;
            default:
                System.out.println("Draw!");
        }
        input.close();
    }
}
