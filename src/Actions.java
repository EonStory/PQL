import java.util.ArrayList;

public class Actions {

	public final int max_players = 10;
	private final short callValue = -1;
	private final short checkValue = -11;
	private final short foldValue = -111;
	//final nBet is stored as negative number.
	private final short maxBetSize = Short.MAX_VALUE + foldValue * max_players;
	
	public ArrayList<Short> actionList = new ArrayList<Short>();
	
	public Actions() {
		
	}
	
	public void addCall(){
		actionList.add(callValue);
	}
	
	public void addFold(){
		actionList.add(foldValue);
	}
	
	public void addCheck(){
		actionList.add(checkValue);
	}
	
	//nBet == 1bet, 2bet, 3bet etc... (n > 0)
	public void addNBet(short nBetSize) {
		actionList.add(nBetSize);
	}
	
	public int getNumberOfActions() {
		return actionList.size();
	}
	
	public String toString() {
		String s = "";
		for (Short a: actionList) {
			if (a == callValue) {
				s += "call, ";
			}
			else if (a == checkValue) {
				s += "check, ";
			}
			else if (a == foldValue) {
				s += "fold, ";
			}
			else {
				s += "nBet to " + a + ", ";
			}
		}
		return s;
	}
	
}
