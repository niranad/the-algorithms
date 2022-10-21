package datastructures;

public class QueueNode<T> {
	T data;
	QueueNode<T> prevNode;
	QueueNode<T> nextNode;
	
	QueueNode(T data) {
		this.data = data;
		this.prevNode = null;
		this.nextNode = null;
	}
	
	public T getData() {
		return data;
	}
	
	public QueueNode<T> getPrev() {
		return prevNode;
	}
	
	public QueueNode<T> getNext() {
		return nextNode;
	}

}
