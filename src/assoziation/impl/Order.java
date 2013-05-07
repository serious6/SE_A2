package assoziation.impl;

/**
 * 
 * Ein Auftrag mit einer speziellen Auftragsnummer
 * 
 */
public class Order {
	public int number;

	/**
	 * Construct Order
	 * 
	 * @param nummer
	 */
	public Order(int nummer) {
		this.number = nummer;
	}

	/**
	 * Überpüft ob das übergebene Objekt inhaltsgleich ist
	 * 
	 * @param überprüfendes Element            
	 * 
	 * @return true bei Gleichheit, false sonst.
	 */
	public boolean equals(Object o) {
		return (o instanceof Order) ? this.equals((Order) o) : false;
	}

	/**
	 * Überpüft ob das übergebene Objekt inhaltsgleich ist
	 * 
	 * @param überprüfendes Order Element           
	 * 
	 * @return true bei Gleichheit, false sonst.
	 */
	public boolean equals(Order other) {
		return this.number == other.number;
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	public String toString() {
		return "Nummer: " + number + "\n";
	}
}
