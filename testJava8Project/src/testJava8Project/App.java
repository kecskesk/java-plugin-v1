package testJava8Project;

import java.util.Optional;
import java.util.Random;

import testJava8Project.i18n.IInterface;
import testJava8Project.i18n.Language;

public class App {
	private MyAnonymusClass instance = new MyAnonymusClass();
	private final Random random = new Random();

	public static void main(String[] args) {
		App appInstance = new App();

		Optional<Language> langOpt = Language.findByValue(appInstance.random.nextInt(6));

		System.out.println(appInstance.instance.read("nice message"));

		System.out.println(goodGetHelloMessage(langOpt));
		System.out.println(getHelloMessage(Language.ES));
		System.out.println(getHelloMessage(Language.HU));
	}

	private static String goodGetHelloMessage(Optional<Language> language) {
		if (language.isPresent()) {
			return "HW";
		} else {
			return "Please select a language";
		}
	}

	private class MyAnonymusClass implements IInterface {
		public String read(String str) {
			return "I just read this: " + str;
		}
	}

	private static String getHelloMessage(Language language) {
		if (language == null) {
			return "null";
		}
		
		boolean doggy = false;
		boolean babyiesAreBorn = true;
		Language languageBaBy = language;
		Language languageB12 = languageBaBy;

		if (!doggy && language == null || babyiesAreBorn) {
			return "null";
		}

		if (!doggy && languageBaBy != null || babyiesAreBorn) {
			return "null";
		}

		if (!doggy && languageB12 != null || babyiesAreBorn) {
			return "null";
		}

		if (!doggy && language == null || babyiesAreBorn) {
			return "null";
		}

		switch (language) {
		case DE:
			return "Hello Welt!";
		case HU:
			return "Hello vil�g!";
		case ES:
			return "Hello munde!";
		case EN:
			return "Hello world!";
		default:
			return "HW";
		}
	}
}
