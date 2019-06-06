package uebung4New;

public class StringElement implements Element, Comparable<String>{
	private String value;
	
	public StringElement(String value) {
		this.value = value;
	}
	
	@Override
	public int compareTo(Element other) {
		this.value.compareTo(other.getVal());
		return 0;
	}

	@Override
	public void print() {
		System.out.println(this.value);
	}

	@Override
	public String getVal() {
		return this.value;
	}
	@Override 
	public Element clone() {
		return new StringElement(this.value);
	}
	
	@Override
	public int hashCode() {
		return value.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof StringElement) {
			return this.value.equals(((StringElement)o).getVal());
		}
		else if(o.equals(this)){
			return true;
		}
		else return false;
	}

	@Override
	public int compareTo(String that) {
		return this.value.compareTo(that);
	}
	
	@Override
	public String toString() {
		return this.value;
	}

}
