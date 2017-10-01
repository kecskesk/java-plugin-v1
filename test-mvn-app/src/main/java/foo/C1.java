package foo;
public class C1 {
    public String test(Object in, boolean f1, boolean f2) {
         if (f1) in = null;
         if (f2) {
			if (in != null) {
				return in.toString();
			}
         }
         return "";
    }
}
