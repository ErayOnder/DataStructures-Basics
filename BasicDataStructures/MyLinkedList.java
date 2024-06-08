package BasicDataStructures;

public class MyLinkedList {
	private Node head;
	private int size = 0;

	public MyLinkedList() {
		this.head = null;
	}

	// Adds a new node with the specified data at the end of the list
	public void add(String data) {
		if (head == null) {
			head = new Node(data);
		} else {
			Node currentElement = head;
			while(currentElement.next != null) {
				currentElement = currentElement.next;
			}
			currentElement.next = new Node(data);
		}
		size++;
	}

	// Inserts a new node with the specified data at the given index
	public void insert(int index, String data) {
		checkIndex(index);
		Node newNode = new Node(data);
		if(index == 0) {
			Node oldHead = head;
			newNode.next = oldHead;
			head = newNode;
		} else {
			Node currentElement = head;
			for(int i = 0; i < index - 1; i++) {
				currentElement = currentElement.next;
			}
			Node nextElement = currentElement.next;
			currentElement.next = newNode;
			newNode.next = nextElement;
		}
		size++;
	}

	// Removes the node at the specified index
	public void remove(int index) {
		checkIndex(index);
		Node currentElement = head;
		if(index == size - 1) {
			while(currentElement.next != null) {
				currentElement = currentElement.next;
			}
			currentElement = null;
		} else {
			for(int i = 0; i < index - 1; i++) {
				currentElement = currentElement.next;
			}
			currentElement.next = currentElement.next.next;
		}
		size--;
	}

	// Returns the data of the node at the specified index
	public String get(int index) {
		Node currentElement = head;
		for(int i = 0; i < index; i++) {
			currentElement = currentElement.next;
		}
		return currentElement.data;
	}

	// Returns the number of elements in the list
	public int size() {
		return size;
	}

	// Checks if the index is in the range of the list size.
	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}
}
