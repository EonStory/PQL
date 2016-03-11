import java.util.Arrays;

public class Card implements Comparable<Card>{
	
	private static Card[] allCards = new Card[52];
	
	public final int rank;
	public final int suit;
	public final int index;
	
	private static String[] rankNames = new String[] {"A","2","3","4","5","6","7","8","9","T","J","Q","K"};
	private static String[] suitNames = new String[] {"s","h","d","c"};
	
	public String toString() {
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
	}
	
	public static Card getCard(int index) {
		return allCards[index];
	}
	
	public static boolean sameSuit(Card c1, Card c2) {
		if (c1.suit == c2.suit) {
			return true;
		}
		return false;
	}
	
	public static boolean sameRank(Card c1, Card c2) {
		if (c1.rank == c2.rank) {
			return true;
		}
		return false;
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
	
	public static Card getCard(char rank, int suit) {
		String s = "";
		s += rank;
		return Card.getCard(s, suitNames[suit]);
	}
	
	public static Card getCard(int rank, int suit) {
		return Card.getCard(suit * 13 + rank);
	}
	
	//maps cards to the isomorphic group
	public static Card[] cardsSimplified(Card[] c) {
		//need to sort it baby!
		Card[] cards = c.clone();
		Arrays.sort(cards);
		
		boolean[] done = new boolean[cards.length];
		for (int i = 0; i < done.length; i++) {
			done[i] = false;
		}
		
		Card[] results = new Card[cards.length];			
		
		for (int i = 0, suitsDone = 0; i < cards.length; i++) {
			if (done[i] == true) {
				continue;
			}
			for (int j = i; j < cards.length; j++) {
				if (cards[i].suit == cards[j].suit) {
					results[j] = Card.getCard(cards[j].rank, suitsDone);
					done[j] = true;
				}						
			}
			suitsDone++;
		}
		
		Arrays.sort(results);
		
		return results;
	}

	@Override
	public int compareTo(Card arg0) {		
		return this.rank - arg0.rank;
	}	
}
