package uebung4New;

public class IntElement implements Element, Comparable<Element>{
	private int value;
	
	public IntElement(int value) {
		this.value = value;
	}
	
	public IntElement() {
		this(0);
	}
	

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int compareTo(Element other) {
		
		if(other instanceof IntElement) {
			
			int thisValue = this.getValue();
			int otherValue = ((IntElement) other).getValue();
			
			return (otherValue - thisValue);
			
		
		}
		
		return 0;
	}
	
	public Object clone() {
		return new IntElement(value);
	}

	@Override
	public void print() {
		System.out.println(value);
	}

	@Override
	public String getVal() {
		return Integer.valueOf(value).toString();
	}
	
	@Override
	public String toString() {
		return this.getVal();
	}


}
