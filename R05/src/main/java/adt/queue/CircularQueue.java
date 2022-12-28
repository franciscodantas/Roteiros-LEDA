package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;
	private int capacity;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
		capacity = size;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(isFull()) 
			throw new QueueOverflowException();
		if(isEmpty()) {
			this.head++;
			this.tail++;
			this.elements++;
			this.array[tail] = element;
		}
		else {
			this.tail = (this.tail + 1) % capacity;
			this.elements++;
			this.array[tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty())
			throw new QueueUnderflowException();
		T element = array[head];
		if(this.head == this.tail) {
			this.head = -1;
			this.tail = -1;
			this.elements--;
		}
		else {
			this.head = (this.head + 1) % capacity;
			this.elements--;
		}
		return element;
	}

	@Override
	public T head() {
		if(isEmpty())
			return null;
		return array[this.head];
	}

	@Override
	public boolean isEmpty() {
		return this.elements == 0;
	}

	@Override
	public boolean isFull() {
		return elements == capacity;
	}

}
