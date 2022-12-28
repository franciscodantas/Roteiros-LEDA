package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array.length == 0 || array.length == 1 || leftIndex >= rightIndex || leftIndex < 0 || rightIndex > array.length - 1) {
			return;
		} else{
			int meio = (leftIndex + rightIndex)/2;
			sort(array, leftIndex, meio);
			sort(array, meio + 1, rightIndex);
			merge(array, leftIndex, meio, rightIndex);
		}
	}
	
	public void merge(T[] array, int leftIndex,int meio, int rightIndex) {
		
		T[] copy = array.clone();
		int i = leftIndex;
		int j = meio + 1;
		int k = leftIndex;
		
		while (i <= meio && j <= rightIndex) {
			if (copy[i].compareTo(copy[j]) <= 0) {
				array[k++] = copy[i++];
			}else {
				array[k++] = copy[j++];
			}
		}
		
		while (i <= meio) {
			array[k++] = copy[i++];
		}
		
		while (j <= rightIndex) {
			array[k++] = copy[j++];
		}
	}
}
