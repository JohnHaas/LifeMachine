import java.util.ArrayList;

/*
 * Room used when making events or visiting locations.
 * Events pull character from the room to make interactions.
 * Room has a set of characteristics, then filled based on those.
 */
public class Room {
	private ArrayList<Integer> charList;
	private ArrayList<Item> items;
	private double[] statChange;
	private double[] atmosphere;
	
	Room() {
		charList = new ArrayList<Integer>();
		items = new ArrayList<Item>();
		statChange = new double[Character.NUM_STATS];
		atmosphere = new double[0];
	}
	
	//Character
	public Character getCharacter(int i) {
		return Character.getChar(charList.get(i));
	}
	
	public void addCharacter(int i) {
		charList.add(i);
	}
	
	public void removeCharacter(int id) {
		for(int i = 0; i < charList.size(); i++) {
			if(charList.get(i) == id) {
				charList.remove(charList.get(i));
				break;
			}
		}
	}

	//Item
	public Item getItems(int i) {
		return items.get(i);
	}

	public void addItem(int i) {
		items.add(Item.getItem(i));
	}

	public void removeItem(int id) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getId() == id) {
				items.remove(items.get(i));
				break;
			}
		}
	}
}
