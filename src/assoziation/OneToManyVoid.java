package assoziation;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OneToManyVoid<E> extends OneToManyList<E> implements IOneToManyVoid<E> {


	OneToManyVoid(){
		
	}
	
	OneToManyVoid(OneToManyVoid<E> list) {
		for (E iterable_element : list) {
			add(iterable_element);
		}
	}

	/**
	 * Fügt ein Element hinzu, falls dieses noch nicht enthalten ist.
	 * 
	 * @param elem
	 *            Das hinzuzufügende Element.
	 * @return void.
	 */
	@Override
	public void add(E elem) {
		if (contains(elem)) {
			throw new RuntimeException("Element bereits enthalten");
		}
		if (head == null) {
			head = new Node<E>(elem);
		} else {
			Node<E> temp = head;
			boolean fertig = false;
			while (temp != null && !fertig) {
				if (temp.getNext() != null) {
					temp = temp.getNext();
				} else {
					temp.setNext(new Node<E>(elem));
					fertig = true;
				}
			}
		}
		size++;
	}

	/**
	 * Entfernt das Element, falls es enthalten ist.
	 * 
	 * @param elem
	 *            Das zu löschende Element.
	 * @return void.
	 */
	@Override
	public void remove(E elem) {
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
	}

	public Iterator<E> iterator() {
		return new MyIterator();
	}

    private class MyIterator implements Iterator<E> {
    	// todo:
    	// The iterators returned by this class's iterator method are fail-fast: if the set is modified at any time after the iterator is created,
    	// in any way except through the iterator's own remove method, the Iterator throws a ConcurrentModificationException.
    	// Thus, in the face of concurrent modification, the iterator fails quickly and cleanly, rather than risking arbitrary,
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
                throw new NoSuchElementException();
            }
            prev = cur;
            cur = cur.getNext();
            return prev.getElem();
        }

        @Override
        public void remove() {
        	// todo: hasNext() check hier nötig?
            OneToManyVoid.this.remove(prev.getElem());
            prev = cur;
            cur = cur.getNext();
        }
    }

}
