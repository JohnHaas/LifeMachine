import java.util.ArrayList;

/*
 * Room used when making events or visiting locations.
 * Events pull character from the room to make interactions.
 * Room has a set of characteristics, then filled based on those.
 */
public class Room {
	private ArrayList<Character> charList;
	
	Room() {
		charList = new ArrayList<Character>();
	}
	
	public void addCharacter(int i) {
		charList.add(Character.getChar(i));
	}
	
	public void removeCharacter(int id) {
		for(int i = 0; i < charList.size(); i++) {
			if(charList.get(i).getId() == id) {
				charList.remove(charList.get(i));
				break;
			}
		}
	}
}
