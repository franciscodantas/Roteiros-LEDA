package adt.bst;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		return equals((BSTNode<T>)tree1.getRoot(),(BSTNode<T>)tree2.getRoot());
	}

	private boolean equals(BSTNode<T> root, BSTNode<T> root2) {
		if(root.isEmpty() && root2.isEmpty()) {
			return true;
		}
		else {
			if(!root.isEmpty() && !root2.isEmpty()) {
				if(!root.getData().equals(root2.getData())) {
					return false;
				}
				else {
					return true && equals((BSTNode<T>)root.getLeft(), (BSTNode<T>)root2.getLeft()) && 
							equals((BSTNode<T>)root.getRight(), (BSTNode<T>)root2.getRight());
				}
			}
			else {
				return false;
			}
		}
		
		
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		return isSimilar((BSTNode<T>)tree1.getRoot(),(BSTNode<T>)tree2.getRoot());
	}

	private boolean isSimilar(BSTNode<T> root, BSTNode<T> root2) {
		if(root.isEmpty() && root2.isEmpty()) {
			return true;
		}
		else {
			if(!root.isEmpty() && !root2.isEmpty()) {
				return true && isSimilar((BSTNode<T>)root.getLeft(), (BSTNode<T>)root2.getLeft()) && 
						isSimilar((BSTNode<T>)root.getRight(), (BSTNode<T>)root2.getRight());
			}else {
				return false;
			}
		}
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		T retorno = null;
		if(k >= 1 && k <= tree.size()) {
			if(k == 1) {
				retorno = tree.minimum().getData();
			}
			else if (k == tree.size()) {
				retorno = tree.maximum().getData();
			}
			else {
				retorno = this.orderStatistic(tree, tree.minimum(), k);
			}
		}
		
		return retorno;
	}

	private T orderStatistic(BST<T> tree, BSTNode<T> root, int k) {
		if(k == 1) {
			return root.getData();
		}
		else {
			return this.orderStatistic(tree, tree.sucessor(root.getData()), --k);
		}
	}

}
