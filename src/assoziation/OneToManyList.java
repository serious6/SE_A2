package assoziation;

public abstract class OneToManyList<E>{
	protected Node<E> head = null;
	protected int size = 0;
	
	/**
	 * Prüft ob das Element enthalten ist.
	 * 
	 * @param elem
	 *            Das zu prüfende Element.
	 * @return true wenn das Element enthalten ist, false sonst.
	 */
	public boolean contains(E elem) {
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
	 * Entfernt alle Elemente.
	 */
	public void clear() {
		head = null;
		size = 0;
	}
	
	/**
	 * Prüft ob Elemente enthalten sind.
	 * 
	 * @return true falls keine Elemente enthalten sind, false sonst.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Gibt die Anzahl der enthaltenen Elemente zurück.
	 * 
	 * @return Die Anzahl der enthaltenen Elemente.
	 */
	public int size() {
		return size;
	}

	/**
	 * Gibt eine Umschreibung des Objekts als String zurück
	 */
	public String toString() {
		Node<E> temp = head;
		StringBuffer sb = new StringBuffer();
		while (temp.getNext() != null) {
			sb.append(temp.getElem());
			temp = temp.getNext();
		}
		sb.append(temp.getElem());
		return sb.toString();
	}


	public static class Node<E> {
		private E elem = null;
		private Node<E> next = null;

		public Node(E elem) {
			this.elem = elem;
		}

		public E getElem() {
			return elem;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> node) {
			this.next = node;
		}
	}

}
