package BasicDataStructures;

/* 
 * This class represents the single node of the data structures that you will implement.
*/

class Node {
	
	String data;
	Node next;

	public Node(String data) {
		this.data = data;
		this.next = null;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
}
