package adt.bst;

import adt.bt.BTNode;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {
	
	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}
	
	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		return isBST(bst.getRoot());
	}

	private boolean isBST(BSTNode<T> root) {
		boolean resp = false;
		if(root.isEmpty()) {
			resp = true;
		}
		else if(Left((BSTNode<T>) root.getLeft(), root) && Right((BSTNode<T>) root.getRight(), root)) {
			resp = this.isBST((BSTNode<T>) root.getLeft()) && this.isBST((BSTNode<T>) root.getRight());
		}
		else {
			resp = false;
		}
		return resp;
	}

	private boolean Right(BSTNode<T> right, BSTNode<T> root) {
		boolean resp = false;
		
		if(right.isEmpty())
			resp = true;
		
		else if (right.getData().compareTo(root.getData()) > 0) {
			resp = true && this.Right((BSTNode<T>) right.getLeft(), root) && this.Right((BSTNode<T>) right.getRight(), root);
		}
		
		return resp;
	}

	private boolean Left(BSTNode<T> left, BSTNode<T> root) {
		boolean resp = false;
		
		if(left.isEmpty())
			resp = true;
		
		else if (left.getData().compareTo(root.getData()) < 0) {
			resp = true && this.Left((BSTNode<T>) left.getLeft(), root) && this.Left((BSTNode<T>) left.getRight(), root);
		}
		
		return resp;
	}
	
}
