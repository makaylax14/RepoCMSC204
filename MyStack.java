import java.util.NoSuchElementException;

/**
 * This class mimicks a stack. It holds
 * the orders that are late.
 * @param <T>
 */
public class MyStack<T> implements StackADT<T>{
	/**
	 * An array that represents stack
	 */
	private T[] stack;
	/**
	 * The top index (last index)
	 */
	private int topIndex;
	/**
	 * The default capacity when the user doesn't give a value
	 */
	private static final int DEFAULT_CAPACITY=10;
	
	/**
	 * The constructor uses capacity to initialize stack.
	 * @param capacity
	 */
	public MyStack(int capacity) {
		if (capacity>MAX_CAPACITY) {
			throw new IllegalStateException("Capacity is too large");
		}
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[capacity];
		stack=temp;
		topIndex=-1;
	}
	
	/**
	 * This method calls the other constructor
	 */
	public MyStack() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
     * Pushes an item onto the top of the stack (adds it to the end)
     * @param item the element to push 
     * @throws IllegalArgumentException if item is null 
     * @throws IllegalStateException if stack has reached max capacity
     */
	@Override
    public void push(T item) {
    	if (item==null) {
    		throw new IllegalArgumentException("Item is null");
    	}
    	if (size()==MAX_CAPACITY || size()==stack.length) {
    		throw new IllegalStateException("Stack is full");
    	}
    	else {
    		stack[topIndex+1]=item;
        	topIndex++;
    	}
    }

    /**
     * Removes and returns the top item from the stack (last index)
     * @return the popped element
     * @throws NoSuchElementException if the stack is empty
     */
	@Override
    public T pop() {
    	if (isEmpty()) {
    		throw new NoSuchElementException("Stack is empty");
    	}
    	else {
    		T top = stack[topIndex];
    		stack[topIndex]=null;
    		topIndex--;
    		return top;
    	}
    }

    /**
     * Returns the top item of the stack without removing. Checks if stack is empty
     * @return the top element
     * @throws NoSuchElementException if the stack is empty
     */
	@Override
    public T peek() {
    	if (isEmpty()) {
    		throw new NoSuchElementException("Stack is empty");
    	}
    	else {
    		return stack[topIndex];
    	}
    }

    /**
     * Checks whether the stack is empty (uses size)
     * @return true if the stack has no elements, false otherwise
     */
	@Override
    public boolean isEmpty() {
    	return (size()==0);
    }

    /**
     * Returns the current number of elements in the stack (uses topIndex+1)
     * @return number of elements
     */
	@Override
    public int size() {
    	return (topIndex+1);
    }
    
    /**
     * Returns an array containing all elements in stack.
     * The element at index 0 is the bottom of the stack, and the
     * element at the index size()-1 is the top.
     */
	@Override
    public Object[] toArray() {
    	Object[] arr = new Object[size()];
    	for (int count = 0; count < size(); count++) {
    		arr[count]=stack[count];
    	}
    	return arr;
    }
}
