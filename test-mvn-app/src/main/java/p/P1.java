package p;

public class P1 {
	void foo(boolean b) {
		Object o = goo();
		if (b) {
			o = null;
		}
		Object p = o;
		if (p != null) {
			p.toString();
		}
	}

	Object goo() {
		Object o = new Object();
		int i = 1;
		while (i <= 10) {
			if (o != null) {
				System.out.println("o at Iteration #" + i + " is-" + o.toString());
			}
			if (i == 5) {
				o = null;
			} else {
				o = new Integer(i);
			}
			i++;
		}
		return o;
	}
}
