package testJava8Project.i18n;

import java.util.Arrays;
import java.util.Optional;

public enum Language {
	HU(0),
	EN(1),
	ES(2),
	DE(3);
	
	private final int code;
	Language(int code)  { this.code = code; }
	public int getCode() { return code; }
	
	public static Optional<Language> findByValue(int code) {
		return Arrays.asList(Language.values()).stream()
				.filter(lan -> lan.getCode() == code)
				.findFirst();
	}
}
