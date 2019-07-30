package Iterator;

public class PrivateOverride {

	private void f() {System.out.println("private f");}
	
	public void surprise() {
		PrivateOverride po = new Derived();
		po.f();
	}
	
	
}

