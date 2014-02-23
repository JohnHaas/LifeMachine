import java.util.ArrayList;

//An entire mapping of a character's relation, including ex-partners and dead people.
public class RelationTree<Character> {
	private ArrayList<RelationTree<Character>> parent;
	private ArrayList<RelationTree<Character>> children;
	private ArrayList<RelationTree<Character>> siblings;
	private ArrayList<RelationTree<Character>> partners;
	private RelationTree<Character> root;
	private Character rootVal;

	RelationTree() {
		this(null, null, null, null, null, null);
	}

	RelationTree(Character c) {
		this(null, null, null, null, null, c);
		root = new RelationTree<Character>(c);
	}

	RelationTree(RelationTree<Character> root) {
		this(null, null, null, null, root, root.rootVal);
	}

	RelationTree(ArrayList<RelationTree<Character>> prt,
			ArrayList<RelationTree<Character>> cld,
			ArrayList<RelationTree<Character>> sbls,
			ArrayList<RelationTree<Character>> ptrs,
			RelationTree<Character> rt, Character rtVal) {
		parent = prt;
		children = cld;
		siblings = sbls;
		partners = ptrs;
		root = rt;
		rootVal = rtVal;
	}

	// Convert Character to RelationTree and add to children.
	public void addChild(Character c) {
		children.add(new RelationTree<Character>(c));
	}

	public void addChild(ArrayList<Character> c) {
		for (int i = 0; i < c.size(); i++) {
			addChild(c.get(i));
		}
	}

	// Convert Character to RelationTree and add to parents.
	public void addParent(Character c) {
		parent.add(new RelationTree<Character>(c));
	}

	public void addParent(ArrayList<Character> c) {
		for (int i = 0; i < c.size(); i++) {
			addParent(c.get(i));
		}
	}

	// Convert Character to RelationTree and add to siblings.
	public void addSibling(Character c) {
		siblings.add(new RelationTree<Character>(c));
	}

	public void addSibling(ArrayList<Character> c) {
		for (int i = 0; i < c.size(); i++) {
			addSibling(c.get(i));
		}
	}

	// Convert Character to RelationTree and add to partners.
	public void addPartners(Character c) {
		partners.add(new RelationTree<Character>(c));
	}

	public void addPartners(ArrayList<Character> c) {
		for (int i = 0; i < c.size(); i++) {
			addPartners(c.get(i));
		}
	}

	// Getters
	// Root getters
	public RelationTree<Character> getRoot() {
		return root;
	}

	public Character getRootVal() {
		return rootVal;
	}

	// Child getters
	public ArrayList<RelationTree<Character>> getChildren() {
		return children;
	}

	public RelationTree<Character> getChild(int i) {
		return children.get(i);
	}

	public Character getChildVal(int i) {
		RelationTree<Character> temp = getChild(i);
		return temp.rootVal;
	}

	// Parent getters
	public ArrayList<RelationTree<Character>> getParents() {
		return parent;
	}

	public RelationTree<Character> getParent(int i) {
		return parent.get(i);
	}

	public Character getParentVal(int i) {
		RelationTree<Character> temp = getParent(i);
		return temp.rootVal;
	}

	// Sibling getters
	public ArrayList<RelationTree<Character>> getSiblings() {
		return siblings;
	}

	public RelationTree<Character> getSibling(int i) {
		return siblings.get(i);
	}

	public Character getSiblingVal(int i) {
		RelationTree<Character> temp = getSibling(i);
		return temp.rootVal;
	}

	// Partner getters
	public ArrayList<RelationTree<Character>> getPartners() {
		return partners;
	}

	public RelationTree<Character> getPartner(int i) {
		return partners.get(i);
	}

	public Character getPartnerVal(int i) {
		RelationTree<Character> temp = getPartner(i);
		return temp.rootVal;
	}
}
