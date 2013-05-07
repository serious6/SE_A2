package assoziation.impl;

import java.util.Collection;

import assoziation.Factory;
import assoziation.IOneToManyVoid;
import assoziation.exception.ElementNotFoundException;
import assoziation.exception.ListAddedException;

/**
 * 
 * Eine Liste die keine doppelten Elemente zulässt und die Objekte in keiner
 * geordneten Reihenfolge speichert. Die Liste lässt auch keine doppelten
 * Elemente zu. Bei nicht Erfolg der add und remove Methoden wird eine Exception
 * geworfen.
 * 
 */
public class OneToManyVoidOrig<E> extends OneToManyAbstract<E> implements
		IOneToManyVoid<E> {

	/**
	 * Construct OneToManyVoidOrig
	 * 
	 * @param factory
	 */
	public OneToManyVoidOrig(Factory<E> factory) {
		this.factory = factory;
	}

	/**
	 * Contruct OneToManyVoidOrig
	 * 
	 * @param factory
	 * @param coll
	 */
	public OneToManyVoidOrig(Factory<E> factory, Collection<E> coll) {
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
	 * @throws RuntimeException
	 * 
	 * @return void.
	 */
	@Override
	public void add(E elem) {
		if (super.addElem(elem) == null) {
			throw new RuntimeException("Element bereits enthalten");
		}
	}

	/**
	 * Entfernt das Element, falls es enthalten ist.
	 * 
	 * @param elem
	 *            : das zu löschende Element
	 * 
	 * @return void.
	 */
	@Override
	public void remove(E elem) {
		super.removeElem(elem);
	}

}
