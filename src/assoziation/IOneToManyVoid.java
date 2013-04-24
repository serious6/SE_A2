package assoziation;

public interface IOneToManyVoid<E> extends Iterable<E> {
	
	/**
	 * Fügt ein Element hinzu, falls dieses noch nicht enthalten ist.
	 * 
	 * @param elem
	 *            Das hinzuzufügende Element.
	 * @return void.
	 */
	void add(E elem);
	
	/**
	 * Entfernt das Element, falls es enthalten ist.
	 * 
	 * @param elem
	 *            Das zu löschende Element.
	 * @return void.
	 */
	void remove(E elem);

	/**
	 * Prüft ob Elemente enthalten sind.
	 * 
	 * @return true falls keine Elemente enthalten sind, false sonst.
	 */
	boolean isEmpty();

	/**
	 * Prüft ob das Element enthalten ist.
	 * 
	 * @param elem
	 *            Das zu prüfende Element.
	 * @return true wenn das Element enthalten ist, false sonst.
	 */
	boolean contains(E elem);

	/**
	 * Gibt die Anzahl der enthaltenen Elemente zurück.
	 * 
	 * @return Die Anzahl der enthaltenen Elemente.
	 */
	int size();

	/**
	 * Entfernt alle Elemente.
	 */
	void clear();
}
