import java.util.ArrayList;

/*
 * An entire mapping of a character's relation, including ex-partners and dead people.
 * A RelationTreeNode stores only the integer CharacterID value, not the Character itself.
 */
public class RelationTreeNode {
	private ArrayList<RelationTreeNode> parents;
	private ArrayList<RelationTreeNode> children;
	private ArrayList<RelationTreeNode> siblings;
	private ArrayList<RelationTreeNode> partners;
	private int rootVal;

	RelationTreeNode() {
		parents = new ArrayList<RelationTreeNode>();
		children = new ArrayList<RelationTreeNode>();
		siblings = new ArrayList<RelationTreeNode>();
		partners = new ArrayList<RelationTreeNode>();
		rootVal = -1;
	}

	RelationTreeNode(int c) {
		this();
		rootVal = c;
	}
	
	RelationTreeNode(Character c) {
		this();
		rootVal = c.getId();
	}

	// Convert Character to RelationTree and add to children.
	public void addChild(int c) {
		children.add(new RelationTreeNode(c));
	}

	// Convert Character to RelationTree and add to parents.
	public void addParent(int c) {
		parents.add(new RelationTreeNode(c));
	}

	// Convert Character to RelationTree and add to siblings.
	public void addSibling(int c) {
		siblings.add(new RelationTreeNode(c));
	}

	// Convert Character to RelationTree and add to partners.
	public void addPartners(int c) {
		partners.add(new RelationTreeNode(c));
	}

	// Getters
	// Root getters
	public int getRootVal() {
		return rootVal;
	}

	// Child getters
	public ArrayList<RelationTreeNode> getChildren() {
		return children;
	}

	public RelationTreeNode getChild(int i) {
		return children.get(i);
	}

	public int getChildVal(int i) {
		RelationTreeNode temp = getChild(i);
		return temp.rootVal;
	}

	// Parent getters
	public ArrayList<RelationTreeNode> getParents() {
		return parents;
	}

	public RelationTreeNode getParent(int i) {
		return parents.get(i);
	}

	public int getParentVal(int i) {
		RelationTreeNode temp = getParent(i);
		return temp.rootVal;
	}

	// Sibling getters
	public ArrayList<RelationTreeNode> getSiblings() {
		return siblings;
	}

	public RelationTreeNode getSibling(int i) {
		return siblings.get(i);
	}

	public int getSiblingVal(int i) {
		RelationTreeNode temp = getSibling(i);
		return temp.rootVal;
	}

	// Partner getters
	public ArrayList<RelationTreeNode> getPartners() {
		return partners;
	}

	public RelationTreeNode getPartner(int i) {
		return partners.get(i);
	}

	public int getPartnerVal(int i) {
		RelationTreeNode temp = getPartner(i);
		return temp.rootVal;
	}
}
