import java.util.ArrayList;
import java.util.List;

//big and sluggish, an array of these would use too much memory to be used in one big array
public class HandObject {

	//byte siteId;
	long handId;
	long time;
	
	List<Byte> boardCards = new ArrayList<Byte>();
	List<Byte> holeCards = new ArrayList<Byte>();
	Actions actions = new Actions();
	List<Short> stacks = new ArrayList<Short>();
	List<Short> blinds = new ArrayList<Short>();
	List<String> names = new ArrayList<String>();
	
	
}
