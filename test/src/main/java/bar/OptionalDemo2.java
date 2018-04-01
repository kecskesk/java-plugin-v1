package bar;

import java.util.Optional;
import java.util.Random;

import bar.i18n.Language;

public class OptionalDemo2 {
	private final Random random = new Random();

	public static void main(String[] args) {
		OptionalDemo2 appInstance = new OptionalDemo2();

		Optional<Language> langOpt = Language.findByValue(appInstance.random.nextInt(6));

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
