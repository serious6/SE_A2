package assoziation.impl;

import java.util.Collection;

import assoziation.Factory;
import assoziation.IOneToManyVoid;

public class OneToManyVoidOrig<E> extends OneToManyAbstract<E> implements IOneToManyVoid<E> {

	public OneToManyVoidOrig(Factory<E> factory) {
    	this.factory = factory;
    }

	public OneToManyVoidOrig(Factory<E> factory, Collection<E> coll) {
		this.factory = factory;
		for(E elem : coll) {
			add(elem);
		}
    }
	

	/**
     * F�gt ein Element hinzu, falls dieses noch nicht enthalten ist.
     * 
     * @param elem Das hinzuzuf�gende Element.
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
     * @param elem Das zu l�schende Element.
     */
    @Override
    public void remove(E elem) {
    	super.removeElem(elem);
    }
	

}
