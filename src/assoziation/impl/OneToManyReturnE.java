package assoziation.impl;

import java.util.Collection;

import assoziation.Factory;
import assoziation.IOneToManyReturnE;

public class OneToManyReturnE<E> extends OneToManyAbstract<E> implements IOneToManyReturnE<E> {

    public OneToManyReturnE(Factory<E> factory) {
    	this.factory = factory;
    }

	public OneToManyReturnE(Factory<E> factory, Collection<E> coll) {
		this.factory = factory;
		for(E elem : coll) {
			add(elem);
		}
    }
    
    /**
     * F�gt ein Element hinzu, falls dieses noch nicht enthalten ist.
     * 
     * @param elem Das hinzuzuf�gende Element.
     * @return Das hinzugef�gte Element oder null, falls bereits vorhanden.
     */
    @Override
	public E add(E elem) {
		return super.addElem(elem);
	}
	
	/**
     * Entfernt das Element, falls es enthalten ist.
     * 
     * @param elem Das zu l�schende Element.
     * @return Das gel�schte Element oder null, falls es nicht enthalten war.
     */
    @Override
    public E remove(E elem) {
    	return super.removeElem(elem);
    }
    
}
