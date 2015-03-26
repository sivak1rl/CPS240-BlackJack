import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BlackJackGame {

	double[] deck;

	public BlackJackGame() {
		deck = new double[52];
		int count = 0;
		for (int i = 1; i < 14; i++) {
			for (int j = 0; j < 4; j++) {
				deck[count] = i % 13 + j / 4.0;
				if (deck[count] < 1) {
					deck[count] += 13;
				}
				count++;
			}
		}
	}

	/**
	 * Creates a game of blackjack (an array of doubles) a list (deck) of cards
	 * (Card Object) based on BlackJackGame.deck and a player hand. deals to the player and asks for a hit.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		List<Card> cardDeck = new LinkedList<Card>();
		BlackJackGame bjg = new BlackJackGame();
		PlayerHand hand1 = new PlayerHand();
		for (double d : bjg.deck) {
			cardDeck.add(new Card(d));
		}
		Collections.shuffle(cardDeck);
		hand1.Deal(cardDeck.remove(0), cardDeck.remove(0));
		System.out.println(hand1);
		Scanner sc = new Scanner(System.in);
		String input;
		//
		System.out
				.print("Do you want a hit? (y for yes, anything else for no): ");
		input = sc.next();
		while (input.equals("y") && hand1.score <= 21) {
			hand1.Hit(cardDeck.remove(0));
			System.out.println(hand1);
			if (hand1.score <= 21) {
				System.out
						.print("Do you want a hit? (y for yes, anything else for no): ");
				input = sc.next();
			}
		}
		sc.close();
	}
}
