package adt.linkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentDoubleLinkedListTest extends StudentLinkedListTest {

	private DoubleLinkedList<Integer> lista3;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

		// Lista com 1 elemento.
		lista3.insert(1);
	}

	private void getImplementations() {
		lista1 = new DoubleLinkedListImpl<Integer>();
		lista2 = new DoubleLinkedListImpl<Integer>();
		lista3 = new DoubleLinkedListImpl<Integer>();
	}

	// Métodos de DoubleLinkedList

	@Test
	public void testInsertFirst() {

		((DoubleLinkedList<Integer>) lista1).insertFirst(4);
		((DoubleLinkedList<Integer>) lista1).insertFirst(null);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista1.toArray());
		
		DoubleLinkedListImpl<Integer> lista4 = new DoubleLinkedListImpl<Integer>();
		lista4.insertFirst(29);
		Assert.assertArrayEquals(new Integer[] {29}, lista4.toArray());
	}
	
	@SuppressWarnings("removal")
	@Test
	public void seach() {
		this.lista1.remove(new Integer(1));
		Assert.assertEquals(new Integer(2), this.lista1.search(new Integer(2)));
	}
	
	@Test
	public void remove() {
		Assert.assertEquals(3, lista1.size());
		lista1.remove(2);
		lista1.remove(1);
		Assert.assertEquals(1, lista1.size());
		lista1.remove(3);
		Assert.assertEquals(0, lista1.size());
		lista1.remove(3);
		Assert.assertEquals(0, lista1.size());
		lista1.remove(null);
	}

	@Test
	public void testRemoveFirst() {
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] { 2, 1 }, lista1.toArray());
		((DoubleLinkedList<Integer>) lista3).removeFirst();
		Assert.assertArrayEquals(new Integer[] {}, lista3.toArray());
		((DoubleLinkedList<Integer>) lista2).removeFirst();
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
	}

	@Test
	public void testRemoveLast() {
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertArrayEquals(new Integer[] { 3, 2 }, lista1.toArray());
		((DoubleLinkedList<Integer>) lista3).removeLast();
		Assert.assertArrayEquals(new Integer[] {}, lista3.toArray());
		((DoubleLinkedList<Integer>) lista2).removeLast();
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
	}
	
	
}