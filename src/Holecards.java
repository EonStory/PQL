
public class Holecards {
	
	public final Card card1;
	public final Card card2;
	
	private static Holecards[] allHolecards = new Holecards[1375];
	
	private Holecards(Card card1, Card card2) {
		this.card1 = card1;
		this.card2 = card2;
	}
	
	static {
		int counter = 0;
		for (int i = 0; i < 53; i++) {
			for (int j = i + 1; j < 53; j++) {				
				allHolecards[counter] = new Holecards(Card.getCard(i), Card.getCard(j));
				counter++;
			}
		}
	}
	
	/*
	private Holecards() {
		card1 = Card.UNKNOWN_CARD;
		card2 = Card.UNKNOWN_CARD;
	}
	*/
	
	
	public Holecards get(Card c1, Card c2) {
		if (c1.index == c2.index && c1 != Card.UNKNOWN_CARD) {
			throw new IllegalArgumentException("cant be the same card! card with index " + c1.index + " is used twice");
		}
				
		//bigger card always goes second!		
		int smallCard = Math.min(c1.index, c2.index);
		int bigCard = Math.max(c1.index, c2.index);
		
		//triangular numbers!
		int cardsBefore = 0;
		for (int i = 0; i < smallCard; i++) {
			cardsBefore += 51 - i;
		}
		cardsBefore += bigCard - smallCard - 1;
		
		return allHolecards[cardsBefore];
	}
	
}