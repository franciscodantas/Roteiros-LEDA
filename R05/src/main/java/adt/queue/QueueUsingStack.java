package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		try {
			stack1.push(element);
		} catch (StackOverflowException e) {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) throw new QueueUnderflowException();
		T head = null;
		try {
			head = stack1.pop();
		} catch (StackUnderflowException e) {}
		while(!isEmpty()) {
			head = this.stack1.top();
			try {
				swapStack(stack1,stack2);
			} catch (StackOverflowException | StackUnderflowException e) {}
		}
		while(!this.stack2.isEmpty()) {
			try {
				swapStack(stack2,stack1);
			} catch (StackOverflowException | StackUnderflowException e) {}
		}
		return head;
	}

	@Override
	public T head() {
		T head = null;
		while(!isEmpty()) {
			try {
				swapStack(stack1,stack2);
			} catch (StackOverflowException | StackUnderflowException e) {}
		}
		head = stack2.top();
		while(!this.stack2.isEmpty()) {
			try {
				swapStack(stack2,stack1);
			} catch (StackOverflowException | StackUnderflowException e) {}
		}
		return head;
		
	}
	
	/**
	 * Faz a troca de um stack para outro.
	 * 
	 * @param s1 stack que passa os elementos.
	 * @param s2 stack que recebe os elementos.
	 * @throws StackOverflowException esse erro não ocorre, os stacks são de mesmo tamanho.
	 * @throws StackUnderflowException caso o que passa seja vazio.
	 */
	private void swapStack(Stack<T> s1, Stack<T> s2) throws StackOverflowException, StackUnderflowException {
		s2.push(s1.pop());
	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack1.isFull();
	}

}
