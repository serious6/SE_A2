package assoziation.impl;

import java.util.Collection;

import assoziation.Factory;
import assoziation.IOneToManyVoid;
import assoziation.exception.ElementNotFoundException;
import assoziation.exception.ListAddedException;
import assoziation.exception.ListAddedException.ExceptionType;

/**
 * 
 * Eine Liste die keine doppelten Elemente zulässt und die Objekte in keiner
 * geordneten Reihenfolge speichert. Die Liste lässt auch keine doppelten
 * Elemente zu. Bei nicht Erfolg der add und remove Methoden wird eine Exception
 * geworfen.
 * 
 */
public class OneToManyVoid<E> extends OneToManyAbstract<E> implements
		IOneToManyVoid<E> {

	/**
	 * Construct OneToManyVoid
	 * 
	 * @param factory
	 */
	public OneToManyVoid(Factory<E> factory) {
		this.factory = factory;
	}

	/**
	 * Construct OneToManyVoid
	 * 
	 * @param factory
	 * @param coll
	 * @throws ListAddedException
	 */
	public OneToManyVoid(Factory<E> factory, Collection<E> coll)
			throws ListAddedException {
		this.factory = factory;
		for (E elem : coll) {
			add(elem);
		}
	}

	/**
	 * Fügt ein Element hinzu, falls dieses noch nicht enthalten ist.
	 * 
	 * @param elem
	 *            : Das hinzuzufügende Element.
	 * @throws ListAddedException
	 * 
	 * @return void.
	 */
	@Override
	public void add(E elem) throws ListAddedException {
		if (elem == null)
			throw new ListAddedException(ExceptionType.ELEMENT_NULL);
		if (this.contains(elem))
			throw new ListAddedException(ExceptionType.ELEMENT_EXISTS);
		else {
			super.addElem(elem);
		}
	}

	/**
	 * Entfernt das Element, falls es enthalten ist.
	 * 
	 * @param elem
	 *            : das zu löschende Element
	 * @throws ElementNotFoundException
	 * 
	 * @return void.
	 */
	@Override
	public void remove(E elem) throws ElementNotFoundException {
		// elem == null Test? throw new
		// ListAddedException(ExceptionType.ELEMENT_NULL);
		if (super.removeElem(elem) == null) {
			throw new ElementNotFoundException();
		}
	}

}