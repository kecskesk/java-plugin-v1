package foo;

import java.util.Optional;

public class C1 {
	public String test(Optional<String> in, Boolean f1, boolean f2) {
		if (f1 == null) {
			if (f2) in = Optional.of(null);
			String s4;
			s4 = in.get();
			return s4;
		}
			
		if (f2) {
			if (!in.isPresent()) {
				if (f1.booleanValue()) {
					test(Optional.of("234"), true, false);
				}
				return in.get().toString();
			}
			String s3 = in.get();
			return s3;
		}
		return in.get();
	}
}
