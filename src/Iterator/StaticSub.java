package Iterator;

public class StaticSub extends StaticSuper {
	public static String staticGet() {
		return "Sub staticGet()";
	}
	@Override
	public String getDynamic() {
		return "Sub getDynamic()";
	}

}
