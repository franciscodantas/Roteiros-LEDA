package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length == 0 || array.length == 1 || leftIndex > rightIndex || leftIndex < 0 || rightIndex > array.length - 1) {
			return;
		} else {
			int k = maior(array, leftIndex, rightIndex) + 1;
			int y = menor(array, leftIndex, rightIndex);
			int[] auxiliar = new int[k - y];
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				auxiliar[array[i] - y] += 1;
			}
			
			for (int i = 1; i < auxiliar.length; i++) {
				auxiliar[i] += auxiliar[i - 1];
			}
			
			Integer[] b = array.clone();
			for (int i = rightIndex; i >= leftIndex; i--) {
				array[leftIndex + auxiliar[b[i] - y]-1] = b[i];
				auxiliar[b[i] - y]--;
			}
		}
	}
	
	private int maior(Integer[] array, int leftIndex, int rightIndex) {
		int maior = Integer.MIN_VALUE;
		for(int i = leftIndex; i <= rightIndex; i++) {
			if(array[i] > maior) {
				maior = array[i];
			}
		}
		return maior;
	}
	
	private int menor(Integer[] array, int leftIndex, int rightIndex) {
		int menor = Integer.MAX_VALUE;
		for(int i = leftIndex; i <= rightIndex; i++) {
			if(array[i] < menor) {
				menor = array[i];
			}
		}
		return menor;
	}

}
