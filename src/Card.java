import java.util.Arrays;

//includes a 53rd card to represent unknown card.
//it has rank == 13 and suit == 4; and is printed as "??"
public class Card {
	
	private static Card[] allCards = new Card[53];
	
	public static final Card UNKNOWN_CARD;
	
	public final int rank;
	public final int suit;
	public final int index;
	
	private static String[] rankNames = new String[] {"A","2","3","4","5","6","7","8","9","T","J","Q","K"};
	private static String[] suitNames = new String[] {"s","h","d","c"};
	
	public String toString() {
		if (index == 52) {
			return "??";
		}
		return rankNames[rank] + suitNames[suit];
	}	
	
	//new cards cannot be constructed outside of this class
	private Card(int rank, int suit) {
		this.rank = rank;
		this.suit = suit;
		index = suit * 13 + rank;
	}
	
	//create all cards
	static {		
		int counter = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				allCards[counter] = new Card(j, i);
				counter++;
			}
		}
		//unknown card
		allCards[counter] = new Card(13, 4);
		UNKNOWN_CARD = allCards[counter];
	}
	
	public static Card getCard(int index) {
		return allCards[index];
	}
	
	public static Card getCard(String rank, String suit) {		
		for (int i = 0; i < 13; i++) {
			if (rank.equals(rankNames[i])) {
				for (int j = 0; j < 4; j++) {
					if (suit.equals(suitNames[j])) {
						return Card.getCard(j * 13 + i);
					}
				}
			}
		}		
		throw new IllegalArgumentException("not in existence: " + rank + "" + suit);
	}
	
	public byte toByte() {
		return (byte) index;
	}
}
