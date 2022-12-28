package adt.linkedList;
public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	public DoubleLinkedListImpl() {
		last = new DoubleLinkedListNode<T>();
		super.head = new DoubleLinkedListNode<T>();
	}
	
	@Override
	public T search(T element) {
		SingleLinkedListNode<T> auxHead = head;
		DoubleLinkedListNode<T> auxLast = last;
		while(!auxHead.equals(auxLast) && !auxHead.getNext().equals(auxLast) 
				&& !auxHead.getData().equals(element) && !auxLast.getData().equals(element)) {
			
			auxHead = auxHead.getNext();
			auxLast = auxLast.getPrevious();
		}
		if(auxHead.getData().equals(element)) {
			return auxHead.getData();
		}else if(auxLast.getData().equals(element)){
			return auxLast.getData();
		}else {
			return null;
		}
	}

	@Override
	public void insertFirst(T element) {
		if(element != null) {
			DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) head;
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, aux, new DoubleLinkedListNode<T>());
			aux.setPrevious(newNode);
			if(head.isNIL()) {
				last = newNode;
			}
			head = newNode;
		}
	}
	
	@Override
	public void insert(T element) {
		if(element != null) {
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element,new DoubleLinkedListNode<T>(), last);
			last.setNext(newNode);
			if(last.isNIL()) {
				head = newNode;
			}
			last = newNode;
		}
	}

	@Override
	public void removeFirst() {
		if(!head.isNIL()) {
			DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) head.getNext();
			aux.setPrevious(new DoubleLinkedListNode<T>());
			head = aux;
		}
	}
	
	@Override
	public void remove(T element) {
		if(element != null) {
			if(!head.isNIL()) {
				if(head.getData().equals(element)) {
					removeFirst();
				}else if(last.getData().equals(element)) {
					removeLast();
				}
				else {
					DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) head.getNext();
					while(!aux.isNIL() && !aux.getData().equals(element) && !aux.equals(last)) {
						aux = (DoubleLinkedListNode<T>) aux.getNext();
					}
					if (!aux.isNIL()) {
						aux.getPrevious().setNext(aux.getNext());
						((DoubleLinkedListNode<T>) aux.next).setPrevious(aux.getPrevious());
					}
				}
			}
		}
	}

	@Override
	public void removeLast() {
		if(!head.isNIL()) {
			if(last.getPrevious().isNIL() && head.getNext().isNIL()) {
				last = new DoubleLinkedListNode<T>();
				head = new DoubleLinkedListNode<T>();
			}
			else {
				last.getPrevious().setNext(new DoubleLinkedListNode<T>());
				last = last.getPrevious();
			}
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
