import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class GenericLinkedList<T> implements Iterable<T> {
	/**
	 * First node
	 */
	private Node head;
	/**
	 * Last node
	 */
	private Node tail;
	/**
	 * The number of entries
	 */
	private int numEntries;
	
	/**
	 * This constructor sets head and tail to null and numEntries to 0.
	 */
	public GenericLinkedList() {
		this.head=null;
		this.tail=null;
		numEntries=0;
	}
	
	/**
	 * This class represents a Node, which holds
	 * data, a reference to the next node, and a reference to the previous node.
	 */
	private class Node{
		/**
		 * The data
		 */
		private T data;
		/**
		 * The address of the next node
		 */
		private Node next;
		/**
		 * The address of the previous node
		 */
		private Node prev;
		/**
		 * This constructor takes in values for data, next, and prev. It assigns the values.
		 * @param data
		 * @param nextNode
		 * @param prevNode
		 */
		public Node(T data, Node nextNode, Node prevNode) {
			this.data=data;
			next=nextNode;
			prev=prevNode;
		}
		/**
		 * This constructor only takes in data and assigns next and prev to null.
		 * @param data
		 */
		public Node(T data) {
			this.data=data;
			next=null;
			prev=null;
		}
	}
	
	/**
	 * This method is used to add a node to the front of the list.
	 * @param entry
	 */
	public void addFirst(T entry) {
		Node newNode = new Node(entry);
		if (isEmpty()) {
			head=newNode;
			tail=newNode;
		} else {
			newNode.next=head;
			head.prev=newNode;
			head=newNode;
		}
		numEntries++;
	}
	
	/**
	 * This method is used to add a node to the end of the list.
	 * @param entry
	 */
	public void addLast(T entry) {
		Node newNode = new Node(entry);
		if (isEmpty()) {
			head=newNode;
			tail=newNode;
		} else {
			tail.next=newNode;
			newNode.prev=tail;
			tail=newNode;
		}
		numEntries++;
	}
	
	/**
	 * This method checks if the list is empty.
	 * @return True if the list is empty
	 */
	public boolean isEmpty() {
		return size()==0;
	}
	
	/**
	 * This method returns the size of the list.
	 * @return The number of entries
	 */
	public int size() {
		return numEntries;
	}
	
	/**
	 * This method checks if an element is in the list.
	 * @param element
	 * @return True if the element is in the list
	 */
	public boolean contains(T element) {
		Iterator<T> iter1 = iterator();
		while (iter1.hasNext()) {
			if ((iter1.next()).equals(element)) {
				return true;
				
			}
		}
		return false;
	}
	
	/**
	 * This method returns the element at a given index.
	 * @param index
	 * @return The object at that index
	 */
	public T get(int index) {
		int counter = 0;
		Iterator<T> iter = iterator();
		while (counter!=index && iter.hasNext()) {
			iter.next();
			counter++;
		}
		return iter.next();
	}
	
	/**
	 * This method returns the data for the head.
	 * @return The head's data
	 */
	public T getFirst() {
		return head.data;
	}
	
	/**
	 * This method returns the data for the tail.
	 * @return The tail's data
	 */
	public T getLast() {
		return tail.data;
	}
	
	/**
	 * This method returns an instance of GenericIterator.
	 */
	public ListIterator<T> iterator() {
		return new GenericIterator();
	}
	
	/**
	 * This method removes the element at a given index.
	 * @param index
	 * @return The element at the given index
	 */
	public T remove(int index) {
		if (index>=size()) {
			throw new IndexOutOfBoundsException();
		}
		int counter = 0;
		Iterator<T> iter = iterator();
		while (counter!=index&&iter.hasNext()) {
			iter.next();
			counter++;
		}
		T val = iter.next();
		iter.remove();
		return val;
	}
	
	/**
	 * This method removes the first element in the list.
	 * @return The first element in the list
	 */
	public T removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		else {
			return remove(0);
		}
	}
	
	/**
	 * This method removes the last element in the list.
	 * @return The last element in the list
	 */
	public T removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		else {
			return remove(size()-1);
		}
	}
	
	/**
	 * This method converts the linked list to an array.
	 * @return The new array
	 */
	public Object[] toArray() {
		Object[] arr = new Object[numEntries];
		Node current = head;
		int index = 0;
		while (current!=null) {
			arr[index]=current.data;
			index++;
			current=current.next;
		}
		return arr;
		
	}
	
	/**
	 * This method removes a given element.
	 * @param element
	 * @return True if the removal was successful
	 */
	public boolean remove(T element) {
		Iterator<T> iter=iterator();
		while (iter.hasNext()) {
			if ((iter.next()).equals(element)) {
				iter.remove();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method clears the list.
	 */
	public void clear() {
		while (size()>0) {
			removeLast();
		}
	}
	
	/**
	 * This class represents a custom iterator that
	 * can traverse back and forth in a linked list.
	 */
	private class GenericIterator implements ListIterator<T>{
		/**
		 * The current node
		 */
		private Node current = head;
		/**
		 * Whether next() was called or not
		 */
		private boolean wasNextCalled=false;
		/**
		 * Whether previous() was called or next
		 */
		private boolean wasPrevCalled=false;
		/**
		 * The node next() or previous() returned
		 */
		private Node beforeNode;
		
		/**
		 * This method checks if there is a next node.
		 */
		@Override
		public boolean hasNext() {
			return current!=null;
		}
		
		/**
		 * This method returns the next node.
		 */
		@Override
		public T next() {
			if (hasNext()) {
				wasNextCalled=true;
				T val = current.data;
				beforeNode=current;
				current=current.next;
				wasNextCalled=false;
				return val;
			}
			else {
				throw new NoSuchElementException();
			}
		}
		
		/**
		 * This method checks if there's a previous node.
		 */
		@Override
		public boolean hasPrevious() {
			if (current==null) {
				return tail!=null;
			}
			return current.prev!=null;
		}
		
		/**
		 * This method returns the previous node.
		 */
		@Override
		public T previous() {
			if (hasPrevious()) {
				T val = null;
				if (current==null) {
					current=tail;
				} else {
					current=current.prev;
				}
				val = current.data;
				beforeNode=current;
				wasPrevCalled=false;
				return val;
			}
			else {
				throw new NoSuchElementException();
			}
		}
		
		/**
		 * This method removes the node returned by next() or previous().
		 */
		@Override
		public void remove() {
			if (beforeNode==null) {
				throw new IllegalStateException();
			}
			if (numEntries==1) {
				head=null;
			} else if (beforeNode.prev==null) {
				head=beforeNode.next;
				head.prev=null;
			} else if (beforeNode.next==null) {
				tail=beforeNode.prev;
				tail.next=null;
			} else {
				beforeNode.prev.next=beforeNode.next;
				beforeNode.next.prev=beforeNode.prev;
			}
			beforeNode=null;
			numEntries--;
			
		}
		
		/**
		 * This method throws UnsupportedOperationException.
		 */
		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}
		
		/**
		 * This method throws UnsupportedOperationException.
		 */
		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}
		
		/**
		 * This method throws UnsupportedOperationException.
		 */
		@Override
		public void set(T e) {
			throw new UnsupportedOperationException();
			
		}
		
		/**
		 * This method throws UnsupportedOperationException.
		 */
		@Override
		public void add(T e) {
			throw new UnsupportedOperationException();
			
		}
	}
}
