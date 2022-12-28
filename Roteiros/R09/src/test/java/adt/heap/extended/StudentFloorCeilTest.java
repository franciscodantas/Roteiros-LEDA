package adt.heap.extended;

import static org.junit.Assert.assertEquals;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import adt.heap.ComparatorMinHeap;

public class StudentFloorCeilTest {
	
	Comparator<Integer> comparator;
	FloorCeilHeapImpl fc;
	Integer[] array = new Integer[] {10,2,7,9,25,30,79};

	@Before
	public void inicial() {
		comparator = new ComparatorMinHeap<Integer>();
		fc = new FloorCeilHeapImpl(comparator);
	}
	
	@Test
	public void floor() {
		assertEquals(fc.floor(array, 2), new Integer(2));
		array = new Integer[] {10,2,7,9,25,30,79};
		assertEquals(fc.floor(array, 200), new Integer(79));
		array = new Integer[] {10,2,7,9,25,30,79};
		assertEquals(fc.floor(array, 0), null);
		array = new Integer[] {10,2,7,9,25,30,79};
		assertEquals(fc.ceil(array, 0), new Integer(2));
		array = new Integer[] {10,2,7,9,25,30,79};
		assertEquals(fc.ceil(array, 200), null);
	}
	
	@Test
	public void nullArray() {
		assertEquals(fc.floor(null, 2), null);
		assertEquals(fc.floor(null, 200), null);
		assertEquals(fc.ceil(null, 0), null);
	}
}
