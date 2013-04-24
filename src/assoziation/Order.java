package assoziation;

public class Order {
	public int number;

	Order(int nummer) {
		this.number = nummer;
	}

	public boolean equals(Object o){
		return (o instanceof Order) ? this.equals((Order) o ) : false;
	}
	
	public boolean equals(Order other) {
		return this.number == other.number;
	}
	
	public String toString(){
		return "Nummer: " + number + "\n"; 
	}
}
