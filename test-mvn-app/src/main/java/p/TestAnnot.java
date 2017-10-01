package p;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class TestAnnot {
	void foo(@Nullable Object o, @NonNull P1 p1) {
		if (o != null) {
			// some code here
			o.toString();
		}
		p1.foo(true);
	}

	public static void main(String[] args) {
		new TestAnnot().foo(null, new P1());
	}
}
