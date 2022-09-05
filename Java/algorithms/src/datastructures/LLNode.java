package datastructures;

public class LLNode<T> {
	T data;
	LLNode<T> next;

	public LLNode(T value) {
		this.data = value;
		this.next = null;
	}
	
	public T getData() {
		return data;
	}
	
	public LLNode<T> getNext() {
		return next;
	}
	
	public void setNext(LLNode<T> next) {
		this.next = next;
	}
	
	public String toString() {
		LLNode<T> current = this;
		String s = "";
		
		while (current != null) {
			s += current.getData() + ", ";
			current = current.getNext();
		}
		
		return s.isBlank() ? s : s.substring(0, s.length() - 2);
	}
}
