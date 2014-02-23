import java.util.ArrayList;

public class Household {
	private ArrayList<Character> household;
	private double income;
	private double upkeep;
	private double money;

	Household() {
		household = new ArrayList<Character>();
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
