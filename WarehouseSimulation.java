/**
 * This class represents a warehouse. It performs tasks every minute,
 * including shipping and releasing orders.
 */
public class WarehouseSimulation implements SimulationInterface {
	/**
	 * The current time
	 */
	private int currentMinute;
	/**
	 * Total number of orders shipped
	 */
	private int totalShipped;
	/**
	 * Total number of orders late
	 */
	private int totalLate;
	/**
	 * Total number of orders arrived
	 */
	private int totalArrived;
	/**
	 * Current index of orders
	 */
	private int index;
	/**
	 * Array that holds the orders
	 */
	private Order[] orders;
	/**
	 * The queue
	 */
	private MyPriorityQueue<Order> queueOfOrders;
	/**
	 * The stack
	 */
	private MyStack<Order> stackOfOrders;
	/**
	 * The comparator for sorting orders
	 */
	private OrderComparator oc;
	
	/**
	 * This constructor takers in orders and assigns it to the attribute. Other attributes are assigned 0.
	 * @param orders The set of orders
	 */
	public WarehouseSimulation(Order[] orders) {
		this.orders=orders;
		oc = new OrderComparator();
		queueOfOrders=new MyPriorityQueue<>(orders.length, oc);
		stackOfOrders=new MyStack<>(orders.length);
		currentMinute=0;
		totalShipped=0;
		totalLate=0;
		totalArrived=0;
		index=0;
	}
	
	/**
     * Advance the simulation by exactly one minute and perform tasks.
     * This may involve:
     *  - Adding new orders to the queue from the orders array
     *  - Shipping one order
     *  - Moving late orders to the returns stack
     */
	@Override
    public void tick() {
    	Order order=null;
    	if (index<orders.length) {
    		order=orders[index];
    		order.setArrivalMinute(currentMinute);
    		queueOfOrders.enqueue(order);
    		totalArrived++;
    		index++;
    	}
    	if (!queueOfOrders.isEmpty()) {
    		Order shippedOrder = queueOfOrders.dequeue();
    		totalShipped++;
    		if (shippedOrder.getDeadlineMinute()<currentMinute) {
    			stackOfOrders.push(shippedOrder);
    			totalLate++;
    		}
    		
    	}
    	currentMinute++;
    }

    /**
     * Returns true if:
     *  - All orders have been released into the queue, AND
     *  - The queue is empty (all orders shipped).
     *  Returns false otherwise
     */
	@Override
    public boolean isFinished() {
		if((index)==orders.length) {
			if (queueOfOrders.isEmpty()) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

    /** Returns the current simulation time in minutes using currentMinute */
    @Override
    public int getCurrentMinute() {
    	return currentMinute;
    }

    /** Returns the total number of orders that have arrived using totalArrived. */
	@Override
    public int getTotalArrived() {
		return totalArrived;
	}

    /** Returns the total number of orders that have been shipped using totalShipped. */
	@Override
    public int getTotalShipped() {
		return totalShipped;
	}

    /** Returns the total number of orders that shipped late using totalLate. */
	@Override
    public int getTotalLate() {
		return totalLate;
	}
}
