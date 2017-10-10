package bar;
import foo.*;
public class B {
    void unused1() { unused2(); }
    void unused2() { unused1(); }
    public static void main(String... args) {
         C1 c = new foo.C1();
         c.test(null, false, true);
         /* if (c == null)
              new B().unused1(); */
    }
}
