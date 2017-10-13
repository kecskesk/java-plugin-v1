package bar;

import org.junit.Test;

public class TestImmutableDemo {
	
	@Test
	public void testExampleSet() {
		ImmutableDemo.exampleSet();
	}

	@Test
	public void testExampleSetException() {
		ImmutableDemo.exampleSetException();
	}

	@Test
	public void testExampleTransformable() {
		ImmutableDemo.exampleTransformable();
	}

	@Test
	public void testExampleTransformableListSurely() {
		ImmutableDemo.exampleTransformableListSurely();
	}

	@Test
	public void testExampleTransformableMapSurely() {
		ImmutableDemo.exampleTransformableMapSurely();
	}

	@Test
	public void testExampleArraysAsList() {
		ImmutableDemo.exampleArraysAsList();
	}

	@Test
	public void testExampleArraysAsListException() {
		ImmutableDemo.exampleArraysAsListException();
	}

	@Test
	public void testExampleSet2() {
		ImmutableDemo.exampleSet2();
	}

	@Test
	public void testExampleMap2() {
		ImmutableDemo.exampleMap2();
	}

	@Test
	public void testExampleSet3() {
		ImmutableDemo.exampleSet3();
	}

	@Test
	public void testExampleList3() {
		ImmutableDemo.exampleList3();
	}

	@Test
	public void testExampleSet4() {
		ImmutableDemo.exampleSet4();
	}

	@Test
	public void testExampleSet4Exception() {
		ImmutableDemo.exampleSet4Exception();
	}

	@Test
	public void testExampleSet4Exception2() {
		ImmutableDemo.exampleSet4Exception2();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExampleSet4Exception3() {
		ImmutableDemo.exampleSet4Exception3();
	}

	@Test
	public void testExampleList4() {
		ImmutableDemo.exampleList4();
	}

	@Test
	public void testExampleList5() {
		ImmutableDemo.exampleList5();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testExampleList5Exception() {
		ImmutableDemo.exampleList5Exception();
	}

	@Test
	public void testExampleMap3() {
		ImmutableDemo.exampleMap3();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testExampleMap3Exception() {
		ImmutableDemo.exampleMap3Exception();
	}

	@Test
	public void testExampleSet5() {
		ImmutableDemo.exampleSet5();
	}
}
