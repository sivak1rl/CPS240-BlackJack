import java.util.ArrayList;
import java.util.List;


public class DealerHand {
	List<Card> dHand;
	public int score;
	
	public DealerHand(){
		dHand = new ArrayList<Card>();
	}
	public void Deal(Card c1, Card c2){
		
		dHand.clear();
		dHand.add(c1);
		score += c1.getScore();
		dHand.add(c2);
		score += c2.getScore();
		if(score>21 && c1.ace){
			score-=10;
			c1.ace=false;
		}else if(score>21 && c2.ace){
			score -= 10;
			c2.ace = false;
		}
		
		
	}
	public void Hit(Card c){
		dHand.add(c);
		
		score+= c.getScore();
		
		if(score > 21) {
			for(Card d: dHand) {
				if(d.ace) {
					score -= 10;
					d.ace = false;
					if(score <= 21) {
						break;
					}
				}
			}
		}
	}
	public String toString(){
		String str="";
		
		for(Card c: dHand){
			str += c + ", ";
		}
		str = str.substring(0, str.length() - 2);
		if (score < 21) {
			return str + " \nScore: " + score;
		} else if (score == 21) {
			return str + "\nBlackJack!";
		} else {
			return str + "\nBust...";
		}
	}
}