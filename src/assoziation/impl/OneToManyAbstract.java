package assoziation.impl;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import assoziation.Factory;

public abstract class OneToManyAbstract<E>{
	Node<E> head = null;
	int size = 0;
	int modCount = 0;
	Factory<E> factory = null;
	
	/**
	 * Pr�ft ob das Element enthalten ist.
	 * 
	 * @param elem
	 *            Das zu pr�fende Element.
	 * @return true wenn das Element enthalten ist, false sonst.
	 */
	public boolean contains(E elem) {
		if(elem == null) return false; // null-Element darf nicht enthalten sein
		
		Node<E> temp = head;
		while (temp != null) {
			if (temp.getElem().equals(elem)) {
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}
	
	/**
     * F�gt ein Element hinzu, falls dieses noch nicht enthalten ist.
     * 
     * @param elem Das hinzuzuf�gende Element.
     * @return Das hinzugef�gte Element oder null, falls bereits vorhanden.
     */
	public E addElem(E elem) {
		modCount++; // am Anfang? auch falls add nicht erfolgreich?
		
		if (elem == null) {
            return null;
        }
		
		if (contains(elem)) {
			return null;
		}
			
       	// vorne Einf�gen
       	head = new Node<E>(factory.create(elem), head);
       	size++;
       	return elem;
	}
	
	 /**
     * Entfernt das Element, falls es enthalten ist.
     * 
     * @param elem Das zu l�schende Element.
     * @return Das gel�schte Element oder null, falls es nicht enthalten war.
     */
    public E removeElem(E elem) {
    	modCount++; // am Anfang?
    	
        if (isEmpty()) {
            return null;
        }
        
        if (elem == null) {
            return null;
        }
        
        if (head.getElem().equals(elem)) {
        	head = head.getNext();
            size--;
            return elem;
        } else {
        	// todo: hier mit angepasstem contains() arbeiten?
        	Node<E> temp = head;
            Node<E> tempPrev = null;

            while (temp.getNext() != null) {
                tempPrev = temp;
                temp = temp.getNext();
                if (temp.getElem().equals(elem)) {
                    tempPrev.setNext(temp.getNext());
                    size--;
                    return elem;
                }
            }
        }
        return null;
    }
	
	/**
	 * Entfernt alle Elemente.
	 */
	public void clear() {
		modCount++;
		head = null;
		size = 0;
	}
	
	/**
	 * Pr�ft ob Elemente enthalten sind.
	 * 
	 * @return true falls keine Elemente enthalten sind, false sonst.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Gibt die Anzahl der enthaltenen Elemente zur�ck.
	 * 
	 * @return Die Anzahl der enthaltenen Elemente.
	 */
	public int size() {
		return size;
	}

	/**
     * Gibt eine Stringrepr�sentation dieses Objekts zur�ck.
     * @return Eine Stringrepr�sentation dieses Objekts.
     */
    @Override
    public String toString() {
        Iterator<E> it = iterator();
        if (!it.hasNext()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (!it.hasNext()) {
                return sb.append(']').toString();
            }
            sb.append(',').append(' ');
        }
    }

	private static class Node<E> {	
		private E elem = null;
		private Node<E> next = null;

		public Node(E elem) {
			this(elem, null);
		}
		
		public Node(E elem, Node<E> next) {
			this.elem = elem;
			this.next = next;
		}

		public E getElem() {
			return elem;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}
	}

	public Iterator<E> iterator() {
		return new MyIterator();
	}
	
    private class MyIterator implements Iterator<E> {
        private Node<E> cur = head;
        private Node<E> prev = head;
        private Node<E> lastReturned = null;
        private int expectedModCount = modCount;
        
        private void checkForComodification() {
        	if (modCount != expectedModCount) throw new ConcurrentModificationException();
        }

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public E next() {
        	checkForComodification();
        	
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = prev;
            prev = cur;
            cur = cur.getNext();
            return factory.create(prev.getElem());
        }

        @Override
        public void remove() {
        	checkForComodification();
        	if (lastReturned == null)
        		throw new IllegalStateException();
            OneToManyAbstract.this.removeElem(prev.getElem());
            expectedModCount++;
            lastReturned = null;
            prev = cur;
            cur = cur.getNext();
        }
    }
}
