package foo;
public class C2 extends C1 {
    @Override
    public String test(Object in, boolean f1, boolean f2) {
         return zork(in);
    }
    String zork(Object in) { return ""; }
}
