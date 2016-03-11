import java.util.HashMap;

//all hands mushed together in a compressed form.

//Ideas for compression:
//negative numbers represent the end of the stuff, and have a special value for no entries
//eg (board[] of [4,6,-5] means -5 is the last card, no need to store length of cards. [-60] means no cards
//use values -1 for fold, -10 for call, -100 for check. then a negative number represents
//how many of those actions occured in squence. -7 implies 7 folds in a row.
//-50 means 5 calls in a row etc. obv cant mix these together. doesnt work.
public class HandArray {
	//do this later
	//public HashMap<Integer, String> bobby = new HashMap<Integer, String>();
	
	long[] id;	
	byte[] board;//format: negative number means last card, -60 is no cards
	short[] stacks;//negative number last stack
	byte[] holecards;//normal. we know the number of players from stacks
	short[] actions;
	short[] blinds;//[bb,sb,ante,bb,sb,ante,bb,sb,ante,bb,sb,ante,bb,....,ante]
	
	public final byte NO_BOARD_CARDS = -60;
	public final byte NO_HOLE_CARDS = -69;	
	
//adds all its elements to the handarray then removes all objects from it
	public void mergeBuffer(HandArrayBuffer hab) {
		//merge id
		long[] idMerged = new long[id.length + hab.handObjects.size()];
		for (int i = 0; i < id.length; i++) {
			idMerged[i] = id[i];
		}
		for (int i = 0; i < hab.handObjects.size(); i++) {
			idMerged[id.length + i] = hab.handObjects.get(i).handId;
		}
		
		//merge board
		int extraBytesNeededForBoard = 0;
		for (HandObject ho: hab.handObjects) {
			if (ho.boardCards.size() == 0) {//if there are no board cards, store 1 number -60. this means no cards
				extraBytesNeededForBoard++;
			}
			else {
				extraBytesNeededForBoard += ho.boardCards.size();//negative number means last card.! hehe
			}
		}
		
		byte[] boardMerged = new byte[board.length + extraBytesNeededForBoard];
		for (int i = 0; i < board.length; i++) {
			boardMerged[i] = board[i];
		}
		
		int currentIndex = 0;
		for (int i = 0; i < hab.handObjects.size(); i++) {
			if (hab.handObjects.get(i).boardCards.size() == 0) {
				boardMerged[board.length + currentIndex] = NO_BOARD_CARDS;
				currentIndex++;
			}
			else {//for all but the last card!
				for (int j = 0; j < hab.handObjects.get(i).boardCards.size() - 1; j++) {
					boardMerged[board.length + currentIndex] = hab.handObjects.get(i).boardCards.get(j);
					currentIndex++;
				}
				
				int lastBoardCard = hab.handObjects.get(i).boardCards.get(hab.handObjects.get(i).boardCards.size() - 1);				
				boardMerged[board.length + currentIndex] = 	(byte) (-lastBoardCard); //java means i have to use casts
				currentIndex++;
			}			
		}
		
		//merge stacks
		//number of stacks must be greater than 0
		//negative number means last stack
		int numberOfExtraStacks = 0;
		for (HandObject ho: hab.handObjects) {
			numberOfExtraStacks += ho.boardCards.size();
		}
		
		short[] stacksMerged = new short[stacks.length + numberOfExtraStacks];
		currentIndex = 0;
		for (int i = 0; i < hab.handObjects.size(); i++) {			
			for (int j = 0; j < hab.handObjects.get(i).stacks.size() - 1; j++) {
				stacksMerged[stacks.length + currentIndex] = hab.handObjects.get(i).stacks.get(j);
				currentIndex++;
			}
			
			int lastStack = hab.handObjects.get(i).stacks.get(hab.handObjects.get(i).stacks.size() - 1);				
			stacksMerged[stacks.length + currentIndex] = 	(short) (-lastStack); //java means i have to use casts
			currentIndex++;					
		}
		
		//merge holecards
		int numberOfExtraHoleCards = 0;
		for (HandObject ho: hab.handObjects) {
			numberOfExtraStacks += ho.holeCards.size();
		}
		
		byte[] holecardsMerged = new byte[holecards.length + numberOfExtraHoleCards];
		for (int i = 0; i < holecards.length; i++) {
			holecardsMerged[i] = holecards[i];
		}
		
		currentIndex = 0;
		for (HandObject ho: hab.handObjects) {
			for (int i = 0; i < ho.holeCards.size(); i++) {
				holecardsMerged[holecards.length + currentIndex] = ho.holeCards.get(i);
				currentIndex++;
			}	
		}		
		
		//merge actions
		int numberOfExtraActions = 0;
		for (HandObject ho: hab.handObjects) {
			numberOfExtraStacks += ho.actions.actionList.size();
		}
		
		short[] actionsMerged = new short[actions.length + numberOfExtraActions];
		for (int i = 0; i < actions.length; i++) {
			actionsMerged[i] = actions[i];
		}
		
		currentIndex = 0;
		for (HandObject ho: hab.handObjects) {
			for (int i = 0; i < ho.actions.actionList.size(); i++) {
				actionsMerged[actions.length + currentIndex] = ho.actions.actionList.get(i);
				currentIndex++;
			}			
		}
		
		//merge blinds
		int numberOfExtraBlinds = 0;
		for (HandObject ho: hab.handObjects) {
			numberOfExtraBlinds += ho.blinds.size();
		}
		
		short[] blindsMerged = new short[blinds.length + numberOfExtraBlinds];
		for (int i = 0; i < blinds.length; i++) {
			blindsMerged[i] = blinds[i];
		}
		
		currentIndex = 0;
		for (HandObject ho: hab.handObjects) {
			for (int i = 0; i < ho.blinds.size(); i++) {
				blindsMerged[blinds.length + currentIndex] = ho.blinds.get(i);
				currentIndex++;
			}			
		}
		
	}	
}
