import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//
public class AgainstDealer {
	
	public static void main(String[] args) {

		BlackJackGame game = new BlackJackGame();
		List<PlayerHand[]> hands = new LinkedList<PlayerHand[]>();
		DealerHand dHand = new DealerHand();
		List<Card> deck = new LinkedList<Card>();
		for (double d : game.deck) {
			deck.add(new Card(d));
		}
		// Collections.shuffle(deck);
		String input;
		int numOfPlayers;
		Scanner sc = new Scanner(System.in);
		System.out.print("How many people are playing? (5 or less): ");
		numOfPlayers = sc.nextInt();
		while (numOfPlayers > 5 || numOfPlayers < 1) {
			System.out
					.print("You must have a player and can't have more than 5.\nEnter another number: ");
			numOfPlayers = sc.nextInt();
		}
		for (int i = 0; i < numOfPlayers; i++) {
			hands.add(new PlayerHand[1]);
			hands.get(i)[0] = new PlayerHand();
			hands.get(i)[0].Deal(deck);
		}
		dHand.Deal(deck);
		for (int i = 0; i < hands.size(); i++) {
			int pNumber = i + 1;
			System.out.println("Player " + pNumber + ":");
			System.out.println(hands.get(i)[0]);
		}
		System.out.println("\nThe dealer's up card is " + dHand.getUpCard()
				+ "\n");
		for (int i = 0; i < hands.size(); i++) {
			int pNumber = i + 1;
			System.out.println("Player " + pNumber + ":");
			System.out.println(hands.get(i)[0]);
			if (hands.get(i)[0].getHasDoubles()) {
				System.out
						.print("You can split. Want to? (y for yes, anything else for no): ");
				input = sc.next();
				if (input.equals("y")) {
					PlayerHand temp = hands.get(i)[0];
					hands.set(i, new PlayerHand[2]);
					for (int j = 0; j < 2; j++) {
						if (temp.pHand.get(j).getScore() == 11) {
							temp.pHand.get(j).ace = true;
						}
					}

					hands.get(i)[0] = new PlayerHand();
					hands.get(i)[0].Deal(temp.pHand.get(0), deck.remove(0));

					hands.get(i)[1] = new PlayerHand();
					hands.get(i)[1].Deal(temp.pHand.get(1), deck.remove(0));

					for (PlayerHand ph : hands.get(i)) {
						PlayerTurn(ph, deck);
					}

					continue;
				}
			}
			input = "y";
			while (input.equals("y") && hands.get(i)[0].getScore() <= 21) {
				System.out.println("\nPlayer " + pNumber + ":");
				System.out.println(hands.get(i));
				System.out
						.print("Do you want a hit? (y for yes, anything else for no): ");
				input = sc.next();
				if (input.equals("y")) {
					hands.get(i)[0].Hit(deck);
				}
			}
			System.out.println("\nPlayer " + pNumber + ":");
			System.out.println(hands.get(i) + "\n");
		}
		// Dealer Stuff
		System.out.println(dHand);
		while (dHand.getScore() < 17) {
			dHand.Hit(deck);
			System.out.println(dHand);
		}

		sc.close();
		for (int i = 0; i < hands.size(); i++) {
			int pNumber = i + 1;
			for (PlayerHand ph : hands.get(i)) {
				if (ph.beatDealer(dHand)) {
					System.out
							.println("Player " + pNumber + " beat the dealer");
				} else {
					System.out.println("Player " + pNumber
							+ " lost to the dealer");
				}
			}
		}
	}

	public static void PlayerTurn(PlayerHand currentHand, List<Card> deck) {
		Scanner sc = new Scanner(System.in);
		String input = "y";
		while (input.equals("y") && currentHand.getScore() <= 21) {
			System.out.println(currentHand);
			System.out
					.print("Do you want a hit? (y for yes, anything else for no): ");
			input = sc.next();
			if (input.equals("y")) {
				currentHand.Hit(deck);
			}
		}
		System.out.println(currentHand + "\n");
	}
}
