package uebung2;

public class StringElement implements Element {
	private String value;

	public StringElement(String value){
		this.value = value;
	}
	
	
	@Override
	public int compareTo(Element e) {
		return ((StringElement)e).getValue().compareTo(this.value);
	}

	@Override
	public Element clone() {
		return new StringElement(value);
	}

	@Override
	public void print() {
		System.out.print(value + " ");
		
	}

	@Override
	public Element getVal() {
		return this;
	}
	
	public String getValue(){
		return this.value;
	}


}
