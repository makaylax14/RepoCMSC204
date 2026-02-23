import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WarehouseSimulationStudentTest {

	WarehouseSimulation sim;
	Order[] orders;
	
	@BeforeEach
	void setUp() throws Exception {
		orders=new Order[4];
		orders[0]=new Order("A", 5);
		orders[1]=new Order("B", 6);
		orders[2]=new Order("C", 1);
		orders[3]=new Order ("D", 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		sim=null;
	}

	@Test
	void testWarehouseSimulation() {
		sim=new WarehouseSimulation(orders);
		assertEquals(0, sim.getCurrentMinute());
		assertEquals(0, sim.getTotalShipped());
		assertEquals(0, sim.getTotalLate());
		assertEquals(0, sim.getTotalArrived());
	}

	@Test
	void testTick() {
		sim=new WarehouseSimulation(orders);
		assertEquals(0, sim.getCurrentMinute());
		assertEquals(0, sim.getTotalShipped());
		assertEquals(0, sim.getTotalLate());
		assertEquals(0, sim.getTotalArrived());
		sim.tick();
		assertEquals(1, sim.getCurrentMinute());
		assertEquals(1, sim.getTotalShipped());
		assertEquals(1, sim.getTotalArrived());
		sim.tick();
		assertEquals(2, sim.getTotalShipped());
		assertEquals(2, sim.getTotalArrived());
		sim.tick();
		assertEquals(1, sim.getTotalLate());
		sim.tick();
		assertEquals(4, sim.getCurrentMinute());
		assertEquals(4, sim.getTotalArrived());
		assertEquals(4, sim.getTotalShipped());
	}

	@Test
	void testIsFinished() {
		sim=new WarehouseSimulation(orders);
		assertEquals(false, sim.isFinished());
		sim.tick();
		sim.tick();
		sim.tick();
		sim.tick();
		assertEquals(true, sim.isFinished());
	}

	@Test
	void testGetCurrentMinute() {
		sim=new WarehouseSimulation(orders);
		sim.tick();
		assertEquals(1, sim.getCurrentMinute());
	}

	@Test
	void testGetTotalArrived() {
		sim=new WarehouseSimulation(orders);
		sim.tick();
		sim.tick();
		sim.tick();
		sim.tick();
		assertEquals(4, sim.getTotalArrived());
	}

	@Test
	void testGetTotalShipped() {
		sim=new WarehouseSimulation(orders);
		sim.tick();
		sim.tick();
		assertEquals(2, sim.getTotalArrived());
	}

	@Test
	void testGetTotalLate() {
		sim=new WarehouseSimulation(orders);
		sim.tick();
		sim.tick();
		sim.tick();
		sim.tick();
		assertEquals(2, sim.getTotalLate());
	}

}
