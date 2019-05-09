package uebung3;


public class StringElement implements Element {
	private String value;

	public StringElement(String value) {
		this.value = value;
	}

	@Override
	public int compareTo(Element e) {
		return ((StringElement) e).getValue().compareTo(this.value);
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

	public String getValue() {
		return this.value;
	}
	@Override
	public int hashCode(){
		int charsum = 0;
		for(int i = 0; i < value.length();i++){
			charsum += value.charAt(i);
		}
//		char char1 = this.value.charAt(0);
//		char char2 = this.value.charAt(1);
//		char char3 = this.value.charAt(2);
//		char char4 = this.value.charAt(3);
//		int charsum = char1 + char2 + char3 + char4;
		return Math.abs(charsum);
//		return Math.abs(value.hashCode());
		
	}
	public boolean equals(Object o){
		if(o instanceof StringElement){
			return this.value.equals(((StringElement) o).getValue());
		}
		return false;
	}

}
