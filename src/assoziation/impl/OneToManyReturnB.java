package assoziation.impl;

import java.util.Collection;

import assoziation.Factory;
import assoziation.IOneToManyReturnB;

/**
 * 
 * Eine Liste die keine doppelten Elemente zulässt und die Objekte in keiner
 * geordneten Reihenfolge speichert. Die Liste lässt auch keine doppelten
 * Elemente zu. Die Methoden add und remove liefern einen boolean zurück: true
 * bei Erfolg und false bei nicht Erfolg.
 * 
 */
public class OneToManyReturnB<E> extends OneToManyAbstract<E> implements
		IOneToManyReturnB<E> {

	/**
	 * Construct OneToManyReturnB
	 * 
	 * @param factory
	 */
	public OneToManyReturnB(Factory<E> factory) {
		this.factory = factory;
	}

	/**
	 * Construct OneToManyReturnB
	 * 
	 * @param factory
	 * @param coll
	 */
	public OneToManyReturnB(Factory<E> factory, Collection<E> coll) {
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
	 * @return True wenn Hinzufügen erfolgreich, false bei nicht erfolg.
	 */
	@Override
	public boolean add(E elem) {
		return super.addElem(elem) != null;
	}

	/**
	 * Entfernt das Element, falls dieses enthalten ist.
	 * 
	 * @param elem
	 *            : Das zu löschende Element.
	 * 
	 * @return True wenn Löschen erfolgreich, false bei nicht erfolg.
	 */
	@Override
	public boolean remove(E elem) {
		return super.removeElem(elem) != null;
	}

}
