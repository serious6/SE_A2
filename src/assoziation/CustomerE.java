package assoziation;

public class CustomerE {

	private String name;
	OneToManyReturnE<Order> list;
	
	CustomerE(String name) {
		this.name = name;
		list = new OneToManyReturnE<Order>();
	}

	public Order add(Order order) {
		return list.add(order);
	}
	
	public Order remove(Order order){
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