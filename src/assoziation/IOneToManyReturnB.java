package assoziation;

public interface IOneToManyReturnB<E> extends Iterable<E> {

	/**
	 * Fügt ein Element hinzu, falls dieses noch nicht enthalten ist.
	 * 
	 * @param elem
	 *            : Das hinzuzufügende Element.
	 * 
	 * @return True wenn Hinzufügen erfolgreich, false bei nicht erfolg.
	 */
	public boolean add(E elem);

	/**
	 * Entfernt das Element, falls dieses enthalten ist.
	 * 
	 * @param elem
	 *            : Das zu löschende Element.
	 * 
	 * @return True wenn Löschen erfolgreich, false bei nicht erfolg.
	 */
	public boolean remove(E elem);

	/**
	 * Prüft ob Elemente enthalten sind.
	 * 
	 * @return true falls keine Elemente enthalten sind, false sonst.
	 */
	public boolean isEmpty();

	/**
	 * Prüft ob das Element enthalten ist.
	 * 
	 * @param elem
	 *            : Das zu prüfende Element.
	 * 
	 * @return true wenn das Element enthalten ist, false sonst.
	 */
	public boolean contains(E elem);

	/**
	 * Gibt die Anzahl der enthaltenen Elemente zurück.
	 * 
	 * @return Die Anzahl der enthaltenen Elemente.
	 */
	public int size();

	/**
	 * Entfernt alle Elemente.
	 */
	public void clear();

}
