import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * This class mimics a queue using an array.
 * It sorts the orders that are added.
 * @param <T>
 */
public class MyPriorityQueue<T> implements PriorityQueueADT<T> {
	/**
	 * An array that simulates a queue
	 */
	private T[] queue;
	/**
	 * The next back index
	 */
	private int backIndex;
	/**
	 * The default capacity
	 */
	private static final int DEFAULT_CAPACITY=10;
	/**
	 * The comparator
	 */
	private Comparator<T> comparator;
	
	/**
	 * This method uses user-given values to decide the capacity and assign a comparator to comparator.
	 * @param capacity
	 * @param comparator
	 */
	public MyPriorityQueue(int capacity, Comparator<T> comparator) {
		if (capacity>MAX_CAPACITY) {
			throw new IllegalStateException("Capacity is too large");
		}
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[capacity];
		queue=temp;
		backIndex=0;
		this.comparator=comparator;
	}
	
	/**
	 * This method calls the other constructor.
	 * @param comparator
	 */
	public MyPriorityQueue(Comparator<T> comparator) {
		this(DEFAULT_CAPACITY, comparator);
	}
	
    /**
     * Inserts an item into the queue according to the priority order (earliest deadline or earliest arrival
     * if the deadlines are the same)
     * @param item the element to insert (cannot be null)
     * @throws IllegalArgumentException if item is null
     */
	@Override
    public void enqueue (T item) {
    	int index=backIndex;
    	if (item==null) {
    		throw new IllegalArgumentException("Item is null");
    	}
    	else if (size()==queue.length){
    		throw new IllegalStateException("PriorityQueue is full");
    	}
    	else {
    		for (int count = 0; count < size(); count++) {
    			if (comparator.compare(queue[count], item)>0) {
    				index=count;
    				break;
    			}
    		}
    		for (int count = backIndex-1; count >= index; count--) {
    			queue[count+1]=queue[count];
    		}
    		queue[index]=item;
    		backIndex++;
    	}
    	
    }

    /**
     * Removes and returns the highest-priority item (item at the first index)
     * @return the dequeued element
     * @throws NoSuchElementException if the queue is empty
     */
	@Override
    public T dequeue() {
		T temp = null;
    	if (isEmpty()) {
    		throw new NoSuchElementException("PriorityQueue is empty");
    	}
    	else {
    		temp=queue[0];
    		for (int count = 0; count < size()-1; count++) {
    			queue[count]=queue[count+1];
    		}
    	}
    	backIndex--;
    	queue[backIndex]=null;
    	return temp;
    }

    /**
     * Returns (without removing) the highest-priority item and checks if the queue is empty
     * @return the front element
     * @throws NoSuchElementException if the queue is empty
     */
	@Override
    public T peek() {
		T temp=null;
    	if (isEmpty()) {
    		throw new NoSuchElementException("PriorityQueue is empty");
    	}
    	else {
    		temp = queue[0];
    	}
    	return temp;
    }

    /**
     * Checks whether the queue has no elements using backIndex
     * @return true if empty, false otherwise
     */
	@Override
    public boolean isEmpty() {
    	return (backIndex)==0;
    }

    /**
     * Returns the current number of elements in the queue using backIndex
     * @return number of elements
     */
	@Override
    public int size() {
    	return backIndex;
    }
    
    /**
     * Returns an array containing all elements in queue,
     * in the same order as the array is in
     */
	@Override
    public Object[] toArray() {
    	Object[] arr = new Object[size()];
    	for (int count = 0; count < size(); count++) {
    		arr[count]=queue[count];
    	}
    	return arr;
    }
    
}
