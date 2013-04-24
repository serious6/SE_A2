package assoziation;

public class CustomerV {

	private String name;
	OneToManyVoid<Order> list;
	
	CustomerV(String name) {
		this.name = name;
		list = new OneToManyVoid<Order>();
	}

	public void add(Order order) {
		list.add(order);
	}
	
	public void remove(Order order){
		list.remove(order);
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
