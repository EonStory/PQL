
public class Holecards {
	
	public final Card card1;
	public final Card card2;
	
	public Holecards(Card card1, Card card2) {
		this.card1 = card1;
		this.card2 = card2;
	}
	
	
	public Holecards() {
		card1 = Card.UNKNOWN_CARD;
		card2 = Card.UNKNOWN_CARD;
	}
}