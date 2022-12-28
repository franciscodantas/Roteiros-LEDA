package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		if(isEmpty())
			return null;
		return array[0];
	}

	@Override
	public boolean isEmpty() {
		return this.tail == -1;
	}

	@Override
	public boolean isFull() {
		return this.tail == this.array.length - 1;
	}

	private void shiftLeft() {
		for(int i = 1; i < this.tail; i++) {
			array[i - 1] = array[i];
		}
		this.tail --;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(isFull()) 
			throw new QueueOverflowException();
		this.tail ++;
		array[tail] = element;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty())
			throw new QueueUnderflowException();
		T element = array[0];
		shiftLeft();
		return element;
	}

}
