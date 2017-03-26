package testJava8Project;

import testJava8Project.i18n.Language;

public class App {
	private MyAnonymusClass instance;
	
	public static void main(String[] args) {
		System.out.println(getHelloMessage(Language.DE));
		System.out.println(getHelloMessage(Language.ES));
		System.out.println(getHelloMessage(Language.HU));		
	}
	
	private static String getHelloMessage(Language language) {
		switch (language) {
		case DE:
			return "Hello Welt!";
		case HU:
			return "Hello világ!";
		case ES:
			return "Hello munde!";
		case EN:
			return "Hello world!";
		default:
			return "HW";
		}
	}
	
	private abstract class MyAnonymusClass {
		
	}
}
