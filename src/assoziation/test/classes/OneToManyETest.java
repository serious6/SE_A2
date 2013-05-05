package assoziation.test.classes;

import static org.junit.Assert.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import assoziation.Factory;
import assoziation.impl.OneToManyReturnE;
import assoziation.impl.Order;

public class OneToManyETest {
	Order auf1;
	Order auf2;
	Order auf3;
	Order auf4;
	Order auf5;
	OneToManyReturnE<Order> auftragliste1;

	@Before
	public void fixture() {
		auf1 = new Order(1);
		auf2 = new Order(2);
		auf3 = new Order(2);
		auf4 = new Order(3);
		auf5 = new Order(4);
		auftragliste1 = new OneToManyReturnE<Order>(new Factory<Order>(){
			@Override
			public Order create(Order elem) {
				return new Order(elem.number);
			}});
	}

	@Test
	public void testAdd() {
		assertEquals(auf2, auftragliste1.add(auf2));
		// Zweimal Auftr�ge mit der selben Auftragsnummer werden nicht
		// hinzugef�gt
		assertNull(auftragliste1.add(auf3));
		// Wir versuchen zwei identische Auftr�ge hinzuzuf�gen
		assertNull(auftragliste1.add(auf2));
		assertNull(auftragliste1.add(null));
	}

	@Test
	public void testRemove() {
		auftragliste1.add(auf2);
		assertEquals(auf2, auftragliste1.remove(auf2));
		// Auftrag der nicht vorhanden ist kann nicht gel�scht werden
		assertNull(auftragliste1.remove(auf1));
		assertNull(auftragliste1.remove(null));
	}
	
	
	@Test
	public void testContains() {
		auftragliste1.add(auf2);
		auftragliste1.add(auf4);
		assertFalse(auftragliste1.contains(auf1));
		assertTrue(auftragliste1.contains(auf2));
		assertFalse(auftragliste1.contains(auf5));
		assertTrue(auftragliste1.contains(auf4));
		
	}

	@Test
	public void testClear() {
		auftragliste1.add(auf2);
		auftragliste1.add(auf4);
		auftragliste1.clear();
		assertEquals(0,auftragliste1.size());
	}

	@Test
	public void testSize() {
		auftragliste1.add(auf2);
		auftragliste1.add(auf4);
		assertEquals(2,auftragliste1.size());
		auftragliste1.remove(auf4);
		assertEquals(1,auftragliste1.size());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(auftragliste1.isEmpty());
		auftragliste1.add(auf2);
		assertFalse(auftragliste1.isEmpty());
	}
	
	@Test
	public void testIteratorHasNext() {
		// nach jeder Ver�nderung der List holen wir einen neuen Iterator
		// FailFast Verhalten wird an anderer Stelle getestet
		Iterator<Order> iterator = auftragliste1.iterator();
		// Test auf doppelten hasNext Aufruf
		assertFalse(iterator.hasNext());
		assertFalse(iterator.hasNext());

		auftragliste1.add(auf4);
		auftragliste1.add(auf2);
		// Neuer Iterator auf geaenderter Liste
		iterator = auftragliste1.iterator();
		assertTrue(iterator.hasNext());
		iterator.next();
		assertTrue(iterator.hasNext());
		iterator.next();
		assertFalse(iterator.hasNext());

	}

	@Test(expected = NoSuchElementException.class)
	public void testIteratorNext() {
		// nach jeder Ver�nderung der List holen wir einen neuen Iterator
		// FailFast Verhalten wird an anderer Stelle getestet
		Iterator<Order> iterator = auftragliste1.iterator();
		// Test Next auf leere Liste
		iterator.next();

		auftragliste1.add(auf4);
		auftragliste1.add(auf2);
		auftragliste1.add(auf1);
		List<Order> list = new LinkedList<>();
		list.add(auf4);
		list.add(auf2);
		list.add(auf1);
		iterator = auftragliste1.iterator();
		// Wir verwenden eine Hilfsliste mit den Auftr�gen die von
		// iterator.next() zur�ckgeliefert werden d�rfen
		// da die Reihenfolge nicht garantiert ist. Nach dem ein Element
		// vorgekommen ist, wird es aus der
		// Hilfsliste entfernt um sicher zu gehen das .next() nicht mehrmals das
		// selbe Element zur�ckgibt.
		for (int i = 0; i < 3; i++) {
			// Es wird geprueft, ob mit Next das naechste Element erreicht wird
			Order temp = iterator.next();
			assertTrue(list.contains(temp));
			list.remove(temp);
		}
	}

	@Test(expected = IllegalStateException.class)
	public void testIteratorRemove() {
		Iterator<Order> iterator = auftragliste1.iterator();
		iterator.remove(); // IllegalStateException erwartet, bisher kein Aufruf
							// von next()

		auftragliste1.add(auf4);
		auftragliste1.add(auf2);
		auftragliste1.add(auf1);
		iterator = auftragliste1.iterator();
		for (int i = 0; i < 3; i++) {
			Order temp = iterator.next();
			iterator.remove();
			// Nicht vorhandenes Element kann nicht geloescht werden
			assertFalse(auftragliste1.contains(temp));
		}
		iterator.remove(); // IllegalStateException erwartet, Aufruf von
							// remove() hintereinander ohne next() dazwischen
	}

	@Test(expected = ConcurrentModificationException.class)
	public void testIteratorFailFastNext() {
		Iterator<Order> iterator = auftragliste1.iterator();
		auftragliste1.add(auf4);
		iterator.next();		
	}
	
	@Test(expected = ConcurrentModificationException.class)
	public void testIteratorFailFastRemove() {
		auftragliste1.add(auf2);
		Iterator<Order> iterator = auftragliste1.iterator();
		iterator.next();	
		auftragliste1.add(auf4);
		iterator.remove();
		
	}
	
	@Test
	public void testDefensiveCopy() {
		
		// Add Test
		
		auftragliste1.add(auf2);
		auf2.number = 100;
		Iterator<Order> iterator = auftragliste1.iterator();
		Order temp = iterator.next();
		assertFalse(temp.equals(auf2));
		
		// Next Test
		
		temp.number = 200;
		iterator = auftragliste1.iterator();
		assertFalse(temp.equals(iterator.next()));
		
	}
}