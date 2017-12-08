package bar;

import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImmutableDemo {
	public static Set<String> exampleSet() {
		Set<String> set = new HashSet<>();
		set.add("foo");
		set.add("bar");
		set.add("baz");
		set = Collections.unmodifiableSet(set);
		return set;
	}

	public static Set<String> exampleSetException() {
		Set<String> set = new HashSet<>();
		set.add("foo");
		set.add("bar");
		set.add(null);
		set = Collections.unmodifiableSet(set);
		return set;
	}

	public static List<String> exampleTransformable() {
		List<String> list = new ArrayList<>();
		list.add("foo");
		list.add("bar");
		list.add("baz");
		return list;
	}

	public static List<String> exampleTransformableListSurely() {
		List<String> list = new ArrayList<>();
		list.add("foo");
		list.add("bar");
		list.add("baz");
		list = Collections.unmodifiableList(list);
		return list;
	}

	public static Map<String, Object> exampleTransformableMapSurely() {
		Map<String, Object> map = new HashMap<>();
		map.put("foo", new Object());
		map.put("bar", "String");
		map.put("baz", 1);
		map = Collections.unmodifiableMap(map);
		return map;
	}

	public static List<String> exampleArraysAsList() {
		List<String> list = Arrays.asList("foo", "bar", "baz");
		return list;
	}

	public static List<String> exampleArraysAsListException() {
		List<String> list = Arrays.asList("foo", "bar", null);
		return list;
	}

	public static Set<String> exampleSet2() {
		return Collections.unmodifiableSet(new HashSet<String>() {
			{
				add("foo");
				add("bar");
				add("baz");
			}
		});
	}

	public static Map<String, Object> exampleMap2() {
		return Collections.unmodifiableMap(new HashMap<String, Object>() {
			{
				put("foo", new Object());
				put("bar", "String");
				put("baz", 1);
			}
		});
	}

	public static Set<String> exampleSet3() {
		return Stream.of("foo", "bar", "baz")
				.collect(Collectors.collectingAndThen(toSet(), Collections::unmodifiableSet));
	}

	public static List<String> exampleList3() {
		String[] hello = { "Hello" };
		List<String> values = Arrays.asList(hello);
		hello[0] = "World";
		return values;
	}

	public static Set<String> exampleSet4() {
		return new HashSet<>(Arrays.asList("Hello", "World", "from", "Java"));
	}

	public static Set<String> exampleSet4Exception() {
		return new HashSet<>(Arrays.asList("Hello", "World", "from", null));
	}

	public static Set<String> exampleSet4Exception2() {
		return new HashSet<>(Arrays.asList("Hello", "World", "from", "from"));
	}

	public static Set<String> exampleSet4Exception3() {
		return Set.of("Hello", "World", "from", "from");
	}

	public static List<String> exampleList4() {
		return new ArrayList<>();
	}

	public static List<String> exampleList5() {
		List<String> emptyList = new ArrayList<>();
		List<String> immutableList = Collections.unmodifiableList(emptyList);
		return immutableList;
	}

	public static List<String> exampleList5Exception() {
		List<String> emptyList = new ArrayList<>();
		List<String> immutableList = Collections.unmodifiableList(emptyList);
		immutableList.add("now that is not nice");
		return immutableList;
	}

	public static Map<String, Object> exampleMap3() {
		Map<String, Object> emptyMap = new HashMap<>();
		Map<String, Object> immutableEmptyMap = Collections.unmodifiableMap(emptyMap);
		return immutableEmptyMap;
	}

	public static Map<String, Object> exampleMap3Exception() {
		Map<String, Object> emptyMap = new HashMap<>();
		Map<String, Object> immutableEmptyMap = Collections.unmodifiableMap(emptyMap);
		immutableEmptyMap.put("now that is not nice", 1);
		return immutableEmptyMap;
	}

	public static Set<String> exampleSet5() {
		Set<String> emptySet = new HashSet<>();
		Set<String> immutableSet = Collections.unmodifiableSet(emptySet);
		return immutableSet;
	}
}
