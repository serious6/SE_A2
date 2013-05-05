package assoziation.impl;

import java.util.Collection;

import assoziation.Factory;
import assoziation.IOneToManyVoid;
import assoziation.exception.ElementNotFoundException;
import assoziation.exception.ListAddedException;
import assoziation.exception.ListAddedException.ExceptionType;

public class OneToManyVoid<E> extends OneToManyAbstract<E> implements
		IOneToManyVoid<E> {

	public OneToManyVoid(Factory<E> factory) {
    	this.factory = factory;
    }

	public OneToManyVoid(Factory<E> factory, Collection<E> coll) throws ListAddedException { 
		this.factory = factory;
		for(E elem : coll) {
			add(elem);
		}
    }


	/**
	 * F�gt ein Element hinzu, falls dieses noch nicht enthalten ist.
	 * 
	 * @param elem
	 *            Das hinzuzuf�gende Element.
	 * @return void.
	 * @throws ListAddedException
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
	 *            Das zu l�schende Element.
	 * @return void.
	 * @throws ElementNotFoundException
	 */
	@Override
	public void remove(E elem) throws ElementNotFoundException {
	// elem == null Test? throw new ListAddedException(ExceptionType.ELEMENT_NULL);
		if (super.removeElem(elem) == null) {
			throw new ElementNotFoundException();
		}
	}


}