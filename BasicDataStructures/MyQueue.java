package BasicDataStructures;

public class MyQueue {
	
    private Node head;
    private Node tail;
    private int size = 0;

    // Adds a new element to the end of the queue
    public void enqueue(String data) {
        Node newNode = new Node(data);
    	if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // Removes and returns the element at the front of the queue
    public String dequeue() {
        if (isEmpty()) {
            System.out.println("Queue empty!");
            return null;
        } else {
            String data = head.data;
            head = head.next;
            size--;
            return data;
        }
    }

    // Returns the element at the front of the queue without removing it
    public String peek() {
    	if (isEmpty()) {
            System.out.println("Queue empty!");
            return null;
        } else {
            return head.data;
        }
    }

    // Checks if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the number of elements in the queue
    public int size() {
        return size;
    }
    
    // Checks if the index is in the range of the queue size.
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
