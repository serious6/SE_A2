package assoziation.impl;

import java.util.Collection;

import assoziation.Factory;
import assoziation.IOneToManyReturnE;

/**
 * 
 * Eine Liste die keine doppelten Elemente zulässt und die Objekte in keiner
 * geordneten Reihenfolge speichert. Die Liste lässt auch keine doppelten
 * Elemente zu. Die Methoden add und remove liefern das übergebene Element
 * zurück bei Erfolg und null bei nicht Erfolg.
 * 
 */
public class OneToManyReturnE<E> extends OneToManyAbstract<E> implements
		IOneToManyReturnE<E> {

	/**
	 * Construct OneToManyReturnE
	 * 
	 * @param factory
	 */
	public OneToManyReturnE(Factory<E> factory) {
		this.factory = factory;
	}

	/**
	 * Construct OneToManyReturnE
	 * 
	 * @param factory
	 * @param coll
	 */
	public OneToManyReturnE(Factory<E> factory, Collection<E> coll) {
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
	 * 
	 * @return Das hinzugefügte Element oder null, falls bereits vorhanden.
	 */
	@Override
	public E add(E elem) {
		return super.addElem(elem);
	}

	/**
	 * Entfernt das Element, falls es enthalten ist.
	 * 
	 * @param elem
	 *            : Das zu löschende Element.
	 * 
	 * @return Das gelöschte Element oder null, falls das Element nicht
	 *         enthalten war.
	 */
	@Override
	public E remove(E elem) {
		return super.removeElem(elem);
	}

}
