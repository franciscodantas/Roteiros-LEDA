package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {
	}
	
	@Override
	public void insert(T element) {
		if(element != null) {
			if(isEmpty()) {
				data = element;
				next = new RecursiveDoubleLinkedListImpl<T>();
				if(previous == null) {
					previous = new RecursiveDoubleLinkedListImpl<T>();
				}
			}
			else {
				next.insert(element);
				((RecursiveDoubleLinkedListImpl<T>) next).previous = this;
			}
		}
	}
	
	@Override
	public void remove(T element) {
		if(!isEmpty() && element != null) {
			if(data.equals(element)) {
				if(this.previous.isEmpty() && next.isEmpty()) {
					data = null;
					next = null;
					previous = null;
				}
				else {
					data = next.getData();
					next = next.getNext();
					if(next != null) {
						((RecursiveDoubleLinkedListImpl<T>) next).previous = this;
					}
				}
			}
			else {
				next.remove(element);
			}
		}
		
	}

	@Override
	public void insertFirst(T element) {
		if(element != null) {
			if(isEmpty()) {
				data = element;
				next = new RecursiveDoubleLinkedListImpl<T>();
				if(previous == null) {
					previous = new RecursiveDoubleLinkedListImpl<T>();
				}
			}else {
				RecursiveDoubleLinkedListImpl<T> aux = new RecursiveDoubleLinkedListImpl<T>();
				aux.setData(data);
				aux.setNext(next);
				((RecursiveDoubleLinkedListImpl<T>) next).previous = aux;
				aux.setPrevious(this);
				this.next = aux;
				this.data = element;
				this.previous = new RecursiveDoubleLinkedListImpl<T>();
			}
		}
	}

	@Override
	public void removeFirst() {
		if(!isEmpty()) {
			if(this.previous.isEmpty() && next.isEmpty()) {
				data = null;
				next = null;
				previous = null;
			}
			else {
				data = next.getData();
				next = next.getNext();
				if(next != null) {
					((RecursiveDoubleLinkedListImpl<T>) next).previous = this;
				}
			}
		}
	}

	@Override
	public void removeLast() {
		if(!isEmpty()) {
			if(next.isEmpty()) {
				if(this.previous.isEmpty()) {
					data = null;
					next = null;
					previous = null;
				}
				else {
					this.data = previous.getData();
					previous.next = null;
					previous.previous.next = this;
					previous = previous.previous;
				}
			}
			else {
				((RecursiveDoubleLinkedListImpl<T>) next).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
