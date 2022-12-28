package adt.heap.extended;

import java.util.Comparator;

import adt.heap.ComparatorMaxHeap;
import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		if(array != null) {
			for(Integer i : array)
				this.insert(i);
		}
		
		return floor(numero, null);
	}

	private Integer floor(double numero, Integer floor) {
		Integer root = this.extractRootElement();

		if (root != null) {
			if (numero >= root && (floor == null || root >= floor))
				floor = this.floor(numero, root);
			else
				floor = this.floor(numero, floor);
		}

		return floor;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		if(array != null) {
			for(Integer i : array)
				this.insert(i);
		}
		
		return ceil(numero, null);
	}

	private Integer ceil(double numero, Integer ceil) {
		Integer root = this.extractRootElement();

		if (root != null) {
			if (numero <= root && (ceil == null || root <= ceil))
				ceil = this.ceil(numero, root);
			else
				ceil = this.ceil(numero, ceil);
		}
		
		return ceil;
	}

}
