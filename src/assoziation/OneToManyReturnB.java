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

	 * F�gt ein Element hinzu, falls dieses noch nicht enthalten ist.
	 * 
	 * @param elem Das hinzuzuf�gende Element.
	 * @return True wenn L�schen erfolgreich, false bei nicht erfolg.
	 */
	@Override
	 public boolean add(E elem) {
	 if(elem == null) return false;
	  if(this.contains(elem)) return false;

	  else {
	   Node<E> temp = head;
	head = new Node<E>(elem);
	   head.setNext(temp);
	   size++;
	   return true;
	  }
	}
	
	
	
	/**
	 * Entfernt das Element, falls es enthalten ist.
	 * 
	 * @param elem
	 *            Das zu l�schende Element.
	 * @return True wenn L�schen erfolgreich, false bei nicht erfolg.
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
        	// todo: hasNext() check hier n�tig?
            OneToManyReturnB.this.remove(prev.getElem());
            prev = cur;
            cur = cur.getNext();
        }
    }

	
}
