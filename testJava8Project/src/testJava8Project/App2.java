package testJava8Project;

import java.util.Optional;
import java.util.Random;

import testJava8Project.i18n.IInterface;
import testJava8Project.i18n.Language;

public class App2 {
	private MyAnonymusClass instance = new MyAnonymusClass();
	private final Random random = new Random();

	public static void main(String[] args) {
		App2 appInstance = new App2();

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
		
		boolean checkDualLanguages = false;
		boolean useDictionary = true;
		Language testLanguageOne = language;
		Language testLanguageTwo = testLanguageOne;

		if (!checkDualLanguages && language == null || useDictionary) {
			return "null";
		}

		if (!checkDualLanguages && testLanguageOne == null || useDictionary) {
			return "null";
		}

		if (!checkDualLanguages && testLanguageTwo == null || useDictionary) {
			return "null";
		}

		if (!checkDualLanguages && language == null || useDictionary) {
			return "null";
		}

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
}
