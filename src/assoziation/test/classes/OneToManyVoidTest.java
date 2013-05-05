package assoziation.test.classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import assoziation.Factory;
import assoziation.exception.ElementNotFoundException;
import assoziation.exception.ListAddedException;
import assoziation.impl.OneToManyVoid;
import assoziation.impl.Order;

public class OneToManyVoidTest {
	Order auf1;
	Order auf2;
	Order auf3;
	Order auf4;
	Order auf5;
	OneToManyVoid<Order> auftragliste1;

	@Before
	public void fixture() {
		auf1 = new Order(1);
		auf2 = new Order(2);
		auf3 = new Order(2);
		auf4 = new Order(3);
		auf5 = new Order(4);
		auftragliste1 = new OneToManyVoid<Order>(new Factory<Order>() {
			@Override
			public Order create(Order elem) {
				return new Order(elem.number);
			}
		});

	}

	@Test(expected = ListAddedException.class)
	public void testAdd() throws ListAddedException {
		auftragliste1.add(auf2);
		auftragliste1.add(auf3);
	}

	public void testAdd2() throws ListAddedException {
		auftragliste1.add(auf1);
		auftragliste1.add(auf2);
		assertEquals(2, auftragliste1.size());
	}

	@Test(expected = ListAddedException.class)
	public void testAdd3() throws ListAddedException {
		auftragliste1.add(null);
	}

	public void testAddAndRemove() throws ListAddedException,
			ElementNotFoundException {
		auftragliste1.add(auf1);
		auftragliste1.add(auf2);

		auftragliste1.remove(auf2);
		auftragliste1.remove(auf1);

		auftragliste1.add(auf1);
		auftragliste1.add(auf2);

		auftragliste1.remove(auf1);

		auftragliste1.add(auf5);

		assertEquals(2, auftragliste1.size());
	}

	@Test
	public void testRemove() throws ElementNotFoundException {
		try {
			auftragliste1.add(auf2);
			auftragliste1.remove(auf2);

			assertEquals(0, auftragliste1.size());
		} catch (ListAddedException e) {
			fail(e.getMessage());
		}
	}

	@Test(expected = ElementNotFoundException.class)
	public void testRemove2() throws ElementNotFoundException {
		try {
			auftragliste1.add(auf1);
			auftragliste1.remove(auf2);
		} catch (ListAddedException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testContains() throws ListAddedException {
		auftragliste1.add(auf2);
		auftragliste1.add(auf4);
		// Pruefung, ob das Element enthalten ist
		assertFalse(auftragliste1.contains(auf1));
		assertTrue(auftragliste1.contains(auf2));
		assertFalse(auftragliste1.contains(auf5));
		assertTrue(auftragliste1.contains(auf4));
		// Pruefung, ob Null enthalten ist
		assertFalse(auftragliste1.contains(null));
	}

	@Test
	public void testClear() throws ListAddedException {
		auftragliste1.add(auf2);
		auftragliste1.add(auf4);
		auftragliste1.clear();
		// Pruefung ob die Liste leer ist
		assertTrue(auftragliste1.size() == 0);
	}

	@Test
	public void testSize() throws ElementNotFoundException, ListAddedException {
		// Pruefung ob leere Liste gleich Null ist
		assertTrue(auftragliste1.size() == 0);
		auftragliste1.add(auf2);
		auftragliste1.add(auf4);
		// Pruefung, ob die Groesse der Liste gleich der Anzahl der
		// hinzugefuegten Elemente ist
		assertTrue(auftragliste1.size() == 2);
		auftragliste1.remove(auf4);
		assertTrue(auftragliste1.size() == 1);
		auftragliste1.remove(auf2);
		assertTrue(auftragliste1.size() == 0);
	}

	@Test
	public void testIsEmpty() throws ListAddedException,
			ElementNotFoundException {
		assertTrue(auftragliste1.isEmpty());
		auftragliste1.add(auf2);
		assertFalse(auftragliste1.isEmpty());
		auftragliste1.remove(auf2);
		assertTrue(auftragliste1.isEmpty());
	}

	@Test
	public void testIteratorHasNext() throws ListAddedException {
		// nach jeder Veraenderung der List holen wir einen neuen Iterator
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
	public void testIteratorNext() throws ListAddedException {
		// nach jeder Veraenderung der List holen wir einen neuen Iterator
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
		// Wir verwenden eine Hilfsliste mit den Auftraegen die von
		// iterator.next() zurueckgeliefert werden duerfen
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
	public void testIteratorRemove() throws ListAddedException {
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
	public void testIteratorFailFastNext() throws ListAddedException {
		Iterator<Order> iterator = auftragliste1.iterator();
		auftragliste1.add(auf4);
		iterator.next();
	}

	@Test(expected = ConcurrentModificationException.class)
	public void testIteratorFailFastRemove() throws ListAddedException {
		auftragliste1.add(auf2);
		Iterator<Order> iterator = auftragliste1.iterator();
		iterator.next();
		auftragliste1.add(auf4);
		iterator.remove();

	}

	@Test
	public void testDefensiveCopy() throws ListAddedException {

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