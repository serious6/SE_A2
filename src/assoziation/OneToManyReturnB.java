package assoziation;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OneToManyReturnB<E> extends OneToManyList<E> implements IOneToManyReturnB<E> {
	

	OneToManyReturnB(){	
	}
	
	OneToManyReturnB(OneToManyVoid<E> list) {
		for (E iterable_element : list) {
			add(iterable_element);
		}
	}
	
	
	/**

	 * Fügt ein Element hinzu, falls dieses noch nicht enthalten ist.
	 * 
	 * @param elem Das hinzuzufügende Element.
	 * @return True wenn Löschen erfolgreich, false bei nicht erfolg.
	 */
	@Override
	public boolean add(E elem) {
		if(this.contains(elem)) return false;   // O(2n) ??? sinnvoll
		
		if(this.isEmpty()) {
			head = new Node<E>(elem);
			size++;
			return true;
		}
		else {
			Node<E> temp = head;
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(new Node<E>(elem));
			size++;
			return true;
		}

	}
	
	
	
	/**
	 * Entfernt das Element, falls es enthalten ist.
	 * 
	 * @param elem
	 *            Das zu löschende Element.
	 * @return True wenn Löschen erfolgreich, false bei nicht erfolg.
	 */
	@Override
	public boolean remove(E elem) {
		if(isEmpty()) return false;
		
		if(head.getElem().equals(elem)) {
			if(head.getNext() == null) {
				head = null;
				size = 0;
				return true;
			}
			else {
				head = head.getNext();
				size--;
				return true;
			}
		}
		else {
			Node<E> temp = head;
			Node<E> tempPrev = null;
			
			while(temp.getNext() != null) {
				tempPrev = temp;
				temp = temp.getNext();
				if(temp.getElem().equals(elem)) {
					tempPrev.setNext(temp.getNext());
					size--;
					return true;
				}
			}
		}
		
		return false;
	}
	
	@Override
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
            OneToManyReturnB.this.remove(prev.getElem());
            prev = cur;
            cur = cur.getNext();
        }
    }

	
}
