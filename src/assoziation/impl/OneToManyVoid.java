package assoziation;

import java.util.Iterator;

import assoziation.exception.ElementNotFoundException;
import assoziation.exception.EndOfListException;
import assoziation.exception.ListAddedException;
import assoziation.exception.ListAddedException.ExceptionType;

public class OneToManyVoid<E> extends OneToManyList<E> implements
		IOneToManyVoid<E> {

	OneToManyVoid() {

	}

	OneToManyVoid(OneToManyVoid<E> list) throws ListAddedException {
		for (E iterable_element : list) {
			add(iterable_element);
		}
	}

	/**
	 * F�gt ein Element hinzu, falls dieses noch nicht enthalten ist.
	 * 
	 * @param elem
	 *            Das hinzuzuf�gende Element.
	 * @return void.
	 * @throws ListAddedException
	 */
	@Override
	public void add(E elem) throws ListAddedException {
		if (elem == null)
			throw new ListAddedException(ExceptionType.ELEMENT_NULL);
		if (this.contains(elem))
			throw new ListAddedException(ExceptionType.ELEMENT_EXISTS);
		else {
			Node<E> temp = head;
			head = new Node<E>(elem);
			head.setNext(temp);
			size++;
		}
	}

	/**
	 * Entfernt das Element, falls es enthalten ist.
	 * 
	 * @param elem
	 *            Das zu l�schende Element.
	 * @return void.
	 * @throws ElementNotFoundException
	 */
	@Override
	public void remove(E elem) throws ElementNotFoundException {
		Node<E> curr = head;
		Node<E> prev = head;
		boolean fertig = false;

		if (head.getElem().equals(elem)) {
			if (head.getNext() == null) {
				head = null;
				size = 0;
				fertig = true;
			} else {
				head = head.getNext();
				size--;
				fertig = true;
			}
		}

		while (curr != null && !fertig) {
			if (curr.getElem().equals(elem)) {
				prev.setNext(curr.getNext());
				size--;
				fertig = true;
			}
			prev = curr;
			curr = curr.getNext();
		}
		if (!fertig) {
			throw new ElementNotFoundException();
		}
	}

	public Iterator<E> iterator() {
		return new MyIterator();
	}

	private class MyIterator implements Iterator<E> {
		// todo:
		// The iterators returned by this class's iterator method are fail-fast:
		// if the set is modified at any time after the iterator is created,
		// in any way except through the iterator's own remove method, the
		// Iterator throws a ConcurrentModificationException.
		// Thus, in the face of concurrent modification, the iterator fails
		// quickly and cleanly, rather than risking arbitrary,
		// non-deterministic behavior at an undetermined time in the future.
		private Node<E> cur = head;
		private Node<E> prev = head;

		@Override
		public boolean hasNext() {
			return cur != null;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				return null;
			}
			prev = cur;
			cur = cur.getNext();
			return prev.getElem();
		}

		@Override
		public void remove() {
			// todo: hasNext() check hier n�tig?
			try {
				OneToManyVoid.this.remove(prev.getElem());
				prev = cur;
				cur = cur.getNext();
			} catch (ElementNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
