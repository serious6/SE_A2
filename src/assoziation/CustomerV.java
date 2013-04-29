package assoziation;

import assoziation.exception.ElementNotFoundException;
import assoziation.exception.ListAddedException;

public class CustomerV {

	private String name;
	OneToManyVoid<Order> list;

	CustomerV(String name) {
		this.name = name;
		list = new OneToManyVoid<Order>();
	}

	public void add(Order order) throws ListAddedException {
		list.add(order);
	}

	public void remove(Order order) throws ElementNotFoundException {
		list.remove(order);
	}

	public boolean contains(Order order) {
		return list.contains(order);
	}

	public void clear() {
		list.clear();
	}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public String toString() {
		return "Name: " + name + list.toString();
	}

}
