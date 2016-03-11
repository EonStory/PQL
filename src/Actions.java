import java.util.ArrayList;

public class Actions {

	private final short callValue = -1;
	private final short checkValue = -20;
	private final short foldValue = -400;
	
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
	
	public String toString() {
		String s = "";
		for (Short a: actionList) {
			if (a == -1) {
				s += "call, ";
			}
			else if (a == -2) {
				s += "check, ";
			}
			else if (a == -3) {
				s += "fold, ";
			}
			else {
				s += "nBet to " + a + ", ";
			}
		}
		return s;
	}
	
}
