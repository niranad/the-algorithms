package datastructures;

public class MapNode<T, E> {
	T key;
	E value;
	MapNode<T, E> prevNode;
	MapNode<T, E> nextNode;
	
	MapNode(T key, E value) {
		this.key = key;
		this.value = value;
		this.prevNode = null;
		this.nextNode = null;
	}
	
	public T getKey() {
		return key;
	}
	
	public E getValue() {
		return value;
	}
}
