package adt.bst;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import adt.bt.BTNode;

public class StudentBSTManipulationTest {

	private BSTImpl<Integer> tree;
	private BSTImpl<Integer> tree2;
	
	private BSTImpl<Integer> tree2Filhos;
	private BSTImpl<Integer> tree1Filho;
	private BSTImpl<Integer> tree0Filho;
	
	private BSTImpl<Integer> treeRaiz2Filhos;
	private BSTImpl<Integer> treeRaiz1Filho;
	private BSTImpl<Integer> treeRaiz0Filho;
	
	private BSTImpl<Integer> treeRandom;
	
	private BSTImpl<Integer> treeSimilar1;
	private BSTImpl<Integer> treeSimilar2;

	private BSTImpl<Integer> treeNotSimilar1;
	private BSTImpl<Integer> treeNotSimilar2;

	
	
	private SimpleBSTManipulation<Integer> manipulator;
	
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
		
		Integer[] arrayRandom = {23, 12, 10, -123, 24, 25, 0, 3, 1, -20, 50, 21, 
				-100, 200, 1000, 2024, 20345, 1000234, -12345, 2, 245, 2111, -25};
		for (int i : arrayRandom) {
			treeRandom.insert(i);
		}
		
		Integer[] arraySimilar1 = {100, 50, 101, 200, 75, 250};
		for (int i : arraySimilar1) {
			treeSimilar1.insert(i);
		}
		
		Integer[] arraySimilar2 = {0, -4, 2, 3, -2, 25};
		for (int i : arraySimilar2) {
			treeSimilar2.insert(i);
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
		
		treeRandom = new BSTImpl<>();
		
		treeSimilar1 = new BSTImpl<>();
		treeSimilar2 = new BSTImpl<>();
		
		manipulator = new SimpleBSTManipulationImpl<Integer>();
	}
	
	@Test
	public void equals() {
		fillTree();
		
		assertTrue(manipulator.equals(tree, tree));
		assertTrue(manipulator.equals(tree2, tree2));
		assertTrue(manipulator.equals(tree2Filhos, tree2Filhos));
		assertTrue(manipulator.equals(tree0Filho, tree0Filho));
		assertTrue(manipulator.equals(treeRaiz1Filho, treeRaiz1Filho));
		assertTrue(manipulator.equals(treeRaiz0Filho, treeRaiz0Filho));
		assertTrue(manipulator.equals(treeRaiz2Filhos, treeRaiz2Filhos));
		
		assertFalse(manipulator.equals(tree, tree2));
		
		assertFalse(manipulator.equals(tree2Filhos, tree1Filho));
		assertFalse(manipulator.equals(treeRaiz2Filhos, treeRaiz1Filho));
		assertFalse(manipulator.equals(treeRaiz2Filhos, treeRaiz0Filho));
		assertFalse(manipulator.equals(treeRaiz1Filho, treeRaiz0Filho));
	}
	@Test
	public void kth() {
		fillTree();
		
		assertEquals(new Integer(-40), (Integer) manipulator.orderStatistic(tree, 1));
		assertEquals(tree.maximum().getData(), (Integer) manipulator.orderStatistic(tree, tree.size()));
		assertEquals(null, (Integer) manipulator.orderStatistic(tree, 134));
		
		Integer[] arrayRandom = {23, 12, 10, -123, 24, 25, 0, 3, 1, -20, 50, 21, 
				-100, 200, 1000, 2024, 20345, 1000234, -12345, 2, 245, 2111, -25};
		
		Arrays.sort(arrayRandom);
		
		for(int i = arrayRandom.length; i > 0; i--) {
			assertEquals(arrayRandom[i - 1], manipulator.orderStatistic(treeRandom,i));
		}
		
		for(int i = 0; i < arrayRandom.length; i++) {
			assertEquals(arrayRandom[i], manipulator.orderStatistic(treeRandom,i + 1));
		}
	}
	
	@Test
	public void similarity() {
		fillTree();
		assertTrue(manipulator.isSimilar(treeSimilar1, treeSimilar2));
	}
}

