package bar;

import java.util.ArrayList;

public class DiamondAnonymDemo {
	public abstract class TestClass<T> {
		protected T myObject;
		
		public TestClass(T myObject) {
			this.myObject = myObject;
		}
		
		abstract String print();
	}
	
	public void initTestClassWithDouble() {
		TestClass<Double> integerTest = new TestClass<>(23d) {
			String print() {
				return myObject.toString();
			}
		};
		System.out.println(integerTest.print());
	}
	
	public void initTestClassWithInteger() {
		ArrayList<Integer> my_list = new ArrayList<Integer>() {
			
		};
		
		TestClass<Integer> integerTest = new TestClass<Integer>(23) {
			String print() {
				return Integer.valueOf(myObject.hashCode()).toString();
			}
		};
		System.out.println(integerTest.print());
	}
	
	public static void main(String[] args) {
		DiamondAnonymDemo demoApp = new DiamondAnonymDemo();
		demoApp.initTestClassWithDouble();
		demoApp.initTestClassWithInteger();
	}
}
