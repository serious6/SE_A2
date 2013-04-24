package assoziation;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class OneToManyVoidTest {
	Order auf1;
	Order auf2;
	Order auf3;
	Order auf4;
	Order auf5;
	OneToManyVoid<Order> auftragliste1;
	CustomerV kunde1;

	@Before
	public void fixture() {
		auf1 = new Order(1);
		auf2 = new Order(2);
		auf3 = new Order(2);
		auf4 = new Order(3);
		auf5 = new Order(4);
		auftragliste1 = new OneToManyVoid<Order>();
		kunde1 = new CustomerV("kunde1");
	}

	@Test(expected=RuntimeException.class)
	public void testAdd() {
		kunde1.add(auf2);
		// Zweimal Aufträge mit der selben Auftragsnummer werden nicht
		// hinzugefügt
		assertEquals(1, kunde1.size());
		kunde1.add(auf3);
		assertEquals(1, kunde1.size());
		// Wir versuchen zwei identische Aufträge hinzuzufügen
		kunde1.add(auf2);
		assertEquals(1, kunde1.size());
		assertTrue(kunde1.contains(auf2));
	}

	@Test
	public void testRemove() {
		//kunde1.add(auf4);
		kunde1.add(auf2);
		kunde1.remove(auf2);
		assertFalse(kunde1.contains(auf2));
	}

	@Test
	public void testContains() {
		kunde1.add(auf2);
		kunde1.add(auf4);
		assertFalse(kunde1.contains(auf1));
		assertTrue(kunde1.contains(auf2));
		assertFalse(kunde1.contains(auf5));
		assertTrue(kunde1.contains(auf4));
	}

	@Test
	public void testClear() {
		kunde1.add(auf2);
		kunde1.add(auf4);
		kunde1.clear();
		assertTrue(kunde1.size() == 0);
	}

	@Test
	public void testSize() {
		kunde1.add(auf2);
		kunde1.add(auf4);
		assertTrue(kunde1.size() == 2);
		kunde1.remove(auf4);
		assertTrue(kunde1.size() == 1);
	}

	@Test
	public void testIsEmpty() {
		assertTrue(kunde1.isEmpty());
	}
}