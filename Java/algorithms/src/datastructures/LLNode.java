package datastructures;

public class LLNode<T> {
	T value;
	LLNode<T> next;

	LLNode(T value) {
		this.value = value;
		this.next = null;
	}
	
	public T getValue() {
		return value;
	}
	
	public LLNode<T> getNext() {
		return next;
	}
}
