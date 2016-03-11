import java.util.ArrayList;

//big and sluggish, an array of these would use too much memory to be used in one big array
public class HandObject {

	//byte siteId;
	long handId;
	long time;
	
	ArrayList<Byte> boardCards = new ArrayList<Byte>();
	ArrayList<Byte> holeCards = new ArrayList<Byte>();
	Actions actions = new Actions();
	ArrayList<Short> stacks = new ArrayList<Short>();
	
	
	
}
