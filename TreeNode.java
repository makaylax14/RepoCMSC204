/**
 * This class represents a tree node. Each
 * node holds a letter.
 * @param <T>
 */
public class TreeNode<T> {
	/**
	 * The data
	 */
	private T data;
	/**
	 * The left child address
	 */
	private TreeNode<T> leftChild;
	/**
	 * The right child address
	 */
	private TreeNode<T> rightChild;
	/**
	 * This method creates a TreeNode given the data.
	 * @param dataNode
	 */
	public TreeNode(T dataNode) {
		data=dataNode;
		leftChild=null;
		rightChild=null;
	}
	/**
	 * This method creates a TreeNode by copying the data from another TreeNode.
	 * @param node
	 */
	public TreeNode(TreeNode<T> node) {
		if (node==null) {
			return;
		}
		data=node.getData();
		if (node.getLeftChild()!=null) {
			leftChild=new TreeNode(node.getLeftChild());
		}
		if (node.getRightChild()!=null) {
			rightChild=new TreeNode(node.getRightChild());
		}
	}
	/**
	 * This methdo returns data.
	 * @return Data
	 */
	public T getData() {
		return data;
	}
	/**
	 * This method sets data.
	 * @param data
	 */
	public void setData(T data) {
		this.data=data;
	}
	/**
	 * This method gets the left child.
	 * @return The left child
	 */
	public TreeNode<T> getLeftChild(){
		return leftChild;
	}
	/**
	 * This method gets the right child.
	 * @return The right child
	 */
	public TreeNode<T> getRightChild(){
		return rightChild;
	}
	/**
	 * This method sets the left child.
	 * @param leftChild
	 */
	public void setLeftChild(TreeNode<T> leftChild) {
		this.leftChild=leftChild;
	}
	/**
	 * This method sets the right child.
	 * @param rightChild
	 */
	public void setRightChild(TreeNode<T> rightChild) {
		this.rightChild=rightChild;
	}
	/**
	 * This method checks if there is a left child.
	 * @return True or false
	 */
	public boolean hasLeftChild() {
		return leftChild!=null;
	}
	/**
	 * This method checks if there is a right child.
	 * @return True or false
	 */
	public boolean hasRightChild() {
		return rightChild!=null;
	}
	/**
	 * This method checks if the node is a leaf.
	 * @return True or false
	 */
	public boolean isLeaf() {
		return (!hasLeftChild())&&(!hasRightChild());
	}
	
}
