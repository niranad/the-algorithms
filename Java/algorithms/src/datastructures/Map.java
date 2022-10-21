package datastructures;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;

public class Map<T, E> extends AbstractMap<T, E> {
	private MapNode<T, E> firstNode;
	private MapNode<T, E> lastNode;

	public Map() {
		firstNode = lastNode = null;
	}
	
	public Map(Map<T, E> map) {
		putAll(map);
	}

	@Override
	public E put(T key, E value) {
		if (firstNode == null) {
			firstNode = lastNode = new MapNode<>(key, value);
		} else if (containsKey(key)) {
			searchMapByKey(firstNode, key).value = value;
		} else {
			if (firstNode == lastNode) {
				lastNode = new MapNode<>(key, value);
				firstNode.nextNode = lastNode;
				lastNode.prevNode = firstNode;
			} else {
				lastNode.nextNode = new MapNode<>(key, value);
				lastNode.nextNode.prevNode = lastNode;
				lastNode = lastNode.nextNode;
			}
		}

		return value;
	}

	public void putAll(Map<T, E> map) {
		Set<Entry<T, E>> entrySet = map.entrySet();
		entrySet.forEach(ent -> put(ent.getKey(), ent.getValue()));
	}

	@Override
	public E remove(Object key) {
		E removedValue = null;

		if (containsKey(key)) {
			MapNode<T, E> nodeToRemove = searchMapByKey(firstNode, key);
			MapNode<T, E> nodeBefore = nodeToRemove.prevNode;
			MapNode<T, E> nodeAfter = nodeToRemove.nextNode;

			removedValue = nodeToRemove.value;

			if (nodeBefore != null) {
				nodeBefore.nextNode = nodeAfter;
			} else {
				firstNode = nodeAfter;
			}

			if (nodeAfter != null) {
				nodeAfter.prevNode = nodeBefore;
			} else {
				lastNode = nodeBefore;
			}
		}

		return removedValue;
	}

	@Override
	public boolean remove(Object key, Object value) {
		if (containsKey(key)) {
			MapNode<T, E> nodeToRemove = searchMapByKey(firstNode, key);

			if (nodeToRemove.value.equals(value)) {
				MapNode<T, E> nodeBefore = nodeToRemove.prevNode;
				MapNode<T, E> nodeAfter = nodeToRemove.nextNode;
				nodeBefore.nextNode = nodeAfter;
				nodeAfter.prevNode = nodeBefore;

				return true;
			}
		}

		return false;
	}

	@Override
	public E replace(T key, E value) {
		E replacedVal = null;

		if (containsKey(key)) {
			MapNode<T, E> node = searchMapByKey(firstNode, key);
			replacedVal = node.value;
			node.value = value;
		}

		return replacedVal;
	}

	@Override
	public boolean replace(T key, E oldValue, E newValue) {
		if (containsKey(key)) {
			MapNode<T, E> node = searchMapByKey(firstNode, key);

			if (Objects.equals(node.value, oldValue)) {
				node.value = newValue;
				return true;
			}
		}

		return false;
	}

	@Override
	public void replaceAll(BiFunction<? super T, ? super E, ? extends E> function) {
		Set<Entry<T, E>> entrySet = entrySet();

		entrySet.forEach(e -> {
			if (containsKey(e.getKey())) {
				searchMapByKey(firstNode, e.getKey()).value = function.apply(e.getKey(),
					e.getValue());
			}
		});
	}

	@Override
	public boolean containsKey(Object key) {
		return searchMapByKey(firstNode, key) != null;
	}

	@Override
	public boolean containsValue(Object value) {
		return searchMapByValue(firstNode, value) != null;
	}

	@Override
	public E get(Object key) {
		if (containsKey(key)) {
			return searchMapByKey(firstNode, key).value;
		}
		return null;
	}

	@Override
	public Set<T> keySet() {
		MapNode<T, E> currNode = firstNode;
		Set<T> keySet = new HashSet<>();

		while (currNode != null) {
			keySet.add(currNode.key);
			currNode = currNode.nextNode;
		}

		return keySet;
	}

	@Override
	public int size() {
		MapNode<T, E> currNode = firstNode;
		int i = 0;

		while (currNode != null) {
			currNode = currNode.nextNode;
			++i;
		}

		return i;
	}

	@Override
	public boolean isEmpty() {
		return firstNode == null;
	}

	@Override
	public Set<Entry<T, E>> entrySet() {
		MapNode<T, E> currNode = firstNode;
		Set<Entry<T, E>> entrySet = new HashSet<>();

		while (currNode != null) {
			Entry<T, E> entry = Map.entry(currNode.key, currNode.value);
			entrySet.add(entry);
			currNode = currNode.nextNode;
		}

		return entrySet;
	}

	@Override
	public void clear() {
		firstNode = lastNode = null;
	}

	private MapNode<T, E> searchMapByKey(MapNode<T, E> node, Object key) {
		while (node != null && !Objects.equals(node.key, key)) {
			node = node.nextNode;
		}

		return node;
	}

	private MapNode<T, E> searchMapByValue(MapNode<T, E> node, Object value) {
		while (node != null && !Objects.equals(node.value, value)) {
			node = node.nextNode;
		}

		return node;
	}

	public static <T, E> Entry<T, E> entry(T key, E value) {
		return new AbstractMap.SimpleEntry<T, E>(key, value);
	}
}
