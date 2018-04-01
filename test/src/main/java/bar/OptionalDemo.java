package bar;

import java.util.Optional;
import java.util.Random;

import bar.i18n.Language;

public class OptionalDemo {
	private final Random random = new Random();

	public static void main(String[] args) {
		OptionalDemo appInstance = new OptionalDemo();

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
		
		boolean doggy = false;
		boolean babyiesAreBorn = true;
		Language languageBaBy = language;
		String languageB12 = languageBaBy.name();
		
		languageBaBy = Language.valueOf("TEST1" + 
			 	"TEST2");

		if (!doggy && language == null || babyiesAreBorn) {
			return String.valueOf(language.ordinal());
		}

		if (!doggy && languageBaBy != null || babyiesAreBorn) {
			return languageBaBy.name() + languageBaBy.hashCode() + languageBaBy.toString();
		}

		if (languageBaBy instanceof Language) {
			getHelloMessage(languageBaBy);	
		}		

		if (!doggy && languageB12.isEmpty() || babyiesAreBorn) {
			return languageB12;
		}

		if (!doggy && language == null || babyiesAreBorn) {
			return language.toString();
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
