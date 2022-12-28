package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length == 0 || array.length == 1 || leftIndex > rightIndex || leftIndex < 0 || rightIndex > array.length - 1) {
			return;
		} else {
			int k = maior(array, leftIndex, rightIndex) + 1;
			int[] auxiliar = new int[k];
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				auxiliar[array[i]] += 1;
			}
			
			for (int i = 1; i < auxiliar.length; i++) {
				auxiliar[i] += auxiliar[i - 1];
			}
			
			Integer[] b = array.clone();
			for (int i = rightIndex; i >= leftIndex; i--) {
				array[leftIndex - 1 + auxiliar[b[i]]] = b[i];
				auxiliar[b[i]]--;
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

}
