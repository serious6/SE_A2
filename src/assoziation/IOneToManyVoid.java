package assoziation;

import assoziation.exception.ElementNotFoundException;
import assoziation.exception.ListAddedException;

public interface IOneToManyVoid<E> extends Iterable<E> {

	/**
	 * F�gt ein Element hinzu, falls dieses noch nicht enthalten ist.
	 * 
	 * @param elem
	 *            Das hinzuzuf�gende Element.
	 * @return void.
	 */
	void add(E elem) throws ListAddedException;

	/**
	 * Entfernt das Element, falls es enthalten ist.
	 * 
	 * @param elem
	 *            Das zu l�schende Element.
	 * @return void.
	 */
	void remove(E elem) throws ElementNotFoundException;

	/**
	 * Pr�ft ob Elemente enthalten sind.
	 * 
	 * @return true falls keine Elemente enthalten sind, false sonst.
	 */
	boolean isEmpty();

	/**
	 * Pr�ft ob das Element enthalten ist.
	 * 
	 * @param elem
	 *            Das zu pr�fende Element.
	 * @return true wenn das Element enthalten ist, false sonst.
	 */
	boolean contains(E elem);

	/**
	 * Gibt die Anzahl der enthaltenen Elemente zur�ck.
	 * 
	 * @return Die Anzahl der enthaltenen Elemente.
	 */
	int size();

	/**
	 * Entfernt alle Elemente.
	 */
	void clear();
}
