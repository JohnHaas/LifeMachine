import java.util.ArrayList;

/*
 * Character class holds all the information about a character.
 * It includes a unique character ID, stats, inventory, relations, etc.
 */
public class Character {
	private static final int NUM_STATS = 12;
	private static final int NUM_PER = 2;
	private static final int NUM_TRAIT = 0;
	private static int globalId = 0;
	private static ArrayList<Character> charList = new ArrayList<Character>();

	private int age;
	private boolean isActive;
	private double upkeep;
	private double income;
	private String name;
	private int id;
	
	private double[] stats;
	private double[] statMult;
	private double[] personality;
	private boolean[] traits;
	private ArrayList<Item> inventory;

	private RelationTreeNode relations;
	
	// Stats are out of 100 points max
	// Constructors
	Character() {
		age = 0;
		isActive = false;
		upkeep = 0;
		income = 0;
		name = "";
		id = globalId;
		id++;
		
		stats = new double[NUM_STATS];
		statMult = new double[NUM_STATS];
		personality = new double[NUM_PER];
		traits = new boolean[NUM_TRAIT];
		inventory = new ArrayList<Item>();
		
		relations = new RelationTreeNode(this);
		charList.add(this);
	}
	
	//Modified by genetics
	Character(Character mom, Character dad) {
		this();
		geneticMachine(mom, dad);
		relations.addParent(mom.getId());
		relations.addParent(dad.getId());
		mom.relations.addChild(this.getId());
		dad.relations.addChild(this.getId());
		if(!mom.relations.getChildren().isEmpty()) {
			for(int i = 0; i < mom.relations.getChildren().size(); i++) {
				relations.addSibling(mom.relations.getChildVal(i));
			}
		}
	}

	Character(double[] s) {
		this();
		stats = s;
	}

	Character(Character c) {
		this();
		age = c.age;
		name = c.name;
		upkeep = c.upkeep;
		stats = c.stats;
		statMult = c.statMult;
		personality = c.personality;
		traits = c.traits;
	}

	public void geneticMachine(Character m, Character d) {
		stats = statRoll(m, d);
		statMult = statMultRoll(m, d);
		personality = perRoll(m, d);
		traits = traitRoll(m, d);
	}
	
	//Does stat random from average of parents, one of parents, and complete random.
	public static double[] statRoll(Character m, Character d) {
		int statMerge = (int)(Math.random() * (NUM_STATS/2));
		int statPick = (int)(Math.random() * (NUM_STATS/2));
		int statLeft = NUM_STATS - statMerge - statPick;
		double[] newStats;
		boolean[] isChanged = new boolean[NUM_STATS];
		for(int i = 0; i < NUM_STATS; i++) {
			isChanged[i] = false;
		}
		if((int)(Math.random() * 2) == 0) {
			newStats = m.stats;
		}
		else {
			newStats = d.stats;
		}
		//Complete Random
		for(int i = 0; i < statLeft; i++) {
			int temp = (int)(Math.random() * NUM_STATS);
			if(!isChanged[temp]) {
				newStats[temp] = (int)(Math.random() * 50) + 25;
				isChanged[temp] = true;
			}
		}
		//Average
		for(int i = 0; i < statMerge; i++) {
			
		}
		//Pick
		for(int i = 0; i < statPick; i++) {
			
		}
		return newStats;
	}
	
	//Uses statRoll algorithm to random stat multiplier.
	public static double[] statMultRoll(Character m, Character d) {
		double[] newStatMult = new double[NUM_STATS];
		return newStatMult;
	}
	
	//Uses statRoll algorithm to random personality.
	public static double[] perRoll(Character m, Character d) {
		double[] newPer = new double[NUM_PER];
		return newPer;
	}
	
	public static boolean[] traitRoll(Character m, Character d) {
		boolean[] newTraits = new boolean[NUM_TRAIT];
		return newTraits;
	}
	
	//Static getter
	//Id parameter. Search through and get Character with id, i.
	public static Character getChar(int i) {
		return charList.get(i);
	}
	
	/*
	 * STATS:
	 * 0 = Strength, 1 = Dexterity, 2 = Charisma, 3 = Intelligence, 4 = Luck, 
	 * 5 = Life, 6 = Independence, 7 = Sanity, 8 = Appeal, 9 = Stamina, 
	 * 10 = Stress, 11 = Health
	 */

	// Stats Get Set
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

	/*
	 * PERSONALITY:
	 * 0 = Temper, 1 = Criminality, 
	 */
	
	
	// Other Get Set

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
