package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		fill(array);
		return floor(root, numero, null);
	}

	private Integer floor(BSTNode<Integer> currentNode, double number, Integer floor) {
		if (!currentNode.isEmpty()) {
			if (number > currentNode.getData())
				floor = this.floor((BSTNode<Integer>) currentNode.getRight(), number, currentNode.getData());
			else if (number < currentNode.getData())
				floor = this.floor((BSTNode<Integer>) currentNode.getLeft(), number, floor);
			else
				floor = currentNode.getData();
		}

		return floor;
	}

	private void fill(Integer[] array) {
		for(int i = 0; i < array.length; i++) {
			this.insert(array[i]);
		}
		
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		fill(array);
		return ceil(this.root, numero, null);
	}

	private Integer ceil(BSTNode<Integer> currentNode, double number, Integer ceil) {
		if (!currentNode.isEmpty()) {
			if (number > currentNode.getData())
				ceil = this.ceil((BSTNode<Integer>) currentNode.getRight(), number, ceil);
			else if (number < currentNode.getData())
				ceil = this.ceil((BSTNode<Integer>) currentNode.getLeft(), number, currentNode.getData());
			else
				ceil = currentNode.getData();
		}

		return ceil;
	}

}
