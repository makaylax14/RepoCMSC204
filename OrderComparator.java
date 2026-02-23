import java.util.Comparator;

/**
 * This class is used to compare two orders
 * when adding an order to the queue.
 */
public class OrderComparator implements Comparator<Order>{
	/**
	 * This method takes in two orders and compares them based on their deadline and arrival times if needed.
	 */
	@Override
	public int compare(Order o1, Order o2) {
		if (o1.getDeadlineMinute()<o2.getDeadlineMinute()){
			return -1;
		} else if (o1.getDeadlineMinute()>o2.getDeadlineMinute()) {
			return 1;
		} else {
			if (o1.getArrivalMinute()<o2.getArrivalMinute()){
				return -1;
			} else if (o1.getArrivalMinute()>o2.getArrivalMinute()) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}
}
