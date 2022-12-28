package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(element != null) {
			if(isFull()) {
				throw new StackOverflowException();
			}
			top.insert(element);
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException();
		}
		T remove = top();
		top.removeLast();
		return remove;
	}

	@Override
	public T top() {
		if(isEmpty()) {
			return null;
		}
		return (T) ((DoubleLinkedListImpl<T>) top).getLast().getData();
	}

	@Override
	public boolean isEmpty() {
		return top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return top.size() == size;
	}

}
