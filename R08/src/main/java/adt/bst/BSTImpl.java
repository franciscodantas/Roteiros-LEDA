package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(root);
	}

	private int height(BSTNode<T> current) {
		if(current.isEmpty()) return -1;
		return 1 + Math.max(height((BSTNode<T>) current.getLeft()), height((BSTNode<T>) current.getRight()));
	}

	@Override
	public BSTNode<T> search(T element) {
		if(element == null) 
			return null;
		else
			return search(root, element);
	}

	private BSTNode<T> search(BSTNode<T> current, T element) {
		if(current.isEmpty()) {
			return new BSTNode<T>();
		}
		if(current.getData().equals(element)) {
			return current;
		}
		else {
			if (current.getData().compareTo(element) > 0) {
				return search((BSTNode<T>) current.getLeft(), element);
			}
			else {
				return search((BSTNode<T>) current.getRight(), element);
			}
		}
	}

	@Override
	public void insert(T element) {
		if(element != null)
			insert(root, element, new BSTNode<T>());
	}

	private void insert(BSTNode<T> current, T element, BSTNode<T> parent) {
		if(current.isEmpty()) {
			current.setData(element);
			current.setParent(parent);
			current.setLeft(new BSTNode<T>());
			current.setRight(new BSTNode<T>());
		}
		else {
			if(current.getData().compareTo(element) < 0) {
				insert((BSTNode<T>)current.getRight(), element, current);
			}else {
				insert((BSTNode<T>)current.getLeft(), element, current);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if(!isEmpty()) {
			return maximum(root);
		}
		return null;
	}

	private BSTNode<T> maximum(BSTNode<T> current) {
		if(current.getRight().isEmpty()) {
			return current;
		}else {
			return maximum((BSTNode<T>) current.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		if(!isEmpty()) {
			return minimum(root);
		}
		return null;
	}

	private BSTNode<T> minimum(BSTNode<T> current) {
		if(current.getLeft().isEmpty()) {
			return current;
		}else {
			return minimum((BSTNode<T>) current.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> retorno = null;
		if(element != null) {
			BSTNode<T> target = this.search(element);
			if(!target.isEmpty()) {
				if(!target.getRight().isEmpty()) {
					retorno = this.minimum((BSTNode<T>) target.getRight());
				}
				else {
					BSTNode<T> y = (BSTNode<T>) target.getParent();
					while(!y.isEmpty() && target == y.getRight()) {
						target = y;
						y = (BSTNode<T>) y.getParent();
					}
					if(y.isEmpty()) {
						retorno = null;
					}
					else {
						retorno = y;
					}
				}
			}
		}
		return retorno;
		
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> retorno = null; 
		if(element != null) {
			BSTNode<T> target = this.search(element);
			if(!target.isEmpty()) {
				if(!target.getLeft().isEmpty()) {
					retorno = maximum((BSTNode<T>) target.getLeft());
				}
				else {
					BSTNode<T> y = (BSTNode<T>) target.getParent();
					while(!y.isEmpty() && target == y.getLeft()) {
						target = y;
						y = (BSTNode<T>) y.getParent();
					}
					if(y.isEmpty()) {
						retorno = null;
					}
					else {
						retorno = y;
					}
				}
			}
		}
		return retorno;
	}

	@Override
	public void remove(T element) {
		if(element != null) {
			BSTNode<T> target = search(element);
			if(!target.isEmpty()) {
				remove(target);
			}
		}
		
	}

	private void remove(BSTNode<T> target) {
		if(target.isLeaf()) {
			if(target.equals(root) && root.isLeaf()) {
				root.setData(null);
			}
			else {
				if(target.getData().compareTo(target.getParent().getData()) < 0) {
					target.getParent().setLeft(new BSTNode<T>());
				}else {
					target.getParent().setRight(new BSTNode<T>());
				}
			}
		}
		
		else if(!target.getLeft().isEmpty() && target.getRight().isEmpty()) {
			if(target.getLeft().equals(root.getLeft())) {
				root = (BSTNode<T>) target.getLeft();
				root.setParent(new BSTNode<T>());
			}
			else {
				target.getLeft().setParent(target.getParent());
				if(target.getData().compareTo(target.getParent().getData()) < 0) {
					target.getParent().setLeft(target.getLeft());
				}else {
					target.getParent().setRight(target.getLeft());
				}
			}
			
		}
		
		else if(target.getLeft().isEmpty() && !target.getRight().isEmpty()) {
			if(target.getRight().equals(root.getRight())) {
				root = (BSTNode<T>) target.getRight();
				root.setParent(null);
			}
			else {
				target.getRight().setParent(target.getParent());
				if(target.getData().compareTo(target.getParent().getData()) < 0) {
					target.getParent().setLeft(target.getRight());
				}else {
					target.getParent().setRight(target.getRight());
				}
			}
		}
		
		else {
			BSTNode<T> sucessor = sucessor(target.getData());
			target.setData(sucessor.getData());
			remove(sucessor);
		}
		
	}

	@Override
	public T[] preOrder() {
		return preOrder(root, new ArrayList<T>());
	}
	
	@SuppressWarnings("unchecked")
	private T[] preOrder(BSTNode<T> current, ArrayList<T> list) {
		if(!current.isEmpty()) {
			list.add(current.getData());
			preOrder((BSTNode<T>) current.getLeft(), list);
			preOrder((BSTNode<T>) current.getRight(), list);
		}
		return (T[]) list.toArray(new Comparable[size()]);
	}

	@Override
	public T[] order() {
		return order(root, new ArrayList<T>());
	}

	@SuppressWarnings("unchecked")
	private T[] order(BSTNode<T> current, ArrayList<T> list) {
		if(!current.isEmpty()) {
			order((BSTNode<T>) current.getLeft(), list);
			list.add(current.getData());
			order((BSTNode<T>) current.getRight(), list);
		}
		return (T[]) list.toArray(new Comparable[size()]);
	}

	@Override
	public T[] postOrder() {
		return postOrder(root, new ArrayList<T>());
	}

	@SuppressWarnings("unchecked")
	private T[] postOrder(BSTNode<T> current, ArrayList<T> list) {
		if(!current.isEmpty()) {
			postOrder((BSTNode<T>) current.getLeft(), list);
			order((BSTNode<T>) current.getRight(), list);
			list.add(current.getData());
		}
		return (T[]) list.toArray(new Comparable[size()]);
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
