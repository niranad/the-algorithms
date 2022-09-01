package datastructures;

public class LLNode<T> {
	T value;
	LLNode<T> next;

	public LLNode(T value) {
		this.value = value;
		this.next = null;
	}
	
	public T getValue() {
		return value;
	}
	
	public LLNode<T> getNext() {
		return next;
	}
	
	public void setNext(LLNode<T> next) {
		this.next = next;
	}
}
