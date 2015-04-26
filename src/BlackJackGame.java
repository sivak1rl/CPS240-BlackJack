
public class BlackJackGame {

	public double[] deck;

//	public BlackJackGame() {
//		deck = new double[52];
//		int count = 0;
//		for (int i = 1; i < 14; i++) {
//			for (int j = 0; j < 4; j++) {
//				deck[count] = i % 13 + j / 4.0;
//				if (deck[count] < 1) {
//					deck[count] += 13;
//				}
//				count++;
//			}
//		}
//	}
	
	
	public BlackJackGame() {
		deck = new double[52];
		int count = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 1; j < 14; j++) {
				deck[count] = j % 13 + i / 4.0;
				if(deck[count] < 1) {
					deck[count] += 13;
				}
				count++;
			}
		}
	}
}