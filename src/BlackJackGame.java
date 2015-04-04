import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BlackJackGame {

	double[] deck;

	// will this import
	// this one will be it
	// 7:48 attempt to commit
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
	 * (Card Object) based on BlackJackGame.deck and a player hand. deals to the
	 * player and asks for a hit.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		List<Card> cardDeck = new LinkedList<Card>();
		BlackJackGame bjg = new BlackJackGame();
		PlayerHand hand1 = new PlayerHand();
		DealerHand dHand = new DealerHand();
		for (double d : bjg.deck) {
			cardDeck.add(new Card(d));
		}
		//Collections.shuffle(cardDeck);
		hand1.Deal(cardDeck.remove(0), cardDeck.remove(0));
		dHand.Deal(cardDeck.remove(0), cardDeck.remove(0));
		System.out.println(hand1);
		Scanner sc = new Scanner(System.in);
		System.out.println("The dealer's up card is " + dHand.getUpCard());
		String input;
		if(hand1.checkDuplicates()){
			//uses checkDuplicates in PlayerHand class,
			//    which uses new methods compareTo and getValue, getSuit in Card class
			System.out
					.println("You have been dealt duplicates, do you want to split your hand?");
			System.out
					.println("(y for yes, anything else for no: ");
			input=sc.next();
			
			if(input.equals("y")){
				PlayerHand hand2 = new PlayerHand();
				hand2.Hit(hand1.getDuplicateCard());
				String input2;
				while (input.equals("y") && hand1.getScore() <= 21&& hand2.getScore()<=21) {
					System.out.println("hand 1:" +hand1);
					System.out.println("hand 2:" +hand2);
					System.out
					.print("Do you want a hit hand 1? (y for yes, anything else for no): ");
					
					input = sc.next();
					
					System.out
					.print("Do you want to hit hand 2? (y for yes, anything else for no):");
					input2=sc.next();
					
					
					if(input=="y"){
						hand1.Hit(cardDeck.remove(0));
					}
					if(input2=="y"){
						hand2.Hit(cardDeck.remove(0));
					}
					
					//need to now ask whether or not to hit on each hand
					System.out.println(hand1);
					System.out.println(hand2);
					if (hand1.getScore() <= 21 &&hand2.getScore()<=21) {
						if(hand1.checkDuplicates()){
							//uses checkDuplicates in PlayerHand class,
							//    which uses new methods compareTo and getValue, getSuit in Card class
							System.out
									.println("You have been dealt duplicates, do you want to split your hand?");
							System.out
									.println("(y for yes, anything else for no: ");
							input=sc.next();
							
							if(input.equals("y")){
								PlayerHand hand3 = new PlayerHand();
								hand2.Hit(hand1.getDuplicateCard());
							}
							
							//same stuff as above but for 3 hands
							
							
						System.out
								.print("Do you want a hit? (y for yes, anything else for no): ");
						input = sc.next();
					}
						if(hand2.checkDuplicates()){
							//uses checkDuplicates in PlayerHand class,
							//    which uses new methods compareTo and getValue, getSuit in Card class
							System.out
									.println("You have been dealt duplicates, do you want to split your hand?");
							System.out
									.println("(y for yes, anything else for no: ");
							input=sc.next();
							
							if(input.equals("y")){
								PlayerHand hand3 = new PlayerHand();
								hand2.Hit(hand1.getDuplicateCard());
							}
							
							//same stuff as above but for 3 hands
						System.out
								.print("Do you want a hit? (y for yes, anything else for no): ");
						input = sc.next();
						}
					}
				}
			}
		}
		System.out
				.print("Do you want a hit? (y for yes, anything else for no): ");
		input = sc.next();
		while (input.equals("y") && hand1.getScore() <= 21) {
			hand1.Hit(cardDeck.remove(0));
			System.out.println(hand1);
			if (hand1.getScore() <= 21) {
				if(hand1.checkDuplicates()){
					//uses checkDuplicates in PlayerHand class,
					//    which uses new methods compareTo and getValue, getSuit in Card class
					System.out
							.println("You have been dealt duplicates, do you want to split your hand?");
					System.out
							.println("(y for yes, anything else for no: ");
					input=sc.next();
					
					if(input.equals("y")){
						PlayerHand hand2 = new PlayerHand();
						hand2.Hit(hand1.getDuplicateCard());
					}
				System.out
						.print("Do you want a hit? (y for yes, anything else for no): ");
				input = sc.next();
			}
			
			
				
				
			}
		}
		System.out.println("The dealer's score is " + dHand.getScore());
		while (dHand.getScore() < 17) {
			dHand.Hit(cardDeck.remove(0));
			System.out
					.println("The dealer hit and now has " + dHand.getScore());
		}
		if (hand1.getScore() > dHand.getScore() && hand1.getScore() <= 21
				|| dHand.getScore() > 21) {
			System.out.println("You win");
		} else if (hand1.getScore() == dHand.getScore()) {
			System.out.println("Push");
		} else if (hand1.getScore() < dHand.getScore() && dHand.getScore() <= 21
				|| hand1.getScore() > 21) {
			System.out.println("You lose");
		}
		sc.close();
	}
}
