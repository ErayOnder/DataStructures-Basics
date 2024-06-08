package BasicDataStructures;

public class MyStack {
	private Node top;
	private int size = 0;

	// Adds a new element to the top of the stack
	public void push(String data) {
		Node newNode = new Node(data);
		newNode.next = top;
		top = newNode;
		size++;
	}

	// Removes and returns the top element of the stack
	public String pop() {
		if (isEmpty()) {
			System.out.println("Stack Empty!");
			return null;
		} else {
			String data = top.data;
			top = top.next;
			size--;
			return data;
		}
	}

	// Returns the top element of the stack without removing it
	public String peek() {
		if (isEmpty()) {
			System.out.println("Stack Empty!");
			return null;
		} else {
			return top.data;
		}
	}

	// Checks if the stack is empty
	public boolean isEmpty() {
		return size == 0;
	}

	// Returns the number of elements in the stack
	public int size() {
		return size;
	}

	// Checks if the index is in the range of the stack size.
	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}
}
