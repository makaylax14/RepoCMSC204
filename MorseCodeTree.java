import java.util.ArrayList;

/**
 * This class is responsible for implementing LinkedConverterTreeInterface
 * and mimics the tree that will hold the alphabet.
 * @param <T>
 */
public class MorseCodeTree<T> implements LinkedConverterTreeInterface<String>{
	/**
	 * The root node
	 */
	private TreeNode<String> root;
	/**
	 * This constructor calls buildTree().
	 */
	public MorseCodeTree() {
		buildTree();
	}
	/**
	 * This method allows a node to be added to the right place.
	 */
	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		if (code.length()==1) {
			if (code.charAt(0)=='.') {
				root.setLeftChild(new TreeNode<String>(letter));
			}
			else {
				root.setRightChild(new TreeNode<String>(letter));
			}
			return;
		}
		if (code.charAt(0)=='.') {
			root=root.getLeftChild();
			addNode(root, code.substring(1), letter);
		}
		if (code.charAt(0)=='-') {
			root=root.getRightChild();
			addNode(root, code.substring(1), letter);
		}
	}
	/**
	 * This method builds the tree.
	 */
	@Override
	public void buildTree() {
		setRoot(new TreeNode<String>(""));
		insert(".", "e");
		insert("-", "t");
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
	}
	/**
	 * This method calls addNode.
	 */
	@Override
	public void insert(String code, String letter) {
		addNode(root, code, letter);
	}
	/**
	 * This method is not supported.
	 */
	@Override
	public MorseCodeTree delete(String data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
	/**
	 * This method is not supported.
	 */
	@Override
	public MorseCodeTree update() throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
	/**
	 * This method calls fetchNode.
	 */
	@Override
	public String fetch(String code) {
		return fetchNode(root, code);
	}
	/**
	 * This method finds the letter corresponding to a given code.
	 */
	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		if (code.length()==1) {
			if (code.charAt(0)=='.') {
				if (root.getLeftChild()!=null) {
					return root.getLeftChild().getData();
				}
			}
			else {
				if (root.getRightChild()!=null) {
					return root.getRightChild().getData();
				}
			}
			return "";
		}
		else if (code.charAt(0)=='.') {
			root=root.getLeftChild();
			return fetchNode(root, code.substring(1));
		}
		else {
			root=root.getRightChild();
			return fetchNode(root, code.substring(1));
		}
	}
	/**
	 * This method returns the root.
	 */
	@Override
	public TreeNode<String> getRoot(){
		return root;
	}
	/**
	 * This method traverses through the tree using LNR traversal.
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if (root!=null) {
			LNRoutputTraversal(root.getLeftChild(), list);
			list.add(root.getData());
			LNRoutputTraversal(root.getRightChild(), list);
		}
	}
	/**
	 * This method sets the root.
	 */
	@Override
	public void setRoot(TreeNode<String> newNode) {
		root=newNode;
	}
	/**
	 * This method returns an ArrayList with all the letters in LNR traversal order.
	 */
	@Override
	public ArrayList<String> toArrayList(){
		ArrayList<String> list= new ArrayList<>();
		LNRoutputTraversal(root, list);
		return list;
	}
}
