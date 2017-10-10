package p;

import org.eclipse.jdt.annotation.NonNull;
import java.util.Optional;

public class TestAnnot {
	void foo(Optional<Object> o, @NonNull P1 p1) {
		if (!o.isPresent()) {
			// some code here
			o.toString();
		}
		p1.foo(true);
	}

	public static void main(String[] args) {
		new TestAnnot().foo(null, new P1());
	}
}
