import java.util.ArrayList;

/*
 * Household holds Characters and has an income and upkeep based on the Characters.
 * Has its own inventory that Characters can take from.
 */
public class Household {
	private ArrayList<Character> household;
	private ArrayList<Item> itemList;
	
	private double income;
	private double upkeep;
	private double money;

	Household() {
		household = new ArrayList<Character>();
		itemList = new ArrayList<Item>();
		income = 0;
		upkeep = 0;
		money = 0;
	}

	Household(Character[] h) {
		for (int i = 0; i < h.length; i++) {
			household.add(h[i]);
			income += h[i].getIncome();
			upkeep += h[i].getUpkeep();
		}
		money = 0;
	}

	Household(Household h) {
		household = h.household;
		income = h.income;
		upkeep = h.upkeep;
		money = h.money;
	}

	//Item change
	public void addItem(int id) {
		itemList.add(Item.getItem(id));
	}
	
	public void moveItem(Character c, int id) {
		itemList.add(c.removeItem(id));
	}
	
	public void takeItem(Character c, int id) {
		c.addItem(removeItem(id));
	}
	
	public Item removeItem(int id) {
		for(int i = 0; i < itemList.size(); i++) {
			if(itemList.get(i).getId() == id) {
				return itemList.remove(i);
			}
		}
		return null;
	}
	
	//Character change in Household
	public void addToHousehold(Character c) {
		household.add(c);
		income += c.getIncome();
		upkeep += c.getUpkeep();
	}

	public Character removeFromHousehold(int id) {
		Character c = new Character();
		for (int i = 0; i < household.size(); i++) {
			if (household.get(i).getId() == id) {
				c = household.remove(i);
				break;
			}
		}
		return c;
	}

	//Get Set
	public double getIncome() {
		return income;
	}

	public double getUpkeep() {
		return upkeep;
	}
	
	public double getMoney() {
		return money;
	}

}
