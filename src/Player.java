import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Player {
	
	//byte site;
	public int totalPlayers = 0;
	
	public static Hashtable<String, Integer> nameToNumber = new Hashtable<String,Integer>();
	public static Hashtable<Integer, Player> numberToPlayer = new Hashtable<Integer, Player>();
	
	//following not needed because you can through 100m hands and checking if number is present in 6 places wont be slow.
	//public List<Integer> handIndexes = new ArrayList<Integer>();
	
	//to avoid collisions name should be prepended with site name
	//Gambler424 -> POKERSTARS_Gambler424
	//P0k3rKING  -> IPOKER_____P0K3rKING
	public String name;
	
	public Player(String name) {
		this.name = name;
		nameToNumber.put(name, totalPlayers);
		numberToPlayer.put(totalPlayers, this);
		totalPlayers++;
	}
	
}
