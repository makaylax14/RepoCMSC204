/**
 * This class represents an order. It has
 * several attributes, including id, arrivalMinute, and deadlineMinute.
 */
public class Order {
	/**
	 * Represents the id
	 */
	private String id;
	/**
	 * Represents when the order arrived
	 */
	private int arrivalMinute;
	/**
	 * Represents the order's deadline
	 */
	private int deadlineMinute;
	/**
	 * This constructor takes in values for id and deadline and initializes the attributes.
	 * @param id
	 * @param deadline
	 */
	public Order(String id, int deadline) {
		this.id=id;
		this.deadlineMinute=deadline;
	}
	/**
	 * This constructor is a no-arg constructor and initializes all the attributes to their default values.
	 */
	public Order() {
		id=null;
		arrivalMinute=0;
		deadlineMinute=0;
	}
	/**
	 * This method sets the arrival time.
	 * @param time
	 */
	public void setArrivalMinute(int time) {
		arrivalMinute=time;
	}
	/**
	 * This method returns the arrival time.
	 * @return arrivalMinute
	 */
	public int getArrivalMinute() {
		return arrivalMinute;
	}
	/**
	 * This method returns the deadline time.
	 * @return deadlineMinute
	 */
	public int getDeadlineMinute() {
		return deadlineMinute;
	}
	/**
	 * This method returns the id.
	 * @return id
	 */
	public String getId() {
		return id;
	}
}
