package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		return data == null; 
	}


	@Override
	public int size() {
		if(isEmpty()) {
			return 0;
		}else {
			return 1 + next.size();
		}
	}

	@Override
	public T search(T element) {
		if(isEmpty() || element == null) {
			return null;
		}
		else {
			if(data.equals(element)) {
				return data;
			}
			else {
				return next.search(element);
			}
		}
	}

	@Override
	public void insert(T element) {
		if(element != null) {
			if(isEmpty()) {
				data = element;
				next = new RecursiveSingleLinkedListImpl<T>();
			}
			else {
				next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if(!isEmpty()) {
			if(data.equals(element)) {
				data = next.getData();
				next = next.getNext();
			}
			else {
				next.remove(element);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		Comparable<T>[] result = new Comparable[size()];
		getElements(result,this,0);
		return (T[]) result;
	}

	@SuppressWarnings("unchecked")
	private void getElements(Comparable<T>[] result, 
			RecursiveSingleLinkedListImpl<T> node, int i) {
		
		if(!node.isEmpty()) {
			result[i] = (Comparable<T>) node.getData();
			getElements(result, node.getNext(), i + 1);
		}
		
	}


	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
