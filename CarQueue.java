import java.util.Random;

public class CarQueue implements Runnable{
	private int size=0;
	private int[] arr;
	public CarQueue() {
		arr= new int[100];
		addToQueue();
		addToQueue();
		addToQueue();
		addToQueue();
		addToQueue();
		addToQueue();
		addToQueue();
		addToQueue();
		addToQueue();
		addToQueue();
	}
	public void addToQueue() {
		Thread t = new Thread(this);
		t.start();
	}
	@Override
	public void run() {
		Random rand = new Random();
		arr[size]=rand.nextInt(4);
		size++;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	public int deleteQueue() {
		do {
			try {
				if (size==0) {
					Thread.sleep(3000);
				}
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		} while (size==0);
		int deleted = arr[0];
		for (int i = 0; i < size-1; i++) {
			arr[i]=arr[i+1];
		}
		size--;
		return deleted;
	}
}
