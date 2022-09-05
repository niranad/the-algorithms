package datastructures;

public class DLLNode<T> {
	T data;
	DLLNode<T> next;
	DLLNode<T> prev;
	
	public DLLNode(T data) {
		this.data = data;
	}
	
	public DLLNode(T data, DLLNode<T> prev) {
		this(data);
		this.prev = prev;
	}
	
	public DLLNode(T data, DLLNode<T> prev, DLLNode<T> next) {
		this(data, prev);
		this.next = next;
	}
	
	public T getData() {
		return data;
	}
	
	public DLLNode<T> getNext() {
		return next;
	}
	
	public void setNext(DLLNode<T> next) {
		this.next = next;
	}
	
	public DLLNode<T> getPrev() {
		return prev;
	}
	
	public void setPrev(DLLNode<T> prev) {
		this.prev = prev;
	}
	
	public String toString() {
		DLLNode<T> current = this;
		String s = "";
		
		while (current != null) {
			s += current.getData() + ", ";
			current = current.getNext();
		}
		
		return s.isEmpty() ? "" : s.substring(0, s.length() - 2);
	}
}
