import java.util.ArrayList;

/*
 * Global item list holder.
 * Global ID to track each item as unique.
 * Stats and SkillAff are the amount that is boosted, so the application is Item.stats + Character.stats
 */
public class Item {
	private static ArrayList<Item> itemList = new ArrayList<Item>();
	private static int globalId = 0;
	
	private int id;
	private String name;
	private String descrip;
	private double[] stats;
	private double[] statMult;
	private double[] personality;
	private double[] skills;
	private double[] skillAff;
	
	private String colName;
	private double weight;
	private boolean canDrop;
	
	Item() {
		name = "";
		descrip = "";
		id = globalId;
		id++;
		
		stats = new double[Character.NUM_STATS];
		statMult = new double[Character.NUM_STATS];
		personality = new double[Character.NUM_PER];
		skills = new double[Character.NUM_SKILL];
		skillAff = new double[Character.NUM_SKILL];
		
		colName = "";
		weight = 0;
		canDrop = true;
		itemList.add(this);
	}
	
	Item(String name) {
		this();
		this.name = name;
	}
	
	Item(double[] stats) {
		this();
		this.stats = stats;
	}
	
	Item(double[] stats, double[] statMult) {
		this();
		this.stats = stats;
		this.statMult = statMult;
	}
	
	Item(double[] stats, double[] statMult, double[] skillAff) {
		this();
		this.stats = stats;
		this.statMult = statMult;
		this.skillAff = skillAff;
	}
	
	//Item Effect Application
	public void applyItem(Character c) {
		for(int i = 0; i < Character.NUM_STATS; i++) {
			c.setStats(i, c.getStats(i) + stats[i]);
		}
		for(int i = 0; i < Character.NUM_STATS; i++) {
			c.setStatMult(i, c.getStats(i) + statMult[i]);
		}
		for(int i = 0; i < Character.NUM_SKILL; i++) {
			c.setSkillAff(i, c.getSkillAff(i) + skillAff[i]);
		}
	}
	
	public void removeItem(Character c) {
		for(int i = 0; i < Character.NUM_STATS; i++) {
			c.setStats(i, c.getStats(i) - stats[i]);
		}
		for(int i = 0; i < Character.NUM_STATS; i++) {
			c.setStatMult(i, c.getStats(i) - statMult[i]);
		}
		for(int i = 0; i < Character.NUM_SKILL; i++) {
			c.setSkillAff(i, c.getSkillAff(i) - skillAff[i]);
		}
	}
	
	//ItemList Changes
	public static Item getItem(int i) {
		return itemList.get(i);
	}
	
	//Get Set
	public double[] getStats() {
		return stats;
	}
	
	public void setStats(double[] val) {
		stats = val;
	}
	
	public double[] getStatMult() {
		return statMult;
	}
	
	public void setStatMult(double[] val) {
		statMult = val;
	}
	
	public double[] getPersonality() {
		return personality;
	}
	
	public void setPersonality(double[] val) {
		personality = val;
	}
	
	public double[] getSkills() {
		return skills;
	}
	
	public void setSkills(double[] val) {
		skills = val;
	}
	
	public double[] getSkillAff() {
		return skillAff;
	}
	
	public void setSkillAff(double[] val) {
		skillAff = val;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String val) {
		name = val;
	}
	
	public String getDescrip() {
		return descrip;
	}
	
	public void setDescrip(String val) {
		descrip = val;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double val) {
		weight = val;
	}
	
	public boolean getDrop() {
		return canDrop;
	}
	
	public void setDrop() {
		canDrop = !canDrop;
	}
	
	public String getColName() {
		return colName;
	}
	
	public void setColName(String val) {
		colName = val;
	}
	
	public int getId() {
		return id;
	}
}
