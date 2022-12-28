package adt.bst;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import adt.bt.BTNode;

public class StudentBSTTest {

	private BSTImpl<Integer> tree;
	private BSTImpl<Integer> tree2;
	
	private BSTImpl<Integer> tree2Filhos;
	private BSTImpl<Integer> tree1Filho;
	private BSTImpl<Integer> tree0Filho;
	
	private BSTImpl<Integer> treeRaiz2Filhos;
	private BSTImpl<Integer> treeRaiz1Filho;
	private BSTImpl<Integer> treeRaiz0Filho;
	
	private BTNode<Integer> NIL = new BTNode<Integer>();

	private void fillTree() {
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		for (int i : array) {
			tree.insert(i);
		}
		
		Integer[] array2 = { 6, 23, 34, 5, 9, 2, 0, 76, 12, 67, 32, 40 };
		for (int i : array2) {
			tree2.insert(i);
		}
		
		Integer[] array2Filhos = { 100, 150, 50, 250, 125};
		for (int i : array2Filhos) {
			tree2Filhos.insert(i);
		}
		
		Integer[] array1Filho = { 100, 150, 50, 250};
		for (int i : array1Filho) {
			tree1Filho.insert(i);
		}
		
		Integer[] array0Filho = { 100, 150, 50};
		for (int i : array0Filho) {
			tree0Filho.insert(i);
		}
		
		Integer[] arrayRaiz2Filhos = { 100, 150, 50};
		for (int i : arrayRaiz2Filhos) {
			treeRaiz2Filhos.insert(i);
		}
		
		Integer[] arrayRaiz1Filho = { 100, 50};
		for (int i : arrayRaiz1Filho) {
			treeRaiz1Filho.insert(i);
		}
		
		Integer[] arrayRaiz0Filho = {100};
		for (int i : arrayRaiz0Filho) {
			treeRaiz0Filho.insert(i);
		}
		
	}

	@Before
	public void setUp() {
		tree = new BSTImpl<>();
		tree2 = new BSTImpl<>();
		
		tree2Filhos = new BSTImpl<>();
		tree1Filho = new BSTImpl<>();
		tree0Filho = new BSTImpl<>();
		
		treeRaiz1Filho= new BSTImpl<>();
		treeRaiz0Filho= new BSTImpl<>();
		treeRaiz2Filhos= new BSTImpl<>();
	}

	@Test
	public void testInit() {
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());
		assertEquals(-1, tree.height());

		assertEquals(NIL, tree.getRoot());

		assertArrayEquals(new Integer[] {}, tree.order());
		assertArrayEquals(new Integer[] {}, tree.preOrder());
		assertArrayEquals(new Integer[] {}, tree.postOrder());

		assertEquals(NIL, tree.search(12));
		assertEquals(NIL, tree.search(-23));
		assertEquals(NIL, tree.search(0));

		assertEquals(null, tree.minimum());
		assertEquals(null, tree.maximum());

		assertEquals(null, tree.sucessor(12));
		assertEquals(null, tree.sucessor(-23));
		assertEquals(null, tree.sucessor(0));

		assertEquals(null, tree.predecessor(12));
		assertEquals(null, tree.predecessor(-23));
		assertEquals(null, tree.predecessor(0));
	}

	@Test
	public void testMinMax() {
		tree.insert(6);
		assertEquals(new Integer(6), tree.minimum().getData());
		assertEquals(new Integer(6), tree.maximum().getData());

		tree.insert(23);
		assertEquals(new Integer(6), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(-34);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(5);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(9);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());
	}

	@Test
	public void testSucessorPredecessor() {

		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(null, tree.predecessor(-40));
		assertEquals(new Integer(-34), tree.sucessor(-40).getData());

		assertEquals(new Integer(-40), tree.predecessor(-34).getData());
		assertEquals(new Integer(0), tree.sucessor(-34).getData());

		assertEquals(new Integer(-34), tree.predecessor(0).getData());
		assertEquals(new Integer(2), tree.sucessor(0).getData());

		assertEquals(new Integer(0), tree.predecessor(2).getData());
		assertEquals(new Integer(5), tree.sucessor(2).getData());
	}

	@Test
	public void testSize() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		int size = 12;
		assertEquals(size, tree.size());

		while (!tree.isEmpty()) {
			tree.remove(tree.getRoot().getData());
			assertEquals(--size, tree.size());
		}
	}

	@Test
	public void testHeight() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		Integer[] preOrder = new Integer[] { 6, -34, -40, 5, 2, 0, 23, 9, 12,
				76, 67, 232 };
		//assertArrayEquals(preOrder, tree.preOrder());
		assertEquals(4, tree.height());

		tree.remove(0);
		assertEquals(3, tree.height());

		tree.remove(2);
		assertEquals(3, tree.height());
	}

	@Test
	public void testRemove() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		Integer[] order = { -40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(6);
		order = new Integer[] { -40, -34, 0, 2, 5, 9, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(9);
		order = new Integer[] { -40, -34, 0, 2, 5, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		assertEquals(NIL, tree.search(6));
		assertEquals(NIL, tree.search(9));

	}

	@Test
	public void testSearch() {

		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(new Integer(-40), tree.search(-40).getData());
		assertEquals(new Integer(-34), tree.search(-34).getData());
		assertEquals(NIL, tree.search(2534));
	}
	
	// ---------------------------------------------------------------
	@Test
	public void preOrder() {
		fillTree();
		
		System.out.println(Arrays.toString(tree.preOrder()));
	}
	
	@Test
	public void testRemoveControlado() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232
		
		tree2Filhos.remove(150);
				
		//  100, 150, 50, 250
		tree1Filho.remove(150);
		tree1Filho.remove(100);
		tree1Filho.remove(100);
		tree1Filho.remove(50);
		tree1Filho.remove(250);
		
		
		tree0Filho.remove(150);

	}
	
}
