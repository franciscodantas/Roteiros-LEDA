package orderStatistic;

import java.util.Arrays;

import util.Util;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Porem, voce pode modificar o array original.
 *   Voce pode criar ainda um array de tamanho k que vai armazenar apenas
 *   os elementos a serem retornados.
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem.
 * - Se a entrada for invalida, deve-se retornar um array vazio (por exemplo,
 *   ao solicitar os 5 maiores elementos em um array que soh contem 3 elementos).
 *   Este metodo NUNCA deve retornar null.
 * 
 * @author campelo and adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{
	
	@Override
	public T[] getKLargest(T[] array, int k) {
		if(array == null || k >= array.length || k <= 0 || array.length == 0) {
			return (T[]) new Comparable[] {};
		}
		
		T[] retorno = Arrays.copyOfRange(array, 0, k);
		int j = k - 1;
		for(int i = array.length - 1; i >= array.length - k; i-- ) {
				retorno[j--] = this.orderStatistics(array, i);
		}
		return retorno;
		
		
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k){
		if(array == null || k >= array.length || k <= 0 || array.length == 0) {
			return null;
		}

		return quickSelect(array, 0, array.length - 1, k);	
	}
	
	/**
	 * Pega o elemento de ordem k, caso exista, e o retorna.
	 * 
	 * @param array array de procura
	 * @param left limite inferior da procura
	 * @param right limite superio da procura
	 * @param k ordem procurada
	 * @return elemento de k ordem.
	 */
	private T quickSelect(T[] array, int left, int right, int k) {
		if(left > right) {
			return null;
		}
		int pivot = particiona(array, left, right);
		if(pivot == k) {
			return array[pivot];
		}
		else if(pivot > k) {
			return quickSelect(array, left, pivot - 1, k);
		}
		else {
			return quickSelect(array, pivot + 1, right, k);
		}
	}
	
	/**
	 * Particionamento usado no quickSelect, usa-se para colocar o pivot no seu devido
	 * local no array.
	 * 
	 * @param array array analizado
	 * @param left primeiro index do array da chamada
	 * @param right ultimo index do array da chamada
	 * @return posição final do pivot.
	 */
	private int particiona(T[] array, int left, int right) {
		T pivot = array[left];
		int i = left;
		for(int j = left + 1; j <= right; j++) {
			if(array[j].compareTo(pivot) < 0) {
				i++;
				Util.swap(array, i, j);
			}
		}
		Util.swap(array, left, i);
		return i;
	}
}
