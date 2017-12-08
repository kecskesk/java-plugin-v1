package bar;

import java.util.Arrays;
import java.util.List;

public class ImmutableDemo2 {
	public static List<String> exampleArraysAsList() {
		List<String> list = Arrays.asList("foo", "bar", "baz");
		return list;
	}
}
