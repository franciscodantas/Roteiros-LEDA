package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = head;
		while(!aux.isNIL()) {
			aux = aux.next;
			size++;
		}
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> aux = head;
		while(!aux.isNIL() && !aux.getData().equals(element)) {
			aux = aux.getNext();
		}
		if(!aux.isNIL()) {
			return aux.getData();
		}else {
			return null;
		}
	}

	@Override
	public void insert(T element) {
		if(element != null) {
			SingleLinkedListNode<T> aux = head;
			while(!aux.isNIL()) {
				aux = aux.getNext();
			}
			aux.setData(element);
			aux.setNext(new SingleLinkedListNode<T>());
		}
	}

	@Override
	public void remove(T element) {
		if(element != null) {
			SingleLinkedListNode<T> aux = head;
			while(!aux.isNIL() && !aux.getData().equals(element)) {
				aux = aux.getNext();
			}
			if(!aux.isNIL()) {
				aux.setData(aux.getNext().getData());
				aux.setNext(aux.getNext().getNext());
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		Comparable<T>[] array = new Comparable[size()];
		SingleLinkedListNode<T> aux = head;
		int i = 0;
		while(!aux.isNIL()) {
			array[i] = (Comparable<T>) aux.getData();
			aux = aux.getNext();
			i++;
		}
		return ((T[]) array);
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}
	
	
}
