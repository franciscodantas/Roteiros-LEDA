package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if(element != null) {
			int i = 0;
			while(i < this.table.length) {
				int hash = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, i);
				if(this.table[hash] == null || this.table[hash].equals(element) || this.table[hash].equals(deletedElement)) {
					this.table[hash] = element;
					this.elements++;
					break;
				}
				i++;
				this.COLLISIONS++;
			}
			if(i == this.table.length) {
				throw new HashtableOverflowException();
			}
		}
	}

	@Override
	public void remove(T element) {
		if(element != null) {
			int i = 0;
			while(i < this.table.length && !this.isEmpty()) {
				int hash = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, i);
				if(this.table[hash] == null) {
					break;
				}
				else if(this.table[hash].equals(element)) {
					this.table[hash] = this.deletedElement;
					this.elements--;
				}
				i++;
			}
		}
	}

	@Override
	public T search(T element) {
		if(element != null) {
			int i = 0;
			while(i < this.table.length && !this.isEmpty()) {
				int hash = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, i);
				if(this.table[hash] == null) {
					break;
				}
				else if(this.table[hash].equals(element)) {
					return (T) this.table[hash];
				}
				i++;
			}
		}
		return null;
	}

	@Override
	public int indexOf(T element) {
		if(element != null) {
			int i = 0;
			while(i < this.table.length && !this.isEmpty()) {
				int hash = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, i);
				if(this.table[hash] == null) {
					break;
				}
				else if(this.table[hash].equals(element)) {
					return hash;
				}
				i++;
			}
		}
		return -1;
	}
}
