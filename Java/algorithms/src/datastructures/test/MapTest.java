package datastructures.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import datastructures.Map;

class MapTest {

	@Test
	void test() {
		Map<String, Integer> map = new Map<>();
		assertEquals(map.size(), 0);
		assertTrue(map.isEmpty());

		// Test put(key, value)
		String[] words = { "march", "purple", "yesterday", "faithful", "diligent", "love",
			"marriage", "family", "love", "economy", "march", "programming", "commute" };
		Arrays.stream(words)
			.forEach(word -> assertEquals(map.put(word, word.length()), word.length()));

		assertEquals(map.size(), words.length - 2);
		assertFalse(map.isEmpty());

		// Test containsKey(key)
		assertTrue(map.containsKey("programming"));
		assertTrue(map.containsKey("march"));
		assertFalse(map.containsKey("lovely"));

		// Test containsValue(value)
		assertTrue(map.containsValue(4));
		assertTrue(map.containsValue(11));
		assertFalse(map.containsValue(20));

		// Test replace(key, value)
		assertNotNull(map.replace("commute", 8));
		assertNull(map.replace("education", 9));

		// Test replace(key, oldValue, newValue)
		assertFalse(map.replace("commute", 7, 8));
		assertTrue(map.replace("commute", 8, 7));
		assertFalse(map.replace("song", 3, 4));

		// Test remove(key)
		assertEquals(map.remove("yesterday"), 9);
		assertFalse(map.containsKey("yesterday"));
		assertNull(map.remove("today"));
		assertEquals(map.remove("march"), 5);
		assertFalse(map.containsKey("march"));
		assertEquals(map.remove("commute"), 7);
		assertFalse(map.containsKey("commute"));
		assertEquals(map.size(), words.length - 5);

		// Test get(key)
		assertEquals(map.get("programming"), 11);
		assertEquals(map.get("love"), 4);

		// Test keySet()
		Set<String> keySet = map.keySet();
		Set<String> set = new HashSet<String>();

		Arrays.asList("purple", "faithful", "diligent", "love", "marriage", "family",
			"economy", "programming").forEach(key -> set.add(key));

		assertEquals(keySet, set);

		// Test entrySet()
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		Set<Entry<String, Integer>> test = new HashSet<>();

		Arrays
			.asList("purple", "faithful", "diligent", "love", "marriage", "family",
				"economy", "programming")
			.forEach(key -> test
				.add(new AbstractMap.SimpleEntry<String, Integer>(key, key.length())));

		assertEquals(entrySet, test);

		// Test replaceAll(bifunction)
		BiFunction<String, Integer, Integer> function = (x, y) -> {
			if (x.length() == 6) {
				return y * 2;
			}
			return y;
		};

		map.replaceAll(function);
		assertEquals(map.get("family"), 12);
		assertEquals(map.get("purple"), 12);
		assertNotEquals(map.get("economy"), 14);

		// Test putAll(map)
		Map<String, Integer> newMap = new Map<>();
		newMap.put("justice", 7);
		newMap.put("clarity", 7);
		newMap.put("kindness", 8);
		newMap.put("posterity", 9);
		newMap.put("family", 6);

		assertEquals(map.size(), 8);
		map.putAll(newMap);
		assertEquals(map.size(), 12);

		// Test clear()
		map.clear();
		assertEquals(map.size(), 0);
		assertTrue(map.isEmpty());
		assertTrue(map.entrySet().isEmpty());
		assertTrue(map.keySet().isEmpty());
		
		// Test Map(map)
		Map<String, Integer> map2 = new Map<>(newMap);
		assertEquals(map2.size(), 5);
		assertFalse(map2.isEmpty());
		assertTrue(map2.containsKey("posterity"));
		assertTrue(map2.containsKey("kindness"));
		assertEquals(map2.get("justice"), 7);
		assertTrue(map2.containsValue(6));
	}
}
