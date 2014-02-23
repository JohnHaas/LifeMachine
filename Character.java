import java.util.ArrayList;


public class Character {
	private static final int NUM_STATS = 12;
	private static int globalId;

	private double[] stats;
	private int age;
	private boolean isActive = false;
	private double upkeep;
	private double income;
	private String name;
	private int id;
	private ArrayList<Item> inventory;

	private RelationTree<Character> relations;

	// 0 = Strength, 1 = Dexterity, 2 = Charisma, 3 = Intelligence, 4 = Luck, 5
	// = Life, //6 = Independence, 7 = Sanity, 8 = Appeal, 9 = Stamina, 10 =
	// Stress, 11 = Health

	// character flags here (traits?)
	boolean spouse; // ie if true you can have event where your wife dies
	boolean evil; // if true more crime related events pop up

	// Stats are out of 100 points max
	// Constructors
	Character() {
		this(new double[NUM_STATS]);
		for (int i = 0; i < NUM_STATS; i++) {
			stats[i] = Math.random() * 100;
		}
	}

	Character(double[] s) {
		age = 0;
		stats = s;
		income = 0;
		upkeep = 100;
		name = "";
		id = globalId;
		globalId++;
	}

	Character(Character c) {
		age = c.age;
		System.arraycopy(c.stats, 0, stats, 0, NUM_STATS);
		income = c.income;
		upkeep = c.upkeep;
		name = c.name;
		id = globalId;
		globalId++;
	}

	// STATS:
	// 0 = Strength, 1 = Dexterity, 2 = Charisma, 3 = Intelligence, 4 = Luck, 5
	// = Life, //6 = Independence, 7 = Sanity, 8 = Appeal, 9 = Stamina, 10 =
	// Stress, 11 = Health

	// Getters and Setters
	public double getStat(int i) {
		return stats[i];
	}

	public void setStat(double val, int i) {
		stats[i] = val;
	}

	public double getStrength() {
		return stats[0];
	}

	public void setStrength(double val) {
		stats[0] = val;
	}

	public double getDexterity() {
		return stats[1];
	}

	public void setDexterity(double val) {
		stats[1] = val;
	}

	public double getCharisma() {
		return stats[2];
	}

	public void setCharisma(double val) {
		stats[2] = val;
	}

	public double getIntelligence() {
		return stats[3];
	}

	public void setIntelligence(double val) {
		stats[3] = val;
	}

	public double getLuck() {
		return stats[4];
	}

	public void setLuck(double val) {
		stats[4] = val;
	}

	public double getLife() {
		return stats[5];
	}

	public void setLife(double val) {
		stats[5] = val;
	}

	public double getIndependence() {
		return stats[6];
	}

	public void setIndependence(double val) {
		stats[6] = val;
	}

	public double getSanity() {
		return stats[7];
	}

	public void setSanity(double val) {
		stats[7] = val;
	}

	public double getAppeal() {
		return stats[8];
	}

	public void setAppeal(double val) {
		stats[8] = val;
	}

	public double getStamina() {
		return stats[9];
	}

	public void setStamina(double val) {
		stats[9] = val;
	}

	// stress is a hidden variable, used for determining availability of suicide
	// option

	public void setStress(double val) {
		stats[10] = val;
	}

	public double getStress() {
		return stats[10];
	}

	public void setHealth(double val) {
		stats[11] = val;
	}

	public double getHealth() {
		return stats[11];
	}

	// Stats get set

	public int getAge() {
		return age;
	}

	public void setAge(int val) {
		age = val;
	}

	public double getUpkeep() {
		return upkeep;
	}

	public void setUpkeep(double u) {
		upkeep = u;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double i) {
		income = i;
	}

	public int getId() {
		return id;
	}
}
