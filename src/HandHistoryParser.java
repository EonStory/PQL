
public class HandHistoryParser {
	
	public static HandObject parse(String s) {
		HandObject ho = new HandObject();
		
		String[] lines = s.split("\\r?\\n");
		int startOfId = lines[0].indexOf('#') + 1;
		int endOfId = lines[0].indexOf(':');
		
		ho.handId = Long.parseLong(lines[0].substring(startOfId, endOfId));
		
		int numberOfPlayers = 0;

		return null;
	}
}
