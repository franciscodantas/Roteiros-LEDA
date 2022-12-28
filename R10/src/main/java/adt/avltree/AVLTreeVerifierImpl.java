package adt.avltree;

import adt.bst.BSTNode;
import adt.bst.BSTVerifierImpl;
import adt.bt.BTNode;

/**
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeVerifierImpl<T extends Comparable<T>> extends BSTVerifierImpl<T> implements AVLTreeVerifier<T> {

	private AVLTreeImpl<T> avlTree;

	public AVLTreeVerifierImpl(AVLTree<T> avlTree) {
		super(avlTree);
		this.avlTree = (AVLTreeImpl<T>) avlTree;
	}

	private AVLTreeImpl<T> getAVLTree() {
		return avlTree;
	}

	@Override
	public boolean isAVLTree() {

		return isBST() && isAVLTree(this.avlTree.getRoot());
	}

	private boolean isAVLTree(BSTNode<T> root) {
		boolean retorno = false;
		if(root.isEmpty()) {
			retorno = true;
		}
		else {
			if(Math.abs(height((BSTNode<T>) root.getLeft()) - height((BSTNode<T>) root.getRight())) <= 1) {
				retorno = true && this.isAVLTree((BSTNode<T>) root.getLeft()) && this.isAVLTree((BSTNode<T>) root.getRight());
			}
			else {
				retorno = false;
			}
		}
		return retorno;
	}

	protected int height(BSTNode<T> current) {
		if(current.isEmpty()) return -1;
		return 1 + Math.max(height((BSTNode<T>) current.getLeft()), height((BSTNode<T>) current.getRight()));
	}
	

}
