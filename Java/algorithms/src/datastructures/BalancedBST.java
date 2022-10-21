package datastructures;

import java.util.ArrayList;
import java.util.Collections;

public class BalancedBST<T extends Comparable<T>> extends BinarySearchTree<T> {
	private ArrayList<T> list;
	
	public BalancedBST() {
		super();
		list = new ArrayList<>();
	}
	
	@Override
	public T insert(T data) {
		if (search(data) == null || !search(data).equals(data)) {
			list.add(data);
			Collections.sort(list);
			super.insert(data);
			
			if (size() >= 3) {
				setRoot(null);
				super.insert(list.get(list.size() / 2));
				
				Collections.shuffle(list);
				list.forEach(el -> super.insert(el));
			}
			
			return data;
		}
		return null;
	}
	
	@Override
	public boolean delete(T data) {
		if (search(data) != null) {
			super.delete(data);
			list.remove(data);
			setRoot(null);
			
			Collections.sort(list);
			super.insert(list.get(list.size() / 2));
			
			Collections.shuffle(list);
			list.forEach(el -> super.insert(el));
			
			return true;
		}
		return false;
	}
}
