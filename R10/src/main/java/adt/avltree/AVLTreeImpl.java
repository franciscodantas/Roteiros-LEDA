package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * A CLASSE AVLTree herda de BSTImpl. VOCE PRECISA SOBRESCREVER A IMPLEMENTACAO
 * DE BSTIMPL RECEBIDA COM SUA IMPLEMENTACAO "OU ENTAO" IMPLEMENTAR OS SEGUITNES
 * METODOS QUE SERAO TESTADOS NA CLASSE AVLTREE:
 *  - insert
 *  - preOrder
 *  - postOrder
 *  - remove
 *  - height
 *  - size
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	@Override
	public void insert(T element) {
		if(element != null)
			insert(root, element, null);
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
		this.rebalance(current);
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
			this.rebalanceUp(target);
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
			
			this.rebalanceUp(target);
			
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
			
			this.rebalanceUp(target);
		}
		
		else {
			BSTNode<T> sucessor = sucessor(target.getData());
			target.setData(sucessor.getData());
			remove(sucessor);
		}
		
	}
	
	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int repost = 0;
		if(!node.isEmpty()) {
			repost = this.height((BSTNode<T>) node.getLeft()) - this.height((BSTNode<T>) node.getRight());
		}
		return repost;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		BSTNode<T> newRoot = null;
		int balance = this.calculateBalance(node);
		if(Math.abs(balance) > 1) {
			
			if(balance > 1) {
				if (this.calculateBalance((BSTNode<T>) node.getLeft()) >= 0) {
					newRoot = Util.rightRotation(node);
				}
				
				else {
					Util.leftRotation((BSTNode<T>) node.getLeft());
					newRoot = Util.rightRotation(node);
				}
			} 
			
			else {
				
				if(this.calculateBalance((BSTNode<T>) node.getRight()) <= 0) {
					newRoot = Util.leftRotation(node);
				}
				
				else {
					Util.rightRotation((BSTNode<T>) node.getRight());
					newRoot = Util.leftRotation(node);
				}
			}
			
			if (this.getRoot().equals(node) && newRoot != null) {
				this.root = newRoot;
			}
			
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while(parent != null) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}
}
