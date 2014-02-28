import java.util.ArrayList;

/*
 * Character class holds all the information about a character.
 * It includes a unique character ID, stats, inventory, relations, etc.
 */
public class Character {
	public static final int NUM_STATS = 13;
	public static final int NUM_PER = 11;
	public static final int NUM_SKILL = 0;
	public static final int NUM_TRAIT = 0;
	
	private static int globalId = 0;
	private static ArrayList<Character> charList = new ArrayList<Character>();

	private int age;
	private double decaytion;
	private boolean isActive;
	private double upkeep;
	private double income;
	private String name;
	private int id;
	
	private double[] stats;
	private double[] statMult;
	private double[] personality;
	private double[] skills;
	private double[] skillAff;
	private boolean[] traits;
	private ArrayList<Item> inventory;

	private RelationTreeNode relations;
	
	// Stats are out of 100 points max
	// Constructors
	Character() {
		age = 0;
		decaytion = .1;
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
	
	//Item usage
	public void addItem(int i) {
		inventory.add(Item.getItem(i));
	}
	
	public void removeItem(String name) {
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).getName().equals(name)) {
				inventory.remove(i);
				break;
			}
		}
	}

	//Ages and changes decaytion
	public void ageUp(Character c) {
		age++;
		if(c.age <= 21) {
			decaytion = Math.sin((Math.PI)*(age/21) / 2);
		}
	}
	
	//Genetic combination algorithms
	public void geneticMachine(Character m, Character d) {
		stats = statRoll(m, d);
		statMult = statMultRoll(m, d);
		personality = perRoll(m, d);
		traits = traitRoll(m, d);
	}
	
	//Does stat random from average of parents, one of parents, and complete random.
	public static double[] statRoll(Character m, Character d) {
		double[] newStats;
		int statMerge = (int)(Math.random() * (NUM_STATS/2));
		int statPick = (int)(Math.random() * (NUM_STATS/2));
		int statLeft = NUM_STATS - statMerge - statPick;
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
				newStats[temp] = (Math.random() * 50) + 25;
				isChanged[temp] = true;
			}
			else {
				i--;
			}
		}
		//Average
		for(int i = 0; i < statMerge; i++) {
			int temp = (int)(Math.random() * NUM_STATS);
			if(!isChanged[temp]) {
				newStats[temp] = (m.stats[temp] + d.stats[temp])/2;
				isChanged[temp] = true;
			}
			else {
				i--;
			}
		}
		//Pick
		for(int i = 0; i < statPick; i++) {
			int temp = (int)(Math.random() * NUM_STATS);
			if(!isChanged[temp]) {
				int pick = (int)(Math.random() * 2);
				if(pick == 0) {
					newStats[temp] = m.stats[temp];
				}
				else {
					newStats[temp] = d.stats[temp];
				}
				isChanged[temp] = true;
			}
			else {
				i--;
			}
		}
		//Added Variance
		for(int i = 0; i < newStats.length; i++) {
			double var = (Math.abs(newStats[i] - 50)/3);
			double change = (Math.random() * var);
			int temp = (int)(Math.random() * 2);
			if(temp == 0) {
				newStats[i] += change;
			}
			else {
				newStats[i] -= change;
			}
		}
		return newStats;
	}
	
	//Uses statRoll algorithm to random stat multiplier.
	public static double[] statMultRoll(Character m, Character d) {
		double[] newStatMult = new double[NUM_STATS];
		int statMerge = (int)(Math.random() * (NUM_STATS/2));
		int statPick = (int)(Math.random() * (NUM_STATS/2));
		int statLeft = NUM_STATS - statMerge - statPick;
		boolean[] isChanged = new boolean[NUM_STATS];
		for(int i = 0; i < NUM_STATS; i++) {
			isChanged[i] = false;
		}
		if((int)(Math.random() * 2) == 0) {
			newStatMult = m.stats;
		}
		else {
			newStatMult = d.stats;
		}
		//Complete Random
		for(int i = 0; i < statLeft; i++) {
			int temp = (int)(Math.random() * NUM_STATS);
			if(!isChanged[temp]) {
				newStatMult[temp] = (Math.random() * .8) + .6;
				isChanged[temp] = true;
			}
			else {
				i--;
			}
		}
		//Average
		for(int i = 0; i < statMerge; i++) {
			int temp = (int)(Math.random() * NUM_STATS);
			if(!isChanged[temp]) {
				newStatMult[temp] = (m.stats[temp] + d.stats[temp])/2;
				isChanged[temp] = true;
			}
			else {
				i--;
			}
		}
		//Pick
		for(int i = 0; i < statPick; i++) {
			int temp = (int)(Math.random() * NUM_STATS);
			if(!isChanged[temp]) {
				int pick = (int)(Math.random() * 2);
				if(pick == 0) {
					newStatMult[temp] = m.stats[temp];
				}
				else {
					newStatMult[temp] = d.stats[temp];
				}
				isChanged[temp] = true;
			}
			else {
				i--;
			}
		}
		//Added Variance
		for(int i = 0; i < newStatMult.length; i++) {
			double var = (Math.abs(newStatMult[i] - 1)/3);
			double change = (Math.random() * var);
			int temp = (int)(Math.random() * 2);
			if(temp == 0) {
				newStatMult[i] += change;
			}
			else {
				newStatMult[i] -= change;
			}
		}
		return newStatMult;
	}
	
	//Uses statRoll algorithm to random personality.
	public static double[] perRoll(Character m, Character d) {
		double[] newPer = new double[NUM_PER];
		int perMerge = (int)(Math.random() * (NUM_PER/2));
		int perPick = (int)(Math.random() * (NUM_PER/2));
		int perLeft = NUM_PER - perMerge - perPick;
		boolean[] isChanged = new boolean[NUM_PER];
		for(int i = 0; i < NUM_PER; i++) {
			isChanged[i] = false;
		}
		if((int)(Math.random() * 2) == 0) {
			newPer = m.stats;
		}
		else {
			newPer = d.stats;
		}
		//Complete Random
		for(int i = 0; i < perLeft; i++) {
			int temp = (int)(Math.random() * NUM_PER);
			if(!isChanged[temp]) {
				newPer[temp] = (Math.random() * 50) + 25;
				isChanged[temp] = true;
			}
			else {
				i--;
			}
		}
		//Average
		for(int i = 0; i < perMerge; i++) {
			int temp = (int)(Math.random() * NUM_PER);
			if(!isChanged[temp]) {
				newPer[temp] = (m.stats[temp] + d.stats[temp])/2;
				isChanged[temp] = true;
			}
			else {
				i--;
			}
		}
		//Pick
		for(int i = 0; i < perPick; i++) {
			int temp = (int)(Math.random() * NUM_PER);
			if(!isChanged[temp]) {
				int pick = (int)(Math.random() * 2);
				if(pick == 0) {
					newPer[temp] = m.stats[temp];
				}
				else {
					newPer[temp] = d.stats[temp];
				}
				isChanged[temp] = true;
			}
			else {
				i--;
			}
		}
		//Added Variance
		for(int i = 0; i < newPer.length; i++) {
			double var = (Math.abs(newPer[i] - 50)/3);
			double change = (Math.random() * var);
			int temp = (int)(Math.random() * 2);
			if(temp == 0) {
				newPer[i] += change;
			}
			else {
				newPer[i] -= change;
			}
		}
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
	 * 5 = Life, 6 = Appeal, 7 = Stamina, 8 = Stress, 9 = Health, 10 = Height,
	 * 11 = Weight, 12 = Perception
	 * 
	 * Decaytion Affected Stats: 0, 1, 2, 3, 7, 8, 10, 11
	 */

	// Stats Get Set
	public double getStats(int i) {
		return stats[i];
	}
	
	public void setStats(int i, double val) {
		stats[i] = val;
	}
	
	public double getStrength() {
		return stats[0] * decaytion;
	}

	public void setStrength(double val) {
		stats[0] = val;
	}

	public double getDexterity() {
		return stats[1] * decaytion;
	}

	public void setDexterity(double val) {
		stats[1] = val;
	}

	public double getCharisma() {
		return stats[2] * decaytion;
	}

	public void setCharisma(double val) {
		stats[2] = val;
	}

	public double getIntelligence() {
		return stats[3] * decaytion;
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

	public double getAppeal() {
		return stats[6];
	}

	public void setAppeal(double val) {
		stats[6] = val;
	}

	public double getStamina() {
		return stats[7] * decaytion;
	}

	public void setStamina(double val) {
		stats[7] = val;
	}
	
	public double getStress() {
		return stats[8] * decaytion;
	}
	
	public void setStress(double val) {
		stats[8] = val;
	}

	public double getHealth() {
		return stats[9];
	}

	public void setHealth(double val) {
		stats[9] = val;
	}
	
	public double getHeight() {
		return stats[10] * decaytion;
	}
	
	public void setHeight(double val) {
		stats[10] = val;
	}
	
	public double getWeight() {
		return stats[11] * decaytion;
	}
	
	public void setWeight(double val) {
		stats[11] = val;
	}
	
	public double getPerception() {
		return stats[12];
	}
	
	public void setPerception(double val) {
		stats[12] = val;
	}
	
	//StatMult Get Set
	public double getStatMult(int i) {
		return statMult[i];
	}
	
	public void setStatMult(int i, double val) {
		statMult[i] = val;
	}

	/*
	 * PERSONALITY:
	 * 0 = Temper, 1 = Criminality, 2 = Good/Evil, 3 = Laziness, 4 = Independence, 5 = Sanity, 
	 * 6 = Courage, 7 = Compassion, 8 = Curiosity, 9 = Loyalty, 10 = Honesty
	 */
	public double getPer(int i) {
		return personality[i];
	}
	
	public void setPer(int i, double val) {
		personality[i] = val;
	}
	
	public double getTemper() {
		return personality[0];
	}
	
	public void setTemper(double val) {
		personality[0] = val;
	}
	
	public double getCriminality() {
		return personality[1];
	}
	
	public void setCriminality(double val) {
		personality[1] = val;
	}
	
	public double getGoodEvil() {
		return personality[2];
	}
	
	public void setGoodEvil(double val) {
		personality[2] = val;
	}
	
	public double getLaziness() {
		return personality[3];
	}
	
	public void setLaziness(double val) {
		personality[3] = val;
	}
	
	public double getIndependence() {
		return personality[4];
	}
	
	public void setIndependence(double val) {
		personality[4] = val;
	}
	
	public double getSanity() {
		return personality[5];
	}
	
	public void setSanity(double val) {
		personality[5] = val;
	}
	
	public double getCourage() {
		return personality[6];
	}
	
	public void setCourage(double val) {
		personality[6] = val;
	}
	
	public double getCompassion() {
		return personality[7];
	}
	
	public void setCompassion(double val) {
		personality[7] = val;
	}
	
	public double getCuriosity() {
		return personality[8];
	}
	
	public void setCuriosity(double val) {
		personality[8] = val;
	}
	
	public double getLoyalty() {
		return personality[9];
	}
	
	public void setLoyalty(double val) {
		personality[9] = val;
	}
	
	public double getHonesty() {
		return personality[10];
	}
	
	public void setHonesty(double val) {
		personality[10] = val;
	}
	
	/*
	 * SKILLS:
	 * 0 = Hunting, 1 = Gathering, 2 = Tool Craft, 3 = Slash, 4 = Blunt, 5 = Pierce,
	 * 6 = Block
	 */
	public double getSkill(int i) {
		return skills[i];
	}
	
	public void setSkill(int i, double val) {
		skills[i] = val;
	}
	
	//SkillAff Get Set
	public double getSkillAff(int i) {
		return skillAff[i];
	}
	
	public void setSkillAff(int i, double val) {
		skillAff[i] = val;
	}
	
	/*
	 * TRAITS:
	 * 
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String val) {
		name = val;
	}

	public int getId() {
		return id;
	}
}
