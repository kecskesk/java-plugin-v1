package bar;

import java.util.ArrayList;
import java.util.List;

public class StreamForEachDemo {
	private List<Integer> numbers = new ArrayList<>();

	public void testOne() {
		for (Integer integer : numbers) {
			useFloat(integer.floatValue());
		}
	}

	public void testTwo() {
		for (int i = 0; i < numbers.size(); i++) {
			useFloat(numbers.get(i).floatValue());
		}
	}
	
	public void testThree() {
		numbers.forEach((integer) -> {
			useFloat(integer.floatValue());
		});
	}
	
	private void useFloat(float number) {
		System.out.println(number);
	}
}
