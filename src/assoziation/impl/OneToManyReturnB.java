package assoziation.impl;

import java.util.Collection;

import assoziation.Factory;
import assoziation.IOneToManyReturnB;

public class OneToManyReturnB<E> extends OneToManyAbstract<E> implements IOneToManyReturnB<E> {
	
	public OneToManyReturnB(Factory<E> factory) {
    	this.factory = factory;
    }

	public OneToManyReturnB(Factory<E> factory, Collection<E> coll) {
		this.factory = factory;
		for(E elem : coll) {
			add(elem);
		}
    }
	
	
	/**
	 * F�gt ein Element hinzu, falls dieses noch nicht enthalten ist.
	 * 
	 * @param elem Das hinzuzuf�gende Element.
	 * @return True wenn L�schen erfolgreich, false bei nicht erfolg.
	 */
	@Override
	public boolean add(E elem) {
		return super.addElem(elem) != null;
	}
	
	
	
	/**
	 * Entfernt das Element, falls es enthalten ist.
	 * 
	 * @param elem
	 *            Das zu l�schende Element.
	 * @return True wenn L�schen erfolgreich, false bei nicht erfolg.
	 */
	@Override
	public boolean remove(E elem) {
		return super.removeElem(elem) != null;
	}
		
}
