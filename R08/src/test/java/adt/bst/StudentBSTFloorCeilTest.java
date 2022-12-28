package adt.bst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.bst.extended.FloorCeilBSTImpl;

public class StudentBSTFloorCeilTest {
	
	private FloorCeilBSTImpl treeRandom;

		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		
		Integer[] array2 = { 6, 23, 34, 5, 9, 2, 0, 76, 12, 67, 32, 40 };
		
		Integer[] array2Filhos = { 100, 150, 50, 250, 125};
		
		Integer[] array1Filho = { 100, 150, 50, 250};
	
		Integer[] array0Filho = { 100, 150, 50};
		
		Integer[] arrayRaiz2Filhos = { 100, 150, 50};
		
		Integer[] arrayRaiz1Filho = { 100, 50};
	
		Integer[] arrayRaiz0Filho = {100};

		Integer[] arrayRandom = {23, 12, 10, -123, 24, 25, 0, 3, 1, -20, 50, 21, 
				-100, 200, 1000, 2024, 20345, 1000234, -12345, 2, 245, 2111, -25};
		
		Integer[] arraySimilar1 = {100, 50, 101, 200, 75, 250};
		
		Integer[] arraySimilar2 = {0, -4, 2, 3, -2, 25};
	

	@Before
	public void setUp() {
		treeRandom = new FloorCeilBSTImpl();
	}
	
	@Test
	public void floor() {
		assertEquals(new Integer(12), treeRandom.floor(arrayRandom,12));
		assertEquals(new Integer(3), treeRandom.floor(arrayRandom,3));
		assertEquals(new Integer(2), treeRandom.floor(arrayRandom,2));
		assertEquals(new Integer(20345), treeRandom.floor(arrayRandom,1000233));
		assertEquals(new Integer(1000234), treeRandom.floor(arrayRandom,1000234));
		assertEquals(new Integer(-12345), treeRandom.floor(arrayRandom,-12345));
		assertEquals(new Integer(0), treeRandom.floor(arrayRandom, 0.5));
		assertEquals(new Integer(12), treeRandom.floor(arrayRandom,12.0001));
	}
	
	@Test
	public void ceil() {
		//{23, 12, 10, -123, 24, 25, 0, 3, 1, -20, 50, 21, 
//		-100, 200, 1000, 2024, 20345, 1000234, -12345, 2, 245, 2111, -25};
		
		assertEquals(new Integer(12), treeRandom.ceil(arrayRandom,12));		
		assertEquals(new Integer(3), treeRandom.ceil(arrayRandom,3));
		assertEquals(new Integer(2), treeRandom.ceil(arrayRandom,2));
		assertEquals(null, treeRandom.ceil(arrayRandom,1000235));
		assertEquals(new Integer(1000234), treeRandom.ceil(arrayRandom,1000234));
		assertEquals(new Integer(-12345), treeRandom.ceil(arrayRandom,-12345));
		assertEquals(new Integer(1), treeRandom.ceil(arrayRandom, 0.5));
		assertEquals(new Integer(21), treeRandom.ceil(arrayRandom,12.0001));
		assertEquals(new Integer(12), treeRandom.ceil(arrayRandom,11.99999));
	}
	
	@Test
	public void ceilFloorVazio() {
		assertEquals(null, treeRandom.ceil(new Integer[] {}, 12));
		assertEquals(null, treeRandom.floor(new Integer[] {}, 12));
	}
	
	@Test
	public void ceilFloor1() {
		assertEquals(new Integer(34), treeRandom.ceil(new Integer[] {34}, 12));
		assertEquals(null, treeRandom.floor(new Integer[] {34}, 12));
	}
}

