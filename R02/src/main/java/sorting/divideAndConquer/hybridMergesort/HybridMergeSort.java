package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;
	
	private int usosInsertSort;
	private int usosMergeSort;

	@Override
	public void sort(T[] array) {
		this.usosInsertSort = 0;
		this.usosMergeSort = 0;
		sort(array, 0, array.length - 1);
	}

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array.length == 0 || array.length == 1 || leftIndex >= rightIndex || leftIndex < 0 || rightIndex > array.length - 1) {
			return;
		}
		if((rightIndex - leftIndex) <= SIZE_LIMIT) {
			insertSort(array, leftIndex, rightIndex);
			return;
		}else {
			int meio = (leftIndex + rightIndex)/2;
			sort(array, leftIndex, meio);
			sort(array, meio + 1, rightIndex);
			merge(array, leftIndex, meio, rightIndex);
		}
	}

	private void insertSort(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			int j = i;
			while (j > leftIndex && array[j].compareTo(array[j - 1]) < 0) {
				Util.swap(array, j, j - 1);
				j--;
			}
		}
		this.usosInsertSort ++;
		
	}

	private void merge(T[] array, int leftIndex, int meio, int rightIndex) {
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
		this.usosMergeSort ++;
	}
	
	public int getMergeApplications() {
		return this.usosMergeSort;
	}
	
	public int getInsertApplications() {
		return this.usosInsertSort;
	}
}
