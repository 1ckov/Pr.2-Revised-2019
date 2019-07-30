package Iterator;

public class Main {

	public static void main(String[] args) {

//		PrivateOverride po = new Derived();
//		po.surprise();
//		
//		StaticSuper sup2 = new StaticSub();
//		System.out.println(sup2.staticGet());
//		System.out.println(sup2.getDynamic());
		A a = new A();
		A b = new B();
		a = b;
		C c = new C();
		c.doThis(a);
		c.doThis(b);
	}

}
