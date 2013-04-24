package assoziation;

public class CustomerB {

	private String name;
	OneToManyReturnB<Order> list;
	
	CustomerB(String name) {
		this.name = name;
		list = new OneToManyReturnB<Order>();
	}

	public boolean add(Order order) {
		return list.add(order);
	}
	
	public boolean remove(Order order){
		return list.remove(order);
	}
	
	public boolean contains(Order order){
		return list.contains(order);
	}
	
	public void clear(){
		list.clear();
	}
	
	public int size(){
		return list.size();
	}
	
	public boolean isEmpty(){
		return list.isEmpty();
	}

	public String toString(){
		return "Name: " + name  +  list.toString();
	}
	
}
