package problems;

import util.Util;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		if(array == null || x == null || array.length == 0) {
			return null;
		}
		
		sort(array, 0 , array.length - 1);
		return buscaFloor(array, 0, array.length - 1, x);
	}
	
	/**
	 * Busca binaria que realiza a busca pelo floor de x, assumindo sempre que o
	 * array está ordenado do metodo que o chamou.
	 * Retorna null caso não exista.
	 * 
	 * @param array array analizado
	 * @param leftIndex primeiro index do array da chamada
	 * @param rightIndex ultimo index do array da chamada
	 * @param x numero que está sendo procurado o seu floor
	 * @return floor de x
	 */
	private Integer buscaFloor(Integer[] array, int leftIndex, int rightIndex, Integer x) {
		
		if(leftIndex > rightIndex) {
			return null;
		}
		
		if(array.length == 1 && array[0].compareTo(x) <= 0) {
			return array[0];
		}
		
		if(array[rightIndex].compareTo(x) < 0) {
			return array[rightIndex];
		}
		
		int meio = (leftIndex + rightIndex)/2;
		if(array[meio].compareTo(x) == 0) {
			return array[meio];
		}
		else if(array[meio].compareTo(x) > 0) {
			if(leftIndex > 0 && array[meio - 1].compareTo(x) < 0) {
				return array[meio - 1];
			}
			else {
				return this.buscaFloor(array, leftIndex, meio - 1, x);
			}
		}
		else {
			return this.buscaFloor(array, meio + 1, rightIndex, x);
		}
	}
	
	/**
	 * QuickSort implementado para garantir que o array sempre está ordenado
	 * para se fazer a busca binaria
	 * 
	 * @param array array analizado
	 * @param leftIndex primeiro index do array da chamada
	 * @param rightIndex ultimo index do array da chamada
	 */
	private void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length == 0 || array.length == 1 || leftIndex >= rightIndex || leftIndex < 0 || rightIndex > array.length - 1) {
			return;
		}else {
			int indexPivot = particiona(array, leftIndex, rightIndex);
			sort(array, leftIndex, indexPivot - 1);
			sort(array, indexPivot + 1, rightIndex);
		}
	}

	/**
	 * Particionamento usado no quicksort, usa-se para colocar o pivot no seu devido
	 * local no array.
	 * 
	 * @param array array analizado
	 * @param leftIndex primeiro index do array da chamada
	 * @param rightIndex ultimo index do array da chamada
	 * @return posição final do pivot.
	 */
	private int particiona(Integer[] array, int leftIndex, int rightIndex) {
		int i  = leftIndex;
		Integer pivot = array[leftIndex];
		for (int j = leftIndex + 1; j <= rightIndex; j++) {
			if(pivot.compareTo(array[j]) >= 0) {
				i++;
				Util.swap(array, i, j);
			}
		}
		
		Util.swap(array, leftIndex, i);
		return i;
	}

}
