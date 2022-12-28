package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array.length == 0 || array.length == 1 || leftIndex >= rightIndex || leftIndex < 0 || rightIndex > array.length - 1) {
			return;
		} else{
			T pivot = mediana(array, leftIndex, rightIndex);
			int indexPivot = particiona(array, leftIndex, rightIndex, pivot);
			sort(array, leftIndex, indexPivot - 1);
			sort(array, indexPivot + 1, rightIndex);
		}
	}
	
	private int particiona(T[] array, int leftIndex, int rightIndex, T pivot) {
		int i  = leftIndex - 1;
		for (int j = leftIndex; j < rightIndex - 1; j++) {
			if(pivot.compareTo(array[j]) >= 0) {
				i++;
				Util.swap(array, i, j);
			}
		}
		
		Util.swap(array, rightIndex - 1, i + 1);
		return i + 1;
	}

	private T mediana(T[] array, int leftIndex, int rightIndex) {
		int meio = (leftIndex + rightIndex)/2;
		if (array[leftIndex].compareTo(array[meio]) < 0) {
			if (array[meio].compareTo(array[rightIndex]) < 0) {
				Util.swap(array, rightIndex - 1, meio);
				return array[rightIndex - 1];
			} else if(array[leftIndex].compareTo(array[rightIndex]) < 0){
				Util.swap(array, rightIndex, meio);
				Util.swap(array, rightIndex - 1, meio);
				return array[rightIndex - 1];
			}else{
				Util.swap(array, rightIndex, meio);
				Util.swap(array, leftIndex, meio);
				Util.swap(array, rightIndex - 1, meio);
				return array[rightIndex - 1];
			}
		}else{
			if (array[rightIndex].compareTo(array[meio]) < 0) {
				Util.swap(array, rightIndex, leftIndex);
				Util.swap(array, rightIndex - 1, meio);
				return array[rightIndex - 1];
			} else if(array[rightIndex].compareTo(array[leftIndex]) < 0){
				Util.swap(array, rightIndex, meio);
				Util.swap(array, rightIndex, leftIndex);
				Util.swap(array, rightIndex - 1, meio);
				return array[rightIndex - 1];
			}else{
				Util.swap(array, leftIndex, meio);
				Util.swap(array, rightIndex - 1, meio);
				return array[rightIndex - 1];
			}
		}
	}

}
