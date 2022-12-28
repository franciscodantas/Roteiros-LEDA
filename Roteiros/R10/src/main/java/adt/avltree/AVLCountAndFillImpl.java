package adt.avltree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}
	
	@Override
	protected void rebalance(BSTNode<T> node) {
		BSTNode<T> newRoot = null;
		int balance = this.calculateBalance(node);
		if(Math.abs(balance) > 1) {
			
			if(balance > 1) {
				if (this.calculateBalance((BSTNode<T>) node.getLeft()) >= 0) {
					newRoot = Util.rightRotation(node);
					this.LLcounter++;
				}
				
				else {
					Util.leftRotation((BSTNode<T>) node.getLeft());
					newRoot = Util.rightRotation(node);
					this.LRcounter++;
				}
			} 
			
			else {
				
				if(this.calculateBalance((BSTNode<T>) node.getRight()) <= 0) {
					newRoot = Util.leftRotation(node);
					this.RRcounter++;
				}
				
				else {
					Util.rightRotation((BSTNode<T>) node.getRight());
					newRoot = Util.leftRotation(node);
					this.RLcounter++;
				}
			}
			
			if (this.getRoot().equals(node) && newRoot != null) {
				this.root = newRoot;
			}
			
		}
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		HashSet<T> set = new HashSet<T>();
		for(T i : array) {
			set.add(i);
		}
		for(T i : this.order()) {
			set.add(i);
		}
		array = (T[]) set.toArray(new Comparable[set.size()]);
		Arrays.sort(array);
		this.root = new BSTNode<T>();
		
		int height = 0;
		
		while(fillWithoutRebalance(array, 0, array.length, height)) {
			height++;
		}

	}

	private boolean fillWithoutRebalance (T[] array, int left, int right, int height) {
		boolean resp = false;

		if (right > left) {
			int middle = left + (right - left) / 2;

			if (height == 0) {
				this.insert(array[middle]);
				resp = true;
			}
			else {
				boolean result1 = fillWithoutRebalance(array, left, middle, height - 1);
				boolean result2 = fillWithoutRebalance(array, middle + 1, right, height - 1);

				resp = result1 || result2;
			}
		}
		return resp;
	}

}
