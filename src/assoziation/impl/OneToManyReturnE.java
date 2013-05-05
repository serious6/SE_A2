package assoziation;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class OneToManyReturnE<E> extends OneToManyList<E> implements IOneToManyReturnE<E> {

    public OneToManyReturnE() {
    }

    public OneToManyReturnE(Node<E> elem) {
        head = elem;
        size++;
    }

    // CopyConstructor
    // f�r ReturnVoid
//    public OneToManyReturnE(IOneToManyReturnVoid<E> aList) {
//    }
    // f�r ReturnBoolean
//    public OneToManyReturnE(IOneToManyReturnBoolean<E> aList) {
//    }
    
    // f�r Collection
	public OneToManyReturnE(Collection<E> coll) {  // Collection<? extends E> coll ?
        addAll(coll);
    }
    
	/**
	 * F�gt den Inhalt einer Collection dieser Liste hinzu.
	 * Ohne Duplikate und null-Elemente.
	 * 
	 * @param coll Die Collection deren Elemente hinzugef�gt werden.
	 */
    private void addAll(Collection<E> coll) {  // Collection<? extends E> coll ?
    	if (!coll.isEmpty()) {
    		Set<E> tempSet = new HashSet<E>(coll);  // Set aus der Collection erstellen um Duplikate zu entfernen
    		Iterator<E> iter = tempSet.iterator();
            Node<E> temp = null;

            while (head == null) {	// falls es keine head-Node gibt, diese zuerst anlegen. Null Elemente �berspringen.
                if (iter.hasNext()) {
                    temp = new Node<>(iter.next());
                    if (temp.getElem() != null) {
                        head = temp;
                        size++;
                    }
                }
            }
            
            // falls es eine head-Node gab: temp auf die letzte Node in der Liste setzen. 
            if(temp == null) {
            	temp = head;
            	while(temp.getNext() != null) {
            		temp = temp.getNext();
            	}
            }

            // Alle Elemente hinzuf�gen, au�er null
            while (iter.hasNext()) {
                Node<E> newNode = new Node<>(iter.next());
                if (newNode.getElem() != null) {
                    temp.setNext(newNode);
                    temp = newNode;
                    size++;
                }
            }
        }
    }

    /**
     * F�gt ein Element hinzu, falls dieses noch nicht enthalten ist.
     * 
     * @param elem Das hinzuzuf�gende Element.
     * @return Das hinzugef�gte Element oder null, falls bereits vorhanden.
     */
    @Override
    public E add(E elem) {
     if(elem == null) return null;
     if(this.contains(elem)) return null;

     else {
      Node<E> temp = head;
   head = new Node<E>(elem);
      head.setNext(temp);
      size++;
      return elem;
     }
    }

    /**
     * Entfernt das Element, falls es enthalten ist.
     * 
     * @param elem Das zu l�schende Element.
     * @return Das gel�schte Element oder null, falls es nicht enthalten war.
     */
    @Override
    public E remove(E elem) {
        if (isEmpty()) {
            return null;
        }

        if (head.getElem().equals(elem)) {
            if (head.getNext() == null) {
                head = null;
                size = 0;
                return elem;
            } else {
                head = head.getNext();
                size--;
                return elem;
            }
        } else {
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
            OneToManyReturnE.this.remove(prev.getElem());
            prev = cur;
            cur = cur.getNext();
        }
    }

}
